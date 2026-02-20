package VaultCore_Financial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import VaultCore_Financial.entity.OtpToken;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpToken, Long> {
    Optional<OtpToken> findTopByEmailOrderByIdDesc(String email);
}
