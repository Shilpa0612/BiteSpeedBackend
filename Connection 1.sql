use userdetails;
show tables;
create table contact(
id int primary key,
email text,
phone text,
linkedId int default null,
linkedPrecedence varchar(20) default 'primary',
createdAt datetime,
deletedAt datetime default null,
updatedAt dateTime
);

create table link(
id int primary key,
email text,
phone text,
linkedId int,
createAt datetime,
updatedAt datetime,
deletedAt datetime default null,
foreign key(linkedId) references contact(id)
);

desc link;
desc contact;
Select * from contact;
select * from link;