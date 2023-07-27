package io.huyvu.reboot.message.service;


import io.huyvu.reboot.message.model.ConvListItemDTO;
import io.huyvu.reboot.message.model.SaveConvDTO;

import java.util.List;

public interface ConversationService {
    void save(SaveConvDTO conv);

    List<ConvListItemDTO> getList();

}
