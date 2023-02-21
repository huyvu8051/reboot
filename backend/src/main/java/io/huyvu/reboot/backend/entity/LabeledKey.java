package io.huyvu.reboot.backend.entity

import lombok.EqualsAndHashCode
import lombok.Getter

import java.io.Serializable

@EqualsAndHashCode
@Getter
public class LabeledKey implements Serializable {

    private Card card

    private Label label
}
