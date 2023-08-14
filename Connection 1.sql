/*create database user_details;
use user_details;
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
show databases;
desc link;
desc contact;
Select * from contact;
Select count(*) from contact;
select * from link;
Select count(*) from link;

delete from contact where id = 1752 or id = 1802 or id = 1852;
delete from link;
insert into contact values(1,'lorraine@hillvalley.edu','123456',null,'primary','2023-04-01 00:00:00',null,'2023-04-01 00:00:00');

alter table contact drop column linked_id;
alter table link drop column linkedPrecedence;
alter table link
drop column createdAt,
drop column deletedAt,
drop column updatedAt;
drop column linked;

alter table link
drop column createAt;

ALTER TABLE contact
MODIFY createdAt TIMESTAMP,
MODIFY updatedAt TIMESTAMP,
MODIFY deletedAt TIMESTAMP;

Alter table link add column createdAt timestamp;
ALTER TABLE link
MODIFY createdAt TIMESTAMP,
MODIFY updatedAt TIMESTAMP,
MODIFY deletedAt TIMESTAMP;

Alter table link
drop column created_at,
drop column deleted_at,
drop column updated_at;
drop column linked_precedence;
alter table contact drop column linkedId;

ALTER TABLE contact DROP COLUMN created_at;

ALTER TABLE contact Add COLUMN linkedPrecedence text, Add COLUMN createdAt text, Add COLUMN deletedAt timestamp, Add COLUMN updatedAt timestamp;

ALTER TABLE link DROP constraint link_ibfk_1;
ALTER TABLE link DROP constraint FK29a0grv0rsko5w8ug39k5tjge;

desc contact;
desc link;
ALTER TABLE link MODIFY linked_id INT NULL;


SELECT
    CONSTRAINT_NAME,
    COLUMN_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE REFERENCED_TABLE_NAME = 'link';

SELECT
    CONSTRAINT_NAME,
    COLUMN_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE REFERENCED_TABLE_NAME = 'link'
    AND COLUMN_NAME = 'linked_id';

SELECT CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'link'
  AND COLUMN_NAME = 'linkedId';
DELETE FROM LINK where
 id = 202;
DELETE FROM CONTACT;
select * from link;
select * from contact;*/
/*
CREATE TABLE hibernate_sequence (
    next_val bigint
);

INSERT INTO hibernate_sequence (next_val) VALUES (1);

drop table link;
drop table contact;
alter table contact drop constraints;
alter table link alter link_linked_id set default -1;*/

show tables;
desc contact;
desc link;
drop table link_seq;
