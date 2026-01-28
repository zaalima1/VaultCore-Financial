package VaultCore_Financial.controller;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    public ViewController(AuthService authService,BalanceService balanceService,UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.balanceService = balanceService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    
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

        ra.addFlashAttribute("msg", "Registration Success  Please Login");
        return "redirect:/login-page";
    }

    
    @GetMapping("/login-page")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, RedirectAttributes ra) {

        try {
            AuthResponse res = authService.login(request);

            ra.addFlashAttribute("msg", "Login Success ✅");
            ra.addFlashAttribute("token", res.getAccessToken());

            return "redirect:/dashboard-page";

        } catch (Exception e) {
            ra.addFlashAttribute("msg", "Login Failed ❌ " + e.getMessage());
            return "redirect:/login-page";
        }
    }

    
    @GetMapping("/dashboard-page")
    public String dashboardPage() {
        return "dashboard";
    }

    
    @GetMapping("/balance")
    public String balance(@RequestParam String accountNumber, RedirectAttributes ra) {

        try {
            BigDecimal balance = balanceService.getBalance(accountNumber);
            ra.addFlashAttribute("balance", balance);

        } catch (Exception e) {
            ra.addFlashAttribute("balanceMsg", "Account Not Found ");
        }

        return "redirect:/dashboard-page";
    }
}
