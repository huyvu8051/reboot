package io.huyvu.reboot.user.card;

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
