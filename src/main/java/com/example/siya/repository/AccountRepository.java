package com.example.siya.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.siya.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	 Optional<Account> findByAccountNumber(String accountNumber);
	 Optional<Account> findByUserEmail(String email);
	
}