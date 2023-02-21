package io.huyvu.reboot.backend.user.board

import io.huyvu.reboot.backend.entity.Board
import io.huyvu.reboot.backend.entity.UserAccount
import io.huyvu.reboot.backend.entity.Workspace
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

import java.util.List
import java.util.Optional

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:35 PM
 */
@Mapper
public interface BdRepository {
    @Select(value = """
            SELECT id
                  ,name
                  ,background_image AS backgroundImage
                  ,stared
              FROM board
             WHERE id IN (SELECT board_id
                            FROM board_member
                           WHERE user_id = #{userId})
                   AND workspace_id = #{wpId}}
                   AND is_deleted = 0
            """)
    List<BoardLsItem> findAllOwnBoard(long wpId, long userId)

    @Select("""
            SELECT u
              FROM UserAccount u
             WHERE id = #{id}
                   AND isDeleted = false
            """)
    Optional<UserAccount> findUser(long id)

    @Select("""
            SELECT w 
              FROM Workspace w
             WHERE id = #{id}
                   AND isDeleted = false
            """)
    Optional<Workspace> findWpById(long wpId)

    Board save(Board b)
}
