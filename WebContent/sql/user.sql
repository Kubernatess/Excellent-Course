/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-09 21:46:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `identity` varchar(10) NOT NULL,
  `status` varchar(3) NOT NULL,
  `password` varchar(16) NOT NULL,
  `name` varchar(5) NOT NULL,
  `depart` varchar(10) NOT NULL,
  `major` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`identity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1139', '老师', '123456', '罗林', '游戏系', '数字媒体技术');
INSERT INTO `user` VALUES ('1640706235', '学生', '302203', '黄晓霖', '计算机系', '计算机科学与技术');
INSERT INTO `user` VALUES ('1699', '老师', '456789', '杨志聪', '软件工程系', '');
INSERT INTO `user` VALUES ('1720', '老师', '987654321', '王吉林', '计算机系', '计算机科学与技术');
INSERT INTO `user` VALUES ('1845', '老师', '456789', '赵巧花', '计算机系', '计算机科学与技术');
INSERT INTO `user` VALUES ('1931', '老师', '654321', '李旭峰', '计算机系', '计算机科学与技术');
INSERT INTO `user` VALUES ('1936', '老师', '130420', '安明忠', '网络技术系', '网络工程');
INSERT INTO `user` VALUES ('admin', '管理员', 'admin', '管理员', '教务处', '');
