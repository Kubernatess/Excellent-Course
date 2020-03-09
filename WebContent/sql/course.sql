/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-09 21:46:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `teacher_identity` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `team` varchar(100) NOT NULL,
  `cover` varchar(255) NOT NULL,
  `major` varchar(50) NOT NULL,
  PRIMARY KEY (`name`,`teacher_identity`),
  KEY `name` (`name`),
  KEY `course_ibfk_1` (`teacher_identity`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_identity`) REFERENCES `user` (`identity`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1720', 'JavaScript及框架应用', 'JavaScript是世界上最流行的脚本语言，因为在电脑、手机、平板上浏览的所有的网页，以及无数基于HTML5的移动App，交互逻辑都是由JavaScript驱动的。本课程介绍了JavaScript和jQuery（JavaScript框架）知识，以创建出更富交互性、更有趣、对用户更友好的Web网站', '王吉林,', 'timg (1).jpg', '计算机科学与技术');
INSERT INTO `course` VALUES ('1720', 'Java程序设计', 'Java是一种优秀的面向对象的语言，具有跨平台性、用途广泛、容易学习等特点，众多的开源项目都是用Java实现的，可以说Java是程序设计必学的语言。这门课程掌握Java语言、面向对象的特点，掌握Java在多线程、图形用户界面、网络等方面的应用，同时要养成良好的编程习惯，能够编写有一定规模的应用程序', '王吉林,蔡木生,', 'Java.jpg', '计算机科学与技术');
