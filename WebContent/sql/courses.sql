/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-05-06 05:09:17
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
  `major` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`id`) USING BTREE,
  KEY `name` (`name`),
  KEY `teacher_identity` (`teacher_identity`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`teacher_identity`) REFERENCES `users` (`identity`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES ('CC2019', '1880', 'Photoshop软件应用', 'Adobe Photoshop，简称“PS”，是由Adobe Systems开发和发行的图像处理软件。\r\nPhotoshop主要处理以像素所构成的数字图像。使用其众多的编修与绘图工具，可以有效地进行图片编辑工作。ps有很多功能，在图像、图形、文字、视频、出版等各方面都有涉及。\r\n2003年，Adobe Photoshop 8被更名为Adobe Photoshop CS。201', '杜兆勇、李列锋、夏少琼、', '');
INSERT INTO `courses` VALUES ('CW3001', '1720', 'C语言程序设计', 'C语言是一种计算机程序设计语言。它既有高级语言的特点，又具有汇编语言的特点。它可以作为系统设计语言，编写工作系统应用程序，也可以作为应用程序设计语言，编写不依赖计算机硬件的应用程序。', '王建红、王吉林、', '计算机科学与技术');
INSERT INTO `courses` VALUES ('GN3009', '1139', 'Android游戏开发', '如今的Android系统市场份额已节节攀升，势不可挡，越来越多的开发者加入到Android应用开发的行列。从2010年的数据表明，Android系统仅仅推出两年已超过诺基亚的Symbian系统，而且2010年Android市场应用也相比2009年增长了6倍之多；最值得一提的是，这些与日俱增的Android应用程序中，无论是按使用量还是总收入排名，70%的应用排行榜首都是游戏。', '罗林、孟辉、', '');
INSERT INTO `courses` VALUES ('NN1017', '1720', '网页设计基础', 'HTML5是构建Web内容的一种语言描述方式。HTML5是互联网的下一代标准，是构建以及呈现互联网内容的一种语言方式．被认为是互联网的核心技术之一。HTML产生于1990年，1997年HTML4成为互联网标准，并广泛应用于互联网应用的开发。\r\nHTML5是Web中核心语言HTML的规范，用户使用任何手段进行网页浏览时看到的内容原本都是HTML格式的，在浏览器中通过一些技术处理将其转换成为了可识别的信息。HTML5在从前HTML4.01的基础上进行了一定的改进，虽然技术人员在开发过程中可能不会将这些新技术投入应用，但是对于该种技术的新特性，网站开发技术人员是必须要有所了解的。', '王吉林、王继红、', '计算机科学与技术');
