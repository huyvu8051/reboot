package io.huyvu.reboot.message.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendMsgDTO {
    long id;
    @JsonProperty("uId")
    long uId;
    @JsonProperty("cId")
    String cId;
    String content;
    long sendAt;
}
