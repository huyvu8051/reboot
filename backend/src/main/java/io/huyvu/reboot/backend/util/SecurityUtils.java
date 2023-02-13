/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:37 AM
 */
package io.huyvu.reboot.backend.util;

import io.huyvu.reboot.backend.auth.UserContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UserContext currentContext() {
        return (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
