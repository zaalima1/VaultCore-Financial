package VaultCore_Financial.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
import VaultCore_Financial.entity.Auditable;
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

    public ViewController(
            AuthService authService,
            BalanceService balanceService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authService = authService;
        this.balanceService = balanceService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Auditable(module = "register", action = "registration")
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
        user.setRole(user.getRole() == null ? "USER" : user.getRole());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        ra.addFlashAttribute("msg", "Registration Success — Please Login");
        return "redirect:/login-page";
    }

    // ================= LOGIN =================
    @Auditable(module = "login", action = "login_page")
    @GetMapping("/login-page")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
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
    @Auditable(module = "verify_otp", action = "otp verifying")
    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestParam String email,
            @RequestParam String otp,
            HttpServletRequest request,
            RedirectAttributes ra,
            Model model
    ) {
        try {
            AuthResponse res = authService.verifyOtp(email, otp);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(() -> "ROLE_" + res.getRole())
                    );

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            HttpSession session = request.getSession(true);
            session.setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    context
            );

            ra.addFlashAttribute("msg", "Login Successful ✅");

            // ROLE-BASED REDIRECT
            if ("ADMIN".equalsIgnoreCase(res.getRole())) {
                return "redirect:/admin";
            }

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
            model.addAttribute("msg", "A new OTP has been sent.");

            return "login";
        } catch (Exception e) {
            model.addAttribute("otpStage", true);
            model.addAttribute("email", email);
            model.addAttribute("msg", "Unable to resend OTP ❌");
            return "login";
        }
    }

    // ================= DASHBOARD =================
    @Auditable(module = "dashboard", action = "Dashboard")
    @GetMapping("/dashboard-page")
    public String dashboard(Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        BigDecimal balance = balanceService.getBalance(email);
        model.addAttribute("balance", balance);

        return "dashboard";
    }
    @PostMapping("/logout")
    public String logout() {
        return "Logout successful";
    }
    
    @GetMapping("/admin")
    public  String fun1()
    {
    	return "admin2";
    }
    
}
