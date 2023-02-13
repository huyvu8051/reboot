package io.huyvu.reboot.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Card extends GenericEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private String dueDateReminder;
    private boolean dueDateComplete;
    private String coverUrl;
    private String coverSize;
    private String coverColor;


    @OneToMany(mappedBy = "key.card")
    private List<CardMember> members = new ArrayList<>();

    @OneToMany(mappedBy = "key.label")
    private List<Labeled> labels = new ArrayList<>();

    @OneToMany(mappedBy = "card")
    private List<Checklist> checklists = new ArrayList<>();

    @OneToMany(mappedBy = "card")
    private List<Attachment> attachments = new ArrayList<>();

    private String location;

    private String automation;

    private boolean isTemplate;


    @OneToMany(mappedBy = "card")
    private List<Activity> activities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "lizt_id")
    private Lizt lizt;
}
