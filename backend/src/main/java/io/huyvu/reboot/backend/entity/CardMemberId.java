package io.huyvu.reboot.backend.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
public class CardMemberId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUserDetails user;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
}
