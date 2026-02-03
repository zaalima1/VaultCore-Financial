package VaultCore_Financial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // ❌ Disable CSRF for simplicity (ok for now)
            .csrf(csrf -> csrf.disable())

            // 🔓 Authorization rules
            .authorizeHttpRequests(auth -> auth

                // ✅ STATIC RESOURCES (VERY IMPORTANT)
                .requestMatchers(
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/webjars/**",
                        "/favicon.ico"
                ).permitAll()

                // ✅ PUBLIC PAGES
                .requestMatchers(
                        "/",
                        "/register-page",
                        "/register",
                        "/login-page",
                        "/login",
                        "/verify-otp",
                        "/resend-otp"
                ).permitAll()

                // 🔒 Everything else secured
                .anyRequest().authenticated()
            )

            // ✅ Custom login page
            .formLogin(form -> form
                .loginPage("/login-page")
                .defaultSuccessUrl("/dashboard-page", true)
                .permitAll()
            )

            // ✅ Logout config
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-page")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
