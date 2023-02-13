package io.huyvu.reboot.backend.auth;

import io.huyvu.reboot.backend.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserDetailRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findOneByUsername(String username);
}
