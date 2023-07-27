package io.huyvu.reboot.message.service;

import io.huyvu.reboot.message.mapper.ConversationMapper;
import io.huyvu.reboot.message.model.ConvListItemDTO;
import io.huyvu.reboot.message.model.SaveConvDTO;
import io.huyvu.reboot.security.util.SecurityUtils;
import io.huyvu.reboot.socket.service.DashboardBroadcast;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {
    private final ConversationMapper mapper;
    private final DashboardBroadcast broadcast;

    @Override
    public void save(SaveConvDTO conv) {
        mapper.insertConv(conv);
    }

    @Override
    public List<ConvListItemDTO> getList() {
        long uId = SecurityUtils.uId();
        return mapper.selectAllConv(uId);
    }
}
