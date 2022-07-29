package com.gdt.security;

import com.gdt.entity.Employee;
import com.gdt.service.AccountService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTfilter extends OncePerRequestFilter {
    private AccountService accountService;
    private JWTTokenUtils jwtTokenUtils;
    private String authorization;

    public JWTfilter(AccountService accountService, JWTTokenUtils jwtTokenUtils, String authorization) {
        this.accountService = accountService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authorization = authorization;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(authorization);
        if(header !=null && header.startsWith("Bearer ") ) {
            String token = header.substring(7);
            String username = this.jwtTokenUtils.getUserNameFromToken(token);

            if(username != null) {
                UserDetails employee = this.accountService.loadUserByUsername(username);
                boolean isTokenValid = jwtTokenUtils.isTokenValid(token, employee);
                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(employee, null, employee.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
