package io.huyvu.reboot.backend.user.workspace;

import io.huyvu.reboot.backend.entity.WorkspaceMember;
import io.huyvu.reboot.backend.entity.WorkspaceMemberKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:28 AM
 */
public interface WpMemberRepository extends JpaRepository<WorkspaceMember, WorkspaceMemberKey> {

}
