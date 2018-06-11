

create table user
(
	id integer not null,
	ime varchar(255) not null,
	mobilni varchar(255),
	poslovni varchar(255),
	privatni varchar(255),
	fiksni varchar(255),
	primary key(id)
);



-- mysql> create database db_example; -- Create the new database
-- mysql> create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
-- mysql> grant all on db_example.* to 'springuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database