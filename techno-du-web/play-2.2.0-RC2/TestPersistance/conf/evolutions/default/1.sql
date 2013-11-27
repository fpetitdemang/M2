# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bar (
  id                        integer not null,
  label                     varchar(255),
  adresse                   varchar(255),
  lat                       float,
  longi                     float,
  constraint pk_bar primary key (id))
;

create table drink (
  id                        integer not null,
  label                     varchar(255),
  constraint pk_drink primary key (id))
;

create table User (
  id                        integer not null,
  email                     varchar(255),
  name                      varchar(255),
  first_name                varchar(255),
  pseudo                    varchar(255),
  sexe                      varchar(255),
  date_naissance            timestamp,
  age                       integer,
  is_admin                  boolean,
  password                  varchar(255),
  constraint pk_User primary key (id))
;


create table bar_User (
  bar_id                         integer not null,
  User_id                        integer not null,
  constraint pk_bar_User primary key (bar_id, User_id))
;

create table User_bar (
  User_id                        integer not null,
  bar_id                         integer not null,
  constraint pk_User_bar primary key (User_id, bar_id))
;
create sequence bar_seq;

create sequence drink_seq;

create sequence User_seq;




alter table bar_User add constraint fk_bar_User_bar_01 foreign key (bar_id) references bar (id) on delete restrict on update restrict;

alter table bar_User add constraint fk_bar_User_User_02 foreign key (User_id) references User (id) on delete restrict on update restrict;

alter table User_bar add constraint fk_User_bar_User_01 foreign key (User_id) references User (id) on delete restrict on update restrict;

alter table User_bar add constraint fk_User_bar_bar_02 foreign key (bar_id) references bar (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists bar;

drop table if exists bar_User;

drop table if exists drink;

drop table if exists User;

drop table if exists User_bar;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists bar_seq;

drop sequence if exists drink_seq;

drop sequence if exists User_seq;

