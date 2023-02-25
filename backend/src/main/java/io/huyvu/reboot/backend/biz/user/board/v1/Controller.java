/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:31 PM
 */
package io.huyvu.reboot.backend.biz.user.board.v1;

import io.huyvu.reboot.backend.config.auth.UserContextVo;
import io.huyvu.reboot.backend.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user/board")
public class Controller {
    private final BdService bdService;

    @PostMapping
    List<BoardLsItem> getAllFromWp(@RequestParam long wpId) {
        UserContextVo userContextVo = SecurityUtils.currentContext();
        return bdService.getAll(wpId, userContextVo.id());
    }


    @PutMapping
    long create(@RequestBody CreateBoardReq req) {
        UserContextVo userContextVo = SecurityUtils.currentContext();
        return bdService.create(req.title(), req.wp(), userContextVo.id());
    }
}
