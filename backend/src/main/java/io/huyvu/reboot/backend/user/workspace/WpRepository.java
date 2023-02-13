package io.huyvu.reboot.backend.user.workspace;

import io.huyvu.reboot.backend.entity.UserAccount;
import io.huyvu.reboot.backend.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:28 AM
 */
public interface WpRepository extends JpaRepository<Workspace, Long> {

    @Query("SELECT u FROM UserAccount u WHERE id = :adminId")
    UserAccount findAdminById(@Param("adminId") long adminId);
}
