package VaultCore_Financial.controller;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
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
    public String login(@ModelAttribute LoginRequest request,
                        Model model) {

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

    /**
     * STEP 2 → Verify OTP and redirect
     */
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp,
                            RedirectAttributes ra,
                            Model model) {

        try {
            // 🔐 Verify OTP
            AuthResponse res = authService.verifyOtp(email, otp);

            // ✅ Flash success message
            ra.addFlashAttribute("msg", "Login Successful ✅");

            // ✅ ROLE-BASED REDIRECT
            if ("ADMIN".equalsIgnoreCase(res.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/dashboard-page";
            }

        } catch (Exception e) {
            // ❌ If OTP fails → stay on OTP screen
            model.addAttribute("otpStage", true);
            model.addAttribute("email", email);
            model.addAttribute("msg", "Invalid OTP ❌ Try again");

            return "login";
        }
    }

    // ================= DASHBOARD =================

    @GetMapping("/dashboard-page")
    public String dashboardPage() {
        return "dashboard";
    }

    // ================= BALANCE =================

    @GetMapping("/balance")
    public String balance(@RequestParam String accountNumber, RedirectAttributes ra) {

        try {
            BigDecimal balance = balanceService.getBalance(accountNumber);
            ra.addFlashAttribute("balance", balance);

        } catch (Exception e) {
            ra.addFlashAttribute("balanceMsg", "Account Not Found");
        }

        return "redirect:/dashboard-page";
    }
}
