/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:17 AM
 */
package io.huyvu.reboot.backend.biz.user.wp.v1;

import io.huyvu.reboot.backend.config.security.UserContextVo;
import io.huyvu.reboot.backend.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/user/workspace")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class Controller {
    private final Service mngWpService;

    @PutMapping
    CreateWpResp createWp(@NotNull
                          @NotEmpty
                          @NotBlank String wpNm) {
        UserContextVo userCtx = SecurityUtils.currentContext();
        WpDetails workspace = mngWpService.create(wpNm, userCtx.id());
        CreateWpResp createWpResp = new CreateWpResp() {{
            setId(workspace.id());
            setTitle(workspace.title());
        }};
        return createWpResp;
    }


    @PostMapping
    List<ListWpItem> getLs() {
        UserContextVo userCtx = SecurityUtils.currentContext();
        return mngWpService.getList(userCtx.id());
    }

    @GetMapping
    WpDetails getDetails(long wpId) {
        UserContextVo userCtx = SecurityUtils.currentContext();
        return mngWpService.getDetails(wpId, userCtx.id());
    }

}
