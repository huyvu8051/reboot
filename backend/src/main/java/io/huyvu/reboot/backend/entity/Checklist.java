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
public class Checklist extends GenericEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private boolean showCheckedItems;


    @OneToMany(mappedBy = "checklist")
    private List<ChecklistItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
}
