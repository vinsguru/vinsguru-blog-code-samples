create table account (
	id INT auto_increment,
	user_name VARCHAR(50),
	balance INT
);

create table money_deposit_event (
    id INT auto_increment,
    account_number INT,
    amount INT,
    foreign key (account_number) references account(id),
    check (amount > 99) -- business rule says there should be min $100 deposit
);

insert into account (user_name, balance) values
     ('Tara', 0),
     ('Daisy', 0),
     ('Fredericka', 0),
     ('Brita', 0);