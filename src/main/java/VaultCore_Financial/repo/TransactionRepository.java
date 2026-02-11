package VaultCore_Financial.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import VaultCore_Financial.entity.Transaction;

public interface TransactionRepository   extends JpaRepository<Transaction, Long>{

}
