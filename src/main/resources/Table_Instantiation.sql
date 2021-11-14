/*
 * Table Instantiation SQL Can be run to create model database
 * 
 * 
 * 		-users (table)
 * 			+ user_uuid (String) (primary key)
 * 			+ first_name (String)
 * 			+ last_name (String)
 * 			+ email (String) (Unique/Not Null)
 * 			+ username (String) (Unique/Not Null)
 * 			+ password (String) (Not Null)
 * 
 * 		- accounts (table)
 * 			+ account_uuid (String) (Primary Key)
 *			+ type (String) (Not Null) (Either 'C' or 'S' determines whether is checking or savings account)
 *			+ account_name (String) 
 *			+ current_balance (Double)
 *
 *		- transactions (table)
 *			+ transaction_uuid (String) (Primary Key)
 *			+ account_uuid (String) (Foreign Key)
 *			+ date_time (date) (used to order transactions when recalling from database)
 *			+ type_flag (Boolean)
 *			+ amount (Double)
 *			+ old_balance (Double)
 *			+ new_balance (Double)
 *
 *     	- user_accounts (table)
 *			+ account_uuid (String) (Primary/Foreign Key)
 *			+ user_uuid (String) (Primary/Foreign Key)
 *
 */


-- drop tables
drop table user_accounts;

drop table transactions;

drop table users;
drop table accounts;

-- instantiate tables
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
	account_uuid varchar,
	date_time timestamp,
	type_flag bool,
	amount numeric(9, 2),
	old_balance numeric(9, 2),
	new_balance numeric(9, 2),
	
	constraint account_fk
	foreign key (account_uuid)
	references accounts (account_uuid)
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

-- create first user
insert into users (user_uuid, first_name, last_name, email, username, password) 
values ('68eba394-6d73-4d23-b30f-ba9dd3216031', 'Andrew', 'Aslakson', 'real@email.mail', 'aaslakson', 'p4ssword');


-- create account for user aaslakson and link together using user_accounts table
insert into accounts (account_uuid, type, account_name, current_balance)
values ('aacbb7c2-2341-4059-8012-2119106edc26', 'C', 'My Checking', 130.00);

insert into user_accounts (account_uuid, user_uuid)
values ('aacbb7c2-2341-4059-8012-2119106edc26', '68eba394-6d73-4d23-b30f-ba9dd3216031');

-- create account for user aaslakson and link together using user_accounts table
insert into accounts (account_uuid, type, account_name, current_balance)
values ('4fab97d1-eb13-480e-bb1a-3370f6681a74', 'S', 'My Savings', 0.0);

insert into user_accounts (account_uuid, user_uuid)
values ('4fab97d1-eb13-480e-bb1a-3370f6681a74', '68eba394-6d73-4d23-b30f-ba9dd3216031');


-- create account for user aaslakson and link together using user_accounts table
insert into accounts (account_uuid, type, account_name, current_balance)
values ('77bfa35c-3423-4816-9b66-c250a5d41ac2', 'C', 'Our Joint Account', 69.00);

insert into user_accounts (account_uuid, user_uuid)
values ('77bfa35c-3423-4816-9b66-c250a5d41ac2', '68eba394-6d73-4d23-b30f-ba9dd3216031');


-- creat new user fakename and add reference to the joint account for aaslakson
insert into users (user_uuid, first_name, last_name, email, username, password)
values ('7a045195-f2e2-4174-aa48-c657aaf1b236', 'Fake', 'Namington', 'email@email.mail', 'fakename', 'p4ssword');

insert into user_accounts (account_uuid, user_uuid)
values ('77bfa35c-3423-4816-9b66-c250a5d41ac2', '7a045195-f2e2-4174-aa48-c657aaf1b236');



-- create account for user fakename and add reference in user_accounts
insert into accounts (account_uuid, type, account_name, current_balance)
values ('862074b5-ad9d-4a9a-bd95-86f2599a8544', 'C', 'Personal', 965.00);

insert into user_accounts (account_uuid, user_uuid)
values ('862074b5-ad9d-4a9a-bd95-86f2599a8544', '7a045195-f2e2-4174-aa48-c657aaf1b236');




-- Example query to get all accounts associated with user_uuid
/*
select a.account_uuid, a.type, a.account_name, a.current_balance
from user_accounts ua
join accounts a
on a.account_uuid = ua.account_uuid
where user_uuid = ?;
*/



-- My checking account transactions
insert into transactions (transaction_uuid, account_uuid, date_time, type_flag, amount, old_balance, new_balance)
values ('b52fea61-79b2-4cdc-8f4d-3ed8bdb26ef9', 'aacbb7c2-2341-4059-8012-2119106edc26', CURRENT_TIMESTAMP,  true, 100.00, 0.00, 100.00);

insert into transactions (transaction_uuid, account_uuid, date_time, type_flag, amount, old_balance, new_balance)
values ('4d1a413a-d35a-4820-ada9-b9b9bce1055a', 'aacbb7c2-2341-4059-8012-2119106edc26', CURRENT_TIMESTAMP,  true, 50.00, 100.00, 150.00);

insert into transactions (transaction_uuid, account_uuid, date_time, type_flag, amount, old_balance, new_balance)
values ('9b613d74-f98b-48a4-9161-71bc320a5a3d', 'aacbb7c2-2341-4059-8012-2119106edc26', CURRENT_TIMESTAMP,  false, 20.00, 150.00, 130.00);



-- our joint account transactions
insert into transactions (transaction_uuid, account_uuid, date_time, type_flag, amount, old_balance, new_balance)
values ('8bda9a3c-2f81-43f6-8bb8-1d2fa8204879', '77bfa35c-3423-4816-9b66-c250a5d41ac2', CURRENT_TIMESTAMP,  true, 89.00, 0.00, 89.00);

insert into transactions (transaction_uuid, account_uuid, date_time, type_flag, amount, old_balance, new_balance)
values ('a4b5804b-91ae-4c97-8885-ef3b91f3be13', '77bfa35c-3423-4816-9b66-c250a5d41ac2', CURRENT_TIMESTAMP,  false, 20.00, 89.00, 69.00);



-- personal checking account for fakename transactions
insert into transactions (transaction_uuid, account_uuid, date_time, type_flag, amount, old_balance, new_balance)
values ('3e4d0a3a-c597-4f9a-a20b-a90378118f80', '862074b5-ad9d-4a9a-bd95-86f2599a8544', CURRENT_TIMESTAMP,  true, 1000.00, 0.00, 1000.00);

insert into transactions (transaction_uuid, account_uuid, date_time, type_flag, amount, old_balance, new_balance)
values ('0c26eaf3-f016-4838-8784-52fed8ba5e03', '862074b5-ad9d-4a9a-bd95-86f2599a8544', CURRENT_TIMESTAMP,  false, 35.00, 1000.00, 965.00);



-- Example query for getting all transactions associated with account
/*
select t.date_time, t.type_flag, t.amount, t.old_balance, t.new_balance
from transactions t
where account_uuid = ?
order by date_time;
*/

-- Example query for updating current_balance of accounts table
/*
update accounts
set current_balance = ?
where account_uuid = ?;
*/



select * from users;

select * from transactions;

select * from accounts;

select * from user_accounts;