package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attachment extends GenericEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

}
