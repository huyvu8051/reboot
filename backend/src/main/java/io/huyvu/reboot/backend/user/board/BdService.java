package io.huyvu.reboot.backend.user.board

import java.util.List

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:34 PM
 */
public interface BdService {
    List<BoardLsItem> getAll(long wpId, long userId)

    long create(String name, long wpId, long userId)
}
