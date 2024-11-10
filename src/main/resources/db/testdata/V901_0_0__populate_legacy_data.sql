create table legacy_users
(
    card_id       bigint       not null primary key,
    last_name     varchar(64)  null,
    first_name    varchar(64)  null,
    username      varchar(16)  null,
    birthday      varchar(8)   null,
    studprog      varchar(10)  null,
    membership    int          null,
    userlevel     int          null,
    password      varchar(64)  null,
    tab           int          null,
    cash          int          null,
    spent         int          null,
    borrowed      varchar(128) null,
    comment       varchar(128) null,
    misc          varchar(128) null,
    creation_date int          null
);

INSERT INTO legacy_users (card_id, last_name, first_name, username, birthday, studprog, membership, userlevel, password,
                          tab, cash, spent, borrowed, comment, misc, creation_date)
VALUES (10975012, 'Legacy', 'Man', 'man', '111195', 'BFY', 1, 9, 'C0FFEE', 5, -175,
        10500, '', '', 'Fyllesvin', 1387228753);
INSERT INTO legacy_users (card_id, last_name, first_name, username, birthday, studprog, membership, userlevel, password,
                          tab, cash, spent, borrowed, comment, misc, creation_date)
VALUES (242054311, 'Legacy', 'Person', 'person', '060689', 'PhD', 1, 1, '051307161D5F505A6B1919', 0, 75, 0,
        '', '?resmedlem', '', 1337958605);
INSERT INTO legacy_users (card_id, last_name, first_name, username, birthday, studprog, membership, userlevel, password,
                          tab, cash, spent, borrowed, comment, misc, creation_date)
VALUES (10993472, 'Legacy', 'Woman', 'woman', '060189', 'MFY', 1, 9, '091417061D5E505A5B0A0E', 1, -10, 35,
        ' ', 'Æresmedlem', '', 1337958605);
INSERT INTO legacy_users (card_id, last_name, first_name, username, birthday, studprog, membership, userlevel, password,
                          tab, cash, spent, borrowed, comment, misc, creation_date)
VALUES (306484731, 'Test', 'Test', 'test', '121212', 'Meth', 1, 0, '', 0, 35, 35, '', '', '', 1541103641);
INSERT INTO legacy_users (card_id, last_name, first_name, username, birthday, studprog, membership, userlevel, password,
                          tab, cash, spent, borrowed, comment, misc, creation_date)
VALUES (1111111101, 'Hektor', 'Hektor', 'hektor', '040914', 'Fisk', 1, 0, '', 0, 0, 0, '', '', '', 1410176229);
