INSERT INTO `coins` (`identity`,`coin`, `full_name`)
VALUES
(1,"BTC", "Bitcoin"),
(2,"ETH", "Ethereum"),
(3,"USDT", "Tether");



INSERT INTO `users` (`user_name`, `name`, `surname`, `hash_of_password`, `role`)
VALUES
("Alice", "Alice","Williams","5e2c1a4ebe119ac6633d435e95ff37be9ead08aa827afca81d419648040ad2a0","admin"),
("Bob", "Bob","Johnson","5e2c1a4ebe119ac6633d435e95ff37be9ead08aa827afca81d419648040ad2a0","sec"),
("Eve", "Eve","Jones","5e2c1a4ebe119ac6633d435e95ff37be9ead08aa827afca81d419648040ad2a0","user"),
("Peggy", "Peggy","Brown","5e2c1a4ebe119ac6633d435e95ff37be9ead08aa827afca81d419648040ad2a0","user"),
("Craig", "Craig","Miller","5e2c1a4ebe119ac6633d435e95ff37be9ead08aa827afca81d419648040ad2a0","user"),
("Mallory", "Mallory","Anderson","5e2c1a4ebe119ac6633d435e95ff37be9ead08aa827afca81d419648040ad2a0","user"),
("Walter", "Walter","Jackson","5e2c1a4ebe119ac6633d435e95ff37be9ead08aa827afca81d419648040ad2a0","user");




INSERT INTO `wallets` (`user_id`, `BTC`, `ETH`,`USDT`)
VALUES
(1, 10,10,10),
(2,  20,15,18),
(3,  10,10,10),
(4,  5,10,10),
(5,  10,7,10),
(6,  10,10,10),
(7,  10,10,10);



INSERT INTO `cryptocurrency_pairs` (`identity`, `first_currency`, `second_currency`, `active`)
VALUES
(1, 1,3,TRUE),
(2, 2,3,TRUE),
(3, 1,2,TRUE);


INSERT INTO `orders` (`user_id`, `pair`, `amount`,`price`,`type`,`state`)
VALUES
(1, 1,100,15000,'Ask','active'),
(1, 1,50,10000,'Ask','active'),
(1, 1,1,1,'Bid','executed');



 