package io.huyvu.reboot.backend.user.board

import io.huyvu.reboot.backend.entity.BoardMember
import org.apache.ibatis.annotations.Mapper

/**
 * @Author HuyVu
 * @CreatedDate 2/14/2023 8:31 AM
 */
@Mapper
public interface BdMemRepository {
    void save(BoardMember bMem)
}
