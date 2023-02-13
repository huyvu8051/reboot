package io.huyvu.reboot.backend.user.board;

import io.huyvu.reboot.backend.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
                           WHERE user_id = :id)
                   AND is_deleted = 0
            """, nativeQuery = true)
    List<BoardLsItem> findAllOwnBoard(@Param("id") long id);
}
