package io.huyvu.reboot.backend.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
public class BoardMemberId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
