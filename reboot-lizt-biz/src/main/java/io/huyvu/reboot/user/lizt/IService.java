package io.huyvu.reboot.user.lizt;

public interface IService {
    void createLz(String title, long bId, long uId);

    void updateLz(long lId, String title, Double ordinal);




    void createCard(long lId, String title, long uId);

    void updateCard(long cId, double ordinal, Long lId);


}
