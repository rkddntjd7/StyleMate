package project.stylemate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.stylemate.service.BookMarkService;

import java.util.regex.Matcher;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.delete;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookMarkController.class)
class BookMarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookMarkService bookMarkService;

    @Test
    public void 북마크추가() throws Exception {
        //given
        Long memberId = 1L;
        Long styleId = 1L;

        //when
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/styles/{styleId}/bookmark", styleId));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.returnCode", is("0000")))
                .andExpect(jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));

        verify(bookMarkService, times(1)).add(eq(memberId), eq(styleId));
    }

    @Test
    public void 북마크삭제() throws Exception {
        //given
        Long styleId = 1L;
        Long bookMarkId = 1L;

        //when
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/styles/{styleId}/bookmarks/{bookMarkId}", styleId, bookMarkId));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.returnCode", is("0000")))
                .andExpect(jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));

        verify(bookMarkService, times(1)).delete(eq(bookMarkId));
    }
}