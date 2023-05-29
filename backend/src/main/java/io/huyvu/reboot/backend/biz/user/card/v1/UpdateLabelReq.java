package io.huyvu.reboot.backend.biz.user.card.v1;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.checkerframework.framework.qual.DefaultQualifier;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateLabelReq {
    Long id;
    Long bId;
    String title;
    String color;
    Boolean isDeleted;
}
