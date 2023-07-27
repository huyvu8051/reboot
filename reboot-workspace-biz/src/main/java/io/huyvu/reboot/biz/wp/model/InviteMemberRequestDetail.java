package io.huyvu.reboot.biz.wp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class InviteMemberRequestDetail {
    String email;
}
