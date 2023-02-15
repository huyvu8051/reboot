package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Attachment extends GenericEntity{
    
    
    private Long id;

    private String name;
    private String type;

    private Card card;

}
