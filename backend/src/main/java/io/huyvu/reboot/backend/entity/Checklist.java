package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Checklist extends GenericEntity {


    private Long id;

    private String title;
    private boolean showCheckedItems;


    private List<ChecklistItem> items = new ArrayList<>();


    private Card card;
}
