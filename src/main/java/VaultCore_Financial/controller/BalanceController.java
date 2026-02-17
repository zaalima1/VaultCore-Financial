package VaultCore_Financial.controller;



import org.springframework.web.bind.annotation.*;

import VaultCore_Financial.entity.Auditable;
import VaultCore_Financial.service.BalanceService;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/balance")

public class BalanceController {

    private final BalanceService balanceService;
    private final ExecutorService virtualThreadExecutor;
    

    public BalanceController(BalanceService balanceService, ExecutorService virtualThreadExecutor) {
		super();
		this.balanceService = balanceService;
		this.virtualThreadExecutor = virtualThreadExecutor;
	}

    @Auditable(module = "Balance", action = "Balanace_checking")
	@GetMapping("/{accountNumber}")
    public BigDecimal getBalance(@PathVariable String accountNumber) throws Exception {
        Future<BigDecimal> f = virtualThreadExecutor.submit(() -> balanceService.getBalance(accountNumber));
        return f.get();
    }
}
