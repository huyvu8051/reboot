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
    LiztVo selectLzNext(long bId,double ordinal, boolean greater);
}
