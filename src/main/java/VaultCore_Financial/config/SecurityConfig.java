package VaultCore_Financial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Full SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for Postman/API testing
            .csrf(csrf -> csrf.disable())

            // Enable CORS
            .cors(cors -> {}) 

            // Stateless session (JWT only)
            
            .authorizeHttpRequests(auth -> auth

            	    .requestMatchers(
            	            "/", 
            	            "/register-page", "/register",
            	            "/login-page", "/login", 
            	            "/verify-otp",
            	            "/resend-otp", 
            	            "/dashboard-page", 
            	            "/trading",
            	            "/admin/**",
            	            "/admin/dashboard2",
            	            "/dash",
            	            "/admin/users",
            	            "/users/export",
            	            "/user-list",
            	            "/user_account",
            	            "/welcom",
            	            "/transfer/send1",
            	            "/transfer/send",
            	            "/bill",
            	            "/admin/audit-logs",
            	            "/audit-logs",
            	            "/login",
            	            "/statement/**",
            	            "/statement-page",
            	            "/account/balance/**",
            	            "/admin",
            	            "/user/loan",
            	            "/Admin-loan-management",
            	            "/user/dashboard",
            	            "/admin2",
            	            "/user/loan/apply",
            	            "/user/admin/loan/approve/**",
            	            "/user_loan",
                       "/user/req_sub",
                       "/user/admin/loans",
                        
                          "/account/create",
            	            
            	            "/loan_success",
            	            "/req_sub",
            	            "/kyc/**",
            	            "/admin/users/export",
            	            "/admin/kyc/**",
            	            "/admin/kyc/pending",
            	            "/admin-kyc-list",
            	            "/key-form",
            	            "/kyc-success",
            	            "/favicon.ico",
            	            "/images/**",
            	            "/css/**",
            	            "/js/**"
            	    ).permitAll()

            	   
            
                // All other endpoints require JWT authentication
                .anyRequest().authenticated()
            )

            // JWT Filter for protected endpoints
            
                .logout(logout -> logout
                        .logoutUrl("/logout")                 // URL to trigger logout
                        .logoutSuccessUrl("/logout-success")  // Redirect after logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                   
            );

        return http.build();
    }

    // AuthenticationManager bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Proper CORS configuration bean
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*")); // allow all origins
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
