/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:17 AM
 */
package io.huyvu.reboot.backend.biz.user.wp.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static io.huyvu.reboot.backend.util.SecurityUtils.uId;

@Validated
@RestController
@RequestMapping("api/v1/user/workspace")
@RequiredArgsConstructor
public class Controller {
    private final IService mngWpService;

    @PutMapping
    CreateWpResp createWp(@NotNull
                          @NotEmpty
                          @NotBlank String wpNm) {
        WpDetails workspace = mngWpService.create(wpNm, uId());
        CreateWpResp createWpResp = new CreateWpResp() {{
            setId(workspace.id());
            setTitle(workspace.title());
        }};
        return createWpResp;
    }


    @PostMapping
    List<ListWpItem> getLs() {
        return mngWpService.getList(uId());
    }

    @GetMapping
    WpDetails getDetails(long wpId) {
        return mngWpService.getDetails(wpId, uId());
    }

    @GetMapping("/members")
    List<UserAccount> searchFulltextMember(String keyword) {
        return mngWpService.searchMembers(keyword);
    }

    @PostMapping("/members")
    void inviteMembers(@RequestBody InviteMemberReq req) {
        System.out.println(req.getMems());
        mngWpService.inviteToWp(req);
    }

}
