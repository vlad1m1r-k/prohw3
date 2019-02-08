DROP TABLE IF EXISTS `addresses`;
CREATE TABLE `addresses` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) NOT NULL,
  `street_id` int(11) NOT NULL,
  `build` int(11) NOT NULL,
  PRIMARY KEY (`address_id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `addresses` VALUES (1,1,1,34),(2,1,2,23),(3,1,3,11),(4,1,4,5),(5,2,4,18);

DROP TABLE IF EXISTS `apartments`;
CREATE TABLE `apartments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  `apartmentNumber` int(11) NOT NULL,
  `space` int(11) NOT NULL,
  `rooms` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `apartments` VALUES (1,1,1,105,28,1,5000000),(2,2,2,98,32,1,700000),(3,3,3,205,42,2,1200000),(4,2,4,160,63,3,3000000),(5,3,5,302,72,4,1500000);

DROP TABLE IF EXISTS `areas`;
CREATE TABLE `areas` (
  `area_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`area_id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `areas` VALUES (1,'Шевченковский'),(2,'Днепровский'),(3,'Броварский');

DROP TABLE IF EXISTS `cities`;
CREATE TABLE `cities` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`city_id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `cities` VALUES (1,'Киев'),(2,'Бровары');

DROP TABLE IF EXISTS `streets`;
CREATE TABLE `streets` (
  `street_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`street_id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `streets` VALUES (1,'Хрещатик'),(2,'Камикадзе'),(3,'Степана Бандеры'),(4,'Броварская');
