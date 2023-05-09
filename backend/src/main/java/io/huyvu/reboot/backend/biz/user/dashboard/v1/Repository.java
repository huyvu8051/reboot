package io.huyvu.reboot.backend.biz.user.dashboard.v1;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
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
                  ,workspace_id
              FROM board
             WHERE id = #{bId}
     
                   AND is_deleted = 0""")
    Optional<BoardVo> selectBoard(long bId);

    @Select("""
            SELECT user_id
                  ,wp_id
                  ,is_admin
              FROM workspace_member
             WHERE wp_id = #{wpId}
                   AND is_deleted = 0""")
    List<WpMemberVo> selectWpMem(long wpId);


    @Select("""
            SELECT id
                  ,name
                  ,background_image
                  ,stared
                  ,workspace_id AS wp_id
              FROM board
             WHERE workspace_id = #{wpId}
                   AND is_deleted = 0
             ORDER BY id DESC""")
    List<BoardVo> selectBoards(long wpId);


    @Select("""
            SELECT id
                  ,title
                  ,picture_url
              FROM workspace
             WHERE id IN (SELECT wp_id
                            FROM workspace_member
                           WHERE user_id = #{uId}
                                 AND is_deleted = 0)
                   AND is_deleted = 0""")
    List<WpVo> selectWps(long uId);


    @Select("""
            SELECT id
                  ,board_id
                  ,w_id
                  ,ordinal
                  ,title
              FROM lizt
             WHERE board_id = #{bId}
               AND is_deleted = 0
             ORDER BY ordinal""")
    List<LiztVo> selectLizts(long bId);

    @Select("""
            SELECT id
            	  ,lizt_id
            	  ,b_id
            	  ,title
            	  ,ordinal
            	  ,automation
            	  ,cover_color
            	  ,cover_size
            	  ,cover_url
            	  ,description
            	  ,due_date
            	  ,due_date_complete
            	  ,due_date_reminder
            	  ,is_template
            	  ,location
            	  ,start_date
              FROM card
             WHERE b_id = #{bId}
                   AND is_deleted = 0
             ORDER BY ordinal""")
    List<CardItemVo> selectCardsByBId(long bId);


    @Select("""
            SELECT id
            	  ,lizt_id
            	  ,(SELECT l.title 
            	      FROM lizt AS l
            	     WHERE l.id = lizt_id) AS lizt_title
            	  ,b_id
            	  ,title
            	  ,ordinal
            	  ,IFNULL((SELECT notification
                             FROM card_member
                            WHERE card_id = #{cId}
                                  AND user_id = #{uId}
                            ORDER BY id DESC
                            LIMIT 1), 0) AS notification
            	  ,automation
            	  ,cover_color
            	  ,cover_size
            	  ,cover_url
            	  ,description
            	  ,due_date
            	  ,due_date_complete
            	  ,due_date_reminder
            	  ,is_template
            	  ,location
            	  ,start_date
              FROM card
             WHERE id = #{cId}
                   AND is_deleted = 0""")
    CardDetailsVo selectCardDetails(long cId, long uId);

    @Select("""
            select id
                  ,username
                  ,full_name
                  ,picture_url
              from user_account
             where id in (select user_id
              				     from card_member
             					 where card_id = #{cId}
             					       and is_deleted = 0)""")
    List<CardMember> selectCardMems(long cId);

    @Select("""
            select id
                  ,color
            	  ,title
              from label
             where id in (select label_id
              				from labeled
               			   where card_id = #{cId}
                   				 and is_deleted = 0)""")
    List<CardLabel> selectCardLabels(long cId);


    @Select("""
            select sql_calc_found_rows id,
                                       card_id as cid,
                                       name,
                                       type,
                                       created_date
            from attachment
            where card_id = #{cId}
            and is_deleted = false
            limit 0, 5
            """)
    @ResultType(AttachmentVo.class)
    Page<AttachmentVo> selectAttachments(long cId);
}
