package io.huyvu.reboot.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChecklistItem extends GenericEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private boolean isCheck;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount assign;

    private LocalDateTime dueDate;

    private String dueDateReminder;

    @ManyToOne
    @JoinColumn(name = "checklist_id")
    private Checklist checklist;

}
