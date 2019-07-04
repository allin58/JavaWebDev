USE `market`;

CREATE TABLE IF NOT EXISTS `coins` (
    `coin` VARCHAR(5) PRIMARY KEY,
    `full_name` VARCHAR(20) NOT NULL
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;


CREATE TABLE IF NOT EXISTS `types` (
     `identity` INTEGER PRIMARY KEY,
    `type` VARCHAR(5) NOT NULL
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `state_of_order` (
     `identity` INTEGER PRIMARY KEY,
     `state` VARCHAR(20) NOT NULL
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `users` (
    `identity` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `user_name` VARCHAR(30) NOT NULL,
    `name` VARCHAR(30) NOT NULL,
    `surname` VARCHAR(30) NOT NULL,
    `hash_of_password` VARCHAR(50) NOT NULL,  
    `role` VARCHAR(30) NOT NULL          
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;


CREATE TABLE IF NOT EXISTS `cryptocurrency_pairs` (
    `identity` INTEGER PRIMARY KEY,
    `first_currency` VARCHAR(20) NOT NULL,
    `second_currency` VARCHAR(20) NOT NULL,
    FOREIGN KEY (`first_currency`)
    REFERENCES `coins` (`coin`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    
    FOREIGN KEY (`second_currency`)
    REFERENCES `coins` (`coin`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT       
    
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;


CREATE TABLE IF NOT EXISTS `wallets` (
    `user_id` INTEGER NOT NULL,
    `BTC` decimal NOT NULL,
    `ETH` decimal NOT NULL,
    `USDT` decimal NOT NULL,
        
    
      FOREIGN KEY (`user_id`)
    REFERENCES `users` (`identity`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
   
  
       
    
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;





CREATE TABLE IF NOT EXISTS `orders` (
  `identity` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `user_id` INTEGER NOT NULL,
  `pair` INTEGER NOT NULL,
  `amount` INTEGER NOT NULL,
  `price` INTEGER NOT NULL,
  `type` INTEGER NOT NULL,
  `state` INTEGER NOT NULL,

FOREIGN KEY (`user_id`)
REFERENCES `users` (`identity`)
ON UPDATE CASCADE
ON DELETE RESTRICT,

  FOREIGN KEY (`pair`)
REFERENCES `cryptocurrency_pairs` (`identity`)
ON UPDATE CASCADE
ON DELETE RESTRICT,

  FOREIGN KEY (`type`)
REFERENCES `types` (`identity`)
ON UPDATE CASCADE
ON DELETE RESTRICT,

  FOREIGN KEY (`state`)
REFERENCES `state_of_order` (`identity`)
ON UPDATE CASCADE
ON DELETE RESTRICT


) ENGINE=INNODB DEFAULT CHARACTER SET utf8;







