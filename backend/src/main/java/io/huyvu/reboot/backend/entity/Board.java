package io.huyvu.reboot.backend.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Data
@Entity
public class Board extends GenericEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String backgroundImage;
    private boolean stared;
    private Visibility visibility;

    @OneToMany(mappedBy = "key.board")
    private List<BoardMember> members = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Lizt> lizts = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Label> labels = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;


}
