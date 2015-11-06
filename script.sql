create database quicktipsenglish;
use quicktipsenglish;

create table category(
  category_id integer not null primary key,
  category_name varchar(200) not null
);

create table tips(
  tips_id integer not null primary key,
  tips_description_br varchar(1000) not null,
  tips_description_us varchar(1000) not null,
  tips_category_id integer not null,
  foreign key (tips_category_id) references category(category_id)
);
