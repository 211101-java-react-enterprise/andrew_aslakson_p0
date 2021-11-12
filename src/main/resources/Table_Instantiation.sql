drop table user_accounts;

drop table users;
drop table accounts;
drop table transactions;

create table users (
	user_uuid varchar constraint user_uuid_pk primary key,
	first_name varchar,
	last_name varchar,
	email varchar(255) unique not null,
	username varchar unique not null,
	password varchar not null
);

create table accounts (
	account_uuid varchar constraint account_uuid_pk primary key,
	type varchar,
	account_name varchar,
	current_balance numeric(9, 2)
);

create table transactions (
	transaction_uuid varchar primary key,
	dateTime date,
	type_flag bool,
	amount numeric(9, 2),
	old_balance numeric(9, 2),
	new_balance numeric(9, 2)
);

create table user_accounts (
	account_uuid varchar,
	user_uuid varchar,

	primary key (account_uuid, user_uuid),

	constraint account_fk
	foreign key (account_uuid)
	references accounts (account_uuid),

	constraint user_fk
	foreign key (user_uuid)
	references users (user_uuid)
);

select * from users;

select * from transactions;

select * from accounts;

select * from user_accounts;






