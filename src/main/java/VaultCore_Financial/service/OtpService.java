package VaultCore_Financial.service;

import org.springframework.stereotype.Service;
import VaultCore_Financial.entity.OtpToken;
import VaultCore_Financial.repo.OtpRepository;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class OtpService {

    private final OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public String generateOtp(String email) {
        String otp = String.valueOf(new SecureRandom().nextInt(900000) + 100000);

        OtpToken token = new OtpToken();
        token.setEmail(email);
        token.setOtp(otp);
        token.setExpiresAt(Instant.now().plus(5, ChronoUnit.MINUTES));
        token.setUsed(false);

        otpRepository.save(token);

        System.out.println("OTP for " + email + " = " + otp); // simulate email
        return otp;
    }
    

    public boolean verifyOtp(String email, String otp) {
        var tokenOpt = otpRepository.findTopByEmailOrderByIdDesc(email);

        if (tokenOpt.isEmpty()) return false;

        OtpToken token = tokenOpt.get();

        if (token.isUsed()) return false;
        if (token.getExpiresAt().isBefore(Instant.now())) return false;

        if (!token.getOtp().equals(otp)) return false;

        token.setUsed(true);
        otpRepository.save(token);
        return true;
    }
}
