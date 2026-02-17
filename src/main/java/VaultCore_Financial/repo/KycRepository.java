package VaultCore_Financial.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import VaultCore_Financial.entity.Admin;
import VaultCore_Financial.entity.Kyc;

@Repository
public interface KycRepository extends JpaRepository<Kyc, Long> {

    Kyc findByAccountNumber(String accountNumber);

	Kyc findByAdmin(Admin user);

	List<Kyc> findByKycStatus(String string);
}
