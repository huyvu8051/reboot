package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Lizt extends GenericEntity {

    
    
    private Long id;

    private String title;
    private int ordinal;


    private List<Card> cards = new ArrayList<>();

    private Board board;
}
