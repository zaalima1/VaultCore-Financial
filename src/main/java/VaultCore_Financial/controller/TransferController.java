package VaultCore_Financial.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import VaultCore_Financial.service.TransferService;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public String transfer(@RequestParam String from,
                           @RequestParam String to,
                           @RequestParam BigDecimal amount) {
        transferService.transfer(from, to, amount);
        return "Transfer Success";
    }
}
