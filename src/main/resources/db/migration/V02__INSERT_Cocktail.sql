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

insert into ingredient (ingredient_title, ingredient_description, fk_ingredient_photo_id)
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
                      cocktail_publication_date,
                      fk_user_id,
                      fk_photo_id)
values ('Кровавая мери',
        'Описание создания коктейля Кровавая мери', '2019-03-10', 1, 1),
       ('Sex on the beach',
        'Описание создания коктейля Sex on the beach', '2019-03-10', 2, 2),
       ('Белый русский',
        'Описание создания коктейля Белый русский', '2019-03-10', 3, 1),
       ('Виски с содовой',
        'Описание создания коктейля Виски с содовой', '2019-03-10', 4, 2),
       ('Ромовый горячий шоколад',
        'Описание создания коктейля Ромовый горячий шоколад', '2019-03-10', 1, 1);

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
