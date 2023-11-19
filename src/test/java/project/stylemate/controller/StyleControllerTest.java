package project.stylemate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.stylemate.dto.common.SmPage;
import project.stylemate.dto.params.UpsertStyleParam;
import project.stylemate.dto.style.SaveStyleRequest;
import project.stylemate.dto.style.StyleSearchCondition;
import project.stylemate.dto.style.StyleSearchRequest;
import project.stylemate.dto.style.UpdateStyleRequest;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.enums.Gender;
import project.stylemate.service.LikeService;
import project.stylemate.service.StyleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StyleController.class)
class StyleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StyleService styleService;

    @MockBean
    private LikeService likeService;

    @Test
    public void 스타일전부조회() throws Exception {
        Long memberId = 1L;

        Style style1 = Style.builder()
                .id(1L)
                .member(new Member())
                .styleImages("images")
                .gender(Gender.FEMALE)
                .minHeight(160)
                .maxHeight(170)
                .styleCategory("CASUAL")
                .content("test")
                .styleRank(10L)
                .build();

        List<Style> styles = new ArrayList<>();
        styles.add(style1);

        when(styleService.getAllStyles(eq(memberId), any(StyleSearchCondition.class), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(styles));

        //when
        ResultActions perform = mockMvc.perform(get("/api/v1/styles")
                .param("gender", "FEMALE")
                .param("minHeight", "160"));

        //then
        perform.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnCode", is("0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));
    }

    @Test
    public void 스타일단건조회() throws Exception {
        //given
        Long styleId = 1L;

        Style style = Style.builder()
                .id(1L)
                .member(new Member())
                .styleImages("images")
                .gender(Gender.FEMALE)
                .minHeight(160)
                .maxHeight(170)
                .styleCategory("CASUAL")
                .content("test")
                .styleRank(10L)
                .build();

        when(styleService.getStyleById(eq(styleId))).thenReturn(style);

        //when
        ResultActions perform = mockMvc.perform(get("/api/v1/styles/{styleId}", styleId));

        //then
        perform.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnCode", is("0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnMessage", is("요청에 성공하였습니다.")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", is("test")));
    }

    @Test
    public void 스타일저장() throws Exception {
        //given
        Long memberId = 1L;
        SaveStyleRequest request = SaveStyleRequest.builder()
                .styleImages("images")
                .gender(Gender.FEMALE)
                .minHeight(160)
                .maxHeight(170)
                .styleCategory("CASUAL")
                .content("TEST")
                .styleRank(5L)
                .build();

        //when
        ResultActions perform = mockMvc.perform(post("/api/v1/styles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        perform.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnCode", is("0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));

        verify(styleService, times(1)).save(any(UpsertStyleParam.class));
    }

    @Test
    public void 댓글수정() throws Exception {
        //given
        Long styleId = 1L;
        UpdateStyleRequest request = UpdateStyleRequest.builder()
                .content("update test")
                .build();

        //when
        ResultActions perform = mockMvc.perform(patch("/api/v1/styles/{styleId}", styleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        perform.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnCode", is("0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));

        verify(styleService, times(1)).update(eq(styleId), any(UpsertStyleParam.class));
    }

    @Test
    public void 댓글삭제() throws Exception {
        //given
        Long styleId = 1L;

        Style style = Style.builder()
                .id(1L)
                .member(new Member())
                .styleImages("images")
                .gender(Gender.FEMALE)
                .minHeight(160)
                .maxHeight(170)
                .styleCategory("CASUAL")
                .content("test")
                .styleRank(10L)
                .build();

        //when
        ResultActions perform = mockMvc.perform(delete("/api/v1/styles/{styleId}", styleId));

        //then
        perform.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnCode", is("0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));

        verify(styleService, times(1)).delete(eq(styleId));
    }

    @Test
    public void 조회수증가() throws Exception {
        //given
        Long styleId = 1L;

        //when
        ResultActions perform = mockMvc.perform(patch("/api/v1/styles/{styleId}/increase-views", styleId));

        //then
        perform.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnCode", is("0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));
    }
}