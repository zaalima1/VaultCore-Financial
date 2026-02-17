package VaultCore_Financial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import VaultCore_Financial.service.TransactionService;



@Controller
@RequestMapping("/transfer")
public class TransferController {
    private final TransactionService txnService;
    public TransferController(TransactionService txnService) {
        this.txnService = txnService;
    }
    
    @GetMapping("/send1")
    public String sendPage() {
        return "send";
    }
    @PostMapping("/send")
    public String sendMoney(
            @RequestParam String fromAccount,
            @RequestParam String toAccount,
            @RequestParam double amount,
            Model model
    ) {
        try {
            txnService.sendMoney(fromAccount, toAccount, amount);    
            model.addAttribute("fromAccount", fromAccount);
            model.addAttribute("toAccount", toAccount);
            model.addAttribute("amount", amount);
            return "bill";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "send";
        }
    }

	

	
}
