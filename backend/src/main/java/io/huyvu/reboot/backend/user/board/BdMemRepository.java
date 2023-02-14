package io.huyvu.reboot.backend.user.board;

import io.huyvu.reboot.backend.entity.BoardMember;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author HuyVu
 * @CreatedDate 2/14/2023 8:31 AM
 */
public interface BdMemRepository extends JpaRepository<BoardMember, Long> {
}
