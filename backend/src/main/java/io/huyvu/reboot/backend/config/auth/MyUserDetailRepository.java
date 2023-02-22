package io.huyvu.reboot.backend.config.auth;

import io.huyvu.reboot.backend.entity.UserAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MyUserDetailRepository {
    Optional<UserAccount> findOneByUsername(String username);

}
