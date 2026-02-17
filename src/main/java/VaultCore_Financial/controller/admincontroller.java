package VaultCore_Financial.controller;




import jakarta.servlet.http.HttpSession;

import java.awt.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import VaultCore_Financial.entity.Admin;
import VaultCore_Financial.entity.Auditable;
import VaultCore_Financial.entity.Loan;
import VaultCore_Financial.repo.LoanRepository;
import VaultCore_Financial.service.AdminService;

@Controller
@RequestMapping("/admin")
public class admincontroller {

    @Autowired
    private AdminService adminService;
    private LoanRepository loanRepository;
    @Auditable(module = "Loan", action = "Loan_Application")
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("account", new Admin());
        return "user_account";
    } 
    @Auditable(module = "Admin", action = "Admin_count")
    @PostMapping("/save")
    public String saveAdmin(@ModelAttribute("account") Admin admin,
                            HttpSession session) {

        Admin savedAdmin = adminService.Admin1(admin);
        session.setAttribute("loggedUser", savedAdmin);

        return "redirect:/admin/dashboard2";
    }

    
    @GetMapping("/dashboard2")
    public String dashboard1(HttpSession session, Model model) {

        Admin user = (Admin) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/admin/create";
        }
        model.addAttribute("user", user);
        return "dash";
    }
        

    }

