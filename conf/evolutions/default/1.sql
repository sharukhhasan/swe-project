# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table users (
  id                        bigserial not null,
  email                     varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  gender                    varchar(255),
  password                  varchar(255),
  role                      varchar(255),
  constraint pk_users primary key (id))
;




# --- !Downs

drop table if exists users cascade;
