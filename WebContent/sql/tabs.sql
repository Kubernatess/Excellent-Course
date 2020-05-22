/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-05-06 05:09:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tabs
-- ----------------------------
DROP TABLE IF EXISTS `tabs`;
CREATE TABLE `tabs` (
  `course_id` char(6) NOT NULL,
  `index` char(1) NOT NULL,
  `name` varchar(20) NOT NULL DEFAULT '新建选项卡',
  PRIMARY KEY (`course_id`,`index`),
  KEY `course_name` (`course_id`),
  KEY `name` (`name`),
  KEY `index` (`index`),
  CONSTRAINT `tabs_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tabs
-- ----------------------------
INSERT INTO `tabs` VALUES ('GN3009', 'B', '作业');
INSERT INTO `tabs` VALUES ('CW3001', 'E', '作业区');
INSERT INTO `tabs` VALUES ('NN1017', 'C', '作业区');
INSERT INTO `tabs` VALUES ('CW3001', 'D', '参考文档');
INSERT INTO `tabs` VALUES ('CW3001', 'C', '测试');
INSERT INTO `tabs` VALUES ('GN3009', 'A', '测试');
INSERT INTO `tabs` VALUES ('NN1017', 'D', '测试');
INSERT INTO `tabs` VALUES ('CW3001', 'B', '视频');
INSERT INTO `tabs` VALUES ('GN3009', 'C', '视频');
INSERT INTO `tabs` VALUES ('NN1017', 'A', '视频');
INSERT INTO `tabs` VALUES ('CW3001', 'A', '课件区');
INSERT INTO `tabs` VALUES ('NN1017', 'B', '课件区');
