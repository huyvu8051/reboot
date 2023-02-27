package io.huyvu.reboot.backend.biz.user.dashboard.v1;

public interface IService {
    DashboardVo getBoard(long uId, Long wpId, Long bId, Long cId);
}
