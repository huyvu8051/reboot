package io.huyvu.reboot.backend.auth.google.v1

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import java.io.IOException
import java.security.GeneralSecurityException
import java.util.Collections

@Service
public class GoogleOAuthServiceImpl implements GoogleOAuthService {

    @Value("${google.oauth.client_id}")
    private String CLIENT_ID

    @Override
    public GoogleOAccountToken extractToken(String token) throws GoogleOAuthException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build()

        // (Receive idTokenString by HTTPS POST)
        try {
            GoogleIdToken idToken = verifier.verify(token)
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload()

                // Print user identifier
                String userId = payload.getSubject()
                System.out.println("User ID: " + userId)

                // Get profile information from payload
                String email = payload.getEmail()
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified())
                String name = (String) payload.get("name")
                String pictureUrl = (String) payload.get("picture")
                String locale = (String) payload.get("locale")
                String familyName = (String) payload.get("family_name")
                String givenName = (String) payload.get("given_name")

                // Use or store profile information
                // ...
                return new GoogleOAccountToken(email, emailVerified, name, pictureUrl, locale, familyName, givenName)
            } else {
                throw new GoogleOAuthException()
            }
        } catch (GeneralSecurityException | IOException | GoogleOAuthException e) {
            throw new GoogleOAuthException()
        }

    }
}
