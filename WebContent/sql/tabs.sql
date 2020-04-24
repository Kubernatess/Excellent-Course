/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-04-24 21:42:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tabs
-- ----------------------------
DROP TABLE IF EXISTS `tabs`;
CREATE TABLE `tabs` (
  `course_id` char(6) NOT NULL,
  `index` char(1) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`course_id`,`index`),
  KEY `course_name` (`course_id`),
  KEY `name` (`name`),
  KEY `index` (`index`),
  CONSTRAINT `tabs_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tabs
-- ----------------------------
INSERT INTO `tabs` VALUES ('GN3009', 'A', '测试');
