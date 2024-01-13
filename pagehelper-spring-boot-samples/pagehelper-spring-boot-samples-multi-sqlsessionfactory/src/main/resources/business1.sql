drop table t_user if exists;

create table t_user
(
    id int not null primary key auto_increment,
    name varchar(20)
);

insert into t_user (name) values ('User1');
insert into t_user (name) values ('User1 HSQL');
insert into t_user (name) values ('User1 DERBY');
insert into t_user (name) values ('User1 DEFAULT');