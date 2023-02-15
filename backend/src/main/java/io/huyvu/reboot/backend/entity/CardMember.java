package io.huyvu.reboot.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter

public class CardMember extends GenericEntity {
    private CardMemberKey key = new CardMemberKey();
    private boolean notification;
}
