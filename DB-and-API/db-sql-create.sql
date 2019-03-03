create table user
(
  user_id bigint auto_increment    primary key,
  user_loginname  varchar,
  user_email      varchar,
  user_password   varchar
)

create table photo
(
  photo_id bigint auto_increment    primary key,
  photo_patch    varchar(255) not null,
  photo_title            varchar(255)  not null,
  photo_alt            varchar(255)
)

create table user_info
(
user_info_id bigint primary key,
firstname  varchar,
lastname      varchar,
foreign key (user_info_id) references user(user_id)
)

create table ingredient
(
  ingredient_id bigint auto_increment    primary key,
  description   LONGTEXT not null,
  ingredient_title varchar(255) not null,
  fk_ingredient_photo_id bigint,
  foreign key (fk_ingredient_photo_id) references photo(photo_id)
)

create table cocktail
(
  cocktail_id     bigint auto_increment    primary key,
  cocktail_announce         LONGTEXT      not null,
  cocktail_cooking_time     int           ,
  cocktail_prep_time_minute int           ,
  cocktail_publication_date datetime      not null,
  cocktail_title            varchar(255)  not null,
  fk_user_id          bigint        ,
  fk_photo_id         bigint        ,
  foreign key (fk_user_id) references user(user_id),
  foreign key (fk_photo_id) references photo(photo_id)
)

create table cocktail_ingredients
(
  cocktail_ingredients_id bigint auto_increment    primary key,
  fk_ingredient_id bigint not null,
  fk_cocktail_id bigint not null,
  foreign key (fk_ingredient_id) references ingredient(ingredient_id),
  foreign key (fk_cocktail_id) references cocktail(cocktail_id)
)
