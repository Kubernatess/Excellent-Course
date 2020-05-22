/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-05-06 05:09:21
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
  `score` float(255,0) NOT NULL DEFAULT '-1',
  `finished` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`course_id`,`tab_index`,`column_index`,`student_identity`),
  KEY `tab_index` (`tab_index`),
  KEY `records_ibfk_3` (`column_index`),
  KEY `records_ibfk_4` (`student_identity`),
  CONSTRAINT `records_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `records_ibfk_4` FOREIGN KEY (`student_identity`) REFERENCES `users` (`identity`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of records
-- ----------------------------
INSERT INTO `records` VALUES ('NN1017', 'A', '1', '1640706235', '2', '-1', '1');
INSERT INTO `records` VALUES ('NN1017', 'A', '2', '1640706235', '1', '-1', '0');
INSERT INTO `records` VALUES ('NN1017', 'B', '1', '1640706235', '2', '-1', '1');
INSERT INTO `records` VALUES ('NN1017', 'C', '1', '1640706235', '4', '-1', '1');
INSERT INTO `records` VALUES ('NN1017', 'C', '2', '1640706235', '2', '-1', '1');
INSERT INTO `records` VALUES ('NN1017', 'D', '1', '1640706235', '3', '66', '1');
INSERT INTO `records` VALUES ('NN1017', 'D', '2', '1640706235', '1', '-1', '1');
INSERT INTO `records` VALUES ('NN1017', 'D', '3', '1640706235', '1', '-1', '1');
