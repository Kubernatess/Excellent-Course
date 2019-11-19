/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-11-18 20:18:08
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
  `depart` varchar(10) NOT NULL,
  `template` varchar(20) NOT NULL,
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
INSERT INTO `course` VALUES ('1845', 'C语言程序设计', 'C语言程序设计课程是华中科技大学人工智能与自动化学院C语言课程组20余年来脚踏实地精心打造的，是湖北省精品课程。课程以程序设计为主线，通过大作业、课程设计以及多种形式的大赛培养学生的程序设计能力，强调实用性，注重培养学生良好的程序设计规范，在师生中具有很好的口碑。', '计算机系', '黑绿之旅', '精品课程网站界面(参考）.png', '计算机科学与技术');
