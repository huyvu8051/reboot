package io.huyvu.reboot.security.google.v1;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.huyvu.reboot.security.util.SecurityUtils.generateBoardResourceToken;
import static io.huyvu.reboot.security.util.SecurityUtils.generateJwtToken;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class Controller {
    private final Repository authRepo;
    private final IService googleOauthIService;

    @PostMapping("/google-auth")
    AuthRes authenticate(@RequestBody AuthReq req) throws AuthException {

        var ggAccToken = googleOauthIService.extractToken(req.getIdToken());
        var userAccount = authRepo.findOneByUsername(ggAccToken.email());

        if (userAccount == null) {
            // Create new Account
//            authRepo.save(ggAccToken.email(), ggAccToken.name(), ggAccToken.pictureUrl(), FulltextSearchUtils.getFulltextIndex(ggAccToken.email(),ggAccToken.name()));
            authRepo.save(ggAccToken.email(), ggAccToken.name(), ggAccToken.pictureUrl(), "");
            userAccount = authRepo.findOneByUsername(ggAccToken.email());
        }

        var roles = List.of("USER", "ADMIN");
        String token = generateJwtToken(userAccount.id(), userAccount.username(), roles);

        List<Long> bIds = authRepo.searchAllBoardId(userAccount.id());
        String resToken = generateBoardResourceToken(bIds);

        return new AuthRes(token, userAccount.username(), userAccount.fullName(), userAccount.pictureUrl(), roles, resToken);
    }

}
