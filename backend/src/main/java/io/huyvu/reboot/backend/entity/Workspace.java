/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 8:41 AM
 */
package io.huyvu.reboot.backend.entity

import lombok.Data
import lombok.NoArgsConstructor

import java.util.ArrayList
import java.util.List

@Data

@NoArgsConstructor
public class Workspace extends GenericEntity {


    private long id

    private String title

    private String pictureUrl

    private List<WorkspaceMember> members = new ArrayList<>()

    private List<Board> labels = new ArrayList<>()

}
