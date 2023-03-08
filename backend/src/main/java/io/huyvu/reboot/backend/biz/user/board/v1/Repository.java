package io.huyvu.reboot.backend.biz.user.board.v1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:35 PM
 */
@Mapper
public interface Repository {
    @Select("""
            SELECT id
                  ,name
                  ,background_image AS backgroundImage
                  ,stared
              FROM board
             WHERE workspace_id = #{wpId}
                   AND is_deleted = 0
            """)
    List<BoardVo> findAllOwnBoard(long wpId);

    @Select("""
            SELECT w.id AS id
                  ,w.title AS title
                  ,w.picture_url AS pictureUrl
            FROM `workspace` w
            WHERE w.id = (SELECT wp_id
                            FROM workspace_member AS wm
                           WHERE wm.user_id = #{uId}
                                 AND wm.wp_id = #{wpId})     
                  AND is_deleted = 0
            """)
    Optional<WorkspaceVo> findWpById(long wpId, long uId);

    @Insert("""
            INSERT INTO	board
               SET NAME = #{nm}
                 ,workspace_id = #{wpId}
            """)
    long insertBoard(String nm, long wpId);

}
