package io.huyvu.reboot.backend.auth;

import io.huyvu.reboot.backend.entity.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<MyUserDetails, Long> {
    Optional<MyUserDetails> findOneByUsername(String username);
}
