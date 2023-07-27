package io.huyvu.reboot.user.dashboard;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardDetailsVo {
    long id;
    long bId;
    CardListVo list;
    String title;
    double ordinal;
    boolean notification;
    String automation;
    String coverColor;
    String overSize;
    String coverUrl;
    String description;
    Date dueDate;
    boolean dueDateComplete;
    String dueDateReminder;
    boolean isTemplate;
    String location;
    Date startDate;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CardListVo {
        long id;
        String title;
    }


}

