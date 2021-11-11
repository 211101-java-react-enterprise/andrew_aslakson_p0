drop table account_transactions;
drop table user_accounts;

drop table app_users;
drop table accounts;
drop table transactions;

create table app_users (
	id varchar primary key,
	first_name varchar,
	last_name varchar,
	email varchar(255) unique not null,
	username varchar unique not null,
	password varchar
);

create table transactions (
	id varchar primary key,
	dateTime date,
	type_flag bool,
	amount numeric(9, 2),
	old_balance numeric(9, 2),
	new_balance numeric(9, 2)	
);

create table accounts ( 
	id varchar primary key,
	uuid varchar,
	account_name varchar,
	current_balance numeric(9, 2)
);

-- Actually a transaction only belongs to a single account so I need to rethink this logic
create table account_transactions (
	account_id varchar,
	transaction_id varchar,
	
	primary key (account_id, transaction_id),
	
	constraint account_fk 
	foreign key (account_id) 
	references accounts (id),
	
	constraint transaction_fk
	foreign key (transaction_id)
	references transactions (id)
); 

create table user_accounts (
	account_id varchar,
	user_id varchar,
	
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






