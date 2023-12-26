package project.stylemate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.stylemate.dto.member.Verification.SendVerificationCodeRequest;
import project.stylemate.dto.member.Verification.VerifyVerificationCodeRequest;
import project.stylemate.service.EmailService;
import project.stylemate.service.VerificationService;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserVerificationController.class)
class UserVerificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmailService emailService;

    @MockBean
    private VerificationService verificationService;

    @Test
    public void 인증번호전송() throws Exception{
        //given
        SendVerificationCodeRequest request = SendVerificationCodeRequest.builder()
                .email("rkddntjd7@naver.com")
                .build();

        //when
        ResultActions perform = mockMvc.perform(post("/api/v1/users/send-verification-code")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        perform.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnCode", is("0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));
    }

    @Test
    public void 인증번호검증() throws Exception {
        //given
        VerifyVerificationCodeRequest request = VerifyVerificationCodeRequest.builder()
                .email("rkddntjd7@naver.com")
                .verificationCode("1234")
                .build();

        //when
        ResultActions perform = mockMvc.perform(post("/api/v1/users/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        perform.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnCode", is("0000")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnMessage", is("요청에 성공하였습니다.")));
    }

}