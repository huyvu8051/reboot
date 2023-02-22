/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:24 AM
 */
package io.huyvu.reboot.backend.biz.user.workspace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWpResp {
    private Long id;
    private String title;
}
