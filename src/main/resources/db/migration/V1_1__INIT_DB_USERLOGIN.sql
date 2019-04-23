create table UserLogin_roles
(
    UserLogin_id bigint       not null,
    roles        varchar(255) not null

)
    engine = MyISAM;


create index FKjuwnguphj5t4rhh2swd2ag6uv
    on UserLogin_roles (UserLogin_id);
create table UserLogin
(
    id       bigint auto_increment not null primary key,
    email    varchar(30)           not null unique,
    password varchar(255)          not null,
    username varchar(30)           not null unique


)
    engine = MyISAM;
