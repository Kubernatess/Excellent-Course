/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-04-24 21:41:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `id` char(6) NOT NULL,
  `teacher_identity` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `team` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`id`) USING BTREE,
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES ('GN3009', '1139', 'Android游戏开发', '如今的Android系统市场份额已节节攀升，势不可挡，越来越多的开发者加入到Android应用开发的行列。从2010年的数据表明，Android系统仅仅推出两年已超过诺基亚的Symbian系统，而且2010年Android市场应用也相比2009年增长了6倍之多；最值得一提的是，这些与日俱增的Android应用程序中，无论是按使用量还是总收入排名，70%的应用排行榜首都是游戏。', '罗林,孟辉,');
