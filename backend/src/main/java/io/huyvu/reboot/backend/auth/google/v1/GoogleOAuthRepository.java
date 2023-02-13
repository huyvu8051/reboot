package io.huyvu.reboot.backend.auth.google.v1;

import io.huyvu.reboot.backend.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleOAuthRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findOneByUsername(String username);

}
