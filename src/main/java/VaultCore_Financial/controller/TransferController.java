package VaultCore_Financial.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import VaultCore_Financial.service.TransferService;

@Controller
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    // ✅ Open Transfer Page
    @GetMapping("/transfer")
    public String transferPage() {
        return "transfer";
    }

   
}
