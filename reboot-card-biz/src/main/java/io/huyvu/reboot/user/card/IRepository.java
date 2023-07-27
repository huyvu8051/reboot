package io.huyvu.reboot.user.card;

import org.apache.ibatis.annotations.Insert;
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

    @Select("""
            select b_id
            from card
            where id = #{cId}""")
    long selectBoardId(long cId);


    @Insert("""
            insert into attachment
            set card_id = #{cId},
                name    = #{originalFileName},
                file_nm = #{storeFileName},
                type    = #{fileExtension}""")
    long insertAttachment(long cId, String originalFileName, String storeFileName, String fileExtension);

    @Select("""
            select id,
                   card_id as cid,
                   name,
                   file_nm,
                   type,
                   created_date
            from attachment
            where id = #{id}""")
    AttachmentVo selectAttachment(long id);


    @Insert("""
            insert into labeled
            set label_id = #{labelId},
                card_id = #{cardId}""")
    long insertLabeled(long labelId, long cardId);

    @Update("""
            update labeled
            set is_deleted = #{isDeleted}
            where id = #{id}""")
    void updateLabeled(long id, boolean isDeleted);

    @Insert("""
            insert into label
            set board_id = #{bId},
                title = #{title},
                color = #{color}""")

    long insertLabel(long bId, String title, String color);


    @Update("""
            <script>
            update label
            set id = id
                <if test="bId != null">
                ,board_id = #{bId}
                </if>
                <if test="title != null">
                ,title = #{title}
                </if>
                <if test="color != null">
                ,color = #{color}
                </if>
                <if test="isDeleted != null">
                ,is_deleted = #{isDeleted}
                </if>
            where id = #{id}
            </script>""")
    void updateLabel(UpdateLabelReq req);
}