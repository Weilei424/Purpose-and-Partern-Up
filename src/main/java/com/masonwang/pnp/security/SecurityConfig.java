package com.masonwang.pnp.security;

import com.masonwang.pnp.security.converter.CustomAuthenticationConverter;
import com.masonwang.pnp.security.manager.CustomAuthenticationManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
    private final CustomAuthenticationConverter customAuthenticationConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager, customAuthenticationConverter);

        return null;
    }
}
