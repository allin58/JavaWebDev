USE `market`;

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
  `type`  ENUM('deposit', 'withdrow') NOT NULL,
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




