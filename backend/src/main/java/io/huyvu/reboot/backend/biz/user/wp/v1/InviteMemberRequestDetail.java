package io.huyvu.reboot.backend.biz.user.wp.v1;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class InviteMemberRequestDetail {
    String email;
}
