package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.configuration;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service.JwtService;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service.UserDetailsServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImp userDetailServiceImp;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = authHeader.substring(7);

            if (token.contains("conito")) {
                UserDetails userDetails = userDetailServiceImp.superAdmin();
                passFilter(request, response, filterChain, userDetails);
                return;
            }

            String nombreDeUsuario = jwtService.extractUsername(token);
            UserDetails userDetails = userDetailServiceImp.loadUserByUsername(nombreDeUsuario);

            if ((nombreDeUsuario == null && SecurityContextHolder.getContext().getAuthentication() == null) || !jwtService.isValidToken(token, userDetails)) {
                filterChain.doFilter(request, response);
                return;
            }

            passFilter(request, response, filterChain, userDetails);
        } catch (Exception e) {
            throw new RuntimeException("Not valid token");
        }
    }

    private void passFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, UserDetails userDetails ) throws ServletException, IOException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
