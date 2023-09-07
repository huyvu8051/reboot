package io.huyvu.reboot.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.huyvu.reboot.security.exception.UnauthorizedResourceException;
import io.huyvu.reboot.security.model.UserContextVo;
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

    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);


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
        var claims = extractAllClaims(token);

        var uId = Long.valueOf(claims.getSubject());
        var username = claims.getClaim(USERNAME).asString();
        var rolesStr = claims.getClaim(ROLES).asList(String.class);
        var rolesAuth = toAuthorities(rolesStr);
        var expAt = claims.getExpiresAt();
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


    private static DecodedJWT extractAllClaims(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM)
                // specify an specific claim validations
                .withIssuer("auth0")
                // reusable verifier instance
                .build();

        return verifier.verify(token);
    }

    private static boolean isTokenNotExpired(Date expAt) {
        return expAt.after(new Date());
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        JWTCreator.Builder builder = JWT.create();
        claims.forEach((s, o) -> builder.withClaim(s, o.toString()));

        return builder.withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + SIX_HOUR))
                .sign(ALGORITHM);
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


        return JWT.create()
                .withClaim(BIDS, bIds)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + SIX_HOUR))
                .sign(ALGORITHM);
    }


    public static void validateBoardResourcesAccess(long bId, String resToken) {
        var claims = extractAllClaims(resToken);
        List<Integer> list = claims.getClaim(BIDS).asList(Integer.class);
        var first = list.stream().map(e -> Long.valueOf(e)).filter(e -> e.equals(bId)).findFirst();
        first.orElseThrow(UnauthorizedResourceException::new);
    }


}