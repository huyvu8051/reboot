create table conv
(
    id bigint not null
        primary key,
    nm int    null
);

create table user_account
(
    id            bigint auto_increment
        primary key,
    username      varchar(255)                 null,
    password      varchar(255)                 null,
    full_name     varchar(255)                 null,
    picture_url   varchar(255)                 null,
    is_deleted    bit default b'0'             not null,
    created_date  char(19)                     null,
    created_by    varchar(30)                  null,
    modified_date char(19)                     null,
    modified_by   varchar(30)                  null,
    full_nm_fts   varchar(255) charset utf8mb3 null comment 'full name fulltext search',
    constraint UK_castjbvpeeus0r8lbpehiu0e4
        unique (username)
);

create table message
(
    id          bigint    not null
        primary key,
    content     text      null,
    c_id        bigint    not null,
    create_time timestamp not null,
    u_id        bigint    null,
    constraint message_conversation_id_fk
        foreign key (c_id) references conv (id),
    constraint message_user_account_id_fk
        foreign key (u_id) references user_account (id)
);

create index id
    on user_account (id);

create fulltext index user_account_full_nm_fts_index
    on user_account (full_nm_fts);

create index username
    on user_account (username);

create definer = root@localhost trigger user_account_before_insert
    before insert
           on user_account
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger user_account_before_update
    before update
                      on user_account
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create table workspace
(
    id            bigint auto_increment
        primary key,
    picture_url   varchar(255)     null,
    title         varchar(255)     null,
    is_deleted    bit default b'0' not null,
    created_by    varchar(30)      null,
    created_date  char(19)         null,
    modified_by   varchar(30)      null,
    modified_date char(19)         null
);

create table board
(
    id               bigint auto_increment
        primary key,
    workspace_id     bigint           null,
    name             varchar(255)     null,
    background_image varchar(255)     null,
    stared           bit default b'0' not null,
    visibility       int              null,
    created_by       varchar(30)      null,
    created_date     char(19)         null,
    is_deleted       bit default b'0' not null,
    modified_by      varchar(30)      null,
    modified_date    char(19)         null,
    constraint FKh8r4ryxrng25r7ko3yh5eaudu
        foreign key (workspace_id) references workspace (id)
);

create index id
    on board (id);

create definer = root@localhost trigger board_before_insert
    before insert
           on board
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger board_before_update
    before update
                      on board
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create table label
(
    id            bigint auto_increment
        primary key,
    board_id      bigint           null,
    color         varchar(255)     null,
    title         varchar(255)     null,
    created_by    varchar(30)      null,
    created_date  char(19)         null,
    is_deleted    bit default b'0' not null,
    modified_by   varchar(30)      null,
    modified_date char(19)         null,
    constraint FKnl4pv15ws9o049sxm6c6dl8oy
        foreign key (board_id) references board (id)
);

create index id
    on label (id);

create definer = root@localhost trigger label_before_insert
    before insert
           on label
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger label_before_update
    before update
                      on label
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create table lizt
(
    id            bigint auto_increment
        primary key,
    w_id          bigint                          null,
    board_id      bigint                          null,
    ordinal       decimal(20, 6) default 0.000000 not null,
    title         varchar(255)                    null,
    is_deleted    bit            default b'0'     not null,
    created_date  char(19)                        null,
    created_by    varchar(30)                     null,
    modified_date char(19)                        null,
    modified_by   varchar(30)                     null,
    constraint FK_w_id
        foreign key (w_id) references workspace (id),
    constraint FKosv002clbdihfs44dffmu2egt
        foreign key (board_id) references board (id)
);

create table card
(
    id                bigint auto_increment
        primary key,
    lizt_id           bigint                          null comment 'Lizt Id',
    b_id              bigint                          null comment 'Board Id',
    title             varchar(255)                    null,
    ordinal           decimal(20, 6) default 0.000000 not null,
    automation        varchar(255)                    null,
    cover_color       varchar(255)                    null,
    cover_size        varchar(255)                    null,
    cover_url         varchar(255)                    null,
    description       longtext collate utf8mb4_bin    null
        check (json_valid(`description`)),
    due_date          datetime                        null,
    due_date_complete bit            default b'0'     not null,
    due_date_reminder varchar(255)                    null,
    is_template       bit            default b'0'     not null,
    location          varchar(255)                    null,
    start_date        datetime                        null,
    created_by        varchar(30)                     null,
    created_date      char(19)                        null,
    is_deleted        bit            default b'0'     not null,
    modified_by       varchar(30)                     null,
    modified_date     char(19)                        null,
    constraint FK2pmnauf5d5cg76rtydn0dofox
        foreign key (lizt_id) references lizt (id),
    constraint FK_b_id
        foreign key (b_id) references board (id)
);

create table activity
(
    id            bigint auto_increment
        primary key,
    card_id       bigint       null,
    user_id       bigint       null,
    content       varchar(255) null,
    created_by    varchar(30)  null,
    created_date  char(19)     null,
    is_deleted    bit          not null,
    modified_by   varchar(30)  null,
    modified_date char(19)     null,
    constraint FK4l3j89hkh9f2p987piyh4pgb0
        foreign key (card_id) references card (id),
    constraint FKb0e1g6c44ampoe1ondy9t6v8w
        foreign key (user_id) references user_account (id)
);

create index id
    on activity (id);

