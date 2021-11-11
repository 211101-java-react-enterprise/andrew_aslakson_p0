drop table account_transactions;
drop table user_accounts;

drop table app_users;
drop table accounts;
drop table transactions;

create table app_users (
	id serial primary key,
	uuid varchar unique not null,
	first_name varchar,
	last_name varchar,
	email varchar(255) unique not null,
	username varchar unique not null,
	password varchar
);

create table transactions (
	id serial primary key,
	dateTime date,
	type_flag bool,
	amount numeric(9, 2),
	old_balance numeric(9, 2),
	new_balance numeric(9, 2)	
);

create table accounts ( 
	id serial primary key,
	uuid varchar,
	account_name varchar,
	current_balance numeric(9, 2)
);

create table account_transactions (
	account_id int,
	transaction_id int,
	
	primary key (account_id, transaction_id),
	
	constraint account_fk 
	foreign key (account_id) 
	references accounts (id),
	
	constraint transaction_fk
	foreign key (transaction_id)
	references transactions (id)
); 

create table user_accounts (
	account_id int,
	user_id int,
	
	primary key (account_id, user_id),
	
	constraint account_fk
	foreign key (account_id)
	references accounts (id),
	
	constraint user_fk
	foreign key (user_id)
	references app_users (id)
);

select * from app_users;

select * from transactions;

select * from accounts;

select * from account_transactions;

select * from user_accounts;






