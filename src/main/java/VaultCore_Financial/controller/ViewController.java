package VaultCore_Financial.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import VaultCore_Financial.dto.AuthResponse;
import VaultCore_Financial.dto.LoginRequest;
import VaultCore_Financial.entity.User;
import VaultCore_Financial.repo.UserRepository;
import VaultCore_Financial.service.AuthService;
import VaultCore_Financial.service.BalanceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller

public class ViewController {

    private final AuthService authService;
    private final BalanceService balanceService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ViewController(AuthService authService,
                          BalanceService balanceService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.balanceService = balanceService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ================= REGISTER =================

    @GetMapping("/register-page")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes ra) {

        if (userRepository.existsByEmail(user.getEmail())) {
            ra.addFlashAttribute("msg", "Email already exists");
            return "redirect:/register-page";
        }

        user.setCreatedAt(Instant.now());

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        ra.addFlashAttribute("msg", "Registration Success — Please Login");
        return "redirect:/login-page";
    }

    // ================= LOGIN =================

    @GetMapping("/login-page")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    /**
     * STEP 1 → Validate email & password and send OTP
     */
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, Model model) {

        try {
            authService.initiateLogin(request);

            model.addAttribute("otpStage", true);
            model.addAttribute("email", request.getEmail());
            model.addAttribute("msg", "OTP sent successfully");

            return "login";

        } catch (Exception e) {
            model.addAttribute("msg", "Login Failed ❌ " + e.getMessage());
            return "login";
        }
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp,
                            HttpServletRequest request,
                            RedirectAttributes ra,
                            Model model) {

        try {
            AuthResponse res = authService.verifyOtp(email, otp);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(() -> "ROLE_" + res.getRole())
                    );

            // ✅ Set authentication
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            // 🔥 THIS LINE IS THE REAL FIX
            HttpSession session = request.getSession(true);
            session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context
            );

            ra.addFlashAttribute("msg", "Login Successful ✅");
            return "redirect:/dashboard-page";

        } catch (Exception e) {
            model.addAttribute("otpStage", true);
            model.addAttribute("email", email);
            model.addAttribute("msg", "Invalid OTP ❌");
            return "login";
        }
    }


    @PostMapping("/resend-otp")
    public String resendOtp(@RequestParam String email, Model model) {

        try {
            authService.resendOtp(email);

            model.addAttribute("otpStage", true);
            model.addAttribute("email", email);
            model.addAttribute("msg", "A new OTP has been sent to your email.");

            return "login";

        } catch (Exception e) {
            model.addAttribute("otpStage", true);
            model.addAttribute("email", email);
            model.addAttribute("msg", "Unable to resend OTP ❌");
            return "login";
        }
    }

    // ================= DASHBOARD =================

    @GetMapping("/dashboard-page")
    public String dashboardPages() {
        return "dashboard";
    }

   

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }
}
