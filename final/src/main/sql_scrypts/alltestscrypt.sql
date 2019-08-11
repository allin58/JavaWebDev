 DROP DATABASE IF EXISTS `testmarket`;

CREATE DATABASE `testmarket` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `testmarket`.*
TO testmarket@localhost
IDENTIFIED BY 'testmarket';

USE `testmarket`;

CREATE TABLE IF NOT EXISTS `coins` (
    `identity` INTEGER PRIMARY KEY,
    `coin` VARCHAR(5),
    `full_name` VARCHAR(20) NOT NULL
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;




CREATE TABLE IF NOT EXISTS `users` (
    `identity` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `user_name` VARCHAR(30) NOT NULL unique,
    `name` VARCHAR(30) NOT NULL,
    `surname` VARCHAR(30) NOT NULL,
    `hash_of_password` VARCHAR(64) NOT NULL,
    `role` ENUM('user','sec','admin') NOT NULL
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;


CREATE TABLE IF NOT EXISTS `cryptocurrency_pairs` (
    `identity` INTEGER PRIMARY KEY,
    `first_currency` INTEGER NOT NULL,
    `second_currency` INTEGER NOT NULL,
    `active` BOOL NOT NULL,
    FOREIGN KEY (`first_currency`)
    REFERENCES `coins` (`identity`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,

    FOREIGN KEY (`second_currency`)
    REFERENCES `coins` (`identity`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT

) ENGINE=INNODB DEFAULT CHARACTER SET utf8;








CREATE TABLE IF NOT EXISTS `wallets` (
    `user_id` INTEGER NOT NULL,
    `BTC` DECIMAL(12,6) NOT NULL,
    `ETH`  DECIMAL(12,6) NOT NULL,
    `USDT`  DECIMAL(12,6) NOT NULL,


      FOREIGN KEY (`user_id`)
    REFERENCES `users` (`identity`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT




) ENGINE=INNODB DEFAULT CHARACTER SET utf8;






CREATE TABLE IF NOT EXISTS `orders` (
  `identity` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `user_id` INTEGER NOT NULL,
  `pair`  ENUM('BTC-USDT', 'ETH-USDT', 'BTC-ETH') NOT NULL,
  `amount`  decimal(12, 6) UNSIGNED NOT NULL,
  `price`  decimal(12, 6) UNSIGNED NOT NULL,
  `type`  ENUM('Ask', 'Bid') NOT NULL,
  `state`  ENUM('active', 'executed','canceled') NOT NULL,

FOREIGN KEY (`user_id`)
REFERENCES `users` (`identity`)
ON UPDATE CASCADE
ON DELETE RESTRICT



) ENGINE=INNODB DEFAULT CHARACTER SET utf8;


CREATE TABLE IF NOT EXISTS `transactions` (
  `identity` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `user_id` INTEGER NOT NULL,
  `coin_id` INTEGER NOT NULL,
  `amount`  decimal(12, 6) UNSIGNED NOT NULL,
  `type`  ENUM('deposit', 'withdraw') NOT NULL,
  `date` TIMESTAMP,
  `status`  ENUM('pending', 'approved','rejected') NOT NULL,

FOREIGN KEY (`user_id`)
REFERENCES `users` (`identity`)
ON UPDATE CASCADE
ON DELETE RESTRICT,

    FOREIGN KEY (`coin_id`)
REFERENCES `coins` (`identity`)
ON UPDATE CASCADE
ON DELETE RESTRICT



) ENGINE=INNODB DEFAULT CHARACTER SET utf8;




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
(3,  1,1,10000),
(4,  1,1,10000),
(5,  10,7,10),
(6,  10,10,10),
(7,  10,10,10);



INSERT INTO `cryptocurrency_pairs` (`identity`, `first_currency`, `second_currency`, `active`)
VALUES
(1, 1,3,TRUE),
(2, 2,3,TRUE),
(3, 1,2,TRUE);



