/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:17 AM
 */
package io.huyvu.reboot.backend.user.workspace;

import io.huyvu.reboot.backend.auth.UserContext;
import io.huyvu.reboot.backend.entity.Workspace;
import io.huyvu.reboot.backend.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/workspace")
public class WpController {
    private final WpService mngWpService;

    @PutMapping
    CreateWpResp createWp(@RequestBody CreateWpReq req) {
        UserContext userCtx = SecurityUtils.currentContext();
        Workspace workspace = mngWpService.create(req.getWpNm(), userCtx.id());
        return CreateWpResp.builder()
                .id(workspace.getId())
                .title(workspace.getTitle())
                .build();

    }
}
