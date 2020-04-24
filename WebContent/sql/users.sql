/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-04-24 21:42:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `identity` varchar(10) NOT NULL,
  `status` varchar(7) NOT NULL,
  `password` varchar(16) NOT NULL,
  `name` varchar(5) NOT NULL,
  `depart` varchar(10) NOT NULL,
  `major` varchar(30) NOT NULL,
  PRIMARY KEY (`identity`),
  KEY `major` (`major`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1139', 'teacher', '123456', '罗林', '游戏系', '数字媒体技术');
INSERT INTO `users` VALUES ('1640706229', 'student', '6229', '谢盛操', '计算机系', '计算机科学与技术');
INSERT INTO `users` VALUES ('1640706235', 'student', '302203', '黄晓霖', '计算机系', '计算机科学与技术');
INSERT INTO `users` VALUES ('1720', 'teacher', '987654321', '王吉林', '计算机系', '计算机科学与技术');
INSERT INTO `users` VALUES ('1845', 'teacher', '456789', '赵巧花', '计算机系', '计算机科学与技术');
INSERT INTO `users` VALUES ('1880', 'teacher', '456789', '杜兆勇', '数码媒体系', '产品设计');
INSERT INTO `users` VALUES ('1931', 'teacher', '654321', '李旭峰', '计算机系', '计算机科学与技术');
INSERT INTO `users` VALUES ('1936', 'teacher', '130420', '安明忠', '网络技术系', '网络工程');
