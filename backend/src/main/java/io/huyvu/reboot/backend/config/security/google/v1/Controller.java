package io.huyvu.reboot.backend.config.security.google.v1;

import io.huyvu.reboot.backend.entity.UserAccount;
import lombok.AllArgsConstructor;
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
    AuthRes authenticate(@RequestBody AuthReq req) throws AuthException {

        AccountTokenVo ggAccToken = googleOauthService.extractToken(req.getIdToken());
        UserAccount userAccount = authRepo.findOneByUsername(ggAccToken.email());

        if (userAccount == null) {
            // Create new Account
            authRepo.save(ggAccToken.email(), ggAccToken.name(), ggAccToken.pictureUrl());
            userAccount = authRepo.findOneByUsername(ggAccToken.email());
        }

        List<String> roles = List.of("USER", "ADMIN");
        String jwtToken = generateJwtToken(userAccount.getId(), userAccount.getUsername(),roles);

        return AuthRes.builder()
                .token(jwtToken)
                .username(userAccount.getUsername())
                .fullName(userAccount.getFullName())
                .pictureUrl(userAccount.getPictureUrl())
                .roles(roles)
                .build();
    }
}
