/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-09 21:46:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for column
-- ----------------------------
DROP TABLE IF EXISTS `column`;
CREATE TABLE `column` (
  `teacher_identity` varchar(10) NOT NULL,
  `course_name` varchar(30) NOT NULL,
  `tab_index` int(255) NOT NULL,
  `index` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content` text,
  PRIMARY KEY (`teacher_identity`,`course_name`,`tab_index`,`index`),
  KEY `supcolumn_index` (`index`),
  KEY `teacher_identity` (`teacher_identity`),
  KEY `tab_index` (`tab_index`),
  KEY `column_ibfk_2` (`course_name`),
  CONSTRAINT `column_ibfk_1` FOREIGN KEY (`teacher_identity`) REFERENCES `user` (`identity`) ON UPDATE CASCADE,
  CONSTRAINT `column_ibfk_2` FOREIGN KEY (`course_name`) REFERENCES `course` (`name`) ON UPDATE CASCADE,
  CONSTRAINT `column_ibfk_3` FOREIGN KEY (`tab_index`) REFERENCES `tab` (`index`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of column
-- ----------------------------
INSERT INTO `column` VALUES ('1720', 'JavaScript及框架应用', '1', '1', '第一节', null);
INSERT INTO `column` VALUES ('1720', 'JavaScript及框架应用', '1', '2', '第二节', null);
