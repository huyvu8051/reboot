package io.huyvu.reboot.backend.biz.user.dashboard.v1;

public interface IService {
    DashboardVo getBoard(Long uId, Long wpId, Long bId, Long cId);
}
