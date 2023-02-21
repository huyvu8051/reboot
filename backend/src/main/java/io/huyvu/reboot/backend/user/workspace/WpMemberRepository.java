package io.huyvu.reboot.backend.user.workspace

import io.huyvu.reboot.backend.entity.WorkspaceMember
import org.apache.ibatis.annotations.Mapper

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:28 AM
 */
@Mapper
public interface WpMemberRepository {

    void save(WorkspaceMember wpMem)
}
