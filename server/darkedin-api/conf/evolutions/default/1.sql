
# --- !Ups
create table user (
    id                  int not null primary key,
    name                varchar(255) not null,
    git_name            varchar(255) not null,
    git_url             varchar(255) not null,
    avatar_url          varchar(255) not null,
    access_token        varchar(64)  null unique,
    token_expire_at     datetime(6)  null,
    created_at          datetime(6)  not null,
    updated_at          datetime(6)  not null
);

# --- !Downs
drop table "user";
