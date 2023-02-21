package io.huyvu.reboot.backend.auth.google.v1

import io.huyvu.reboot.backend.util.JwtUtils
import io.huyvu.reboot.backend.entity.UserAccount
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.util.List

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class GoogleOAuthController {
    private final GoogleOAuthRepository authRepo
    private final JwtUtils jwtUtils
    private final GoogleOAuthService googleOauthService

    @PostMapping("/google-auth")
    AuthResp authenticate(@RequestBody AuthReq req) throws GoogleOAuthException {

        GoogleOAccountToken ggAccToken = googleOauthService.extractToken(req.getIdToken())

        UserAccount userAccount = authRepo.findOneByUsername(ggAccToken.email())

        if (userAccount == null) {
            // Create new Account
            authRepo.save(ggAccToken.email(), ggAccToken.name(), ggAccToken.pictureUrl())
            userAccount = authRepo.findOneByUsername(ggAccToken.email())
        }

        List<String> roles = List.of("USER", "ADMIN")

        String jwtToken = jwtUtils.generateJwtToken(userAccount.getId(), userAccount.getUsername(),roles)
        return AuthResp.builder()
                .token(jwtToken)
                .username(userAccount.getUsername())
                .fullName(userAccount.getFullName())
                .pictureUrl(userAccount.getPictureUrl())
                .roles(roles)
                .build()
    }
}
