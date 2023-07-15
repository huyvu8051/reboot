package io.huyvu.reboot.backend.config.resource;

import io.huyvu.reboot.security.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ResourceController {
    @GetMapping("/api/v1/resources/board/{bId}/{fileNm}")
    public String getResource(@PathVariable long bId, @PathVariable String fileNm, @CookieValue String resToken) {
        SecurityUtils.validateBoardResourcesAccess(bId, resToken);
        return "forward:/api/v1/internal/resources/board/" + bId + "/" + fileNm;
    }
}
