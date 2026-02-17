package VaultCore_Financial.controller;
import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import VaultCore_Financial.entity.Admin;
import VaultCore_Financial.entity.Auditable;
import VaultCore_Financial.entity.Kyc;
import VaultCore_Financial.repo.AdminRepository;
import VaultCore_Financial.repo.KycRepository;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/kyc")
public class KycController {

    private final KycRepository kycRepo;
    private final AdminRepository adminRepo;

   

    
    

    // 🔹 2️⃣ Put constructor here (INSIDE class, below fields)
    public KycController(KycRepository kycRepo,
                         AdminRepository adminRepo) {
        this.kycRepo = kycRepo;
        this.adminRepo = adminRepo;
    }
    @Auditable(module = "KYC", action = "KYC_Application")
    @GetMapping("/apply")
    
    public String showKycForm(Model model) {
        model.addAttribute("kyc", new Kyc());
        return "kyc-form";
    }
    @PostMapping("/submit")
    public String dashboard1(HttpSession session, Model model,Kyc kyc) {

        Admin user = (Admin) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/admin/create";
        }
        
        kyc.setAdmin(user);
        kyc.setKycStatus("PENDING");

        kycRepo.save(kyc);

        return "kyc-success";
    }

}
