/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-04-24 21:41:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for reviews
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews` (
  `course_id` char(6) NOT NULL,
  `identity` varchar(10) NOT NULL,
  `date` datetime NOT NULL,
  `review` varchar(255) NOT NULL,
  PRIMARY KEY (`identity`,`date`,`course_id`),
  KEY `course_code` (`course_id`),
  CONSTRAINT `reviews_ibfk_3` FOREIGN KEY (`identity`) REFERENCES `users` (`identity`) ON UPDATE CASCADE,
  CONSTRAINT `reviews_ibfk_4` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reviews
-- ----------------------------
