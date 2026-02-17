package VaultCore_Financial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import VaultCore_Financial.entity.Account;
import VaultCore_Financial.entity.Auditable;
import VaultCore_Financial.repo.AccountRepository;



@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountRepository accountRepo;

    public AccountController(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @PostMapping("/create")
    @Auditable(module = "ACCOUNT", action = "ACCOUNT_CREATED")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        if (accountRepo.existsByAccountNumber(account.getAccountNumber())) {
            return ResponseEntity.badRequest()
                    .body("Account already exists");
        }

        accountRepo.save(account);
        return ResponseEntity.ok("Account created successfully");
    }
    @Auditable(module = "account", action = "account_check")
    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<String> getBalance(@PathVariable String accountNumber) {

        Account account = accountRepo.findByAccountNumber(accountNumber)
                .orElse(null);

        if (account == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Account number not found!");
        }

        return ResponseEntity.ok("₹ " + account.getBalance());
    }
}
