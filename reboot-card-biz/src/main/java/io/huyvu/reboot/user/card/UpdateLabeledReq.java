package io.huyvu.reboot.user.card;

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
