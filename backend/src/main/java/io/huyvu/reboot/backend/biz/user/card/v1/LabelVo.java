package io.huyvu.reboot.backend.biz.user.card.v1;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LabelVo {
    long id;
    String color;
    String title;
}
