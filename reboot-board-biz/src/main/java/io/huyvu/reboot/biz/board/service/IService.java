package io.huyvu.reboot.biz.board.service;

import io.huyvu.reboot.biz.board.model.BoardVo;

import java.util.List;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:34 PM
 */
public interface IService {
    List<BoardVo> getAll(long wpId);

    long create(String name, long wpId, long userId);
}
