package io.huyvu.reboot.backend.entity

import lombok.Data
import lombok.experimental.Accessors


import java.util.ArrayList
import java.util.List

@Accessors(chain = true)
@Data

public class Board extends GenericEntity {
    
    
    private Long id

    private String name
    private String backgroundImage
    private boolean stared
    private Visibility visibility

    private List<BoardMember> members = new ArrayList<>()

    private List<Lizt> lizts = new ArrayList<>()

    private List<Label> labels = new ArrayList<>()

    private Workspace workspace


}
