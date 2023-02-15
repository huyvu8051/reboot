/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:37 AM
 */
package io.huyvu.reboot.backend.util;

import io.huyvu.reboot.backend.auth.UserContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UserContext currentContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof String s){
            return new UserContext(0, s);
        }

        if (principal instanceof UserContext uc){
            return uc;
        }

        return (UserContext) principal;
    }
}
