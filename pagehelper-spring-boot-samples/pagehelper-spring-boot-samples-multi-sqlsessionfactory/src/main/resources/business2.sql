drop table blog if exists;

CREATE TABLE blog
(
    id int not null primary key auto_increment,
    user_id INT,
    title     VARCHAR(255)
);

insert into blog(user_id, title) values (1, 'blog_1');
insert into blog(user_id, title) values (1, 'blog_2');
insert into blog(user_id, title) values (1, 'blog_3');