package VaultCore_Financial.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VaultCore_Financial.entity.Account;
import VaultCore_Financial.entity.Transaction;
import VaultCore_Financial.repo.AccountRepository;
import VaultCore_Financial.repo.TransactionRepository;
import jakarta.transaction.Transactional;

@Service
public class BankingService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
   private TransactionRepository txnRepo;

    @Transactional
    public Transaction sendMoney(String email, String toAccount, BigDecimal amount) {

        Account acc = accountRepo.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (acc.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient Balance");
        }

        acc.setBalance(acc.getBalance().subtract(amount));
        accountRepo.save(acc);

        Transaction txn = new Transaction();
        txn.setTxnId("TXN" + System.currentTimeMillis());
        txn.setFromAccount(acc.getAccountNumber());
        txn.setToAccount(toAccount);
        txn.setAmount(amount);
        txn.setTxnType("SEND");
        txn.setCreatedAt(LocalDateTime.now());

        return txnRepo.save(txn);
    }

    @Transactional
    public Transaction addMoney(String email, BigDecimal amount) {
Account acc = accountRepo.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        acc.setBalance(acc.getBalance().add(amount));
        accountRepo.save(acc);

        Transaction txn = new Transaction();
        txn.setTxnId("ADD" + System.currentTimeMillis());
        txn.setAmount(amount);
        txn.setTxnType("ADD");
        txn.setCreatedAt(LocalDateTime.now());

        return txnRepo.save(txn);
    }
}
