create table COCKTAIL
(
    ID           bigint       not null auto_increment,
    ANNOUNCE     varchar(255) null,
    COOKING_TIME int          null,
    PREP_TIME    int          null,
    CREATED_DATE datetime     not null,
    TITLE        varchar(255) not null,
    USER_ID      bigint,
    PHOTO_ID     bigint,
    primary key (ID)
);

create table INGREDIENT
(
    ID          bigint       not null auto_increment,
    TITLE       varchar(255) not null,
    DESCRIPTION varchar(255) null,
    primary key (ID)
);

create table COCKTAIL_INGREDIENTS
(
    COCKTAIL_ID   bigint,
    INGREDIENT_ID bigint
);

create table PHOTO
(
    ID    bigint       not null auto_increment,
    PATH  varchar(255) not null,
    TITLE varchar(255) not null,
    ALT   varchar(255) null,
    primary key (ID)
);

create table USER
(
    ID   bigint       not null auto_increment,
    NAME varchar(255) not null,
    primary key (ID)
);

alter table COCKTAIL
    add constraint USER_ID_USER_ID foreign key (USER_ID) references USER (ID) ON DELETE CASCADE;
alter table COCKTAIL
    add constraint COCKTAIL_PHOTO_ID_PHOTO_ID foreign key (PHOTO_ID) references PHOTO (ID) ON DELETE CASCADE;

alter table COCKTAIL_INGREDIENTS
    add constraint COCKTAIL_INGREDIENTS_COCKTAIL_ID_COCKTAIL_ID foreign key (COCKTAIL_ID) references COCKTAIL (ID) ON DELETE CASCADE;

alter table COCKTAIL_INGREDIENTS
    add constraint COCKTAIL_INGREDIENTS_INGREDIENT_ID_INGREDIENT_ID foreign key (INGREDIENT_ID) references INGREDIENT (ID) ON DELETE CASCADE;