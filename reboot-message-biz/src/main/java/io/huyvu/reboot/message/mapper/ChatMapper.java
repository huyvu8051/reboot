package io.huyvu.reboot.message.mapper;

import io.huyvu.reboot.message.model.SendMsgDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {
    void insertMsg(SendMsgDTO msg, long uId);
}
