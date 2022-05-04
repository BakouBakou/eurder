create table CUSTOMER
(
    ID        varchar(255) NOT NULL UNIQUE,
    FIRSTNAME varchar(255) NOT NULL,
    LASTNAME  varchar(255) NOT NULL,
    EMAIL     varchar(255) NOT NULL UNIQUE,
    ADDRESS   varchar(255) NOT NULL,
    PHONE     varchar(255),
    CONSTRAINT PK_CUSTOMER PRIMARY KEY (ID)
);

create table ORDER_DETAIL
(
    ID             varchar(255) NOT NULL UNIQUE,
    FK_CUSTOMER_ID varchar(255) NOT NULL,
    TOTAL_PRICE    NUMERIC      NOT NULL,
    CONSTRAINT FK_CUSTOMER_ID foreign key (FK_CUSTOMER_ID) references CUSTOMER (ID),
    CONSTRAINT PK_ORDER_DETAIL PRIMARY KEY (ID)
);

create table ITEM
(
    ID          varchar(255) NOT NULL UNIQUE,
    NAME        varchar(255) NOT NULL,
    DESCRIPTION varchar(255) NOT NULL,
    PRICE       NUMERIC      NOT NULL,
    STOCK       INTEGER      NOT NULL,
    CONSTRAINT PK_ITEM PRIMARY KEY (ID)
);

create table ITEM_GROUP
(
    ID          varchar(255) NOT NULL UNIQUE,
    FK_ORDER_DETAIL_ID varchar(255) NOT NULL,
    PRICE       NUMERIC      NOT NULL,
    AMOUNT      INTEGER      NOT NULL,
    CONSTRAINT FK_ORDER_DETAIL_ID foreign key (FK_ORDER_DETAIL_ID) references ORDER_DETAIL (ID),
    CONSTRAINT FK_ITEM_ID foreign key (ID) references ITEM (ID),
    CONSTRAINT PK_ITEM_GROUP PRIMARY KEY (ID)
);


