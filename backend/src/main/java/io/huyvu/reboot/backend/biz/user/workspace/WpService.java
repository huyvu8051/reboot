package io.huyvu.reboot.backend.biz.user.workspace;

import java.util.List;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:22 AM
 */
public interface WpService {
    WpDetails create(String wpNm, long adminId);

    List<ListWpItem> getList(long userId);

    WpDetails getDetails(long wpId, long id);
}
