package VaultCore_Financial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import VaultCore_Financial.entity.LedgerEntry;

public interface LedgerRepository extends JpaRepository<LedgerEntry, Long>{

	 List<LedgerEntry> findByAccount_Id(Long accountId);
}
