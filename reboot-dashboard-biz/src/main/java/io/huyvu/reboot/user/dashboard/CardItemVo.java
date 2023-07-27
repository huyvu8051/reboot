package io.huyvu.reboot.user.dashboard;

import java.util.Date;

public record CardItemVo(long id,
                         long liztId,
                         long bId,
                         String title,
                         double ordinal,
                         String automation,
                         String coverColor,
                         String overSize,
                         String coverUrl,
                         String description,
                         Date dueDate,
                         boolean dueDateComplete,
                         String dueDateReminder,
                         boolean isTemplate,
                         String location,
                         Date startDate) {
}
