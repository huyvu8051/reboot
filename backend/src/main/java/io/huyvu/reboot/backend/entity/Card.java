package io.huyvu.reboot.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data

@NoArgsConstructor
public class Card extends GenericEntity {


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


    private List<CardMember> members = new ArrayList<>();

    private List<Labeled> labels = new ArrayList<>();

    private List<Checklist> checklists = new ArrayList<>();

    private List<Attachment> attachments = new ArrayList<>();

    private String location;

    private String automation;

    private boolean isTemplate;


    private List<Activity> activities = new ArrayList<>();


    private Lizt lizt;
}
