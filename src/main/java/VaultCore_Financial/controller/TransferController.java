package VaultCore_Financial.controller;

import java.security.Principal;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import VaultCore_Financial.entity.Admin;
import VaultCore_Financial.entity.Transaction;
import VaultCore_Financial.entity.User;
import VaultCore_Financial.repo.AdminRepository;
import VaultCore_Financial.service.TransactionService;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/transfer")
public class TransferController {
	private final TransactionService txnService;
    private final AdminRepository userRepository;

    public TransferController(TransactionService txnService,
                              AdminRepository userRepository) {
        this.txnService = txnService;
        this.userRepository = userRepository;
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

    @GetMapping("/total-transactions")
    public String getTotalTransactions(Principal principal, Model model) {

        String email = principal.getName();  // logged in email

        // Fetch user account number from email
        Admin user = userRepository.findByEmail(email);
        String accountNumber = user.getAccountNumber();

        List<Transaction> transactions =
                txnService.getTransactionsByAccount(accountNumber);

        model.addAttribute("transactions", transactions);

        return "transactions";
    }



    


	
}
