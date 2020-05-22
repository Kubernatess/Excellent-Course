/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-05-06 05:09:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `identity` varchar(10) NOT NULL,
  `status` varchar(7) NOT NULL DEFAULT 'student',
  `password` varchar(16) NOT NULL,
  `name` varchar(5) NOT NULL,
  `depart` varchar(5) NOT NULL DEFAULT '软件工程系',
  PRIMARY KEY (`identity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('0344', 'teacher', 'upload_0344', '李列锋', '数码媒体系');
INSERT INTO `users` VALUES ('0351', 'teacher', 'upload_0351', '蔡木生', '计算机系');
INSERT INTO `users` VALUES ('0387', 'teacher', 'upload_0387', '孟辉', '游戏系');
INSERT INTO `users` VALUES ('1139', 'teacher', '123456', '罗林', '游戏系');
INSERT INTO `users` VALUES ('1640706229', 'student', '6229', '谢盛操', '计算机系');
INSERT INTO `users` VALUES ('1640706235', 'student', '302203', '黄晓霖', '计算机系');
INSERT INTO `users` VALUES ('1720', 'teacher', 'upload_1720', '王吉林', '计算机系');
INSERT INTO `users` VALUES ('1830', 'teacher', 'upload_1830', '陈晓旭', '游戏系');
INSERT INTO `users` VALUES ('1845', 'teacher', '456789', '赵巧花', '计算机系');
INSERT INTO `users` VALUES ('1863', 'teacher', 'upload_1863', '程亮', '游戏系');
INSERT INTO `users` VALUES ('1880', 'teacher', 'upload_1880', '杜兆勇', '数码媒体系');
INSERT INTO `users` VALUES ('1931', 'teacher', '654321', '李旭峰', '计算机系');
INSERT INTO `users` VALUES ('1936', 'teacher', '130420', '安明忠', '网络技术系');
