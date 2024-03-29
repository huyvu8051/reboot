package io.huyvu.reboot.message.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageItemDTO {
    long id;
    String content;
    @JsonProperty("cId")
    long cId;
    String createTime;
    @JsonProperty("uId")
    long uId;
}