create definer = root@localhost trigger activity_before_insert
    before insert
           on activity
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger activity_before_update
    before update
                      on activity
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create table attachment
(
    id            bigint auto_increment
        primary key,
    card_id       bigint           null,
    name          varchar(255)     null,
    file_nm       varchar(255)     not null comment 'file name',
    type          varchar(255)     null,
    created_by    varchar(30)      null,
    created_date  char(19)         null,
    is_deleted    bit default b'0' not null,
    modified_by   varchar(30)      null,
    modified_date char(19)         null,
    constraint FKpyjq6uiperx43dbsny1gjvxne
        foreign key (card_id) references card (id)
);

create index id
    on attachment (id);

create definer = root@localhost trigger attachment_before_insert
    before insert
           on attachment
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger attachment_before_update
    before update
                      on attachment
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create index b_id
    on card (b_id);

create fulltext index card_title_index
    on card (title);

create index id
    on card (id);

create definer = root@localhost trigger card_before_insert
    before insert
           on card
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger card_before_update
    before update
                      on card
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create table card_member
(
    id            bigint auto_increment
        primary key,
    card_id       bigint           not null,
    user_id       bigint           not null,
    notification  bit default b'0' not null,
    is_deleted    bit default b'0' not null,
    created_by    varchar(30)      null,
    created_date  char(19)         null,
    modified_by   varchar(30)      null,
    modified_date char(19)         null,
    constraint FKgp6lai9ewkcfodigcua5taanf
        foreign key (card_id) references card (id),
    constraint FKq5low2n4jxef19lg35k6syqru
        foreign key (user_id) references user_account (id)
);

create index card_id
    on card_member (card_id);

create index id
    on card_member (id);

create definer = root@localhost trigger card_member_before_insert
    before insert
           on card_member
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger card_member_before_update
    before update
                      on card_member
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create table checklist
(
    id                 bigint auto_increment
        primary key,
    card_id            bigint           null,
    title              varchar(255)     null,
    show_checked_items bit default b'0' not null,
    is_deleted         bit default b'0' not null,
    created_by         varchar(30)      null,
    created_date       char(19)         null,
    modified_by        varchar(30)      null,
    modified_date      char(19)         null,
    constraint FKmq05kujpd06x59cm9u46s9eui
        foreign key (card_id) references card (id)
);

create index id
    on checklist (id);

create definer = root@localhost trigger checklist_before_insert
    before insert
           on checklist
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger checklist_before_update
    before update
                      on checklist
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create table checklist_item
(
    id                bigint auto_increment
        primary key,
    user_id           bigint           null,
    checklist_id      bigint           null,
    due_date          datetime         null,
    due_date_reminder varchar(255)     null,
    is_check          bit default b'0' not null,
    title             varchar(255)     null,
    is_deleted        bit default b'0' not null,
    created_by        varchar(30)      null,
    created_date      char(19)         null,
    modified_by       varchar(30)      null,
    modified_date     char(19)         null,
    constraint FKn8v1y7el5kl0xefukq4nu3gfm
        foreign key (checklist_id) references checklist (id),
    constraint FKo1pmhdej4mt1uhfrpq9hn68hu
        foreign key (user_id) references user_account (id)
);

create index id
    on checklist_item (id);

create definer = root@localhost trigger checklist_item_before_insert
    before insert
           on checklist_item
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger checklist_item_before_update
    before update
                      on checklist_item
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create table labeled
(
    id            bigint auto_increment
        primary key,
    card_id       bigint           not null,
    label_id      bigint           not null,
    created_by    varchar(30)      null,
    created_date  char(19)         null,
    is_deleted    bit default b'0' not null,
    modified_by   varchar(30)      null,
    modified_date char(19)         null,
    constraint FKf248152pjipi76om7ie74wde9
        foreign key (card_id) references card (id),
    constraint FKt0fjqpq3kfepviqionu0qu866
        foreign key (label_id) references label (id)
);

create index card_id
    on labeled (card_id);

create index id
    on labeled (id);

create definer = root@localhost trigger labeled_before_insert
    before insert
           on labeled
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger labeled_before_update
    before update
                      on labeled
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create index id
    on lizt (id);

create index w_id
    on lizt (w_id);

create definer = root@localhost trigger lizt_before_insert
    before insert
           on lizt
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger lizt_before_update
    before update
                      on lizt
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create index id
    on workspace (id);

create definer = root@localhost trigger workspace_before_insert
    before insert
           on workspace
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger workspace_before_update
    before update
                      on workspace
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

create table workspace_member
(
    id            bigint auto_increment
        primary key,
    user_id       bigint           not null,
    wp_id         bigint           not null,
    is_admin      bit default b'0' not null,
    created_by    varchar(30)      null,
    created_date  char(19)         null,
    is_deleted    bit default b'0' not null,
    modified_by   varchar(30)      null,
    modified_date char(19)         null,
    constraint FK63j92rshcw8dephxvjh9iha1p
        foreign key (wp_id) references workspace (id),
    constraint FKmqpu62lr3vvysaqeuj9a6e62u
        foreign key (user_id) references user_account (id)
);

create index id
    on workspace_member (id);

create index user_id
    on workspace_member (user_id);

create index wp_id
    on workspace_member (wp_id);

create definer = root@localhost trigger workspace_member_before_insert
    before insert
           on workspace_member
               for each row
BEGIN
SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END;

create definer = root@localhost trigger workspace_member_before_update
    before update
                      on workspace_member
                      for each row
BEGIN
SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END;

