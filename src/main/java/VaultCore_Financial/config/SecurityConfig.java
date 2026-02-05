package VaultCore_Financial.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService) throws Exception {

        http
            // ✅ Disable CSRF (required for form POST + JWT)
            .csrf(csrf -> csrf.disable())
            .cors(withDefaults())

            // ⚠️ Stateless (works for now with your flow)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                // ✅ PUBLIC AUTH & UI ROUTES
                .requestMatchers(
                        "/",
                        "/register-page",
                        "/register",
                        "/login-page",
                        "/login",
                        "/logout",       
                        "/verify-otp",
                        "/resend-otp",     // ✅ FIX: REQUIRED
                        "/dashboard-page",
                        "/admin/dashboard",
                        "/favicon.ico"
                ).permitAll()

                // ✅ STATIC RESOURCES
                .requestMatchers(
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/webjars/**"
                ).permitAll()

                // 🔒 EVERYTHING ELSE
                .anyRequest().authenticated()
            )

            // ✅ JWT FILTER
            .addFilterBefore(
                new JwtAuthFilter(jwtService),
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
