package io.huyvu.reboot.backend.biz.user.dashboard.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.huyvu.reboot.backend.util.SecurityUtils.currentContext;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("api/v1/user/dashboard")
@RequiredArgsConstructor
public class Controller {

    private final IService service;

    @GetMapping
    DashboardVo getDashBoard(Long wId, Long bId, Long cId) {
        long uId = currentContext().id();
        return service.getBoard(uId, wId, bId, cId);
    }
}
