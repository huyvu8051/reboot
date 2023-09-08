package io.huyvu.reboot.message.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.logging.Level;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageInitDTO {
    List<ConvListItemDTO> convs;
    List<MessageItemDTO> msgs;
}
