/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 1:59 PM
 */
package io.huyvu.reboot.backend.util;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class AuthorityUtils {
    public static List<GrantedAuthority> toAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add((GrantedAuthority) () -> role);
        }
        return authorities;
    }

    public static List<String> toRoles(List<GrantedAuthority> authorities) {
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority auth : authorities) {
            roles.add(auth.getAuthority());
        }
        return roles;
    }
}
