package VaultCore_Financial.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import VaultCore_Financial.entity.Transaction;



public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("SELECT t FROM Transaction t " +
	           "WHERE (t.fromAccount = :accountNumber OR t.toAccount = :accountNumber) " +
	           "AND t.timestamp BETWEEN :start AND :end")
	    List<Transaction> findMonthlyTransactions(
	            @Param("accountNumber") String accountNumber,
	            @Param("start") LocalDateTime start,
	            @Param("end") LocalDateTime end
	    );

}

