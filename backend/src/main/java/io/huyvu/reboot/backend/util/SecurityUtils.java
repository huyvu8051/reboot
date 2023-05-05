package io.huyvu.reboot.backend.util;

import io.huyvu.reboot.backend.config.resource.UnauthorizedResourceException;
import io.huyvu.reboot.backend.config.security.UserContextVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import java.util.*;

@Slf4j
public class SecurityUtils {
    private static final String USERNAME = "username";
    private static final String ROLES = "roles";
    private static final String BIDS = "bIds";
    private static final int SIX_HOUR = 1000 * 60 * 60 * 6;
    private static final int ONE_HOUR = 1000 * 60 * 60 * 1;
//    private final int ONE_DAY = 1000 * 60

    @Value("${reboot.jwt-key}")
    private static String SECRET_KEY = "iloveu3000";

    public static long uId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new AccessDeniedException("Not authorized.");
        }
        return ((UserContextVo) authentication.getPrincipal()).uId();
    }

    public static String username() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken anonymous) {
            return (String) anonymous.getPrincipal();
        } else if (authentication.getPrincipal() instanceof UserContextVo ucVo) {
            return ucVo.username();
        }

        return "";


    }

    public static String generateJwtToken(long userId, String username, Collection<? extends GrantedAuthority> roles) {
        return generateJwtToken(userId, username, toRoles(roles));
    }

    public static String generateJwtToken(long userId, String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME, username);
        claims.put(ROLES, roles);
        return createToken(claims, String.valueOf(userId));
    }

    public static UserContextVo validateJWTToken(String token) {
        Claims claims = extractAllClaims(token);

        var uId = Long.valueOf(claims.getSubject());
        var username = claims.get(USERNAME, String.class);
        var rolesStr = claims.get(ROLES, List.class);
        var rolesAuth = toAuthorities(rolesStr);
        var expAt = claims.getExpiration();
        var mustRefresh = tokenMustRefresh(expAt);
        Assert.isTrue(isTokenNotExpired(expAt), "Token is expired.");

        return new UserContextVo(uId, username, rolesAuth, mustRefresh);

    }


    /**
     * If token expired over 1 hour, is need to refresh
     *
     * @param expAt
     * @return
     */
    private static boolean tokenMustRefresh(Date expAt) {
        Date now = new Date(System.currentTimeMillis() + ONE_HOUR);
        return expAt.before(now);
    }


    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private static boolean isTokenNotExpired(Date expAt) {
        return expAt.after(new Date());
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + SIX_HOUR))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private static List<GrantedAuthority> toAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add((GrantedAuthority) () -> role);
        }
        return authorities;
    }

    private static List<String> toRoles(Collection<? extends GrantedAuthority> authorities) {
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority auth : authorities) {
            roles.add(auth.getAuthority());
        }
        return roles;
    }

    public static String generateBoardResourceToken(List<Long> bIds) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(BIDS, bIds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + SIX_HOUR))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


    public static void validateBoardResourcesAccess(long bId, String resToken) {
        var claims = extractAllClaims(resToken);
        List<Integer> list = claims.get(BIDS, List.class);
        var first = list.stream().map(e->Long.valueOf(e)).filter(e -> e.equals(bId)).findFirst();
        first.orElseThrow(UnauthorizedResourceException::new);
    }


}