/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 1:00 PM
 */
package io.huyvu.reboot.backend.config.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Context only when user logged
 *
 * @param uId      user uId
 * @param username username
 */
public record UserContextVo(Long uId,
                            String username,
                            Collection<? extends GrantedAuthority> roles,
                            boolean mustRefresh) {

}
