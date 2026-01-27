package VaultCore_Financial.controller;

import VaultCore_Financial.dto.LoginRequest;
import VaultCore_Financial.dto.AuthResponse;
import VaultCore_Financial.service.AuthService;
import VaultCore_Financial.service.BalanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class ViewController {

    private final AuthService authService;
    private final BalanceService balanceService;

    public ViewController(AuthService authService, BalanceService balanceService) {
        this.authService = authService;
        this.balanceService = balanceService;
    }

    @GetMapping("/login-page")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/auth/login")
    public String login(@ModelAttribute LoginRequest request, Model model) {

        try {
            AuthResponse res = authService.login(request);

            
            model.addAttribute("msg", "Login Success ✅ AccessToken: " + res.getAccessToken());

            return "dashboard";
        } catch (Exception e) {
            model.addAttribute("msg", "Login Failed ");
            model.addAttribute("loginRequest", new LoginRequest());
            return "login";
        }
    }

    @GetMapping("/balance")
    public String balance(@RequestParam String accountNumber, Model model) {
        try {
            BigDecimal balance = balanceService.getBalance(accountNumber);
            model.addAttribute("balance", balance);
        } catch (Exception e) {
            model.addAttribute("balanceMsg", "Account Not Found ");
        }
        return "dashboard";
    }
}
