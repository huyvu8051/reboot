package io.huyvu.reboot.message.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageItemDTO {
    long id;
    String content;
    long cId;
    String createTime;
    long uId;
}
