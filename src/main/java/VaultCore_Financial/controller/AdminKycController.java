package VaultCore_Financial.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import VaultCore_Financial.entity.Kyc;
import VaultCore_Financial.repo.KycRepository;

@Controller
@RequestMapping("/admin/kyc")
public class AdminKycController {

    private final KycRepository kycRepo;

    public AdminKycController(KycRepository kycRepo) {
        this.kycRepo = kycRepo;
    }

    @GetMapping("/pending")
    public String viewPending(Model model) {
        model.addAttribute("kycList", kycRepo.findAll());
        return "admin-kyc-list";
    }

    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Long id) {
        Kyc kyc = kycRepo.findById(id).orElse(null);
        if (kyc != null) {
            kyc.setKycStatus("APPROVED");
            kycRepo.save(kyc);
        }
        return "redirect:/admin/kyc/pending";
    }

    @GetMapping("/reject/{id}")
    public String reject(@PathVariable Long id) {
        Kyc kyc = kycRepo.findById(id).orElse(null);
        if (kyc != null) {
            kyc.setKycStatus("REJECTED");
            kycRepo.save(kyc);
        }
        return "redirect:/admin/kyc/pending";
    }
    
    @GetMapping("/admin/pending-kyc")
    public String viewPendingKyc(Model model) {

       List<Kyc> pendingList = kycRepo.findByKycStatus("PENDING");

        model.addAttribute("kycList", pendingList);

        return "admin-kyc-list";
    }

    
    
}

