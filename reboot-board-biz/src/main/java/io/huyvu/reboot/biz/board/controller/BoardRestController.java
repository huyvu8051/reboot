/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:31 PM
 */
package io.huyvu.reboot.biz.board.controller;

import io.huyvu.reboot.biz.board.model.BoardVo;
import io.huyvu.reboot.biz.board.model.CreateBoardReq;
import io.huyvu.reboot.biz.board.service.IService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.huyvu.reboot.security.util.SecurityUtils.uId;


@RestController
@RequestMapping("api/v1/user/board")
@RequiredArgsConstructor
public class BoardRestController {
    private final IService service;

    @PostMapping
    List<BoardVo> getAllFromWp(@RequestParam long wpId) {
        return service.getAll(wpId);
    }

    @PutMapping
    long create(@RequestBody CreateBoardReq req) {
        return service.create(req.title(), req.wp(), uId());
    }
}
