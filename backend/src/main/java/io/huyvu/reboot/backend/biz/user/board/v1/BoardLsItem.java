package io.huyvu.reboot.backend.biz.user.board.v1;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 4:38 PM
 */
public interface BoardLsItem {
    long getId();

    String getName();

    String getBackgroundImage();

    boolean isStared();
}
