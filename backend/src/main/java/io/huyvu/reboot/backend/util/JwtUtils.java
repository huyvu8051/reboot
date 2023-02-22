package io.huyvu.reboot.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtils {
    private final int SIX_HOUR = 1000 * 60 * 60 * 6;
    private final int ONE_HOUR = 1000 * 60 * 60 * 1;
//    private final int ONE_DAY = 1000 * 60

    @Value("${reboot.jwt-key}")
    private String SECRET_KEY = null;


    public String generateJwtToken(long userId, String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", roles);
        return createToken(claims, String.valueOf(userId));
    }

    public long extractUserId(String jwt) {
        return Long.valueOf(extractClaim(jwt, Claims::getSubject));
    }

    public String extractUsername(String token) {
        return extractClaim(token, "username");
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("roles", List.class);
    }

    /**
     * Is token none expired
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    /**
     * If token expired over 1 hour, is need to refresh
     *
     * @param token
     * @return
     */
    public boolean tokenMustRefresh(String token) {
        Date now = new Date(System.currentTimeMillis() + ONE_HOUR);
        Date expiration = extractExpiration(token);
        return expiration.before(now);
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

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
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
                .setExpiration(new Date(System.currentTimeMillis() + SIX_HOUR))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


}