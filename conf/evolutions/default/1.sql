# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table access_codes (
  id                        bigserial not null,
  used                      boolean,
  role                      varchar(255),
  constraint pk_access_codes primary key (id))
;

create table email_activation (
  id                        bigserial not null,
  email                     varchar(255),
  token                     varchar(255),
  used                      boolean,
  constraint pk_email_activation primary key (id))
;

create table password_resets (
  id                        bigserial not null,
  email                     varchar(255),
  token                     varchar(255),
  used                      boolean,
  constraint pk_password_resets primary key (id))
;

create table users (
  id                        bigserial not null,
  email                     varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  gender                    varchar(255),
  role                      varchar(255),
  birth_date                timestamp,
  password                  varchar(255),
  activated                 boolean,
  constraint pk_users primary key (id))
;




# --- !Downs

drop table if exists access_codes cascade;

drop table if exists email_activation cascade;

drop table if exists password_resets cascade;

drop table if exists users cascade;

