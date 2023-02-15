package io.huyvu.reboot.backend.entity;

import lombok.Data;
import lombok.experimental.Accessors;



@Accessors(chain = true)
@Data

public class BoardMember extends GenericEntity {
    private BoardMemberKey key = new BoardMemberKey();
}
