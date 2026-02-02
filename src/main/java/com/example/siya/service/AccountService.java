package com.example.siya.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.siya.entity.Account;
import com.example.siya.entity.Transaction;
import com.example.siya.repository.AccountRepository;
import com.example.siya.repository.TransactionRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
    @Transactional
    public void sendMoneyByAccountNumber(String fromAcc, String toAcc, Double amount) {

        Account sender = accountRepository.findByAccountNumber(fromAcc)
            .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account receiver = accountRepository.findByAccountNumber(toAcc)
            .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        accountRepository.save(sender);
        accountRepository.save(receiver);
    }
    public Double getMyBalance(String email) {
        Account account = accountRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return account.getBalance();
    }


   
}
