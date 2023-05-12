package io.huyvu.reboot.backend.biz.user.dashboard.v1;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data

public class CardDetailsVo {
    private long id;
    private long bId;
    private CardListVo list;
    private String title;
    private double ordinal;
    private boolean notification;
    private String automation;
    private String coverColor;
    private String overSize;
    private String coverUrl;
    private String description;
    private Date dueDate;
    private boolean dueDateComplete;
    private String dueDateReminder;
    private boolean isTemplate;
    private String location;
    private Date startDate;


    @Data
    public static class CardListVo{
        private long id;
        private String title;
    }


}

