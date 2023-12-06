package project.stylemate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import project.stylemate.dto.comment.SaveCommentRequest;
import project.stylemate.dto.comment.UpdateCommentRequest;
import project.stylemate.entity.Comment;
import project.stylemate.entity.Member;
import project.stylemate.entity.Style;
import project.stylemate.service.CommentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    @Test
    public void 댓글조회() throws Exception {
        //given
        Long styleId = 1L;

        Comment comment1 = Comment.builder()
                .id(1L)
                .content("test")
                .member(new Member())
                .style(new Style())
                .build();

        Comment comment2 = Comment.builder()
                .id(1L)
                .content("test2")
                .member(new Member())
                .style(new Style())
                .build();

        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);

        when(commentService.getAllCommentsByStyleId(eq(styleId), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(comments));


        //when
        ResultActions perform = mockMvc.perform(get("/api/v1/styles/{styleId}/comments", styleId));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.returnCode", is("0000")))
                .andExpect(jsonPath("$.returnMessage", is("요청에 성공하였습니다.")))
                .andExpect(jsonPath("$.data.contents[0].content", is("test")))
                .andExpect(jsonPath("$.data.contents[1].content", is("test2")));
    }

    @Test
    public void 댓글저장() throws Exception {
        //given
        Long styleId = 1L;
        SaveCommentRequest request = SaveCommentRequest.builder()
                .content("test comment")
                .build();

        ResultActions perform = mockMvc.perform(post("/api/v1/styles/{styleId}/comments", styleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.returnCode", is("0000")))
                .andExpect(jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));

        verify(commentService, times(1)).save(eq(styleId), eq("test comment"), any(Long.class));
    }

    @Test
    public void 댓글수정() throws Exception {
        Long styleId = 1L;
        Long commentId = 1L;
        UpdateCommentRequest request = UpdateCommentRequest.builder()
                .content("test comment")
                .build();

        ResultActions perform = mockMvc.perform(patch("/api/v1/styles/{styleId}/comments/{commentId}", styleId, commentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.returnCode", is("0000")))
                .andExpect(jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));

        verify(commentService, times(1)).update(eq(commentId), eq("test comment"));
    }

    @Test
    public void 댓글삭제() throws Exception {
        //given
        Long styleId = 1L;
        Long commentId = 1L;

        //when
        ResultActions perform = mockMvc.perform(delete("/api/v1/styles/{styleId}/comments/{commentId}", styleId, commentId));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.returnCode", is("0000")))
                .andExpect(jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));

        verify(commentService, times(1)).delete(eq(commentId));
    }


}