package VaultCore_Financial.repo;

import VaultCore_Financial.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByAccountNumber(String accountNumber);

    Optional<Admin> findByMobileNumber(String mobileNumber);

    Admin findByUsername(String name);

    Page<Admin> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

    Page<Admin> findByStatus(String status, Pageable pageable);

    Page<Admin> findByUsernameContainingIgnoreCaseAndStatus(
            String username, String status, Pageable pageable);
}
