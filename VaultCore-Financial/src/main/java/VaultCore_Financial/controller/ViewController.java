package VaultCore_Financial.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

<<<<<<< HEAD
=======
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
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

<<<<<<< HEAD
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

=======
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
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
<<<<<<< HEAD

    // ================= REGISTER =================

=======
    
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
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

    @GetMapping("/login-page")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
<<<<<<< HEAD

=======
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, Model model) {
        try {
            authService.initiateLogin(request);
<<<<<<< HEAD
            model.addAttribute("otpStage", true);
            model.addAttribute("email", request.getEmail());
            model.addAttribute("msg", "OTP sent successfully");
=======

            model.addAttribute("otpStage", true);
            model.addAttribute("email", request.getEmail());
            model.addAttribute("msg", "OTP sent successfully");

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
            return "login";
        } catch (Exception e) {
            model.addAttribute("msg", "Login Failed ❌ " + e.getMessage());
            return "login";
        }
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestParam String email,
            @RequestParam String otp,
            HttpServletRequest request,
            RedirectAttributes ra,
            Model model
    ) {
<<<<<<< HEAD

=======
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
        try {
            AuthResponse res = authService.verifyOtp(email, otp);

            UsernamePasswordAuthenticationToken authentication =
<<<<<<< HEAD
                new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    List.of(() -> "ROLE_" + res.getRole())
                );
=======
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(() -> "ROLE_" + res.getRole())
                    );
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            HttpSession session = request.getSession(true);
            session.setAttribute(
<<<<<<< HEAD
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context
=======
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    context
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
            );

            ra.addFlashAttribute("msg", "Login Successful ✅");

<<<<<<< HEAD
            // ✅ ROLE-BASED REDIRECT (THIS IS THE FIX)
            if ("ADMIN".equalsIgnoreCase(res.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/dashboard-page";
            }

=======
            // ROLE-BASED REDIRECT
            if ("ADMIN".equalsIgnoreCase(res.getRole())) {
                return "redirect:/admin/dashboard";
            }

            return "redirect:/dashboard-page";

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
        } catch (Exception e) {
            model.addAttribute("otpStage", true);
            model.addAttribute("email", email);
            model.addAttribute("msg", "Invalid OTP ❌");
            return "login";
        }
    }

<<<<<<< HEAD

=======
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
    @PostMapping("/resend-otp")
    public String resendOtp(@RequestParam String email, Model model) {
        try {
            authService.resendOtp(email);
<<<<<<< HEAD
            model.addAttribute("otpStage", true);
            model.addAttribute("email", email);
            model.addAttribute("msg", "A new OTP has been sent.");
=======

            model.addAttribute("otpStage", true);
            model.addAttribute("email", email);
            model.addAttribute("msg", "A new OTP has been sent.");

>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
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
    public String dashboard(Model model) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        BigDecimal balance = balanceService.getBalance(email);
        model.addAttribute("balance", balance);

        return "dashboard";
    }
}
