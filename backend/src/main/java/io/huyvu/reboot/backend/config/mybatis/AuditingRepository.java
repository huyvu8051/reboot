package io.huyvu.reboot.backend.config.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author HuyVu
 * @CreatedDate 2/23/2023 8:58 AM
 */
@Mapper
public interface AuditingRepository {
    @Select("SET @USER_CTX = #{USER_CTX};")
    void setUserCtx(String val);

    @Select("SELECT LAST_INSERT_ID();")
    long getLastInsertId();
}