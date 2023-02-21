package io.huyvu.reboot.backend.entity


import lombok.Data



@Data

public class WorkspaceMember extends GenericEntity {
    private WorkspaceMemberKey key = new WorkspaceMemberKey()
    private boolean isAdmin
}
