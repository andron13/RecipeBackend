create table if not exists user
(
  user_id        bigint auto_increment primary key,
  user_loginname varchar(255),
  user_email     varchar(255),
  user_password  varchar(255)
);

create table if not exists photo
(
  photo_id    bigint auto_increment primary key,
  photo_path  varchar(255) not null,
  photo_title varchar(255) not null,
  photo_alt   varchar(255)
);

create table if not exists user_info
(
  user_info_id bigint primary key,
  firstname    varchar(255),
  lastname     varchar(255),
  foreign key (user_info_id) references user (user_id)
);

create table if not exists ingredient
(
  ingredient_id          bigint auto_increment primary key,
  ingredient_title       varchar(255) not null,
  ingredient_description            LONGTEXT,
  fk_ingredient_photo_id bigint,
  foreign key (fk_ingredient_photo_id) references photo (photo_id)
);

create table if not exists cocktail
(
  cocktail_id               bigint auto_increment primary key,
  cocktail_title            varchar(255) not null,
  cocktail_announce         LONGTEXT     not null,
  cocktail_cooking_time     int,
  cocktail_prep_time_minute int,
  cocktail_publication_date datetime     not null,
  fk_user_id                bigint,
  fk_photo_id               bigint,
  foreign key (fk_user_id) references user (user_id),
  foreign key (fk_photo_id) references photo (photo_id)
);

create table if not exists cocktail_ingredients
(
  cocktail_ingredients_id bigint auto_increment primary key,
  fk_ingredient_id        bigint not null,
  fk_cocktail_id          bigint not null,
  foreign key (fk_ingredient_id) references ingredient (ingredient_id),
  foreign key (fk_cocktail_id) references cocktail (cocktail_id)
);
