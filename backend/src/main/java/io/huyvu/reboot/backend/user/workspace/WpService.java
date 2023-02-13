package io.huyvu.reboot.backend.user.workspace;

import io.huyvu.reboot.backend.entity.Workspace;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:22 AM
 */
public interface WpService {
    Workspace create(String wpNm, long adminId);
}
