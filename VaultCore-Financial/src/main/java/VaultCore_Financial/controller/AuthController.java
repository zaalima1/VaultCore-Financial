//package VaultCore_Financial.controller;
//
//import VaultCore_Financial.dto.LoginRequest;
//import VaultCore_Financial.entity.User;
//import VaultCore_Financial.repo.UserRepository;
//import VaultCore_Financial.service.OtpService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class AuthController {
//
//    private final UserRepository userRepository;
//    private final OtpService otpService;
//
//    public AuthController(UserRepository userRepository, OtpService otpService) {
//        this.userRepository = userRepository;
//        this.otpService = otpService;
//    }
//
//    // ================= LOGIN =================
//    @PostMapping("/login")
//    public String initiateLogin(
//            @ModelAttribute LoginRequest request,
//            HttpServletRequest httpRequest
//    ) {
//
//        // 🔐 Clear old session (VERY IMPORTANT)
//        HttpSession oldSession = httpRequest.getSession(false);
//        if (oldSession != null) {
//            oldSession.invalidate();
//        }
//
//        // ✅ Fetch user correctly (Optional → User)
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
//
//        // ✅ Validate password
//        if (!user.getPassword().equals(request.getPassword())) {
//            throw new RuntimeException("Invalid email or password");
//        }
//
//        // ✅ Create new session
//        HttpSession session = httpRequest.getSession(true);
//        session.setAttribute("userId", user.getId());
//        session.setAttribute("email", user.getEmail());
//
//        // ✅ Generate & send OTP
//        otpService.generateOtp(user.getEmail());
//
//        return "login"; // OTP page
//    }
//
//    // ================= LOGOUT =================
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/login";
//    }
//}
