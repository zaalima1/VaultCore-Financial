package VaultCore_Financial.service;



import org.springframework.stereotype.Service;

import VaultCore_Financial.repo.AccountRepository;
import VaultCore_Financial.repo.LedgerRepository;

import java.math.BigDecimal;

@Service

public class BalanceService {

    private final AccountRepository accountRepository;
    private final LedgerRepository ledgerRepository;
    
    
    

    public BalanceService(AccountRepository accountRepository,LedgerRepository ledgerRepository) {
		super();
		this.accountRepository = accountRepository;
		this.ledgerRepository = ledgerRepository;
	}




	public BigDecimal getBalance(String accountNumber) {
        var account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return ledgerRepository.findByAccount_Id(account.getId())
                .stream()
                .map(e -> e.getEntryType().equals("CREDIT") ? e.getAmount() : e.getAmount().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
