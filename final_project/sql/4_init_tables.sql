INSERT INTO `coins` (`coin`, `full_name`)
VALUES
("BTC", "Bitcoin"),
("ETH", "Ethereum"),
("USDT", "Tether");


INSERT INTO `types` (`identity`, `type`)
VALUES
(1, "ask"),
(2, "bid");

INSERT INTO `state_of_order` (`identity`, `state`)
VALUES
(1, "active"),
(2, "executed"),
(3, "partially_executed"),
(4, "canceled");


INSERT INTO `users` (`user_name`, `name`, `surname`, `hash_of_password`, `role`)
VALUES
("Alice", "Alice","Williams","password","user"),
("Bob", "Bob","Johnson","password","user"),
("Eve", "Eve","Jones","password","user"),
("Peggy", "Peggy","Brown","password","user"),
("Craig", "Craig","Miller","password","user"),
("Mallory", "Mallory","Anderson","password","user"),
("Walter", "Walter","Jackson","password","user");




INSERT INTO `wallets` (`user_id`, `BTC`, `ETH`,`USDT`)
VALUES
(1, 10,10,10),
(2,  20,15,18),
(3,  10,10,10),
(4,  5,10,10),
(5,  10,7,10),
(6,  10,10,10),
(7,  10,10,10);



INSERT INTO `cryptocurrency_pairs` (`identity`, `first_currency`, `second_currency`)
VALUES
(1, "BTC","USDT"),
(2, "ETH","USDT"),
(3, "BTC","ETH");


INSERT INTO `orders` (`user_id`, `pair`, `amount`,`price`,`type`,`state`)
VALUES
(1, 1,100,15000,1,2),
(1, 1,50,10000,1,2),
(1, 1,1,1,1,2);



 