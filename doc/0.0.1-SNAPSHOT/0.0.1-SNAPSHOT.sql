/*
Navicat MySQL Data Transfer

Source Server         : 192.168.195.161
Source Server Version : 50171
Source Host           : 192.168.195.161:3306
Source Database       : ztest

Target Server Type    : MYSQL
Target Server Version : 50171
File Encoding         : 65001

Date: 2017-12-11 17:42:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for muser
-- ----------------------------
DROP TABLE IF EXISTS `muser`;
CREATE TABLE `muser` (
  `id` varchar(36) NOT NULL,
  `name` varchar(36) DEFAULT NULL,
  `age` int(8) DEFAULT NULL,
  `address` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sequence
-- ----------------------------
DROP TABLE IF EXISTS `t_sequence`;
CREATE TABLE `t_sequence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `current_num` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sku
-- ----------------------------
DROP TABLE IF EXISTS `t_sku`;
CREATE TABLE `t_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ware_name` varchar(200) DEFAULT NULL,
  `ware_id` int(11) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `size` decimal(10,1) DEFAULT NULL,
  `colour` varchar(50) DEFAULT NULL,
  `img` varchar(1000) DEFAULT NULL COMMENT '数据库存储json',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UN_WAREID_SIZE_COLOUR` (`ware_id`,`size`,`colour`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1751 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_supplier
-- ----------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL,
  `status` int(4) NOT NULL DEFAULT '1',
  `modified` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_trade
-- ----------------------------
DROP TABLE IF EXISTS `t_trade`;
CREATE TABLE `t_trade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vender_id` bigint(20) DEFAULT NULL,
  `sku_id` bigint(20) DEFAULT NULL,
  `ware_id` bigint(20) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `num` int(8) DEFAULT NULL,
  `market_day` date DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `status` int(4) DEFAULT '1',
  `modified` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_MARKET_DAY` (`market_day`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_vender
-- ----------------------------
DROP TABLE IF EXISTS `t_vender`;
CREATE TABLE `t_vender` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `qq` varchar(100) DEFAULT NULL,
  `wx` varchar(100) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `status` int(4) NOT NULL DEFAULT '1',
  `modified` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ware
-- ----------------------------
DROP TABLE IF EXISTS `t_ware`;
CREATE TABLE `t_ware` (
  `id` int(11) NOT NULL DEFAULT '100000',
  `name` varchar(100) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL COMMENT '厂商编号',
  `sizes` varchar(500) DEFAULT NULL,
  `colours` varchar(5000) DEFAULT NULL,
  `status` int(4) NOT NULL DEFAULT '1',
  `modified` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `ztest`.`t_sequence` (`id`, `name`, `current_num`) VALUES ('1', 'WARE_ID', '10000');
INSERT INTO `ztest`.`t_sequence` (`id`, `name`, `current_num`) VALUES ('2', 'VENDER_ID', '1000');
INSERT INTO `ztest`.`t_sequence` (`id`, `name`, `current_num`) VALUES ('3', 'SUPPLIER_ID', '100');
