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
    `role` VARCHAR(30) NOT NULL          
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
  `pair` INTEGER NOT NULL,
  `amount`  DECIMAL(12,6) NOT NULL,
  `price`  DECIMAL(12,6) NOT NULL,
  `type` ENUM('Ask','Bid') NOT NULL,
  `state`  ENUM('canceled','active','executed')NOT NULL,

FOREIGN KEY (`user_id`)
REFERENCES `users` (`identity`)
ON UPDATE CASCADE
ON DELETE RESTRICT,

  FOREIGN KEY (`pair`)
REFERENCES `cryptocurrency_pairs` (`identity`)
ON UPDATE CASCADE
ON DELETE RESTRICT



) ENGINE=INNODB DEFAULT CHARACTER SET utf8;







