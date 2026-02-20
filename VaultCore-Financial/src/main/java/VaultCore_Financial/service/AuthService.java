package VaultCore_Financial.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import VaultCore_Financial.config.JwtService;
import VaultCore_Financial.dto.AuthResponse;
import VaultCore_Financial.dto.LoginRequest;
import VaultCore_Financial.entity.User;
import VaultCore_Financial.repo.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final OtpService otpService;

<<<<<<< HEAD
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       RefreshTokenService refreshTokenService,
                       OtpService otpService) {
=======
    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            RefreshTokenService refreshTokenService,
            OtpService otpService
    ) {
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.otpService = otpService;
    }

<<<<<<< HEAD
    /**
     * STEP 1: Validate email & password, then send OTP
     */
    public void initiateLogin(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        otpService.generateOtp(user.getEmail());
    }

    /**
     * STEP 2: Verify OTP and generate tokens
     */
    public AuthResponse verifyOtp(String email, String otp) {

        if (!otpService.verifyOtp(email, otp)) {
            throw new RuntimeException("Invalid OTP");
=======
    // =================================================
    // STEP 1: Validate email & password → Send OTP
    // =================================================
    public void initiateLogin(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Generate & send OTP
        otpService.generateOtp(user.getEmail());
    }

    // =================================================
    // STEP 2: Verify OTP → Generate Tokens
    // =================================================
    public AuthResponse verifyOtp(String email, String otp) {

        boolean isOtpValid = otpService.verifyOtp(email, otp);
        if (!isOtpValid) {
            throw new RuntimeException("Invalid or expired OTP");
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

<<<<<<< HEAD
        String accessToken = jwtService.generateAccessToken(email, 15);
=======
        String accessToken = jwtService.generateAccessToken(email, 15); // minutes
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
        String refreshToken = refreshTokenService.create(user).getToken();

        return new AuthResponse(
                accessToken,
                refreshToken,
                user.getRole()
        );
    }

<<<<<<< HEAD
    /**
     * 🔁 RESEND OTP
     */
=======
    // =================================================
    // 🔁 RESEND OTP
    // =================================================
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
    public void resendOtp(String email) {

        // Ensure user exists
        userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

<<<<<<< HEAD
        // Generate & send NEW OTP
=======
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
        otpService.generateOtp(email);
    }
}
