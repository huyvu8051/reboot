package io.huyvu.reboot.backend.biz.user.wp.v1;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class InviteMemberReq {
    List<InviteMemberRequestDetail> mems;
    String msg;
    long wId;
}
