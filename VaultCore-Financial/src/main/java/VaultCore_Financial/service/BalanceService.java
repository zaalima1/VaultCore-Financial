package VaultCore_Financial.service;

import org.springframework.stereotype.Service;
import VaultCore_Financial.repo.AccountRepository;
import VaultCore_Financial.repo.LedgerRepository;

import java.math.BigDecimal;

@Service
public class BalanceService {

    private final AccountRepository accountRepository;
    private final LedgerRepository ledgerRepository;

    public BalanceService(AccountRepository accountRepository,
                          LedgerRepository ledgerRepository) {
        this.accountRepository = accountRepository;
        this.ledgerRepository = ledgerRepository;
    }

    public BigDecimal getBalance(String accountNumber) {

        // ✅ If account does not exist, return ZERO balance
        var accountOpt = accountRepository.findByAccountNumber(accountNumber);

        if (accountOpt.isEmpty()) {
            return BigDecimal.ZERO;
        }

        var account = accountOpt.get();

        return ledgerRepository.findByAccount_Id(account.getId())
                .stream()
                .map(entry ->
                        "CREDIT".equals(entry.getEntryType())
                                ? entry.getAmount()
                                : entry.getAmount().negate()
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
