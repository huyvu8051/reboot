package io.huyvu.reboot.backend.biz.user.board.v1;

import java.util.List;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:34 PM
 */
public interface IService {
    List<BoardVo> getAll(long wpId, long userId);

    long create(String name, long wpId, long userId);
}
