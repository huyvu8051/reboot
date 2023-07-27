package io.huyvu.reboot.biz.board.repository;

import io.huyvu.reboot.biz.board.model.BoardVo;
import io.huyvu.reboot.biz.board.model.WorkspaceVo;
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
            select id
                  ,name
                  ,background_image as backgroundImage
                  ,stared
              from board
             where workspace_id = #{wpId}
                   and is_deleted = 0
            """)
    List<BoardVo> findAllOwnBoard(long wpId);

    @Select("""
            select w.id as id
                  ,w.title as title
                  ,w.picture_url as pictureUrl
            from workspace w
            where w.id = (select wp_id
                            from workspace_member as wm
                           where wm.user_id = #{uId}
                                 and wm.wp_id = #{wpId})     
                  and is_deleted = 0
            """)
    Optional<WorkspaceVo> findWpById(long wpId, long uId);

    @Insert("""
            insert into	board
               set name = #{nm}
                 ,workspace_id = #{wpId}
            """)
    long insertBoard(String nm, long wpId);

    @Select("""
            select id
                  ,name
                  ,background_image as backgroundImage
                  ,stared
              from board
             where id = #{id}""")
    BoardVo findBoardById(long id);
}
