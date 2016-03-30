# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table access_codes (
  id                        bigserial not null,
  used                      boolean,
  role                      varchar(255),
  constraint pk_access_codes primary key (id))
;

create table cart (
  id                        bigserial not null,
  user_id                   bigint,
  purchase_date             timestamp,
  total                     float,
  purchased                 boolean,
  constraint pk_cart primary key (id))
;

create table cart_item (
  id                        bigserial not null,
  cart_id                   bigint,
  product_id                bigint,
  quantity                  integer,
  constraint pk_cart_item primary key (id))
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

create table products (
  id                        bigserial not null,
  product_name              varchar(255),
  product_description       varchar(255),
  product_quantity          integer,
  product_manufacturer      varchar(255),
  product_price             float,
  product_width             integer,
  product_height            integer,
  product_length            integer,
  product_weight            integer,
  product_color             varchar(255),
  date_posted               varchar(255),
  constraint pk_products primary key (id))
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

alter table cart_item add constraint fk_cart_item_cart_1 foreign key (cart_id) references cart (id);
create index ix_cart_item_cart_1 on cart_item (cart_id);
alter table cart_item add constraint fk_cart_item_product_2 foreign key (product_id) references products (id);
create index ix_cart_item_product_2 on cart_item (product_id);



# --- !Downs

drop table if exists access_codes cascade;

drop table if exists cart cascade;

drop table if exists cart_item cascade;

drop table if exists email_activation cascade;

drop table if exists password_resets cascade;

drop table if exists products cascade;

drop table if exists users cascade;

