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
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtService jwtService
    ) throws Exception {

        http
            // ✅ Disable CSRF for simplicity (OK for now)
            .csrf(csrf -> csrf.disable())
            .cors(withDefaults())

            // ✅ STATEFUL SESSION (REQUIRED)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )

            // ✅ AUTH RULES
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/register-page",
                    "/register",
                    "/login-page",
                    "/login",
                    "/verify-otp",
                    "/resend-otp",
<<<<<<< HEAD
=======
                    "/trading",
>>>>>>> 5abb3a4d3f63f7044d0c32c3bf2e822dea0beeeb
                    "/favicon.ico",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/webjars/**"
                ).permitAll()

                // 🔒 Protected pages
//                .requestMatchers("/dashboard-page", "/admin/**")
//                .authenticated()

                .anyRequest().authenticated()
            )

            // ✅ LOGOUT CONFIG (THIS FIXES YOUR ISSUE)
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-page?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            // ✅ JWT FILTER (OPTIONAL FOR API CALLS)
            .addFilterBefore(
                new JwtAuthFilter(jwtService),
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }
}
