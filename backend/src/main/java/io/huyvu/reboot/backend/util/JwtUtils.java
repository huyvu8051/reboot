package io.huyvu.reboot.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtils {
    private final int SEVENT_DAYS = 1000 * 60 * 60 * 24 * 7; // 7 days

    @Value("${reboot.jwt-key}")
    private String SECRET_KEY = null;


    public String generateJwtToken(String userId, String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", roles);
        return createToken(claims, userId);
    }

    public long extractUserId(String jwt) {
        return Long.valueOf(extractClaim(jwt,Claims::getSubject));
    }

    public String extractUsername(String token) {
        return extractClaim(token, "username");
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("roles", List.class);
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private String extractClaim(String token, String claimName) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName, String.class);
    }




    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SEVENT_DAYS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


}