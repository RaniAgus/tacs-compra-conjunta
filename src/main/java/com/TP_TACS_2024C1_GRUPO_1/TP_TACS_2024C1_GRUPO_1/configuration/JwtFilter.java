package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.configuration;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.exception.TokenNoValido;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.substring(7);

            // TODO: Remove when persistence is implemented
            if (token.contains("conito")) {
                UserDetails userDetails = userDetailService.loadUserByUsername("admin");
                passFilter(request, response, filterChain, userDetails);
                return;
            }

            String nombreDeUsuario = jwtService.extractUsername(token);
            if (nombreDeUsuario == null && SecurityContextHolder.getContext().getAuthentication() == null) {
                filterChain.doFilter(request, response);
                return;
            }

            UserDetails userDetails = userDetailService.loadUserByUsername(nombreDeUsuario);
            if (!jwtService.isValidToken(token, userDetails)) {
                filterChain.doFilter(request, response);
                return;
            }

            passFilter(request, response, filterChain, userDetails);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, new TokenNoValido());
        }
    }

    private void passFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, UserDetails userDetails ) throws ServletException, IOException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
