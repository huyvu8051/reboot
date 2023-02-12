package io.huyvu.reboot.backend.auth.google.v1;

import io.huyvu.reboot.backend.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoogleAuthRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findOneByUsername(String username);
}
