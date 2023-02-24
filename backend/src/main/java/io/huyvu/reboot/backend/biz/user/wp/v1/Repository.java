package io.huyvu.reboot.backend.biz.user.wp.v1;

import io.huyvu.reboot.backend.entity.UserAccount;
import io.huyvu.reboot.backend.entity.Workspace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;


/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:28 AM
 */
@Mapper
public interface Repository {

    @Select("""
            SELECT u 
              FROM UserAccount u 
             WHERE u.id = #{userId}
            """)
    UserAccount findAdminById(long userId);

    @Select(value = """
            SELECT id, title, picture_url AS pictureUrl
              FROM `workspace`
             WHERE id IN (SELECT wp_id FROM `workspace_member` WHERE user_id = #{userId})
                   AND is_deleted = 0
             ORDER BY `modified_date` DESC
            """)
    List<ListWpItem> selectWpItem(long userId);

    @Select("""
            SELECT w.id AS id, w.title AS title, w.picture_url AS pictureUrl
            FROM `workspace` w
            WHERE w.id = (SELECT wp_id
                         FROM workspace_member AS wm
                        WHERE wm.user_id = #{userId}
                              AND wm.wp_id = #{wpId})     
                  AND is_deleted = 0
            """)
    Optional<WpDetails> selectWpDetails(long wpId, long userId);



    @Insert({"""
            INSERT INTO workspace
               SET username = #{username}
                  ,full_name = #{fullName}
                  ,picture_url = #{pictureUrl}"""})
    Workspace createWp(String wpNm, long adminId);

    /**
     * @param wpNm workspace name
     * @return generated workspace id
     */
    @Insert({"""
            INSERT INTO workspace
               SET title = #{wpNm}"""})
    long insertWp(String wpNm);

    @Select({"""
            SELECT LAST_INSERT_ID()"""})
    long selectLastInsertId();

    @Insert({"""
            INSERT INTO workspace_member
               SET wp_id = #{wpId}
                  ,user_id = #{adId}
                  ,is_admin = #{isAd}"""})
    void insertWpMem(long wpId, long adId, boolean isAd);
}
