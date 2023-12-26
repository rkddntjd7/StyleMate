package project.stylemate.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import project.stylemate.constants.RedisKeyConstants;
import project.stylemate.exception.SmRequestException;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VerificationServiceTest {

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private VerificationService verificationService;

    @Test
    public void 인증번호전송및저장() {
        // given
        String email = "rkddntjd7@naver.com";

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        doNothing().when(valueOperations).set(anyString(), anyString(), eq(5L), eq(TimeUnit.MINUTES));

        // when
        verificationService.saveVerificationCode(email);

        // then
        verify(emailService, times(1)).sendEmail(eq(email), anyString(), anyString());
        verify(valueOperations, times(1)).set(anyString(), anyString(), eq(5L), eq(TimeUnit.MINUTES));
    }

    @Test
    public void 인증번호검증() {
        //given
        String email = "rkddntjd7@naver.com";
        String verificationCode = "1234";

        String savedCode = "1234";
        String key = "verification-code:rkddntjd7@naver.com";

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(key)).thenReturn(savedCode);

        //when
        verificationService.verifyEmail(email, verificationCode);

        //then
        verify(redisTemplate).delete(key);
    }

    @Test
    public void 인증번호검증_실패() {
        //given
        String email = "rkddntjd7@naver.com";
        String verificationCode = "1234";

        String savedCode = "1235";
        String key = "verification-code:rkddntjd7@naver.com";

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(key)).thenReturn(savedCode);

        //when
        assertThrows(SmRequestException.class, () -> verificationService.verifyEmail(email, verificationCode));

        //then
        verify(redisTemplate, never()).delete(key);

    }
}