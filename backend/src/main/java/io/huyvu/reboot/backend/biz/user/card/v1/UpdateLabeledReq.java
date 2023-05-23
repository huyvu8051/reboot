package io.huyvu.reboot.backend.biz.user.card.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateLabeledReq {
    Long id;
    long labelId;
    long cardId;
    @JsonProperty(value="isDeleted")
    boolean isDeleted;
}
