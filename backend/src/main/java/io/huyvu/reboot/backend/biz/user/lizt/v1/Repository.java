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

    @Select("""
            <script>
                SELECT id
                      ,board_id
                      ,w_id
                      ,ordinal
                      ,title
                  FROM lizt
                 WHERE board_id = #{bId}
                 <if test="greater">
                       AND ordinal &gt; #{ordinal}
                 </if>       
                 <if test="!greater">
                       AND ordinal &lt; #{ordinal}
                 </if> 
                       AND is_deleted = 0
                 ORDER BY ordinal 
                 <if test="!greater">
                      DESC
                 </if> 
                 LIMIT 1
            </script>""")
    LiztVo selectLzNext(long bId, double ordinal, boolean greater);

    @Insert("""
            SET @ordinal = (SELECT IFNULL(MAX(ordinal), 0) + 50
                              FROM card
                             WHERE lizt_id = #{lId});
            INSERT INTO card
               SET lizt_id = #{lId}
                  ,b_id = #{bId}
                  ,title = #{title}
                  ,ordinal = @ordinal""")
    void insertCard(long lId, long bId, String title);




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
}
