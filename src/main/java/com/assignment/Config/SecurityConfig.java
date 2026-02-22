package com.assignment.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationSuccessHandler successHandler;

    // Constructor injection (recommended in 2025+ style)
    public SecurityConfig(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Public pages - NO login required
                        //.requestMatchers("/", "/home", "/register", "/css/", "/js/", "/images/").permitAll()
                        // Login & error pages
                        .requestMatchers("/users/login", "/users/register").permitAll()

                        // Recruiter area
                        .requestMatchers("/recruiter/").hasRole("RECRUITER")
                        .requestMatchers("/jobseeker/**").hasRole("JOB_SEEKER")
                        // All other pages require authentication
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/users/login")                  // your custom login page
                        .loginProcessingUrl("/users/login")         // POST URL Spring listens to
                        .usernameParameter("email")                 // must match <input name="email">
                        .passwordParameter("password")
                        .successHandler(successHandler)             // ← use custom handler here
                        .failureUrl("/users/login?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/users/login?logout")
                        .permitAll()
                )

                // Optional: better CSRF for Thymeleaf (usually auto-enabled)
                .csrf(csrf -> csrf.disable());   // ← only disable for testing; keep enabled in production

        return http.build();
    }
}