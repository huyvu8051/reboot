/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:24 AM
 */
package io.huyvu.reboot.backend.user.workspace;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateWpResp {
    private Long id;
    private String title;
}
