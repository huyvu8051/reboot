package io.huyvu.reboot.backend.authenticate;

import io.huyvu.reboot.backend.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticateRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findOneByUsername(String username);
}
