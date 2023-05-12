package io.huyvu.reboot.backend.config.socketio;

public interface DashboardBroadcast {
    void byCardId(long cId, String event, Object... toJsonObj);
}
