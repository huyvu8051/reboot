/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:31 PM
 */
package io.huyvu.reboot.backend.user.board;

import io.huyvu.reboot.backend.auth.UserContext;
import io.huyvu.reboot.backend.entity.Board;
import io.huyvu.reboot.backend.user.workspace.CreateWpReq;
import io.huyvu.reboot.backend.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user/board")
public class BdController {
    private final BdService bdService;

    @PostMapping
    List<BoardLsItem> getAll(){
        UserContext userContext = SecurityUtils.currentContext();
        return bdService.getAll(userContext.id());

    }


    @PutMapping
    long create(String name){
        UserContext userContext = SecurityUtils.currentContext();
        return bdService.create(name, userContext.id());

    }
}
