package io.huyvu.reboot.message.mapper;

import io.huyvu.reboot.message.model.ConvListItemDTO;
import io.huyvu.reboot.message.model.SaveConvDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConversationMapper {
    void insertConv(SaveConvDTO conv);

    List<ConvListItemDTO> selectAllConv(long uId);
}
