/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : zngd_monitor

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2019-01-01 13:05:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for threshold
-- ----------------------------
DROP TABLE IF EXISTS `threshold`;
CREATE TABLE `threshold` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` varchar(255) NOT NULL DEFAULT '' COMMENT '阈值类型 例如可燃气阈值类型gas_1',
  `max_val` varchar(512) NOT NULL DEFAULT '' COMMENT '阈值上限值',
  `min_val` varchar(512) NOT NULL DEFAULT '' COMMENT '下限值',
  `identical_val` varchar(255) NOT NULL DEFAULT '' COMMENT '恒等值,不等于此值则报警',
  `outletid` int(11) NOT NULL DEFAULT '0' COMMENT '工地id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of threshold
-- ----------------------------
INSERT INTO `threshold` VALUES ('1', 'gas_1', '{\"23\":\"#CC9900\",\"26\":\"#FF0000\"}', '', '', '2');
INSERT INTO `threshold` VALUES ('2', 'gas_2', '{\"23\":\"#CC9900\",\"26\":\"#FF0000\"}', '{\"17\":\"#CC9900\",\"15\":\"#FF0000\"}', '', '2');
INSERT INTO `threshold` VALUES ('3', 'gas_3', '{\"23\":\"#CC9900\",\"26\":\"#FF0000\"}', '{\"17\":\"#CC9900\",\"15\":\"#FF0000\"}', '', '2');
INSERT INTO `threshold` VALUES ('4', 'gas_4', '{\"23\":\"#CC9900\",\"26\":\"#FF0000\"}', '{\"17\":\"#CC9900\",\"15\":\"#FF0000\"}', '', '2');
