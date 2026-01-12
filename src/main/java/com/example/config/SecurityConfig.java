package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Για να δουλεύει το Postman εύκολα
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/programs/announced").permitAll() // Ο Visitor βλέπει ελεύθερα
                        .requestMatchers("/api/programs/**").hasRole("PROGRAMMER") // Μόνο ο Programmer αλλάζει states
                        .requestMatchers("/api/screenings/**").hasAnyRole("SUBMITTER", "STAFF")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Θα ζητάει Username/Password στο Postman

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Φτιάχνουμε 3 δοκιμαστικούς χρήστες για το Postman
        UserDetails prog = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("PROGRAMMER")
                .build();

        UserDetails staff = User.withDefaultPasswordEncoder()
                .username("staff1")
                .password("staff123")
                .roles("STAFF")
                .build();

        return new InMemoryUserDetailsManager(prog, staff);
    }
}