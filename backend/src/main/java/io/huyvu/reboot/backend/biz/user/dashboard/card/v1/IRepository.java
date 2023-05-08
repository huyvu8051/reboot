package io.huyvu.reboot.backend.biz.user.dashboard.card.v1;

import io.huyvu.reboot.backend.biz.user.lizt.v1.UpdateCardDetailsReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface IRepository {
    @Select("""
            SET @b_id = (SELECT b_id
            				   FROM card
            				  WHERE id = #{cId});
            				 
            SELECT workspace_id
              FROM board
             WHERE id = @b_id""")
    long selectWpId(long cId);
    @Update("""
            <script>
            update card
               set id          = #{id}
                 <if test="lId != null">
                 , lizt_id     = #{lId}
                 </if>
                 <if test="title != null">
                 , title       = #{title}
                 </if>
                 <if test="ordinal != null">
                 , ordinal     = #{ordinal}
                 </if>
                 <if test="coverUrl != null">
                 , cover_url=#{coverUrl}
                 </if>
                 <if test="description != null">
                 , description = #{description}
                 </if>
                 <if test="isDeleted != null">
                 , is_deleted  = #{isDeleted}
                 </if>
               where id = #{id};
            </script>""")
    void updateCardDetails(UpdateCardDetailsReq req);

    @Select("""
            select id
                 , name
                 , (select workspace.title
                    from workspace
                    where id = board.workspace_id) as wNm
            from board
            where is_deleted = false
              and workspace_id in (select wp_id
                                   from workspace_member
                                   where user_id = #{uId}
                                     and is_deleted = false)""")
    List<BoardsDetailsResp> selectBoardsDetails(long uId);
}
