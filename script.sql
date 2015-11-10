drop database if exists quicktipsenglish;
create database quicktipsenglish;
use quicktipsenglish;

create table user(
  user_id integer not null auto_increment primary key,
  user_email varchar(150) not null,
  user_nick_name varchar(25) not null,
  user_password varchar(25) not null
);

create table tips(
  tips_id integer not null auto_increment primary key,
  tips_name varchar(200) not null
);

create table tips_item(
  tips_item_id integer not null auto_increment primary key,
  tips_item_description_br varchar(1000) not null,
  tips_item_description_us varchar(1000) not null,
  tips_item_tips_id integer not null,
  foreign key (tips_item_tips_id) references tips(tips_id)
);

insert into tips (tips_id, tips_name) values (1, "Cantadas");
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("Do you have a girlfriend?", "Você tem namorada?", 1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("Do you have a boyfriend?", "Você tem namorado?", 1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("I am in love with you?", "Eu estou apaixonado por você?", 1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("Is it hot in here, or is that just you?", "Está quente aqui, ou é apenas você?", 1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("Do you want to be my girlfriend?","Você quer ser minha namorada?",1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("Do you want to be my boyfriend?","Você quer ser meu namorado?", 1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("You look like my next girlfriend","Você parece com minha próxima namorada", 1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values
("You look like my next boyfriend","Você parece com meu próximo namorado", 1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("Did it hurt when you fell from heaven?","Machucou quando você caiu do céu?", 1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("May i buy you a drink?","Eu posso te pagar uma bebida?",1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("I wish i were cross-eyed so i could see you twice","Eu queria ser um vesgo para ver você em dobro",1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("You look like somebody famous","Você parece alguém famoso",1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("Do you believe in love at first sight or should I walk by again?","Você acredita em amor à primeira vista ou eu devo passar por aqui de novo?",1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("Are you religious? ‘Cause you’re the answer to all my prayers.","Você é religiosa? Porque você é a resposta de todas as minhas preces",1);
insert into tips_item (tips_item_description_br, tips_item_description_us, tips_item_tips_id) values 
("I didn’t know that angels could fly so low!","Eu não sabia que anjos podiam voar tão baixo",1);
