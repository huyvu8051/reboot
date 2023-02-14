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
        @AssociationOverride(name = "key.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "key.card", joinColumns = @JoinColumn(name = "card_id"))
})
public class CardMember extends GenericEntity{
    @EmbeddedId
    private CardMemberKey key = new CardMemberKey();
    private boolean notification;
}
