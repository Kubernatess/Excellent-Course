/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-11-20 13:58:10
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
  `email` varchar(30) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `name` varchar(5) DEFAULT NULL,
  `depart` varchar(10) DEFAULT NULL,
  `major` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`identity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1640706235', '学生', '123456', null, null, null, null, null);
INSERT INTO `user` VALUES ('1720', '老师', '987654321', null, null, null, null, null);
INSERT INTO `user` VALUES ('1845', '老师', '456789', null, null, null, null, null);
INSERT INTO `user` VALUES ('1931', '老师', '654321', null, null, null, null, null);
INSERT INTO `user` VALUES ('admin', '管理员', 'admin', null, null, null, null, null);
