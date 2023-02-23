/**
 * @Author HuyVu
 * @CreatedDate 2/14/2023 12:51 PM
 */
package io.huyvu.reboot.backend.biz.user.board.v1;

public record CreateBoardReq(String title, long wp, String visibility) {}
