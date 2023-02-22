package io.huyvu.reboot.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@EqualsAndHashCode
@Accessors(chain = true)
@Data
public class BoardMemberKey implements Serializable {

    private UserAccount user;


    private Board board;

}
