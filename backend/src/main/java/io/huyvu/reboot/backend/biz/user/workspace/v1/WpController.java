/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:17 AM
 */
package io.huyvu.reboot.backend.biz.user.workspace.v1;

import io.huyvu.reboot.backend.config.auth.UserContext;
import io.huyvu.reboot.backend.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user/workspace")
@Validated
public class WpController {
    private final WpService mngWpService;

    @PutMapping
    CreateWpResp createWp(@NotNull
                          @NotEmpty
                          @NotBlank String wpNm) {
        UserContext userCtx = SecurityUtils.currentContext();
        WpDetails workspace = mngWpService.create(wpNm, userCtx.id());
        CreateWpResp createWpResp = new CreateWpResp() {{
            setId(workspace.id());
            setTitle(workspace.title());
        }};
        return createWpResp;
    }

    @PostMapping
    List<ListWpItem> getLs() {
        UserContext userCtx = SecurityUtils.currentContext();
        return mngWpService.getList(userCtx.id());
    }

    @PostMapping("/{wpId}")
    WpDetails getDetails(@PathVariable long wpId) {
        UserContext userCtx = SecurityUtils.currentContext();
        return mngWpService.getDetails(wpId, userCtx.id());
    }

}
