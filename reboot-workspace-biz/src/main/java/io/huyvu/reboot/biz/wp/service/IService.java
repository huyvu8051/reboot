package io.huyvu.reboot.biz.wp.service;

import io.huyvu.reboot.biz.wp.model.InviteMemberReq;
import io.huyvu.reboot.biz.wp.model.ListWpItem;
import io.huyvu.reboot.biz.wp.model.UserAccount;
import io.huyvu.reboot.biz.wp.model.WpDetails;

import java.util.List;

/**
 * @Author HuyVu
 * @CreatedDate 2/13/2023 11:22 AM
 */
public interface IService {
    WpDetails create(String wpNm, long adminId);

    List<ListWpItem> getList(long userId);

    WpDetails getDetails(long wpId, long id);

    List<UserAccount> searchMembers(String keyword);

    void inviteToWp(InviteMemberReq req);
}
