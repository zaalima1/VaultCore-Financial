package VaultCore_Financial.repo;

import VaultCore_Financial.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByAccountNumber(String accountNumber);

    Optional<Admin> findByMobileNumber(String mobileNumber);
}
