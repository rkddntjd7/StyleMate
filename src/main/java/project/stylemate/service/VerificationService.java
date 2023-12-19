package project.stylemate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import project.stylemate.enums.ReturnCode;
import project.stylemate.exception.SmRequestException;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String VERIFICATION_CODE_KEY_PREFIX = "verification-code:";

    public String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }

    public void saveVerificationCode(String email, String verificationCode) {
        String key = VERIFICATION_CODE_KEY_PREFIX + email;
        redisTemplate.opsForValue().set(key, verificationCode, 5, TimeUnit.MINUTES);
    }

    public void verifyEmail(String email, String verificationCode) {
        String key = VERIFICATION_CODE_KEY_PREFIX + email;
        String savedCode = redisTemplate.opsForValue().get(key);

        if (savedCode != null && savedCode.equals(verificationCode)) {
            redisTemplate.delete(key);
        } else {
            throw new SmRequestException(ReturnCode.VERIFICATIONCODE_NOT_VALID);
        }

    }


}
