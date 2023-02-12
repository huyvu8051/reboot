package io.huyvu.reboot.backend.auth.google.v1;

import io.huyvu.reboot.backend.auth.JwtUtils;
import io.huyvu.reboot.backend.entity.UserAccount;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class GoogleAuthController {
    private final AuthenticationManager authenticationManager;
    private final GoogleAuthRepository authRepo;
    private final JwtUtils jwtUtils;
    private final GoogleOAuthService googleOauthService;

    @PostMapping("/authenticate")
    GoogleAuthResp authenticate(@RequestBody GoogleAuthReq req) throws GoogleAuthException{


        GoogleAccountToken ggAccToken = googleOauthService.extractToken(req.getIdToken());

        UserAccount userAccount = authRepo.findOneByUsername(ggAccToken.email()).orElseThrow(() -> new UsernameNotFoundException("Token invalid"));

        String jwtToken = jwtUtils.generateToken(userAccount);
        return GoogleAuthResp.builder()
                .token(jwtToken)
                .username(userAccount.getUsername())
                .fullName(userAccount.getFullName())
                .build();
    }
}
