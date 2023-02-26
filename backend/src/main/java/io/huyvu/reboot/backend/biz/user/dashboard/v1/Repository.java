package io.huyvu.reboot.backend.biz.user.dashboard.v1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface Repository {
    @Select("""
            SELECT id
                  ,title
                  ,picture_url
              FROM workspace
             WHERE id = #{id}
                   AND is_deleted = 0""")
    Optional<WpVo> selectWp(long id);

    @Select("""
            SELECT id
                  ,name
                  ,background_image
                  ,stared
                  ,wp_id
              FROM board
             WHERE id = #{bId}
                   AND id IN (SELECT 
                                FROM board_member
                               WHERE user_id = ${})
                   AND is_deleted = 0""")

    Optional<BoardVo> selectBoard(long bId, long uId);

    List<WpMemberVo> selectWpMem(long wpId);

    List<BoardVo> selectBoards(long wpId);

    List<WpVo> selectWps(Long uId);
}
