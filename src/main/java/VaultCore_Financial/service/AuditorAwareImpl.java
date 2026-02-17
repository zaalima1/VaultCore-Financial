package VaultCore_Financial.service;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component("auditorProvider")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // If using Spring Security:
        // return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());

        // For now, manual fallback
        return Optional.of("CURRENT_USER"); 
    }
}
