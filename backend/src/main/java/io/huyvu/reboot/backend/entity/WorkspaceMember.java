package io.huyvu.reboot.backend.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AssociationOverrides({
        @AssociationOverride(name = "key.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "key.wp", joinColumns = @JoinColumn(name = "wp_id"))
})
public class WorkspaceMember extends GenericEntity {
    @EmbeddedId
    private WorkspaceMemberId key = new WorkspaceMemberId();
    private boolean isAdmin;
}
