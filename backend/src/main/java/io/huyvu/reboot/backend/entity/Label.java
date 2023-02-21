package io.huyvu.reboot.backend.entity

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor



@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Label extends GenericEntity {

    
    
    private Long id

    private String title
    private String color

    
    private Board board
}
