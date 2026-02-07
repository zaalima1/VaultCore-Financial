package com.example.siya.controller;

import com.example.siya.entity.Admin;
import com.example.siya.service.AdminService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminControllers {

    @Autowired
    private AdminService adminService;
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("account", new Admin());
        return "account";
    } 
    @PostMapping("/save")
    public String saveAdmin(
            @ModelAttribute("account") Admin admin,
            HttpSession session
    ) {
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
        return "dashboard";
    }
}
