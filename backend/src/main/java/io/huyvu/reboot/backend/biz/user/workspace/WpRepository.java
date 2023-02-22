package io.huyvu.reboot.backend.biz.user.workspace;

import io.huyvu.reboot.backend.entity.UserAccount;
import io.huyvu.reboot.backend.entity.Workspace;
import org.apache.ibatis.annotations.*;

import java.util.List;

import static io.huyvu.reboot.backend.config.mybatis.MybatisAuditingInterceptor.SET_USER_CTX_QUERY;

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
    UserAccount findAdminById(long userId);

    @Select(value = """
            SELECT id, title, picture_url AS pictureUrl
              FROM `workspace`
             WHERE id IN (SELECT wp_id FROM `workspace_member` WHERE user_id = #{userId})
             ORDER BY `modified_date` DESC
            """)
    List<ListWpItem> findAllWpItem(long userId);

    @Select(value = """
            SELECT w.id AS id, w.title AS title, w.picture_url AS pictureUrl
            FROM `workspace` w
            WHERE w.id = (SELECT wp_id
                         FROM workspace_member AS wm
                        WHERE wm.user_id = :userId
                              AND wm.wp_id = :wpId)     
            """)
    WpDetails findWpDetails(long wpId, long userId);

    Workspace save(Workspace wp);


    @Insert({SET_USER_CTX_QUERY, """
            INSERT INTO workspace
               SET username = #{username}
                  ,full_name = #{fullName}
                  ,picture_url = #{pictureUrl}"""})
    Workspace createWp(String wpNm, long adminId);

    /**
     * @param wpNm workspace name
     * @return generated workspace id
     */
    @Insert({SET_USER_CTX_QUERY, """
            INSERT INTO workspace
               SET title = #{wpNm}"""})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long insertWp(String wpNm);

    @Insert({SET_USER_CTX_QUERY, """
            INSERT INTO workspace_member
               SET wp_id = #{wpId}
                  ,user_id = #{adId}
                  ,is_admin = #{isAd}"""})
    @SelectKey(statement = """
            SELECT wp_id AS wpId
                  ,user_id AS adId
                  ,is_admin AS isAd
              FROM workspace_member
             WHERE user_id = #{adId}
                   AND wp_id = #{wpId}""", keyProperty = "id", before = false, resultType = WpMem.class)
    WpMem insertWpMem(long wpId, long adId, boolean isAd);
}
