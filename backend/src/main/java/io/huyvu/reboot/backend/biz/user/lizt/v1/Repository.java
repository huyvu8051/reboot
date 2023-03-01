package io.huyvu.reboot.backend.biz.user.lizt.v1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Repository {

    @Insert("""
            SET @ORDINAL = (SELECT MAX(ordinal)
                              FROM lizt
                             WHERE board_id = #{bId});
            INSERT INTO lizt
               SET board_id = #{bId}
                  ,ordinal = @ORDINAL
                  ,title""")
    long insertLz(String title,long bId, long uId);

    @Select("""
            SELECT id
                  ,board_id
                  ,workspace_id
                  ,ordinal
                  ,title
              FROM lizt
             WHERE id = #{id}
               AND isDeleted = 0""")
    LiztVo selectLz(long id);
}
