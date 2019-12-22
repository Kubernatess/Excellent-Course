/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-11-20 13:58:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `teacher_identity` varchar(10) NOT NULL,
  `course_name` varchar(30) NOT NULL,
  `member` varchar(5) NOT NULL,
  PRIMARY KEY (`teacher_identity`,`course_name`,`member`),
  KEY `course_name` (`course_name`),
  CONSTRAINT `team_ibfk_1` FOREIGN KEY (`teacher_identity`) REFERENCES `user` (`identity`) ON UPDATE CASCADE,
  CONSTRAINT `team_ibfk_2` FOREIGN KEY (`course_name`) REFERENCES `course` (`name`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('1845', 'C语言程序设计', '王建红');
INSERT INTO `team` VALUES ('1845', 'C语言程序设计', '蔡木生');
