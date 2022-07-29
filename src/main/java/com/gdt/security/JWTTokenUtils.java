package com.gdt.security;

import com.gdt.entity.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JWTTokenUtils {
    public String generateToken(Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", employee.getFirstName());
        claims.put("lastName", employee.getLastName());
        claims.put("roles", employee.getRoles());
        claims.put("userName", employee.getUsername());

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR, 2);

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(employee.getUsername())
                .setIssuer("GESTIONS_TACHES")
                .setIssuedAt(now)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, "SECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEY")
                .compact();
        log.info("TOKEN {}", token);
        return token;
    }

    public String getUserNameFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey("SECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEYSECRETKEY")
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public boolean isTokenValid(String token, UserDetails employee) {
        Claims claims = getClaims(token);
        Date expiration = claims.getExpiration();
        String userNameFromToken = getUserNameFromToken(token);
        Boolean isValidUsername = userNameFromToken.equals(employee.getUsername());
        Boolean isValidDate = expiration.after(new Date());
        return isValidUsername && isValidDate;
    }
}
