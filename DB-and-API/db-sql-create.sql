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

/* add user */

insert into user(user_loginname, user_email, user_password)
values ('andron13', 'andron13@gmail.com', '123456'),
       ('Juri', 'test1@test.dev', '123456'),
       ('Elena', 'test2@test.dev', '123456'),
       ('Dmitri', 'test3@test.dev', '123456');

/* add 1 photo */

insert into photo(photo_path, photo_title, photo_alt)
values ('https://ru.inshaker.com/uploads/cocktail/hires/698/1537730384-Parkside-Fizz__highres.jpg',
        'коктейль номер 1', 'так выглядит коктейль номер 1'),
       ('https://ru.inshaker.com/uploads/cocktail/hires/698/1537730384-Parkside-Fizz__highres.jpg',
        'коктейль номер 2', 'так выглядит коктейль номер 2'),
       ('https://ru.inshaker.com/uploads/cocktail/hires/698/1537730384-Parkside-Fizz__highres.jpg',
        'Ингредиент номер 1', 'так выглядит ингредиент номер 1');

/* add user_info */

insert into user_info
values (1, 'Андрей', 'Подлубный');

/* add ingredient */

insert into ingredient (ingredient_title, description, fk_ingredient_photo_id)
values ('Водка', 'Водка, один из самых популярных ингредиентов для коктейлей', 3),
       ('Томатный сок', 'Томатный сок входит в состав многих алкогольных и безалкогольных коктейлей', 3),
       ('Текила', 'Описание Текилы', 3),
       ('Перец', 'Описание перца', 3),
       ('Лёд', 'Описание видов льда', 3),
       ('Шампанское', 'Шампанское в коктейлях - риск вспомнить или забыть всё', 3),
       ('Апельсиновый сок', '', 3),
       ('Яблочный сок', '', 3),
       ('Персиковый сок', '', 3),
       ('Молоко', '', 3),
       ('Кофе', '', 3),
       ('Чай', '', 3),
       ('Красное вино', '', 3),
       ('Ликёр', '', 3),
       ('Коньяк', '', 3),
       ('Соль', '', 3),
       ('Сахар', '', 3),
       ('Лимон', '', 3),
       ('Лимете', '', 3),
       ('Грейпфрут', '', 3),
       ('Мороженное', '', 3);

/* add cocktails */

insert into cocktail (cocktail_title,
                      cocktail_announce,
                      cocktail_cooking_time,
                      cocktail_prep_time_minute,
                      cocktail_publication_date,
                      fk_user_id,
                      fk_photo_id)
values ('Кровавая мери',
        'Описание создания коктейля Кровавая мери',
        '', '', '2019-03-10', 1, 1),
       ('Sex on the beach',
        'Описание создания коктейля Sex on the beach',
        '', '', '2019-03-10', 2, 2),
       ('Белый русский',
        'Описание создания коктейля Белый русский',
        '', '', '2019-03-10', 3, 1),
       ('Виски с содовой',
        'Описание создания коктейля Виски с содовой',
        '', '', '2019-03-10', 4, 2),
       ('Ромовый горячий шоколад',
        'Описание создания коктейля Ромовый горячий шоколад',
        '', '', '2019-03-10', 1, 1);

/* add cocktail_ingredients */

insert into cocktail_ingredients(fk_ingredient_id, fk_cocktail_id)
values
  /* add cocktail кровавая мери */
  (1, 1),
  (2, 1),
  (4, 1),

  /* add cocktail Sex on the beach */
  (1, 2),
  (2, 2),
  (4, 2),

  /* add cocktail Белый русский */

  (1, 3),
  (2, 3),
  (4, 3);
