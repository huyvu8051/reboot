package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@AssociationOverrides({
        @AssociationOverride(name = "key.card", joinColumns = @JoinColumn(name = "card_id")),
        @AssociationOverride(name = "key.label", joinColumns = @JoinColumn(name = "label_id"))
})
public class Labeled extends GenericEntity {
    @EmbeddedId
    private LabeledKey key = new LabeledKey();
}
