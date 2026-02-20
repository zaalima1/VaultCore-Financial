package VaultCore_Financial.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import VaultCore_Financial.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String email);

<<<<<<< HEAD
	Account findByUserEmail(String email);
=======
	Optional<Account> findByUserEmail(String email);
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
}
