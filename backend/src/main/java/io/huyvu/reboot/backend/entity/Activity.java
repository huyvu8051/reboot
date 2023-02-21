package io.huyvu.reboot.backend.entity

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor



@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Activity extends GenericEntity{
    
    
    private Long id


    private UserAccount user



    private Card card

    private String content
}
