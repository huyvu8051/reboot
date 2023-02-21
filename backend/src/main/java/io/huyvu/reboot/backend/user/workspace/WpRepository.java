package io.huyvu.reboot.backend.user.workspace

import io.huyvu.reboot.backend.entity.UserAccount
import io.huyvu.reboot.backend.entity.Workspace
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

import java.util.List

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:28 AM
 */
@Mapper
public interface WpRepository {

    @Select("""
            SELECT u 
              FROM UserAccount u 
             WHERE u.id = #{userId}
            """)
    UserAccount findAdminById(long userId)

    @Select(value = """
            SELECT id, title, picture_url AS pictureUrl
              FROM `workspace`
             WHERE id IN (SELECT wp_id FROM `workspace_member` WHERE user_id = #{userId})
             ORDER BY `modified_date` DESC
            """)
    List<ListWpItem> findAllWpItem(long userId)

    @Select(value = """
            SELECT w.id AS id, w.title AS title, w.picture_url AS pictureUrl
            FROM `workspace` w
            WHERE w.id = (SELECT wp_id
                         FROM workspace_member AS wm
                        WHERE wm.user_id = :userId 
                              AND wm.wp_id = :wpId)     
            """)
    WpDetails findWpDetails(long wpId, long userId)

    Workspace save(Workspace wp)
}
