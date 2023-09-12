package io.huyvu.reboot.security.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.huyvu.reboot.security.exception.AuthException;
import io.huyvu.reboot.security.model.AccountTokenVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleAuthenticationServiceImpl implements GoogleAuthenticationService {

    @Value("${google.oauth.client_id}")
    private String CLIENT_ID;

    @Override
    public AccountTokenVo extractToken(String token) throws AuthException {
        var verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        // (Receive idTokenString by HTTPS POST)
        try {
            var idToken = verifier.verify(token);
            if (idToken != null) {
                var payload = idToken.getPayload();

                // Print user identifier
                var userId = payload.getSubject();
                System.out.println("User ID: " + userId);

                // Get profile information from payload
                var email = payload.getEmail();
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                var name = (String) payload.get("name");
                var pictureUrl = (String) payload.get("picture");
                var locale = (String) payload.get("locale");
                var familyName = (String) payload.get("family_name");
                var givenName = (String) payload.get("given_name");

                // Use or store profile information
                // ...
                return new AccountTokenVo(email, emailVerified, name, pictureUrl, locale, familyName, givenName);
            } else {
                throw new AuthException();
            }
        } catch (GeneralSecurityException | IOException | AuthException e) {
            throw new AuthException();
        }

    }
}
