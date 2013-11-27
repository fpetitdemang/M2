# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bar (
  label                     varchar(255))
;

create table user (
  email                     varchar(255),
  password                  varchar(255),
  fullname                  varchar(255),
  is_admin                  boolean)
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists bar;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

