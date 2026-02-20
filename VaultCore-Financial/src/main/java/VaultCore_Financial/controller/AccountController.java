package VaultCore_Financial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import VaultCore_Financial.entity.Admin;
import VaultCore_Financial.service.AdminService;
import VaultCore_Financial.service.AuthService;
import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/account")
public class AccountController
{
	private final AdminService authService;

	public AccountController(AdminService authService) {
	    this.authService = authService;
	}

    
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("account", new Admin());
        return "home"; // account-form.html
    }
    @PostMapping("/save")
    public String saveAdmin(
            @ModelAttribute("account") Admin admin,
            HttpSession session
    ) {
        Admin savedAdmin = authService.Admin1(admin);
        session.setAttribute("loggedUser", savedAdmin);

        return "redirect:/account/dashboard2";
    }

    @GetMapping("/dashboard2")
    public String dashboard(HttpSession session, Model model) {

        Admin user = (Admin) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/account/create";
        }

        model.addAttribute("user", user);
        return "welcome"; // welcome.html
    }
}

