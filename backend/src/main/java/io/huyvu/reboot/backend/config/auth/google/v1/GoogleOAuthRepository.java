package io.huyvu.reboot.backend.config.auth.google.v1;

import io.huyvu.reboot.backend.entity.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import static io.huyvu.reboot.backend.config.mybatis.MybatisAuditingInterceptor.SET_USER_CTX_QUERY;


@Mapper
public interface GoogleOAuthRepository {

    @Select({
            SET_USER_CTX_QUERY, """
            SELECT id
                  ,username
                  ,full_name
                  ,picture_url
              FROM user_account
             WHERE username = #{username}
                   AND is_deleted = false
            """
    })
    UserAccount findOneByUsername(String username);

    @Select("""
            SELECT id
                  ,username
                  ,full_name
                  ,picture_url
              FROM user_account
             WHERE id = #{id}
                   AND is_deleted = false
            """)
    UserAccount findOneById(long id);

    @Insert({SET_USER_CTX_QUERY, """
            INSERT INTO user_account
               SET username = #{username}
                  ,full_name = #{fullName}
                  ,picture_url = #{pictureUrl}   
            """})
    void save(String username, String fullName, String pictureUrl);

}


