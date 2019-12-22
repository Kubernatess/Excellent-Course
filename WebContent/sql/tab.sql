/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-11-20 13:57:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tab
-- ----------------------------
DROP TABLE IF EXISTS `tab`;
CREATE TABLE `tab` (
  `teacher_identity` varchar(10) NOT NULL,
  `course_name` varchar(30) NOT NULL,
  `tab_index` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`course_name`,`teacher_identity`,`tab_index`),
  KEY `course_name` (`course_name`),
  KEY `tab_index` (`tab_index`),
  KEY `tab_ibfk_1` (`teacher_identity`),
  CONSTRAINT `tab_ibfk_1` FOREIGN KEY (`teacher_identity`) REFERENCES `user` (`identity`) ON UPDATE CASCADE,
  CONSTRAINT `tab_ibfk_2` FOREIGN KEY (`course_name`) REFERENCES `course` (`name`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab
-- ----------------------------
INSERT INTO `tab` VALUES ('1845', 'C语言程序设计', '1', '课件');
INSERT INTO `tab` VALUES ('1845', 'C语言程序设计', '2', '测验与作业');
