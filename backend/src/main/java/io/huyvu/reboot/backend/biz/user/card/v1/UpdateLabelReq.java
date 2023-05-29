package io.huyvu.reboot.backend.biz.user.card.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateLabelReq {
    @JsonProperty("bId")
    long bId;
    Long id;
    String title;
    String color;
    Boolean isDeleted;
}
