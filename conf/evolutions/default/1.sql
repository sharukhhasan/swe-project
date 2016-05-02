# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table motel_guests (
  id                        bigserial not null,
  guest_first_name          varchar(255),
  guest_last_name           varchar(255),
  guest_gender              varchar(255),
  guest_platenum            varchar(255),
  guest_birthdate           timestamp,
  guest_checkin             timestamp,
  guest_checkout            timestamp,
  guest_paid                float,
  room_number               bigint,
  num_nights                integer,
  constraint pk_motel_guests primary key (id))
;

create table password_resets (
  id                        bigserial not null,
  email                     varchar(255),
  token                     varchar(255),
  used                      boolean,
  constraint pk_password_resets primary key (id))
;

create table rooms (
  id                        bigserial not null,
  is_occupied               boolean,
  constraint pk_rooms primary key (id))
;

create table model_supplies (
  id                        bigserial not null,
  supply_name               varchar(255),
  supply_stock              integer,
  supply_restock            boolean,
  supply_price              float,
  constraint pk_model_supplies primary key (id))
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
  constraint pk_users primary key (id))
;




# --- !Downs

drop table if exists motel_guests cascade;

drop table if exists password_resets cascade;

drop table if exists rooms cascade;

drop table if exists model_supplies cascade;

drop table if exists users cascade;

