/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-05-06 05:09:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `identity` varchar(10) NOT NULL,
  `date_time` datetime NOT NULL,
  `course_id` char(6) NOT NULL,
  `tab_index` char(1) NOT NULL DEFAULT '',
  `column_index` int(255) NOT NULL DEFAULT '0',
  `opposite` varchar(10) DEFAULT NULL,
  `lunch_time` datetime DEFAULT NULL,
  `comment` varchar(255) NOT NULL,
  PRIMARY KEY (`identity`,`date_time`),
  KEY `column_index` (`column_index`),
  KEY `course_id` (`course_id`),
  KEY `tab_index` (`tab_index`),
  KEY `opposite` (`opposite`),
  KEY `date` (`date_time`),
  KEY `lunch_time` (`lunch_time`),
  CONSTRAINT `comments_ibfk_4` FOREIGN KEY (`identity`) REFERENCES `users` (`identity`) ON UPDATE CASCADE,
  CONSTRAINT `comments_ibfk_5` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `comments_ibfk_6` FOREIGN KEY (`opposite`) REFERENCES `users` (`identity`) ON UPDATE CASCADE,
  CONSTRAINT `comments_ibfk_7` FOREIGN KEY (`lunch_time`) REFERENCES `comments` (`date_time`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1640706235', '2020-05-05 17:06:33', 'NN1017', 'C', '1', null, null, '发起讨论');
