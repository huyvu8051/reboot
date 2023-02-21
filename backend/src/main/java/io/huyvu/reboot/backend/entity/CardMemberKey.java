package io.huyvu.reboot.backend.entity

import lombok.EqualsAndHashCode
import lombok.Getter

import java.io.Serializable

@EqualsAndHashCode
@Getter
public class CardMemberKey implements Serializable {

    private UserAccount user


    private Card card
}
