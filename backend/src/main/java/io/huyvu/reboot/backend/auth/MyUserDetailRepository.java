package io.huyvu.reboot.backend.auth;

import io.huyvu.reboot.backend.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MyUserDetailRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findOneByUsername(String username);

}
