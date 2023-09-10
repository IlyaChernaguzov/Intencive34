create table orders
(
    ID int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    item_name varchar(100) NOT NULL
);

create table users
(
    ID int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    surname varchar(100) NOT NULL,
    name varchar(100) NOT NULL,
    phone varchar(10) NOT NULL UNIQUE,
    email varchar(100) NOT NULL UNIQUE,
    orderID int,
    FOREIGN KEY (orderID) REFERENCES orders(ID) ON DELETE SET NULL
);

insert into orders (item_name) values ('Book');
insert into orders (item_name) values ('Phone');
insert into orders (item_name) values ('Toy');
insert into orders (item_name) values ('Music player');

insert into users (surname, name, phone, email, orderID) values ('Ivanov', 'Ivan', '9998797654', 'ivan@test.ru', 2);
insert into users (surname, name, phone, email, orderID) values ('Sergeev','Sergey', '9213456789', 'sergey@test.ru', 2);
insert into users (surname, name, phone, email, orderID) values ('Nikolaev', 'Nikolay', '9219876543', 'nikolay@test.ru', 1);
insert into users (surname, name, phone, email, orderID) values ('Pertrov', 'Petr', '9012346754', 'petr@test.ru', 4);
