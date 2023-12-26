package project.stylemate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.stylemate.dto.member.CheckUsernameRequest;
import project.stylemate.repository.MemberRepository;
import project.stylemate.service.MemberService;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @Test
    public void 아이디중복검증() throws Exception {
        //given
        CheckUsernameRequest request = CheckUsernameRequest.builder()
                .username("rkddntjd8")
                .build();

        Mockito.when(memberRepository.findByUsername(request.getUsername())).thenReturn(null);

        //when
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/check-username")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        perform.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnCode", is("0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));
    }
}