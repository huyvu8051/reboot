package io.huyvu.reboot.user.dashboard;

public interface IService {
    DashboardVo getBoard(long uId, Long wpId, Long bId, Long cId);
}
