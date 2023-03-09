package io.huyvu.reboot.backend.config.security.google.v1;

import lombok.AllArgsConstructor;
import manifold.ext.rt.api.auto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.huyvu.reboot.backend.util.SecurityUtils.generateJwtToken;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class Controller {
    private final Repository authRepo;
    private final Service googleOauthService;

    @PostMapping("/google-auth")
    auto authenticate(@RequestBody AuthReq req) throws AuthException {

        var ggAccToken = googleOauthService.extractToken(req.getIdToken());
        var userAccount = authRepo.findOneByUsername(ggAccToken.email());

        if (userAccount == null) {
            // Create new Account
            authRepo.save(ggAccToken.email(), ggAccToken.name(), ggAccToken.pictureUrl());
            userAccount = authRepo.findOneByUsername(ggAccToken.email());
        }
        
        var roles = List.of("USER", "ADMIN");
        String token = generateJwtToken(userAccount.id(), userAccount.username(), roles);
        return (token, userAccount.username(), userAccount.fullName(), pictureUrl:userAccount.pictureUrl(), roles);
    }

}
