package io.huyvu.reboot.backend.biz.user.lizt.v1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Repository {

    @Insert("""
            SET @w_id = (SELECT workspace_id 
                           FROM board
                          WHERE id = #{bId});
            SET @ordinal = (SELECT IFNULL(MAX(ordinal), 0) + 50
                              FROM lizt
                             WHERE board_id = #{bId});
            INSERT INTO lizt
               SET board_id = #{bId}
                  ,w_id = @w_id
                  ,ordinal = @ordinal
                  ,title = #{title}""")
    long insertLz(String title, long bId, long uId);

    @Select("""
            SELECT id
                  ,board_id
                  ,w_id
                  ,ordinal
                  ,title
              FROM lizt
             WHERE id = #{id}
                   AND is_deleted = 0""")
    LiztVo selectLz(long id);

    @Update("""
            <script>
                UPDATE lizt
                   SET id = #{lId} 
                <if test="title != null">
                      ,title = #{title}
                </if>
                <if test="ordinal != null">
                      ,ordinal = #{ordinal}
                </if>
                <if test="isDel != null">
                      ,is_deleted = #{isDel}
                </if>
                 WHERE id = #{lId}
            </script>""")
    void updateLz(long lId, String title, Double ordinal, Boolean isDel);


    @Insert("""
            SET @ordinal = (SELECT IFNULL(MAX(ordinal), 0) + 50
                              FROM card
                             WHERE lizt_id = #{lId});
            INSERT INTO card
               SET lizt_id = #{lId}
                  ,b_id = #{bId}
                  ,title = #{title}
                  ,ordinal = @ordinal""")
    long insertCard(long lId, long bId, String title);




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
             WHERE id = #{cId}""")

    CardVo selectCard(long cId);

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
             WHERE lizt_id = #{lId}
             AND ordinal > #{ordinal}
             ORDER BY ordinal 
             
                 LIMIT 1""")

    CardVo selectCardNext(long lId, double ordinal);

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
             WHERE lizt_id = #{lId}
             AND ordinal < #{ordinal}
             ORDER BY ordinal DESC
                 LIMIT 1""")

    CardVo selectCardPrevious(long lId, double ordinal);


    @Update("""
            UPDATE card
               SET ordinal = #{ordinal}
                  ,lizt_id = #{lId}
             WHERE id = #{id}""")
    void updateCardOrdinal(long id, double ordinal, long lId);
    @Update("""
            UPDATE card
               SET ordinal = #{ordinal}
             WHERE id = #{id}""")
    void updateCardOrdinal2(long id, double ordinal);


    @Select("""
           
            SET @bId = (SELECT board_id 
                          FROM lizt
                         WHERE id = #{lId});
                         
            SET @ordinal = (SELECT ordinal
                              FROM lizt 
                             WHERE id = #{lId});
            
            SET @new_ordinal = (SELECT ordinal 
                                  FROM lizt
                                 WHERE board_id = @bId
                                       AND ordinal >= @ordinal
                                       AND id != #{lId}
                                       AND is_deleted = 0
                                 ORDER BY ordinal, id
                                 LIMIT 1);
                                 
            SELECT IFNULL(@new_ordinal, (SELECT MAX(ordinal) + 50
                                           FROM lizt
                                          WHERE board_id = @bId
                                                AND is_deleted = 0))""")
    double selectLzNextOrdinal(long lId);

    @Select("""
                 
            SET @bId = (SELECT board_id 
                          FROM lizt
                         WHERE id = #{lId});
                         
            SET @ordinal = (SELECT ordinal
                              FROM lizt 
                             WHERE id = #{lId});
            
            SET @new_ordinal = (SELECT ordinal 
                                  FROM lizt
                                 WHERE board_id = @bId
                                       AND ordinal <= @ordinal
                                       AND id != #{lId}
                                       AND is_deleted = 0
                                 ORDER BY ordinal, id
                                 LIMIT 1);
                                 
            SELECT IFNULL(@new_ordinal, (SELECT MIN(ordinal) - 50
                                           FROM lizt
                                          WHERE board_id = @bId
                                                AND is_deleted = 0))""")
    double selectLzPrevOrdinal(long boardId);



    @Select("""
                 
            SET @lId = (SELECT lizt_id 
                          FROM card
                         WHERE id = #{id});
                         
            SET @ordinal = (SELECT ordinal 
                              FROM card
                             WHERE id = #{id});
            
            SET @new_ordinal = (SELECT ordinal 
                                  FROM card
                                 WHERE lizt_id = @lId
                                       AND ordinal >= @ordinal
                                       AND id != #{id}
                                       AND is_deleted = 0
                                 ORDER BY ordinal, id
                                 LIMIT 1);
                                 
            SELECT IFNULL(@new_ordinal, (SELECT MAX(ordinal) + 50
                                           FROM card
                                          WHERE lizt_id = @lId
                                                AND is_deleted = 0))""")
    double selectCardNextOrdinal(long id);

    @Select("""
                 
            SET @lId = (SELECT lizt_id 
                          FROM card
                         WHERE id = #{id});
                         
            SET @ordinal = (SELECT ordinal 
                              FROM card
                             WHERE id = #{id});
            
            SET @new_ordinal = (SELECT ordinal 
                                  FROM card
                                 WHERE lizt_id = @lId
                                       AND ordinal <= @ordinal
                                       AND id != #{id}
                                       AND is_deleted = 0
                                 ORDER BY ordinal, id
                                 LIMIT 1);
                                 
            SELECT IFNULL(@new_ordinal, (SELECT MIN(ordinal) - 50
                                           FROM card
                                          WHERE lizt_id = @lId
                                                AND is_deleted = 0))""")
    double selectCardPreviousOrdinal(Long id);

    @Select("""
            SET @b_id = (SELECT b_id
            				   FROM card
            				  WHERE id = #{cId});
            				 
            SELECT workspace_id
              FROM board
             WHERE id = @b_id""")
    long selectWpId(long cId);
}
