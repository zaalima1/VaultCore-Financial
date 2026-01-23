package VaultCore_Financial.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import VaultCore_Financial.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}
