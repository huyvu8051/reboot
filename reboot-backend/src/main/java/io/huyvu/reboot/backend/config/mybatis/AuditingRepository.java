package io.huyvu.reboot.backend.config.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author HuyVu
 * @CreatedDate 2/23/2023 8:58 AM
 */
@Mapper
public interface AuditingRepository {
    @Select("set @USER_CTX = #{userCtx};")
    void setUserCtx(String userCtx);

    @Select("select last_insert_id();")
    long getLastInsertId();

}