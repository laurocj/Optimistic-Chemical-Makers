SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;


CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL,
  `longitude` double(10,8) DEFAULT NULL,
  `latitude` double(10,8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `delivery_boy` (
  `id` int(11) NOT NULL,
  `longitude` double(10,8) NOT NULL,
  `latitude` double(10,8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `delivery_item` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `delivery_order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `delivery_order` (
  `id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `accepted_at` timestamp NULL DEFAULT NULL,
  `prepared_at` timestamp NULL DEFAULT NULL,
  `estimated_delivery_time` timestamp NULL DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `store_id` int(11) DEFAULT NULL,
  `latitude` varchar(45) DEFAULT NULL,
  `longitude` varchar(45) DEFAULT NULL,
  `delivery_route_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `delivery_route` (
  `id` bigint(20) NOT NULL,
  `closed_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `hash` varchar(255) NOT NULL,
  `started_at` datetime DEFAULT NULL,
  `delivery_boy` bigint(20) DEFAULT NULL,
  `store_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL,
  `description` varchar(254) DEFAULT NULL,
  `hash` varchar(65) DEFAULT NULL,
  `store_hash` varchar(65) DEFAULT NULL,
  `restaurant` varchar(254) DEFAULT NULL,
  `classification` varchar(254) DEFAULT NULL,
  `price` varchar(97) DEFAULT NULL,
  `city` varchar(95) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=1001 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `store` (
  `id` int(11) NOT NULL,
  `hash` varchar(65) DEFAULT NULL,
  `name` varchar(254) DEFAULT NULL,
  `city` varchar(254) DEFAULT NULL,
  `longitude` varchar(254) DEFAULT NULL,
  `latitude` varchar(454) DEFAULT NULL,
  `dish_description` varchar(254) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=1001 DEFAULT CHARSET=latin1;


ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `delivery_boy`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `delivery_item`
  ADD PRIMARY KEY (`id`), ADD KEY `FK_product_order` (`product_id`);

ALTER TABLE `delivery_order`
  ADD PRIMARY KEY (`id`), ADD KEY `FK_Customer_Order` (`customer_id`);

ALTER TABLE `delivery_route`
  ADD PRIMARY KEY (`id`), ADD KEY `FKk4wgonm7lb7q8how9svystgk1` (`delivery_boy`), ADD KEY `FKoo7r5yhj7cf4p8f6xppwb3ys9` (`store_id`);

ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `store`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `delivery_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `delivery_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `delivery_route`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1001;
ALTER TABLE `store`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1001;

ALTER TABLE `delivery_order`
ADD CONSTRAINT `FK_Customer_Order` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
