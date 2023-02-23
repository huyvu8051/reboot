package io.huyvu.reboot.backend.config.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author HuyVu
 * @CreatedDate 2/23/2023 8:58 AM
 */
@Mapper
public interface UserContextRepository {
    String SET_USER_CTX_QUERY = "SET @USER_CTX = #{USER_CTX};";

    @Select(SET_USER_CTX_QUERY)
    void setUserCtx(String val);
}