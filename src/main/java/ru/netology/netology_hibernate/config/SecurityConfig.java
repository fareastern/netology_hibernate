package ru.netology.netology_hibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/persons/by-city").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .permitAll()
                        .defaultSuccessUrl("/persons", true)
                )
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userRead = User.builder()
                .username("reader")
                .password(passwordEncoder().encode("pass"))
                .roles("READ")
                .build();

        UserDetails userWrite = User.builder()
                .username("writer")
                .password(passwordEncoder().encode("pass"))
                .roles("WRITE")
                .build();

        UserDetails userDelete = User.builder()
                .username("deleter")
                .password(passwordEncoder().encode("pass"))
                .roles("DELETE")
                .build();

        UserDetails userReadWrite = User.builder()
                .username("rw")
                .password(passwordEncoder().encode("pass"))
                .roles("READ", "WRITE")
                .build();

        return new InMemoryUserDetailsManager(userRead, userWrite, userDelete, userReadWrite);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}