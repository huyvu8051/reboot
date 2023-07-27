package io.huyvu.reboot.socket.service;

public interface DashboardBroadcast {
    void byCardId(long cId, String event, Object... toJsonObj);

    void byBoardId(long bId, String event, Object... toJsonObj);

    void byWorkspaceId(long bId, String event, Object... toJsonObj);
}
