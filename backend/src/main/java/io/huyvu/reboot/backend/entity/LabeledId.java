package io.huyvu.reboot.backend.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@Getter
public class LabeledId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "label_id")
    private Label label;
}
