/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-04-24 21:41:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for records
-- ----------------------------
DROP TABLE IF EXISTS `records`;
CREATE TABLE `records` (
  `course_id` char(6) NOT NULL,
  `tab_index` char(1) NOT NULL,
  `column_index` int(255) NOT NULL,
  `student_identity` varchar(10) NOT NULL,
  `hitcount` int(255) NOT NULL DEFAULT '1',
  `score` float(255,0) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`tab_index`,`column_index`,`student_identity`),
  KEY `tab_index` (`tab_index`),
  KEY `records_ibfk_3` (`column_index`),
  KEY `records_ibfk_4` (`student_identity`),
  CONSTRAINT `records_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `records_ibfk_2` FOREIGN KEY (`tab_index`) REFERENCES `tabs` (`index`) ON UPDATE CASCADE,
  CONSTRAINT `records_ibfk_3` FOREIGN KEY (`column_index`) REFERENCES `columns` (`index`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `records_ibfk_4` FOREIGN KEY (`student_identity`) REFERENCES `users` (`identity`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of records
-- ----------------------------
INSERT INTO `records` VALUES ('GN3009', 'A', '1', '1640706235', '0', null);
