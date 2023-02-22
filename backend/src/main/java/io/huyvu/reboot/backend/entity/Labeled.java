package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Labeled extends GenericEntity {
    private LabeledKey key = new LabeledKey();
}
