package io.huyvu.reboot.message.controller;

import io.huyvu.reboot.message.model.ConvListItemDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ConvListDTO {
    List<ConvListItemDTO> convs;
}
