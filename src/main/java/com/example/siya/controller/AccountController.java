package com.example.siya.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.siya.service.AccountService;

@CrossOrigin(origins = "*") // allow frontend JS calls from any origin
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendByAccountNumber(@RequestBody Map<String, Object> req) {
        try {
            String fromAccountNumber = req.get("fromAccountNumber").toString();
            String toAccountNumber = req.get("toAccountNumber").toString();
            Double amount = Double.parseDouble(req.get("amount").toString());

            accountService.sendMoneyByAccountNumber(fromAccountNumber, toAccountNumber, amount);

            return ResponseEntity.ok(Map.of(
                "message", "Money transferred successfully",
                "toAccount", toAccountNumber,
                "amount", amount
            ));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
