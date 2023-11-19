package project.stylemate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import project.stylemate.service.LikeService;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LikeController.class)
class LikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LikeService likeService;

    @Test
    public void 좋아요추가() throws Exception {
        //given
        Long memberId = 1L;
        Long styleId = 1L;

        //when
        ResultActions perform = mockMvc.perform(post("/api/v1/styles/{styleId}/favorites", styleId));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.returnCode", is("0000")))
                .andExpect(jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));

        verify(likeService, times(1)).addLike(eq(memberId), eq(styleId));
    }

    @Test
    public void 댓글삭제() throws Exception {
        //given
        Long styleId = 1L;
        Long likeId = 1L;

        //when
        ResultActions perform = mockMvc.perform(delete("/api/v1/styles/{styleId}/{likeId}", styleId, likeId));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.returnCode", is("0000")))
                .andExpect(jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));

        verify(likeService, times(1)).delete(eq(likeId));
    }
}