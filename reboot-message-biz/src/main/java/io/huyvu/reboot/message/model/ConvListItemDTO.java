package io.huyvu.reboot.message.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConvListItemDTO {
    double id;
    String nm;
}
