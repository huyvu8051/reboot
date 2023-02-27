/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:37 AM
 */
package io.huyvu.reboot.backend.util;

import io.huyvu.reboot.backend.config.security.UserContextVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UserContextVo currentContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return new UserContextVo(0, "anonymous");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof String s) {
            return new UserContextVo(0, s);
        }

        if (principal instanceof UserContextVo uc) {
            return uc;
        }

        return (UserContextVo) principal;
    }
}
