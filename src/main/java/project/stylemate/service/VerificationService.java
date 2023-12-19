package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import project.stylemate.constants.RedisKeyConstants;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmRequestException;

import java.util.Objects;
import java.util.concurrent.TimeUnit;



@Service
@RequiredArgsConstructor
public class VerificationService {

    private final RedisTemplate<String, String> redisTemplate;
    private final EmailService emailService;

    private String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }

    public void saveVerificationCode(String email) {
        String key = RedisKeyConstants.VERIFICATION_CODE_KEY_PREFIX + email;

        String verificationCode = generateVerificationCode();

        emailService.sendEmail(email, "이메일 인증", "인증 번호: " + verificationCode);

        redisTemplate.opsForValue().set(key, verificationCode, 5, TimeUnit.MINUTES);
    }

    public void verifyEmail(String email, String verificationCode) {
        String key = RedisKeyConstants.VERIFICATION_CODE_KEY_PREFIX + email;
        String savedCode = redisTemplate.opsForValue().get(key);

        if (StringUtils.equals(savedCode, verificationCode)) {
            redisTemplate.delete(key);
        } else {
            throw new SmRequestException(ReturnCode.VERIFICATIONCODE_NOT_VALID);
        }

    }


}
