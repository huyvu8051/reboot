package io.huyvu.reboot.backend.biz.user.dashboard.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.huyvu.reboot.backend.util.SecurityUtils.uId;

@RestController
@RequestMapping("api/v1/user/dashboard")
@RequiredArgsConstructor
public class Controller {

    private final IService service;

    @GetMapping
    DashboardVo getDashBoard(Long wId,
                             Long bId,
                             Long cId) {
//        var item = (name:"huy", age:18, companyNm:"posco");
//        out.println(item);

        return service.getBoard(uId(), wId, bId, cId);
    }

}
