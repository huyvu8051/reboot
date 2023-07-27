package io.huyvu.reboot.user.card;

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
