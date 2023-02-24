package io.huyvu.reboot.backend.biz.user.board.v1;

import io.huyvu.reboot.backend.entity.Board;
import io.huyvu.reboot.backend.entity.UserAccount;
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
public interface BdRepository {
    @Select("""
            SELECT id
                  ,name
                  ,background_image AS backgroundImage
                  ,stared
              FROM board
             WHERE id IN (SELECT board_id
                            FROM board_member
                           WHERE user_id = #{userId})
                   AND workspace_id = #{wpId}
                   AND is_deleted = 0
            """)
    List<BoardLsItem> findAllOwnBoard(long wpId, long userId);

    @Select("""
            SELECT u
              FROM UserAccount u
             WHERE id = #{id}
                   AND isDeleted = false
            """)
    Optional<UserAccount> findUser(long id);

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
    Optional<WorkspaceDto> findWpById(long wpId, long uId);

    Board save(Board b);


    @Insert("""
            INSERT INTO	board
               SET NAME = #{nm}
                 ,workspace_id = #{wpId}
            """)
    long insertBoard(String nm, long wpId);
}
