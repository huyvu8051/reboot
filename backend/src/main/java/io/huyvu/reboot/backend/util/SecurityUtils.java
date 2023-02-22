/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:37 AM
 */
package io.huyvu.reboot.backend.util;

import io.huyvu.reboot.backend.config.auth.UserContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UserContext currentContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return new UserContext(0, "anonymous");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof String s) {
            return new UserContext(0, s);
        }

        if (principal instanceof UserContext uc) {
            return uc;
        }

        return (UserContext) principal;
    }
}
