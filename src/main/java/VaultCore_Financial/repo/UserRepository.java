package VaultCore_Financial.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import VaultCore_Financial.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	 Optional<User> findByEmail(String email);

	 boolean existsByEmail(String email);
}
