package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Label extends GenericEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String color;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
