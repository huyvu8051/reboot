package io.huyvu.reboot.backend.user.board;

import io.huyvu.reboot.backend.entity.Board;
import io.huyvu.reboot.backend.entity.UserAccount;
import io.huyvu.reboot.backend.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:35 PM
 */
public interface BdRepository extends JpaRepository<Board, Long> {
    @Query(value = """
            SELECT id
                  ,name
                  ,background_image AS backgroundImage
                  ,stared
              FROM board
             WHERE id IN (SELECT board_id
                            FROM board_member
                           WHERE user_id = :userId)
                   AND workspace_id = :wpId
                   AND is_deleted = 0
            """, nativeQuery = true)
    List<BoardLsItem> findAllOwnBoard(long wpId,long userId);

    @Query("""
            SELECT u
              FROM UserAccount u
             WHERE id = :id
                   AND isDeleted = false
            """)
    Optional<UserAccount> findUser(@Param("id") long id);

    @Query("""
            SELECT w 
              FROM Workspace w
             WHERE id = :id
                   AND isDeleted = false
            """)
    Optional<Workspace> findWpById(@Param("id") long wpId);
}
