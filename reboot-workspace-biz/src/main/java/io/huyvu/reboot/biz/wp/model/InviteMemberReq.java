package io.huyvu.reboot.biz.wp.model;

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
