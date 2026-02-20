package VaultCore_Financial.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import VaultCore_Financial.entity.Account;
import VaultCore_Financial.entity.LedgerEntry;
import VaultCore_Financial.repo.AccountRepository;
import VaultCore_Financial.repo.LedgerRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class TransferService {

    private final AccountRepository accountRepo;
    private final LedgerRepository ledgerRepo;
    private final BalanceService balanceService;

    public TransferService(AccountRepository accountRepo,
                           LedgerRepository ledgerRepo,
                           BalanceService balanceService) {
        this.accountRepo = accountRepo;
        this.ledgerRepo = ledgerRepo;
        this.balanceService = balanceService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transfer(String fromAcc, String toAcc, BigDecimal amount) {

        Account sender = accountRepo.findByAccountNumber(fromAcc)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Account receiver = accountRepo.findByAccountNumber(toAcc)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        BigDecimal senderBalance = balanceService.getBalance(fromAcc);

        if (senderBalance.compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        String ref = UUID.randomUUID().toString();

        ledgerRepo.save(new LedgerEntry(
                null, sender, "DEBIT", amount, ref, Instant.now()));

        ledgerRepo.save(new LedgerEntry(
                null, receiver, "CREDIT", amount, ref, Instant.now()));
    }
}
