/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-04-24 21:41:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for columns
-- ----------------------------
DROP TABLE IF EXISTS `columns`;
CREATE TABLE `columns` (
  `course_id` char(6) NOT NULL,
  `tab_index` char(1) NOT NULL,
  `index` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content` text,
  PRIMARY KEY (`course_id`,`tab_index`,`index`),
  KEY `supcolumn_index` (`index`),
  KEY `tab_index` (`tab_index`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `columns_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `columns_ibfk_3` FOREIGN KEY (`tab_index`) REFERENCES `tabs` (`index`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of columns
-- ----------------------------
INSERT INTO `columns` VALUES ('GN3009', 'A', '1', '测试一', '<div><span style=\"font-size: 24px;\">下列哪种排序算法的最坏时间复杂度与平均时间复杂度不同？</span></div><div><input type=\"radio\" name=\"202032002354\" value=\"A\">A.冒泡排序<div><input type=\"radio\" name=\"202032002354\" value=\"B\">B.快速排序</div><div><input type=\"radio\" name=\"202032002354\" value=\"C\">C.归并排序</div><div><input type=\"radio\" name=\"202032002354\" value=\"D\">D.堆排序</div></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">C++11有哪些新的特性（多选）</span></div><input type=\"checkbox\" name=\"202032002444\" value=\"A\">A.右值引用<div><input type=\"checkbox\" name=\"202032002444\" value=\"B\">B.Lambda函数</div><div><input type=\"checkbox\" name=\"202032002444\" value=\"C\">C.using新特性</div><div><input type=\"checkbox\" name=\"202032002444\" value=\"D\">D.协程</div><div><input type=\"checkbox\" name=\"202032002444\" value=\"E\">E.STL容器</div><div><br></div><input type=\"submit\" class=\"btn\" value=\"提交答案\" form=\"testing\">');
INSERT INTO `columns` VALUES ('GN3009', 'A', '2', '测试二', null);
INSERT INTO `columns` VALUES ('GN3009', 'A', '3', '测试三', null);
