package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ChecklistItem extends GenericEntity {


    private Long id;

    private String title;

    private boolean isCheck;


    private UserAccount assign;

    private LocalDateTime dueDate;

    private String dueDateReminder;


    private Checklist checklist;

}
