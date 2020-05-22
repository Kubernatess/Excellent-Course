/*
Navicat MySQL Data Transfer

Source Server         : doudizhu
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : excellent course

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-05-06 05:09:05
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
  `name` varchar(255) NOT NULL DEFAULT '新建栏目',
  `content` text,
  `answer` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`course_id`,`tab_index`,`index`),
  KEY `supcolumn_index` (`index`),
  KEY `tab_index` (`tab_index`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `columns_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of columns
-- ----------------------------
INSERT INTO `columns` VALUES ('CW3001', 'A', '2', '课件一', null, '');
INSERT INTO `columns` VALUES ('CW3001', 'A', '3', '课件二', null, '');
INSERT INTO `columns` VALUES ('CW3001', 'B', '1', '短视频', null, '');
INSERT INTO `columns` VALUES ('CW3001', 'C', '1', '测试一', '<div><span style=\"font-size: 24px;\">给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">示例 1：</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">输入: \"babad\"</span></div><div><span style=\"font-size: 24px;\">输出: \"bab\"</span></div><div><span style=\"font-size: 24px;\">注意: \"aba\" 也是一个有效答案。</span></div><div><span style=\"font-size: 24px;\">示例 2：</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">输入: \"cbbd\"</span></div><div><span style=\"font-size: 24px;\">输出: \"bb\"</span></div><div><span style=\"font-size: 24px;\">通过次数247,392提交次数836,707</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><br></div><div><textarea form=\"homework\" name=\"textarea\" style=\"margin: 0px; width: 464px; height: 125px;\"></textarea></div><div><br></div><div><input type=\"file\" name=\"upload\" form=\"homework\"></div><div><br></div><div><br></div><div><input type=\"submit\" class=\"btn\" value=\"提交作业\" form=\"homework\"></div>', '');
INSERT INTO `columns` VALUES ('CW3001', 'C', '2', '测试二', null, '');
INSERT INTO `columns` VALUES ('GN3009', 'A', '1', '测试一', '\r\n\r\n\r\n\r\n\r\n<div><span style=\"font-size: 24px;\">下列哪种排序算法的最坏时间复杂度与平均时间复杂度不同？</span></div><div><input type=\"radio\" name=\"1\" value=\"A\">A.冒泡排序<div><input type=\"radio\" name=\"1\" value=\"B\">B.快速排序</div><div><input type=\"radio\" name=\"1\" value=\"C\">C.归并排序</div><div><input type=\"radio\" name=\"1\" value=\"D\">D.堆排序</div></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">C++11有哪些新的特性（多选）</span></div><input type=\"checkbox\" name=\"2\" value=\"A\">A.右值引用<div><input type=\"checkbox\" name=\"2\" value=\"B\">B.Lambda函数</div><div><input type=\"checkbox\" name=\"2\" value=\"C\">C.using新特性</div><div><input type=\"checkbox\" name=\"2\" value=\"D\">D.协程</div><div><input type=\"checkbox\" name=\"2\" value=\"E\">E.STL容器</div><div><br></div><input type=\"submit\" class=\"btn\" value=\"提交答案\" form=\"testing\">\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n', ',B,ACD');
INSERT INTO `columns` VALUES ('GN3009', 'A', '2', '测试二', '\r\n\r\n\r\n\r\n<span>在此处编辑</span>\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n<div><span style=\"font-size: 24px;\">在此处编辑</span></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div></div></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div></div></div></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div></div></div></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div><div><span style=\"font-size: 24px;\">在此处编辑</span><div><span style=\"font-size: 24px;\">在此处编辑</span></div></div></div></div>', '');
INSERT INTO `columns` VALUES ('GN3009', 'A', '3', '测试三', '\r\n<!-- \r\n\r\n<!-- \r\n<span>在此处编辑</span>\r\n\r\n -->\r\n<span>在此处编辑</span>\r\n\r\n<span style=\"font-size: 24px; font-style: normal; color: rgb(255, 128, 128); font-weight: normal;\">在此处编辑</span><span style=\"font-size: 24px; font-style: normal; color: rgb(255, 128, 192); font-weight: normal;\">在此处编辑</span><span style=\"font-size: 24px; font-style: normal; color: rgb(0, 128, 255); font-weight: normal;\">在此处编辑</span>&nbsp;', '');
INSERT INTO `columns` VALUES ('GN3009', 'A', '4', '测试四', null, '');
INSERT INTO `columns` VALUES ('GN3009', 'B', '1', '作业一', null, '');
INSERT INTO `columns` VALUES ('GN3009', 'B', '3', '作业三', null, '');
INSERT INTO `columns` VALUES ('GN3009', 'C', '2', '视频二', '\r\n\r\n\r\n\r\n<span>在此处编辑</span>&nbsp;<img src=\"/Excellent-Course/upload/GN3009/C/2/-1720877224.png\r\n\"><div>附件：1640706235_黄晓霖_计算机系毕业设计答辩申请表.doc<a target=\"_blank\" href=\"/Excellent-Course/upload/GN3009/C/2/1296437777.doc\r\n\">下载链接</a></div>', '');
INSERT INTO `columns` VALUES ('NN1017', 'A', '1', '视频一', '\r\n<span>在此处编辑</span>\r\n\r\n<div><video width=\"800\" height=\"400\" controls=\"\"><source type=\"video/mp4\" src=\"/Excellent-Course/upload/NN1017/A/1/1963428640.mp4\r\n\"></video></div>', '');
INSERT INTO `columns` VALUES ('NN1017', 'A', '2', '视频二', '\r\n<span>在此处编辑</span>\r\n\r\n<div><video width=\"800\" height=\"400\" controls=\"\"><source type=\"video/mp4\" src=\"/Excellent-Course/upload/NN1017/A/2/-827902158.mp4\r\n\"></video></div>', '');
INSERT INTO `columns` VALUES ('NN1017', 'A', '3', '视频三', '<div><video width=\"800\" height=\"400\" controls=\"\"><source type=\"video/mp4\" src=\"/Excellent-Course/upload/NN1017/A/3/-304734937.mp4\r\n\"><source type=\"video/ogg\" src=\"/Excellent-Course/upload/NN1017/A/3/-304734937.mp4\r\n\"></video></div>', '');
INSERT INTO `columns` VALUES ('NN1017', 'B', '1', '课件一', '&nbsp;<img src=\"/Excellent-Course/upload/NN1017/B/1/13050085.png\r\n\">', '');
INSERT INTO `columns` VALUES ('NN1017', 'B', '2', '课件二', '<img src=\"/Excellent-Course/upload/NN1017/B/2/110364485.jpg\r\n\">', '');
INSERT INTO `columns` VALUES ('NN1017', 'B', '3', '课件三', null, '');
INSERT INTO `columns` VALUES ('NN1017', 'C', '1', '作业一', '\r\n\r\n<div><span style=\"font-size: 24px;\">给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">你可以假设 nums1 和 nums2 不会同时为空。</span></div><div><div>示例 1:</div><div><br></div><div>nums1 = [1, 3]</div><div>nums2 = [2]</div><div><br></div><div>则中位数是 2.0</div><div>示例 2:</div><div><br></div><div>nums1 = [1, 2]</div><div>nums2 = [3, 4]</div><div><br></div><div>则中位数是 (2 + 3)/2 = 2.5</div><div>通过次数180,654提交次数483,822</div><div>在真实的面试中遇到过这道题？</div><div><br></div><div>是</div><div><br></div><div>否</div><div><br></div></div>\r\n\r\n<div><textarea form=\"homework\" name=\"textarea\" style=\"margin: 0px; height: 150px; width: 428px;\"></textarea></div><div><input type=\"submit\" class=\"btn\" value=\"提交作业\" form=\"homework\"></div>', '');
INSERT INTO `columns` VALUES ('NN1017', 'C', '2', '作业二', '\r\n<div><span style=\"font-size: 24px;\">表1: Person</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">+-------------+---------+</span></div><div><span style=\"font-size: 24px;\">| 列名&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 类型&nbsp; &nbsp; &nbsp;|</span></div><div><span style=\"font-size: 24px;\">+-------------+---------+</span></div><div><span style=\"font-size: 24px;\">| PersonId&nbsp; &nbsp; | int&nbsp; &nbsp; &nbsp;|</span></div><div><span style=\"font-size: 24px;\">| FirstName&nbsp; &nbsp;| varchar |</span></div><div><span style=\"font-size: 24px;\">| LastName&nbsp; &nbsp; | varchar |</span></div><div><span style=\"font-size: 24px;\">+-------------+---------+</span></div><div><span style=\"font-size: 24px;\">PersonId 是上表主键</span></div><div><span style=\"font-size: 24px;\">表2: Address</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">+-------------+---------+</span></div><div><span style=\"font-size: 24px;\">| 列名&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| 类型&nbsp; &nbsp; |</span></div><div><span style=\"font-size: 24px;\">+-------------+---------+</span></div><div><span style=\"font-size: 24px;\">| AddressId&nbsp; &nbsp;| int&nbsp; &nbsp; &nbsp;|</span></div><div><span style=\"font-size: 24px;\">| PersonId&nbsp; &nbsp; | int&nbsp; &nbsp; &nbsp;|</span></div><div><span style=\"font-size: 24px;\">| City&nbsp; &nbsp; &nbsp; &nbsp; | varchar |</span></div><div><span style=\"font-size: 24px;\">| State&nbsp; &nbsp; &nbsp; &nbsp;| varchar |</span></div><div><span style=\"font-size: 24px;\">+-------------+---------+</span></div><div><span style=\"font-size: 24px;\">AddressId 是上表主键</span></div><div><span style=\"font-size: 24px;\">&nbsp;</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息：</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">&nbsp;</span></div><div><span style=\"font-size: 24px;\"><br></span></div><div><span style=\"font-size: 24px;\">FirstName, LastName, City, State</span></div><div><span style=\"font-size: 24px;\">通过次数121,989提交次数168,496</span></div><div><br></div>\r\n\r\n<div><input type=\"file\" name=\"upload\" form=\"homework\"></div><div><br></div><div><input type=\"submit\" class=\"btn\" value=\"提交作业\" form=\"homework\"></div>', '');
INSERT INTO `columns` VALUES ('NN1017', 'C', '3', '作业三', null, '');
INSERT INTO `columns` VALUES ('NN1017', 'D', '1', '测试一', '\r\n\r\n\r\n\r\n\r\n\r\n\r\n<span style=\"color: rgb(51, 51, 51); font-family: system, -apple-system, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Segoe UI&quot;, &quot;Microsoft YaHei&quot;, &quot;wenquanyi micro hei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Hiragino Sans GB W3&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Fira Sans&quot;, &quot;Droid Sans&quot;, Arial, sans-serif; font-size: 14px; background-color: rgb(255, 255, 255);\">1.以下数据结构中不属于线性数据结构的是</span><div><ol type=\"A\"><li><input type=\"radio\" name=\"1\" value=\"A\">队列</li><li><input type=\"radio\" name=\"1\" value=\"B\">线性表</li><li><input type=\"radio\" name=\"1\" value=\"C\" checked=\"true\">二叉树</li><li><input type=\"radio\" name=\"1\" value=\"D\">栈</li></ol><div>2.<span style=\"background-color: rgb(255, 255, 255); color: rgb(51, 51, 51); font-family: system, -apple-system, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Segoe UI&quot;, &quot;Microsoft YaHei&quot;, &quot;wenquanyi micro hei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Hiragino Sans GB W3&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Fira Sans&quot;, &quot;Droid Sans&quot;, Arial, sans-serif; font-size: 14px;\">以下数据结构中是线性结构是（）</span></div></div><div><ol type=\"A\"><li><input type=\"checkbox\" name=\"2\" value=\"A\" checked=\"true\">队列</li><li><input type=\"checkbox\" name=\"2\" value=\"B\" checked=\"true\">栈</li><li><input type=\"checkbox\" name=\"2\" value=\"C\">二叉树</li><li><input type=\"checkbox\" name=\"2\" value=\"D\" checked=\"true\">线性表</li></ol><div>3.<span style=\"background-color: rgb(255, 255, 255); color: rgb(51, 51, 51); font-family: system, -apple-system, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Segoe UI&quot;, &quot;Microsoft YaHei&quot;, &quot;wenquanyi micro hei&quot;, &quot;Hiragino Sans GB&quot;, &quot;Hiragino Sans GB W3&quot;, Roboto, Oxygen, Ubuntu, Cantarell, &quot;Fira Sans&quot;, &quot;Droid Sans&quot;, Arial, sans-serif; font-size: 14px;\">下列数据中,哪项是非线性数据结构</span></div></div><div><ol type=\"A\"><li><input type=\"radio\" name=\"3\" value=\"A\">栈</li><li><input type=\"radio\" name=\"3\" value=\"B\">堆</li><li><input type=\"radio\" name=\"3\" value=\"C\">队列</li><li><input type=\"radio\" name=\"3\" value=\"D\" checked=\"true\">完全二叉树</li></ol></div><div><br></div><div><input type=\"submit\" class=\"btn\" value=\"提交答案\" form=\"testing\"></div>\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n', ',C,ABD,D');
INSERT INTO `columns` VALUES ('NN1017', 'D', '2', '测试二', null, '');
INSERT INTO `columns` VALUES ('NN1017', 'D', '3', '测试三', null, '');
INSERT INTO `columns` VALUES ('NN1017', 'D', '4', '测试四', null, '');
DROP TRIGGER IF EXISTS `cascade_update`;
DELIMITER ;;
CREATE TRIGGER `cascade_update` AFTER UPDATE ON `columns` FOR EACH ROW begin  
      update records set column_index=new.`index` where course_id=old.course_id and tab_index=old.tab_index and column_index=old.`INDEX`;
      update comments set column_index=new.`index` where course_id=old.course_id and tab_index=old.tab_index and column_index=old.`INDEX`;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `cascade_delete`;
DELIMITER ;;
CREATE TRIGGER `cascade_delete` AFTER DELETE ON `columns` FOR EACH ROW begin  
      delete from records where course_id=old.course_id and tab_index=old.tab_index and column_index=old.`INDEX`;
      delete from comments where course_id=old.course_id and tab_index=old.tab_index and column_index=old.`INDEX`;
end
;;
DELIMITER ;
