package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/health").permitAll()
                .requestMatchers("/articulos").permitAll() // TODO: Protect this endpoint
                .anyRequest().authenticated()
        ).csrf(AbstractHttpConfigurer::disable); // TODO: Enable CSRF protection
        return http.build();
    }
}
