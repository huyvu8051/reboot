package io.huyvu.reboot.backend.user.workspace;

import io.huyvu.reboot.backend.entity.UserAccount;
import io.huyvu.reboot.backend.entity.WorkspaceMember;
import io.huyvu.reboot.backend.entity.WorkspaceMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:28 AM
 */
public interface WpMemberRepository extends JpaRepository<WorkspaceMember, WorkspaceMemberId> {

}
