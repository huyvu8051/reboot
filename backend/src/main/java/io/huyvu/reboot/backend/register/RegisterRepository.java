package io.huyvu.reboot.backend.register;

import io.huyvu.reboot.backend.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<UserAccount, Long> {
}
