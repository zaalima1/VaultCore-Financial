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
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(withDefaults())

            // ✅ JWT Stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth

                // ✅ Allow Thymeleaf pages
                .requestMatchers(
                        "/",
                        "/register-page",
                        "/register",
                        "/login-page",
                        "/login",
                        "/dashboard-page"
                ).permitAll()

                // ✅ Allow Static resources (CSS/JS/Images)
                .requestMatchers(
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/webjars/**"
                ).permitAll()

//                // ✅ Allow Auth APIs
//                .requestMatchers("").permitAll()

                // ✅ Everything else requires JWT
                .anyRequest().authenticated()
            )

            // ✅ JWT Filter
            .addFilterBefore(new JwtAuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
