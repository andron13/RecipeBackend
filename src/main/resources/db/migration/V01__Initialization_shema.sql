create table Cocktail
(
  id              bigint auto_increment

    primary key,
  announce        varchar(255) null,
  cookingTime     int          null,
  prepTimeMinute  int          null,
  publicationDate date         null,
  title           varchar(255) null,
  author_id       bigint       null
)
  engine = MyISAM;

create index FK4qblqyomyx9gpa1tbpr6q2gvw
  on cocktail (author_id);

create table cocktail_image
(
  Cocktail_id bigint not null,
  image_id    bigint not null
)
  engine = MyISAM;

create index FKl8hbq73mvonmmh3pbe73vqufy
  on cocktail_image (Cocktail_id);

create index FKpvfletane5mhidv5sa24ecicf
  on cocktail_image (image_id);

create table cocktail_ingredients
(
  Cocktail_id    bigint not null,
  ingredients_id bigint not null
)
  engine = MyISAM;

create index FKkjkp19c3bd2w0ogpq6jkxvmh9
  on cocktail_ingredients (Cocktail_id);

create index FKtm04d2ya3m712fe7tgsy15rv2
  on cocktail_ingredients (ingredients_id);

create table ingredient
(
  id          bigint auto_increment

    primary key,
  description varchar(255) null,
  TITLE       varchar(255) null
)
  engine = MyISAM;

create table photo
(
  id    bigint auto_increment

    primary key,
  patch varchar(255) null
)
  engine = MyISAM;

create table user
(
  id   bigint auto_increment

    primary key,
  name varchar(255) null
)
  engine = MyISAM;