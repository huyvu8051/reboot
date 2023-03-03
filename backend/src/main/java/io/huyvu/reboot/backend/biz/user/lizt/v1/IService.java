package io.huyvu.reboot.backend.biz.user.lizt.v1;

public interface IService {
    void createLz(String title, long bId, long uId);

    void updateLz(long lId, String title, Double ordinal, Long desId);

    void createCard(long lId, String title, long uId);

    void updateCard(long cId, double ordinal, Long lId);
}
