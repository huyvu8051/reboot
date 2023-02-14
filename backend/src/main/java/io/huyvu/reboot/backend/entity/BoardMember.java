package io.huyvu.reboot.backend.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@Entity
@AssociationOverrides({
        @AssociationOverride(name = "key.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "key.board", joinColumns = @JoinColumn(name = "board_id"))
})
public class BoardMember extends GenericEntity {
    @EmbeddedId
    private BoardMemberKey key = new BoardMemberKey();
}
