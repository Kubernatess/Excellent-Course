/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-04-24 21:41:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answers
-- ----------------------------
DROP TABLE IF EXISTS `answers`;
CREATE TABLE `answers` (
  `course_id` char(6) NOT NULL,
  `tab_index` char(1) NOT NULL,
  `column_index` int(255) NOT NULL,
  `group_name` varchar(255) NOT NULL,
  `answer` varchar(5) NOT NULL,
  PRIMARY KEY (`course_id`,`tab_index`,`column_index`,`group_name`),
  KEY `tab_index` (`tab_index`),
  KEY `column_index` (`column_index`),
  CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `answers_ibfk_2` FOREIGN KEY (`tab_index`) REFERENCES `tabs` (`index`) ON UPDATE CASCADE,
  CONSTRAINT `answers_ibfk_3` FOREIGN KEY (`column_index`) REFERENCES `columns` (`index`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answers
-- ----------------------------
