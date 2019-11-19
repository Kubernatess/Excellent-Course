/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-11-18 20:18:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for subcolumn
-- ----------------------------
DROP TABLE IF EXISTS `subcolumn`;
CREATE TABLE `subcolumn` (
  `teacher_identity` varchar(10) NOT NULL,
  `course_name` varchar(30) NOT NULL,
  `tab_index` int(255) NOT NULL,
  `column_index` int(255) NOT NULL,
  `subcolumn_index` int(255) NOT NULL,
  `subcolumn_name` varchar(255) NOT NULL,
  `content` text,
  PRIMARY KEY (`teacher_identity`,`course_name`,`column_index`,`tab_index`,`subcolumn_index`),
  KEY `column_index` (`column_index`),
  KEY `subcolumn_ibfk_3` (`tab_index`),
  KEY `subcolumn_ibfk_2` (`course_name`),
  KEY `subcolumn_index` (`subcolumn_index`),
  CONSTRAINT `subcolumn_ibfk_1` FOREIGN KEY (`teacher_identity`) REFERENCES `user` (`identity`) ON UPDATE CASCADE,
  CONSTRAINT `subcolumn_ibfk_2` FOREIGN KEY (`course_name`) REFERENCES `course` (`name`) ON UPDATE CASCADE,
  CONSTRAINT `subcolumn_ibfk_3` FOREIGN KEY (`tab_index`) REFERENCES `tab` (`tab_index`) ON UPDATE CASCADE,
  CONSTRAINT `subcolumn_ibfk_4` FOREIGN KEY (`column_index`) REFERENCES `column` (`column_index`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subcolumn
-- ----------------------------
INSERT INTO `subcolumn` VALUES ('1845', 'C语言程序设计', '1', '1', '1', '添加子栏目', null);
INSERT INTO `subcolumn` VALUES ('1845', 'C语言程序设计', '1', '1', '2', '第2讲 C语言上机环境  ', '\r\n\r\n<span>请在此处编辑</span>\r\n\r\n<span style=\"font-size: 20px; font-style: normal; color: black; font-weight: normal;\">在此处编辑</span><span style=\"font-size: 24px; font-style: normal; color: black; font-weight: normal;\">在此处编辑fs你好啊</span><span style=\"color: rgb(255, 128, 192); font-style: italic; font-weight: 700;\">编辑</span><span style=\"font-size: 24px; font-style: normal; color: black; font-weight: normal;\">在此处编辑</span><span style=\"font-size: 24px; font-style: normal; color: black; font-weight: bold;\">在此处编辑</span><span style=\"font-size: 24px; font-style: italic; color: black; font-weight: bold;\">在此处编辑</span><span style=\"font-size: 24px; font-style: italic; color: rgb(255, 128, 192); font-weight: bold;\">在此处编辑编辑</span>\r\n\r\n');
INSERT INTO `subcolumn` VALUES ('1845', 'C语言程序设计', '1', '2', '1', '添加子栏目', '\r\n<span>在此处编辑</span>\r\n\r\n<span style=\"font-size: 24px; font-style: normal; color: black; font-weight: normal;\">在此处编辑</span><span style=\"font-size: 24px; font-style: normal; color: black; font-weight: bold;\">在此处编辑</span><span style=\"font-size: 24px; font-style: normal; color: rgb(0, 128, 255); font-weight: bold;\">在此处编辑</span><span style=\"font-size: 28px; font-style: normal; color: rgb(0, 128, 255); font-weight: bold;\">在此处编辑</span><span style=\"font-size: 36px; font-style: normal; color: rgb(0, 128, 255); font-weight: bold;\">在此处编辑</span><span style=\"font-size: 24px; font-style: normal; color: black; font-weight: normal;\">在此处编辑</span>');
