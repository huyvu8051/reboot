package io.huyvu.reboot.security.google.v1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface Repository {

    @Select("""
            select id
                  ,username
                  ,full_name
                  ,picture_url
              from user_account
             where username = #{username}
                   and is_deleted = false
            """)
    UserAccount findOneByUsername(String username);

    @Select("""
            select id
                  ,username
                  ,full_name
                  ,picture_url
              from user_account
             where id = #{id}
                   and is_deleted = false
            """)
    UserAccount findOneById(long id);

    @Insert({"""
            insert into user_account
               set username = #{username}
                  ,full_name = #{fullName}
                  ,picture_url = #{pictureUrl}   
                  ,full_nm_fts = #{fullNmFts}   
            """})
    void save(String username, String fullName, String pictureUrl, String fullNmFts);

    @Select("""
            select id
            from board
            where is_deleted = false
              and workspace_id in (select workspace_id
                                   from workspace_member
                                   where user_id = #{userId}
                                     and board.is_deleted = false)
            """)
    List<Long> searchAllBoardId(long userId);
}


