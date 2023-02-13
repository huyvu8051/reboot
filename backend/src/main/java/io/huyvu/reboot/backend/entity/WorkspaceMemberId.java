package io.huyvu.reboot.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class WorkspaceMemberId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name = "wp_id")
    private Workspace wp;
}
