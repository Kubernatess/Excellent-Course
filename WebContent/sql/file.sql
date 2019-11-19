/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-11-18 20:18:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `teacher_identity` varchar(10) NOT NULL,
  `course_name` varchar(30) NOT NULL,
  `tab_index` int(10) NOT NULL,
  `column_index` int(10) NOT NULL,
  `subcolumn_index` int(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`subcolumn_index`,`teacher_identity`,`course_name`,`tab_index`,`column_index`,`name`),
  KEY `teacher_identity` (`teacher_identity`),
  KEY `course_name` (`course_name`),
  KEY `tab_index` (`tab_index`),
  KEY `column_index` (`column_index`),
  CONSTRAINT `file_ibfk_1` FOREIGN KEY (`teacher_identity`) REFERENCES `user` (`identity`) ON UPDATE CASCADE,
  CONSTRAINT `file_ibfk_2` FOREIGN KEY (`course_name`) REFERENCES `course` (`name`) ON UPDATE CASCADE,
  CONSTRAINT `file_ibfk_3` FOREIGN KEY (`tab_index`) REFERENCES `tab` (`tab_index`) ON UPDATE CASCADE,
  CONSTRAINT `file_ibfk_4` FOREIGN KEY (`column_index`) REFERENCES `column` (`column_index`) ON UPDATE CASCADE,
  CONSTRAINT `file_ibfk_5` FOREIGN KEY (`subcolumn_index`) REFERENCES `subcolumn` (`subcolumn_index`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
