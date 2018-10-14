/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50128
Source Host           : localhost:3306
Source Database       : bookmanagement

Target Server Type    : MYSQL
Target Server Version : 50128
File Encoding         : 65001

Date: 2018-08-21 12:17:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminName` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) DEFAULT NULL,
  `Email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`adminName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '123456', 'admin@163.com');

-- ----------------------------
-- Table structure for `books_info`
-- ----------------------------
DROP TABLE IF EXISTS `books_info`;
CREATE TABLE `books_info` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `book_id` varchar(11) NOT NULL,
  `book_name` varchar(50) NOT NULL,
  `book_press` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `book_price` varchar(20) DEFAULT NULL,
  `book_num` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`,`book_id`),
  KEY `book_id` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of books_info
-- ----------------------------
INSERT INTO `books_info` VALUES ('1', '1001', '平凡的世界', '清华大学出版社', '三毛', '56', '233');
INSERT INTO `books_info` VALUES ('2', '1002', 'Java语言程序设计', '高等教育出版社', '陈帧', '49.8', '100');
INSERT INTO `books_info` VALUES ('3', '1003', '水浒传', '人民邮电出版社', '测试', '33', '200');

-- ----------------------------
-- Table structure for `borrow_info`
-- ----------------------------
DROP TABLE IF EXISTS `borrow_info`;
CREATE TABLE `borrow_info` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `book_id` varchar(20) DEFAULT NULL,
  `user_id` varchar(11) DEFAULT NULL,
  `username` varchar(11) NOT NULL DEFAULT '0',
  `bookname` varchar(50) DEFAULT NULL,
  `borrowdate` date DEFAULT NULL,
  `yreturndate` date DEFAULT NULL,
  `sreturndate` date DEFAULT NULL,
  `fakuan` double(20,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bookid` (`book_id`),
  KEY `userid` (`user_id`),
  CONSTRAINT `userid` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bookid` FOREIGN KEY (`book_id`) REFERENCES `books_info` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow_info
-- ----------------------------
INSERT INTO `borrow_info` VALUES ('18', '1001', '201801', '黄山', '平凡的世界', '2018-08-20', '2018-09-19', '2018-10-12', '5');
INSERT INTO `borrow_info` VALUES ('20', '1002', '201801', '黄山', 'Java语言程序设计', '2018-08-21', '2018-09-20', null, null);
INSERT INTO `borrow_info` VALUES ('25', '1001', '201702', '何申丽', '平凡的世界', '2018-08-21', '2018-09-20', '2018-09-25', '0');
INSERT INTO `borrow_info` VALUES ('27', '1001', '201703', '张加家', '平凡的世界', '2018-08-21', '2018-09-20', '2018-09-25', '0');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(11) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `department` varchar(20) DEFAULT NULL,
  `uclass` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`userid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '201801', '黄山', '计算机工程系', '18-1', '123');
INSERT INTO `user_info` VALUES ('2', '201702', '何申丽', '艺术系', '17-2', '234');
INSERT INTO `user_info` VALUES ('3', '201703', '张加家', '计算机工程系', '17-1', '123');
INSERT INTO `user_info` VALUES ('4', '201504', '张笑笑', '计算机工程系', '15-1', '111');
INSERT INTO `user_info` VALUES ('9', '201614', '何中华', '会计系', '16-1', '12322');
