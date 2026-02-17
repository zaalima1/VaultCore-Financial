package VaultCore_Financial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@SpringBootApplication
public class VaultCoreFinancialApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaultCoreFinancialApplication.class, args);
	}

}
