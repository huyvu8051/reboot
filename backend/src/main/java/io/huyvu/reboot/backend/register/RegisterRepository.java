package io.huyvu.reboot.backend.register;

import io.huyvu.reboot.backend.entity.UserAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterRepository {
    void save(UserAccount userAccount);
}
