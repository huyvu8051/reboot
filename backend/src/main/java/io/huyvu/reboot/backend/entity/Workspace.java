/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 8:41 AM
 */
package io.huyvu.reboot.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Workspace extends GenericEntity {
    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String pictureUrl;

    @OneToMany(mappedBy = "key.wp")
    private List<WorkspaceMember> members = new ArrayList<>();

    @OneToMany(mappedBy = "workspace")
    private List<Board> labels = new ArrayList<>();

}
