/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : zngd_monitor

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2019-01-01 13:05:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for settlement
-- ----------------------------
DROP TABLE IF EXISTS `settlement`;
CREATE TABLE `settlement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `location` varchar(255) NOT NULL DEFAULT '' COMMENT '对应位置',
  `location_no` varchar(255) NOT NULL DEFAULT '' COMMENT '测点编号',
  `last_val` float NOT NULL DEFAULT '0' COMMENT '上次变量',
  `current_val` float NOT NULL DEFAULT '0' COMMENT '本次变量',
  `accumulate_val` float NOT NULL DEFAULT '0' COMMENT '累计变量',
  `rate` float NOT NULL DEFAULT '0' COMMENT '变化速率(mm/d)',
  `remark` varchar(1024) NOT NULL DEFAULT '' COMMENT '备注',
  `dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '本次检测日期',
  `equipment` varchar(255) NOT NULL DEFAULT '' COMMENT '仪器名称',
  `equipment_no` varchar(255) NOT NULL DEFAULT '' COMMENT '仪器编号',
  `outletid` int(11) NOT NULL DEFAULT '0' COMMENT '工地id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of settlement
-- ----------------------------
INSERT INTO `settlement` VALUES ('1', '大江东公安分局', 'JGC1-1', '0.42', '-1.04', '-2.19', '-0.35', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('2', '大江东公安分局', 'JGC1-2', '0.62', '-1.33', '-1.06', '-0.44', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('3', '大江东公安分局', 'JGC1-3', '1.38', '-1.12', '0.46', '-0.37', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('4', '大江东公安分局', 'JGC1-4', '0.23', '-0.9', '1.37', '-0.3', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('5', '大江东公安分局', 'JGC1-5', '0.43', '-0.68', '0.44', '-0.23', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('6', '大江东公安分局', 'JGC1-6', '0.63', '-1.3', '-0.42', '-0.43', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('7', '义逢体育中心', 'JGC2-1', '-0.26', '-0.48', '1.56', '-0.16', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('8', '义逢体育中心', 'JGC2-2', '0.5', '-0.15', '0.4', '-0.05', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('9', '义逢体育中心', 'JGC2-3', '-1.08', '0.87', '1.34', '0.29', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('10', '义逢体育中心', 'JGC2-4', '-0.2', '-0.02', '1.48', '-0.01', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('11', '义逢体育中心', 'JGC2-5', '-0.81', '0.57', '1.54', '0.19', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('12', '义逢体育中心', 'JGC2-6', '-0.58', '0.34', '0.89', '0.11', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('13', '义逢体育中心', 'JGC2-7', '-0.67', '0.34', '1.05', '0.11', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('14', '义逢体育中心', 'JGC2-8', '-0.91', '0.27', '2.06', '0.09', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('15', '义逢体育中心', 'JGC2-9', '-0.52', '0.22', '1.36', '0.07', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('16', '大江东公安分局', 'JGC1-1', '-1.04', '-0.28', '-2.47', '-0.09', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('17', '大江东公安分局', 'JGC1-2', '-1.33', '-0.14', '-1.2', '-0.05', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('18', '大江东公安分局', 'JGC1-3', '-1.12', '-0.13', '0.33', '-0.04', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('19', '大江东公安分局', 'JGC1-4', '-0.9', '0.1', '1.47', '0.03', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('20', '大江东公安分局', 'JGC1-5', '-0.68', '0.03', '0.47', '0.01', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('21', '大江东公安分局', 'JGC1-6', '-1.3', '-0.21', '-0.63', '-0.07', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('22', '义逢体育中心', 'JGC2-1', '-0.48', '-0.08', '1.48', '-0.03', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('23', '义逢体育中心', 'JGC2-2', '-0.15', '0.02', '0.42', '0.01', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('24', '义逢体育中心', 'JGC2-3', '0.87', '0.15', '1.49', '0.05', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('25', '义逢体育中心', 'JGC2-4', '-0.02', '0.03', '1.51', '0.01', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('26', '义逢体育中心', 'JGC2-5', '0.57', '-0.24', '1.3', '-0.08', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('27', '义逢体育中心', 'JGC2-6', '0.34', '0', '0.89', '0', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('28', '义逢体育中心', 'JGC2-7', '0.34', '-0.15', '0.9', '-0.05', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('29', '义逢体育中心', 'JGC2-8', '0.27', '-0.16', '1.9', '-0.05', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('30', '义逢体育中心', 'JGC2-9', '0.22', '-0.04', '1.32', '-0.01', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('31', '大江东公安分局', 'JGC1-1', '-0.28', '0.14', '-2.33', '0.05', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('32', '大江东公安分局', 'JGC1-2', '-0.14', '-0.12', '-1.32', '-0.04', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('33', '大江东公安分局', 'JGC1-3', '-0.13', '-0.33', '0', '-0.11', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('34', '大江东公安分局', 'JGC1-4', '0.1', '-0.33', '1.14', '-0.11', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('35', '大江东公安分局', 'JGC1-5', '0.03', '-0.03', '0.44', '-0.01', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('36', '大江东公安分局', 'JGC1-6', '-0.21', '-0.29', '-0.92', '-0.1', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('37', '义逢体育中心', 'JGC2-1', '-0.08', '0.08', '1.56', '0.03', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('38', '义逢体育中心', 'JGC2-2', '0.02', '0.1', '0.52', '0.03', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('39', '义逢体育中心', 'JGC2-3', '0.15', '-0.11', '1.38', '-0.04', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('40', '义逢体育中心', 'JGC2-4', '0.03', '-0.19', '1.32', '-0.06', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('41', '义逢体育中心', 'JGC2-5', '-0.24', '-0.31', '0.99', '-0.1', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('42', '义逢体育中心', 'JGC2-6', '0', '-0.27', '0.62', '-0.09', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('43', '义逢体育中心', 'JGC2-7', '-0.15', '-0.2', '0.7', '-0.07', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('44', '义逢体育中心', 'JGC2-8', '-0.16', '-0.22', '1.68', '-0.07', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('45', '义逢体育中心', 'JGC2-9', '-0.04', '-0.19', '1.13', '-0.06', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('46', '大江东公安分局', 'JGC1-1', '0.14', '-0.31', '-2.64', '-0.1', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('47', '大江东公安分局', 'JGC1-2', '-0.12', '0.17', '-1.15', '0.06', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('48', '大江东公安分局', 'JGC1-3', '-0.33', '-0.04', '-0.04', '-0.01', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('49', '大江东公安分局', 'JGC1-4', '-0.33', '-0.37', '0.77', '-0.12', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('50', '大江东公安分局', 'JGC1-5', '-0.03', '-0.2', '0.24', '-0.07', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('51', '大江东公安分局', 'JGC1-6', '-0.29', '-0.05', '-0.97', '-0.02', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('52', '义逢体育中心', 'JGC2-1', '0.08', '-0.3', '1.26', '-0.1', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('53', '义逢体育中心', 'JGC2-2', '0.1', '-0.1', '0.42', '-0.03', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('54', '义逢体育中心', 'JGC2-3', '-0.11', '-0.4', '0.98', '-0.13', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('55', '义逢体育中心', 'JGC2-4', '-0.19', '-0.27', '1.05', '-0.09', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('56', '义逢体育中心', 'JGC2-5', '-0.31', '-0.42', '0.57', '-0.14', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('57', '义逢体育中心', 'JGC2-6', '-0.27', '-0.36', '0.26', '-0.12', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('58', '义逢体育中心', 'JGC2-7', '-0.2', '-0.15', '0.55', '-0.05', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('59', '义逢体育中心', 'JGC2-8', '-0.22', '0.1', '1.78', '0.03', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('60', '义逢体育中心', 'JGC2-9', '-0.19', '0.1', '1.23', '0.03', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('61', '大江东公安分局', 'JGC1-1', '-0.31', '0.82', '-1.82', '0.27', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('62', '大江东公安分局', 'JGC1-2', '0.17', '0.32', '-0.83', '0.11', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('63', '大江东公安分局', 'JGC1-3', '-0.04', '0.16', '0.12', '0.05', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('64', '大江东公安分局', 'JGC1-4', '-0.37', '-0.05', '0.72', '-0.02', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('65', '大江东公安分局', 'JGC1-5', '-0.2', '0.18', '0.42', '0.06', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('66', '大江东公安分局', 'JGC1-6', '-0.05', '0.86', '-0.11', '0.29', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('67', '义逢体育中心', 'JGC2-1', '-0.3', '0.88', '2.14', '0.29', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('68', '义逢体育中心', 'JGC2-2', '-0.1', '-0.32', '0.1', '-0.11', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('69', '义逢体育中心', 'JGC2-3', '-0.4', '-0.86', '0.12', '-0.29', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('70', '义逢体育中心', 'JGC2-4', '-0.27', '-0.38', '0.67', '-0.13', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('71', '义逢体育中心', 'JGC2-5', '-0.42', '0.71', '1.28', '0.24', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('72', '义逢体育中心', 'JGC2-6', '-0.36', '0.83', '1.09', '0.28', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('73', '义逢体育中心', 'JGC2-7', '-0.15', '0.2', '0.75', '0.07', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('74', '义逢体育中心', 'JGC2-8', '0.1', '0.12', '1.9', '0.04', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('75', '义逢体育中心', 'JGC2-9', '0.1', '-0.35', '0.88', '-0.12', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('76', '大江东公安分局', 'JGC1-1', '0.82', '-0.15', '-1.97', '-0.05', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('77', '大江东公安分局', 'JGC1-2', '0.32', '-0.35', '-1.18', '-0.12', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('78', '大江东公安分局', 'JGC1-3', '0.16', '0.01', '0.13', '0', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('79', '大江东公安分局', 'JGC1-4', '-0.05', '-0.04', '0.68', '-0.01', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('80', '大江东公安分局', 'JGC1-5', '0.18', '0.12', '0.54', '0.04', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('81', '大江东公安分局', 'JGC1-6', '0.86', '-0.3', '-0.41', '-0.1', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('82', '义逢体育中心', 'JGC2-1', '0.88', '0.08', '2.22', '0.03', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('83', '义逢体育中心', 'JGC2-2', '-0.32', '0.02', '0.12', '0.01', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('84', '义逢体育中心', 'JGC2-3', '-0.86', '0.12', '0.24', '0.04', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('85', '义逢体育中心', 'JGC2-4', '-0.38', '-0.36', '0.31', '-0.12', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('86', '义逢体育中心', 'JGC2-5', '0.71', '-0.25', '1.03', '-0.08', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('87', '义逢体育中心', 'JGC2-6', '0.83', '0.04', '1.13', '0.01', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('88', '义逢体育中心', 'JGC2-7', '0.2', '-0.37', '0.38', '-0.12', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('89', '义逢体育中心', 'JGC2-8', '0.12', '-0.27', '1.63', '-0.09', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('90', '义逢体育中心', 'JGC2-9', '-0.35', '0.15', '1.03', '0.05', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('91', '大江东公安分局', 'JGC1-1', '-0.15', '-0.17', '-2.14', '-0.06', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('92', '大江东公安分局', 'JGC1-2', '-0.35', '-0.11', '-1.29', '-0.04', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('93', '大江东公安分局', 'JGC1-3', '0.01', '-0.23', '-0.1', '-0.08', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('94', '大江东公安分局', 'JGC1-4', '-0.04', '0.1', '0.78', '0.03', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('95', '大江东公安分局', 'JGC1-5', '0.12', '0.08', '0.62', '0.03', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('96', '大江东公安分局', 'JGC1-6', '-0.3', '-0.17', '-0.58', '-0.06', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('97', '义逢体育中心', 'JGC2-1', '0.08', '-0.36', '1.86', '-0.12', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('98', '义逢体育中心', 'JGC2-2', '0.02', '0.72', '0.84', '0.24', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('99', '义逢体育中心', 'JGC2-3', '0.12', '0.06', '0.3', '0.02', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('100', '义逢体育中心', 'JGC2-4', '-0.36', '0.52', '0.83', '0.17', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('101', '义逢体育中心', 'JGC2-5', '-0.25', '-0.5', '0.53', '-0.17', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('102', '义逢体育中心', 'JGC2-6', '0.04', '-0.44', '0.69', '-0.15', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('103', '义逢体育中心', 'JGC2-7', '-0.37', '-0.28', '0.1', '-0.09', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('104', '义逢体育中心', 'JGC2-8', '-0.27', '-0.29', '1.34', '-0.1', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('105', '义逢体育中心', 'JGC2-9', '0.15', '-0.01', '1.02', '-0', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('106', '大江东公安分局', 'JGC1-1', '-0.17', '-1.41', '-3.55', '-0.47', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('107', '大江东公安分局', 'JGC1-2', '-0.11', '-1.22', '-2.51', '-0.41', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('108', '大江东公安分局', 'JGC1-3', '-0.23', '-1.47', '-1.57', '-0.49', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('109', '大江东公安分局', 'JGC1-4', '0.1', '0.3', '1.08', '0.1', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('110', '大江东公安分局', 'JGC1-5', '0.08', '-1.76', '-1.14', '-0.59', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('111', '大江东公安分局', 'JGC1-6', '-0.17', '-1.18', '-1.76', '-0.39', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('112', '义逢体育中心', 'JGC2-1', '-0.36', '-0.13', '1.73', '-0.04', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('113', '义逢体育中心', 'JGC2-2', '0.72', '-1.87', '-1.03', '-0.62', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('114', '义逢体育中心', 'JGC2-3', '0.06', '0.94', '1.24', '0.31', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('115', '义逢体育中心', 'JGC2-4', '0.52', '0.83', '1.66', '0.28', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('116', '义逢体育中心', 'JGC2-5', '-0.5', '1.02', '1.55', '0.34', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('117', '义逢体育中心', 'JGC2-6', '-0.44', '0.59', '1.28', '0.2', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('118', '义逢体育中心', 'JGC2-7', '-0.28', '1.25', '1.35', '0.42', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('119', '义逢体育中心', 'JGC2-8', '-0.29', '0.22', '1.56', '0.07', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('120', '义逢体育中心', 'JGC2-9', '-0.01', '-0.05', '0.97', '-0.02', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('121', '基坑东侧', 'JGC1-1', '0.52', '0.72', '2.54', '0.24', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('122', '基坑东侧', 'JGC1-2', '0.07', '0.57', '1.88', '0.19', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('123', '基坑东侧', 'JGC1-3', '0.25', '0.49', '1.96', '0.16', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('124', '基坑东侧', 'JGC1-4', '-0.44', '0.17', '1.82', '0.06', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('125', '基坑东侧', 'JGC1-5', '-0.18', '-0.03', '2.03', '-0.01', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('126', '基坑东侧', 'JGC1-6', '-0.08', '-0.04', '1.82', '-0.01', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('127', '基坑东侧', 'JGC1-7', '0.12', '0.53', '2.21', '0.18', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('128', '基坑东侧', 'JGC1-8', '1.1', '0.47', '3.79', '0.16', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('129', '基坑东侧', 'JGC1-9', '0.9', '0.89', '3.78', '0.3', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('130', '基坑东侧', 'JGC1-10', '0.43', '0.66', '3.7', '0.22', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('131', '基坑东侧', 'JGC1-11', '0.45', '0.65', '2.07', '0.22', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('132', '基坑东侧', 'JGC1-12', '0.61', '0.56', '2.25', '0.19', '', '2018-12-03 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('133', '基坑东侧', 'JGC1-1', '0.72', '-0.12', '2.42', '-0.04', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('134', '基坑东侧', 'JGC1-2', '0.57', '-0.08', '1.8', '-0.03', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('135', '基坑东侧', 'JGC1-3', '0.49', '0.17', '2.13', '0.06', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('136', '基坑东侧', 'JGC1-4', '0.17', '0', '1.82', '0', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('137', '基坑东侧', 'JGC1-5', '-0.03', '-0.2', '1.83', '-0.07', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('138', '基坑东侧', 'JGC1-6', '-0.04', '-0.27', '1.55', '-0.09', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('139', '基坑东侧', 'JGC1-7', '0.53', '-0.81', '1.4', '-0.27', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('140', '基坑东侧', 'JGC1-8', '0.47', '-0.57', '3.22', '-0.19', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('141', '基坑东侧', 'JGC1-9', '0.89', '-0.14', '3.64', '-0.05', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('142', '基坑东侧', 'JGC1-10', '0.66', '-0.88', '2.82', '-0.29', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('143', '基坑东侧', 'JGC1-11', '0.65', '-0.18', '1.89', '-0.06', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('144', '基坑东侧', 'JGC1-12', '0.56', '-0.34', '1.91', '-0.11', '', '2018-12-06 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('145', '基坑东侧', 'JGC1-1', '-0.12', '-0.61', '1.81', '-0.2', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('146', '基坑东侧', 'JGC1-2', '-0.08', '-0.31', '1.49', '-0.1', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('147', '基坑东侧', 'JGC1-3', '0.17', '-0.4', '1.73', '-0.13', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('148', '基坑东侧', 'JGC1-4', '0', '-0.11', '1.71', '-0.04', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('149', '基坑东侧', 'JGC1-5', '-0.2', '0.04', '1.87', '0.01', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('150', '基坑东侧', 'JGC1-6', '-0.27', '-0.09', '1.46', '-0.03', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('151', '基坑东侧', 'JGC1-7', '-0.81', '0.09', '1.49', '0.03', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('152', '基坑东侧', 'JGC1-8', '-0.57', '-0.6', '2.62', '-0.2', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('153', '基坑东侧', 'JGC1-9', '-0.14', '-0.7', '2.94', '-0.23', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('154', '基坑东侧', 'JGC1-10', '-0.88', '-0.43', '2.39', '-0.14', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('155', '基坑东侧', 'JGC1-11', '-0.18', '-0.31', '1.58', '-0.1', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('156', '基坑东侧', 'JGC1-12', '-0.34', '-0.33', '1.58', '-0.11', '', '2018-12-10 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('157', '基坑东侧', 'JGC1-1', '-0.61', '-0.45', '1.36', '-0.15', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('158', '基坑东侧', 'JGC1-2', '-0.31', '-0.81', '0.68', '-0.27', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('159', '基坑东侧', 'JGC1-3', '-0.4', '-0.03', '1.7', '-0.01', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('160', '基坑东侧', 'JGC1-4', '-0.11', '0.08', '1.79', '0.03', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('161', '基坑东侧', 'JGC1-5', '0.04', '-0.54', '1.33', '-0.18', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('162', '基坑东侧', 'JGC1-6', '-0.09', '-0.42', '1.04', '-0.14', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('163', '基坑东侧', 'JGC1-7', '0.09', '0.17', '1.66', '0.06', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('164', '基坑东侧', 'JGC1-8', '-0.6', '-0.84', '1.78', '-0.28', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('165', '基坑东侧', 'JGC1-9', '-0.7', '-0.84', '2.1', '-0.28', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('166', '基坑东侧', 'JGC1-10', '-0.43', '-0.8', '1.59', '-0.27', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('167', '基坑东侧', 'JGC1-11', '-0.31', '-0.42', '1.16', '-0.14', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('168', '基坑东侧', 'JGC1-12', '-0.33', '-0.24', '1.34', '-0.08', '', '2018-12-13 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('169', '基坑东侧', 'JGC1-1', '-0.45', '-0.12', '1.24', '-0.04', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('170', '基坑东侧', 'JGC1-2', '-0.81', '0.41', '1.09', '0.14', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('171', '基坑东侧', 'JGC1-3', '-0.03', '-0.43', '1.27', '-0.14', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('172', '基坑东侧', 'JGC1-4', '0.08', '-0.37', '1.42', '-0.12', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('173', '基坑东侧', 'JGC1-5', '-0.54', '-0.23', '1.1', '-0.08', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('174', '基坑东侧', 'JGC1-6', '-0.42', '0.04', '1.08', '0.01', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('175', '基坑东侧', 'JGC1-7', '0.17', '0.08', '1.74', '0.03', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('176', '基坑东侧', 'JGC1-8', '-0.84', '0.53', '2.31', '0.18', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('177', '基坑东侧', 'JGC1-9', '-0.84', '-0.33', '1.77', '-0.11', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('178', '基坑东侧', 'JGC1-10', '-0.8', '-0.06', '1.53', '-0.02', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('179', '基坑东侧', 'JGC1-11', '-0.42', '-0.46', '0.7', '-0.15', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('180', '基坑东侧', 'JGC1-12', '-0.24', '-0.33', '1.01', '-0.11', '', '2018-12-17 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('181', '基坑东侧', 'JGC1-1', '-0.12', '0.09', '1.33', '0.03', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('182', '基坑东侧', 'JGC1-2', '0.41', '-0.76', '0.33', '-0.25', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('183', '基坑东侧', 'JGC1-3', '-0.43', '-0.54', '0.73', '-0.18', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('184', '基坑东侧', 'JGC1-4', '-0.37', '-0.46', '0.96', '-0.15', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('185', '基坑东侧', 'JGC1-5', '-0.23', '-0.65', '0.45', '-0.22', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('186', '基坑东侧', 'JGC1-6', '0.04', '-0.22', '0.86', '-0.07', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('187', '基坑东侧', 'JGC1-7', '0.08', '-0.46', '1.28', '-0.15', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('188', '基坑东侧', 'JGC1-8', '0.53', '-0.75', '1.56', '-0.25', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('189', '基坑东侧', 'JGC1-9', '-0.33', '-0.06', '1.71', '-0.02', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('190', '基坑东侧', 'JGC1-10', '-0.06', '-0.3', '1.23', '-0.1', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('191', '基坑东侧', 'JGC1-11', '-0.46', '-0.72', '-0.02', '-0.24', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('192', '基坑东侧', 'JGC1-12', '-0.33', '-0.04', '0.97', '-0.01', '', '2018-12-20 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('193', '基坑东侧', 'JGC1-1', '0.09', '-0.75', '0.58', '-0.25', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('194', '基坑东侧', 'JGC1-2', '-0.76', '-0.51', '-0.18', '-0.17', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('195', '基坑东侧', 'JGC1-3', '-0.54', '-0.24', '0.49', '-0.08', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('196', '基坑东侧', 'JGC1-4', '-0.46', '-0.58', '0.38', '-0.19', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('197', '基坑东侧', 'JGC1-5', '-0.65', '-0.64', '-0.19', '-0.21', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('198', '基坑东侧', 'JGC1-6', '-0.22', '0.17', '1.03', '0.06', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('199', '基坑东侧', 'JGC1-7', '-0.46', '-0.63', '0.65', '-0.21', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('200', '基坑东侧', 'JGC1-8', '-0.75', '-0.65', '0.91', '-0.22', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('201', '基坑东侧', 'JGC1-9', '-0.06', '-0.44', '1.27', '-0.15', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('202', '基坑东侧', 'JGC1-10', '-0.3', '-0.24', '0.99', '-0.08', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('203', '基坑东侧', 'JGC1-11', '-0.72', '-0.24', '-0.26', '-0.08', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('204', '基坑东侧', 'JGC1-12', '-0.04', '-0.37', '0.6', '-0.12', '', '2018-12-24 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('205', '基坑东侧', 'JGC1-1', '-0.75', '0.14', '0.72', '0.05', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('206', '基坑东侧', 'JGC1-2', '-0.51', '0.75', '0.57', '0.25', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('207', '基坑东侧', 'JGC1-3', '-0.24', '-0.38', '0.11', '-0.13', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('208', '基坑东侧', 'JGC1-4', '-0.58', '0.69', '1.07', '0.23', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('209', '基坑东侧', 'JGC1-5', '-0.64', '0.74', '0.55', '0.25', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('210', '基坑东侧', 'JGC1-6', '0.17', '0.66', '1.69', '0.22', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('211', '基坑东侧', 'JGC1-7', '-0.63', '0.71', '1.36', '0.24', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('212', '基坑东侧', 'JGC1-8', '-0.65', '1.05', '1.96', '0.35', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('213', '基坑东侧', 'JGC1-9', '-0.44', '0.49', '1.76', '0.16', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('214', '基坑东侧', 'JGC1-10', '-0.24', '0.78', '1.77', '0.26', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('215', '基坑东侧', 'JGC1-11', '-0.24', '0.31', '0.05', '0.1', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
INSERT INTO `settlement` VALUES ('216', '基坑东侧', 'JGC1-12', '-0.37', '-0.24', '0.36', '-0.08', '', '2018-12-27 00:00:01', 'Trinmble DiNi03', '732815', '2');
