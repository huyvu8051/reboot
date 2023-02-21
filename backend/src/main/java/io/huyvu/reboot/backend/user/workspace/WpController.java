/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:17 AM
 */
package io.huyvu.reboot.backend.user.workspace

import io.huyvu.reboot.backend.auth.UserContext
import io.huyvu.reboot.backend.entity.Workspace
import io.huyvu.reboot.backend.util.SecurityUtils
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*

import java.util.List

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user/workspace")
public class WpController {
    private final WpService mngWpService

    @PutMapping
    CreateWpResp createWp(@RequestBody CreateWpReq req) {
        UserContext userCtx = SecurityUtils.currentContext()
        Workspace workspace = mngWpService.create(req.getWpNm(), userCtx.id())
        return CreateWpResp.builder()
                .id(workspace.getId())
                .title(workspace.getTitle())
                .build()

    }

    @PostMapping
    List<ListWpItem> getLs(){
        UserContext userCtx = SecurityUtils.currentContext()
        return mngWpService.getList(userCtx.id())
    }

    @PostMapping("/{wpId}")
    WpDetails getDetails(@PathVariable long wpId){
        UserContext userCtx = SecurityUtils.currentContext()
        return mngWpService.getDetails(wpId,userCtx.id())
    }

}
