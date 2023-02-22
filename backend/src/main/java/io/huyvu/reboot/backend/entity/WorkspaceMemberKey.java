package io.huyvu.reboot.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class WorkspaceMemberKey implements Serializable {

    private UserAccount user;


    private Workspace wp;
}
