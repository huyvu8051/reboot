package io.huyvu.reboot.backend.biz.user.lizt.v1;

public interface IService {
    void createLz(String title,long bId, long uId);

    void updateLz(long lId, String title, Double ordinal,Long desId);
}
