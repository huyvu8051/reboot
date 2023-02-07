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
        @AssociationOverride(name = "key.board", joinColumns = @JoinColumn(name = "board_id"))
})
public class BoardMember extends GenericEntity {
    @EmbeddedId
    private BoardMemberId key = new BoardMemberId();
}
