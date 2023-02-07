package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lizt extends GenericEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private int ordinal;


    @OneToMany(mappedBy = "lizt")
    private List<Card> cards = new ArrayList<>();



    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
