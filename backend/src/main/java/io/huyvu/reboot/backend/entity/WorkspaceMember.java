package io.huyvu.reboot.backend.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@AssociationOverrides({
        @AssociationOverride(name = "key.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "key.wp", joinColumns = @JoinColumn(name = "wp_id"))
})
public class WorkspaceMember extends GenericEntity {
    @EmbeddedId
    private WorkspaceMemberKey key = new WorkspaceMemberKey();
    private boolean isAdmin;
}
