package io.huyvu.reboot.backend.config.socketio;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Repository {

    @Select("""
            SET @b_id = (SELECT b_id
            				   FROM card
            				  WHERE id = #{cId});
            				 
            SELECT workspace_id
              FROM board
             WHERE id = @b_id""")
    long selectWpIdFromCId(long cId);

    @Select("""
            SELECT workspace_id
              FROM board
             WHERE id = #{bId}""")
    long selectWpIdFromBId(long bId);
}
