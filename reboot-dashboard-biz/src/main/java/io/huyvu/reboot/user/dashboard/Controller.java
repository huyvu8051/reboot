package io.huyvu.reboot.user.dashboard;

import io.huyvu.reboot.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.huyvu.reboot.security.util.SecurityUtils.uId;

@RestController
@RequestMapping("api/v1/user/dashboard")
@RequiredArgsConstructor
public class Controller {

    private final IService service;

    @GetMapping
    DashboardVo getDashBoard(Long wId,
                             Long bId,
                             Long cId) {
        return service.getBoard(SecurityUtils.uId(), wId, bId, cId);
    }

}
