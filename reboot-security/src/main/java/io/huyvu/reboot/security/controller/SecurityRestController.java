package io.huyvu.reboot.security.controller;

import io.huyvu.reboot.security.exception.AuthException;
import io.huyvu.reboot.security.model.AuthReq;
import io.huyvu.reboot.security.model.AuthRes;
import io.huyvu.reboot.security.repository.Repository;
import io.huyvu.reboot.security.service.GoogleAuthenticationService;
import io.huyvu.reboot.security.util.SecurityUtils;
import jakarta.servlet.http.HttpServletResponse;
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
public class SecurityRestController {
    private final Repository authRepo;
    private final GoogleAuthenticationService googleOauthGoogleAuthenticationService;

    @PostMapping("/google-auth")
    AuthRes authenticate(@RequestBody AuthReq req, HttpServletResponse res) throws AuthException {

        var ggAccToken = googleOauthGoogleAuthenticationService.extractToken(req.getIdToken());
        var userAccount = authRepo.findOneByUsername(ggAccToken.email());

        if (userAccount == null) {
            // Create new Account
//            authRepo.save(ggAccToken.email(), ggAccToken.name(), ggAccToken.pictureUrl(), FulltextSearchUtils.getFulltextIndex(ggAccToken.email(),ggAccToken.name()));
            authRepo.save(ggAccToken.email(), ggAccToken.name(), ggAccToken.pictureUrl(), "");
            userAccount = authRepo.findOneByUsername(ggAccToken.email());
        }

        var roles = List.of("USER", "ADMIN");
        var token = generateJwtToken(userAccount.id(), userAccount.username(), roles);
        SecurityUtils.setResponseToken(res, token);

        var bIds = authRepo.searchAllBoardId(userAccount.id());
        var resToken = generateBoardResourceToken(bIds);

        return new AuthRes(userAccount.id(), token, userAccount.username(), userAccount.fullName(), userAccount.pictureUrl(), roles, resToken);
    }

}
