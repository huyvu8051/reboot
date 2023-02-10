package io.huyvu.reboot.backend.authenticate;

import io.huyvu.reboot.backend.entity.UserAccount;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthenticateController {
    private final AuthenticationManager authenticationManager;
    private final AuthenticateRepository authRepo;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    AuthenticateResp authenticate(@RequestBody AuthenticateReq req) {
        String formattedUsername = req.getUsername().trim().toLowerCase();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(formattedUsername, req.getPassword()));


        UserAccount userAccount = authRepo.findOneByUsername(formattedUsername).orElseThrow(() -> new UsernameNotFoundException("Token invalid"));

        String token = jwtUtils.generateToken(userAccount);
        return AuthenticateResp.builder()
                .token(token)
                .username(userAccount.getUsername())
                .fullName(userAccount.getFullName())
                .build();
    }
}
