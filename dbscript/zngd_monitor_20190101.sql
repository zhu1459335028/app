/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : zngd_monitor

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2019-01-01 13:04:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accessdevice
-- ----------------------------
DROP TABLE IF EXISTS `accessdevice`;
CREATE TABLE `accessdevice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accessdeviceid` varchar(255) NOT NULL COMMENT '设备id',
  `devicename` varchar(255) NOT NULL COMMENT '设备名',
  `outletid` int(11) NOT NULL COMMENT '所属工地，与ICSServerId设置方式一致',
  `manufacturer` varchar(255) NOT NULL DEFAULT '' COMMENT '厂家',
  `icsserverid` int(11) NOT NULL,
  `type` int(11) NOT NULL COMMENT '1.工地入口，2.工地出口，3.基坑入口，4.基坑出口',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `device_unique` (`outletid`,`accessdeviceid`) USING BTREE,
  KEY `normal_accessdevice` (`outletid`,`icsserverid`,`id`,`accessdeviceid`(32),`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accessdevice
-- ----------------------------
INSERT INTO `accessdevice` VALUES ('13', 'DS-2CD8627FXD-B20181117AACH225891559', '192.168.2.63-8000', '1', '', '9', '1', '');
INSERT INTO `accessdevice` VALUES ('20', 'DS-2CD8627FXD-B20181117AACH225891550', '192.168.18.121北区入口-8000', '2', '', '10', '1', '');
INSERT INTO `accessdevice` VALUES ('21', 'DS-2CD8627FXD-B20181117AACH225891559', '192.168.18.123南区入口-8000', '2', '', '10', '1', '');
INSERT INTO `accessdevice` VALUES ('22', 'DS-2CD8627FXD-B20181117AACH225891561', '192.168.18.122南区出口-8000', '2', '', '10', '2', '');
INSERT INTO `accessdevice` VALUES ('23', 'DS-2CD8627FXD-B20181119AACH226067604', '192.168.18.120北区出口-8000', '2', '', '10', '2', '');

-- ----------------------------
-- Table structure for accessdeviceemap
-- ----------------------------
DROP TABLE IF EXISTS `accessdeviceemap`;
CREATE TABLE `accessdeviceemap` (
  `accessdeviceid` int(11) NOT NULL,
  `emapId` int(11) NOT NULL,
  `coordinate` varchar(255) NOT NULL DEFAULT '',
  `direction` varchar(255) NOT NULL COMMENT ' 门禁进出方向',
  `remarks` varchar(1024) NOT NULL DEFAULT '',
  PRIMARY KEY (`accessdeviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accessdeviceemap
-- ----------------------------

-- ----------------------------
-- Table structure for aitask
-- ----------------------------
DROP TABLE IF EXISTS `aitask`;
CREATE TABLE `aitask` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `taskname` varchar(30) NOT NULL COMMENT '任务名称',
  `userid` int(11) NOT NULL COMMENT '关联的用户',
  `deviceno` int(11) NOT NULL COMMENT '设备id',
  `camerano` int(11) NOT NULL COMMENT '相机id',
  `worktime` varchar(255) DEFAULT NULL COMMENT '工作时间',
  `taskstatus` tinyint(4) NOT NULL DEFAULT '0' COMMENT '当前任务状态，enable为1时有效。1为启动，0为空闲',
  `enable` tinyint(4) NOT NULL COMMENT '任务启动标志，1为启动，0为关闭',
  `modelid` int(11) NOT NULL COMMENT '算法模型文件id',
  `checktype` tinyint(4) NOT NULL COMMENT '算法类型；2代表ai过滤，3代表实时检测',
  `maxsavetime` int(11) NOT NULL COMMENT '最大保留时间，以小时记',
  `similaritythreshold` tinyint(4) NOT NULL DEFAULT '50' COMMENT '相似度阈值，阈值越高，说明相似度越高，范围是0-100，默认值是50',
  `cfgfilename` varchar(255) NOT NULL DEFAULT '' COMMENT '实时任务算法配置文件名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `aitask_unique` (`camerano`,`modelid`,`checktype`) USING BTREE,
  KEY `aitask_key_normal` (`userid`,`deviceno`,`camerano`),
  KEY `aitask_id_normal` (`id`,`userid`,`camerano`,`modelid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aitask
-- ----------------------------
INSERT INTO `aitask` VALUES ('1', '安全帽违规检测_AI实时筛选通道1', '2', '1', '1', '[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]', '0', '1', '12000', '3', '3', '50', 'HardHat.xml');
INSERT INTO `aitask` VALUES ('2', '安全帽违规检测_AI实时筛选通道2', '2', '1', '2', '[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]', '0', '1', '12000', '3', '3', '50', 'HardHat.xml');
INSERT INTO `aitask` VALUES ('3', '安全帽违规检测_AI实时筛选通道3', '2', '1', '3', '[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]', '0', '1', '12000', '3', '3', '50', 'HardHat.xml');
INSERT INTO `aitask` VALUES ('4', '安全帽违规检测_AI实时筛选通道4', '2', '1', '4', '[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]', '0', '1', '12000', '3', '3', '50', 'HardHat.xml');
INSERT INTO `aitask` VALUES ('5', '安全帽违规检测_AI实时筛选通道5', '2', '1', '5', '[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]', '0', '1', '12000', '3', '3', '50', 'HardHat.xml');
INSERT INTO `aitask` VALUES ('6', '安全帽违规检测_AI实时筛选通道6', '2', '1', '6', '[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]', '0', '1', '12000', '3', '3', '50', 'HardHat.xml');
INSERT INTO `aitask` VALUES ('7', '安全帽违规检测_AI实时筛选通道7', '2', '1', '7', '[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]', '0', '1', '12000', '3', '3', '50', 'HardHat.xml');
INSERT INTO `aitask` VALUES ('8', '安全帽违规检测_AI实时筛选通道8', '2', '1', '8', '[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]', '0', '1', '12000', '3', '3', '50', 'HardHat.xml');

-- ----------------------------
-- Table structure for alarmevent
-- ----------------------------
DROP TABLE IF EXISTS `alarmevent`;
CREATE TABLE `alarmevent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `AlarmTime` datetime NOT NULL,
  `AckTime` datetime NOT NULL,
  `DeviceNo_Alarm` int(10) unsigned DEFAULT '0',
  `Input_Alarm` int(10) unsigned DEFAULT '0',
  `CameraNo_Trigger` int(10) unsigned DEFAULT '0',
  `DeviceNo_Trigger` int(10) unsigned DEFAULT '0',
  `OutputDeviceNo_Trigger` int(10) unsigned DEFAULT '0',
  `Output_Trigger` int(10) unsigned DEFAULT '0',
  `Description` varchar(256) DEFAULT '',
  `RealInfo` varchar(256) DEFAULT '',
  `AckComment` varchar(256) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of alarmevent
-- ----------------------------

-- ----------------------------
-- Table structure for algorithum
-- ----------------------------
DROP TABLE IF EXISTS `algorithum`;
CREATE TABLE `algorithum` (
  `serviceid` int(11) NOT NULL COMMENT '算法id\r\n',
  `cnname` varchar(255) NOT NULL COMMENT '中文名',
  `enname` varchar(255) NOT NULL COMMENT '英文名',
  `enable` int(1) NOT NULL DEFAULT '1' COMMENT '1为启用，0为禁用',
  PRIMARY KEY (`serviceid`),
  UNIQUE KEY `algcname` (`cnname`) USING HASH COMMENT '中文名不能重复',
  UNIQUE KEY `algenname` (`enname`) USING HASH COMMENT '英文名不能重复',
  KEY `algorithumnormal` (`serviceid`,`enable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of algorithum
-- ----------------------------
INSERT INTO `algorithum` VALUES ('12000', '安全帽违规检测', 'HardHat', '1');

-- ----------------------------
-- Table structure for appupdate
-- ----------------------------
DROP TABLE IF EXISTS `appupdate`;
CREATE TABLE `appupdate` (
  `apptype` tinyint(4) NOT NULL COMMENT '1为Android，2为ios',
  `version` varchar(255) NOT NULL COMMENT '版本号',
  `details` text NOT NULL COMMENT '更新详情',
  `forceupdate` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1为强制更新，0为非强制更新'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appupdate
-- ----------------------------

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brandname` varchar(64) NOT NULL,
  `brandlogo` varchar(256) NOT NULL,
  `description` varchar(256) NOT NULL,
  `parentid` int(11) NOT NULL,
  `schedule_time` varchar(1024) DEFAULT '',
  `layer` int(11) NOT NULL DEFAULT '0',
  `wait_time` int(11) NOT NULL DEFAULT '5',
  `Enabled` int(11) NOT NULL DEFAULT '0',
  `path` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `brandname` (`brandname`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1', '中铁隧道', '', '交通', '0', null, '0', '0', '0', '_1_');

-- ----------------------------
-- Table structure for brandcustomer
-- ----------------------------
DROP TABLE IF EXISTS `brandcustomer`;
CREATE TABLE `brandcustomer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brandid` int(11) NOT NULL,
  `customerid` int(11) NOT NULL,
  `layer` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `brandcustomerunique` (`brandid`,`customerid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brandcustomer
-- ----------------------------
INSERT INTO `brandcustomer` VALUES ('1', '1', '1', '0');
INSERT INTO `brandcustomer` VALUES ('2', '1', '2', '0');
INSERT INTO `brandcustomer` VALUES ('4', '1', '4', '0');
INSERT INTO `brandcustomer` VALUES ('5', '1', '5', '0');
INSERT INTO `brandcustomer` VALUES ('11', '1', '3', '0');
INSERT INTO `brandcustomer` VALUES ('12', '1', '6', '0');
INSERT INTO `brandcustomer` VALUES ('13', '1', '7', '0');

-- ----------------------------
-- Table structure for brandoutlet
-- ----------------------------
DROP TABLE IF EXISTS `brandoutlet`;
CREATE TABLE `brandoutlet` (
  `brandid` int(11) NOT NULL,
  `outletid` int(11) NOT NULL,
  KEY `brandid` (`brandid`,`outletid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brandoutlet
-- ----------------------------
INSERT INTO `brandoutlet` VALUES ('1', '1');
INSERT INTO `brandoutlet` VALUES ('1', '2');

-- ----------------------------
-- Table structure for branduser
-- ----------------------------
DROP TABLE IF EXISTS `branduser`;
CREATE TABLE `branduser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brandid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `layer` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `branduserunique` (`brandid`,`userid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of branduser
-- ----------------------------
INSERT INTO `branduser` VALUES ('4', '1', '2', '0');
INSERT INTO `branduser` VALUES ('7', '1', '5', '0');
INSERT INTO `branduser` VALUES ('8', '1', '3', '0');

-- ----------------------------
-- Table structure for camera
-- ----------------------------
DROP TABLE IF EXISTS `camera`;
CREATE TABLE `camera` (
  `CameraNo` int(11) NOT NULL AUTO_INCREMENT,
  `DeviceNo` int(11) unsigned NOT NULL,
  `ChanIndex` int(10) unsigned NOT NULL,
  `CameraName` varchar(256) DEFAULT NULL,
  `POS_X` int(10) unsigned DEFAULT '16',
  `POS_Y` int(10) unsigned DEFAULT '50',
  `POS_FontSize` int(10) unsigned DEFAULT '14',
  `POS_FontType` int(10) unsigned DEFAULT '1',
  `POS_FontColor` int(10) unsigned DEFAULT '1',
  `POS_FontBold` int(10) unsigned DEFAULT '1',
  `UseProfile` int(10) unsigned DEFAULT '0',
  `MainProfile` int(10) unsigned DEFAULT '0',
  `SubProfile` int(10) unsigned DEFAULT '0',
  `Enabled` int(10) unsigned NOT NULL DEFAULT '0',
  `CheckSD` int(10) unsigned DEFAULT '0',
  `CheckLowBattery` int(10) unsigned DEFAULT '0',
  `Authority` varchar(256) NOT NULL DEFAULT '0,0,0,0,0,0',
  `suspend` int(10) NOT NULL DEFAULT '0',
  `CameraType` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CameraNo`),
  UNIQUE KEY `CameraNo_UNIQUE` (`CameraNo`),
  KEY `cameraEnable` (`Enabled`,`CameraNo`,`DeviceNo`) USING BTREE,
  KEY `cameraDeviceNo` (`DeviceNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of camera
-- ----------------------------
INSERT INTO `camera` VALUES ('1', '1', '1', 'Camera1_杭临城铁隧道', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('2', '1', '2', 'Camera2_杭临城铁隧道', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('3', '1', '3', 'Camera3_杭临城铁隧道', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('4', '1', '4', 'Camera4_杭临城铁隧道', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('5', '1', '5', 'Camera5_杭临城铁隧道', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('6', '1', '6', 'Camera6_杭临城铁隧道', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('7', '1', '7', 'Camera7_杭临城铁隧道', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('8', '1', '8', 'Camera8_杭临城铁隧道', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('9', '2', '1', 'Camera1_地铁义蓬站1', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('10', '2', '2', 'Camera2_地铁义蓬站1', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('11', '2', '3', 'Camera3_地铁义蓬站1', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('12', '2', '4', 'Camera4_地铁义蓬站1', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('13', '3', '1', 'Camera1_地铁义蓬站2', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');
INSERT INTO `camera` VALUES ('16', '3', '4', 'Camera4_地铁义蓬站2', '16', '50', '14', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1,1,1,1,1,1', '0', '0');

-- ----------------------------
-- Table structure for cameraauthoritytypes
-- ----------------------------
DROP TABLE IF EXISTS `cameraauthoritytypes`;
CREATE TABLE `cameraauthoritytypes` (
  `AuthorityId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AuthorityName` varchar(256) NOT NULL,
  `AuthorityType` int(10) unsigned NOT NULL,
  PRIMARY KEY (`AuthorityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cameraauthoritytypes
-- ----------------------------

-- ----------------------------
-- Table structure for cameracomparepic
-- ----------------------------
DROP TABLE IF EXISTS `cameracomparepic`;
CREATE TABLE `cameracomparepic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cameraid` int(11) NOT NULL,
  `md5` varchar(64) NOT NULL,
  `picurl` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cameraid` (`cameraid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cameracomparepic
-- ----------------------------

-- ----------------------------
-- Table structure for cameraemap
-- ----------------------------
DROP TABLE IF EXISTS `cameraemap`;
CREATE TABLE `cameraemap` (
  `cameraid` int(11) NOT NULL COMMENT '该字段在表中唯一,与现有Camera表中ID一致',
  `emapid` int(11) NOT NULL,
  `coordinate` varchar(255) NOT NULL DEFAULT '' COMMENT '在上级地图中的坐标点: “h，w”，使用绝对值',
  `direction` varchar(255) NOT NULL DEFAULT '' COMMENT '相机在地图上的方向，第一版本只需支持8方向，数据库中取值使用与竖直向上方向的夹角角度，便于后续版本扩展；',
  `scopeangle` varchar(255) NOT NULL DEFAULT '' COMMENT '预览字段，后续版本中，用于标记相机在地图上的视野角度',
  `scopedistance` varchar(255) NOT NULL DEFAULT '' COMMENT '预览字段，后续版本中，用于标记相机在地图上的视野距离',
  PRIMARY KEY (`cameraid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cameraemap
-- ----------------------------
INSERT INTO `cameraemap` VALUES ('1', '3', '951.683199710846,398.33422304153447', '0', '', '');
INSERT INTO `cameraemap` VALUES ('2', '3', '1182.713199710846,600.4854730415344', '0', '', '');
INSERT INTO `cameraemap` VALUES ('3', '3', '752.740699710846,430.42172304153445', '0', '', '');
INSERT INTO `cameraemap` VALUES ('4', '3', '1882.220699710846,449.67422304153445', '0', '', '');
INSERT INTO `cameraemap` VALUES ('5', '3', '1134.581949710846,276.40172304153447', '0', '', '');
INSERT INTO `cameraemap` VALUES ('6', '3', '1750.661949710846,619.7379730415345', '0', '', '');
INSERT INTO `cameraemap` VALUES ('7', '3', '1587.015699710846,366.24672304153444', '0', '', '');
INSERT INTO `cameraemap` VALUES ('8', '3', '922.804449710846,600.4854730415344', '0', '', '');

-- ----------------------------
-- Table structure for cameragrouppair
-- ----------------------------
DROP TABLE IF EXISTS `cameragrouppair`;
CREATE TABLE `cameragrouppair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `GroupId` int(11) NOT NULL,
  `CameraNo` int(11) NOT NULL,
  `DeviceNo` int(11) NOT NULL,
  `IndexInGroup` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of cameragrouppair
-- ----------------------------

-- ----------------------------
-- Table structure for camerapos
-- ----------------------------
DROP TABLE IF EXISTS `camerapos`;
CREATE TABLE `camerapos` (
  `CameraNo` int(11) DEFAULT NULL,
  `PosSpecialId` int(11) DEFAULT NULL,
  `PosName` varchar(256) DEFAULT NULL,
  `PosType` int(11) DEFAULT NULL,
  `PosKeyword` varchar(256) DEFAULT NULL,
  `lastjobtime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of camerapos
-- ----------------------------

-- ----------------------------
-- Table structure for capturechanneltask
-- ----------------------------
DROP TABLE IF EXISTS `capturechanneltask`;
CREATE TABLE `capturechanneltask` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `deviceno` int(11) NOT NULL,
  `camerano` int(11) NOT NULL,
  `taskname` varchar(255) NOT NULL,
  `capturetime` varchar(512) DEFAULT NULL,
  `picquality` int(11) NOT NULL DEFAULT '0',
  `enable` int(11) NOT NULL DEFAULT '0',
  `maxsavetime` int(11) NOT NULL DEFAULT '48',
  `tasktype` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:远程抓图（即现有的切片任务）,1:相机自动推图',
  `ftpsvrid` int(11) NOT NULL DEFAULT '0' COMMENT '对应于ftpserver表中的id，默认为0，表示没有配置',
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_unqiue` (`taskid`,`camerano`) USING BTREE,
  KEY `capturetask_normal` (`userid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of capturechanneltask
-- ----------------------------
INSERT INTO `capturechanneltask` VALUES ('8', '1', '3', '1', '1', '杭临城铁隧道', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '1', '1', '72', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('9', '1', '3', '1', '2', '杭临城铁隧道', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '1', '1', '72', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('10', '1', '3', '1', '3', '杭临城铁隧道', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '1', '1', '72', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('11', '1', '3', '1', '4', '杭临城铁隧道', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '1', '1', '72', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('12', '1', '3', '1', '5', '杭临城铁隧道', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '1', '1', '72', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('13', '1', '3', '1', '6', '杭临城铁隧道', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '1', '1', '72', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('14', '1', '3', '1', '7', '杭临城铁隧道', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '1', '1', '72', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('15', '1', '3', '1', '8', '杭临城铁隧道', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '1', '1', '72', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('16', '2', '3', '2', '9', '地铁义蓬站1', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '0', '1', '24', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('17', '2', '3', '2', '10', '地铁义蓬站1', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '0', '1', '24', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('18', '2', '3', '2', '11', '地铁义蓬站1', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '0', '1', '24', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('19', '2', '3', '2', '12', '地铁义蓬站1', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '0', '1', '24', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('20', '3', '3', '3', '13', '地铁义蓬站2', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '0', '1', '24', '0', '0');
INSERT INTO `capturechanneltask` VALUES ('21', '3', '3', '3', '16', '地铁义蓬站2', '{\"timeType\":\"daily\",\"interval\":\"15\",\"timePeriods\":[{\"beginTime\":\"00:00:00\",\"endTime\":\"23:59:59\"}]}', '0', '1', '24', '0', '0');

-- ----------------------------
-- Table structure for captureconfig
-- ----------------------------
DROP TABLE IF EXISTS `captureconfig`;
CREATE TABLE `captureconfig` (
  `userid` int(11) NOT NULL,
  `savepath` varchar(256) NOT NULL,
  `autodelete` int(11) NOT NULL DEFAULT '0',
  `alarmlimit` int(11) NOT NULL DEFAULT '0',
  `autocapture` int(11) NOT NULL DEFAULT '1',
  `expiretime` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(255) DEFAULT NULL,
  `ip2` varchar(255) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  UNIQUE KEY `user_capture_unique` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of captureconfig
-- ----------------------------
INSERT INTO `captureconfig` VALUES ('3', '', '0', '1000', '1', '', '192.168.1.24', '122.233.188.41', '10003');

-- ----------------------------
-- Table structure for capturetask
-- ----------------------------
DROP TABLE IF EXISTS `capturetask`;
CREATE TABLE `capturetask` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `deviceno` int(11) NOT NULL,
  `camerano` int(11) NOT NULL,
  `taskname` varchar(64) NOT NULL,
  `enable` int(11) NOT NULL DEFAULT '0',
  `once` int(11) NOT NULL DEFAULT '0',
  `oncestarttime` varchar(32) NOT NULL DEFAULT '',
  `daily` int(11) NOT NULL DEFAULT '0',
  `dailystarttime` varchar(32) NOT NULL DEFAULT '',
  `dailyinterval` int(11) NOT NULL DEFAULT '0',
  `dailyduration` int(11) NOT NULL DEFAULT '0',
  `weekly` int(11) NOT NULL DEFAULT '0',
  `weekday` int(11) NOT NULL DEFAULT '0',
  `weekstarttime` varchar(32) NOT NULL DEFAULT '',
  `weekinterval` int(11) NOT NULL DEFAULT '0',
  `weekduration` int(11) NOT NULL DEFAULT '0',
  `monthly` int(11) NOT NULL DEFAULT '0',
  `monthday` int(11) NOT NULL DEFAULT '0',
  `monthstarttime` varchar(32) NOT NULL DEFAULT '',
  `monthinterval` int(11) NOT NULL DEFAULT '0',
  `monthduration` int(11) NOT NULL DEFAULT '0',
  `isautoupload` int(11) NOT NULL DEFAULT '0',
  `taskid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of capturetask
-- ----------------------------

-- ----------------------------
-- Table structure for capturetaskstatus
-- ----------------------------
DROP TABLE IF EXISTS `capturetaskstatus`;
CREATE TABLE `capturetaskstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskstatus` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of capturetaskstatus
-- ----------------------------

-- ----------------------------
-- Table structure for checkconfig
-- ----------------------------
DROP TABLE IF EXISTS `checkconfig`;
CREATE TABLE `checkconfig` (
  `userid` int(11) NOT NULL,
  `savepath` varchar(256) NOT NULL,
  `autodelete` int(11) NOT NULL DEFAULT '0',
  `alarmlimit` int(11) NOT NULL DEFAULT '0',
  `autocheck` int(11) NOT NULL DEFAULT '0',
  `checktype` int(11) NOT NULL DEFAULT '0',
  `sourcepath` varchar(255) NOT NULL DEFAULT '',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `srcpicsavetime` int(11) NOT NULL DEFAULT '3',
  `parallelnums` int(11) NOT NULL DEFAULT '1',
  `ip` varchar(255) NOT NULL DEFAULT '',
  `ip2` varchar(255) NOT NULL DEFAULT '',
  `port` int(11) NOT NULL DEFAULT '8000',
  `worktime` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_check_unique` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkconfig
-- ----------------------------
INSERT INTO `checkconfig` VALUES ('2', '', '0', '0', '0', '0', '', '1', '2', '5', '192.168.1.24', '122.233.188.41', '10002', '00:00:00-23:59:59');

-- ----------------------------
-- Table structure for checking_1
-- ----------------------------
DROP TABLE IF EXISTS `checking_1`;
CREATE TABLE `checking_1` (
  `checkid` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `deviceid` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `imageurl` varchar(254) DEFAULT '',
  PRIMARY KEY (`checkid`,`time`) USING BTREE,
  KEY `time` (`time`) USING BTREE,
  KEY `cardid` (`cardid`) USING HASH,
  KEY `deviceid` (`deviceid`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (to_days(time))
(PARTITION d201810 VALUES LESS THAN (737333) ENGINE = InnoDB,
 PARTITION d201811 VALUES LESS THAN (737364) ENGINE = InnoDB,
 PARTITION d201812 VALUES LESS THAN (737394) ENGINE = InnoDB,
 PARTITION d201901 VALUES LESS THAN (737425) ENGINE = InnoDB,
 PARTITION d201902 VALUES LESS THAN (737456) ENGINE = InnoDB,
 PARTITION d201903 VALUES LESS THAN (737484) ENGINE = InnoDB,
 PARTITION d201904 VALUES LESS THAN (737515) ENGINE = InnoDB,
 PARTITION d201905 VALUES LESS THAN (737545) ENGINE = InnoDB,
 PARTITION d201906 VALUES LESS THAN (737576) ENGINE = InnoDB,
 PARTITION d201907 VALUES LESS THAN (737606) ENGINE = InnoDB,
 PARTITION d201908 VALUES LESS THAN (737637) ENGINE = InnoDB,
 PARTITION d201909 VALUES LESS THAN (737668) ENGINE = InnoDB,
 PARTITION d201910 VALUES LESS THAN (737698) ENGINE = InnoDB,
 PARTITION d201911 VALUES LESS THAN (737729) ENGINE = InnoDB,
 PARTITION d201912 VALUES LESS THAN (737759) ENGINE = InnoDB,
 PARTITION d202001 VALUES LESS THAN (737790) ENGINE = InnoDB,
 PARTITION d202002 VALUES LESS THAN (737821) ENGINE = InnoDB,
 PARTITION d202003 VALUES LESS THAN (737850) ENGINE = InnoDB,
 PARTITION d202004 VALUES LESS THAN (737881) ENGINE = InnoDB,
 PARTITION d202005 VALUES LESS THAN (737911) ENGINE = InnoDB,
 PARTITION d202006 VALUES LESS THAN (737942) ENGINE = InnoDB,
 PARTITION d202007 VALUES LESS THAN (737972) ENGINE = InnoDB,
 PARTITION d202008 VALUES LESS THAN (738003) ENGINE = InnoDB,
 PARTITION d202009 VALUES LESS THAN (738034) ENGINE = InnoDB,
 PARTITION d202010 VALUES LESS THAN (738064) ENGINE = InnoDB,
 PARTITION d202011 VALUES LESS THAN (738095) ENGINE = InnoDB,
 PARTITION d202012 VALUES LESS THAN (738125) ENGINE = InnoDB,
 PARTITION d202101 VALUES LESS THAN (738156) ENGINE = InnoDB,
 PARTITION d202102 VALUES LESS THAN (738187) ENGINE = InnoDB,
 PARTITION d202103 VALUES LESS THAN (738215) ENGINE = InnoDB,
 PARTITION d202104 VALUES LESS THAN (738246) ENGINE = InnoDB,
 PARTITION d202105 VALUES LESS THAN (738276) ENGINE = InnoDB,
 PARTITION d202106 VALUES LESS THAN (738307) ENGINE = InnoDB,
 PARTITION d202107 VALUES LESS THAN (738337) ENGINE = InnoDB,
 PARTITION d202108 VALUES LESS THAN (738368) ENGINE = InnoDB,
 PARTITION d202109 VALUES LESS THAN (738399) ENGINE = InnoDB,
 PARTITION d202110 VALUES LESS THAN (738429) ENGINE = InnoDB,
 PARTITION d202111 VALUES LESS THAN (738460) ENGINE = InnoDB,
 PARTITION d202112 VALUES LESS THAN (738490) ENGINE = InnoDB,
 PARTITION d202201 VALUES LESS THAN (738521) ENGINE = InnoDB,
 PARTITION d202202 VALUES LESS THAN (738552) ENGINE = InnoDB,
 PARTITION d202203 VALUES LESS THAN (738580) ENGINE = InnoDB,
 PARTITION d202204 VALUES LESS THAN (738611) ENGINE = InnoDB,
 PARTITION d202205 VALUES LESS THAN (738641) ENGINE = InnoDB,
 PARTITION d202206 VALUES LESS THAN (738672) ENGINE = InnoDB,
 PARTITION d202207 VALUES LESS THAN (738702) ENGINE = InnoDB,
 PARTITION d202208 VALUES LESS THAN (738733) ENGINE = InnoDB,
 PARTITION d202209 VALUES LESS THAN (738764) ENGINE = InnoDB,
 PARTITION d202210 VALUES LESS THAN (738794) ENGINE = InnoDB,
 PARTITION d202211 VALUES LESS THAN (738825) ENGINE = InnoDB,
 PARTITION d202212 VALUES LESS THAN (738855) ENGINE = InnoDB,
 PARTITION d202301 VALUES LESS THAN (738886) ENGINE = InnoDB,
 PARTITION d202302 VALUES LESS THAN (738917) ENGINE = InnoDB,
 PARTITION d202303 VALUES LESS THAN (738945) ENGINE = InnoDB,
 PARTITION d202304 VALUES LESS THAN (738976) ENGINE = InnoDB,
 PARTITION d202305 VALUES LESS THAN (739006) ENGINE = InnoDB,
 PARTITION d202306 VALUES LESS THAN (739037) ENGINE = InnoDB,
 PARTITION d202307 VALUES LESS THAN (739067) ENGINE = InnoDB,
 PARTITION d202308 VALUES LESS THAN (739098) ENGINE = InnoDB,
 PARTITION d202309 VALUES LESS THAN (739129) ENGINE = InnoDB) */;

-- ----------------------------
-- Records of checking_1
-- ----------------------------
INSERT INTO `checking_1` VALUES ('17', '0002', '2018-09-26 13:47:27', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('1', '0001', '2018-10-17 10:24:24', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('2', '0001', '2018-10-17 10:41:45', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('3', '0001', '2018-10-17 10:42:59', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('4', '0001', '2018-10-17 10:54:53', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('5', '0001', '2018-10-17 11:10:17', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('6', '0001', '2018-10-17 11:10:19', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('7', '0001', '2018-10-17 11:10:50', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('8', '0001', '2018-10-17 13:16:55', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('9', '0001', '2018-10-21 12:22:50', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('10', '0001', '2018-10-21 12:22:58', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('11', '0001', '2018-10-21 12:29:11', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('12', '0001', '2018-10-21 12:30:12', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('13', '0001', '2018-10-21 12:30:17', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('14', '0001', '2018-10-21 13:38:23', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('15', '0001', '2018-10-21 14:15:04', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('16', '0002', '2018-10-21 14:33:25', 'DS-K5603-H20180508V010001CHC47700946', '2', '');
INSERT INTO `checking_1` VALUES ('18', '11111111', '2018-12-17 16:16:40', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');
INSERT INTO `checking_1` VALUES ('19', '11111111', '2018-12-17 16:18:47', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');
INSERT INTO `checking_1` VALUES ('20', '11111111', '2018-12-17 16:19:38', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');
INSERT INTO `checking_1` VALUES ('21', '11111111', '2018-12-17 16:22:06', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');
INSERT INTO `checking_1` VALUES ('22', '11111111', '2018-12-17 16:22:53', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');
INSERT INTO `checking_1` VALUES ('23', '11111111', '2018-12-17 16:26:08', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');
INSERT INTO `checking_1` VALUES ('24', '11111111', '2018-12-17 16:26:50', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');
INSERT INTO `checking_1` VALUES ('25', '11111111', '2018-12-17 16:29:06', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');
INSERT INTO `checking_1` VALUES ('26', '11111111', '2018-12-17 16:29:45', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');
INSERT INTO `checking_1` VALUES ('27', '11111111', '2018-12-17 16:29:51', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');
INSERT INTO `checking_1` VALUES ('28', '11111111', '2018-12-17 16:29:56', 'DS-2CD8627FXD-B20181117AACH225891559', '2', '');

-- ----------------------------
-- Table structure for checking_17
-- ----------------------------
DROP TABLE IF EXISTS `checking_17`;
CREATE TABLE `checking_17` (
  `checkid` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `deviceid` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `imageurl` varchar(255) DEFAULT '',
  PRIMARY KEY (`checkid`,`time`) USING BTREE,
  KEY `time` (`time`) USING BTREE,
  KEY `cardid` (`cardid`) USING HASH,
  KEY `deviceid` (`deviceid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (to_days(time))
(PARTITION d201812 VALUES LESS THAN (737394) ENGINE = InnoDB,
 PARTITION d201901 VALUES LESS THAN (737425) ENGINE = InnoDB,
 PARTITION d201902 VALUES LESS THAN (737456) ENGINE = InnoDB,
 PARTITION d201903 VALUES LESS THAN (737484) ENGINE = InnoDB,
 PARTITION d201904 VALUES LESS THAN (737515) ENGINE = InnoDB,
 PARTITION d201905 VALUES LESS THAN (737545) ENGINE = InnoDB,
 PARTITION d201906 VALUES LESS THAN (737576) ENGINE = InnoDB,
 PARTITION d201907 VALUES LESS THAN (737606) ENGINE = InnoDB,
 PARTITION d201908 VALUES LESS THAN (737637) ENGINE = InnoDB,
 PARTITION d201909 VALUES LESS THAN (737668) ENGINE = InnoDB,
 PARTITION d201910 VALUES LESS THAN (737698) ENGINE = InnoDB,
 PARTITION d201911 VALUES LESS THAN (737729) ENGINE = InnoDB,
 PARTITION d201912 VALUES LESS THAN (737759) ENGINE = InnoDB,
 PARTITION d202001 VALUES LESS THAN (737790) ENGINE = InnoDB,
 PARTITION d202002 VALUES LESS THAN (737821) ENGINE = InnoDB,
 PARTITION d202003 VALUES LESS THAN (737850) ENGINE = InnoDB,
 PARTITION d202004 VALUES LESS THAN (737881) ENGINE = InnoDB,
 PARTITION d202005 VALUES LESS THAN (737911) ENGINE = InnoDB,
 PARTITION d202006 VALUES LESS THAN (737942) ENGINE = InnoDB,
 PARTITION d202007 VALUES LESS THAN (737972) ENGINE = InnoDB,
 PARTITION d202008 VALUES LESS THAN (738003) ENGINE = InnoDB,
 PARTITION d202009 VALUES LESS THAN (738034) ENGINE = InnoDB,
 PARTITION d202010 VALUES LESS THAN (738064) ENGINE = InnoDB,
 PARTITION d202011 VALUES LESS THAN (738095) ENGINE = InnoDB,
 PARTITION d202012 VALUES LESS THAN (738125) ENGINE = InnoDB,
 PARTITION d202101 VALUES LESS THAN (738156) ENGINE = InnoDB,
 PARTITION d202102 VALUES LESS THAN (738187) ENGINE = InnoDB,
 PARTITION d202103 VALUES LESS THAN (738215) ENGINE = InnoDB,
 PARTITION d202104 VALUES LESS THAN (738246) ENGINE = InnoDB,
 PARTITION d202105 VALUES LESS THAN (738276) ENGINE = InnoDB,
 PARTITION d202106 VALUES LESS THAN (738307) ENGINE = InnoDB,
 PARTITION d202107 VALUES LESS THAN (738337) ENGINE = InnoDB,
 PARTITION d202108 VALUES LESS THAN (738368) ENGINE = InnoDB,
 PARTITION d202109 VALUES LESS THAN (738399) ENGINE = InnoDB,
 PARTITION d202110 VALUES LESS THAN (738429) ENGINE = InnoDB,
 PARTITION d202111 VALUES LESS THAN (738460) ENGINE = InnoDB,
 PARTITION d202112 VALUES LESS THAN (738490) ENGINE = InnoDB,
 PARTITION d202201 VALUES LESS THAN (738521) ENGINE = InnoDB,
 PARTITION d202202 VALUES LESS THAN (738552) ENGINE = InnoDB,
 PARTITION d202203 VALUES LESS THAN (738580) ENGINE = InnoDB,
 PARTITION d202204 VALUES LESS THAN (738611) ENGINE = InnoDB,
 PARTITION d202205 VALUES LESS THAN (738641) ENGINE = InnoDB,
 PARTITION d202206 VALUES LESS THAN (738672) ENGINE = InnoDB,
 PARTITION d202207 VALUES LESS THAN (738702) ENGINE = InnoDB,
 PARTITION d202208 VALUES LESS THAN (738733) ENGINE = InnoDB,
 PARTITION d202209 VALUES LESS THAN (738764) ENGINE = InnoDB,
 PARTITION d202210 VALUES LESS THAN (738794) ENGINE = InnoDB,
 PARTITION d202211 VALUES LESS THAN (738825) ENGINE = InnoDB,
 PARTITION d202212 VALUES LESS THAN (738855) ENGINE = InnoDB,
 PARTITION d202301 VALUES LESS THAN (738886) ENGINE = InnoDB,
 PARTITION d202302 VALUES LESS THAN (738917) ENGINE = InnoDB,
 PARTITION d202303 VALUES LESS THAN (738945) ENGINE = InnoDB,
 PARTITION d202304 VALUES LESS THAN (738976) ENGINE = InnoDB,
 PARTITION d202305 VALUES LESS THAN (739006) ENGINE = InnoDB,
 PARTITION d202306 VALUES LESS THAN (739037) ENGINE = InnoDB,
 PARTITION d202307 VALUES LESS THAN (739067) ENGINE = InnoDB,
 PARTITION d202308 VALUES LESS THAN (739098) ENGINE = InnoDB,
 PARTITION d202309 VALUES LESS THAN (739129) ENGINE = InnoDB,
 PARTITION d202310 VALUES LESS THAN (739159) ENGINE = InnoDB,
 PARTITION d202311 VALUES LESS THAN (739190) ENGINE = InnoDB) */;

-- ----------------------------
-- Records of checking_17
-- ----------------------------

-- ----------------------------
-- Table structure for checking_2
-- ----------------------------
DROP TABLE IF EXISTS `checking_2`;
CREATE TABLE `checking_2` (
  `checkid` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `deviceid` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `imageurl` varchar(255) DEFAULT '',
  PRIMARY KEY (`checkid`,`time`) USING BTREE,
  KEY `time` (`time`) USING BTREE,
  KEY `cardid` (`cardid`) USING HASH,
  KEY `deviceid` (`deviceid`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (to_days(time))
(PARTITION d201812 VALUES LESS THAN (737394) ENGINE = InnoDB,
 PARTITION d201901 VALUES LESS THAN (737425) ENGINE = InnoDB,
 PARTITION d201902 VALUES LESS THAN (737456) ENGINE = InnoDB,
 PARTITION d201903 VALUES LESS THAN (737484) ENGINE = InnoDB,
 PARTITION d201904 VALUES LESS THAN (737515) ENGINE = InnoDB,
 PARTITION d201905 VALUES LESS THAN (737545) ENGINE = InnoDB,
 PARTITION d201906 VALUES LESS THAN (737576) ENGINE = InnoDB,
 PARTITION d201907 VALUES LESS THAN (737606) ENGINE = InnoDB,
 PARTITION d201908 VALUES LESS THAN (737637) ENGINE = InnoDB,
 PARTITION d201909 VALUES LESS THAN (737668) ENGINE = InnoDB,
 PARTITION d201910 VALUES LESS THAN (737698) ENGINE = InnoDB,
 PARTITION d201911 VALUES LESS THAN (737729) ENGINE = InnoDB,
 PARTITION d201912 VALUES LESS THAN (737759) ENGINE = InnoDB,
 PARTITION d202001 VALUES LESS THAN (737790) ENGINE = InnoDB,
 PARTITION d202002 VALUES LESS THAN (737821) ENGINE = InnoDB,
 PARTITION d202003 VALUES LESS THAN (737850) ENGINE = InnoDB,
 PARTITION d202004 VALUES LESS THAN (737881) ENGINE = InnoDB,
 PARTITION d202005 VALUES LESS THAN (737911) ENGINE = InnoDB,
 PARTITION d202006 VALUES LESS THAN (737942) ENGINE = InnoDB,
 PARTITION d202007 VALUES LESS THAN (737972) ENGINE = InnoDB,
 PARTITION d202008 VALUES LESS THAN (738003) ENGINE = InnoDB,
 PARTITION d202009 VALUES LESS THAN (738034) ENGINE = InnoDB,
 PARTITION d202010 VALUES LESS THAN (738064) ENGINE = InnoDB,
 PARTITION d202011 VALUES LESS THAN (738095) ENGINE = InnoDB,
 PARTITION d202012 VALUES LESS THAN (738125) ENGINE = InnoDB,
 PARTITION d202101 VALUES LESS THAN (738156) ENGINE = InnoDB,
 PARTITION d202102 VALUES LESS THAN (738187) ENGINE = InnoDB,
 PARTITION d202103 VALUES LESS THAN (738215) ENGINE = InnoDB,
 PARTITION d202104 VALUES LESS THAN (738246) ENGINE = InnoDB,
 PARTITION d202105 VALUES LESS THAN (738276) ENGINE = InnoDB,
 PARTITION d202106 VALUES LESS THAN (738307) ENGINE = InnoDB,
 PARTITION d202107 VALUES LESS THAN (738337) ENGINE = InnoDB,
 PARTITION d202108 VALUES LESS THAN (738368) ENGINE = InnoDB,
 PARTITION d202109 VALUES LESS THAN (738399) ENGINE = InnoDB,
 PARTITION d202110 VALUES LESS THAN (738429) ENGINE = InnoDB,
 PARTITION d202111 VALUES LESS THAN (738460) ENGINE = InnoDB,
 PARTITION d202112 VALUES LESS THAN (738490) ENGINE = InnoDB,
 PARTITION d202201 VALUES LESS THAN (738521) ENGINE = InnoDB,
 PARTITION d202202 VALUES LESS THAN (738552) ENGINE = InnoDB,
 PARTITION d202203 VALUES LESS THAN (738580) ENGINE = InnoDB,
 PARTITION d202204 VALUES LESS THAN (738611) ENGINE = InnoDB,
 PARTITION d202205 VALUES LESS THAN (738641) ENGINE = InnoDB,
 PARTITION d202206 VALUES LESS THAN (738672) ENGINE = InnoDB,
 PARTITION d202207 VALUES LESS THAN (738702) ENGINE = InnoDB,
 PARTITION d202208 VALUES LESS THAN (738733) ENGINE = InnoDB,
 PARTITION d202209 VALUES LESS THAN (738764) ENGINE = InnoDB,
 PARTITION d202210 VALUES LESS THAN (738794) ENGINE = InnoDB,
 PARTITION d202211 VALUES LESS THAN (738825) ENGINE = InnoDB,
 PARTITION d202212 VALUES LESS THAN (738855) ENGINE = InnoDB,
 PARTITION d202301 VALUES LESS THAN (738886) ENGINE = InnoDB,
 PARTITION d202302 VALUES LESS THAN (738917) ENGINE = InnoDB,
 PARTITION d202303 VALUES LESS THAN (738945) ENGINE = InnoDB,
 PARTITION d202304 VALUES LESS THAN (738976) ENGINE = InnoDB,
 PARTITION d202305 VALUES LESS THAN (739006) ENGINE = InnoDB,
 PARTITION d202306 VALUES LESS THAN (739037) ENGINE = InnoDB,
 PARTITION d202307 VALUES LESS THAN (739067) ENGINE = InnoDB,
 PARTITION d202308 VALUES LESS THAN (739098) ENGINE = InnoDB,
 PARTITION d202309 VALUES LESS THAN (739129) ENGINE = InnoDB,
 PARTITION d202310 VALUES LESS THAN (739159) ENGINE = InnoDB,
 PARTITION d202311 VALUES LESS THAN (739190) ENGINE = InnoDB) */;

-- ----------------------------
-- Records of checking_2
-- ----------------------------
INSERT INTO `checking_2` VALUES ('1', '00000027', '2018-12-20 16:03:47', 'DS-2CD8627FXD-B20181117AACH225891550', '2', '/resource/getImage/20181220/f427a0e6-3803-4c4b-bcd3-37a80bcbeda5.png');

-- ----------------------------
-- Table structure for checking_22
-- ----------------------------
DROP TABLE IF EXISTS `checking_22`;
CREATE TABLE `checking_22` (
  `checkid` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `deviceid` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `imageurl` varchar(254) DEFAULT '',
  PRIMARY KEY (`checkid`,`time`) USING BTREE,
  KEY `time` (`time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (to_days(time))
(PARTITION d201809 VALUES LESS THAN (737303) ENGINE = InnoDB,
 PARTITION d201810 VALUES LESS THAN (737333) ENGINE = InnoDB,
 PARTITION d201811 VALUES LESS THAN (737364) ENGINE = InnoDB,
 PARTITION d201812 VALUES LESS THAN (737394) ENGINE = InnoDB,
 PARTITION d201901 VALUES LESS THAN (737425) ENGINE = InnoDB,
 PARTITION d201902 VALUES LESS THAN (737456) ENGINE = InnoDB,
 PARTITION d201903 VALUES LESS THAN (737484) ENGINE = InnoDB,
 PARTITION d201904 VALUES LESS THAN (737515) ENGINE = InnoDB,
 PARTITION d201905 VALUES LESS THAN (737545) ENGINE = InnoDB,
 PARTITION d201906 VALUES LESS THAN (737576) ENGINE = InnoDB,
 PARTITION d201907 VALUES LESS THAN (737606) ENGINE = InnoDB,
 PARTITION d201908 VALUES LESS THAN (737637) ENGINE = InnoDB,
 PARTITION d201909 VALUES LESS THAN (737668) ENGINE = InnoDB,
 PARTITION d201910 VALUES LESS THAN (737698) ENGINE = InnoDB,
 PARTITION d201911 VALUES LESS THAN (737729) ENGINE = InnoDB,
 PARTITION d201912 VALUES LESS THAN (737759) ENGINE = InnoDB,
 PARTITION d202001 VALUES LESS THAN (737790) ENGINE = InnoDB,
 PARTITION d202002 VALUES LESS THAN (737821) ENGINE = InnoDB,
 PARTITION d202003 VALUES LESS THAN (737850) ENGINE = InnoDB,
 PARTITION d202004 VALUES LESS THAN (737881) ENGINE = InnoDB,
 PARTITION d202005 VALUES LESS THAN (737911) ENGINE = InnoDB,
 PARTITION d202006 VALUES LESS THAN (737942) ENGINE = InnoDB,
 PARTITION d202007 VALUES LESS THAN (737972) ENGINE = InnoDB,
 PARTITION d202008 VALUES LESS THAN (738003) ENGINE = InnoDB,
 PARTITION d202009 VALUES LESS THAN (738034) ENGINE = InnoDB,
 PARTITION d202010 VALUES LESS THAN (738064) ENGINE = InnoDB,
 PARTITION d202011 VALUES LESS THAN (738095) ENGINE = InnoDB,
 PARTITION d202012 VALUES LESS THAN (738125) ENGINE = InnoDB,
 PARTITION d202101 VALUES LESS THAN (738156) ENGINE = InnoDB,
 PARTITION d202102 VALUES LESS THAN (738187) ENGINE = InnoDB,
 PARTITION d202103 VALUES LESS THAN (738215) ENGINE = InnoDB,
 PARTITION d202104 VALUES LESS THAN (738246) ENGINE = InnoDB,
 PARTITION d202105 VALUES LESS THAN (738276) ENGINE = InnoDB,
 PARTITION d202106 VALUES LESS THAN (738307) ENGINE = InnoDB,
 PARTITION d202107 VALUES LESS THAN (738337) ENGINE = InnoDB,
 PARTITION d202108 VALUES LESS THAN (738368) ENGINE = InnoDB,
 PARTITION d202109 VALUES LESS THAN (738399) ENGINE = InnoDB,
 PARTITION d202110 VALUES LESS THAN (738429) ENGINE = InnoDB,
 PARTITION d202111 VALUES LESS THAN (738460) ENGINE = InnoDB,
 PARTITION d202112 VALUES LESS THAN (738490) ENGINE = InnoDB,
 PARTITION d202201 VALUES LESS THAN (738521) ENGINE = InnoDB,
 PARTITION d202202 VALUES LESS THAN (738552) ENGINE = InnoDB,
 PARTITION d202203 VALUES LESS THAN (738580) ENGINE = InnoDB,
 PARTITION d202204 VALUES LESS THAN (738611) ENGINE = InnoDB,
 PARTITION d202205 VALUES LESS THAN (738641) ENGINE = InnoDB,
 PARTITION d202206 VALUES LESS THAN (738672) ENGINE = InnoDB,
 PARTITION d202207 VALUES LESS THAN (738702) ENGINE = InnoDB,
 PARTITION d202208 VALUES LESS THAN (738733) ENGINE = InnoDB,
 PARTITION d202209 VALUES LESS THAN (738764) ENGINE = InnoDB,
 PARTITION d202210 VALUES LESS THAN (738794) ENGINE = InnoDB,
 PARTITION d202211 VALUES LESS THAN (738825) ENGINE = InnoDB,
 PARTITION d202212 VALUES LESS THAN (738855) ENGINE = InnoDB,
 PARTITION d202301 VALUES LESS THAN (738886) ENGINE = InnoDB,
 PARTITION d202302 VALUES LESS THAN (738917) ENGINE = InnoDB,
 PARTITION d202303 VALUES LESS THAN (738945) ENGINE = InnoDB,
 PARTITION d202304 VALUES LESS THAN (738976) ENGINE = InnoDB,
 PARTITION d202305 VALUES LESS THAN (739006) ENGINE = InnoDB,
 PARTITION d202306 VALUES LESS THAN (739037) ENGINE = InnoDB,
 PARTITION d202307 VALUES LESS THAN (739067) ENGINE = InnoDB,
 PARTITION d202308 VALUES LESS THAN (739098) ENGINE = InnoDB) */;

-- ----------------------------
-- Records of checking_22
-- ----------------------------

-- ----------------------------
-- Table structure for checking_23
-- ----------------------------
DROP TABLE IF EXISTS `checking_23`;
CREATE TABLE `checking_23` (
  `checkid` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `deviceid` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `imageurl` varchar(254) DEFAULT '',
  PRIMARY KEY (`checkid`,`time`) USING BTREE,
  KEY `time` (`time`) USING BTREE,
  KEY `cardid` (`cardid`) USING HASH,
  KEY `deviceid` (`deviceid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (to_days(time))
(PARTITION d201809 VALUES LESS THAN (737303) ENGINE = InnoDB,
 PARTITION d201810 VALUES LESS THAN (737333) ENGINE = InnoDB,
 PARTITION d201811 VALUES LESS THAN (737364) ENGINE = InnoDB,
 PARTITION d201812 VALUES LESS THAN (737394) ENGINE = InnoDB,
 PARTITION d201901 VALUES LESS THAN (737425) ENGINE = InnoDB,
 PARTITION d201902 VALUES LESS THAN (737456) ENGINE = InnoDB,
 PARTITION d201903 VALUES LESS THAN (737484) ENGINE = InnoDB,
 PARTITION d201904 VALUES LESS THAN (737515) ENGINE = InnoDB,
 PARTITION d201905 VALUES LESS THAN (737545) ENGINE = InnoDB,
 PARTITION d201906 VALUES LESS THAN (737576) ENGINE = InnoDB,
 PARTITION d201907 VALUES LESS THAN (737606) ENGINE = InnoDB,
 PARTITION d201908 VALUES LESS THAN (737637) ENGINE = InnoDB,
 PARTITION d201909 VALUES LESS THAN (737668) ENGINE = InnoDB,
 PARTITION d201910 VALUES LESS THAN (737698) ENGINE = InnoDB,
 PARTITION d201911 VALUES LESS THAN (737729) ENGINE = InnoDB,
 PARTITION d201912 VALUES LESS THAN (737759) ENGINE = InnoDB,
 PARTITION d202001 VALUES LESS THAN (737790) ENGINE = InnoDB,
 PARTITION d202002 VALUES LESS THAN (737821) ENGINE = InnoDB,
 PARTITION d202003 VALUES LESS THAN (737850) ENGINE = InnoDB,
 PARTITION d202004 VALUES LESS THAN (737881) ENGINE = InnoDB,
 PARTITION d202005 VALUES LESS THAN (737911) ENGINE = InnoDB,
 PARTITION d202006 VALUES LESS THAN (737942) ENGINE = InnoDB,
 PARTITION d202007 VALUES LESS THAN (737972) ENGINE = InnoDB,
 PARTITION d202008 VALUES LESS THAN (738003) ENGINE = InnoDB,
 PARTITION d202009 VALUES LESS THAN (738034) ENGINE = InnoDB,
 PARTITION d202010 VALUES LESS THAN (738064) ENGINE = InnoDB,
 PARTITION d202011 VALUES LESS THAN (738095) ENGINE = InnoDB,
 PARTITION d202012 VALUES LESS THAN (738125) ENGINE = InnoDB,
 PARTITION d202101 VALUES LESS THAN (738156) ENGINE = InnoDB,
 PARTITION d202102 VALUES LESS THAN (738187) ENGINE = InnoDB,
 PARTITION d202103 VALUES LESS THAN (738215) ENGINE = InnoDB,
 PARTITION d202104 VALUES LESS THAN (738246) ENGINE = InnoDB,
 PARTITION d202105 VALUES LESS THAN (738276) ENGINE = InnoDB,
 PARTITION d202106 VALUES LESS THAN (738307) ENGINE = InnoDB,
 PARTITION d202107 VALUES LESS THAN (738337) ENGINE = InnoDB,
 PARTITION d202108 VALUES LESS THAN (738368) ENGINE = InnoDB,
 PARTITION d202109 VALUES LESS THAN (738399) ENGINE = InnoDB,
 PARTITION d202110 VALUES LESS THAN (738429) ENGINE = InnoDB,
 PARTITION d202111 VALUES LESS THAN (738460) ENGINE = InnoDB,
 PARTITION d202112 VALUES LESS THAN (738490) ENGINE = InnoDB,
 PARTITION d202201 VALUES LESS THAN (738521) ENGINE = InnoDB,
 PARTITION d202202 VALUES LESS THAN (738552) ENGINE = InnoDB,
 PARTITION d202203 VALUES LESS THAN (738580) ENGINE = InnoDB,
 PARTITION d202204 VALUES LESS THAN (738611) ENGINE = InnoDB,
 PARTITION d202205 VALUES LESS THAN (738641) ENGINE = InnoDB,
 PARTITION d202206 VALUES LESS THAN (738672) ENGINE = InnoDB,
 PARTITION d202207 VALUES LESS THAN (738702) ENGINE = InnoDB,
 PARTITION d202208 VALUES LESS THAN (738733) ENGINE = InnoDB,
 PARTITION d202209 VALUES LESS THAN (738764) ENGINE = InnoDB,
 PARTITION d202210 VALUES LESS THAN (738794) ENGINE = InnoDB,
 PARTITION d202211 VALUES LESS THAN (738825) ENGINE = InnoDB,
 PARTITION d202212 VALUES LESS THAN (738855) ENGINE = InnoDB,
 PARTITION d202301 VALUES LESS THAN (738886) ENGINE = InnoDB,
 PARTITION d202302 VALUES LESS THAN (738917) ENGINE = InnoDB,
 PARTITION d202303 VALUES LESS THAN (738945) ENGINE = InnoDB,
 PARTITION d202304 VALUES LESS THAN (738976) ENGINE = InnoDB,
 PARTITION d202305 VALUES LESS THAN (739006) ENGINE = InnoDB,
 PARTITION d202306 VALUES LESS THAN (739037) ENGINE = InnoDB,
 PARTITION d202307 VALUES LESS THAN (739067) ENGINE = InnoDB,
 PARTITION d202308 VALUES LESS THAN (739098) ENGINE = InnoDB) */;

-- ----------------------------
-- Records of checking_23
-- ----------------------------

-- ----------------------------
-- Table structure for checking_603
-- ----------------------------
DROP TABLE IF EXISTS `checking_603`;
CREATE TABLE `checking_603` (
  `checkid` int(11) NOT NULL AUTO_INCREMENT,
  `cardid` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `deviceid` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `imageurl` varchar(254) DEFAULT '',
  PRIMARY KEY (`checkid`,`time`) USING BTREE,
  KEY `time` (`time`) USING BTREE,
  KEY `cardid` (`cardid`) USING HASH,
  KEY `deviceid` (`deviceid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (to_days(time))
(PARTITION d201809 VALUES LESS THAN (737303) ENGINE = InnoDB,
 PARTITION d201810 VALUES LESS THAN (737333) ENGINE = InnoDB,
 PARTITION d201811 VALUES LESS THAN (737364) ENGINE = InnoDB,
 PARTITION d201812 VALUES LESS THAN (737394) ENGINE = InnoDB,
 PARTITION d201901 VALUES LESS THAN (737425) ENGINE = InnoDB,
 PARTITION d201902 VALUES LESS THAN (737456) ENGINE = InnoDB,
 PARTITION d201903 VALUES LESS THAN (737484) ENGINE = InnoDB,
 PARTITION d201904 VALUES LESS THAN (737515) ENGINE = InnoDB,
 PARTITION d201905 VALUES LESS THAN (737545) ENGINE = InnoDB,
 PARTITION d201906 VALUES LESS THAN (737576) ENGINE = InnoDB,
 PARTITION d201907 VALUES LESS THAN (737606) ENGINE = InnoDB,
 PARTITION d201908 VALUES LESS THAN (737637) ENGINE = InnoDB,
 PARTITION d201909 VALUES LESS THAN (737668) ENGINE = InnoDB,
 PARTITION d201910 VALUES LESS THAN (737698) ENGINE = InnoDB,
 PARTITION d201911 VALUES LESS THAN (737729) ENGINE = InnoDB,
 PARTITION d201912 VALUES LESS THAN (737759) ENGINE = InnoDB,
 PARTITION d202001 VALUES LESS THAN (737790) ENGINE = InnoDB,
 PARTITION d202002 VALUES LESS THAN (737821) ENGINE = InnoDB,
 PARTITION d202003 VALUES LESS THAN (737850) ENGINE = InnoDB,
 PARTITION d202004 VALUES LESS THAN (737881) ENGINE = InnoDB,
 PARTITION d202005 VALUES LESS THAN (737911) ENGINE = InnoDB,
 PARTITION d202006 VALUES LESS THAN (737942) ENGINE = InnoDB,
 PARTITION d202007 VALUES LESS THAN (737972) ENGINE = InnoDB,
 PARTITION d202008 VALUES LESS THAN (738003) ENGINE = InnoDB,
 PARTITION d202009 VALUES LESS THAN (738034) ENGINE = InnoDB,
 PARTITION d202010 VALUES LESS THAN (738064) ENGINE = InnoDB,
 PARTITION d202011 VALUES LESS THAN (738095) ENGINE = InnoDB,
 PARTITION d202012 VALUES LESS THAN (738125) ENGINE = InnoDB,
 PARTITION d202101 VALUES LESS THAN (738156) ENGINE = InnoDB,
 PARTITION d202102 VALUES LESS THAN (738187) ENGINE = InnoDB,
 PARTITION d202103 VALUES LESS THAN (738215) ENGINE = InnoDB,
 PARTITION d202104 VALUES LESS THAN (738246) ENGINE = InnoDB,
 PARTITION d202105 VALUES LESS THAN (738276) ENGINE = InnoDB,
 PARTITION d202106 VALUES LESS THAN (738307) ENGINE = InnoDB,
 PARTITION d202107 VALUES LESS THAN (738337) ENGINE = InnoDB,
 PARTITION d202108 VALUES LESS THAN (738368) ENGINE = InnoDB,
 PARTITION d202109 VALUES LESS THAN (738399) ENGINE = InnoDB,
 PARTITION d202110 VALUES LESS THAN (738429) ENGINE = InnoDB,
 PARTITION d202111 VALUES LESS THAN (738460) ENGINE = InnoDB,
 PARTITION d202112 VALUES LESS THAN (738490) ENGINE = InnoDB,
 PARTITION d202201 VALUES LESS THAN (738521) ENGINE = InnoDB,
 PARTITION d202202 VALUES LESS THAN (738552) ENGINE = InnoDB,
 PARTITION d202203 VALUES LESS THAN (738580) ENGINE = InnoDB,
 PARTITION d202204 VALUES LESS THAN (738611) ENGINE = InnoDB,
 PARTITION d202205 VALUES LESS THAN (738641) ENGINE = InnoDB,
 PARTITION d202206 VALUES LESS THAN (738672) ENGINE = InnoDB,
 PARTITION d202207 VALUES LESS THAN (738702) ENGINE = InnoDB,
 PARTITION d202208 VALUES LESS THAN (738733) ENGINE = InnoDB,
 PARTITION d202209 VALUES LESS THAN (738764) ENGINE = InnoDB,
 PARTITION d202210 VALUES LESS THAN (738794) ENGINE = InnoDB,
 PARTITION d202211 VALUES LESS THAN (738825) ENGINE = InnoDB,
 PARTITION d202212 VALUES LESS THAN (738855) ENGINE = InnoDB,
 PARTITION d202301 VALUES LESS THAN (738886) ENGINE = InnoDB,
 PARTITION d202302 VALUES LESS THAN (738917) ENGINE = InnoDB,
 PARTITION d202303 VALUES LESS THAN (738945) ENGINE = InnoDB,
 PARTITION d202304 VALUES LESS THAN (738976) ENGINE = InnoDB,
 PARTITION d202305 VALUES LESS THAN (739006) ENGINE = InnoDB,
 PARTITION d202306 VALUES LESS THAN (739037) ENGINE = InnoDB,
 PARTITION d202307 VALUES LESS THAN (739067) ENGINE = InnoDB,
 PARTITION d202308 VALUES LESS THAN (739098) ENGINE = InnoDB) */;

-- ----------------------------
-- Records of checking_603
-- ----------------------------

-- ----------------------------
-- Table structure for checkstandard
-- ----------------------------
DROP TABLE IF EXISTS `checkstandard`;
CREATE TABLE `checkstandard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template` varchar(256) NOT NULL,
  `isdelete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `checkstandard_key` (`isdelete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkstandard
-- ----------------------------
INSERT INTO `checkstandard` VALUES ('1', '杭州城铁巡视检查标准', '0');

-- ----------------------------
-- Table structure for checkstandarddirectory
-- ----------------------------
DROP TABLE IF EXISTS `checkstandarddirectory`;
CREATE TABLE `checkstandarddirectory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `templateid` int(11) NOT NULL,
  `isdelete` int(11) NOT NULL DEFAULT '0',
  `directoryname` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chkstartarddir_key` (`isdelete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkstandarddirectory
-- ----------------------------
INSERT INTO `checkstandarddirectory` VALUES ('1', '1', '0', '作业安全');
INSERT INTO `checkstandarddirectory` VALUES ('2', '1', '0', '物品堆放');
INSERT INTO `checkstandarddirectory` VALUES ('3', '1', '0', '机械设备');
INSERT INTO `checkstandarddirectory` VALUES ('4', '1', '0', '机器设备');

-- ----------------------------
-- Table structure for checkstandarditem
-- ----------------------------
DROP TABLE IF EXISTS `checkstandarditem`;
CREATE TABLE `checkstandarditem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subdirectoryid` int(11) NOT NULL,
  `itemname` varchar(256) NOT NULL,
  `standard` varchar(256) NOT NULL,
  `type` int(11) NOT NULL,
  `severity` int(11) NOT NULL,
  `priority` int(11) NOT NULL,
  `isdelete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `chkstandarditem_key` (`isdelete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkstandarditem
-- ----------------------------
INSERT INTO `checkstandarditem` VALUES ('1', '1', '未戴安全帽', '未戴安全帽', '1', '7', '7', '0');
INSERT INTO `checkstandarditem` VALUES ('2', '1', '安全帽佩戴不规范', '安全帽佩戴不规范', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('3', '1', '女性头发未收起', '女性头发未收起', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('4', '2', '高处作业未系安全带', '高处作业未系安全带', '1', '7', '7', '0');
INSERT INTO `checkstandarditem` VALUES ('5', '2', '安全带系挂不规范', '安全带系挂不规范', '1', '6', '6', '0');
INSERT INTO `checkstandarditem` VALUES ('6', '3', '临边护栏高度不足', '临边护栏高度不足', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('7', '4', '货物无人看护', '下车货物无人看护', '1', '2', '1', '1');
INSERT INTO `checkstandarditem` VALUES ('8', '5', '车辆超速', '车辆超速', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('9', '6', '钱箱未锁', '人离开钱箱未锁', '1', '8', '8', '1');
INSERT INTO `checkstandarditem` VALUES ('10', '7', '吊车作业违规', ' 吊车作业违规', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('11', '8', '氧气瓶乙炔瓶过近', ' 氧气瓶乙炔瓶过近', '1', '6', '6', '0');
INSERT INTO `checkstandarditem` VALUES ('12', '1', '穿高跟鞋', ' 穿高跟鞋', '1', '6', '6', '0');
INSERT INTO `checkstandarditem` VALUES ('13', '2', '梯子放置不规范', ' 梯子放置不规范', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('14', '2', '违规登高作业', ' 违规登高作业', '1', '6', '6', '0');
INSERT INTO `checkstandarditem` VALUES ('15', '2', '登高手持物品', ' 登高手持物品', '1', '6', '6', '0');
INSERT INTO `checkstandarditem` VALUES ('16', '2', '高空抛物', ' 高空抛物', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('17', '2', '易滑物品摆放', ' 易滑物品摆放', '1', '7', '7', '0');
INSERT INTO `checkstandarditem` VALUES ('18', '3', '临边护栏强度不足', ' 临边护栏强度不足', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('19', '3', '临边护栏未封闭', ' 临边护栏未封闭', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('20', '3', '临边护栏私自拆除', ' 临边护栏私自拆除', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('21', '7', '起重吊装无司索工', ' 起重吊装无司索工', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('22', '8', '乙炔瓶靠近明火', ' 乙炔瓶靠近明火', '1', '8', '8', '0');
INSERT INTO `checkstandarditem` VALUES ('23', '8', '乙炔瓶无回火阀', ' 乙炔瓶无回火阀', '1', '6', '6', '0');
INSERT INTO `checkstandarditem` VALUES ('24', '9', '材料未分类', ' 材料未分类', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('25', '9', '材料未分批', ' 材料未分批', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('26', '9', '材料未分规格', ' 材料未分规格', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('27', '9', '材料未堆放整齐', ' 材料未堆放整齐', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('28', '9', '材料不整洁', ' 材料不整洁', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('29', '9', '材料无挂牌标识', ' 材料无挂牌标识', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('30', '9', '砂石堆放无底脚', ' 砂石堆放无底脚', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('31', '9', '砂石堆放不整齐', ' 砂石堆放不整齐', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('32', '9', '砖砌体堆放不整齐', ' 砖砌体堆放不整齐', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('33', '9', '砖砌体堆放过高', ' 砖砌体堆放过高', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('34', '9', '钢筋未堆放指定位置', ' 钢筋未堆放指定位置', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('35', '9', '钢筋未分规格', ' 钢筋未分规格', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('36', '9', '钢筋未堆放整齐', ' 钢筋未堆放整齐', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('37', '9', '钢筋无挂牌标识', ' 钢筋无挂牌标识', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('38', '9', '砼支撑堆放材料', ' 砼支撑堆放材料', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('39', '9', '钢支撑堆放材料', ' 钢支撑堆放材料', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('40', '9', '砼支撑人员站立', ' 砼支撑人员站立', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('41', '9', '钢支撑人员站立', ' 钢支撑人员站立', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('42', '9', '砼支撑人员通过', ' 砼支撑人员通过', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('43', '9', '钢支撑人员通过', ' 钢支撑人员通过', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('44', '9', '基坑边堆载过大', ' 基坑边堆载过大', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('45', '9', '裸露土体未覆盖', ' 裸露土体未覆盖', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('46', '9', '场地内施工便道泥土未清扫', ' 场地内施工便道泥土未清扫', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('47', '9', '泥浆未冲洗', ' 泥浆未冲洗', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('48', '9', '施工场地围挡未关闭', ' 施工场地围挡未关闭', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('49', '9', '施工现场大门未关闭', ' 施工现场大门未关闭', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('50', '9', 'new item', ' ', '1', '5', '5', '1');
INSERT INTO `checkstandarditem` VALUES ('51', '10', '吊车作业地形不当', ' 吊车作业地形不当', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('52', '10', '吊车作业未铺垫木板', ' 吊车作业未铺垫木板', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('53', '10', '起重臂下站人', ' 起重臂下站人', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('54', '10', '起吊物体上站人', ' 起吊物体上站人', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('55', '10', '吊装时无人指挥', ' 吊装时无人指挥', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('56', '10', '吊装时信号不清', ' 吊装时信号不清', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('57', '10', '吊装物体有尖角', ' 吊装物体有尖角', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('58', '10', '起重物体上下有人', ' 起重物体上下有人', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('59', '10', '吊装行走范围有人', ' 吊装行走范围有人', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('60', '10', '吊车距高压线过近', ' 吊车距高压线过近', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('61', '10', '吊车距低压线过近', ' 吊车距低压线过近', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('62', '10', '吊装重物不得行车', ' 吊装重物不得行车', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('63', '10', '汽车吊代替拖车使用', ' 汽车吊代替拖车使用', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('64', '10', '吊车行走外部乘人', ' 吊车行走外部乘人', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('65', '11', '履带吊装地面不坚实', ' 履带吊装地面不坚实', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('66', '11', '履带下方无枕木', ' 履带下方无枕木', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('67', '11', '吊装时无人指挥', ' 吊装时无人指挥', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('68', '11', '起重机靠近基坑', ' 起重机靠近基坑', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('69', '11', '起重机停放斜坡', ' 起重机停放斜坡', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('70', '11', '起重机载重上下坡', ' 起重机载重上下坡', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('71', '11', '上坡时起重臂未放下', ' 上坡时起重臂未放下', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('72', '11', '下坡时起重臂未上扬', ' 下坡时起重臂未上扬', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('73', '11', '夜间吊装照明不足', ' 夜间吊装照明不足', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('74', '11', '恶劣气候吊装', ' 恶劣气候吊装', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('75', '11', '起重机械周边有障碍物', ' 起重机械周边有障碍物', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('76', '11', '起重机抬吊性能不一', ' 起重机抬吊性能不一', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('77', '11', '抬吊未统一指挥', ' 抬吊未统一指挥', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('78', '11', '抬吊动作不一致', ' 抬吊动作不一致', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('79', '11', '载荷分配不一致', ' 载荷分配不一致', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('80', '11', '起吊重物时间过长', ' 起吊重物时间过长', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('81', '11', '突发故障重物未降落', ' 突发故障重物未降落', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('82', '12', '龙门吊无信号员', ' 龙门吊无信号员', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('83', '12', '龙门吊未停车', ' 龙门吊未停车', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('84', '12', '龙门吊无信号操作', ' 龙门吊无信号操作', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('85', '12', '龙门吊轨道行走', ' 龙门吊轨道行走', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('86', '12', '龙门吊重物长时间悬吊', ' 龙门吊重物长时间悬吊', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('87', '12', '吊物越过人头', ' 吊物越过人头', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('88', '12', '调运物件离地过高 ', ' 调运物件离地过高 ', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('89', '12', '恶劣天气未停止工作', ' 恶劣天气未停止工作', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('90', '12', '车轮前后未垫块', ' 车轮前后未垫块', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('91', '12', '夜间作业无充足照明', ' 夜间作业无充足照明', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('92', '12', '轨道有障碍物', ' 轨道有障碍物', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('93', '12', '吊运大件无人监视指挥', ' 吊运大件无人监视指挥', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('94', '12', '吊运时信号不明', ' 吊运时信号不明', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('95', '12', '吊运时重量不明', ' 吊运时重量不明', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('96', '12', '吊运时光线暗淡', ' 吊运时光线暗淡', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('97', '12', '吊绳捆缚不牢', ' 吊绳捆缚不牢', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('98', '12', '附件捆缚不牢', ' 附件捆缚不牢', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('99', '12', '吊挂重物直接加工', ' 吊挂重物直接加工', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('100', '12', '歪拉斜挂', ' 歪拉斜挂', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('101', '12', '工件站人时吊装', ' 工件站人时吊装', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('102', '12', '工件放置活动物吊装', ' 工件放置活动物吊装', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('103', '12', '爆炸性物品吊装', ' 爆炸性物品吊装', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('104', '12', '尖锐品吊装', ' 尖锐品吊装', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('105', '12', '缺口物品吊装', ' 缺口物品吊装', '1', '5', '5', '0');
INSERT INTO `checkstandarditem` VALUES ('106', '12', 'new item', ' ', '1', '5', '5', '1');
INSERT INTO `checkstandarditem` VALUES ('107', '12', 'new item', ' ', '1', '5', '5', '1');
INSERT INTO `checkstandarditem` VALUES ('108', '12', 'new item', ' ', '1', '5', '5', '1');
INSERT INTO `checkstandarditem` VALUES ('109', '12', 'new item', ' ', '1', '5', '5', '1');
INSERT INTO `checkstandarditem` VALUES ('110', '12', 'new item', ' ', '1', '5', '5', '1');
INSERT INTO `checkstandarditem` VALUES ('111', '6', '建议调整摄像头', ' 建议调整摄像头', '1', '5', '5', '0');

-- ----------------------------
-- Table structure for checkstandardsubdirctory
-- ----------------------------
DROP TABLE IF EXISTS `checkstandardsubdirctory`;
CREATE TABLE `checkstandardsubdirctory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `directoryid` int(11) NOT NULL,
  `subdirectoryname` varchar(256) NOT NULL,
  `isdelete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkstandardsubdirctory
-- ----------------------------

-- ----------------------------
-- Table structure for checkstandardsubdirectory
-- ----------------------------
DROP TABLE IF EXISTS `checkstandardsubdirectory`;
CREATE TABLE `checkstandardsubdirectory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `directoryid` int(11) NOT NULL,
  `subdirectoryname` varchar(256) NOT NULL,
  `isdelete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `chkstandardsubdir_key` (`isdelete`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkstandardsubdirectory
-- ----------------------------
INSERT INTO `checkstandardsubdirectory` VALUES ('1', '1', '人员安全', '0');
INSERT INTO `checkstandardsubdirectory` VALUES ('2', '1', '登高作业', '0');
INSERT INTO `checkstandardsubdirectory` VALUES ('3', '1', '临边防护', '0');
INSERT INTO `checkstandardsubdirectory` VALUES ('4', '2', '流程规范', '1');
INSERT INTO `checkstandardsubdirectory` VALUES ('5', '3', '进出车辆', '0');
INSERT INTO `checkstandardsubdirectory` VALUES ('6', '4', '监控设备', '0');
INSERT INTO `checkstandardsubdirectory` VALUES ('7', '1', '起重吊装', '0');
INSERT INTO `checkstandardsubdirectory` VALUES ('8', '1', '动火作业', '0');
INSERT INTO `checkstandardsubdirectory` VALUES ('9', '2', '材料堆放', '0');
INSERT INTO `checkstandardsubdirectory` VALUES ('10', '3', '汽车吊', '0');
INSERT INTO `checkstandardsubdirectory` VALUES ('11', '3', '履带吊', '0');
INSERT INTO `checkstandardsubdirectory` VALUES ('12', '3', '龙门吊', '0');

-- ----------------------------
-- Table structure for checktask
-- ----------------------------
DROP TABLE IF EXISTS `checktask`;
CREATE TABLE `checktask` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `deviceno` int(11) NOT NULL,
  `camerano` int(11) NOT NULL,
  `taskname` varchar(64) NOT NULL,
  `enable` int(11) NOT NULL DEFAULT '1',
  `taskstatus` int(11) NOT NULL DEFAULT '0',
  `areaenable` int(11) NOT NULL DEFAULT '0',
  `picleft` int(11) NOT NULL DEFAULT '0',
  `pictop` int(11) NOT NULL DEFAULT '0',
  `picwidth` int(11) NOT NULL DEFAULT '0',
  `picheight` int(11) NOT NULL DEFAULT '0',
  `argtype` int(11) NOT NULL DEFAULT '0',
  `lastpic` varchar(256) NOT NULL DEFAULT '',
  `keep_percent` int(11) NOT NULL DEFAULT '10',
  `worktime` varchar(255) DEFAULT '',
  `tasktype` int(11) NOT NULL DEFAULT '0',
  `maxsavetime` int(11) NOT NULL DEFAULT '2',
  `checkstarttime` varchar(255) NOT NULL DEFAULT '',
  `checkendtime` varchar(255) NOT NULL DEFAULT '',
  `aiEnable` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1为启用AI，默认为0',
  PRIMARY KEY (`id`),
  KEY `checktask_userid` (`userid`,`enable`) USING BTREE,
  KEY `checktask_camerano` (`camerano`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checktask
-- ----------------------------
INSERT INTO `checktask` VALUES ('8', '2', '1', '1', '通用过滤_1', '1', '0', '0', '0', '0', '0', '0', '0', '', '10', '', '0', '3', '', '', '0');
INSERT INTO `checktask` VALUES ('9', '2', '1', '2', '通用过滤_2', '1', '0', '0', '0', '0', '0', '0', '0', '', '10', '', '0', '3', '', '', '0');
INSERT INTO `checktask` VALUES ('10', '2', '1', '3', '通用过滤_3', '1', '0', '0', '0', '0', '0', '0', '0', '', '10', '', '0', '3', '', '', '0');
INSERT INTO `checktask` VALUES ('11', '2', '1', '4', '通用过滤_4', '1', '0', '0', '0', '0', '0', '0', '0', '', '10', '', '0', '3', '', '', '0');
INSERT INTO `checktask` VALUES ('12', '2', '1', '5', '通用过滤_5', '1', '0', '0', '0', '0', '0', '0', '0', '', '10', '', '0', '3', '', '', '0');
INSERT INTO `checktask` VALUES ('13', '2', '1', '6', '通用过滤_6', '1', '0', '0', '0', '0', '0', '0', '0', '', '10', '', '0', '3', '', '', '0');
INSERT INTO `checktask` VALUES ('14', '2', '1', '7', '通用过滤_7', '1', '0', '0', '0', '0', '0', '0', '0', '', '10', '', '0', '3', '', '', '0');
INSERT INTO `checktask` VALUES ('15', '2', '1', '8', '通用过滤_8', '1', '0', '0', '0', '0', '0', '0', '0', '', '10', '', '0', '3', '', '', '0');

-- ----------------------------
-- Table structure for contacts
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `ContactId` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(256) DEFAULT NULL,
  `FamilyName` varchar(256) DEFAULT NULL,
  `Phone1` varchar(45) DEFAULT '',
  `Phone2` varchar(45) DEFAULT '',
  `Email` varchar(256) DEFAULT NULL,
  `Address` varchar(256) DEFAULT NULL,
  `Company` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ContactId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of contacts
-- ----------------------------

-- ----------------------------
-- Table structure for corporation
-- ----------------------------
DROP TABLE IF EXISTS `corporation`;
CREATE TABLE `corporation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `corpid` varchar(255) NOT NULL,
  `corpname` varchar(255) NOT NULL,
  `brandid` int(11) NOT NULL,
  `sendmode` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `corp_brandid_unique` (`brandid`),
  UNIQUE KEY `corp_unique` (`corpid`,`brandid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of corporation
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(256) NOT NULL,
  `userpswd` varchar(256) NOT NULL,
  `userdes` varchar(256) NOT NULL DEFAULT '',
  `userright` int(11) NOT NULL,
  `reportright` int(11) NOT NULL,
  `deviceright` int(11) NOT NULL,
  `isfirst` int(11) NOT NULL DEFAULT '1',
  `isinspector` int(11) NOT NULL DEFAULT '0',
  `managerright` int(11) NOT NULL DEFAULT '1',
  `customercode` varchar(256) NOT NULL DEFAULT '',
  `phone` varchar(32) NOT NULL DEFAULT '',
  `bindsafecode` tinyint(4) NOT NULL DEFAULT '0',
  `safecode` varchar(255) NOT NULL DEFAULT '',
  `position` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `in_username` (`username`(32),`userpswd`(32)),
  KEY `customer_deviceright` (`deviceright`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'hzctadmin', 'hzsz123456', '杭州城铁', '10', '15', '2', '1', '0', '1', '', '', '0', '', '');
INSERT INTO `customer` VALUES ('2', 'hzctyf', 'ab123456', '杭州城铁杨峰', '10', '15', '2', '1', '0', '1', '', '', '0', '', '');
INSERT INTO `customer` VALUES ('3', 'ZNGDIOS', 'h1234567', '苹果审核帐号（勿删）', '34', '15', '1', '1', '0', '1', '', '', '0', '', '');
INSERT INTO `customer` VALUES ('4', 'zhangdj', 'z1234567', 'Andriod审核帐号（勿删）', '34', '15', '1', '0', '0', '1', '', '', '0', '', '');
INSERT INTO `customer` VALUES ('5', 'wangguotao', 'ab123456', '王国涛', '10', '15', '2', '1', '0', '1', '', '', '0', '', '');
INSERT INTO `customer` VALUES ('6', 'sitetest', 'h1234567', 'test', '10', '15', '2', '0', '0', '1', '', '', '0', '', 'test');
INSERT INTO `customer` VALUES ('7', 'hzszyhh', 'yhh123456', '数科 余华海', '10', '19', '2', '1', '0', '1', '', '', '0', '', '');

-- ----------------------------
-- Table structure for customercamera
-- ----------------------------
DROP TABLE IF EXISTS `customercamera`;
CREATE TABLE `customercamera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `cameraid` int(11) NOT NULL,
  `deviceid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cameraid` (`cameraid`),
  KEY `customerid` (`customerid`),
  KEY `customercameranormal` (`id`,`customerid`,`cameraid`,`deviceid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customercamera
-- ----------------------------
INSERT INTO `customercamera` VALUES ('1', '1', '1', '1');
INSERT INTO `customercamera` VALUES ('2', '1', '2', '1');
INSERT INTO `customercamera` VALUES ('3', '1', '3', '1');
INSERT INTO `customercamera` VALUES ('4', '1', '4', '1');
INSERT INTO `customercamera` VALUES ('5', '1', '5', '1');
INSERT INTO `customercamera` VALUES ('6', '1', '6', '1');
INSERT INTO `customercamera` VALUES ('7', '1', '7', '1');
INSERT INTO `customercamera` VALUES ('8', '1', '8', '1');
INSERT INTO `customercamera` VALUES ('9', '2', '1', '1');
INSERT INTO `customercamera` VALUES ('10', '2', '2', '1');
INSERT INTO `customercamera` VALUES ('11', '2', '3', '1');
INSERT INTO `customercamera` VALUES ('12', '2', '4', '1');
INSERT INTO `customercamera` VALUES ('13', '2', '5', '1');
INSERT INTO `customercamera` VALUES ('14', '2', '6', '1');
INSERT INTO `customercamera` VALUES ('15', '2', '7', '1');
INSERT INTO `customercamera` VALUES ('16', '2', '8', '1');
INSERT INTO `customercamera` VALUES ('17', '3', '1', '1');
INSERT INTO `customercamera` VALUES ('18', '3', '2', '1');
INSERT INTO `customercamera` VALUES ('19', '3', '3', '1');
INSERT INTO `customercamera` VALUES ('20', '3', '4', '1');
INSERT INTO `customercamera` VALUES ('21', '3', '5', '1');
INSERT INTO `customercamera` VALUES ('22', '3', '6', '1');
INSERT INTO `customercamera` VALUES ('23', '3', '7', '1');
INSERT INTO `customercamera` VALUES ('24', '3', '8', '1');
INSERT INTO `customercamera` VALUES ('25', '4', '1', '1');
INSERT INTO `customercamera` VALUES ('26', '4', '2', '1');
INSERT INTO `customercamera` VALUES ('27', '4', '3', '1');
INSERT INTO `customercamera` VALUES ('28', '4', '4', '1');
INSERT INTO `customercamera` VALUES ('29', '4', '5', '1');
INSERT INTO `customercamera` VALUES ('30', '4', '6', '1');
INSERT INTO `customercamera` VALUES ('31', '4', '7', '1');
INSERT INTO `customercamera` VALUES ('32', '4', '8', '1');
INSERT INTO `customercamera` VALUES ('33', '5', '1', '1');
INSERT INTO `customercamera` VALUES ('34', '5', '2', '1');
INSERT INTO `customercamera` VALUES ('35', '5', '3', '1');
INSERT INTO `customercamera` VALUES ('36', '5', '4', '1');
INSERT INTO `customercamera` VALUES ('37', '5', '5', '1');
INSERT INTO `customercamera` VALUES ('38', '5', '6', '1');
INSERT INTO `customercamera` VALUES ('39', '5', '7', '1');
INSERT INTO `customercamera` VALUES ('40', '5', '8', '1');
INSERT INTO `customercamera` VALUES ('41', '6', '1', '1');
INSERT INTO `customercamera` VALUES ('42', '6', '2', '1');
INSERT INTO `customercamera` VALUES ('43', '6', '3', '1');
INSERT INTO `customercamera` VALUES ('44', '6', '4', '1');
INSERT INTO `customercamera` VALUES ('45', '6', '5', '1');
INSERT INTO `customercamera` VALUES ('46', '6', '6', '1');
INSERT INTO `customercamera` VALUES ('47', '6', '7', '1');
INSERT INTO `customercamera` VALUES ('48', '6', '8', '1');
INSERT INTO `customercamera` VALUES ('49', '7', '9', '2');
INSERT INTO `customercamera` VALUES ('50', '7', '10', '2');
INSERT INTO `customercamera` VALUES ('51', '7', '11', '2');
INSERT INTO `customercamera` VALUES ('52', '7', '12', '2');
INSERT INTO `customercamera` VALUES ('53', '7', '13', '3');
INSERT INTO `customercamera` VALUES ('56', '7', '16', '3');

-- ----------------------------
-- Table structure for customermobiledevice
-- ----------------------------
DROP TABLE IF EXISTS `customermobiledevice`;
CREATE TABLE `customermobiledevice` (
  `customerid` int(11) NOT NULL,
  `cid` varchar(128) NOT NULL DEFAULT '',
  `dt` varchar(128) NOT NULL DEFAULT '',
  `i18n` varchar(256) NOT NULL DEFAULT 'CN-GB',
  PRIMARY KEY (`customerid`,`cid`,`dt`),
  KEY `customerid` (`customerid`),
  KEY `in_customerid` (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customermobiledevice
-- ----------------------------
INSERT INTO `customermobiledevice` VALUES ('2', '', 'c7a3682140b31d844ca2090e69db64daa65c20c58ef4fe482f1f8f11f59f8cfd', 'CN-GB');
INSERT INTO `customermobiledevice` VALUES ('3', '', 'ac73e5a43f3de38c592213e79e0fcb727d9c8ab0ac909b5862b449a6f3304d9c', 'CN-GB');
INSERT INTO `customermobiledevice` VALUES ('4', '', '', 'CN-GB');
INSERT INTO `customermobiledevice` VALUES ('5', '', '8292c0cfc39836dc158e79cc0691e3e77740fe4bb3909e8332f95c539e0fbe5c', 'CN-GB');

-- ----------------------------
-- Table structure for customeroutlet
-- ----------------------------
DROP TABLE IF EXISTS `customeroutlet`;
CREATE TABLE `customeroutlet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `outletid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customeroutlet_unique` (`customerid`,`outletid`),
  KEY `outletid` (`outletid`),
  KEY `customerid` (`customerid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customeroutlet
-- ----------------------------
INSERT INTO `customeroutlet` VALUES ('1', '1', '1');
INSERT INTO `customeroutlet` VALUES ('2', '2', '1');
INSERT INTO `customeroutlet` VALUES ('3', '3', '1');
INSERT INTO `customeroutlet` VALUES ('4', '4', '1');
INSERT INTO `customeroutlet` VALUES ('5', '5', '1');
INSERT INTO `customeroutlet` VALUES ('6', '6', '1');
INSERT INTO `customeroutlet` VALUES ('7', '7', '2');

-- ----------------------------
-- Table structure for customerreport
-- ----------------------------
DROP TABLE IF EXISTS `customerreport`;
CREATE TABLE `customerreport` (
  `customerid` int(11) NOT NULL,
  `reporttype` int(11) NOT NULL,
  `reportid` varchar(128) NOT NULL,
  `status` int(11) NOT NULL,
  `readtime` datetime DEFAULT NULL,
  `actionresult` int(11) NOT NULL,
  `sendtime` datetime DEFAULT NULL,
  `receivedtime` datetime DEFAULT NULL,
  `lastupdatetime` datetime DEFAULT NULL,
  `reportsender` int(11) NOT NULL,
  `transcount` int(11) NOT NULL,
  `actiontype` int(11) NOT NULL,
  `outletid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`customerid`,`reportid`),
  KEY `reportid` (`reportid`),
  KEY `customerreporttype` (`reporttype`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customerreport
-- ----------------------------
INSERT INTO `customerreport` VALUES ('1', '4', '02d1df74-d427-44f8-bf16-dcefc8254e46', '0', null, '0', '2018-10-21 14:27:10', null, '2018-10-21 14:27:10', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', '137df24d-05f0-4018-812f-0ff88df586a5', '0', null, '0', '2018-10-21 15:13:04', null, '2018-10-21 15:13:04', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '13cd0250-9a86-4c04-b1cb-b92d088ec842', '0', null, '2', '2018-12-07 17:06:43', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', '16d73a45-c5a4-4a9c-a0a0-b79865775a86', '0', null, '0', '2018-10-21 15:12:53', null, '2018-10-21 15:12:53', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '17aba7f7-1d42-48f3-a136-934d442ef13c', '0', null, '2', '2018-12-27 16:36:35', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '1843c786-9248-408f-9437-5179ff9aa1c7', '0', null, '2', '2018-12-04 18:47:43', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '1b265762-dc09-43f0-873f-8d8e6935c18d', '0', null, '2', '2018-11-28 17:37:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '1d2a8027-c148-494a-ad14-6395f9205bf9', '0', null, '2', '2018-10-17 15:26:19', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '21ad7f10-3296-46d6-82dd-7e6b1d1377e8', '0', null, '2', '2018-10-18 19:27:48', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', '2794ae82-03c1-42ec-bde6-03bdbae0f9dc', '0', null, '0', '2018-10-21 12:25:57', null, '2018-10-21 12:25:57', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '27df990d-0dcd-4e9e-aab9-b792d5764f3b', '0', null, '2', '2018-12-24 17:53:52', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '28a0c52d-362e-4d41-8392-68c7c6b890a6', '0', null, '2', '2018-10-29 17:59:15', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '29c45257-34bd-4cef-8c49-25d4652067e5', '0', null, '2', '2018-11-21 17:18:44', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', '2a6e4791-1221-436b-a09b-97a5d88fbb06', '0', null, '0', '2018-10-21 14:27:07', null, '2018-10-21 14:27:07', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '2f0801af-fd2f-40ad-b3da-2a78d050e303', '0', null, '2', '2018-12-13 17:29:21', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '2fc4d22d-f783-412b-a693-45935f0c0f6b', '0', null, '2', '2018-12-03 19:02:58', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '357d9f58-f433-40a7-8d47-b494a35eb00a', '0', null, '2', '2018-12-12 13:40:28', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '3ba58e19-8113-43a0-9ada-08870d1f5a2e', '0', null, '2', '2018-11-08 08:52:32', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '404d8d22-aa3c-431b-80d6-2bbc70903364', '0', null, '2', '2018-11-13 19:16:31', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '420d4d71-9f6d-421b-93b0-67b47fef0a39', '0', null, '2', '2018-11-06 21:36:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '4690bf4b-415f-4bf7-9b03-1fb4b564c9bc', '0', null, '2', '2018-12-10 16:50:55', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '49f68e92-ea63-4258-9486-8aeb537ea703', '0', null, '2', '2018-10-24 17:41:49', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '4c1702c8-0afe-4d39-ba6f-a99892f34b9f', '0', null, '2', '2018-11-15 10:08:42', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '4e997401-1215-48d3-9f17-eba9f4d9f988', '0', null, '2', '2018-12-11 16:46:23', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '4f09f79a-4911-4157-8fd8-fd9d20d78f43', '0', null, '2', '2018-12-14 17:17:11', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '531553a6-0b18-4471-9e04-dc29946df379', '0', null, '2', '2018-10-30 19:16:03', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', '5522be72-8df5-41fd-8eaa-798b928e2c4d', '0', null, '0', '2018-10-17 13:13:19', null, '2018-10-17 13:13:19', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '5561f95b-ed08-47fc-8e53-706bd0018d14', '0', null, '2', '2018-12-21 16:36:43', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '55914eed-8a06-4dc7-ab58-389fe49139bf', '0', null, '2', '2018-12-05 17:15:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '57bb0dc6-56aa-4078-9672-4d385d15aea2', '0', null, '2', '2018-10-25 17:54:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '5a149828-b614-4ad3-b69a-32f1b0a82691', '0', null, '2', '2018-12-25 15:48:30', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '5b41f36f-679a-471a-b80b-101f32b2a1ff', '0', null, '2', '2018-10-31 18:39:14', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '66f6e1e2-68ce-45f5-8769-3eb1660ee39a', '0', null, '2', '2018-11-19 17:17:55', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '6c3ca4d9-b328-46ba-a7bc-aec282e3cca3', '0', null, '2', '2018-10-26 17:53:48', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '6f7e4ec5-bbb3-411d-a26c-e30e0812c168', '0', null, '2', '2018-12-17 16:41:38', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '6fd6c786-b889-4537-a53e-e42e735464bd', '0', null, '2', '2018-11-22 19:22:25', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '7564b019-ed3c-4883-aac4-82908a48d1fc', '0', null, '2', '2018-10-19 09:42:00', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '78361150-ab63-43c9-8bb2-187275a20e8a', '0', null, '2', '2018-11-15 15:14:25', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '79f63bca-94af-47d2-8ab8-a5b3c07d6241', '0', null, '2', '2018-10-19 09:42:00', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', '84599966-defd-4917-9eaf-0e26cfeb7fdf', '0', null, '0', '2018-10-20 18:16:48', null, '2018-10-20 18:16:48', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '9171c00b-db0e-463d-9de5-cc7fc5427354', '0', null, '2', '2018-11-23 13:36:32', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '97488b17-77d1-4599-b770-88c4ee192641', '0', null, '2', '2018-12-18 17:12:37', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', '992c62d1-d33e-4f56-a129-096873595f81', '0', null, '0', '2018-10-21 15:12:58', null, '2018-10-21 15:12:58', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', '9f7293ef-8cb1-4ab7-937d-df019ecd17af', '0', null, '2', '2018-11-01 17:41:13', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', 'a4615074-6b3c-4a7b-bbe0-8b05f294dcbd', '0', null, '0', '2018-10-21 14:27:19', null, '2018-10-21 14:27:19', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'a66c6bba-8d97-41be-a551-a90dc4fd2eb2', '0', null, '2', '2018-11-05 18:22:36', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'a6d47cf9-f3bc-4c76-a209-6df84b861ead', '0', null, '2', '2018-12-20 17:00:53', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'a9f0dd12-b7eb-4b2a-8d2b-dffeba75a01c', '0', null, '2', '2018-11-02 13:18:29', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', 'b88d36d4-937e-4855-8a99-6fcf86b08173', '0', null, '0', '2018-10-21 15:13:07', null, '2018-10-21 15:13:07', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'b9f8d22e-f7d8-4697-a13a-7b730a631889', '0', null, '2', '2018-12-19 17:14:08', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', 'bb103ae3-2cc5-4608-a889-113a802f70ee', '0', null, '0', '2018-10-21 12:26:00', null, '2018-10-21 12:26:00', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'c38d2ee7-cf60-4dfb-8388-fe9532d1313a', '0', null, '2', '2018-10-19 18:34:12', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'ca2aebb5-d7eb-4de3-a419-9117edf50756', '0', null, '2', '2018-10-23 18:16:45', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'cbaabda9-2617-4542-b1ce-3342fb5c1ef9', '0', null, '2', '2018-12-26 16:29:07', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'd34ce007-204a-4dfa-8834-ec11fcef8b24', '0', null, '2', '2018-12-06 16:51:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'e380745f-4aba-44ea-bfe3-1a23a72303f9', '0', null, '2', '2018-10-19 09:42:00', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'e53dc286-dc0f-4d62-900f-1ebaf6d66c43', '0', null, '2', '2018-11-20 17:38:01', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'e85f83c4-f75f-4026-9ab6-57f2e8389879', '0', null, '2', '2018-11-16 16:32:05', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', 'e8d5423e-d259-42b6-b147-f9e8e58b2de6', '0', null, '0', '2018-10-21 15:13:11', null, '2018-10-21 15:13:11', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', 'e90ad1c3-13ed-460c-a4cf-4dcee98b40d0', '0', null, '0', '2018-10-20 18:16:47', null, '2018-10-20 18:16:47', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '4', 'f4f23dba-90a5-4e39-a3d1-7e141e74af36', '0', null, '0', '2018-10-17 15:03:29', null, '2018-10-17 15:03:29', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('1', '2', 'f9d12169-d58e-4892-b4d4-f317fde0e2d0', '0', null, '2', '2018-10-22 18:16:15', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '4', '137df24d-05f0-4018-812f-0ff88df586a5', '0', null, '0', '2018-10-21 15:13:04', null, '2018-10-21 15:13:04', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '13cd0250-9a86-4c04-b1cb-b92d088ec842', '0', null, '2', '2018-12-07 17:06:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '4', '16d73a45-c5a4-4a9c-a0a0-b79865775a86', '0', null, '0', '2018-10-21 15:12:53', null, '2018-10-21 15:12:53', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '17aba7f7-1d42-48f3-a136-934d442ef13c', '0', null, '2', '2018-12-27 16:36:31', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '1843c786-9248-408f-9437-5179ff9aa1c7', '0', null, '2', '2018-12-04 18:47:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '1b265762-dc09-43f0-873f-8d8e6935c18d', '0', null, '2', '2018-11-28 17:37:18', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '1d2a8027-c148-494a-ad14-6395f9205bf9', '0', null, '2', '2018-10-17 15:26:19', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '21ad7f10-3296-46d6-82dd-7e6b1d1377e8', '0', null, '2', '2018-10-18 19:27:42', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '27df990d-0dcd-4e9e-aab9-b792d5764f3b', '0', null, '2', '2018-12-24 17:53:48', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '28a0c52d-362e-4d41-8392-68c7c6b890a6', '0', null, '2', '2018-10-29 17:59:15', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '29c45257-34bd-4cef-8c49-25d4652067e5', '0', null, '2', '2018-11-21 17:18:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '2f0801af-fd2f-40ad-b3da-2a78d050e303', '0', null, '2', '2018-12-13 17:29:17', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '2fc4d22d-f783-412b-a693-45935f0c0f6b', '0', null, '2', '2018-12-03 19:02:55', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '357d9f58-f433-40a7-8d47-b494a35eb00a', '0', null, '2', '2018-12-12 13:40:24', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '3ba58e19-8113-43a0-9ada-08870d1f5a2e', '0', null, '2', '2018-11-08 08:52:32', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '404d8d22-aa3c-431b-80d6-2bbc70903364', '0', null, '2', '2018-11-13 19:16:31', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '420d4d71-9f6d-421b-93b0-67b47fef0a39', '0', null, '2', '2018-11-06 21:36:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '4690bf4b-415f-4bf7-9b03-1fb4b564c9bc', '0', null, '2', '2018-12-10 16:50:52', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '49f68e92-ea63-4258-9486-8aeb537ea703', '0', null, '2', '2018-10-24 17:41:49', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '4c1702c8-0afe-4d39-ba6f-a99892f34b9f', '0', null, '2', '2018-11-15 10:08:42', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '4e997401-1215-48d3-9f17-eba9f4d9f988', '0', null, '2', '2018-12-11 16:46:20', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '4f09f79a-4911-4157-8fd8-fd9d20d78f43', '0', null, '2', '2018-12-14 17:17:08', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '531553a6-0b18-4471-9e04-dc29946df379', '0', null, '2', '2018-10-30 19:16:03', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '4', '5522be72-8df5-41fd-8eaa-798b928e2c4d', '0', null, '0', '2018-10-17 13:13:19', null, '2018-10-17 13:13:19', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '5561f95b-ed08-47fc-8e53-706bd0018d14', '0', null, '2', '2018-12-21 16:36:39', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '55914eed-8a06-4dc7-ab58-389fe49139bf', '0', null, '2', '2018-12-05 17:15:37', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '57bb0dc6-56aa-4078-9672-4d385d15aea2', '0', null, '2', '2018-10-25 17:54:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '5a149828-b614-4ad3-b69a-32f1b0a82691', '0', null, '2', '2018-12-25 15:48:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '5b41f36f-679a-471a-b80b-101f32b2a1ff', '0', null, '2', '2018-10-31 18:39:14', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '66f6e1e2-68ce-45f5-8769-3eb1660ee39a', '0', null, '2', '2018-11-19 17:17:51', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '6c3ca4d9-b328-46ba-a7bc-aec282e3cca3', '0', null, '2', '2018-10-26 17:53:48', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '6f7e4ec5-bbb3-411d-a26c-e30e0812c168', '0', null, '2', '2018-12-17 16:41:35', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '6fd6c786-b889-4537-a53e-e42e735464bd', '0', null, '2', '2018-11-22 19:22:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '7564b019-ed3c-4883-aac4-82908a48d1fc', '0', null, '2', '2018-10-19 09:41:54', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '78361150-ab63-43c9-8bb2-187275a20e8a', '0', null, '2', '2018-11-15 15:14:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '79f63bca-94af-47d2-8ab8-a5b3c07d6241', '0', null, '2', '2018-10-19 09:41:44', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '9171c00b-db0e-463d-9de5-cc7fc5427354', '0', null, '2', '2018-11-23 13:36:27', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '97488b17-77d1-4599-b770-88c4ee192641', '0', null, '2', '2018-12-18 17:12:34', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '4', '992c62d1-d33e-4f56-a129-096873595f81', '0', null, '0', '2018-10-21 15:12:58', null, '2018-10-21 15:12:58', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', '9f7293ef-8cb1-4ab7-937d-df019ecd17af', '0', null, '2', '2018-11-01 17:41:13', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'a66c6bba-8d97-41be-a551-a90dc4fd2eb2', '0', null, '2', '2018-11-05 18:22:36', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'a6d47cf9-f3bc-4c76-a209-6df84b861ead', '0', null, '2', '2018-12-20 17:00:50', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'a9f0dd12-b7eb-4b2a-8d2b-dffeba75a01c', '0', null, '2', '2018-11-02 13:18:29', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '4', 'b88d36d4-937e-4855-8a99-6fcf86b08173', '0', null, '0', '2018-10-21 15:13:07', null, '2018-10-21 15:13:07', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'b9f8d22e-f7d8-4697-a13a-7b730a631889', '0', null, '2', '2018-12-19 17:14:05', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'c38d2ee7-cf60-4dfb-8388-fe9532d1313a', '0', null, '2', '2018-10-19 18:34:12', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'ca2aebb5-d7eb-4de3-a419-9117edf50756', '0', null, '2', '2018-10-23 18:16:45', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'cbaabda9-2617-4542-b1ce-3342fb5c1ef9', '0', null, '2', '2018-12-26 16:29:04', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'd34ce007-204a-4dfa-8834-ec11fcef8b24', '0', null, '2', '2018-12-06 16:51:23', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'e380745f-4aba-44ea-bfe3-1a23a72303f9', '0', null, '2', '2018-10-19 09:41:50', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'e53dc286-dc0f-4d62-900f-1ebaf6d66c43', '0', null, '2', '2018-11-20 17:37:57', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'e85f83c4-f75f-4026-9ab6-57f2e8389879', '0', null, '2', '2018-11-16 16:32:05', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '4', 'e8d5423e-d259-42b6-b147-f9e8e58b2de6', '0', null, '0', '2018-10-21 15:13:11', null, '2018-10-21 15:13:11', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '4', 'f4f23dba-90a5-4e39-a3d1-7e141e74af36', '0', null, '0', '2018-10-17 15:03:29', null, '2018-10-17 15:03:29', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('2', '2', 'f9d12169-d58e-4892-b4d4-f317fde0e2d0', '0', null, '2', '2018-10-22 18:16:15', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '13cd0250-9a86-4c04-b1cb-b92d088ec842', '0', null, '2', '2018-12-07 17:06:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '17aba7f7-1d42-48f3-a136-934d442ef13c', '0', null, '2', '2018-12-27 16:36:31', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '1843c786-9248-408f-9437-5179ff9aa1c7', '0', null, '2', '2018-12-04 18:47:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '1b265762-dc09-43f0-873f-8d8e6935c18d', '1', '2018-11-28 18:07:52', '2', '2018-11-28 17:37:18', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '1d2a8027-c148-494a-ad14-6395f9205bf9', '0', null, '2', '2018-10-17 15:25:44', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '21ad7f10-3296-46d6-82dd-7e6b1d1377e8', '0', null, '2', '2018-10-18 19:27:42', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '27df990d-0dcd-4e9e-aab9-b792d5764f3b', '0', null, '2', '2018-12-24 17:53:48', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '28a0c52d-362e-4d41-8392-68c7c6b890a6', '1', '2018-11-01 17:38:34', '2', '2018-10-29 17:59:10', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '29c45257-34bd-4cef-8c49-25d4652067e5', '0', null, '2', '2018-11-21 17:18:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '2f0801af-fd2f-40ad-b3da-2a78d050e303', '0', null, '2', '2018-12-13 17:29:17', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '2fc4d22d-f783-412b-a693-45935f0c0f6b', '0', null, '2', '2018-12-03 19:02:55', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '357d9f58-f433-40a7-8d47-b494a35eb00a', '0', null, '2', '2018-12-12 13:40:24', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '3ba58e19-8113-43a0-9ada-08870d1f5a2e', '0', null, '2', '2018-11-08 08:52:27', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '404d8d22-aa3c-431b-80d6-2bbc70903364', '0', null, '2', '2018-11-13 19:16:25', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '420d4d71-9f6d-421b-93b0-67b47fef0a39', '0', null, '2', '2018-11-06 21:36:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '4690bf4b-415f-4bf7-9b03-1fb4b564c9bc', '0', null, '2', '2018-12-10 16:50:52', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '49f68e92-ea63-4258-9486-8aeb537ea703', '0', null, '2', '2018-10-24 17:41:46', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '4c1702c8-0afe-4d39-ba6f-a99892f34b9f', '0', null, '2', '2018-11-15 10:08:37', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '4e997401-1215-48d3-9f17-eba9f4d9f988', '0', null, '2', '2018-12-11 16:46:20', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '4f09f79a-4911-4157-8fd8-fd9d20d78f43', '0', null, '2', '2018-12-14 17:17:08', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '531553a6-0b18-4471-9e04-dc29946df379', '1', '2018-11-01 17:38:00', '2', '2018-10-30 19:16:00', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '5561f95b-ed08-47fc-8e53-706bd0018d14', '0', null, '2', '2018-12-21 16:36:39', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '55914eed-8a06-4dc7-ab58-389fe49139bf', '0', null, '2', '2018-12-05 17:15:37', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '57bb0dc6-56aa-4078-9672-4d385d15aea2', '0', null, '2', '2018-10-25 17:54:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '5a149828-b614-4ad3-b69a-32f1b0a82691', '0', null, '2', '2018-12-25 15:48:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '5b41f36f-679a-471a-b80b-101f32b2a1ff', '1', '2018-11-01 17:36:48', '2', '2018-10-31 18:39:11', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '66f6e1e2-68ce-45f5-8769-3eb1660ee39a', '0', null, '2', '2018-11-19 17:17:51', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '6c3ca4d9-b328-46ba-a7bc-aec282e3cca3', '0', null, '2', '2018-10-26 17:53:45', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '6f7e4ec5-bbb3-411d-a26c-e30e0812c168', '0', null, '2', '2018-12-17 16:41:35', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '6fd6c786-b889-4537-a53e-e42e735464bd', '0', null, '2', '2018-11-22 19:22:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '7564b019-ed3c-4883-aac4-82908a48d1fc', '0', null, '2', '2018-10-19 09:41:54', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '78361150-ab63-43c9-8bb2-187275a20e8a', '0', null, '2', '2018-11-15 15:14:21', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '79f63bca-94af-47d2-8ab8-a5b3c07d6241', '0', null, '2', '2018-10-19 09:41:44', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '9171c00b-db0e-463d-9de5-cc7fc5427354', '1', '2018-11-27 11:19:55', '2', '2018-11-23 13:36:27', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '97488b17-77d1-4599-b770-88c4ee192641', '0', null, '2', '2018-12-18 17:12:34', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', '9f7293ef-8cb1-4ab7-937d-df019ecd17af', '0', null, '2', '2018-11-01 17:41:09', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'a66c6bba-8d97-41be-a551-a90dc4fd2eb2', '0', null, '2', '2018-11-05 18:22:33', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'a6d47cf9-f3bc-4c76-a209-6df84b861ead', '0', null, '2', '2018-12-20 17:00:50', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'a9f0dd12-b7eb-4b2a-8d2b-dffeba75a01c', '0', null, '2', '2018-11-02 13:18:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'b9f8d22e-f7d8-4697-a13a-7b730a631889', '0', null, '2', '2018-12-19 17:14:05', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'c38d2ee7-cf60-4dfb-8388-fe9532d1313a', '0', null, '2', '2018-10-19 18:34:06', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'ca2aebb5-d7eb-4de3-a419-9117edf50756', '1', '2018-11-27 09:42:24', '2', '2018-10-23 18:16:41', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'cbaabda9-2617-4542-b1ce-3342fb5c1ef9', '0', null, '2', '2018-12-26 16:29:04', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'd34ce007-204a-4dfa-8834-ec11fcef8b24', '0', null, '2', '2018-12-06 16:51:23', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'e380745f-4aba-44ea-bfe3-1a23a72303f9', '0', null, '2', '2018-10-19 09:41:50', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'e53dc286-dc0f-4d62-900f-1ebaf6d66c43', '0', null, '2', '2018-11-20 17:37:57', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'e85f83c4-f75f-4026-9ab6-57f2e8389879', '0', null, '2', '2018-11-16 16:32:01', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '4', 'f4f23dba-90a5-4e39-a3d1-7e141e74af36', '1', '2018-10-18 16:22:08', '0', '2018-10-17 15:03:29', null, '2018-10-17 15:03:29', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('3', '2', 'f9d12169-d58e-4892-b4d4-f317fde0e2d0', '1', '2018-10-23 13:35:07', '2', '2018-10-22 18:16:11', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '13cd0250-9a86-4c04-b1cb-b92d088ec842', '0', null, '2', '2018-12-07 17:06:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '17aba7f7-1d42-48f3-a136-934d442ef13c', '0', null, '2', '2018-12-27 16:36:31', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '1843c786-9248-408f-9437-5179ff9aa1c7', '0', null, '2', '2018-12-04 18:47:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '1b265762-dc09-43f0-873f-8d8e6935c18d', '0', null, '2', '2018-11-28 17:37:18', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '1d2a8027-c148-494a-ad14-6395f9205bf9', '0', null, '2', '2018-10-17 15:25:44', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '21ad7f10-3296-46d6-82dd-7e6b1d1377e8', '0', null, '2', '2018-10-18 19:27:42', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '27df990d-0dcd-4e9e-aab9-b792d5764f3b', '0', null, '2', '2018-12-24 17:53:48', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '28a0c52d-362e-4d41-8392-68c7c6b890a6', '0', null, '2', '2018-10-29 17:59:10', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '29c45257-34bd-4cef-8c49-25d4652067e5', '0', null, '2', '2018-11-21 17:18:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '2f0801af-fd2f-40ad-b3da-2a78d050e303', '0', null, '2', '2018-12-13 17:29:17', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '2fc4d22d-f783-412b-a693-45935f0c0f6b', '0', null, '2', '2018-12-03 19:02:55', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '357d9f58-f433-40a7-8d47-b494a35eb00a', '0', null, '2', '2018-12-12 13:40:24', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '3ba58e19-8113-43a0-9ada-08870d1f5a2e', '0', null, '2', '2018-11-08 08:52:27', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '404d8d22-aa3c-431b-80d6-2bbc70903364', '0', null, '2', '2018-11-13 19:16:25', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '420d4d71-9f6d-421b-93b0-67b47fef0a39', '0', null, '2', '2018-11-06 21:36:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '4690bf4b-415f-4bf7-9b03-1fb4b564c9bc', '0', null, '2', '2018-12-10 16:50:52', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '49f68e92-ea63-4258-9486-8aeb537ea703', '0', null, '2', '2018-10-24 17:41:46', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '4c1702c8-0afe-4d39-ba6f-a99892f34b9f', '0', null, '2', '2018-11-15 10:08:37', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '4e997401-1215-48d3-9f17-eba9f4d9f988', '0', null, '2', '2018-12-11 16:46:20', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '4f09f79a-4911-4157-8fd8-fd9d20d78f43', '0', null, '2', '2018-12-14 17:17:08', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '531553a6-0b18-4471-9e04-dc29946df379', '0', null, '2', '2018-10-30 19:16:00', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '5561f95b-ed08-47fc-8e53-706bd0018d14', '0', null, '2', '2018-12-21 16:36:39', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '55914eed-8a06-4dc7-ab58-389fe49139bf', '0', null, '2', '2018-12-05 17:15:37', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '57bb0dc6-56aa-4078-9672-4d385d15aea2', '0', null, '2', '2018-10-25 17:54:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '5a149828-b614-4ad3-b69a-32f1b0a82691', '0', null, '2', '2018-12-25 15:48:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '5b41f36f-679a-471a-b80b-101f32b2a1ff', '0', null, '2', '2018-10-31 18:39:11', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '66f6e1e2-68ce-45f5-8769-3eb1660ee39a', '0', null, '2', '2018-11-19 17:17:51', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '6c3ca4d9-b328-46ba-a7bc-aec282e3cca3', '0', null, '2', '2018-10-26 17:53:45', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '6f7e4ec5-bbb3-411d-a26c-e30e0812c168', '0', null, '2', '2018-12-17 16:41:35', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '6fd6c786-b889-4537-a53e-e42e735464bd', '0', null, '2', '2018-11-22 19:22:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '7564b019-ed3c-4883-aac4-82908a48d1fc', '0', null, '2', '2018-10-19 09:41:54', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '78361150-ab63-43c9-8bb2-187275a20e8a', '0', null, '2', '2018-11-15 15:14:21', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '79f63bca-94af-47d2-8ab8-a5b3c07d6241', '0', null, '2', '2018-10-19 09:41:44', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '9171c00b-db0e-463d-9de5-cc7fc5427354', '0', null, '2', '2018-11-23 13:36:27', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '97488b17-77d1-4599-b770-88c4ee192641', '0', null, '2', '2018-12-18 17:12:34', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', '9f7293ef-8cb1-4ab7-937d-df019ecd17af', '0', null, '2', '2018-11-01 17:41:09', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'a66c6bba-8d97-41be-a551-a90dc4fd2eb2', '0', null, '2', '2018-11-05 18:22:33', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'a6d47cf9-f3bc-4c76-a209-6df84b861ead', '0', null, '2', '2018-12-20 17:00:50', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'a9f0dd12-b7eb-4b2a-8d2b-dffeba75a01c', '0', null, '2', '2018-11-02 13:18:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'b9f8d22e-f7d8-4697-a13a-7b730a631889', '0', null, '2', '2018-12-19 17:14:05', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'c38d2ee7-cf60-4dfb-8388-fe9532d1313a', '0', null, '2', '2018-10-19 18:34:06', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'ca2aebb5-d7eb-4de3-a419-9117edf50756', '0', null, '2', '2018-10-23 18:16:41', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'cbaabda9-2617-4542-b1ce-3342fb5c1ef9', '0', null, '2', '2018-12-26 16:29:04', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'd34ce007-204a-4dfa-8834-ec11fcef8b24', '0', null, '2', '2018-12-06 16:51:23', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'e380745f-4aba-44ea-bfe3-1a23a72303f9', '0', null, '2', '2018-10-19 09:41:50', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'e53dc286-dc0f-4d62-900f-1ebaf6d66c43', '0', null, '2', '2018-11-20 17:37:57', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'e85f83c4-f75f-4026-9ab6-57f2e8389879', '0', null, '2', '2018-11-16 16:32:01', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('4', '2', 'f9d12169-d58e-4892-b4d4-f317fde0e2d0', '0', null, '2', '2018-10-22 18:16:11', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', '02d1df74-d427-44f8-bf16-dcefc8254e46', '1', '2018-10-21 14:30:15', '0', '2018-10-21 14:27:10', null, '2018-10-21 14:27:10', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', '137df24d-05f0-4018-812f-0ff88df586a5', '1', '2018-10-22 21:20:24', '0', '2018-10-21 15:13:04', null, '2018-10-21 15:13:04', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '13cd0250-9a86-4c04-b1cb-b92d088ec842', '0', null, '2', '2018-12-07 17:06:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', '16d73a45-c5a4-4a9c-a0a0-b79865775a86', '1', '2018-10-22 21:20:33', '0', '2018-10-21 15:12:53', null, '2018-10-21 15:12:53', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '17aba7f7-1d42-48f3-a136-934d442ef13c', '0', null, '2', '2018-12-27 16:36:31', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '1843c786-9248-408f-9437-5179ff9aa1c7', '0', null, '2', '2018-12-04 18:47:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '1b265762-dc09-43f0-873f-8d8e6935c18d', '0', null, '2', '2018-11-28 17:37:18', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '1d2a8027-c148-494a-ad14-6395f9205bf9', '1', '2018-10-20 09:34:19', '2', '2018-10-17 15:25:44', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '21ad7f10-3296-46d6-82dd-7e6b1d1377e8', '1', '2018-10-20 09:33:46', '2', '2018-10-18 19:27:42', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', '2794ae82-03c1-42ec-bde6-03bdbae0f9dc', '1', '2018-10-21 14:46:24', '0', '2018-10-21 12:25:57', null, '2018-10-21 12:25:57', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '27df990d-0dcd-4e9e-aab9-b792d5764f3b', '0', null, '2', '2018-12-24 17:53:48', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '28a0c52d-362e-4d41-8392-68c7c6b890a6', '0', null, '2', '2018-10-29 17:59:10', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '29c45257-34bd-4cef-8c49-25d4652067e5', '0', null, '2', '2018-11-21 17:18:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', '2a6e4791-1221-436b-a09b-97a5d88fbb06', '1', '2018-10-21 14:46:14', '0', '2018-10-21 14:27:07', null, '2018-10-21 14:27:07', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '2f0801af-fd2f-40ad-b3da-2a78d050e303', '0', null, '2', '2018-12-13 17:29:17', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '2fc4d22d-f783-412b-a693-45935f0c0f6b', '0', null, '2', '2018-12-03 19:02:55', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '357d9f58-f433-40a7-8d47-b494a35eb00a', '0', null, '2', '2018-12-12 13:40:24', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '3ba58e19-8113-43a0-9ada-08870d1f5a2e', '1', '2018-11-08 15:39:02', '2', '2018-11-08 08:52:32', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '404d8d22-aa3c-431b-80d6-2bbc70903364', '0', null, '2', '2018-11-13 19:16:25', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '420d4d71-9f6d-421b-93b0-67b47fef0a39', '0', null, '2', '2018-11-06 21:36:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '4690bf4b-415f-4bf7-9b03-1fb4b564c9bc', '0', null, '2', '2018-12-10 16:50:52', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '49f68e92-ea63-4258-9486-8aeb537ea703', '1', '2018-10-26 15:50:23', '2', '2018-10-24 17:41:46', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '4c1702c8-0afe-4d39-ba6f-a99892f34b9f', '0', null, '2', '2018-11-15 10:08:37', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '4e997401-1215-48d3-9f17-eba9f4d9f988', '0', null, '2', '2018-12-11 16:46:20', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '4f09f79a-4911-4157-8fd8-fd9d20d78f43', '0', null, '2', '2018-12-14 17:17:08', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '531553a6-0b18-4471-9e04-dc29946df379', '0', null, '2', '2018-10-30 19:16:00', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', '5522be72-8df5-41fd-8eaa-798b928e2c4d', '1', '2018-10-17 15:04:04', '0', '2018-10-17 13:13:19', null, '2018-10-17 13:13:19', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '5561f95b-ed08-47fc-8e53-706bd0018d14', '0', null, '2', '2018-12-21 16:36:39', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '55914eed-8a06-4dc7-ab58-389fe49139bf', '0', null, '2', '2018-12-05 17:15:37', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '57bb0dc6-56aa-4078-9672-4d385d15aea2', '1', '2018-10-26 15:49:56', '2', '2018-10-25 17:54:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '5a149828-b614-4ad3-b69a-32f1b0a82691', '0', null, '2', '2018-12-25 15:48:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '5b41f36f-679a-471a-b80b-101f32b2a1ff', '0', null, '2', '2018-10-31 18:39:11', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '66f6e1e2-68ce-45f5-8769-3eb1660ee39a', '0', null, '2', '2018-11-19 17:17:51', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '6c3ca4d9-b328-46ba-a7bc-aec282e3cca3', '0', null, '2', '2018-10-26 17:53:45', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '6f7e4ec5-bbb3-411d-a26c-e30e0812c168', '0', null, '2', '2018-12-17 16:41:35', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '6fd6c786-b889-4537-a53e-e42e735464bd', '0', null, '2', '2018-11-22 19:22:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '7564b019-ed3c-4883-aac4-82908a48d1fc', '1', '2018-10-20 09:33:16', '2', '2018-10-19 09:41:54', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '78361150-ab63-43c9-8bb2-187275a20e8a', '0', null, '2', '2018-11-15 15:14:21', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '79f63bca-94af-47d2-8ab8-a5b3c07d6241', '1', '2018-10-20 09:33:40', '2', '2018-10-19 09:41:44', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', '84599966-defd-4917-9eaf-0e26cfeb7fdf', '1', '2018-10-20 18:17:02', '0', '2018-10-20 18:16:48', null, '2018-10-20 18:16:48', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '9171c00b-db0e-463d-9de5-cc7fc5427354', '0', null, '2', '2018-11-23 13:36:27', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '97488b17-77d1-4599-b770-88c4ee192641', '0', null, '2', '2018-12-18 17:12:34', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', '992c62d1-d33e-4f56-a129-096873595f81', '1', '2018-10-22 21:20:31', '0', '2018-10-21 15:12:58', null, '2018-10-21 15:12:58', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', '9f7293ef-8cb1-4ab7-937d-df019ecd17af', '0', null, '2', '2018-11-01 17:41:09', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', 'a4615074-6b3c-4a7b-bbe0-8b05f294dcbd', '1', '2018-10-21 14:30:06', '0', '2018-10-21 14:27:19', null, '2018-10-21 14:27:19', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'a66c6bba-8d97-41be-a551-a90dc4fd2eb2', '0', null, '2', '2018-11-05 18:22:33', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'a6d47cf9-f3bc-4c76-a209-6df84b861ead', '0', null, '2', '2018-12-20 17:00:50', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'a9f0dd12-b7eb-4b2a-8d2b-dffeba75a01c', '0', null, '2', '2018-11-02 13:18:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', 'b88d36d4-937e-4855-8a99-6fcf86b08173', '1', '2018-10-21 15:13:59', '0', '2018-10-21 15:13:07', null, '2018-10-21 15:13:07', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'b9f8d22e-f7d8-4697-a13a-7b730a631889', '0', null, '2', '2018-12-19 17:14:05', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', 'bb103ae3-2cc5-4608-a889-113a802f70ee', '1', '2018-10-21 14:41:02', '0', '2018-10-21 12:26:00', null, '2018-10-21 12:26:00', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'c38d2ee7-cf60-4dfb-8388-fe9532d1313a', '1', '2018-10-20 09:32:52', '2', '2018-10-19 18:34:06', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'ca2aebb5-d7eb-4de3-a419-9117edf50756', '1', '2018-10-26 15:51:15', '2', '2018-10-23 18:16:41', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'cbaabda9-2617-4542-b1ce-3342fb5c1ef9', '0', null, '2', '2018-12-26 16:29:04', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'd34ce007-204a-4dfa-8834-ec11fcef8b24', '0', null, '2', '2018-12-06 16:51:23', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'e380745f-4aba-44ea-bfe3-1a23a72303f9', '1', '2018-10-20 09:33:31', '2', '2018-10-19 09:41:50', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'e53dc286-dc0f-4d62-900f-1ebaf6d66c43', '1', '2018-12-18 09:52:54', '2', '2018-11-20 17:37:57', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'e85f83c4-f75f-4026-9ab6-57f2e8389879', '0', null, '2', '2018-11-16 16:32:01', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', 'e8d5423e-d259-42b6-b147-f9e8e58b2de6', '1', '2018-10-21 15:13:27', '0', '2018-10-21 15:13:11', null, '2018-10-21 15:13:11', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', 'e90ad1c3-13ed-460c-a4cf-4dcee98b40d0', '1', '2018-10-20 18:17:10', '0', '2018-10-20 18:16:47', null, '2018-10-20 18:16:47', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '4', 'f4f23dba-90a5-4e39-a3d1-7e141e74af36', '1', '2018-10-17 15:03:58', '0', '2018-10-17 15:03:29', null, '2018-10-17 15:03:29', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('5', '2', 'f9d12169-d58e-4892-b4d4-f317fde0e2d0', '1', '2018-10-22 21:14:29', '2', '2018-10-22 18:16:11', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', '02d1df74-d427-44f8-bf16-dcefc8254e46', '0', null, '0', '2018-10-21 14:27:10', null, '2018-10-21 14:27:10', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', '137df24d-05f0-4018-812f-0ff88df586a5', '0', null, '0', '2018-10-21 15:13:04', null, '2018-10-21 15:13:04', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '13cd0250-9a86-4c04-b1cb-b92d088ec842', '0', null, '2', '2018-12-07 17:06:43', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', '16d73a45-c5a4-4a9c-a0a0-b79865775a86', '0', null, '0', '2018-10-21 15:12:53', null, '2018-10-21 15:12:53', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '17aba7f7-1d42-48f3-a136-934d442ef13c', '0', null, '2', '2018-12-27 16:36:35', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '1843c786-9248-408f-9437-5179ff9aa1c7', '0', null, '2', '2018-12-04 18:47:43', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '1b265762-dc09-43f0-873f-8d8e6935c18d', '0', null, '2', '2018-11-28 17:37:22', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '1d2a8027-c148-494a-ad14-6395f9205bf9', '1', '2018-10-17 15:37:59', '2', '2018-10-17 15:26:19', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '21ad7f10-3296-46d6-82dd-7e6b1d1377e8', '0', null, '2', '2018-10-18 19:27:48', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', '2794ae82-03c1-42ec-bde6-03bdbae0f9dc', '0', null, '0', '2018-10-21 12:25:57', null, '2018-10-21 12:25:57', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '27df990d-0dcd-4e9e-aab9-b792d5764f3b', '0', null, '2', '2018-12-24 17:53:52', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '28a0c52d-362e-4d41-8392-68c7c6b890a6', '0', null, '2', '2018-10-29 17:59:15', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '29c45257-34bd-4cef-8c49-25d4652067e5', '0', null, '2', '2018-11-21 17:18:44', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', '2a6e4791-1221-436b-a09b-97a5d88fbb06', '0', null, '0', '2018-10-21 14:27:07', null, '2018-10-21 14:27:07', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '2f0801af-fd2f-40ad-b3da-2a78d050e303', '0', null, '2', '2018-12-13 17:29:21', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '2fc4d22d-f783-412b-a693-45935f0c0f6b', '0', null, '2', '2018-12-03 19:02:58', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '357d9f58-f433-40a7-8d47-b494a35eb00a', '0', null, '2', '2018-12-12 13:40:28', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '3ba58e19-8113-43a0-9ada-08870d1f5a2e', '0', null, '2', '2018-11-08 08:52:32', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '404d8d22-aa3c-431b-80d6-2bbc70903364', '0', null, '2', '2018-11-13 19:16:31', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '420d4d71-9f6d-421b-93b0-67b47fef0a39', '0', null, '2', '2018-11-06 21:36:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '4690bf4b-415f-4bf7-9b03-1fb4b564c9bc', '0', null, '2', '2018-12-10 16:50:55', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '49f68e92-ea63-4258-9486-8aeb537ea703', '0', null, '2', '2018-10-24 17:41:49', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '4c1702c8-0afe-4d39-ba6f-a99892f34b9f', '0', null, '2', '2018-11-15 10:08:42', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '4e997401-1215-48d3-9f17-eba9f4d9f988', '0', null, '2', '2018-12-11 16:46:23', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '4f09f79a-4911-4157-8fd8-fd9d20d78f43', '0', null, '2', '2018-12-14 17:17:11', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '531553a6-0b18-4471-9e04-dc29946df379', '0', null, '2', '2018-10-30 19:16:03', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', '5522be72-8df5-41fd-8eaa-798b928e2c4d', '1', '2018-10-17 15:13:43', '0', '2018-10-17 13:13:19', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '5561f95b-ed08-47fc-8e53-706bd0018d14', '0', null, '2', '2018-12-21 16:36:43', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '55914eed-8a06-4dc7-ab58-389fe49139bf', '0', null, '2', '2018-12-05 17:15:40', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '57bb0dc6-56aa-4078-9672-4d385d15aea2', '0', null, '2', '2018-10-25 17:54:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '5a149828-b614-4ad3-b69a-32f1b0a82691', '0', null, '2', '2018-12-25 15:48:30', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '5b41f36f-679a-471a-b80b-101f32b2a1ff', '0', null, '2', '2018-10-31 18:39:14', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '66f6e1e2-68ce-45f5-8769-3eb1660ee39a', '0', null, '2', '2018-11-19 17:17:55', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '6c3ca4d9-b328-46ba-a7bc-aec282e3cca3', '0', null, '2', '2018-10-26 17:53:48', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '6f7e4ec5-bbb3-411d-a26c-e30e0812c168', '0', null, '2', '2018-12-17 16:41:38', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '6fd6c786-b889-4537-a53e-e42e735464bd', '0', null, '2', '2018-11-22 19:22:25', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '7564b019-ed3c-4883-aac4-82908a48d1fc', '0', null, '2', '2018-10-19 09:42:00', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '78361150-ab63-43c9-8bb2-187275a20e8a', '0', null, '2', '2018-11-15 15:14:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '79f63bca-94af-47d2-8ab8-a5b3c07d6241', '0', null, '2', '2018-10-19 09:42:00', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', '84599966-defd-4917-9eaf-0e26cfeb7fdf', '0', null, '0', '2018-10-20 18:16:48', null, '2018-10-20 18:16:48', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '9171c00b-db0e-463d-9de5-cc7fc5427354', '0', null, '2', '2018-11-23 13:36:32', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '97488b17-77d1-4599-b770-88c4ee192641', '0', null, '2', '2018-12-18 17:12:37', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', '992c62d1-d33e-4f56-a129-096873595f81', '0', null, '0', '2018-10-21 15:12:58', null, '2018-10-21 15:12:58', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', '9f7293ef-8cb1-4ab7-937d-df019ecd17af', '0', null, '2', '2018-11-01 17:41:13', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', 'a4615074-6b3c-4a7b-bbe0-8b05f294dcbd', '0', null, '0', '2018-10-21 14:27:19', null, '2018-10-21 14:27:19', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'a66c6bba-8d97-41be-a551-a90dc4fd2eb2', '0', null, '2', '2018-11-05 18:22:36', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'a6d47cf9-f3bc-4c76-a209-6df84b861ead', '0', null, '2', '2018-12-20 17:00:53', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'a9f0dd12-b7eb-4b2a-8d2b-dffeba75a01c', '0', null, '2', '2018-11-02 13:18:29', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', 'b88d36d4-937e-4855-8a99-6fcf86b08173', '0', null, '0', '2018-10-21 15:13:07', null, '2018-10-21 15:13:07', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'b9f8d22e-f7d8-4697-a13a-7b730a631889', '0', null, '2', '2018-12-19 17:14:08', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', 'bb103ae3-2cc5-4608-a889-113a802f70ee', '0', null, '0', '2018-10-21 12:26:00', null, '2018-10-21 12:26:00', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'c38d2ee7-cf60-4dfb-8388-fe9532d1313a', '0', null, '2', '2018-10-19 18:34:12', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'ca2aebb5-d7eb-4de3-a419-9117edf50756', '0', null, '2', '2018-10-23 18:16:45', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'cbaabda9-2617-4542-b1ce-3342fb5c1ef9', '0', null, '2', '2018-12-26 16:29:07', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'd34ce007-204a-4dfa-8834-ec11fcef8b24', '0', null, '2', '2018-12-06 16:51:26', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'e380745f-4aba-44ea-bfe3-1a23a72303f9', '0', null, '2', '2018-10-19 09:42:00', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'e53dc286-dc0f-4d62-900f-1ebaf6d66c43', '0', null, '2', '2018-11-20 17:38:01', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'e85f83c4-f75f-4026-9ab6-57f2e8389879', '0', null, '2', '2018-11-16 16:32:05', null, null, '0', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', 'e8d5423e-d259-42b6-b147-f9e8e58b2de6', '0', null, '0', '2018-10-21 15:13:11', null, '2018-10-21 15:13:11', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', 'e90ad1c3-13ed-460c-a4cf-4dcee98b40d0', '0', null, '0', '2018-10-20 18:16:47', null, '2018-10-20 18:16:47', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '4', 'f4f23dba-90a5-4e39-a3d1-7e141e74af36', '1', '2018-10-17 15:08:19', '0', '2018-10-17 15:03:29', null, '2018-10-17 15:03:29', '7', '0', '0', '1');
INSERT INTO `customerreport` VALUES ('6', '2', 'f9d12169-d58e-4892-b4d4-f317fde0e2d0', '0', null, '2', '2018-10-22 18:16:15', null, null, '0', '0', '0', '1');

-- ----------------------------
-- Table structure for customersatisfaction
-- ----------------------------
DROP TABLE IF EXISTS `customersatisfaction`;
CREATE TABLE `customersatisfaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `customername` varchar(255) NOT NULL,
  `createtime` datetime NOT NULL,
  `comments` varchar(255) NOT NULL DEFAULT '',
  `satisfaction` tinyint(4) NOT NULL COMMENT '0为吐槽，1为满意',
  `phonenum` varchar(255) NOT NULL DEFAULT '' COMMENT '电话号码',
  `pictureurl` varchar(1024) NOT NULL DEFAULT '' COMMENT '图片url',
  `itemlist` varchar(255) NOT NULL DEFAULT '' COMMENT '预设的问题反馈列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customersatisfaction
-- ----------------------------
INSERT INTO `customersatisfaction` VALUES ('1', '3', 'ZNGDIOS', '2018-10-15 14:06:46', '', '1', '', '', '');
INSERT INTO `customersatisfaction` VALUES ('2', '5', 'wangguotao', '2018-10-15 16:13:34', '', '1', '', '', '');
INSERT INTO `customersatisfaction` VALUES ('3', '6', 'sitetest', '2018-10-17 15:38:48', '', '1', '', '', '');
INSERT INTO `customersatisfaction` VALUES ('4', '6', 'sitetest', '2018-10-17 19:31:55', '', '1', '', '', '');
INSERT INTO `customersatisfaction` VALUES ('5', '3', 'ZNGDIOS', '2018-11-01 17:38:08', '', '1', '', '', '');
INSERT INTO `customersatisfaction` VALUES ('6', '3', 'ZNGDIOS', '2018-11-27 09:43:05', '', '1', '', '', '');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `parentid` int(11) NOT NULL DEFAULT '0',
  `outletid` int(11) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`outletid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('3', '项目作业班组', '0', '1', '_3_', '0');
INSERT INTO `department` VALUES ('4', '综合班组', '3', '1', '_3_4_', '1');
INSERT INTO `department` VALUES ('5', '临建班组', '3', '1', '_3_5_', '1');
INSERT INTO `department` VALUES ('6', '围护结构班组', '3', '1', '_3_6_', '1');
INSERT INTO `department` VALUES ('7', '地基加固班组', '3', '1', '_3_7_', '1');
INSERT INTO `department` VALUES ('8', '降水班组', '3', '1', '_3_8_', '1');
INSERT INTO `department` VALUES ('9', '开挖班组', '3', '1', '_3_9_', '1');
INSERT INTO `department` VALUES ('10', '钢支撑班组', '3', '1', '_3_10_', '1');
INSERT INTO `department` VALUES ('11', '主体结构班组', '3', '1', '_3_11_', '1');
INSERT INTO `department` VALUES ('12', '盾构班组', '3', '1', '_3_12_', '1');
INSERT INTO `department` VALUES ('13', '项目领导班子', '0', '1', '_13_', '0');
INSERT INTO `department` VALUES ('14', '项目前勤值班', '0', '1', '_14_', '0');
INSERT INTO `department` VALUES ('15', '领导班子', '0', '2', '_15_', '0');
INSERT INTO `department` VALUES ('16', '现场', '0', '2', '_16_', '0');
INSERT INTO `department` VALUES ('17', '安质部', '0', '2', '_17_', '0');
INSERT INTO `department` VALUES ('18', '物机部', '0', '2', '_18_', '0');
INSERT INTO `department` VALUES ('19', '工程部', '0', '2', '_19_', '0');
INSERT INTO `department` VALUES ('20', '测量组', '0', '2', '_20_', '0');
INSERT INTO `department` VALUES ('21', '试验室', '0', '2', '_21_', '0');
INSERT INTO `department` VALUES ('22', '财务部', '0', '2', '_22_', '0');
INSERT INTO `department` VALUES ('23', '工经部', '0', '2', '_23_', '0');
INSERT INTO `department` VALUES ('24', '综合办公室', '0', '2', '_24_', '0');
INSERT INTO `department` VALUES ('25', '项目作业班组', '0', '2', '_25_', '0');

-- ----------------------------
-- Table structure for deviceauthoritytypes
-- ----------------------------
DROP TABLE IF EXISTS `deviceauthoritytypes`;
CREATE TABLE `deviceauthoritytypes` (
  `AuthorityId` int(10) NOT NULL AUTO_INCREMENT,
  `AuthorityName` varchar(256) NOT NULL,
  `AuthorityType` int(10) NOT NULL,
  PRIMARY KEY (`AuthorityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of deviceauthoritytypes
-- ----------------------------

-- ----------------------------
-- Table structure for deviceexceptionevent
-- ----------------------------
DROP TABLE IF EXISTS `deviceexceptionevent`;
CREATE TABLE `deviceexceptionevent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `EventTime` datetime NOT NULL,
  `DeviceNo` int(11) DEFAULT '0',
  `DeviceName` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of deviceexceptionevent
-- ----------------------------

-- ----------------------------
-- Table structure for devicemodel
-- ----------------------------
DROP TABLE IF EXISTS `devicemodel`;
CREATE TABLE `devicemodel` (
  `ModelName` varchar(45) NOT NULL,
  `DeviceType` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ModelName`),
  UNIQUE KEY `ModelName_UNIQUE` (`ModelName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of devicemodel
-- ----------------------------
INSERT INTO `devicemodel` VALUES ('DAHUA device', '5');
INSERT INTO `devicemodel` VALUES ('EZVIZ device', '11');
INSERT INTO `devicemodel` VALUES ('Freedom server', '2');
INSERT INTO `devicemodel` VALUES ('HIK-LS device', '12');
INSERT INTO `devicemodel` VALUES ('Hikvision device', '4');
INSERT INTO `devicemodel` VALUES ('lechange device', '13');
INSERT INTO `devicemodel` VALUES ('ONVIF IPC', '10');
INSERT INTO `devicemodel` VALUES ('Sony IPC', '6');

-- ----------------------------
-- Table structure for devicestatus
-- ----------------------------
DROP TABLE IF EXISTS `devicestatus`;
CREATE TABLE `devicestatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `deviceno` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `description` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of devicestatus
-- ----------------------------

-- ----------------------------
-- Table structure for emailinfo
-- ----------------------------
DROP TABLE IF EXISTS `emailinfo`;
CREATE TABLE `emailinfo` (
  `Smtp` varchar(64) DEFAULT NULL,
  `useSSL` int(11) DEFAULT NULL,
  `serverport` int(11) DEFAULT NULL,
  `EmailAddress` varchar(64) DEFAULT NULL,
  `EmailPassword` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emailinfo
-- ----------------------------

-- ----------------------------
-- Table structure for emap
-- ----------------------------
DROP TABLE IF EXISTS `emap`;
CREATE TABLE `emap` (
  `MapId` int(11) NOT NULL AUTO_INCREMENT,
  `MapName` varchar(256) DEFAULT NULL,
  `MapObject` longblob,
  `MD5` varchar(128) NOT NULL DEFAULT '',
  PRIMARY KEY (`MapId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of emap
-- ----------------------------

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `cardid` varchar(255) NOT NULL,
  `departmentid` int(11) NOT NULL DEFAULT '0',
  `jobid` int(11) NOT NULL DEFAULT '0',
  `phone` varchar(255) NOT NULL DEFAULT '',
  `alternative` varchar(255) NOT NULL DEFAULT '',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '1.黑名单，2.白名单',
  `remarks` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `headurl` varchar(255) NOT NULL DEFAULT '' COMMENT '头像图片地址',
  `sex` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别：1.男，2.女',
  `outletid` int(11) NOT NULL DEFAULT '0',
  `deviceid` varchar(255) NOT NULL COMMENT '员工信息来源设备id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_unique` (`outletid`,`cardid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '余华海', '0001', '13', '1', '', '', '0', '', '', '0', '1', 'DS-K5603-H20180508V010001CHC47700946');
INSERT INTO `employee` VALUES ('2', 'caowangsheng', '00010', '4', '0', '', '', '0', '', '', '0', '1', 'DS-K5603-H20180508V010001CHC47700946');
INSERT INTO `employee` VALUES ('3', '吴涛', '0002', '14', '4', '', '', '0', '', '', '0', '1', 'DS-K5603-H20180508V010001CHC47700946');
INSERT INTO `employee` VALUES ('4', 'jiagfabo', '0004', '4', '0', '', '', '0', '', '', '0', '1', 'DS-K5603-H20180508V010001CHC47700946');
INSERT INTO `employee` VALUES ('5', '董液封', '0005', '14', '3', '', '', '0', '', '', '0', '1', 'DS-K5603-H20180508V010001CHC47700946');
INSERT INTO `employee` VALUES ('12', 'bbbb', '11111111', '0', '0', '', '', '0', '', '', '0', '1', 'DS-2CD8627FXD-B20181117AACH225891559');
INSERT INTO `employee` VALUES ('13', 'name1', '123456781', '0', '0', '', '', '0', '', '', '0', '1', 'DS-2CD8627FXD-B20181117AACH225891559');
INSERT INTO `employee` VALUES ('14', 'name2', '123456782', '0', '0', '', '', '0', '', '', '0', '1', 'DS-2CD8627FXD-B20181117AACH225891559');
INSERT INTO `employee` VALUES ('15', 'name3', '123456783', '0', '0', '', '', '0', '', '', '0', '1', 'DS-2CD8627FXD-B20181117AACH225891559');
INSERT INTO `employee` VALUES ('16', 'name4', '123456784', '0', '0', '', '', '0', '', '', '0', '1', 'DS-2CD8627FXD-B20181117AACH225891559');
INSERT INTO `employee` VALUES ('17', 'name5', '123456785', '0', '0', '', '', '0', '', '', '0', '1', 'DS-2CD8627FXD-B20181117AACH225891559');
INSERT INTO `employee` VALUES ('18', 'name6', '123456786', '0', '0', '', '', '0', '', '', '0', '1', 'DS-2CD8627FXD-B20181117AACH225891559');
INSERT INTO `employee` VALUES ('19', 'name7', '123456787', '0', '0', '', '', '0', '', '', '0', '1', 'DS-2CD8627FXD-B20181117AACH225891559');
INSERT INTO `employee` VALUES ('20', '名字1', '123456788', '0', '0', '', '', '0', '', '', '0', '1', 'DS-2CD8627FXD-B20181117AACH225891559');
INSERT INTO `employee` VALUES ('21', 'jiangfabo', '12345690', '0', '0', '', '', '0', '', '', '0', '1', 'DS-2CD8627FXD-B20181117AACH225891559');
INSERT INTO `employee` VALUES ('31', '郭鑫', '00000010', '0', '0', '', '', '0', '', '/resource/getImage/20181228/c35a3234-5020-4bbb-af81-bc84061dac1c.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('33', '李进龙', '00000012', '21', '29', '', '', '0', '', '/resource/getImage/20181228/854fcd48-4920-42b4-be4d-7d103093f2a0.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('53', '余华海', '340825198705241038', '0', '0', '', '', '0', '', '/resource/getImage/20181228/19f6a8ae-897f-4139-a371-8ce4f6e341b0.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('56', '王浩杰', '140521199706117616', '20', '18', '', '', '0', '', '/resource/getImage/20181228/08227496-15b9-40ef-adfa-94a9289d2497.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('57', '陈红斌', '330227197312224257', '15', '5', '', '', '0', '', '/resource/getImage/20181228/8da5ae48-4ebe-473f-933a-e6e49550f2b5.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('58', '冯洋', '330621199104053791', '23', '23', '', '', '0', '', '/resource/getImage/20181228/273a7ac0-c1a9-422a-8910-181957f07e3a.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('59', '胡红华', '330781199411152011', '19', '7', '', '', '0', '', '/resource/getImage/20181228/f4657178-6c0a-410b-b88e-8a08f1ca4a5e.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('60', '黄宁', '330825199010100311', '19', '33', '', '', '0', '', '/resource/getImage/20181228/b48ed5fb-f280-404f-ac18-0fe2086a69ee.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('62', '谷国想', '37292319910422531X', '20', '28', '', '', '0', '', '/resource/getImage/20181228/ea99eea0-7d12-4deb-b667-a48f3b0ae869.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('63', '张翼', '410303198112191517', '17', '15', '', '', '0', '', '/resource/getImage/20181228/e5aa2f54-2593-4d7d-b1de-8e002240522e.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('64', '董梅迪', '410303199301083720', '23', '34', '', '', '0', '', '/resource/getImage/20181228/e596a7da-54b9-41bc-8d21-96b33d8b8c23.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('65', '李颖超', '410326198812100035', '17', '17', '', '', '0', '', '/resource/getImage/20181228/5d0ee01b-4137-4e03-8532-0d652d8678a1.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('66', '李志凯', '410521198710150196', '17', '16', '', '', '0', '', '/resource/getImage/20181228/9bc08459-029a-4807-8107-6fae47c414de.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('67', '王锦坤', '41052199606150051', '24', '36', '', '', '0', '', '/resource/getImage/20181228/4d085639-ce00-402a-a566-6d603ee0268d.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('68', '马腾飞', '411281199308173010', '18', '20', '', '', '0', '', '/resource/getImage/20181228/b552abf7-c684-4dc2-9af1-56ccbe0dfd39.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('69', '彭武奎', '420982198405030012', '15', '12', '', '', '0', '', '/resource/getImage/20181228/d98f32c3-30ac-4269-86b9-6e2bad1eebd1.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('70', '段国雄', '440225196502090470', '15', '9', '', '', '0', '', '/resource/getImage/20181228/efe4a41c-78b2-4c9d-9e2d-d0a96eb2f35b.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('71', '李川潼', '440225196911221339', '18', '19', '', '', '0', '', '/resource/getImage/20181228/dcc04fe2-4731-4f03-a638-d796f388d75c.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('72', '殷明霞', '440225197306170427', '15', '11', '', '', '0', '', '/resource/getImage/20181228/0ad93132-89e3-4ee3-93b7-78befefebc9a.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('73', '刘明源', '440225197510250539', '15', '10', '', '', '0', '', '/resource/getImage/20181228/4ff8f1eb-da93-4ad7-a796-0d67ff8bc077.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('74', '袁亮', '440225197705140436', '16', '13', '', '', '0', '', '/resource/getImage/20181228/ae4efd79-115f-4ec6-b44b-c5041f822639.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('75', '唐雷', '440225197710164015', '20', '27', '', '', '0', '', '/resource/getImage/20181228/3406b9e3-9809-4b2e-b17b-46102c9c895b.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('76', '林茂富', '500383199106280407', '24', '35', '', '', '0', '', '/resource/getImage/20181228/8c9f4aa7-3e21-4cba-a84d-e1b7e9e1744f.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('77', '王磊', '511002199008252830', '20', '28', '', '', '0', '', '/resource/getImage/20181228/ff907ec1-f7aa-48db-86fa-719b6320aad1.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('78', '李现兵', '511023197009141799', '17', '6', '', '', '0', '', '/resource/getImage/20181228/6ff7aab5-a308-4190-9770-fc453b76901a.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('79', '韦远成', '51102619691228001X', '19', '24', '', '', '0', '', '/resource/getImage/20181228/399fe2f8-c96f-4dad-b3aa-b48184b1c214.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('80', '何勇', '511302198807292814', '20', '28', '', '', '0', '', '/resource/getImage/20181228/d3b769fa-c5bf-4c59-a6f1-254110d0d88f.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('81', '杨定福', '512921197104181155', '15', '10', '', '', '0', '', '/resource/getImage/20181228/c999fd61-944b-4c11-a0c3-57e4ccfd8c45.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('82', '赵世华', '532526197810063811', '16', '14', '', '', '0', '', '/resource/getImage/20181228/461690d1-9e14-428c-8721-f4a17720ef6e.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('83', '王阳武', '610424197612155272', '21', '30', '', '', '0', '', '/resource/getImage/20181228/7089e8dd-36e3-4005-b319-28054e5df810.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('84', '李留典', '610522199303125055', '21', '31', '', '', '0', '', '/resource/getImage/20181228/3a0e1470-ee47-4d7a-9422-c168186b8ea8.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');
INSERT INTO `employee` VALUES ('85', '曹强', '622424199401185511', '19', '7', '', '', '0', '', '/resource/getImage/20181228/acd60325-6b9c-460e-bf1d-6710ca2dff90.png', '0', '2', 'DS-2CD8627FXD-B20181117AACH225891550');

-- ----------------------------
-- Table structure for erplycustom
-- ----------------------------
DROP TABLE IF EXISTS `erplycustom`;
CREATE TABLE `erplycustom` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `customerCode` varchar(256) DEFAULT NULL,
  `username` varchar(256) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `posRegisterNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of erplycustom
-- ----------------------------

-- ----------------------------
-- Table structure for erplyposinfo
-- ----------------------------
DROP TABLE IF EXISTS `erplyposinfo`;
CREATE TABLE `erplyposinfo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `pointOfSaleId` int(11) DEFAULT NULL,
  `warehouseID` int(11) NOT NULL DEFAULT '0',
  `name` varchar(511) DEFAULT NULL,
  `customerCode` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of erplyposinfo
-- ----------------------------

-- ----------------------------
-- Table structure for ftpserver
-- ----------------------------
DROP TABLE IF EXISTS `ftpserver`;
CREATE TABLE `ftpserver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `servername` varchar(255) NOT NULL COMMENT 'FTP服务器名称',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `userpasswd` varchar(255) NOT NULL COMMENT '密码',
  `port` int(11) NOT NULL COMMENT '端口号',
  `serveraddr` varchar(255) NOT NULL COMMENT '服务器访问地址',
  `path` varchar(255) NOT NULL COMMENT '存储路径/共享目录方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ftpserver
-- ----------------------------

-- ----------------------------
-- Table structure for gas
-- ----------------------------
DROP TABLE IF EXISTS `gas`;
CREATE TABLE `gas` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `tbm_range` varchar(255) NOT NULL DEFAULT '' COMMENT '盾构区间',
  `dttte` int(11) NOT NULL DEFAULT '0' COMMENT 'Distance to the tunnel entrance 到隧道口的距离 ',
  `dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '检测日期',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '1:可燃气 2:氧气 3:一氧化碳 4:硫化氢',
  `val` varchar(255) NOT NULL DEFAULT '' COMMENT '检测数据值',
  `rummager` varchar(255) NOT NULL DEFAULT '' COMMENT '检测人',
  `outletid` int(11) NOT NULL DEFAULT '0' COMMENT '工地id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=361 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gas
-- ----------------------------
INSERT INTO `gas` VALUES ('1', '创中区间上行线', '0', '2018-10-01 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('2', '创中区间上行线', '0', '2018-10-01 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('3', '创中区间上行线', '0', '2018-10-01 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('4', '创中区间上行线', '0', '2018-10-01 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('5', '创中区间上行线', '0', '2018-10-02 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('6', '创中区间上行线', '0', '2018-10-02 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('7', '创中区间上行线', '0', '2018-10-02 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('8', '创中区间上行线', '0', '2018-10-02 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('9', '创中区间上行线', '0', '2018-10-03 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('10', '创中区间上行线', '0', '2018-10-03 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('11', '创中区间上行线', '0', '2018-10-03 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('12', '创中区间上行线', '0', '2018-10-03 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('13', '创中区间上行线', '0', '2018-10-04 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('14', '创中区间上行线', '0', '2018-10-04 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('15', '创中区间上行线', '0', '2018-10-04 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('16', '创中区间上行线', '0', '2018-10-04 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('17', '创中区间上行线', '0', '2018-10-05 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('18', '创中区间上行线', '0', '2018-10-05 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('19', '创中区间上行线', '0', '2018-10-05 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('20', '创中区间上行线', '0', '2018-10-05 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('21', '创中区间上行线', '0', '2018-10-06 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('22', '创中区间上行线', '0', '2018-10-06 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('23', '创中区间上行线', '0', '2018-10-06 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('24', '创中区间上行线', '0', '2018-10-06 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('25', '创中区间上行线', '0', '2018-10-07 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('26', '创中区间上行线', '0', '2018-10-07 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('27', '创中区间上行线', '0', '2018-10-07 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('28', '创中区间上行线', '0', '2018-10-07 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('29', '创中区间上行线', '0', '2018-10-08 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('30', '创中区间上行线', '0', '2018-10-08 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('31', '创中区间上行线', '0', '2018-10-08 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('32', '创中区间上行线', '0', '2018-10-08 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('33', '创中区间上行线', '0', '2018-10-09 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('34', '创中区间上行线', '0', '2018-10-09 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('35', '创中区间上行线', '0', '2018-10-09 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('36', '创中区间上行线', '0', '2018-10-09 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('37', '创中区间上行线', '0', '2018-10-10 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('38', '创中区间上行线', '0', '2018-10-10 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('39', '创中区间上行线', '0', '2018-10-10 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('40', '创中区间上行线', '0', '2018-10-10 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('41', '创中区间上行线', '0', '2018-10-11 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('42', '创中区间上行线', '0', '2018-10-11 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('43', '创中区间上行线', '0', '2018-10-11 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('44', '创中区间上行线', '0', '2018-10-11 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('45', '创中区间上行线', '0', '2018-10-12 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('46', '创中区间上行线', '0', '2018-10-12 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('47', '创中区间上行线', '0', '2018-10-12 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('48', '创中区间上行线', '0', '2018-10-12 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('49', '创中区间上行线', '0', '2018-10-13 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('50', '创中区间上行线', '0', '2018-10-13 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('51', '创中区间上行线', '0', '2018-10-13 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('52', '创中区间上行线', '0', '2018-10-13 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('53', '创中区间上行线', '0', '2018-10-14 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('54', '创中区间上行线', '0', '2018-10-14 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('55', '创中区间上行线', '0', '2018-10-14 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('56', '创中区间上行线', '0', '2018-10-14 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('57', '创中区间上行线', '0', '2018-10-15 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('58', '创中区间上行线', '0', '2018-10-15 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('59', '创中区间上行线', '0', '2018-10-15 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('60', '创中区间上行线', '0', '2018-10-15 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('61', '创中区间上行线', '0', '2018-10-16 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('62', '创中区间上行线', '0', '2018-10-16 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('63', '创中区间上行线', '0', '2018-10-16 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('64', '创中区间上行线', '0', '2018-10-16 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('65', '创中区间上行线', '0', '2018-10-18 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('66', '创中区间上行线', '0', '2018-10-18 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('67', '创中区间上行线', '0', '2018-10-18 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('68', '创中区间上行线', '0', '2018-10-18 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('69', '创中区间上行线', '0', '2018-10-19 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('70', '创中区间上行线', '0', '2018-10-19 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('71', '创中区间上行线', '0', '2018-10-19 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('72', '创中区间上行线', '0', '2018-10-19 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('73', '创中区间上行线', '0', '2018-10-20 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('74', '创中区间上行线', '0', '2018-10-20 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('75', '创中区间上行线', '0', '2018-10-20 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('76', '创中区间上行线', '0', '2018-10-20 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('77', '创中区间上行线', '0', '2018-10-21 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('78', '创中区间上行线', '0', '2018-10-21 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('79', '创中区间上行线', '0', '2018-10-21 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('80', '创中区间上行线', '0', '2018-10-21 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('81', '创中区间上行线', '0', '2018-10-22 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('82', '创中区间上行线', '0', '2018-10-22 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('83', '创中区间上行线', '0', '2018-10-22 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('84', '创中区间上行线', '0', '2018-10-22 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('85', '创中区间上行线', '0', '2018-10-23 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('86', '创中区间上行线', '0', '2018-10-23 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('87', '创中区间上行线', '0', '2018-10-23 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('88', '创中区间上行线', '0', '2018-10-23 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('89', '创中区间上行线', '0', '2018-10-24 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('90', '创中区间上行线', '0', '2018-10-24 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('91', '创中区间上行线', '0', '2018-10-24 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('92', '创中区间上行线', '0', '2018-10-24 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('93', '创中区间上行线', '0', '2018-10-25 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('94', '创中区间上行线', '0', '2018-10-25 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('95', '创中区间上行线', '0', '2018-10-25 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('96', '创中区间上行线', '0', '2018-10-25 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('97', '创中区间上行线', '0', '2018-10-26 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('98', '创中区间上行线', '0', '2018-10-26 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('99', '创中区间上行线', '0', '2018-10-26 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('100', '创中区间上行线', '0', '2018-10-26 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('101', '创中区间上行线', '0', '2018-10-27 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('102', '创中区间上行线', '0', '2018-10-27 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('103', '创中区间上行线', '0', '2018-10-27 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('104', '创中区间上行线', '0', '2018-10-27 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('105', '创中区间上行线', '0', '2018-10-28 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('106', '创中区间上行线', '0', '2018-10-28 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('107', '创中区间上行线', '0', '2018-10-28 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('108', '创中区间上行线', '0', '2018-10-28 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('109', '创中区间上行线', '0', '2018-10-29 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('110', '创中区间上行线', '0', '2018-10-29 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('111', '创中区间上行线', '0', '2018-10-29 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('112', '创中区间上行线', '0', '2018-10-29 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('113', '创中区间上行线', '0', '2018-10-30 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('114', '创中区间上行线', '0', '2018-10-30 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('115', '创中区间上行线', '0', '2018-10-30 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('116', '创中区间上行线', '0', '2018-10-30 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('117', '创中区间上行线', '0', '2018-10-31 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('118', '创中区间上行线', '0', '2018-10-31 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('119', '创中区间上行线', '0', '2018-10-31 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('120', '创中区间上行线', '0', '2018-10-31 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('121', '创中区间上行线', '0', '2018-11-01 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('122', '创中区间上行线', '0', '2018-11-01 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('123', '创中区间上行线', '0', '2018-11-01 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('124', '创中区间上行线', '0', '2018-11-01 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('125', '创中区间上行线', '0', '2018-11-02 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('126', '创中区间上行线', '0', '2018-11-02 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('127', '创中区间上行线', '0', '2018-11-02 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('128', '创中区间上行线', '0', '2018-11-02 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('129', '创中区间上行线', '0', '2018-11-03 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('130', '创中区间上行线', '0', '2018-11-03 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('131', '创中区间上行线', '0', '2018-11-03 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('132', '创中区间上行线', '0', '2018-11-03 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('133', '创中区间上行线', '0', '2018-11-04 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('134', '创中区间上行线', '0', '2018-11-04 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('135', '创中区间上行线', '0', '2018-11-04 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('136', '创中区间上行线', '0', '2018-11-04 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('137', '创中区间上行线', '0', '2018-11-05 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('138', '创中区间上行线', '0', '2018-11-05 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('139', '创中区间上行线', '0', '2018-11-05 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('140', '创中区间上行线', '0', '2018-11-05 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('141', '创中区间上行线', '0', '2018-11-06 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('142', '创中区间上行线', '0', '2018-11-06 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('143', '创中区间上行线', '0', '2018-11-06 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('144', '创中区间上行线', '0', '2018-11-06 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('145', '创中区间上行线', '0', '2018-11-07 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('146', '创中区间上行线', '0', '2018-11-07 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('147', '创中区间上行线', '0', '2018-11-07 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('148', '创中区间上行线', '0', '2018-11-07 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('149', '创中区间上行线', '0', '2018-11-08 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('150', '创中区间上行线', '0', '2018-11-08 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('151', '创中区间上行线', '0', '2018-11-08 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('152', '创中区间上行线', '0', '2018-11-08 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('153', '创中区间上行线', '0', '2018-11-09 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('154', '创中区间上行线', '0', '2018-11-09 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('155', '创中区间上行线', '0', '2018-11-09 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('156', '创中区间上行线', '0', '2018-11-09 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('157', '创中区间上行线', '0', '2018-11-10 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('158', '创中区间上行线', '0', '2018-11-10 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('159', '创中区间上行线', '0', '2018-11-10 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('160', '创中区间上行线', '0', '2018-11-10 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('161', '创中区间上行线', '0', '2018-11-11 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('162', '创中区间上行线', '0', '2018-11-11 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('163', '创中区间上行线', '0', '2018-11-11 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('164', '创中区间上行线', '0', '2018-11-11 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('165', '创中区间上行线', '0', '2018-11-12 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('166', '创中区间上行线', '0', '2018-11-12 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('167', '创中区间上行线', '0', '2018-11-12 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('168', '创中区间上行线', '0', '2018-11-12 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('169', '创中区间上行线', '0', '2018-11-13 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('170', '创中区间上行线', '0', '2018-11-13 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('171', '创中区间上行线', '0', '2018-11-13 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('172', '创中区间上行线', '0', '2018-11-13 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('173', '创中区间上行线', '0', '2018-11-14 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('174', '创中区间上行线', '0', '2018-11-14 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('175', '创中区间上行线', '0', '2018-11-14 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('176', '创中区间上行线', '0', '2018-11-14 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('177', '创中区间上行线', '0', '2018-11-15 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('178', '创中区间上行线', '0', '2018-11-15 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('179', '创中区间上行线', '0', '2018-11-15 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('180', '创中区间上行线', '0', '2018-11-15 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('181', '创中区间上行线', '0', '2018-11-16 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('182', '创中区间上行线', '0', '2018-11-16 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('183', '创中区间上行线', '0', '2018-11-16 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('184', '创中区间上行线', '0', '2018-11-16 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('185', '创中区间上行线', '0', '2018-11-17 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('186', '创中区间上行线', '0', '2018-11-17 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('187', '创中区间上行线', '0', '2018-11-17 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('188', '创中区间上行线', '0', '2018-11-17 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('189', '创中区间上行线', '0', '2018-11-18 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('190', '创中区间上行线', '0', '2018-11-18 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('191', '创中区间上行线', '0', '2018-11-18 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('192', '创中区间上行线', '0', '2018-11-18 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('193', '创中区间上行线', '0', '2018-11-19 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('194', '创中区间上行线', '0', '2018-11-19 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('195', '创中区间上行线', '0', '2018-11-19 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('196', '创中区间上行线', '0', '2018-11-19 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('197', '创中区间上行线', '0', '2018-11-20 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('198', '创中区间上行线', '0', '2018-11-20 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('199', '创中区间上行线', '0', '2018-11-20 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('200', '创中区间上行线', '0', '2018-11-20 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('201', '创中区间上行线', '0', '2018-11-21 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('202', '创中区间上行线', '0', '2018-11-21 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('203', '创中区间上行线', '0', '2018-11-21 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('204', '创中区间上行线', '0', '2018-11-21 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('205', '创中区间上行线', '0', '2018-11-22 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('206', '创中区间上行线', '0', '2018-11-22 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('207', '创中区间上行线', '0', '2018-11-22 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('208', '创中区间上行线', '0', '2018-11-22 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('209', '创中区间上行线', '0', '2018-11-23 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('210', '创中区间上行线', '0', '2018-11-23 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('211', '创中区间上行线', '0', '2018-11-23 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('212', '创中区间上行线', '0', '2018-11-23 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('213', '创中区间上行线', '0', '2018-11-24 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('214', '创中区间上行线', '0', '2018-11-24 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('215', '创中区间上行线', '0', '2018-11-24 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('216', '创中区间上行线', '0', '2018-11-24 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('217', '创中区间上行线', '0', '2018-11-25 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('218', '创中区间上行线', '0', '2018-11-25 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('219', '创中区间上行线', '0', '2018-11-25 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('220', '创中区间上行线', '0', '2018-11-25 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('221', '创中区间上行线', '0', '2018-11-26 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('222', '创中区间上行线', '0', '2018-11-26 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('223', '创中区间上行线', '0', '2018-11-26 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('224', '创中区间上行线', '0', '2018-11-26 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('225', '创中区间上行线', '0', '2018-11-27 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('226', '创中区间上行线', '0', '2018-11-27 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('227', '创中区间上行线', '0', '2018-11-27 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('228', '创中区间上行线', '0', '2018-11-27 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('229', '创中区间上行线', '0', '2018-11-28 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('230', '创中区间上行线', '0', '2018-11-28 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('231', '创中区间上行线', '0', '2018-11-28 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('232', '创中区间上行线', '0', '2018-11-28 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('233', '创中区间上行线', '0', '2018-11-29 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('234', '创中区间上行线', '0', '2018-11-29 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('235', '创中区间上行线', '0', '2018-11-29 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('236', '创中区间上行线', '0', '2018-11-29 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('237', '创中区间上行线', '0', '2018-11-30 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('238', '创中区间上行线', '0', '2018-11-30 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('239', '创中区间上行线', '0', '2018-11-30 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('240', '创中区间上行线', '0', '2018-11-30 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('241', '创中区间上行线', '0', '2018-12-01 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('242', '创中区间上行线', '0', '2018-12-01 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('243', '创中区间上行线', '0', '2018-12-01 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('244', '创中区间上行线', '0', '2018-12-01 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('245', '创中区间上行线', '0', '2018-12-02 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('246', '创中区间上行线', '0', '2018-12-02 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('247', '创中区间上行线', '0', '2018-12-02 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('248', '创中区间上行线', '0', '2018-12-02 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('249', '创中区间上行线', '0', '2018-12-03 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('250', '创中区间上行线', '0', '2018-12-03 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('251', '创中区间上行线', '0', '2018-12-03 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('252', '创中区间上行线', '0', '2018-12-03 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('253', '创中区间上行线', '0', '2018-12-04 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('254', '创中区间上行线', '0', '2018-12-04 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('255', '创中区间上行线', '0', '2018-12-04 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('256', '创中区间上行线', '0', '2018-12-04 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('257', '创中区间上行线', '0', '2018-12-05 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('258', '创中区间上行线', '0', '2018-12-05 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('259', '创中区间上行线', '0', '2018-12-05 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('260', '创中区间上行线', '0', '2018-12-05 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('261', '创中区间上行线', '0', '2018-12-06 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('262', '创中区间上行线', '0', '2018-12-06 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('263', '创中区间上行线', '0', '2018-12-06 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('264', '创中区间上行线', '0', '2018-12-06 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('265', '创中区间上行线', '0', '2018-12-07 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('266', '创中区间上行线', '0', '2018-12-07 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('267', '创中区间上行线', '0', '2018-12-07 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('268', '创中区间上行线', '0', '2018-12-07 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('269', '创中区间上行线', '0', '2018-12-08 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('270', '创中区间上行线', '0', '2018-12-08 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('271', '创中区间上行线', '0', '2018-12-08 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('272', '创中区间上行线', '0', '2018-12-08 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('273', '创中区间上行线', '0', '2018-12-09 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('274', '创中区间上行线', '0', '2018-12-09 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('275', '创中区间上行线', '0', '2018-12-09 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('276', '创中区间上行线', '0', '2018-12-09 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('277', '创中区间上行线', '0', '2018-12-10 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('278', '创中区间上行线', '0', '2018-12-10 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('279', '创中区间上行线', '0', '2018-12-10 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('280', '创中区间上行线', '0', '2018-12-10 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('281', '创中区间上行线', '0', '2018-12-11 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('282', '创中区间上行线', '0', '2018-12-11 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('283', '创中区间上行线', '0', '2018-12-11 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('284', '创中区间上行线', '0', '2018-12-11 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('285', '创中区间上行线', '0', '2018-12-12 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('286', '创中区间上行线', '0', '2018-12-12 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('287', '创中区间上行线', '0', '2018-12-12 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('288', '创中区间上行线', '0', '2018-12-12 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('289', '创中区间上行线', '0', '2018-12-13 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('290', '创中区间上行线', '0', '2018-12-13 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('291', '创中区间上行线', '0', '2018-12-13 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('292', '创中区间上行线', '0', '2018-12-13 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('293', '创中区间上行线', '0', '2018-12-14 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('294', '创中区间上行线', '0', '2018-12-14 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('295', '创中区间上行线', '0', '2018-12-14 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('296', '创中区间上行线', '0', '2018-12-14 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('297', '创中区间上行线', '0', '2018-12-15 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('298', '创中区间上行线', '0', '2018-12-15 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('299', '创中区间上行线', '0', '2018-12-15 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('300', '创中区间上行线', '0', '2018-12-15 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('301', '创中区间上行线', '0', '2018-12-16 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('302', '创中区间上行线', '0', '2018-12-16 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('303', '创中区间上行线', '0', '2018-12-16 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('304', '创中区间上行线', '0', '2018-12-16 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('305', '创中区间上行线', '0', '2018-12-17 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('306', '创中区间上行线', '0', '2018-12-17 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('307', '创中区间上行线', '0', '2018-12-17 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('308', '创中区间上行线', '0', '2018-12-17 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('309', '创中区间上行线', '0', '2018-12-18 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('310', '创中区间上行线', '0', '2018-12-18 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('311', '创中区间上行线', '0', '2018-12-18 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('312', '创中区间上行线', '0', '2018-12-18 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('313', '创中区间上行线', '0', '2018-12-19 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('314', '创中区间上行线', '0', '2018-12-19 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('315', '创中区间上行线', '0', '2018-12-19 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('316', '创中区间上行线', '0', '2018-12-19 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('317', '创中区间上行线', '0', '2018-12-20 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('318', '创中区间上行线', '0', '2018-12-20 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('319', '创中区间上行线', '0', '2018-12-20 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('320', '创中区间上行线', '0', '2018-12-20 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('321', '创中区间上行线', '0', '2018-12-21 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('322', '创中区间上行线', '0', '2018-12-21 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('323', '创中区间上行线', '0', '2018-12-21 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('324', '创中区间上行线', '0', '2018-12-21 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('325', '创中区间上行线', '0', '2018-12-22 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('326', '创中区间上行线', '0', '2018-12-22 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('327', '创中区间上行线', '0', '2018-12-22 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('328', '创中区间上行线', '0', '2018-12-22 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('329', '创中区间上行线', '0', '2018-12-23 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('330', '创中区间上行线', '0', '2018-12-23 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('331', '创中区间上行线', '0', '2018-12-23 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('332', '创中区间上行线', '0', '2018-12-23 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('333', '创中区间上行线', '0', '2018-12-24 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('334', '创中区间上行线', '0', '2018-12-24 00:00:00', '2', '20.8', '王国涛', '2');
INSERT INTO `gas` VALUES ('335', '创中区间上行线', '0', '2018-12-24 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('336', '创中区间上行线', '0', '2018-12-24 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('337', '创中区间上行线', '0', '2018-12-25 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('338', '创中区间上行线', '0', '2018-12-25 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('339', '创中区间上行线', '0', '2018-12-25 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('340', '创中区间上行线', '0', '2018-12-25 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('341', '创中区间上行线', '0', '2018-12-26 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('342', '创中区间上行线', '0', '2018-12-26 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('343', '创中区间上行线', '0', '2018-12-26 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('344', '创中区间上行线', '0', '2018-12-26 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('345', '创中区间上行线', '0', '2018-12-27 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('346', '创中区间上行线', '0', '2018-12-27 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('347', '创中区间上行线', '0', '2018-12-27 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('348', '创中区间上行线', '0', '2018-12-27 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('349', '创中区间上行线', '0', '2018-12-28 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('350', '创中区间上行线', '0', '2018-12-28 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('351', '创中区间上行线', '0', '2018-12-28 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('352', '创中区间上行线', '0', '2018-12-28 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('353', '创中区间上行线', '0', '2018-12-29 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('354', '创中区间上行线', '0', '2018-12-29 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('355', '创中区间上行线', '0', '2018-12-29 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('356', '创中区间上行线', '0', '2018-12-29 00:00:00', '4', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('357', '创中区间上行线', '0', '2018-12-30 00:00:00', '1', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('358', '创中区间上行线', '0', '2018-12-30 00:00:00', '2', '20.9', '王国涛', '2');
INSERT INTO `gas` VALUES ('359', '创中区间上行线', '0', '2018-12-30 00:00:00', '3', '0', '王国涛', '2');
INSERT INTO `gas` VALUES ('360', '创中区间上行线', '0', '2018-12-30 00:00:00', '4', '0', '王国涛', '2');

-- ----------------------------
-- Table structure for googledriveinfo
-- ----------------------------
DROP TABLE IF EXISTS `googledriveinfo`;
CREATE TABLE `googledriveinfo` (
  `user` varchar(256) NOT NULL,
  `refreshToken` varchar(512) NOT NULL,
  `accessToken` varchar(512) DEFAULT NULL,
  `expireDateTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of googledriveinfo
-- ----------------------------

-- ----------------------------
-- Table structure for inspectorcustomerrelationship
-- ----------------------------
DROP TABLE IF EXISTS `inspectorcustomerrelationship`;
CREATE TABLE `inspectorcustomerrelationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `inspectorid` int(11) NOT NULL,
  `relationship` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customerid` (`customerid`,`inspectorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectorcustomerrelationship
-- ----------------------------

-- ----------------------------
-- Table structure for inspectreporthistory
-- ----------------------------
DROP TABLE IF EXISTS `inspectreporthistory`;
CREATE TABLE `inspectreporthistory` (
  `reportid` varchar(128) NOT NULL,
  `customerid` int(11) NOT NULL,
  `outletid` int(11) NOT NULL,
  `itemid` int(11) DEFAULT NULL,
  `wifiname` varchar(256) NOT NULL DEFAULT '',
  `isemergency` int(11) NOT NULL DEFAULT '0',
  `distancefromdestoutlet` int(11) NOT NULL,
  `notifycustomer` int(5) DEFAULT NULL,
  `processor` int(5) DEFAULT NULL,
  `picarea` varchar(256) DEFAULT '',
  `comment` varchar(256) NOT NULL DEFAULT '',
  `reporturl` varchar(512) NOT NULL DEFAULT '',
  `reportmd5` varchar(1024) NOT NULL DEFAULT '',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reportid`),
  KEY `reportid` (`reportid`,`customerid`),
  KEY `inspectreporthistory_outletid_key` (`outletid`,`createtime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectreporthistory
-- ----------------------------

-- ----------------------------
-- Table structure for inspect_del_report_request
-- ----------------------------
DROP TABLE IF EXISTS `inspect_del_report_request`;
CREATE TABLE `inspect_del_report_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reportid` varchar(128) NOT NULL,
  `inspectorid` int(11) NOT NULL,
  `customerid` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `createtime` varchar(128) NOT NULL,
  `reporturl` varchar(255) NOT NULL,
  `inspectorname` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `report_unique` (`reportid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspect_del_report_request
-- ----------------------------

-- ----------------------------
-- Table structure for internalreport
-- ----------------------------
DROP TABLE IF EXISTS `internalreport`;
CREATE TABLE `internalreport` (
  `JobId` int(11) NOT NULL,
  `OperatorId` int(11) NOT NULL,
  `CameraNo` int(11) NOT NULL,
  `ClientId` varchar(256) NOT NULL,
  `PosId` varchar(256) NOT NULL,
  `Keyword` varchar(256) NOT NULL,
  `VideoStartTime` datetime NOT NULL,
  `VideoEndTime` datetime NOT NULL,
  `ReviewTime` int(11) NOT NULL,
  `PosText` varchar(10240) DEFAULT NULL,
  `OperatorStatus` int(11) NOT NULL,
  `OperatorFlag` int(11) NOT NULL,
  `Comments` varchar(256) DEFAULT NULL,
  `PosType` int(11) NOT NULL DEFAULT '0',
  `extflag` varchar(256) NOT NULL DEFAULT '',
  KEY `in_operatorid` (`OperatorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of internalreport
-- ----------------------------

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '职位名称',
  `outletid` int(11) DEFAULT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否关键岗位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('1', '项目副经理', '1', '1');
INSERT INTO `job` VALUES ('2', '技术员', '1', '1');
INSERT INTO `job` VALUES ('3', '安全员', '1', '1');
INSERT INTO `job` VALUES ('4', '施工员', '1', '1');
INSERT INTO `job` VALUES ('5', '项目经理', '2', '1');
INSERT INTO `job` VALUES ('6', '安全员', '2', '1');
INSERT INTO `job` VALUES ('7', '技术员', '2', '1');
INSERT INTO `job` VALUES ('8', '施工员', '2', '1');
INSERT INTO `job` VALUES ('9', '项目书记', '2', '0');
INSERT INTO `job` VALUES ('10', '项目副经理', '2', '0');
INSERT INTO `job` VALUES ('11', '工会主席', '2', '0');
INSERT INTO `job` VALUES ('12', '项目总工', '2', '0');
INSERT INTO `job` VALUES ('13', '工区主任', '2', '0');
INSERT INTO `job` VALUES ('14', '工区副主任', '2', '0');
INSERT INTO `job` VALUES ('15', '安全副总监', '2', '0');
INSERT INTO `job` VALUES ('16', '安质部副部长', '2', '0');
INSERT INTO `job` VALUES ('17', '安质部室主任', '2', '0');
INSERT INTO `job` VALUES ('18', '实习生', '2', '0');
INSERT INTO `job` VALUES ('19', '物机部副部长', '2', '0');
INSERT INTO `job` VALUES ('20', '物机部部员', '2', '0');
INSERT INTO `job` VALUES ('21', '物机部工程师', '2', '0');
INSERT INTO `job` VALUES ('22', '项目副总工', '2', '0');
INSERT INTO `job` VALUES ('23', '工程部部长', '2', '0');
INSERT INTO `job` VALUES ('24', '领工员', '2', '0');
INSERT INTO `job` VALUES ('25', '调度', '2', '0');
INSERT INTO `job` VALUES ('26', '资料员', '2', '0');
INSERT INTO `job` VALUES ('27', '测量主管', '2', '0');
INSERT INTO `job` VALUES ('28', '测量员', '2', '0');
INSERT INTO `job` VALUES ('29', '试验室主任', '2', '0');
INSERT INTO `job` VALUES ('30', '试验员', '2', '0');
INSERT INTO `job` VALUES ('31', '试验室部员', '2', '0');
INSERT INTO `job` VALUES ('32', '财务部部长', '2', '0');
INSERT INTO `job` VALUES ('33', '工经部部长', '2', '0');
INSERT INTO `job` VALUES ('34', '工经部部员', '2', '0');
INSERT INTO `job` VALUES ('35', '综合办公室主任', '2', '0');
INSERT INTO `job` VALUES ('36', '综合办公室干事', '2', '0');

-- ----------------------------
-- Table structure for licence
-- ----------------------------
DROP TABLE IF EXISTS `licence`;
CREATE TABLE `licence` (
  `account` varchar(45) NOT NULL,
  `licenceType` int(10) NOT NULL,
  `licenceCount` int(10) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of licence
-- ----------------------------
INSERT INTO `licence` VALUES ('admin', '1', '100');

-- ----------------------------
-- Table structure for newemap
-- ----------------------------
DROP TABLE IF EXISTS `newemap`;
CREATE TABLE `newemap` (
  `mapid` int(11) NOT NULL AUTO_INCREMENT COMMENT '地图id',
  `outletid` int(11) NOT NULL COMMENT '标记该地图所属工地',
  `mapurl` varchar(1024) NOT NULL DEFAULT '' COMMENT '地图图片对应URL',
  `mapheight` int(11) NOT NULL DEFAULT '0' COMMENT '当前地图图片高度',
  `mapwidth` int(11) NOT NULL DEFAULT '0' COMMENT '当前地图图片宽度',
  `parentmapid` int(11) NOT NULL DEFAULT '0' COMMENT '上级地图ID，若无上级地图，则字段值为0',
  `coordinate` varchar(255) NOT NULL DEFAULT '' COMMENT '在上级地图中的坐标点: “h，w”，使用绝对值',
  PRIMARY KEY (`mapid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newemap
-- ----------------------------
INSERT INTO `newemap` VALUES ('3', '1', '/outlet/getMap/1/20181017/76e034d6-bb2c-4f9c-9b97-85b1add4af0d.png', '867', '2567', '0', '');
INSERT INTO `newemap` VALUES ('4', '2', '/outlet/getMap/2/20181220/512f2ebb-9ca5-4497-b3b5-9a6e994ca95e.png', '1248', '2551', '0', '');

-- ----------------------------
-- Table structure for notify
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `clienttype` int(11) NOT NULL,
  `notifytitle` varchar(64) NOT NULL,
  `notify` varchar(1024) NOT NULL,
  `type` int(11) NOT NULL,
  `readable` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  `readtime` datetime DEFAULT NULL,
  `deviceno` int(11) NOT NULL DEFAULT '0',
  `camerano` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notify
-- ----------------------------

-- ----------------------------
-- Table structure for operationevent
-- ----------------------------
DROP TABLE IF EXISTS `operationevent`;
CREATE TABLE `operationevent` (
  `Eventid` int(11) NOT NULL AUTO_INCREMENT,
  `EventTime` datetime NOT NULL,
  `EventType` int(11) unsigned DEFAULT '0',
  `Description` varchar(256) DEFAULT '',
  PRIMARY KEY (`Eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of operationevent
-- ----------------------------

-- ----------------------------
-- Table structure for outlet
-- ----------------------------
DROP TABLE IF EXISTS `outlet`;
CREATE TABLE `outlet` (
  `outletid` int(11) NOT NULL AUTO_INCREMENT,
  `outletname` varchar(64) NOT NULL,
  `description` varchar(256) NOT NULL,
  `outletlogo` varchar(256) NOT NULL DEFAULT '',
  `outletaddress` varchar(1024) NOT NULL,
  `outletcode` varchar(255) NOT NULL DEFAULT '',
  `areamanager` varchar(255) NOT NULL DEFAULT '',
  `storemanager` varchar(255) NOT NULL DEFAULT '',
  `tradingarea` varchar(255) NOT NULL DEFAULT '',
  `topbrandid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`outletid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outlet
-- ----------------------------
INSERT INTO `outlet` VALUES ('1', '杭临城铁隧道', '城铁隧道', '', '杭州市余杭区东西大道凤新路口', '001', '', '', '', '1');
INSERT INTO `outlet` VALUES ('2', '地铁7号线义蓬站', '地铁7号线义蓬站', '', '地铁7号线义蓬站', '7002', '', '', '', '1');

-- ----------------------------
-- Table structure for patrolcamerastatus
-- ----------------------------
DROP TABLE IF EXISTS `patrolcamerastatus`;
CREATE TABLE `patrolcamerastatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceno` int(11) NOT NULL,
  `camerano` int(11) NOT NULL,
  `operatorid` int(11) NOT NULL,
  `lastpic` int(11) NOT NULL,
  `lasttime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patrolcamerastatus
-- ----------------------------

-- ----------------------------
-- Table structure for patrolopconfig
-- ----------------------------
DROP TABLE IF EXISTS `patrolopconfig`;
CREATE TABLE `patrolopconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `backup` int(11) NOT NULL,
  `sourcepath` varchar(256) NOT NULL,
  `backuppath` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patrolopconfig
-- ----------------------------
INSERT INTO `patrolopconfig` VALUES ('1', '5', '0', 'd', '');

-- ----------------------------
-- Table structure for patroloperator
-- ----------------------------
DROP TABLE IF EXISTS `patroloperator`;
CREATE TABLE `patroloperator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminid` int(11) NOT NULL,
  `operatorid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patroloperator
-- ----------------------------
INSERT INTO `patroloperator` VALUES ('1', '6', '5');

-- ----------------------------
-- Table structure for patrolrecord
-- ----------------------------
DROP TABLE IF EXISTS `patrolrecord`;
CREATE TABLE `patrolrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reportid` varchar(128) NOT NULL,
  `outletid` int(11) NOT NULL,
  `deviceno` int(11) NOT NULL,
  `camerano` int(11) NOT NULL,
  `operatorid` int(11) NOT NULL,
  `adminid` int(11) NOT NULL DEFAULT '0',
  `clientip` varchar(32) NOT NULL,
  `committime` datetime NOT NULL,
  `actiontime` datetime NOT NULL,
  `itemid` varchar(512) NOT NULL,
  `comment` varchar(256) NOT NULL,
  `majorpic` varchar(1024) NOT NULL,
  `minorpic` varchar(1024) NOT NULL DEFAULT '',
  `videoinfo` varchar(1024) NOT NULL DEFAULT '',
  `majormd5` varchar(128) NOT NULL DEFAULT '',
  `minormd5` varchar(1024) NOT NULL DEFAULT '',
  `outletname` varchar(255) NOT NULL DEFAULT '',
  `devicename` varchar(255) NOT NULL DEFAULT '',
  `cameraname` varchar(255) NOT NULL DEFAULT '',
  `adminname` varchar(255) NOT NULL DEFAULT '',
  `itemname` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `patrolrecord_unique` (`reportid`)
) ENGINE=InnoDB AUTO_INCREMENT=324 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patrolrecord
-- ----------------------------
INSERT INTO `patrolrecord` VALUES ('323', 'b154361e-a38f-4936-86cf-fb7793c85762', '1', '1', '1', '5', '5', '122.233.188.41', '2018-12-28 12:56:45', '2018-12-27 13:44:22', '45', '', '/getrecordimg/1/1/1/20181228/2018-12-27_13_44_22_1_1_1_1_1.jpg', '', '', '', '', '杭临城铁隧道', '杭临城铁隧道', 'Camera1_杭临城铁隧道', 'oparthur', '物品堆放>材料堆放>裸露土体未覆盖');

-- ----------------------------
-- Table structure for patrolrecordhistory
-- ----------------------------
DROP TABLE IF EXISTS `patrolrecordhistory`;
CREATE TABLE `patrolrecordhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reportid` varchar(128) NOT NULL,
  `deviceno` int(11) NOT NULL,
  `camerano` int(11) NOT NULL,
  `operatorid` int(11) NOT NULL,
  `adminid` int(11) NOT NULL,
  `clientip` varchar(32) NOT NULL,
  `committime` datetime NOT NULL,
  `itemid` varchar(512) NOT NULL,
  `comment` varchar(256) NOT NULL,
  `majorpic` varchar(1024) NOT NULL,
  `minorpic` varchar(1024) NOT NULL DEFAULT '',
  `videoinfo` varchar(1024) NOT NULL DEFAULT '',
  `outletid` int(11) NOT NULL,
  `audittime` datetime DEFAULT NULL,
  `auditresult` int(11) NOT NULL,
  `readtime` datetime DEFAULT NULL,
  `actiontime` datetime NOT NULL,
  `majormd5` varchar(128) NOT NULL DEFAULT '',
  `minormd5` varchar(1024) NOT NULL DEFAULT '',
  `operatorname` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `patrolrecordhistorynormal` (`outletid`,`committime`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patrolrecordhistory
-- ----------------------------
INSERT INTO `patrolrecordhistory` VALUES ('1', '88083f3c-4bbb-4063-8c67-a8cbd6563652', '1', '1', '5', '0', '183.156.52.13', '2018-10-17 15:07:06', '53', '', '/getrecordimg/1/1/1/20181017/2018-10-16_07_31_15_1_1_1_1_1.jpg', '', '', '1', '2018-10-17 15:25:25', '2', null, '2018-10-16 07:31:15', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('2', '58719d62-fd7d-4a6f-8f6a-9613718b71a7', '1', '1', '5', '0', '183.156.52.13', '2018-10-17 15:08:46', '1', '', '/getrecordimg/1/1/1/20181017/2018-10-16_14_15_01_1_1_1_1_1.jpg', '', '', '1', '2018-10-17 15:25:32', '2', null, '2018-10-16 14:15:01', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('3', 'de55863d-b367-4215-950f-af209c988b24', '1', '5', '5', '0', '183.156.52.13', '2018-10-17 15:13:10', '27', '', '/getrecordimg/1/1/5/20181017/2018-10-16_20_50_41_1_1_1_5_1.jpg', '', '', '1', '2018-10-17 15:25:34', '2', null, '2018-10-16 20:50:41', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('4', '3f28667b-b796-4fa4-807e-036e9eae08f8', '1', '7', '5', '0', '183.156.52.13', '2018-10-17 15:14:24', '1', '', '/getrecordimg/1/1/7/20181017/2018-10-16_16_48_02_1_1_1_7_1.jpg', '', '', '1', '2018-10-17 15:25:36', '2', null, '2018-10-16 16:48:02', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('5', 'f27dbeb5-fe56-4515-823b-d0d6da5fe5e3', '1', '7', '5', '0', '183.156.52.13', '2018-10-17 15:15:09', '21', '', '/getrecordimg/1/1/7/20181017/2018-10-16_16_03_17_1_1_1_7_1.jpg', '', '', '1', '2018-10-17 15:25:38', '2', null, '2018-10-16 16:03:17', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('6', 'afe846ed-bcaf-426e-a645-d68ba222200f', '1', '1', '5', '0', '183.156.52.13', '2018-10-18 18:56:21', '1', '', '/getrecordimg/1/1/1/20181018/2018-10-17_11_16_37_1_1_1_1_1.jpg', '', '', '1', '2018-10-18 19:22:11', '2', null, '2018-10-17 11:16:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('7', '338d2e0e-2ee6-47db-bfde-b2b482226fe7', '1', '1', '5', '0', '183.156.52.13', '2018-10-18 18:56:34', '1', '', '/getrecordimg/1/1/1/20181018/2018-10-17_11_40_13_1_1_1_1_1.jpg', '', '', '1', '2018-10-18 19:22:12', '2', null, '2018-10-17 11:40:13', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('8', '6d38a7ac-fe5a-49cc-890d-eced9884d895', '1', '1', '5', '0', '183.156.52.13', '2018-10-18 18:55:51', '19', '', '/getrecordimg/1/1/1/20181018/2018-10-17_10_33_11_1_1_1_1_1.jpg', '', '', '1', '2018-10-18 19:22:19', '2', null, '2018-10-17 10:33:11', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('9', '2db802b7-6830-4914-918e-a72cc5f37087', '1', '2', '5', '0', '183.156.52.13', '2018-10-18 19:01:54', '19', '', '/getrecordimg/1/1/2/20181018/2018-10-17_09_11_08_1_1_1_2_1.jpg', '', '', '1', '2018-10-18 19:22:20', '2', null, '2018-10-17 09:11:08', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('10', 'ee77e6e1-9f5b-4847-819b-8abde6fab054', '1', '3', '5', '0', '183.156.52.13', '2018-10-18 19:04:22', '49', '', '/getrecordimg/1/1/3/20181018/2018-10-17_00_09_47_1_1_1_3_1.jpg', '', '', '1', '2018-10-18 19:22:27', '2', null, '2018-10-17 00:09:47', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('11', '5582578c-2f0f-4a8a-83b3-850e32cdc221', '1', '3', '5', '0', '183.156.52.13', '2018-10-18 19:06:15', '49', '', '/getrecordimg/1/1/3/20181018/2018-10-17_07_16_37_1_1_1_3_1.jpg', '', '', '1', '2018-10-18 19:22:28', '2', null, '2018-10-17 07:16:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('12', '21b93ae8-34b3-4ffd-ae29-5bd6bcba2e0e', '1', '1', '5', '0', '183.156.52.13', '2018-10-18 19:07:53', '2', '', '/getrecordimg/1/1/1/20181018/2018-10-17_12_18_04_1_1_1_1_1.jpg', '', '', '1', '2018-10-18 19:22:32', '2', null, '2018-10-17 12:18:04', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('13', '9274af65-0be9-4ff4-b0fd-cb395edb4aa6', '1', '5', '5', '0', '183.156.52.13', '2018-10-18 19:09:01', '24', '', '/getrecordimg/1/1/5/20181018/2018-10-17_00_03_44_1_1_1_5_1.jpg', '', '', '1', '2018-10-18 19:22:33', '2', null, '2018-10-17 00:03:44', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('14', '1007727e-0aef-4cbd-bdd6-35b01c4ff222', '1', '7', '5', '0', '183.156.52.13', '2018-10-18 19:10:45', '1', '', '/getrecordimg/1/1/7/20181018/2018-10-17_12_00_28_1_1_1_7_1.jpg', '', '', '1', '2018-10-18 19:22:38', '2', null, '2018-10-17 12:00:28', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('15', '7273712b-f164-4839-81a3-2a250676267d', '1', '1', '5', '0', '183.156.52.13', '2018-10-18 19:23:56', '47', '', '/getrecordimg/1/1/1/20181018/2018-10-17_10_56_03_1_1_1_1_1.jpg', '', '', '1', '2018-10-18 19:27:35', '2', null, '2018-10-17 10:56:03', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('16', 'b1c7fedd-ca29-4409-a239-f5decf768d13', '1', '7', '5', '0', '183.156.52.13', '2018-10-18 19:26:02', '52', '', '/getrecordimg/1/1/7/20181018/2018-10-17_21_46_43_1_1_1_7_1.jpg', '', '', '1', '2018-10-18 19:27:36', '2', null, '2018-10-17 21:46:43', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('17', '405aa93c-4d82-43b0-a4fb-eb6bbd7cbb48', '1', '1', '5', '0', '115.196.141.211', '2018-10-19 18:19:33', '1', '', '/getrecordimg/1/1/1/20181019/2018-10-18_09_52_34_1_1_1_1_1.jpg', '', '', '1', '2018-10-19 18:33:45', '2', null, '2018-10-18 09:52:34', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('18', '99706d86-2eaa-462d-b64a-553ff0b6f402', '1', '1', '5', '0', '115.196.141.211', '2018-10-19 18:20:00', '1', '', '/getrecordimg/1/1/1/20181019/2018-10-18_10_39_38_1_1_1_1_1.jpg', '', '', '1', '2018-10-19 18:33:47', '2', null, '2018-10-18 10:39:38', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('19', 'a46341db-c022-4fff-885c-ed864378ae1e', '1', '1', '5', '0', '115.196.141.211', '2018-10-19 18:20:19', '1', '', '/getrecordimg/1/1/1/20181019/2018-10-18_11_50_21_1_1_1_1_1.jpg', '', '', '1', '2018-10-19 18:33:49', '2', null, '2018-10-18 11:50:21', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('20', 'f8646411-e2f6-4a8d-8fe7-cd1ae065f98e', '1', '1', '5', '0', '115.196.141.211', '2018-10-19 18:21:43', '1', '', '/getrecordimg/1/1/1/20181019/2018-10-18_19_16_02_1_1_1_1_1.jpg', '', '', '1', '2018-10-19 18:33:50', '2', null, '2018-10-18 19:16:02', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('21', 'bf21d2e7-8f3f-46df-8102-2b09160e5c55', '1', '2', '5', '0', '115.196.141.211', '2018-10-19 18:28:04', '19', '', '/getrecordimg/1/1/2/20181019/2018-10-18_19_58_43_1_1_1_2_1.jpg', '', '', '1', '2018-10-19 18:33:52', '2', null, '2018-10-18 19:58:43', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('22', '627e575e-c65a-45b8-b621-070996ac5db2', '1', '3', '5', '0', '115.196.141.211', '2018-10-19 18:29:57', '49', '', '/getrecordimg/1/1/3/20181019/2018-10-18_07_08_33_1_1_1_3_1.jpg', '', '', '1', '2018-10-19 18:33:58', '2', null, '2018-10-18 07:08:33', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('23', 'be58d933-4ce6-4540-8af8-127727b0c6d4', '1', '3', '5', '0', '115.196.141.211', '2018-10-19 18:29:30', '49', '', '/getrecordimg/1/1/3/20181019/2018-10-18_04_55_04_1_1_1_3_1.jpg', '', '', '1', '2018-10-19 18:34:01', '0', null, '2018-10-18 04:55:04', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('24', 'ad093c15-141c-4896-bc1e-5529847ca159', '1', '1', '5', '0', '115.196.141.211', '2018-10-22 18:08:34', '1', '', '/getrecordimg/1/1/1/20181022/2018-10-21_07_21_13_1_1_1_1_1.jpg', '', '', '1', '2018-10-22 18:15:50', '2', null, '2018-10-21 07:21:13', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('25', 'a5fa0fab-468b-4db6-9801-29561ba6c13b', '1', '1', '5', '0', '115.196.141.211', '2018-10-22 18:10:43', '1', '', '/getrecordimg/1/1/1/20181022/2018-10-21_16_55_01_1_1_1_1_1.jpg', '', '', '1', '2018-10-22 18:15:52', '2', null, '2018-10-21 16:55:01', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('26', 'e02ce533-f4a8-4ac0-b87e-12b326fcaaad', '1', '2', '5', '0', '115.196.141.211', '2018-10-22 18:11:28', '48', '', '/getrecordimg/1/1/2/20181022/2018-10-21_05_51_37_1_1_1_2_1.jpg', '', '', '1', '2018-10-22 18:15:57', '2', null, '2018-10-21 05:51:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('27', '006b068d-54e0-4f41-b07c-52a5ebce39e9', '1', '2', '5', '0', '115.196.141.211', '2018-10-22 18:11:37', '48', '', '/getrecordimg/1/1/2/20181022/2018-10-21_07_12_39_1_1_1_2_1.jpg', '', '', '1', '2018-10-22 18:16:01', '2', null, '2018-10-21 07:12:39', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('28', '3e731f05-8d7f-4b7f-a3a0-0b7b63725938', '1', '3', '5', '0', '115.196.141.211', '2018-10-22 18:12:16', '49', '', '/getrecordimg/1/1/3/20181022/2018-10-21_07_35_03_1_1_1_3_1.jpg', '', '', '1', '2018-10-22 18:16:04', '2', null, '2018-10-21 07:35:03', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('29', 'd6b49591-d9bd-4815-86eb-9880e08d4ede', '1', '3', '5', '0', '115.196.141.211', '2018-10-22 18:12:44', '1', '', '/getrecordimg/1/1/3/20181022/2018-10-21_07_47_30_1_1_1_3_1.jpg', '', '', '1', '2018-10-22 18:16:05', '2', null, '2018-10-21 07:47:30', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('30', '68086220-f7ec-4a9d-86b7-cfe7c63158b4', '1', '1', '5', '0', '115.196.141.211', '2018-10-23 09:32:46', '1', '', '/getrecordimg/1/1/1/20181023/2018-10-22_07_00_56_1_1_1_1_1.jpg', '', '', '1', '2018-10-23 18:16:09', '2', null, '2018-10-22 07:00:56', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('31', '29fb03a2-2337-4872-8f6a-f6a758881955', '1', '1', '5', '0', '115.196.141.211', '2018-10-23 09:33:35', '1', '', '/getrecordimg/1/1/1/20181023/2018-10-22_07_29_55_1_1_1_1_1.jpg', '', '', '1', '2018-10-23 18:16:11', '2', null, '2018-10-22 07:29:55', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('32', '20cd3462-ac92-4c6a-9774-d7c86cf5f0fd', '1', '1', '5', '0', '115.196.141.211', '2018-10-23 09:36:18', '1', '', '/getrecordimg/1/1/1/20181023/2018-10-22_23_14_40_1_1_1_1_1.jpg', '', '', '1', '2018-10-23 18:16:13', '2', null, '2018-10-22 23:14:40', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('33', 'dc1a175d-5ca2-4a01-936c-065ee1bd2bb0', '1', '2', '5', '0', '115.196.141.211', '2018-10-23 09:37:49', '48', '', '/getrecordimg/1/1/2/20181023/2018-10-22_17_17_45_1_1_1_2_1.jpg', '', '', '1', '2018-10-23 18:16:15', '2', null, '2018-10-22 17:17:45', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('34', '9637e8a3-7806-42b3-96c8-9a4770eac8f8', '1', '3', '5', '0', '115.196.141.211', '2018-10-23 09:38:32', '49', '', '/getrecordimg/1/1/3/20181023/2018-10-22_16_42_18_1_1_1_3_1.jpg', '', '', '1', '2018-10-23 18:16:18', '2', null, '2018-10-22 16:42:18', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('35', 'ac21f56d-ad9b-4a2c-938c-79a219df719d', '1', '6', '5', '0', '115.196.141.211', '2018-10-23 09:41:20', '24', '', '/getrecordimg/1/1/6/20181023/2018-10-22_08_34_41_1_1_1_6_1.jpg', '', '', '1', '2018-10-23 18:16:29', '2', null, '2018-10-22 08:34:41', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('36', '543fafbd-255b-4dc7-8791-ef9473346a39', '1', '7', '5', '0', '115.196.141.211', '2018-10-23 09:42:11', '1', '', '/getrecordimg/1/1/7/20181023/2018-10-22_13_17_20_1_1_1_7_1.jpg', '', '', '1', '2018-10-23 18:16:31', '2', null, '2018-10-22 13:17:20', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('37', '3a3faae2-ffb0-48bf-89b3-fffdfbb443a9', '1', '7', '5', '0', '115.196.141.211', '2018-10-23 09:42:22', '1', '', '/getrecordimg/1/1/7/20181023/2018-10-22_14_44_45_1_1_1_7_1.jpg', '', '', '1', '2018-10-23 18:16:33', '2', null, '2018-10-22 14:44:45', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('38', '0b0a53b7-cb48-4116-8f8d-a23a497f6601', '1', '1', '5', '0', '125.118.103.61', '2018-10-24 09:36:49', '1', '', '/getrecordimg/1/1/1/20181024/2018-10-23_11_15_29_1_1_1_1_1.jpg', '', '', '1', '2018-10-24 17:41:24', '2', null, '2018-10-23 11:15:29', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('39', 'aba4aaab-224a-4f2b-9e18-6bb58a6139fa', '1', '1', '5', '0', '125.118.103.61', '2018-10-24 09:38:11', '1', '', '/getrecordimg/1/1/1/20181024/2018-10-23_12_47_31_1_1_1_1_1.jpg', '', '', '1', '2018-10-24 17:41:26', '2', null, '2018-10-23 12:47:31', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('40', '0fb851c5-0e72-4978-8b08-a55f705e1ad4', '1', '2', '5', '0', '125.118.103.61', '2018-10-24 09:41:46', '19', '', '/getrecordimg/1/1/2/20181024/2018-10-23_09_35_46_1_1_1_2_1.jpg', '', '', '1', '2018-10-24 17:41:28', '2', null, '2018-10-23 09:35:46', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('41', 'bcbc9b2d-1e57-4758-9a8d-c05c7f621cfa', '1', '2', '5', '0', '125.118.103.61', '2018-10-24 09:42:28', '1', '', '/getrecordimg/1/1/2/20181024/2018-10-23_12_47_29_1_1_1_2_1.jpg', '', '', '1', '2018-10-24 17:41:31', '2', null, '2018-10-23 12:47:29', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('42', '44c4dd0c-c047-4f8d-8567-bfe1179111ff', '1', '8', '5', '0', '125.118.103.61', '2018-10-24 09:48:29', '27', '', '/getrecordimg/1/1/8/20181024/2018-10-23_10_11_10_1_1_1_8_1.jpg', '', '', '1', '2018-10-24 17:41:34', '2', null, '2018-10-23 10:11:10', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('43', '950b7655-0e2b-457a-8a58-9ff04cdccb7d', '1', '7', '5', '0', '125.118.103.61', '2018-10-24 09:50:25', '1', '', '/getrecordimg/1/1/7/20181024/2018-10-23_09_20_11_1_1_1_7_1.jpg', '', '', '1', '2018-10-24 17:41:36', '2', null, '2018-10-23 09:20:11', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('44', 'bba700a0-8558-4f1e-996b-4102df7803e9', '1', '7', '5', '0', '125.118.103.61', '2018-10-24 09:50:57', '1', '', '/getrecordimg/1/1/7/20181024/2018-10-23_13_20_27_1_1_1_7_1.jpg', '', '', '1', '2018-10-24 17:41:38', '2', null, '2018-10-23 13:20:27', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('45', 'e9134e52-9fca-4837-92c8-f002758dd4ec', '1', '1', '5', '0', '125.118.103.61', '2018-10-25 09:27:32', '1', '', '/getrecordimg/1/1/1/20181025/2018-10-24_11_59_30_1_1_1_1_1.jpg', '', '', '1', '2018-10-25 17:53:31', '2', null, '2018-10-24 11:59:30', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('46', '906f24a8-b0af-489d-8468-c60c0c4e0655', '1', '1', '5', '0', '125.118.103.61', '2018-10-25 09:28:45', '1', '', '/getrecordimg/1/1/1/20181025/2018-10-24_12_52_42_1_1_1_1_1.jpg', '', '', '1', '2018-10-25 17:53:35', '2', null, '2018-10-24 12:52:42', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('47', '7052a7bf-6830-48f4-9f1c-cb68e41ee9d2', '1', '1', '5', '0', '125.118.103.61', '2018-10-25 09:28:55', '1', '', '/getrecordimg/1/1/1/20181025/2018-10-24_12_57_48_1_1_1_1_1.jpg', '', '', '1', '2018-10-25 17:53:37', '2', null, '2018-10-24 12:57:48', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('48', '8b15c3d8-d897-4658-8db4-9bf4e2128201', '1', '1', '5', '0', '125.118.103.61', '2018-10-25 09:29:33', '1', '', '/getrecordimg/1/1/1/20181025/2018-10-24_13_17_23_1_1_1_1_1.jpg', '', '', '1', '2018-10-25 17:53:38', '2', null, '2018-10-24 13:17:23', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('49', 'dec4d1d1-1048-4425-a3f8-2a26c447ad5e', '1', '1', '5', '0', '125.118.103.61', '2018-10-25 09:30:48', '1', '', '/getrecordimg/1/1/1/20181025/2018-10-24_15_42_52_1_1_1_1_1.jpg', '', '', '1', '2018-10-25 17:53:40', '2', null, '2018-10-24 15:42:52', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('50', 'fcefc03a-c966-46d7-b454-d47d696a9cc2', '1', '2', '5', '0', '125.118.103.61', '2018-10-25 09:33:27', '2', '', '/getrecordimg/1/1/2/20181025/2018-10-24_11_31_19_1_1_1_2_1.jpg', '', '', '1', '2018-10-25 17:53:43', '2', null, '2018-10-24 11:31:19', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('51', 'aebc332c-1fb0-4dcd-99b3-4677dbb42c76', '1', '2', '5', '0', '125.118.103.61', '2018-10-25 09:33:48', '2', '', '/getrecordimg/1/1/2/20181025/2018-10-24_11_56_44_1_1_1_2_1.jpg', '', '', '1', '2018-10-25 17:53:45', '2', null, '2018-10-24 11:56:44', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('52', 'bd6e16fa-76f0-4e39-adc8-54065f7c2654', '1', '2', '5', '0', '125.118.103.61', '2018-10-25 09:34:09', '1', '', '/getrecordimg/1/1/2/20181025/2018-10-24_11_59_32_1_1_1_2_1.jpg', '', '', '1', '2018-10-25 17:53:47', '2', null, '2018-10-24 11:59:32', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('53', '80cbf98b-426e-448f-913b-b64cec7e21ab', '1', '2', '5', '0', '125.118.103.61', '2018-10-25 09:34:30', '1', '', '/getrecordimg/1/1/2/20181025/2018-10-24_12_46_05_1_1_1_2_1.jpg', '', '', '1', '2018-10-25 17:53:49', '2', null, '2018-10-24 12:46:05', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('54', '552d2357-cb33-4dad-9d20-ac3863872333', '1', '2', '5', '0', '125.118.103.61', '2018-10-25 09:35:32', '48', '', '/getrecordimg/1/1/2/20181025/2018-10-24_18_19_53_1_1_1_2_1.jpg', '', '', '1', '2018-10-25 17:53:52', '2', null, '2018-10-24 18:19:53', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('55', 'e6d89ed2-e409-4879-a6e5-b27592c1bfed', '1', '3', '5', '0', '125.118.103.61', '2018-10-25 09:36:40', '1', '', '/getrecordimg/1/1/3/20181025/2018-10-24_07_14_36_1_1_1_3_1.jpg', '', '', '1', '2018-10-25 17:53:55', '2', null, '2018-10-24 07:14:36', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('56', '73c53e13-841e-4eeb-811e-5c5d3956a548', '1', '3', '5', '0', '125.118.103.61', '2018-10-25 09:36:56', '1', '', '/getrecordimg/1/1/3/20181025/2018-10-24_09_00_27_1_1_1_3_1.jpg', '', '', '1', '2018-10-25 17:53:58', '2', null, '2018-10-24 09:00:27', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('57', '7ae7303d-16ab-4d03-aee2-70ba9e12d4ce', '1', '3', '5', '0', '125.118.103.61', '2018-10-25 09:37:17', '1', '', '/getrecordimg/1/1/3/20181025/2018-10-24_11_28_59_1_1_1_3_1.jpg', '', '', '1', '2018-10-25 17:54:00', '2', null, '2018-10-24 11:28:59', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('58', '8080fabc-82b6-4624-97ea-a5702d31be3f', '1', '3', '5', '0', '125.118.103.61', '2018-10-25 09:38:04', '49', '', '/getrecordimg/1/1/3/20181025/2018-10-24_17_36_40_1_1_1_3_1.jpg', '', '', '1', '2018-10-25 17:54:02', '2', null, '2018-10-24 17:36:40', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('59', 'e33275b1-551b-41fc-8779-42dcf5a79535', '1', '7', '5', '0', '125.118.103.61', '2018-10-25 09:39:46', '1', '', '/getrecordimg/1/1/7/20181025/2018-10-24_10_46_14_1_1_1_7_1.jpg', '', '', '1', '2018-10-25 17:54:04', '2', null, '2018-10-24 10:46:14', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('60', 'f1647ac5-cf50-41cb-9fd6-599b6e58a01b', '1', '8', '5', '0', '125.118.103.61', '2018-10-25 09:40:33', '24', '', '/getrecordimg/1/1/8/20181025/2018-10-24_07_14_48_1_1_1_8_1.jpg', '', '', '1', '2018-10-25 17:54:11', '2', null, '2018-10-24 07:14:48', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('61', '7f96a8d4-112b-47eb-8ae9-e0a20eff1abf', '1', '1', '5', '0', '125.118.103.61', '2018-10-26 09:13:43', '1', '', '/getrecordimg/1/1/1/20181026/2018-10-25_07_14_45_1_1_1_1_1.jpg', '', '', '1', '2018-10-26 17:53:23', '2', null, '2018-10-25 07:14:45', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('62', '2caf08e8-09eb-467d-a48d-0fb592e77b75', '1', '1', '5', '0', '125.118.103.61', '2018-10-26 09:16:47', '1', '', '/getrecordimg/1/1/1/20181026/2018-10-25_09_17_03_1_1_1_1_1.jpg', '', '', '1', '2018-10-26 17:53:26', '2', null, '2018-10-25 09:17:03', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('63', '0bb403e5-7ce0-4b0b-8e75-d626739981f9', '1', '1', '5', '0', '125.118.103.61', '2018-10-26 09:17:11', '1', '', '/getrecordimg/1/1/1/20181026/2018-10-25_10_59_24_1_1_1_1_1.jpg', '', '', '1', '2018-10-26 17:53:28', '2', null, '2018-10-25 10:59:24', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('64', 'cedcfd76-7bd7-4fd7-a2bc-0accd1ae7551', '1', '1', '5', '0', '125.118.103.61', '2018-10-26 09:17:41', '1', '', '/getrecordimg/1/1/1/20181026/2018-10-25_11_56_06_1_1_1_1_1.jpg', '', '', '1', '2018-10-26 17:53:30', '2', null, '2018-10-25 11:56:06', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('65', '2730090a-5e2a-4222-a3ac-3b05e49e5430', '1', '2', '5', '0', '125.118.103.61', '2018-10-26 09:19:09', '48', '', '/getrecordimg/1/1/2/20181026/2018-10-25_00_11_16_1_1_1_2_1.jpg', '', '', '1', '2018-10-26 17:53:34', '2', null, '2018-10-25 00:11:16', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('66', 'e97c0bd2-b12a-46f1-8010-4836dd98964a', '1', '3', '5', '0', '125.118.103.61', '2018-10-26 09:21:20', '49', '', '/getrecordimg/1/1/3/20181026/2018-10-25_11_55_12_1_1_1_3_1.jpg', '', '', '1', '2018-10-26 17:53:36', '2', null, '2018-10-25 11:55:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('67', 'd1127a3b-49c7-4dbf-8b61-c3eef62d337e', '1', '8', '5', '0', '125.118.103.61', '2018-10-26 09:26:44', '24', '', '/getrecordimg/1/1/8/20181026/2018-10-25_08_09_08_1_1_1_8_1.jpg', '', '', '1', '2018-10-26 17:53:39', '2', null, '2018-10-25 08:09:08', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('68', '36e22bb1-1cb6-45fb-9b70-66399608dda2', '1', '1', '5', '0', '115.199.109.51', '2018-10-29 09:44:28', '1', '', '/getrecordimg/1/1/1/20181029/2018-10-28_10_40_25_1_1_1_1_1.jpg', '', '', '1', '2018-10-29 17:58:47', '2', null, '2018-10-28 10:40:25', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('69', 'a9c226b1-c41a-4351-9871-1bf036872908', '1', '1', '5', '0', '115.199.109.51', '2018-10-29 09:45:59', '1', '', '/getrecordimg/1/1/1/20181029/2018-10-28_15_31_35_1_1_1_1_1.jpg', '', '', '1', '2018-10-29 17:58:49', '0', null, '2018-10-28 15:31:35', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('70', '28494fb5-c6b2-454a-aa7d-9d843a1a30ec', '1', '3', '5', '0', '115.199.109.51', '2018-10-29 09:51:44', '1', '', '/getrecordimg/1/1/3/20181029/2018-10-28_17_13_03_1_1_1_3_1.jpg', '', '', '1', '2018-10-29 17:58:52', '2', null, '2018-10-28 17:13:03', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('71', '66cc026b-cee3-49a1-8111-2d54352ff9c2', '1', '4', '5', '0', '115.199.109.51', '2018-10-29 09:52:37', '27', '', '/getrecordimg/1/1/4/20181029/2018-10-28_14_55_04_1_1_1_4_1.jpg', '', '', '1', '2018-10-29 17:58:57', '0', null, '2018-10-28 14:55:04', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('72', '1f62383c-ce8e-46a7-9672-172176ccaf4e', '1', '8', '5', '0', '115.199.109.51', '2018-10-29 09:54:53', '1', '', '/getrecordimg/1/1/8/20181029/2018-10-28_09_04_23_1_1_1_8_1.jpg', '', '', '1', '2018-10-29 17:59:01', '2', null, '2018-10-28 09:04:23', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('73', '10948aff-72c4-4a49-97fc-648806c82410', '1', '8', '5', '0', '115.199.109.51', '2018-10-29 09:55:20', '25', '', '/getrecordimg/1/1/8/20181029/2018-10-28_09_24_41_1_1_1_8_1.jpg', '', '', '1', '2018-10-29 17:59:03', '2', null, '2018-10-28 09:24:41', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('74', '89e0e72a-f10c-4e90-aa1f-d6767ef166b0', '1', '7', '5', '0', '115.199.109.51', '2018-10-30 10:09:31', '1', '', '/getrecordimg/1/1/7/20181030/2018-10-29_07_20_35_1_1_1_7_1.jpg', '', '', '1', '2018-10-30 19:15:33', '2', null, '2018-10-29 07:20:35', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('75', '37bb8913-2e38-4688-822c-3fdc818ebe0f', '1', '8', '5', '0', '115.199.109.51', '2018-10-30 10:12:37', '25', '', '/getrecordimg/1/1/8/20181030/2018-10-29_07_00_19_1_1_1_8_1.jpg', '', '', '1', '2018-10-30 19:15:35', '0', null, '2018-10-29 07:00:19', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('76', 'e3fd2b8c-d6d9-4e7a-be18-01c744674da3', '1', '7', '5', '0', '115.199.109.51', '2018-10-30 10:13:25', '34', '', '/getrecordimg/1/1/7/20181030/2018-10-29_07_23_07_1_1_1_7_1.jpg', '', '', '1', '2018-10-30 19:15:40', '0', null, '2018-10-29 07:23:07', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('77', '16994e6f-37bd-4838-a971-1207fd24f5fd', '1', '2', '5', '0', '115.199.109.51', '2018-10-30 10:17:36', '48', '', '/getrecordimg/1/1/2/20181030/2018-10-29_00_00_21_1_1_1_2_1.jpg', '', '', '1', '2018-10-30 19:15:44', '2', null, '2018-10-29 00:00:21', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('78', '7fb63669-31d4-4b8c-85fb-68f5df9fa6eb', '1', '1', '5', '0', '115.199.109.51', '2018-10-31 09:31:11', '1', '', '/getrecordimg/1/1/1/20181031/2018-10-30_04_22_31_1_1_1_1_1.jpg', '', '', '1', '2018-10-31 18:38:42', '2', null, '2018-10-30 04:22:31', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('79', '39e93af2-308e-430d-b53c-b5bf235821a0', '1', '1', '5', '0', '115.199.109.51', '2018-10-31 09:36:15', '1', '', '/getrecordimg/1/1/1/20181031/2018-10-30_05_12_01_1_1_1_1_1.jpg', '', '', '1', '2018-10-31 18:38:44', '2', null, '2018-10-30 05:12:01', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('80', '318b7340-5d14-4f52-8af5-0ded28275dbb', '1', '2', '5', '0', '115.199.109.51', '2018-10-31 09:52:35', '48', '', '/getrecordimg/1/1/2/20181031/2018-10-30_02_08_05_1_1_1_2_1.jpg', '', '', '1', '2018-10-31 18:38:49', '2', null, '2018-10-30 02:08:05', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('81', '9530bfb8-70df-42e8-999a-78e7fbb46b9c', '1', '5', '5', '0', '115.199.109.51', '2018-10-31 09:59:45', '27', '', '/getrecordimg/1/1/5/20181031/2018-10-30_11_47_17_1_1_1_5_1.jpg', '', '', '1', '2018-10-31 18:38:52', '2', null, '2018-10-30 11:47:17', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('82', 'ec9f89e4-686f-474b-af67-392229d5bf7e', '1', '5', '5', '0', '115.199.109.51', '2018-10-31 10:06:46', '1', '', '/getrecordimg/1/1/5/20181031/2018-10-30_15_45_13_1_1_1_5_1.jpg', '', '', '1', '2018-10-31 18:38:55', '2', null, '2018-10-30 15:45:13', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('83', '52f2e8cc-0345-4da1-a014-307ab6d39e48', '1', '7', '5', '0', '115.199.109.51', '2018-10-31 10:09:10', '34', '', '/getrecordimg/1/1/7/20181031/2018-10-30_14_55_30_1_1_1_7_1.jpg', '', '', '1', '2018-10-31 18:39:00', '0', null, '2018-10-30 14:55:30', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('84', '2e14d24d-cb81-4660-be52-0f1be76ad65f', '1', '8', '5', '0', '115.199.109.51', '2018-10-31 10:11:13', '24', '', '/getrecordimg/1/1/8/20181031/2018-10-30_09_09_16_1_1_1_8_1.jpg', '', '', '1', '2018-10-31 18:39:05', '0', null, '2018-10-30 09:09:16', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('85', '0cfd2e8d-638d-4abd-85b5-9df83336725c', '1', '1', '5', '0', '115.194.180.255', '2018-11-01 09:35:07', '1', '', '/getrecordimg/1/1/1/20181101/2018-10-31_06_28_30_1_1_1_1_1.jpg', '', '', '1', '2018-11-01 17:40:40', '2', null, '2018-10-31 06:28:30', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('86', '563b4998-e616-41ce-8ade-c4bb7a63c41b', '1', '1', '5', '0', '115.194.180.255', '2018-11-01 09:35:46', '1', '', '/getrecordimg/1/1/1/20181101/2018-10-31_06_56_44_1_1_1_1_1.jpg', '', '', '1', '2018-11-01 17:40:42', '2', null, '2018-10-31 06:56:44', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('87', 'c6d85ba0-bb10-42c1-a90f-207c5e869343', '1', '1', '5', '0', '115.194.180.255', '2018-11-01 09:36:07', '1', '', '/getrecordimg/1/1/1/20181101/2018-10-31_07_02_05_1_1_1_1_1.jpg', '', '', '1', '2018-11-01 17:40:44', '2', null, '2018-10-31 07:02:05', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('88', '1db70a0a-ae21-4428-a5da-a6c4d63bb404', '1', '1', '5', '0', '115.194.180.255', '2018-11-01 09:40:55', '1', '', '/getrecordimg/1/1/1/20181101/2018-10-31_17_20_56_1_1_1_1_1.jpg', '', '', '1', '2018-11-01 17:40:46', '2', null, '2018-10-31 17:20:56', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('89', 'de0e096f-5b6e-4398-a69e-49730a02983b', '1', '2', '5', '0', '115.194.180.255', '2018-11-01 09:44:09', '48', '', '/getrecordimg/1/1/2/20181101/2018-10-31_16_13_29_1_1_1_2_1.jpg', '', '', '1', '2018-11-01 17:40:50', '2', null, '2018-10-31 16:13:29', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('90', '171e86ad-97c5-4a94-9814-427cc923dc51', '1', '5', '5', '0', '115.194.180.255', '2018-11-01 09:44:58', '27', '', '/getrecordimg/1/1/5/20181101/2018-10-31_06_52_46_1_1_1_5_1.jpg', '', '', '1', '2018-11-01 17:40:53', '2', null, '2018-10-31 06:52:46', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('91', 'ec1ca995-fb63-48cb-965f-eacfea2084cb', '1', '7', '5', '0', '115.194.180.255', '2018-11-01 09:45:55', '1', '', '/getrecordimg/1/1/7/20181101/2018-10-31_08_07_01_1_1_1_7_1.jpg', '', '', '1', '2018-11-01 17:40:55', '2', null, '2018-10-31 08:07:01', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('92', '9aefcc06-8e44-493f-901a-26b3e26b59ac', '1', '7', '5', '0', '115.194.180.255', '2018-11-01 09:46:29', '34', '', '/getrecordimg/1/1/7/20181101/2018-10-31_08_18_30_1_1_1_7_1.jpg', '', '', '1', '2018-11-01 17:40:58', '0', null, '2018-10-31 08:18:30', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('93', 'e9bce05f-5cb8-4ffb-bc6a-bb83e68e394f', '1', '8', '5', '0', '115.194.180.255', '2018-11-01 09:47:22', '24', '', '/getrecordimg/1/1/8/20181101/2018-10-31_08_34_29_1_1_1_8_1.jpg', '', '', '1', '2018-11-01 17:41:03', '2', null, '2018-10-31 08:34:29', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('94', '9b55c7eb-db65-4f39-bdab-287bb40313c3', '1', '1', '5', '0', '115.194.180.255', '2018-11-02 10:32:24', '1', '', '/getrecordimg/1/1/1/20181102/2018-11-01_14_25_09_1_1_1_1_1.jpg', '', '', '1', '2018-11-02 11:20:38', '2', null, '2018-11-01 14:25:09', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('95', 'c1a6d270-9ff6-43b8-8e66-872e0dc175ae', '1', '1', '5', '0', '115.194.180.255', '2018-11-02 10:41:13', '1', '', '/getrecordimg/1/1/1/20181102/2018-11-01_17_05_22_1_1_1_1_1.jpg', '', '', '1', '2018-11-02 11:20:40', '2', null, '2018-11-01 17:05:22', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('96', '2f3e6419-7e64-427d-8c63-368a5de9bf09', '1', '1', '5', '0', '115.194.180.255', '2018-11-02 10:57:15', '1', '', '/getrecordimg/1/1/1/20181102/2018-11-01_22_58_43_1_1_1_1_1.jpg', '', '', '1', '2018-11-02 13:18:06', '2', null, '2018-11-01 22:58:43', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('97', '7d5da759-d8b4-4c89-b5ae-774bb561ee7e', '1', '5', '5', '0', '115.194.180.255', '2018-11-02 11:21:51', '27', '', '/getrecordimg/1/1/5/20181102/2018-11-01_05_57_22_1_1_1_5_1.jpg', '', '', '1', '2018-11-02 13:18:11', '2', null, '2018-11-01 05:57:22', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('98', 'd3cb5d0c-946d-4a4f-bf18-c14dd058e5a3', '1', '7', '5', '0', '115.194.180.255', '2018-11-02 11:22:48', '1', '', '/getrecordimg/1/1/7/20181102/2018-11-01_08_27_15_1_1_1_7_1.jpg', '', '', '1', '2018-11-02 13:18:13', '2', null, '2018-11-01 08:27:15', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('99', '2f021384-65c0-49d9-845a-1a184bfb6c65', '1', '7', '5', '0', '115.194.180.255', '2018-11-02 11:23:40', '36', '', '/getrecordimg/1/1/7/20181102/2018-11-01_11_47_38_1_1_1_7_1.jpg', '', '', '1', '2018-11-02 13:18:17', '0', null, '2018-11-01 11:47:38', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('100', 'b0530b2e-8010-4a97-b5cc-9c5b1c24eeea', '1', '8', '5', '0', '115.194.180.255', '2018-11-02 11:24:44', '24', '', '/getrecordimg/1/1/8/20181102/2018-11-01_07_12_36_1_1_1_8_1.jpg', '', '', '1', '2018-11-02 13:18:19', '2', null, '2018-11-01 07:12:36', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('101', 'ab11f449-8349-4621-9b6a-6d52e9925a4c', '1', '1', '5', '0', '125.118.100.178', '2018-11-05 09:46:38', '1', '', '/getrecordimg/1/1/1/20181105/2018-11-04_10_53_13_1_1_1_1_1.jpg', '', '', '1', '2018-11-05 18:22:13', '2', null, '2018-11-04 10:53:13', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('102', '4ccde70a-b0c0-48bb-80fb-7d467558440b', '1', '1', '5', '0', '125.118.100.178', '2018-11-05 11:07:41', '1', '', '/getrecordimg/1/1/1/20181105/2018-11-04_15_13_31_1_1_1_1_1.jpg', '', '', '1', '2018-11-05 18:22:16', '2', null, '2018-11-04 15:13:31', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('103', '5ddcb4ba-a287-4e0a-8365-52768aa8ddeb', '1', '3', '5', '0', '125.118.100.178', '2018-11-05 12:07:09', '1', '', '/getrecordimg/1/1/3/20181105/2018-11-04_17_18_52_1_1_1_3_1.jpg', '', '', '1', '2018-11-05 18:22:18', '2', null, '2018-11-04 17:18:52', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('104', '822b7275-6e98-46f1-9fee-8698fb5388bb', '1', '5', '5', '0', '125.118.100.178', '2018-11-05 12:11:40', '27', '', '/getrecordimg/1/1/5/20181105/2018-11-04_06_01_28_1_1_1_5_1.jpg', '', '', '1', '2018-11-05 18:22:21', '2', null, '2018-11-04 06:01:28', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('105', '30686c4e-9e40-4964-a9af-d962c4d2c822', '1', '7', '5', '0', '125.118.100.178', '2018-11-05 12:12:54', '34', '', '/getrecordimg/1/1/7/20181105/2018-11-04_08_34_07_1_1_1_7_1.jpg', '', '', '1', '2018-11-05 18:22:24', '0', null, '2018-11-04 08:34:07', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('106', '13c2c7f8-f757-4381-8db9-de88bf86ec27', '1', '8', '5', '0', '125.118.100.178', '2018-11-05 12:13:57', '24', '', '/getrecordimg/1/1/8/20181105/2018-11-04_07_39_56_1_1_1_8_1.jpg', '', '', '1', '2018-11-05 18:22:28', '2', null, '2018-11-04 07:39:56', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('107', 'f8d9cb28-e42d-4313-9e25-7639a5b1a465', '1', '1', '5', '0', '125.118.100.178', '2018-11-06 21:23:34', '1', '', '/getrecordimg/1/1/1/20181106/2018-11-05_07_09_11_1_1_1_1_1.jpg', '', '', '1', '2018-11-06 21:35:36', '2', null, '2018-11-05 07:09:11', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('108', '3d2ec41e-8ec8-4c2a-8407-a23030ec25cf', '1', '1', '5', '0', '125.118.100.178', '2018-11-06 21:24:25', '1', '', '/getrecordimg/1/1/1/20181106/2018-11-05_09_34_08_1_1_1_1_1.jpg', '', '', '1', '2018-11-06 21:35:38', '2', null, '2018-11-05 09:34:08', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('109', '3b8eacc2-6a59-4327-a21e-7fdfbd9b47cc', '1', '1', '5', '0', '125.118.100.178', '2018-11-06 21:24:54', '1', '', '/getrecordimg/1/1/1/20181106/2018-11-05_11_58_54_1_1_1_1_1.jpg', '', '', '1', '2018-11-06 21:35:40', '2', null, '2018-11-05 11:58:54', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('110', '0369b9ef-bc3f-4b66-8c33-52f5eb4f291b', '1', '1', '5', '0', '125.118.100.178', '2018-11-06 21:25:38', '47', '', '/getrecordimg/1/1/1/20181106/2018-11-05_16_51_12_1_1_1_1_1.jpg', '', '', '1', '2018-11-06 21:35:44', '2', null, '2018-11-05 16:51:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('111', '7dc9dce8-5cc1-4d75-a074-0e897f55f350', '1', '2', '5', '0', '125.118.100.178', '2018-11-06 21:26:38', '1', '', '/getrecordimg/1/1/2/20181106/2018-11-05_07_26_11_1_1_1_2_1.jpg', '', '', '1', '2018-11-06 21:35:46', '2', null, '2018-11-05 07:26:11', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('112', '40d741b4-7c00-46e4-911a-e36f16732f07', '1', '2', '5', '0', '125.118.100.178', '2018-11-06 21:28:43', '1', '', '/getrecordimg/1/1/2/20181106/2018-11-05_11_42_42_1_1_1_2_1.jpg', '', '', '1', '2018-11-06 21:35:48', '2', null, '2018-11-05 11:42:42', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('113', '8f27679a-004d-414c-87a7-a51e0a4838e4', '1', '3', '5', '0', '125.118.100.178', '2018-11-06 21:30:54', '10', '', '/getrecordimg/1/1/3/20181106/2018-11-05_09_53_47_1_1_1_3_1.jpg', '', '', '1', '2018-11-06 21:35:53', '2', null, '2018-11-05 09:53:47', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('114', '1bb76841-a4bc-4011-9aa5-1dd89195b743', '1', '4', '5', '0', '125.118.100.178', '2018-11-06 21:32:19', '1', '', '/getrecordimg/1/1/4/20181106/2018-11-05_03_03_12_1_1_1_4_1.jpg', '', '', '1', '2018-11-06 21:35:55', '2', null, '2018-11-05 03:03:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('115', 'a53d9a1c-057c-450c-892e-b3ff3119d08f', '1', '6', '5', '0', '125.118.100.178', '2018-11-06 21:33:30', '1', '', '/getrecordimg/1/1/6/20181106/2018-11-05_22_14_56_1_1_1_6_1.jpg', '', '', '1', '2018-11-06 21:35:57', '2', null, '2018-11-05 22:14:56', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('116', '91dd9c09-f50b-4049-ab21-a99523f1ee81', '1', '1', '5', '0', '125.118.100.178', '2018-11-07 09:49:23', '1', '', '/getrecordimg/1/1/1/20181107/2018-11-06_14_24_04_1_1_1_1_1.jpg', '', '', '1', '2018-11-08 08:52:01', '2', null, '2018-11-06 14:24:04', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('117', '2e8b303f-5205-4319-9543-56fa2c647a16', '1', '1', '5', '0', '125.118.100.178', '2018-11-07 09:53:02', '1', '', '/getrecordimg/1/1/1/20181107/2018-11-06_16_43_06_1_1_1_1_1.jpg', '', '', '1', '2018-11-08 08:52:03', '2', null, '2018-11-06 16:43:06', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('118', '46a51624-9395-4ea8-a9d2-6e9e745d701f', '1', '1', '5', '0', '125.118.100.178', '2018-11-07 09:53:31', '1', '', '/getrecordimg/1/1/1/20181107/2018-11-06_22_07_12_1_1_1_1_1.jpg', '', '', '1', '2018-11-08 08:52:05', '2', null, '2018-11-06 22:07:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('119', 'a8d173d3-361d-4c41-99ac-da70a6f1fc84', '1', '2', '5', '0', '125.118.100.178', '2018-11-07 09:57:25', '1', '', '/getrecordimg/1/1/2/20181107/2018-11-06_14_46_45_1_1_1_2_1.jpg', '', '', '1', '2018-11-08 08:52:08', '2', null, '2018-11-06 14:46:45', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('120', '24ebec9d-6cf0-4b6d-9042-1a2d92d2f33c', '1', '2', '5', '0', '125.118.100.178', '2018-11-07 09:58:17', '1', '', '/getrecordimg/1/1/2/20181107/2018-11-06_16_53_03_1_1_1_2_1.jpg', '', '', '1', '2018-11-08 08:52:10', '2', null, '2018-11-06 16:53:03', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('121', '89a04133-1ce4-4db2-ad6b-b3f7161e50b6', '1', '2', '5', '0', '125.118.100.178', '2018-11-07 09:59:03', '1', '', '/getrecordimg/1/1/2/20181107/2018-11-06_23_57_46_1_1_1_2_1.jpg', '', '', '1', '2018-11-08 08:52:13', '2', null, '2018-11-06 23:57:46', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('122', 'afd01876-5d5f-4b38-84d6-6f888e03ec08', '1', '3', '5', '0', '125.118.100.178', '2018-11-07 10:00:29', '1', '', '/getrecordimg/1/1/3/20181107/2018-11-06_14_55_41_1_1_1_3_1.jpg', '', '', '1', '2018-11-08 08:52:15', '2', null, '2018-11-06 14:55:41', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('123', '3fbfca59-dc29-434d-be09-6a238dbd48d1', '1', '3', '5', '0', '125.118.100.178', '2018-11-07 10:01:35', '1', '', '/getrecordimg/1/1/3/20181107/2018-11-06_22_19_13_1_1_1_3_1.jpg', '', '', '1', '2018-11-08 08:52:17', '2', null, '2018-11-06 22:19:13', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('124', 'eb7abde2-790d-43ad-b9e5-188c28fbb227', '1', '5', '5', '0', '125.118.100.178', '2018-11-07 10:02:26', '27', '', '/getrecordimg/1/1/5/20181107/2018-11-06_04_53_31_1_1_1_5_1.jpg', '', '', '1', '2018-11-08 08:52:19', '2', null, '2018-11-06 04:53:31', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('125', '762197f6-3561-47f4-9228-456b24eef1f1', '1', '8', '5', '0', '125.118.100.178', '2018-11-07 10:03:33', '24', '', '/getrecordimg/1/1/8/20181107/2018-11-06_03_58_30_1_1_1_8_1.jpg', '', '', '1', '2018-11-08 08:52:21', '0', null, '2018-11-06 03:58:30', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('126', '3ca38879-c30d-4591-ac26-83caef701afb', '1', '1', '5', '0', '183.156.99.151', '2018-11-12 16:34:39', '1', '', '/getrecordimg/1/1/1/20181112/2018-11-11_11_41_55_1_1_1_1_1.jpg', '', '', '1', '2018-11-13 19:14:36', '2', null, '2018-11-11 11:41:55', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('127', '8ea252b5-66b8-43aa-aa2f-3f9851473805', '1', '1', '5', '0', '183.156.99.151', '2018-11-12 16:35:03', '1', '', '/getrecordimg/1/1/1/20181112/2018-11-11_12_44_52_1_1_1_1_1.jpg', '', '', '1', '2018-11-13 19:14:39', '2', null, '2018-11-11 12:44:52', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('128', 'e4776e07-e821-4d4b-9548-6f90d34f0b34', '1', '1', '5', '0', '183.156.99.151', '2018-11-12 16:38:48', '1', '', '/getrecordimg/1/1/1/20181112/2018-11-11_21_44_33_1_1_1_1_1.jpg', '', '', '1', '2018-11-13 19:14:42', '2', null, '2018-11-11 21:44:33', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('129', 'baed0d79-8f34-493b-ba5b-ebbc3bda2966', '1', '2', '5', '0', '183.156.99.151', '2018-11-12 16:40:31', '1', '', '/getrecordimg/1/1/2/20181112/2018-11-11_12_45_21_1_1_1_2_1.jpg', '', '', '1', '2018-11-13 19:14:51', '2', null, '2018-11-11 12:45:21', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('130', '6947327a-f546-49ea-bd7e-b5fc6d2783d4', '1', '2', '5', '0', '183.156.99.151', '2018-11-12 16:42:33', '1', '', '/getrecordimg/1/1/2/20181112/2018-11-11_21_20_47_1_1_1_2_1.jpg', '', '', '1', '2018-11-13 19:14:53', '2', null, '2018-11-11 21:20:47', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('131', 'c8b788db-f215-4225-ad77-37f93066154b', '1', '2', '5', '0', '183.156.99.151', '2018-11-12 16:42:49', '1', '', '/getrecordimg/1/1/2/20181112/2018-11-11_21_49_47_1_1_1_2_1.jpg', '', '', '1', '2018-11-13 19:14:57', '2', null, '2018-11-11 21:49:47', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('132', '794aaa64-1202-43f7-a83e-830b72e8c75d', '1', '7', '5', '0', '183.156.99.151', '2018-11-12 17:12:01', '1', '', '/getrecordimg/1/1/7/20181112/2018-11-11_15_58_29_1_1_1_7_1.jpg', '', '', '1', '2018-11-13 19:15:00', '2', null, '2018-11-11 15:58:29', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('133', '9943e236-66a7-4b6e-ba9c-d1375fe24d1b', '1', '7', '5', '0', '183.156.99.151', '2018-11-12 17:12:19', '1', '', '/getrecordimg/1/1/7/20181112/2018-11-11_17_07_26_1_1_1_7_1.jpg', '', '', '1', '2018-11-13 19:15:04', '2', null, '2018-11-11 17:07:26', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('134', 'b323a43b-fdbe-45a7-b563-cbb63e698cbf', '1', '1', '5', '0', '183.156.99.151', '2018-11-12 17:45:29', '1', '', '/getrecordimg/1/1/1/20181112/2018-11-11_19_14_05_1_1_1_1_1.jpg', '', '', '1', '2018-11-13 19:15:07', '2', null, '2018-11-11 19:14:05', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('135', 'f0058691-c184-4b82-bf86-74a06e51af68', '1', '1', '5', '0', '183.156.99.151', '2018-11-12 17:46:03', '1', '', '/getrecordimg/1/1/1/20181112/2018-11-11_21_29_05_1_1_1_1_1.jpg', '', '', '1', '2018-11-13 19:15:08', '2', null, '2018-11-11 21:29:05', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('136', '3912dec5-23b1-4caa-980b-db34b45eeb6b', '1', '2', '5', '0', '183.156.99.151', '2018-11-12 17:47:38', '1', '', '/getrecordimg/1/1/2/20181112/2018-11-11_19_13_26_1_1_1_2_1.jpg', '', '', '1', '2018-11-13 19:15:15', '2', null, '2018-11-11 19:13:26', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('137', '248d7f3d-8ca3-454c-8887-a92bfe9eb64d', '1', '3', '5', '0', '183.156.99.151', '2018-11-12 17:48:42', '31', '', '/getrecordimg/1/1/3/20181112/2018-11-11_12_44_11_1_1_1_3_1.jpg', '', '', '1', '2018-11-13 19:15:22', '2', null, '2018-11-11 12:44:11', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('138', 'b42fe871-bbbd-41b1-87da-6e13c68c74cd', '1', '5', '5', '0', '183.156.99.151', '2018-11-12 17:52:22', '36', '', '/getrecordimg/1/1/5/20181112/2018-11-11_16_23_04_1_1_1_5_1.jpg', '', '', '1', '2018-11-13 19:15:30', '2', null, '2018-11-11 16:23:04', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('139', '9b618327-d0e1-435c-930f-e201b14f232c', '1', '7', '5', '0', '183.156.99.151', '2018-11-12 17:53:11', '1', '', '/getrecordimg/1/1/7/20181112/2018-11-11_11_16_41_1_1_1_7_1.jpg', '', '', '1', '2018-11-13 19:15:38', '2', null, '2018-11-11 11:16:41', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('140', 'dcc61435-4cf2-4aca-b8ae-7a1a026f9979', '1', '7', '5', '0', '183.156.99.151', '2018-11-12 17:53:17', '1', '', '/getrecordimg/1/1/7/20181112/2018-11-11_11_35_47_1_1_1_7_1.jpg', '', '', '1', '2018-11-13 19:15:41', '2', null, '2018-11-11 11:35:47', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('141', '330f60d6-d96f-4efc-84f9-91f6e7be1c54', '1', '7', '5', '0', '183.156.99.151', '2018-11-12 17:53:47', '1', '', '/getrecordimg/1/1/7/20181112/2018-11-11_11_57_24_1_1_1_7_1.jpg', '', '', '1', '2018-11-13 19:15:43', '2', null, '2018-11-11 11:57:24', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('142', '9c78cd44-2011-44c9-a5ad-128b9b6d550e', '1', '7', '5', '0', '183.156.99.151', '2018-11-12 17:53:59', '1', '', '/getrecordimg/1/1/7/20181112/2018-11-11_14_06_16_1_1_1_7_1.jpg', '', '', '1', '2018-11-13 19:15:46', '2', null, '2018-11-11 14:06:16', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('143', '83d017ae-d5cb-43aa-bf71-4c95318145f0', '1', '2', '5', '0', '183.156.99.151', '2018-11-13 09:29:58', '19', '', '/getrecordimg/1/1/2/20181113/2018-11-12_01_28_46_1_1_1_2_1.jpg', '', '', '1', '2018-11-13 19:15:51', '2', null, '2018-11-12 01:28:46', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('144', 'ce0cd1c7-9684-4722-b7c7-edce4c548e90', '1', '2', '5', '0', '183.156.99.151', '2018-11-13 09:33:57', '1', '', '/getrecordimg/1/1/2/20181113/2018-11-12_06_46_35_1_1_1_2_1.jpg', '', '', '1', '2018-11-13 19:15:54', '2', null, '2018-11-12 06:46:35', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('145', '9eaacc51-5580-4d3e-bf52-c00526f7098a', '1', '3', '5', '0', '183.156.99.151', '2018-11-13 09:42:31', '49', '', '/getrecordimg/1/1/3/20181113/2018-11-12_02_54_42_1_1_1_3_1.jpg', '', '', '1', '2018-11-13 19:15:58', '2', null, '2018-11-12 02:54:42', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('146', '2075fc6b-f3c8-404e-8158-6ec4015aef9a', '1', '4', '5', '0', '183.156.99.151', '2018-11-13 09:43:22', '1', '', '/getrecordimg/1/1/4/20181113/2018-11-12_00_26_54_1_1_1_4_1.jpg', '', '', '1', '2018-11-13 19:16:03', '2', null, '2018-11-12 00:26:54', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('147', '218bdf40-c32f-4158-806e-2b8e50f5c720', '1', '5', '5', '0', '183.156.99.151', '2018-11-13 09:49:09', '27', '', '/getrecordimg/1/1/5/20181113/2018-11-12_02_54_28_1_1_1_5_1.jpg', '', '', '1', '2018-11-13 19:16:05', '2', null, '2018-11-12 02:54:28', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('148', '5d96f0d6-a5dc-49d8-ba70-28952f7d5039', '1', '8', '5', '0', '183.156.99.151', '2018-11-13 09:51:05', '24', '', '/getrecordimg/1/1/8/20181113/2018-11-12_09_46_52_1_1_1_8_1.jpg', '', '', '1', '2018-11-13 19:16:08', '2', null, '2018-11-12 09:46:52', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('149', 'e2e42495-d3d9-4d28-9732-ad4a67098e32', '1', '1', '5', '0', '183.156.99.151', '2018-11-14 09:37:36', '1', '', '/getrecordimg/1/1/1/20181114/2018-11-13_00_04_15_1_1_1_1_1.jpg', '', '', '1', '2018-11-15 10:08:11', '2', null, '2018-11-13 00:04:15', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('150', '77feaab9-3d21-4d41-812e-3feb34e498c6', '1', '1', '5', '0', '183.156.99.151', '2018-11-14 09:37:52', '1', '', '/getrecordimg/1/1/1/20181114/2018-11-13_00_12_39_1_1_1_1_1.jpg', '', '', '1', '2018-11-15 10:08:12', '2', null, '2018-11-13 00:12:39', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('151', '22d1634f-7e53-46f9-a860-a4af05cdfebb', '1', '2', '5', '0', '183.156.99.151', '2018-11-14 09:47:48', '1', '', '/getrecordimg/1/1/2/20181114/2018-11-13_05_45_01_1_1_1_2_1.jpg', '', '', '1', '2018-11-15 10:08:14', '2', null, '2018-11-13 05:45:01', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('152', '6dbf2798-f2d9-4d38-a0c6-00dc9a0f175f', '1', '2', '5', '0', '183.156.99.151', '2018-11-14 09:51:32', '1', '', '/getrecordimg/1/1/2/20181114/2018-11-13_16_52_36_1_1_1_2_1.jpg', '', '', '1', '2018-11-15 10:08:18', '2', null, '2018-11-13 16:52:36', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('153', '0f62805f-df0d-4823-8829-0292ef4add6d', '1', '3', '5', '0', '183.156.99.151', '2018-11-14 09:54:55', '1', '', '/getrecordimg/1/1/3/20181114/2018-11-13_12_25_17_1_1_1_3_1.jpg', '', '', '1', '2018-11-15 10:08:20', '2', null, '2018-11-13 12:25:17', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('154', 'b844dad4-0378-4d17-9cd9-31c9c08f91c2', '1', '3', '5', '0', '183.156.99.151', '2018-11-14 09:55:07', '1', '', '/getrecordimg/1/1/3/20181114/2018-11-13_12_36_43_1_1_1_3_1.jpg', '', '', '1', '2018-11-15 10:08:22', '2', null, '2018-11-13 12:36:43', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('155', '002fb85e-845d-4775-aa29-fb5bbf4423f2', '1', '3', '5', '0', '183.156.99.151', '2018-11-14 09:55:34', '1', '', '/getrecordimg/1/1/3/20181114/2018-11-13_16_58_51_1_1_1_3_1.jpg', '', '', '1', '2018-11-15 10:08:24', '2', null, '2018-11-13 16:58:51', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('156', 'eff7bd72-8315-43bf-885d-06ef40e1a314', '1', '5', '5', '0', '183.156.99.151', '2018-11-14 09:56:51', '27', '', '/getrecordimg/1/1/5/20181114/2018-11-13_13_29_21_1_1_1_5_1.jpg', '', '', '1', '2018-11-15 10:08:28', '2', null, '2018-11-13 13:29:21', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('157', 'b3fdf162-c017-40af-9849-c5a77a616c1b', '1', '8', '5', '0', '183.156.99.151', '2018-11-14 09:58:03', '24', '', '/getrecordimg/1/1/8/20181114/2018-11-13_06_15_26_1_1_1_8_1.jpg', '', '', '1', '2018-11-15 10:08:30', '0', null, '2018-11-13 06:15:26', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('158', '460f8ea2-bea6-47e3-82a6-4ce49e40442d', '1', '2', '5', '0', '183.156.99.151', '2018-11-15 10:16:08', '1', '', '/getrecordimg/1/1/2/20181115/2018-11-14_08_39_37_1_1_1_2_1.jpg', '', '', '1', '2018-11-15 15:13:58', '2', null, '2018-11-14 08:39:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('159', '02a97a93-dcce-4508-a609-d2a1c5d1f017', '1', '2', '5', '0', '183.156.99.151', '2018-11-15 10:19:23', '1', '', '/getrecordimg/1/1/2/20181115/2018-11-14_14_02_30_1_1_1_2_1.jpg', '', '', '1', '2018-11-15 15:14:01', '2', null, '2018-11-14 14:02:30', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('160', '63aefd48-4cce-4b6c-89ce-45d74c70638c', '1', '2', '5', '0', '183.156.99.151', '2018-11-15 10:19:58', '1', '', '/getrecordimg/1/1/2/20181115/2018-11-14_14_14_44_1_1_1_2_1.jpg', '', '', '1', '2018-11-15 15:14:03', '2', null, '2018-11-14 14:14:44', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('161', '45df9dea-eb70-4803-bdc7-7d2f63a6bde7', '1', '3', '5', '0', '183.156.99.151', '2018-11-15 10:23:06', '1', '', '/getrecordimg/1/1/3/20181115/2018-11-14_13_27_48_1_1_1_3_1.jpg', '', '', '1', '2018-11-15 15:14:06', '2', null, '2018-11-14 13:27:48', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('162', '8cec7318-64a6-4892-a739-24448cefe8e1', '1', '4', '5', '0', '183.156.99.151', '2018-11-15 10:24:50', '1', '', '/getrecordimg/1/1/4/20181115/2018-11-14_01_24_37_1_1_1_4_1.jpg', '', '', '1', '2018-11-15 15:14:08', '2', null, '2018-11-14 01:24:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('163', '898478bd-0dac-4334-953b-5369727594f8', '1', '5', '5', '0', '183.156.99.151', '2018-11-15 10:27:18', '27', '', '/getrecordimg/1/1/5/20181115/2018-11-14_07_45_57_1_1_1_5_1.jpg', '', '', '1', '2018-11-15 15:14:10', '2', null, '2018-11-14 07:45:57', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('164', '6afefa96-a8f5-4de8-8525-fce3ad19c31a', '1', '8', '5', '0', '183.156.99.151', '2018-11-15 10:28:31', '24', '', '/getrecordimg/1/1/8/20181115/2018-11-14_06_19_31_1_1_1_8_1.jpg', '', '', '1', '2018-11-15 15:14:14', '0', null, '2018-11-14 06:19:31', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('165', 'a50a4fda-2b38-497d-b2ce-5c6b78d3a58f', '1', '1', '5', '0', '183.156.99.151', '2018-11-16 15:30:27', '1', '', '/getrecordimg/1/1/1/20181116/2018-11-15_09_16_07_1_1_1_1_1.jpg', '', '', '1', '2018-11-16 16:00:25', '2', null, '2018-11-15 09:16:07', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('166', 'f1c7dd03-a0a5-4b77-a37f-4f50b3c1fe1c', '1', '1', '5', '0', '183.156.99.151', '2018-11-16 15:31:58', '1', '', '/getrecordimg/1/1/1/20181116/2018-11-15_10_04_19_1_1_1_1_1.jpg', '', '', '1', '2018-11-16 16:00:31', '2', null, '2018-11-15 10:04:19', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('167', '3ac30e7a-62ec-4a86-8726-6b9459dcbab2', '1', '1', '5', '0', '183.156.99.151', '2018-11-16 15:32:46', '1', '', '/getrecordimg/1/1/1/20181116/2018-11-15_12_06_16_1_1_1_1_1.jpg', '', '', '1', '2018-11-16 16:00:35', '2', null, '2018-11-15 12:06:16', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('168', '30d94c06-6eae-4c82-b658-2d770c72995e', '1', '1', '5', '0', '183.156.99.151', '2018-11-16 15:34:50', '1', '', '/getrecordimg/1/1/1/20181116/2018-11-15_17_48_30_1_1_1_1_1.jpg', '', '', '1', '2018-11-16 16:00:38', '2', null, '2018-11-15 17:48:30', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('169', '17ceabe6-1615-4ce7-8661-bf37b571c7d7', '1', '2', '5', '0', '183.156.99.151', '2018-11-16 15:38:24', '1', '', '/getrecordimg/1/1/2/20181116/2018-11-15_07_55_40_1_1_1_2_1.jpg', '', '', '1', '2018-11-16 16:00:42', '2', null, '2018-11-15 07:55:40', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('170', 'a99d44b6-3e02-46a9-aa87-ea08573728e1', '1', '2', '5', '0', '183.156.99.151', '2018-11-16 15:39:15', '1', '', '/getrecordimg/1/1/2/20181116/2018-11-15_12_04_26_1_1_1_2_1.jpg', '', '', '1', '2018-11-16 16:00:45', '2', null, '2018-11-15 12:04:26', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('171', '7bb8aafc-728c-4fb0-b90a-d46aa81fb3ac', '1', '4', '5', '0', '183.156.99.151', '2018-11-16 15:43:41', '1', '', '/getrecordimg/1/1/4/20181116/2018-11-15_02_41_14_1_1_1_4_1.jpg', '', '', '1', '2018-11-16 16:00:52', '2', null, '2018-11-15 02:41:14', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('172', '295b4b7a-bf42-43f3-b2bc-c914344464b1', '1', '5', '5', '0', '183.156.99.151', '2018-11-16 15:46:39', '24', '', '/getrecordimg/1/1/5/20181116/2018-11-15_15_31_37_1_1_1_5_1.jpg', '', '', '1', '2018-11-16 16:00:56', '2', null, '2018-11-15 15:31:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('173', '1d0a47b6-4e67-4be0-9953-2533ed152511', '1', '6', '5', '0', '183.156.99.151', '2018-11-16 15:47:51', '1', '', '/getrecordimg/1/1/6/20181116/2018-11-15_12_06_06_1_1_1_6_1.jpg', '', '', '1', '2018-11-16 16:00:59', '2', null, '2018-11-15 12:06:06', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('174', '9ff2bda6-76e8-421a-97b1-d180a963e746', '1', '7', '5', '0', '183.156.99.151', '2018-11-16 15:48:53', '1', '', '/getrecordimg/1/1/7/20181116/2018-11-15_07_09_12_1_1_1_7_1.jpg', '', '', '1', '2018-11-16 16:12:55', '2', null, '2018-11-15 07:09:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('175', '4ea61c7c-cc89-4627-84f7-adb85bfc3387', '1', '2', '5', '0', '183.156.99.151', '2018-11-16 16:03:20', '8', '', '/getrecordimg/1/1/2/20181116/2018-11-15_22_12_11_1_1_1_2_1.jpg', '', '', '1', '2018-11-16 16:13:10', '2', null, '2018-11-15 22:12:11', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('176', 'ae0a6bed-3c67-4fe6-868a-2c6b3504f884', '1', '2', '5', '0', '183.156.99.151', '2018-11-16 16:03:45', '47', '', '/getrecordimg/1/1/2/20181116/2018-11-15_15_13_20_1_1_1_2_1.jpg', '', '', '1', '2018-11-16 16:13:14', '2', null, '2018-11-15 15:13:20', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('177', '2ed4303c-4cf7-4b20-b7a8-e71d503b59b4', '1', '2', '5', '0', '183.156.99.151', '2018-11-16 16:04:59', '47', '', '/getrecordimg/1/1/2/20181116/2018-11-15_10_56_40_1_1_1_2_1.jpg', '', '', '1', '2018-11-16 16:13:18', '2', null, '2018-11-15 10:56:40', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('178', 'f3d3f452-ab41-4e24-9d8d-5cc72ed8891d', '1', '5', '5', '0', '183.156.99.151', '2018-11-16 16:10:43', '27', '', '/getrecordimg/1/1/5/20181116/2018-11-15_09_44_17_1_1_1_5_1.jpg', '', '', '1', '2018-11-16 16:13:55', '2', null, '2018-11-15 09:44:17', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('179', '363ab87a-262f-4ae9-8a93-ec089f246f0c', '1', '7', '5', '0', '183.156.99.151', '2018-11-16 16:12:09', '27', '', '/getrecordimg/1/1/7/20181116/2018-11-15_16_18_49_1_1_1_7_1.jpg', '', '', '1', '2018-11-16 16:14:02', '0', null, '2018-11-15 16:18:49', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('180', 'b8e5fe8e-4ac7-4f96-a648-42e1f553fb6d', '1', '3', '5', '0', '183.156.99.151', '2018-11-16 16:06:31', '80', '', '/getrecordimg/1/1/3/20181116/2018-11-15_16_13_00_1_1_1_3_1.jpg', '', '', '1', '2018-11-16 16:14:06', '0', null, '2018-11-15 16:13:00', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('181', '5f1c365e-4e1e-4b88-980a-8ffe4590e9d2', '1', '3', '5', '0', '183.156.99.151', '2018-11-16 16:06:50', '79', '', '/getrecordimg/1/1/3/20181116/2018-11-15_16_13_00_1_1_1_3_2.jpg', '', '', '1', '2018-11-16 16:14:14', '0', null, '2018-11-15 16:13:00', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('182', '84c9cdf2-adbe-4978-9f44-a097b28b7997', '1', '8', '5', '0', '183.156.99.151', '2018-11-16 16:14:40', '20', '', '/getrecordimg/1/1/8/20181116/2018-11-15_16_41_38_1_1_1_8_1.jpg', '', '', '1', '2018-11-16 16:31:49', '0', null, '2018-11-15 16:41:38', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('183', 'ee57eaaa-1f72-4d3e-994e-f0eeb568a358', '1', '1', '5', '0', '125.119.237.89', '2018-11-19 10:10:08', '1', '', '/getrecordimg/1/1/1/20181119/2018-11-18_13_56_42_1_1_1_1_1.jpg', '', '', '1', '2018-11-19 17:17:39', '2', null, '2018-11-18 13:56:42', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('184', '85a1e4b8-629a-401a-8f05-f24bdf3c55a9', '1', '5', '5', '0', '125.119.237.89', '2018-11-19 10:25:43', '27', '', '/getrecordimg/1/1/5/20181119/2018-11-18_06_42_28_1_1_1_5_1.jpg', '', '', '1', '2018-11-19 17:17:41', '2', null, '2018-11-18 06:42:28', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('185', 'db82fed2-f064-4a68-92e7-9d9f025489c1', '1', '7', '5', '0', '125.119.237.89', '2018-11-19 10:30:36', '24', '', '/getrecordimg/1/1/7/20181119/2018-11-18_07_47_41_1_1_1_7_1.jpg', '', '', '1', '2018-11-19 17:17:44', '0', null, '2018-11-18 07:47:41', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('186', 'b3864b06-3ec5-4fa3-a9c2-a1d4b1a7e480', '1', '8', '5', '0', '125.119.237.89', '2018-11-19 10:32:37', '24', '', '/getrecordimg/1/1/8/20181119/2018-11-18_07_08_18_1_1_1_8_1.jpg', '', '', '1', '2018-11-19 17:17:47', '2', null, '2018-11-18 07:08:18', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('187', 'cddee9fe-cc66-4a0d-8826-8bff32b06628', '1', '1', '5', '0', '115.199.108.224', '2018-11-20 10:21:27', '1', '', '/getrecordimg/1/1/1/20181120/2018-11-19_06_53_46_1_1_1_1_1.jpg', '', '', '1', '2018-11-20 17:37:46', '2', null, '2018-11-19 06:53:46', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('188', 'c93887cd-a65f-4d25-a00d-941e35f1c42c', '1', '2', '5', '0', '115.199.108.224', '2018-11-20 10:24:52', '1', '', '/getrecordimg/1/1/2/20181120/2018-11-19_09_52_19_1_1_1_2_1.jpg', '', '', '1', '2018-11-20 17:37:49', '2', null, '2018-11-19 09:52:19', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('189', '326aa764-78f9-4a8b-a32a-282cf8a1036e', '1', '5', '5', '0', '115.199.108.224', '2018-11-20 10:29:14', '27', '', '/getrecordimg/1/1/5/20181120/2018-11-19_02_45_12_1_1_1_5_1.jpg', '', '', '1', '2018-11-20 17:37:51', '2', null, '2018-11-19 02:45:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('190', 'efff827b-d2fe-4968-b604-578e5054230a', '1', '8', '5', '0', '115.199.108.224', '2018-11-20 10:33:04', '24', '', '/getrecordimg/1/1/8/20181120/2018-11-19_09_28_12_1_1_1_8_1.jpg', '', '', '1', '2018-11-20 17:37:53', '2', null, '2018-11-19 09:28:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('191', '6ae6f0a4-b8c0-4223-8a89-766209ec6370', '1', '1', '5', '0', '115.199.108.224', '2018-11-21 14:01:10', '1', '', '/getrecordimg/1/1/1/20181121/2018-11-20_12_03_37_1_1_1_1_1.jpg', '', '', '1', '2018-11-21 17:18:23', '2', null, '2018-11-20 12:03:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('192', '2d93cfb0-237e-46f0-adc8-c7b324c21325', '1', '1', '5', '0', '115.199.108.224', '2018-11-21 14:01:52', '1', '', '/getrecordimg/1/1/1/20181121/2018-11-20_13_35_47_1_1_1_1_1.jpg', '', '', '1', '2018-11-21 17:18:25', '2', null, '2018-11-20 13:35:47', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('193', '4a92b67f-8752-4d13-8910-65bc634b44c1', '1', '2', '5', '0', '115.199.108.224', '2018-11-21 14:04:04', '1', '', '/getrecordimg/1/1/2/20181121/2018-11-20_12_08_49_1_1_1_2_1.jpg', '', '', '1', '2018-11-21 17:18:27', '2', null, '2018-11-20 12:08:49', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('194', '5052ffb1-b5a6-4f24-b9f4-a1c129737281', '1', '5', '5', '0', '115.199.108.224', '2018-11-21 14:06:50', '27', '', '/getrecordimg/1/1/5/20181121/2018-11-20_00_19_38_1_1_1_5_1.jpg', '', '', '1', '2018-11-21 17:18:30', '2', null, '2018-11-20 00:19:38', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('195', '151142a5-d336-401c-aaf6-96c8cd639b9c', '1', '7', '5', '0', '115.199.108.224', '2018-11-21 14:09:16', '1', '', '/getrecordimg/1/1/7/20181121/2018-11-20_13_35_59_1_1_1_7_1.jpg', '', '', '1', '2018-11-21 17:18:32', '2', null, '2018-11-20 13:35:59', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('196', '0256ed04-c576-4d89-a819-b3f9e0b1c62e', '1', '8', '5', '0', '115.199.108.224', '2018-11-21 14:10:23', '24', '', '/getrecordimg/1/1/8/20181121/2018-11-20_08_54_42_1_1_1_8_1.jpg', '', '', '1', '2018-11-21 17:18:34', '2', null, '2018-11-20 08:54:42', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('197', '98475305-5ff7-49ff-84b8-398bcb4032e9', '1', '7', '5', '0', '115.199.108.224', '2018-11-22 19:19:20', '27', '', '/getrecordimg/1/1/7/20181122/2018-11-21_12_29_50_1_1_1_7_1.jpg', '', '', '1', '2018-11-22 19:21:56', '2', null, '2018-11-21 12:29:50', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('198', '7c21ac9b-7e25-4162-aaf6-c5ce72b38252', '1', '7', '5', '0', '115.199.108.224', '2018-11-22 19:19:36', '27', '', '/getrecordimg/1/1/7/20181122/2018-11-21_14_17_28_1_1_1_7_1.jpg', '', '', '1', '2018-11-22 19:22:03', '2', null, '2018-11-21 14:17:28', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('199', '2cd22cfb-ade8-43e5-b331-ed295270660f', '1', '7', '5', '0', '115.199.108.224', '2018-11-22 19:19:44', '27', '', '/getrecordimg/1/1/7/20181122/2018-11-21_16_30_59_1_1_1_7_1.jpg', '', '', '1', '2018-11-22 19:22:06', '2', null, '2018-11-21 16:30:59', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('200', 'f6d88b2c-fc72-4420-bc16-1fd7c6a10a63', '1', '8', '5', '0', '115.199.108.224', '2018-11-22 19:20:45', '45', '', '/getrecordimg/1/1/8/20181122/2018-11-21_09_57_12_1_1_1_8_1.jpg', '', '', '1', '2018-11-22 19:22:15', '2', null, '2018-11-21 09:57:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('201', '8a2b9871-b839-4e03-908a-c1cd338544c7', '1', '1', '5', '0', '115.199.108.224', '2018-11-22 19:21:51', '1', '', '/getrecordimg/1/1/1/20181122/2018-11-21_23_41_40_1_1_1_1_1.jpg', '', '', '1', '2018-11-22 19:22:17', '2', null, '2018-11-21 23:41:40', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('202', '89e30c6a-8597-4a33-9f0f-1865a59c8436', '1', '1', '5', '0', '115.199.108.224', '2018-11-23 10:01:52', '1', '', '/getrecordimg/1/1/1/20181123/2018-11-22_17_16_43_1_1_1_1_1.jpg', '', '', '1', '2018-11-23 13:35:24', '2', null, '2018-11-22 17:16:43', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('203', 'abc3e19f-0789-4f3a-a10c-75cb480d7b1e', '1', '1', '5', '0', '115.199.108.224', '2018-11-23 10:02:50', '1', '', '/getrecordimg/1/1/1/20181123/2018-11-22_20_56_26_1_1_1_1_1.jpg', '', '', '1', '2018-11-23 13:35:26', '2', null, '2018-11-22 20:56:26', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('204', '2fe0eb35-6651-44b3-a286-2754d566f630', '1', '3', '5', '0', '115.199.108.224', '2018-11-23 10:10:45', '1', '', '/getrecordimg/1/1/3/20181123/2018-11-22_09_09_18_1_1_1_3_1.jpg', '', '', '1', '2018-11-23 13:35:28', '2', null, '2018-11-22 09:09:18', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('205', 'b0e3b311-f825-4835-9e86-5a9b8382da69', '1', '3', '5', '0', '115.199.108.224', '2018-11-23 10:11:16', '1', '', '/getrecordimg/1/1/3/20181123/2018-11-22_12_15_35_1_1_1_3_1.jpg', '', '', '1', '2018-11-23 13:35:31', '2', null, '2018-11-22 12:15:35', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('206', '543c4d34-0afb-448c-afbb-95593fb99e9f', '1', '7', '5', '0', '115.199.108.224', '2018-11-23 10:16:32', '27', '', '/getrecordimg/1/1/7/20181123/2018-11-22_06_17_46_1_1_1_7_1.jpg', '', '', '1', '2018-11-23 13:35:33', '2', null, '2018-11-22 06:17:46', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('207', 'f9ee42ac-0253-4502-8d96-b6e8392093d7', '1', '7', '5', '0', '115.199.108.224', '2018-11-23 10:18:31', '45', '', '/getrecordimg/1/1/7/20181123/2018-11-22_19_46_36_1_1_1_7_1.jpg', '', '', '1', '2018-11-23 13:35:37', '2', null, '2018-11-22 19:46:36', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('208', 'c56e4a2b-db30-41b4-90da-7a8171edee10', '1', '7', '5', '0', '115.199.108.224', '2018-11-23 10:19:04', '30', '', '/getrecordimg/1/1/7/20181123/2018-11-22_07_53_56_1_1_1_7_1.jpg', '', '', '1', '2018-11-23 13:35:40', '2', null, '2018-11-22 07:53:56', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('209', '3ce2eab3-4084-47ae-b1f2-c6085c1f25a7', '1', '7', '5', '0', '115.199.108.224', '2018-11-23 10:19:36', '27', '', '/getrecordimg/1/1/7/20181123/2018-11-22_07_56_29_1_1_1_7_1.jpg', '', '', '1', '2018-11-23 13:35:43', '0', null, '2018-11-22 07:56:29', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('210', '1b515ea7-edf7-4985-b6c1-7e2fa6ac0485', '1', '8', '5', '0', '115.199.108.224', '2018-11-23 10:20:34', '20', '', '/getrecordimg/1/1/8/20181123/2018-11-22_09_30_21_1_1_1_8_1.jpg', '', '', '1', '2018-11-23 13:35:47', '0', null, '2018-11-22 09:30:21', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('211', '7c170fd8-377e-4dc4-96f2-777d99e58b54', '1', '1', '5', '0', '115.199.108.224', '2018-11-23 10:21:51', '47', '', '/getrecordimg/1/1/1/20181123/2018-11-22_20_23_34_1_1_1_1_1.jpg', '', '', '1', '2018-11-23 13:35:53', '0', null, '2018-11-22 20:23:34', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('212', 'cdf6d5db-b461-455f-ac7d-4ae0cf9e9a8a', '1', '1', '5', '0', '115.199.108.224', '2018-11-23 10:23:24', '1', '', '/getrecordimg/1/1/1/20181123/2018-11-22_17_16_27_1_1_1_1_1.jpg', '', '', '1', '2018-11-23 13:35:56', '2', null, '2018-11-22 17:16:27', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('213', 'bdd5e091-0dc1-455b-b959-5688625bcf4d', '1', '3', '5', '0', '115.199.108.224', '2018-11-23 10:30:29', '37', '', '/getrecordimg/1/1/3/20181123/2018-11-22_16_38_39_1_1_1_3_1.jpg', '', '', '1', '2018-11-23 13:36:14', '0', null, '2018-11-22 16:38:39', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('214', '229bd8d5-5ee2-4876-bc3f-ab11ab8ee345', '1', '1', '5', '0', '115.199.108.224', '2018-11-23 10:24:17', '47', '', '/getrecordimg/1/1/1/20181123/2018-11-22_10_15_52_1_1_1_1_1.jpg', '', '', '1', '2018-11-23 13:36:17', '2', null, '2018-11-22 10:15:52', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('215', 'a2246dc3-dd0e-4f5c-aaa1-48eb3379be57', '1', '2', '5', '0', '115.199.108.224', '2018-11-23 10:28:11', '47', '', '/getrecordimg/1/1/2/20181123/2018-11-22_03_38_34_1_1_1_2_1.jpg', '', '', '1', '2018-11-23 13:36:19', '0', null, '2018-11-22 03:38:34', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('216', 'c2431566-d949-44ab-9d83-79822b163038', '1', '1', '5', '0', '115.199.109.63', '2018-11-28 10:17:21', '1', '', '/getrecordimg/1/1/1/20181128/2018-11-27_18_38_10_1_1_1_1_1.jpg', '', '', '1', '2018-11-28 17:36:59', '2', null, '2018-11-27 18:38:10', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('217', 'bd377aee-3226-4208-822b-697fd10937d6', '1', '1', '5', '0', '115.199.109.63', '2018-11-28 10:18:01', '1', '', '/getrecordimg/1/1/1/20181128/2018-11-27_20_29_57_1_1_1_1_1.jpg', '', '', '1', '2018-11-28 17:37:01', '2', null, '2018-11-27 20:29:57', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('218', 'b9ad7e62-0e81-4562-809d-299e7168adc2', '1', '3', '5', '0', '115.199.109.63', '2018-11-28 10:20:05', '1', '', '/getrecordimg/1/1/3/20181128/2018-11-27_17_02_54_1_1_1_3_1.jpg', '', '', '1', '2018-11-28 17:37:03', '2', null, '2018-11-27 17:02:54', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('219', '22d0c9f9-f483-40a5-82c6-c4fa94d7344a', '1', '3', '5', '0', '115.199.109.63', '2018-11-28 10:21:54', '49', '', '/getrecordimg/1/1/3/20181128/2018-11-27_21_35_24_1_1_1_3_1.jpg', '', '', '1', '2018-11-28 17:37:06', '2', null, '2018-11-27 21:35:24', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('220', 'ce8b7b79-b99d-40bd-a2c7-2e1245ad48b5', '1', '6', '5', '0', '115.199.109.63', '2018-11-28 10:25:31', '1', '', '/getrecordimg/1/1/6/20181128/2018-11-27_22_18_08_1_1_1_6_1.jpg', '', '', '1', '2018-11-28 17:37:07', '2', null, '2018-11-27 22:18:08', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('221', '22b78f93-3963-449b-b0ee-2d80730d6062', '1', '8', '5', '0', '115.199.109.63', '2018-11-28 10:27:16', '24', '', '/getrecordimg/1/1/8/20181128/2018-11-27_17_16_49_1_1_1_8_1.jpg', '', '', '1', '2018-11-28 17:37:12', '2', null, '2018-11-27 17:16:49', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('222', 'f0e598ae-abe0-4d26-a83e-09447bbe2514', '1', '1', '5', '0', '115.196.143.225', '2018-12-03 18:43:06', '1', '', '/getrecordimg/1/1/1/20181203/2018-12-02_12_44_08_1_1_1_1_1.jpg', '', '', '1', '2018-12-03 18:49:37', '2', null, '2018-12-02 12:44:08', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('223', 'ab48bd2c-e940-4d4e-8677-7cc39fd31056', '1', '3', '5', '0', '115.196.143.225', '2018-12-03 18:43:50', '49', '', '/getrecordimg/1/1/3/20181203/2018-12-02_00_00_10_1_1_1_3_1.jpg', '', '', '1', '2018-12-03 18:49:40', '2', null, '2018-12-02 00:00:10', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('224', 'bfa81a61-245d-4bfc-9d3d-e32f79cc2491', '1', '4', '5', '0', '115.196.143.225', '2018-12-03 18:44:25', '27', '', '/getrecordimg/1/1/4/20181203/2018-12-02_12_17_04_1_1_1_4_1.jpg', '', '', '1', '2018-12-03 18:49:42', '2', null, '2018-12-02 12:17:04', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('225', 'a46a5268-51e9-4ca9-8cf7-64f5690e810b', '1', '5', '5', '0', '115.196.143.225', '2018-12-03 18:49:16', '96', '', '/getrecordimg/1/1/5/20181203/2018-12-02_16_41_45_1_1_1_5_1.jpg', '', '', '1', '2018-12-03 19:02:37', '0', null, '2018-12-02 16:41:45', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('226', '0da91086-0e9c-4f27-8e62-21babd707d55', '1', '8', '5', '0', '115.196.143.225', '2018-12-03 18:50:21', '19', '', '/getrecordimg/1/1/8/20181203/2018-12-02_11_51_34_1_1_1_8_1.jpg', '', '', '1', '2018-12-03 19:02:51', '0', null, '2018-12-02 11:51:34', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('227', '25a14cf3-354e-4a02-aba8-45b468c556fd', '1', '1', '5', '0', '115.196.143.225', '2018-12-04 18:26:18', '1', '', '/getrecordimg/1/1/1/20181204/2018-12-03_10_21_56_1_1_1_1_1.jpg', '', '', '1', '2018-12-04 18:47:28', '2', null, '2018-12-03 10:21:56', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('228', '66c1f3c0-4578-4fdb-ba3a-8b33e45f4440', '1', '3', '5', '0', '115.196.143.225', '2018-12-04 18:27:31', '49', '', '/getrecordimg/1/1/3/20181204/2018-12-03_03_10_09_1_1_1_3_1.jpg', '', '', '1', '2018-12-04 18:47:30', '2', null, '2018-12-03 03:10:09', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('229', 'b710d04b-11f1-4d78-b952-57e83afef749', '1', '5', '5', '0', '115.196.143.225', '2018-12-04 18:28:45', '27', '', '/getrecordimg/1/1/5/20181204/2018-12-03_13_33_17_1_1_1_5_1.jpg', '', '', '1', '2018-12-04 18:47:33', '2', null, '2018-12-03 13:33:17', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('230', '76406f72-b43c-460e-be83-3ba242005320', '1', '8', '5', '0', '115.196.143.225', '2018-12-04 18:29:32', '19', '', '/getrecordimg/1/1/8/20181204/2018-12-03_06_44_01_1_1_1_8_1.jpg', '', '', '1', '2018-12-04 18:47:36', '0', null, '2018-12-03 06:44:01', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('231', '4cde6b28-2a92-4929-b87b-b3a5de13c033', '1', '1', '5', '0', '115.196.143.225', '2018-12-05 16:59:31', '1', '', '/getrecordimg/1/1/1/20181205/2018-12-04_09_28_37_1_1_1_1_1.jpg', '', '', '1', '2018-12-05 17:15:11', '2', null, '2018-12-04 09:28:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('232', 'c7c86722-74df-4a09-b791-8e0c2ed5d664', '1', '3', '5', '0', '115.196.143.225', '2018-12-05 17:00:57', '49', '', '/getrecordimg/1/1/3/20181205/2018-12-04_07_30_06_1_1_1_3_1.jpg', '', '', '1', '2018-12-05 17:15:16', '2', null, '2018-12-04 07:30:06', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('233', 'cad113f6-c0c2-4c8c-922f-df4d4f1d615c', '1', '8', '5', '0', '115.196.143.225', '2018-12-05 17:02:09', '19', '', '/getrecordimg/1/1/8/20181205/2018-12-04_07_48_03_1_1_1_8_1.jpg', '', '', '1', '2018-12-05 17:15:19', '0', null, '2018-12-04 07:48:03', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('234', '3b23c4a7-5b8b-4647-82d0-1b6d92834a17', '1', '1', '5', '0', '115.196.143.225', '2018-12-05 17:02:52', '47', '', '/getrecordimg/1/1/1/20181205/2018-12-04_13_04_53_1_1_1_1_1.jpg', '', '', '1', '2018-12-05 17:15:22', '2', null, '2018-12-04 13:04:53', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('235', '9246df14-a9f0-4f50-8fc2-08e1bdde1f55', '1', '1', '5', '0', '115.196.143.225', '2018-12-05 17:03:54', '24', '', '/getrecordimg/1/1/1/20181205/2018-12-04_13_29_12_1_1_1_1_1.jpg', '', '', '1', '2018-12-05 17:15:30', '0', null, '2018-12-04 13:29:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('236', 'a515dd9a-9a65-4f58-8ec6-abbe0156df9e', '1', '1', '5', '0', '115.199.98.88', '2018-12-06 16:41:34', '47', '', '/getrecordimg/1/1/1/20181206/2018-12-05_09_21_23_1_1_1_1_1.jpg', '', '', '1', '2018-12-06 16:51:02', '2', null, '2018-12-05 09:21:23', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('237', '6b46dab1-701f-4c37-92f5-8a07736a3b1b', '1', '1', '5', '0', '115.199.98.88', '2018-12-06 16:42:08', '1', '', '/getrecordimg/1/1/1/20181206/2018-12-05_11_52_03_1_1_1_1_1.jpg', '', '', '1', '2018-12-06 16:51:03', '2', null, '2018-12-05 11:52:03', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('238', 'd5feaa34-b709-444e-a42f-4e44a25ee1ee', '1', '3', '5', '0', '115.199.98.88', '2018-12-06 16:42:37', '49', '', '/getrecordimg/1/1/3/20181206/2018-12-05_00_28_49_1_1_1_3_1.jpg', '', '', '1', '2018-12-06 16:51:09', '2', null, '2018-12-05 00:28:49', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('239', '70e53c08-48ac-4d1f-9ff3-09c62bdcc89b', '1', '1', '5', '0', '115.199.98.88', '2018-12-06 16:41:09', '24', '', '/getrecordimg/1/1/1/20181206/2018-12-05_08_01_33_1_1_1_1_1.jpg', '', '', '1', '2018-12-06 16:51:13', '0', null, '2018-12-05 08:01:33', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('240', 'ab1cd6dd-41b7-4299-b55b-91a115c3572c', '1', '8', '5', '0', '115.199.98.88', '2018-12-06 16:43:19', '19', '', '/getrecordimg/1/1/8/20181206/2018-12-05_09_11_36_1_1_1_8_1.jpg', '', '', '1', '2018-12-06 16:51:18', '0', null, '2018-12-05 09:11:36', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('241', '5110907c-e0e9-40fc-b19e-ba6109bb8d12', '1', '1', '5', '0', '115.199.98.88', '2018-12-07 17:00:15', '1', '', '/getrecordimg/1/1/1/20181207/2018-12-06_00_24_21_1_1_1_1_1.jpg', '', '', '1', '2018-12-07 17:06:18', '2', null, '2018-12-06 00:24:21', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('242', '72b92635-fa78-4164-b81e-64343fc7c31f', '1', '1', '5', '0', '115.199.98.88', '2018-12-07 17:00:29', '47', '', '/getrecordimg/1/1/1/20181207/2018-12-06_07_49_40_1_1_1_1_1.jpg', '', '', '1', '2018-12-07 17:06:21', '2', null, '2018-12-06 07:49:40', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('243', '3a379fa1-226e-487c-94ca-455ca1b1db24', '1', '1', '5', '0', '115.199.98.88', '2018-12-07 17:00:43', '24', '', '/getrecordimg/1/1/1/20181207/2018-12-06_11_10_15_1_1_1_1_1.jpg', '', '', '1', '2018-12-07 17:06:24', '0', null, '2018-12-06 11:10:15', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('244', 'a3d4d120-b383-4817-85c1-9c40ac8592e7', '1', '3', '5', '0', '115.199.98.88', '2018-12-07 17:01:12', '49', '', '/getrecordimg/1/1/3/20181207/2018-12-06_00_08_37_1_1_1_3_1.jpg', '', '', '1', '2018-12-07 17:06:26', '2', null, '2018-12-06 00:08:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('245', '7d68a8cb-ce3c-4aae-b6bc-e126c38c0a33', '1', '8', '5', '0', '115.199.98.88', '2018-12-07 17:02:19', '6', '', '/getrecordimg/1/1/8/20181207/2018-12-06_12_43_19_1_1_1_8_1.jpg', '', '', '1', '2018-12-07 17:06:31', '0', null, '2018-12-06 12:43:19', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('246', '2f5e8e30-02d7-4fc5-9950-2553d498d66d', '1', '8', '5', '0', '115.199.98.88', '2018-12-07 17:02:36', '19', '', '/getrecordimg/1/1/8/20181207/2018-12-06_15_59_02_1_1_1_8_1.jpg', '', '', '1', '2018-12-07 17:06:36', '0', null, '2018-12-06 15:59:02', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('247', '84cc6b7d-a2d9-4945-96d7-42e6cb85ab01', '1', '1', '5', '0', '115.199.102.12', '2018-12-10 11:20:49', '1', '', '/getrecordimg/1/1/1/20181210/2018-12-09_11_12_37_1_1_1_1_1.jpg', '', '', '1', '2018-12-10 16:50:23', '2', null, '2018-12-09 11:12:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('248', '0e684f1a-9ca8-4b74-b0d6-d5a21e67688f', '1', '1', '5', '0', '115.199.102.12', '2018-12-10 11:21:12', '47', '', '/getrecordimg/1/1/1/20181210/2018-12-09_14_27_31_1_1_1_1_1.jpg', '', '', '1', '2018-12-10 16:50:27', '2', null, '2018-12-09 14:27:31', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('249', '69032438-7b0a-46fb-a437-e20cb7d3d799', '1', '3', '5', '0', '115.199.102.12', '2018-12-10 11:21:27', '49', '', '/getrecordimg/1/1/3/20181210/2018-12-09_00_38_12_1_1_1_3_1.jpg', '', '', '1', '2018-12-10 16:50:35', '2', null, '2018-12-09 00:38:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('250', 'c904155a-7e0d-4e1b-b2a4-c3a0103a5020', '1', '1', '5', '0', '115.199.102.12', '2018-12-10 11:23:12', '24', '', '/getrecordimg/1/1/1/20181210/2018-12-09_14_27_31_1_1_1_1_2.jpg', '', '', '1', '2018-12-10 16:50:38', '0', null, '2018-12-09 14:27:31', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('251', '51c86823-8b82-4de6-81cc-95b7dd7dd06f', '1', '6', '5', '0', '115.199.102.12', '2018-12-10 11:23:46', '6', '', '/getrecordimg/1/1/6/20181210/2018-12-09_08_28_26_1_1_1_6_1.jpg', '', '', '1', '2018-12-10 16:50:47', '0', null, '2018-12-09 08:28:26', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('252', 'f0c092b3-6be4-4228-a7c4-7863776d7e13', '1', '2', '5', '0', '115.199.102.12', '2018-12-11 11:20:26', '47', '', '/getrecordimg/1/1/2/20181211/2018-12-10_08_05_10_1_1_1_2_1.jpg', '', '', '1', '2018-12-11 16:46:07', '2', null, '2018-12-10 08:05:10', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('253', '76996d34-8331-437d-acdd-17a12207bb12', '1', '2', '5', '0', '115.199.102.12', '2018-12-11 11:20:54', '1', '', '/getrecordimg/1/1/2/20181211/2018-12-10_10_09_31_1_1_1_2_1.jpg', '', '', '1', '2018-12-11 16:46:10', '2', null, '2018-12-10 10:09:31', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('254', '03c583cc-3a77-441f-a857-f9c18381be38', '1', '4', '5', '0', '115.199.102.12', '2018-12-11 11:23:10', '27', '', '/getrecordimg/1/1/4/20181211/2018-12-10_15_52_37_1_1_1_4_1.jpg', '', '', '1', '2018-12-11 16:46:13', '2', null, '2018-12-10 15:52:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('255', '8b8f6782-65f0-41a3-bd1e-ca046a058276', '1', '6', '5', '0', '115.199.102.12', '2018-12-11 11:23:49', '36', '', '/getrecordimg/1/1/6/20181211/2018-12-10_14_22_41_1_1_1_6_1.jpg', '', '', '1', '2018-12-11 16:46:15', '2', null, '2018-12-10 14:22:41', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('256', '61441f4c-6616-4183-b3c2-10e2a486ca77', '1', '1', '5', '0', '115.199.102.12', '2018-12-12 11:22:08', '1', '', '/getrecordimg/1/1/1/20181212/2018-12-11_13_00_38_1_1_1_1_1.jpg', '', '', '1', '2018-12-12 13:40:10', '2', null, '2018-12-11 13:00:38', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('257', 'f774d7e6-1727-4227-b592-cdba6bad8b56', '1', '1', '5', '0', '115.199.102.12', '2018-12-12 11:22:18', '47', '', '/getrecordimg/1/1/1/20181212/2018-12-11_13_44_09_1_1_1_1_1.jpg', '', '', '1', '2018-12-12 13:40:13', '2', null, '2018-12-11 13:44:09', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('258', '65b2bf34-8475-46fe-be8c-2d38648b5913', '1', '2', '5', '0', '115.199.102.12', '2018-12-12 11:23:48', '24', '', '/getrecordimg/1/1/2/20181212/2018-12-11_13_05_31_1_1_1_2_1.jpg', '', '', '1', '2018-12-12 13:40:17', '0', null, '2018-12-11 13:05:31', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('259', '471404c3-a96b-49b0-bf02-07b772ecb015', '1', '6', '5', '0', '115.199.102.12', '2018-12-12 11:26:46', '27', '', '/getrecordimg/1/1/6/20181212/2018-12-11_08_08_38_1_1_1_6_1.jpg', '', '', '1', '2018-12-12 13:40:19', '2', null, '2018-12-11 08:08:38', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('260', 'c44552b0-77f1-49fe-a6a0-1e4b84816106', '1', '1', '5', '0', '115.199.102.12', '2018-12-13 17:23:18', '1', '', '/getrecordimg/1/1/1/20181213/2018-12-12_03_03_37_1_1_1_1_1.jpg', '', '', '1', '2018-12-13 17:28:30', '2', null, '2018-12-12 03:03:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('261', '5ee0960a-9171-401e-a7e4-0901e0eff81a', '1', '3', '5', '0', '115.199.102.12', '2018-12-13 17:24:56', '49', '', '/getrecordimg/1/1/3/20181213/2018-12-12_01_03_27_1_1_1_3_1.jpg', '', '', '1', '2018-12-13 17:28:34', '2', null, '2018-12-12 01:03:27', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('262', '6196812c-4581-4b87-9952-1e12e1ce949a', '1', '4', '5', '0', '115.199.102.12', '2018-12-13 17:26:10', '37', '', '/getrecordimg/1/1/4/20181213/2018-12-12_15_21_57_1_1_1_4_1.jpg', '', '', '1', '2018-12-13 17:28:45', '2', null, '2018-12-12 15:21:57', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('263', '5328b870-64b9-4c3b-a6b2-84048b6b5bea', '1', '3', '5', '0', '115.199.102.12', '2018-12-13 17:25:31', '47', '', '/getrecordimg/1/1/3/20181213/2018-12-12_13_20_44_1_1_1_3_1.jpg', '', '', '1', '2018-12-13 17:28:47', '0', null, '2018-12-12 13:20:44', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('264', '7bd43f14-2323-4f94-a07d-9a05ff5cd221', '1', '6', '5', '0', '115.199.102.12', '2018-12-13 17:28:51', '24', '', '/getrecordimg/1/1/6/20181213/2018-12-12_08_03_20_1_1_1_6_1.jpg', '', '', '1', '2018-12-13 17:29:11', '2', null, '2018-12-12 08:03:20', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('265', 'f97795f2-a2f6-4ab6-824b-9ffe8225f1c0', '1', '1', '5', '0', '115.194.183.182', '2018-12-14 16:52:09', '1', '', '/getrecordimg/1/1/1/20181214/2018-12-13_14_18_36_1_1_1_1_1.jpg', '', '', '1', '2018-12-14 17:16:50', '2', null, '2018-12-13 14:18:36', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('266', '0db780c2-ac6a-4a0d-9189-25016d11eeb3', '1', '3', '5', '0', '115.194.183.182', '2018-12-14 16:53:35', '49', '', '/getrecordimg/1/1/3/20181214/2018-12-13_01_51_09_1_1_1_3_1.jpg', '', '', '1', '2018-12-14 17:16:53', '2', null, '2018-12-13 01:51:09', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('267', '01e1121a-989f-46d3-bf9e-a301700f1585', '1', '4', '5', '0', '115.194.183.182', '2018-12-14 16:53:57', '37', '', '/getrecordimg/1/1/4/20181214/2018-12-13_14_40_23_1_1_1_4_1.jpg', '', '', '1', '2018-12-14 17:16:59', '2', null, '2018-12-13 14:40:23', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('268', '2e42d48f-11f3-4a1b-867c-3e4ac445f3a9', '1', '6', '5', '0', '115.194.183.182', '2018-12-14 16:54:29', '24', '', '/getrecordimg/1/1/6/20181214/2018-12-13_08_55_48_1_1_1_6_1.jpg', '', '', '1', '2018-12-14 17:17:01', '2', null, '2018-12-13 08:55:48', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('269', 'f26a5462-7c2f-4290-bfcb-f627026d6c23', '1', '1', '5', '0', '115.194.183.182', '2018-12-14 16:51:19', '47', '', '/getrecordimg/1/1/1/20181214/2018-12-13_08_51_22_1_1_1_1_1.jpg', '', '', '1', '2018-12-14 17:17:04', '0', null, '2018-12-13 08:51:22', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('270', 'bf6e921c-4ebc-4f23-b62b-738bac97434a', '1', '1', '5', '0', '115.194.183.182', '2018-12-17 16:29:29', '1', '', '/getrecordimg/1/1/1/20181217/2018-12-16_14_00_07_1_1_1_1_1.jpg', '', '', '1', '2018-12-17 16:41:09', '2', null, '2018-12-16 14:00:07', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('271', '9b4cff8c-d783-4fa7-8d8c-b0fd45aac42d', '1', '3', '5', '0', '115.194.183.182', '2018-12-17 16:33:02', '49', '', '/getrecordimg/1/1/3/20181217/2018-12-16_13_37_44_1_1_1_3_1.jpg', '', '', '1', '2018-12-17 16:41:11', '2', null, '2018-12-16 13:37:44', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('272', '26009eb3-774a-4934-81de-9a1358ba6a7a', '1', '4', '5', '0', '115.194.183.182', '2018-12-17 16:33:34', '24', '', '/getrecordimg/1/1/4/20181217/2018-12-16_15_45_22_1_1_1_4_1.jpg', '', '', '1', '2018-12-17 16:41:18', '2', null, '2018-12-16 15:45:22', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('273', 'c560306f-95ca-439b-8354-4cb855a6f664', '1', '2', '5', '0', '115.194.183.182', '2018-12-17 16:36:58', '53', '', '/getrecordimg/1/1/2/20181217/2018-12-16_13_20_40_1_1_1_2_1.jpg', '', '', '1', '2018-12-17 16:41:26', '2', null, '2018-12-16 13:20:40', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('274', '6006f13b-33f7-4538-9b1a-b9fd4f281827', '1', '1', '5', '0', '115.194.183.182', '2018-12-18 11:17:57', '1', '', '/getrecordimg/1/1/1/20181218/2018-12-17_19_09_22_1_1_1_1_1.jpg', '', '', '1', '2018-12-18 17:11:54', '2', null, '2018-12-17 19:09:22', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('275', 'b307b608-46ee-4e88-b62c-12e748666363', '1', '3', '5', '0', '115.194.183.182', '2018-12-18 11:19:12', '49', '', '/getrecordimg/1/1/3/20181218/2018-12-17_07_10_57_1_1_1_3_1.jpg', '', '', '1', '2018-12-18 17:11:57', '2', null, '2018-12-17 07:10:57', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('276', 'b5dc823b-4393-487f-b432-689fc4755f38', '1', '4', '5', '0', '115.194.183.182', '2018-12-18 11:19:34', '36', '', '/getrecordimg/1/1/4/20181218/2018-12-17_16_56_04_1_1_1_4_1.jpg', '', '', '1', '2018-12-18 17:12:14', '2', null, '2018-12-17 16:56:04', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('277', '8ce6bb16-d43d-4b05-826f-3d12251907b8', '1', '6', '5', '0', '115.194.183.182', '2018-12-18 11:20:11', '27', '', '/getrecordimg/1/1/6/20181218/2018-12-17_15_03_45_1_1_1_6_1.jpg', '', '', '1', '2018-12-18 17:12:18', '0', null, '2018-12-17 15:03:45', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('278', '4cadef77-a484-43b3-8024-69e86541fc3f', '1', '5', '5', '0', '115.194.183.182', '2018-12-18 11:21:01', '27', '', '/getrecordimg/1/1/5/20181218/2018-12-17_07_21_35_1_1_1_5_1.jpg', '', '', '1', '2018-12-18 17:12:21', '2', null, '2018-12-17 07:21:35', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('279', '3ea737cd-a587-4874-bcdf-5b5c1eefd54c', '1', '1', '5', '0', '115.194.183.182', '2018-12-18 11:21:21', '46', '', '/getrecordimg/1/1/1/20181218/2018-12-17_19_25_37_1_1_1_1_1.jpg', '', '', '1', '2018-12-18 17:12:29', '0', null, '2018-12-17 19:25:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('280', '28ee8f84-71a3-4d13-a81e-bcf6ee46ba41', '1', '1', '5', '0', '125.120.161.56', '2018-12-19 15:46:30', '1', '', '/getrecordimg/1/1/1/20181219/2018-12-18_00_31_52_1_1_1_1_1.jpg', '', '', '1', '2018-12-19 17:13:39', '2', null, '2018-12-18 00:31:52', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('281', '3c9982ab-7393-4a34-8834-3facc2264b30', '1', '3', '5', '0', '125.120.161.56', '2018-12-19 15:47:21', '49', '', '/getrecordimg/1/1/3/20181219/2018-12-18_08_07_12_1_1_1_3_1.jpg', '', '', '1', '2018-12-19 17:13:42', '2', null, '2018-12-18 08:07:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('282', '513bddac-ee9b-41aa-bcb5-af20115abd77', '1', '5', '5', '0', '125.120.161.56', '2018-12-19 15:49:52', '27', '', '/getrecordimg/1/1/5/20181219/2018-12-18_10_29_50_1_1_1_5_1.jpg', '', '', '1', '2018-12-19 17:13:57', '2', null, '2018-12-18 10:29:50', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('283', 'cc41dd05-e2e7-4949-8151-fbd2bff849cf', '1', '4', '5', '0', '125.120.161.56', '2018-12-19 15:47:49', '36', '', '/getrecordimg/1/1/4/20181219/2018-12-18_12_35_48_1_1_1_4_1.jpg', '', '', '1', '2018-12-19 17:13:59', '2', null, '2018-12-18 12:35:48', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('284', '64c91d35-0686-42a2-9e07-81550304a273', '1', '1', '5', '0', '125.120.161.56', '2018-12-19 15:49:25', '24', '', '/getrecordimg/1/1/1/20181219/2018-12-18_16_04_53_1_1_1_1_1.jpg', '', '', '1', '2018-12-19 17:14:00', '0', null, '2018-12-18 16:04:53', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('285', '295f5c77-6b12-4488-8796-497f45fcabbb', '1', '1', '5', '0', '125.120.161.56', '2018-12-20 11:20:36', '1', '', '/getrecordimg/1/1/1/20181220/2018-12-19_06_34_59_1_1_1_1_1.jpg', '', '', '1', '2018-12-20 17:00:30', '2', null, '2018-12-19 06:34:59', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('286', '1b112f31-c99a-4647-b734-f90a6e2ba4ce', '1', '3', '5', '0', '125.120.161.56', '2018-12-20 11:21:24', '49', '', '/getrecordimg/1/1/3/20181220/2018-12-19_00_56_12_1_1_1_3_1.jpg', '', '', '1', '2018-12-20 17:00:33', '2', null, '2018-12-19 00:56:12', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('287', '6de930a4-3405-4ef5-b7ce-203e7d45f447', '1', '5', '5', '0', '125.120.161.56', '2018-12-20 11:22:51', '24', '', '/getrecordimg/1/1/5/20181220/2018-12-19_12_37_46_1_1_1_5_1.jpg', '', '', '1', '2018-12-20 17:00:41', '2', null, '2018-12-19 12:37:46', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('288', '8d6721f9-cd0d-47df-bcfd-55feec7a0e68', '1', '4', '5', '0', '125.120.161.56', '2018-12-20 11:21:58', '36', '', '/getrecordimg/1/1/4/20181220/2018-12-19_07_34_27_1_1_1_4_1.jpg', '', '', '1', '2018-12-20 17:00:44', '0', null, '2018-12-19 07:34:27', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('289', '5559736b-e2ad-47d9-84e3-c5836b55ad77', '1', '4', '5', '0', '125.120.161.56', '2018-12-20 11:22:12', '27', '', '/getrecordimg/1/1/4/20181220/2018-12-19_07_51_00_1_1_1_4_1.jpg', '', '', '1', '2018-12-20 17:00:45', '0', null, '2018-12-19 07:51:00', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('290', 'e9c559ea-5e80-4b73-b645-4d1f0c689f70', '1', '1', '5', '0', '125.120.161.56', '2018-12-21 15:20:50', '1', '', '/getrecordimg/1/1/1/20181221/2018-12-20_08_39_48_1_1_1_1_1.jpg', '', '', '1', '2018-12-21 16:36:07', '2', null, '2018-12-20 08:39:48', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('291', '944a8e25-dcb5-4b64-9c25-cb75e9b2633d', '1', '3', '5', '0', '125.120.161.56', '2018-12-21 15:23:31', '49', '', '/getrecordimg/1/1/3/20181221/2018-12-20_10_16_40_1_1_1_3_1.jpg', '', '', '1', '2018-12-21 16:36:10', '2', null, '2018-12-20 10:16:40', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('292', 'c061cbd7-d83e-488a-ad6a-0ba9581ff38c', '1', '2', '5', '0', '125.120.161.56', '2018-12-21 15:25:13', '47', '', '/getrecordimg/1/1/2/20181221/2018-12-20_12_00_36_1_1_1_2_1.jpg', '', '', '1', '2018-12-21 16:36:21', '2', null, '2018-12-20 12:00:36', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('293', 'bda90156-06d7-4254-82dd-e52b8cca96ad', '1', '4', '5', '0', '125.120.161.56', '2018-12-21 15:24:09', '36', '', '/getrecordimg/1/1/4/20181221/2018-12-20_10_39_45_1_1_1_4_1.jpg', '', '', '1', '2018-12-21 16:36:34', '2', null, '2018-12-20 10:39:45', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('294', 'ce70eba4-ea3c-4a24-8ded-60a69f28c99f', '1', '5', '5', '0', '125.120.161.56', '2018-12-21 15:24:27', '27', '', '/getrecordimg/1/1/5/20181221/2018-12-20_15_25_54_1_1_1_5_1.jpg', '', '', '1', '2018-12-21 16:36:35', '0', null, '2018-12-20 15:25:54', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('295', '3859d4a4-45d2-4972-a537-d42a332044ea', '1', '4', '5', '0', '115.192.117.36', '2018-12-24 11:43:06', '58', '', '/getrecordimg/1/1/4/20181224/2018-12-23_15_04_03_1_1_1_4_1.jpg', '', '', '1', '2018-12-24 17:53:10', '2', null, '2018-12-23 15:04:03', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('296', '858803b5-cebf-4d8b-a226-4cac0e0fb8cd', '1', '1', '5', '0', '115.192.117.36', '2018-12-24 11:43:51', '27', '', '/getrecordimg/1/1/1/20181224/2018-12-23_08_56_19_1_1_1_1_1.jpg', '', '', '1', '2018-12-24 17:53:13', '2', null, '2018-12-23 08:56:19', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('297', '3520f097-eafe-47a7-9b97-eab5960854c5', '1', '2', '5', '0', '115.192.117.36', '2018-12-24 11:45:11', '1', '', '/getrecordimg/1/1/2/20181224/2018-12-23_11_22_56_1_1_1_2_1.jpg', '', '', '1', '2018-12-24 17:53:16', '2', null, '2018-12-23 11:22:56', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('298', '980ff7ed-e877-4376-95cd-cf2b428198ab', '1', '3', '5', '0', '115.192.117.36', '2018-12-24 11:52:04', '1', '', '/getrecordimg/1/1/3/20181224/2018-12-23_23_15_04_1_1_1_3_1.jpg', '', '', '1', '2018-12-24 17:53:19', '2', null, '2018-12-23 23:15:04', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('299', '300e7f86-2cae-4b2f-a837-26748d2f8dc5', '1', '5', '5', '0', '115.192.117.36', '2018-12-24 11:53:33', '27', '', '/getrecordimg/1/1/5/20181224/2018-12-23_14_41_42_1_1_1_5_1.jpg', '', '', '1', '2018-12-24 17:53:22', '2', null, '2018-12-23 14:41:42', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('300', '82678ebe-86d8-447d-ba19-b2256727a1e8', '1', '1', '5', '0', '115.192.117.36', '2018-12-24 11:55:19', '45', '', '/getrecordimg/1/1/1/20181224/2018-12-23_07_54_00_1_1_1_1_1.jpg', '', '', '1', '2018-12-24 17:53:25', '2', null, '2018-12-23 07:54:00', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('301', '01ba9d8e-f537-447b-8d5f-cf0ca66d17ed', '1', '4', '5', '0', '115.192.117.36', '2018-12-24 11:59:01', '36', '', '/getrecordimg/1/1/4/20181224/2018-12-23_12_38_05_1_1_1_4_1.jpg', '', '', '1', '2018-12-24 17:53:28', '2', null, '2018-12-23 12:38:05', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('302', 'fc565ee4-6922-4f7c-aa7a-d7395d3de897', '1', '4', '5', '0', '115.192.117.36', '2018-12-24 11:59:34', '55', '', '/getrecordimg/1/1/4/20181224/2018-12-23_08_09_57_1_1_1_4_1.jpg', '', '', '1', '2018-12-24 17:53:37', '0', null, '2018-12-23 08:09:57', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('303', '40ca7e71-1f0d-4d9a-9a9d-26130f0afaf3', '1', '4', '5', '0', '115.192.117.36', '2018-12-24 12:00:39', '58', '', '/getrecordimg/1/1/4/20181224/2018-12-23_14_23_37_1_1_1_4_1.jpg', '', '', '1', '2018-12-24 17:53:42', '0', null, '2018-12-23 14:23:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('304', '7e23cb9b-cc6a-4624-b585-e87d0145690c', '1', '1', '5', '0', '115.192.117.36', '2018-12-25 12:16:56', '24', '', '/getrecordimg/1/1/1/20181225/2018-12-24_09_19_56_1_1_1_1_1.jpg', '', '', '1', '2018-12-25 15:47:42', '2', null, '2018-12-24 09:19:56', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('305', 'fa54f1d5-cd84-4449-b019-6a878158f838', '1', '1', '5', '0', '115.192.117.36', '2018-12-25 12:17:06', '45', '', '/getrecordimg/1/1/1/20181225/2018-12-24_09_20_11_1_1_1_1_1.jpg', '', '', '1', '2018-12-25 15:47:45', '0', null, '2018-12-24 09:20:11', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('306', '17ff48fb-fcbd-4017-98a1-10799144307f', '1', '1', '5', '0', '115.192.117.36', '2018-12-25 12:18:31', '31', '', '/getrecordimg/1/1/1/20181225/2018-12-24_17_21_16_1_1_1_1_1.jpg', '', '', '1', '2018-12-25 15:47:56', '2', null, '2018-12-24 17:21:16', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('307', 'caf2e428-f320-4802-bdad-8a1ae78affc2', '1', '4', '5', '0', '115.192.117.36', '2018-12-25 12:21:56', '59', '', '/getrecordimg/1/1/4/20181225/2018-12-24_15_45_40_1_1_1_4_1.jpg', '', '', '1', '2018-12-25 15:48:09', '2', null, '2018-12-24 15:45:40', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('308', 'a0a3a180-ea97-44c4-a43d-dfaba5341708', '1', '2', '5', '0', '115.192.117.36', '2018-12-25 12:57:50', '47', '', '/getrecordimg/1/1/2/20181225/2018-12-24_01_39_46_1_1_1_2_1.jpg', '', '', '1', '2018-12-25 15:48:16', '2', null, '2018-12-24 01:39:46', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('309', 'a2e099e9-6b13-49fc-89a1-4b96ebdc5244', '1', '3', '5', '0', '115.192.117.36', '2018-12-25 13:13:42', '1', '', '/getrecordimg/1/1/3/20181225/2018-12-24_09_13_06_1_1_1_3_1.jpg', '', '', '1', '2018-12-25 15:48:18', '0', null, '2018-12-24 09:13:06', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('310', 'f28590ac-bae8-452e-9f18-b6f2918dbd63', '1', '4', '5', '0', '115.192.117.36', '2018-12-25 13:21:06', '36', '', '/getrecordimg/1/1/4/20181225/2018-12-24_13_58_38_1_1_1_4_1.jpg', '', '', '1', '2018-12-25 15:48:20', '2', null, '2018-12-24 13:58:38', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('311', 'f5c5d99a-c230-41b1-abc4-261c5ba29e97', '1', '1', '5', '0', '122.233.188.41', '2018-12-26 14:07:13', '27', '', '/getrecordimg/1/1/1/20181226/2018-12-25_06_44_37_1_1_1_1_1.jpg', '', '', '1', '2018-12-26 16:28:35', '2', null, '2018-12-25 06:44:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('312', 'e18eaf08-234a-4748-a915-0ca210c0be5d', '1', '1', '5', '0', '122.233.188.41', '2018-12-26 14:07:22', '45', '', '/getrecordimg/1/1/1/20181226/2018-12-25_06_44_52_1_1_1_1_1.jpg', '', '', '1', '2018-12-26 16:28:37', '2', null, '2018-12-25 06:44:52', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('313', '07534116-5aff-473a-b9c4-1c37c2cbfdd1', '1', '3', '5', '0', '122.233.188.41', '2018-12-26 14:11:40', '1', '', '/getrecordimg/1/1/3/20181226/2018-12-25_11_59_23_1_1_1_3_1.jpg', '', '', '1', '2018-12-26 16:28:50', '2', null, '2018-12-25 11:59:23', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('314', '22f00536-f471-41f3-ad3d-c203a0975983', '1', '2', '5', '0', '122.233.188.41', '2018-12-26 14:16:37', '47', '', '/getrecordimg/1/1/2/20181226/2018-12-25_23_28_17_1_1_1_2_1.jpg', '', '', '1', '2018-12-26 16:28:53', '0', null, '2018-12-25 23:28:17', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('315', 'ec85b6e9-5899-46a5-bef9-26df6001224c', '1', '2', '5', '0', '122.233.188.41', '2018-12-26 14:17:12', '47', '', '/getrecordimg/1/1/2/20181226/2018-12-25_13_00_36_1_1_1_2_1.jpg', '', '', '1', '2018-12-26 16:28:55', '2', null, '2018-12-25 13:00:36', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('316', '629076fd-2e53-468c-b2ee-d9cbfe2e5523', '1', '6', '5', '0', '122.233.188.41', '2018-12-26 14:18:52', '45', '', '/getrecordimg/1/1/6/20181226/2018-12-25_23_50_07_1_1_1_6_1.jpg', '', '', '1', '2018-12-26 16:28:58', '2', null, '2018-12-25 23:50:07', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('317', '0cd7a3ae-c71a-407b-b1c6-982c6a4e18c3', '1', '1', '5', '0', '122.233.188.41', '2018-12-27 13:06:59', '45', '', '/getrecordimg/1/1/1/20181227/2018-12-26_14_45_37_1_1_1_1_1.jpg', '', '', '1', '2018-12-27 16:36:10', '2', null, '2018-12-26 14:45:37', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('318', '640cd6a1-c93a-4ee2-aaab-97883657defa', '1', '1', '5', '0', '122.233.188.41', '2018-12-27 13:07:32', '24', '', '/getrecordimg/1/1/1/20181227/2018-12-26_15_25_14_1_1_1_1_1.jpg', '', '', '1', '2018-12-27 16:36:15', '2', null, '2018-12-26 15:25:14', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('319', '24d83a05-0694-4587-a62d-a6edfe5b2c88', '1', '2', '5', '0', '122.233.188.41', '2018-12-27 13:09:29', '47', '', '/getrecordimg/1/1/2/20181227/2018-12-26_16_07_42_1_1_1_2_1.jpg', '', '', '1', '2018-12-27 16:36:18', '0', null, '2018-12-26 16:07:42', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('320', '62ed7f0a-dbb3-4902-87fa-2e4c08d5242a', '1', '3', '5', '0', '122.233.188.41', '2018-12-27 13:10:18', '1', '', '/getrecordimg/1/1/3/20181227/2018-12-26_09_08_25_1_1_1_3_1.jpg', '', '', '1', '2018-12-27 16:36:21', '2', null, '2018-12-26 09:08:25', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('321', '3b32cbc7-91ce-4943-ac1c-4b9c919922ea', '1', '3', '5', '0', '122.233.188.41', '2018-12-27 13:10:40', '1', '', '/getrecordimg/1/1/3/20181227/2018-12-26_15_23_32_1_1_1_3_1.jpg', '', '', '1', '2018-12-27 16:36:23', '0', null, '2018-12-26 15:23:32', '', '', '');
INSERT INTO `patrolrecordhistory` VALUES ('322', '68fe39f2-2cd0-48c8-9746-c6e14387dacb', '1', '2', '5', '0', '122.233.188.41', '2018-12-27 13:13:18', '45', '', '/getrecordimg/1/1/2/20181227/2018-12-26_12_17_21_1_1_1_2_1.jpg', '', '', '1', '2018-12-27 16:36:26', '2', null, '2018-12-26 12:17:21', '', '', '');

-- ----------------------------
-- Table structure for patrolreporthistory
-- ----------------------------
DROP TABLE IF EXISTS `patrolreporthistory`;
CREATE TABLE `patrolreporthistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reportid` varchar(128) NOT NULL,
  `reporturl` varchar(256) NOT NULL,
  `reportpdf` varchar(256) NOT NULL,
  `adminid` int(11) NOT NULL,
  `recordid` text NOT NULL,
  `createtime` datetime NOT NULL,
  `readtime` datetime DEFAULT NULL,
  `outletid` int(11) NOT NULL,
  `sendtime` datetime DEFAULT NULL,
  `severity` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `patrolreporthistorynormal` (`reportid`) USING HASH,
  KEY `patrolreporthistorynormalcreatetime` (`createtime`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patrolreporthistory
-- ----------------------------
INSERT INTO `patrolreporthistory` VALUES ('1', '1d2a8027-c148-494a-ad14-6395f9205bf9', '/mobilereport/1/1/1d2a8027-c148-494a-ad14-6395f9205bf9', '', '0', '[1,2]', '2018-10-17 15:25:44', null, '1', '2018-10-17 15:26:20', '1');
INSERT INTO `patrolreporthistory` VALUES ('2', '21ad7f10-3296-46d6-82dd-7e6b1d1377e8', '/mobilereport/1/1/21ad7f10-3296-46d6-82dd-7e6b1d1377e8', '', '0', '[6,7,8,9,10,11,12,13,14,15,16]', '2018-10-18 19:27:42', null, '1', '2018-10-18 19:27:48', '1');
INSERT INTO `patrolreporthistory` VALUES ('3', '79f63bca-94af-47d2-8ab8-a5b3c07d6241', '/mobilereport/1/1/79f63bca-94af-47d2-8ab8-a5b3c07d6241', '', '0', '[3]', '2018-10-19 09:41:44', null, '1', '2018-10-19 09:42:00', '0');
INSERT INTO `patrolreporthistory` VALUES ('4', 'e380745f-4aba-44ea-bfe3-1a23a72303f9', '/mobilereport/1/1/e380745f-4aba-44ea-bfe3-1a23a72303f9', '', '0', '[4]', '2018-10-19 09:41:50', null, '1', '2018-10-19 09:42:00', '1');
INSERT INTO `patrolreporthistory` VALUES ('5', '7564b019-ed3c-4883-aac4-82908a48d1fc', '/mobilereport/1/1/7564b019-ed3c-4883-aac4-82908a48d1fc', '', '0', '[5]', '2018-10-19 09:41:54', null, '1', '2018-10-19 09:42:00', '0');
INSERT INTO `patrolreporthistory` VALUES ('6', 'c38d2ee7-cf60-4dfb-8388-fe9532d1313a', '/mobilereport/1/1/c38d2ee7-cf60-4dfb-8388-fe9532d1313a', '', '0', '[17,18,19,20,21,22]', '2018-10-19 18:34:06', null, '1', '2018-10-19 18:34:13', '1');
INSERT INTO `patrolreporthistory` VALUES ('7', 'f9d12169-d58e-4892-b4d4-f317fde0e2d0', '/mobilereport/1/1/f9d12169-d58e-4892-b4d4-f317fde0e2d0', '', '0', '[24,29,25,26,27,28]', '2018-10-22 18:16:11', null, '1', '2018-10-22 18:16:15', '1');
INSERT INTO `patrolreporthistory` VALUES ('8', 'ca2aebb5-d7eb-4de3-a419-9117edf50756', '/mobilereport/1/1/ca2aebb5-d7eb-4de3-a419-9117edf50756', '', '0', '[36,37,30,31,32,33,34,35]', '2018-10-23 18:16:41', null, '1', '2018-10-23 18:16:45', '1');
INSERT INTO `patrolreporthistory` VALUES ('9', '49f68e92-ea63-4258-9486-8aeb537ea703', '/mobilereport/1/1/49f68e92-ea63-4258-9486-8aeb537ea703', '', '0', '[38,41,39,43,44,40,42]', '2018-10-24 17:41:46', null, '1', '2018-10-24 17:41:50', '1');
INSERT INTO `patrolreporthistory` VALUES ('10', '57bb0dc6-56aa-4078-9672-4d385d15aea2', '/mobilereport/1/1/57bb0dc6-56aa-4078-9672-4d385d15aea2', '', '0', '[52,53,55,56,57,59,45,46,47,48,49,50,51,54,58,60]', '2018-10-25 17:54:22', null, '1', '2018-10-25 17:54:27', '1');
INSERT INTO `patrolreporthistory` VALUES ('11', '6c3ca4d9-b328-46ba-a7bc-aec282e3cca3', '/mobilereport/1/1/6c3ca4d9-b328-46ba-a7bc-aec282e3cca3', '', '0', '[61,62,63,64,65,66,67]', '2018-10-26 17:53:45', null, '1', '2018-10-26 17:53:49', '1');
INSERT INTO `patrolreporthistory` VALUES ('12', '28a0c52d-362e-4d41-8392-68c7c6b890a6', '/mobilereport/1/1/28a0c52d-362e-4d41-8392-68c7c6b890a6', '', '0', '[68,70,72,73]', '2018-10-29 17:59:10', null, '1', '2018-10-29 17:59:16', '1');
INSERT INTO `patrolreporthistory` VALUES ('13', '531553a6-0b18-4471-9e04-dc29946df379', '/mobilereport/1/1/531553a6-0b18-4471-9e04-dc29946df379', '', '0', '[74,77]', '2018-10-30 19:16:00', null, '1', '2018-10-30 19:16:03', '1');
INSERT INTO `patrolreporthistory` VALUES ('14', '5b41f36f-679a-471a-b80b-101f32b2a1ff', '/mobilereport/1/1/5b41f36f-679a-471a-b80b-101f32b2a1ff', '', '0', '[78,79,80,81,82]', '2018-10-31 18:39:11', null, '1', '2018-10-31 18:39:14', '1');
INSERT INTO `patrolreporthistory` VALUES ('15', '9f7293ef-8cb1-4ab7-937d-df019ecd17af', '/mobilereport/1/1/9f7293ef-8cb1-4ab7-937d-df019ecd17af', '', '0', '[85,86,87,88,89,90,91,93]', '2018-11-01 17:41:09', null, '1', '2018-11-01 17:41:13', '1');
INSERT INTO `patrolreporthistory` VALUES ('16', 'a9f0dd12-b7eb-4b2a-8d2b-dffeba75a01c', '/mobilereport/1/1/a9f0dd12-b7eb-4b2a-8d2b-dffeba75a01c', '', '0', '[94,95,96,97,98,100]', '2018-11-02 13:18:26', null, '1', '2018-11-02 13:18:30', '1');
INSERT INTO `patrolreporthistory` VALUES ('17', 'a66c6bba-8d97-41be-a551-a90dc4fd2eb2', '/mobilereport/1/1/a66c6bba-8d97-41be-a551-a90dc4fd2eb2', '', '0', '[101,102,103,104,106]', '2018-11-05 18:22:33', null, '1', '2018-11-05 18:22:37', '1');
INSERT INTO `patrolreporthistory` VALUES ('18', '420d4d71-9f6d-421b-93b0-67b47fef0a39', '/mobilereport/1/1/420d4d71-9f6d-421b-93b0-67b47fef0a39', '', '0', '[107,108,109,110,111,112,113,114,115]', '2018-11-06 21:36:22', null, '1', '2018-11-06 21:36:26', '1');
INSERT INTO `patrolreporthistory` VALUES ('19', '3ba58e19-8113-43a0-9ada-08870d1f5a2e', '/mobilereport/1/1/3ba58e19-8113-43a0-9ada-08870d1f5a2e', '', '0', '[116,117,118,119,120,121,122,123,124]', '2018-11-08 08:52:27', null, '1', '2018-11-08 08:52:33', '1');
INSERT INTO `patrolreporthistory` VALUES ('20', '404d8d22-aa3c-431b-80d6-2bbc70903364', '/mobilereport/1/1/404d8d22-aa3c-431b-80d6-2bbc70903364', '', '0', '[126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148]', '2018-11-13 19:16:25', null, '1', '2018-11-13 19:16:33', '1');
INSERT INTO `patrolreporthistory` VALUES ('21', '4c1702c8-0afe-4d39-ba6f-a99892f34b9f', '/mobilereport/1/1/4c1702c8-0afe-4d39-ba6f-a99892f34b9f', '', '0', '[149,150,151,152,153,154,155,156]', '2018-11-15 10:08:37', null, '1', '2018-11-15 10:08:43', '1');
INSERT INTO `patrolreporthistory` VALUES ('22', '78361150-ab63-43c9-8bb2-187275a20e8a', '/mobilereport/1/1/78361150-ab63-43c9-8bb2-187275a20e8a', '', '0', '[158,159,160,161,162,163]', '2018-11-15 15:14:21', null, '1', '2018-11-15 15:14:27', '1');
INSERT INTO `patrolreporthistory` VALUES ('23', 'e85f83c4-f75f-4026-9ab6-57f2e8389879', '/mobilereport/1/1/e85f83c4-f75f-4026-9ab6-57f2e8389879', '', '0', '[165,166,167,168,169,170,171,172,173,174,175,176,177,178]', '2018-11-16 16:32:01', null, '1', '2018-11-16 16:32:07', '1');
INSERT INTO `patrolreporthistory` VALUES ('24', '66f6e1e2-68ce-45f5-8769-3eb1660ee39a', '/mobilereport/1/1/66f6e1e2-68ce-45f5-8769-3eb1660ee39a', '', '0', '[183,184,186]', '2018-11-19 17:17:51', null, '1', '2018-11-19 17:17:56', '1');
INSERT INTO `patrolreporthistory` VALUES ('25', 'e53dc286-dc0f-4d62-900f-1ebaf6d66c43', '/mobilereport/1/1/e53dc286-dc0f-4d62-900f-1ebaf6d66c43', '', '0', '[187,188,189,190]', '2018-11-20 17:37:57', null, '1', '2018-11-20 17:38:02', '1');
INSERT INTO `patrolreporthistory` VALUES ('26', '29c45257-34bd-4cef-8c49-25d4652067e5', '/mobilereport/1/1/29c45257-34bd-4cef-8c49-25d4652067e5', '', '0', '[191,192,193,194,195,196]', '2018-11-21 17:18:40', null, '1', '2018-11-21 17:18:46', '1');
INSERT INTO `patrolreporthistory` VALUES ('27', '6fd6c786-b889-4537-a53e-e42e735464bd', '/mobilereport/1/1/6fd6c786-b889-4537-a53e-e42e735464bd', '', '0', '[197,198,199,200,201]', '2018-11-22 19:22:22', null, '1', '2018-11-22 19:22:26', '1');
INSERT INTO `patrolreporthistory` VALUES ('28', '9171c00b-db0e-463d-9de5-cc7fc5427354', '/mobilereport/1/1/9171c00b-db0e-463d-9de5-cc7fc5427354', '', '0', '[202,203,204,205,206,207,208,212,214]', '2018-11-23 13:36:27', null, '1', '2018-11-23 13:36:33', '1');
INSERT INTO `patrolreporthistory` VALUES ('29', '1b265762-dc09-43f0-873f-8d8e6935c18d', '/mobilereport/1/1/1b265762-dc09-43f0-873f-8d8e6935c18d', '', '0', '[216,217,218,219,220,221]', '2018-11-28 17:37:18', null, '1', '2018-11-28 17:37:23', '1');
INSERT INTO `patrolreporthistory` VALUES ('30', '2fc4d22d-f783-412b-a693-45935f0c0f6b', '/mobilereport/1/1/2fc4d22d-f783-412b-a693-45935f0c0f6b', '', '0', '[222,223,224]', '2018-12-03 19:02:55', null, '1', '2018-12-03 19:02:59', '1');
INSERT INTO `patrolreporthistory` VALUES ('31', '1843c786-9248-408f-9437-5179ff9aa1c7', '/mobilereport/1/1/1843c786-9248-408f-9437-5179ff9aa1c7', '', '0', '[227,228,229]', '2018-12-04 18:47:40', null, '1', '2018-12-04 18:47:44', '1');
INSERT INTO `patrolreporthistory` VALUES ('32', '55914eed-8a06-4dc7-ab58-389fe49139bf', '/mobilereport/1/1/55914eed-8a06-4dc7-ab58-389fe49139bf', '', '0', '[231,232,234]', '2018-12-05 17:15:37', null, '1', '2018-12-05 17:15:42', '1');
INSERT INTO `patrolreporthistory` VALUES ('33', 'd34ce007-204a-4dfa-8834-ec11fcef8b24', '/mobilereport/1/1/d34ce007-204a-4dfa-8834-ec11fcef8b24', '', '0', '[236,237,238]', '2018-12-06 16:51:23', null, '1', '2018-12-06 16:51:28', '1');
INSERT INTO `patrolreporthistory` VALUES ('34', '13cd0250-9a86-4c04-b1cb-b92d088ec842', '/mobilereport/1/1/13cd0250-9a86-4c04-b1cb-b92d088ec842', '', '0', '[241,242,244]', '2018-12-07 17:06:40', null, '1', '2018-12-07 17:06:45', '1');
INSERT INTO `patrolreporthistory` VALUES ('35', '4690bf4b-415f-4bf7-9b03-1fb4b564c9bc', '/mobilereport/1/1/4690bf4b-415f-4bf7-9b03-1fb4b564c9bc', '', '0', '[247,248,249]', '2018-12-10 16:50:52', null, '1', '2018-12-10 16:50:56', '1');
INSERT INTO `patrolreporthistory` VALUES ('36', '4e997401-1215-48d3-9f17-eba9f4d9f988', '/mobilereport/1/1/4e997401-1215-48d3-9f17-eba9f4d9f988', '', '0', '[252,253,254,255]', '2018-12-11 16:46:20', null, '1', '2018-12-11 16:46:25', '1');
INSERT INTO `patrolreporthistory` VALUES ('37', '357d9f58-f433-40a7-8d47-b494a35eb00a', '/mobilereport/1/1/357d9f58-f433-40a7-8d47-b494a35eb00a', '', '0', '[256,257,259]', '2018-12-12 13:40:24', null, '1', '2018-12-12 13:40:29', '1');
INSERT INTO `patrolreporthistory` VALUES ('39', '2f0801af-fd2f-40ad-b3da-2a78d050e303', '/mobilereport/1/1/2f0801af-fd2f-40ad-b3da-2a78d050e303', '', '0', '[260,261,262,264]', '2018-12-13 17:29:17', null, '1', '2018-12-13 17:29:22', '1');
INSERT INTO `patrolreporthistory` VALUES ('40', '4f09f79a-4911-4157-8fd8-fd9d20d78f43', '/mobilereport/1/1/4f09f79a-4911-4157-8fd8-fd9d20d78f43', '', '0', '[265,266,267,268]', '2018-12-14 17:17:08', null, '1', '2018-12-14 17:17:13', '1');
INSERT INTO `patrolreporthistory` VALUES ('41', '6f7e4ec5-bbb3-411d-a26c-e30e0812c168', '/mobilereport/1/1/6f7e4ec5-bbb3-411d-a26c-e30e0812c168', '', '0', '[270,271,272,273]', '2018-12-17 16:41:35', null, '1', '2018-12-17 16:41:40', '1');
INSERT INTO `patrolreporthistory` VALUES ('42', '97488b17-77d1-4599-b770-88c4ee192641', '/mobilereport/1/1/97488b17-77d1-4599-b770-88c4ee192641', '', '0', '[274,275,276,278]', '2018-12-18 17:12:34', null, '1', '2018-12-18 17:12:39', '1');
INSERT INTO `patrolreporthistory` VALUES ('43', 'b9f8d22e-f7d8-4697-a13a-7b730a631889', '/mobilereport/1/1/b9f8d22e-f7d8-4697-a13a-7b730a631889', '', '0', '[280,281,282,283]', '2018-12-19 17:14:05', null, '1', '2018-12-19 17:14:10', '1');
INSERT INTO `patrolreporthistory` VALUES ('44', 'a6d47cf9-f3bc-4c76-a209-6df84b861ead', '/mobilereport/1/1/a6d47cf9-f3bc-4c76-a209-6df84b861ead', '', '0', '[285,286,287]', '2018-12-20 17:00:50', null, '1', '2018-12-20 17:00:55', '1');
INSERT INTO `patrolreporthistory` VALUES ('45', '5561f95b-ed08-47fc-8e53-706bd0018d14', '/mobilereport/1/1/5561f95b-ed08-47fc-8e53-706bd0018d14', '', '0', '[290,291,292,293]', '2018-12-21 16:36:39', null, '1', '2018-12-21 16:36:44', '1');
INSERT INTO `patrolreporthistory` VALUES ('46', '27df990d-0dcd-4e9e-aab9-b792d5764f3b', '/mobilereport/1/1/27df990d-0dcd-4e9e-aab9-b792d5764f3b', '', '0', '[295,296,297,298,299,300,301]', '2018-12-24 17:53:48', null, '1', '2018-12-24 17:53:53', '1');
INSERT INTO `patrolreporthistory` VALUES ('47', '5a149828-b614-4ad3-b69a-32f1b0a82691', '/mobilereport/1/1/5a149828-b614-4ad3-b69a-32f1b0a82691', '', '0', '[304,306,307,308,310]', '2018-12-25 15:48:26', null, '1', '2018-12-25 15:48:31', '0');
INSERT INTO `patrolreporthistory` VALUES ('48', 'cbaabda9-2617-4542-b1ce-3342fb5c1ef9', '/mobilereport/1/1/cbaabda9-2617-4542-b1ce-3342fb5c1ef9', '', '0', '[311,312,313,315,316]', '2018-12-26 16:29:04', null, '1', '2018-12-26 16:29:09', '1');
INSERT INTO `patrolreporthistory` VALUES ('49', '17aba7f7-1d42-48f3-a136-934d442ef13c', '/mobilereport/1/1/17aba7f7-1d42-48f3-a136-934d442ef13c', '', '0', '[317,322,318,320]', '2018-12-27 16:36:31', null, '1', '2018-12-27 16:36:36', '1');

-- ----------------------------
-- Table structure for playgroup
-- ----------------------------
DROP TABLE IF EXISTS `playgroup`;
CREATE TABLE `playgroup` (
  `groupid` int(11) NOT NULL AUTO_INCREMENT COMMENT '轮播信息id',
  `userid` int(11) NOT NULL,
  `outletid` int(11) NOT NULL COMMENT '工地id',
  `grouptype` tinyint(4) NOT NULL DEFAULT '0' COMMENT '执行方式：1.单次执行，0.循环执行',
  `groupinfo` varchar(1024) NOT NULL COMMENT '轮播信息，以；分割，每组内用：区分。如，“1：20；3：25”，表示CameraId为1的相机，播放10秒后，CameraId为3的相机播放25秒',
  PRIMARY KEY (`groupid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of playgroup
-- ----------------------------
INSERT INTO `playgroup` VALUES ('1', '8', '1', '0', '2:30;5:30;8:30');
INSERT INTO `playgroup` VALUES ('2', '11', '2', '0', '9:30;10:30;11:30;12:30;13:30;16:30');

-- ----------------------------
-- Table structure for realtimereporthistory
-- ----------------------------
DROP TABLE IF EXISTS `realtimereporthistory`;
CREATE TABLE `realtimereporthistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reportid` varchar(255) NOT NULL,
  `reporturl` varchar(255) NOT NULL,
  `brandid` int(11) NOT NULL,
  `adminid` int(11) NOT NULL,
  `adminname` varchar(255) NOT NULL DEFAULT '',
  `outletid` int(11) NOT NULL,
  `outletname` varchar(255) NOT NULL DEFAULT '',
  `deviceno` int(11) NOT NULL,
  `devicename` varchar(255) NOT NULL DEFAULT '',
  `camerano` int(11) NOT NULL,
  `cameraname` varchar(255) NOT NULL DEFAULT '',
  `itemid` int(11) NOT NULL,
  `itemname` varchar(255) NOT NULL DEFAULT '',
  `majorpic` varchar(255) DEFAULT NULL,
  `minorpic` varchar(255) DEFAULT NULL,
  `videoinfo` varchar(255) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `COMMENT` varchar(255) NOT NULL DEFAULT '',
  `actiontime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_realtime_report_history` (`reportid`) USING HASH,
  KEY `realtime_report_history_outletid` (`outletid`) USING BTREE,
  KEY `realtime_report_history_createtime` (`createtime`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of realtimereporthistory
-- ----------------------------
INSERT INTO `realtimereporthistory` VALUES ('1', '5522be72-8df5-41fd-8eaa-798b928e2c4d', '/realtime/mobilereportrecord/1/5522be72-8df5-41fd-8eaa-798b928e2c4d', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '7', 'Camera7_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/7/20181017/2018-10-16_10_39_23_1_1_1_7.jpg', '', '', '2018-10-17 13:13:19', '', '2018-10-16 10:39:23');
INSERT INTO `realtimereporthistory` VALUES ('2', 'f4f23dba-90a5-4e39-a3d1-7e141e74af36', '/realtime/mobilereportrecord/1/f4f23dba-90a5-4e39-a3d1-7e141e74af36', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '7', 'Camera7_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/7/20181017/2018-10-16_10_39_08_1_1_1_7.jpg', '', '', '2018-10-17 15:03:29', '', '2018-10-16 10:39:08');
INSERT INTO `realtimereporthistory` VALUES ('3', 'e90ad1c3-13ed-460c-a4cf-4dcee98b40d0', '/realtime/mobilereportrecord/1/e90ad1c3-13ed-460c-a4cf-4dcee98b40d0', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '3', 'Camera3_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/3/20181020/2018-10-20_17_18_06_1_1_1_3.jpg', '', '', '2018-10-20 18:16:47', '', '2018-10-20 17:18:06');
INSERT INTO `realtimereporthistory` VALUES ('4', '84599966-defd-4917-9eaf-0e26cfeb7fdf', '/realtime/mobilereportrecord/1/84599966-defd-4917-9eaf-0e26cfeb7fdf', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '2', 'Camera2_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/2/20181020/2018-10-20_17_18_20_1_1_1_2.jpg', '', '', '2018-10-20 18:16:48', '', '2018-10-20 17:18:20');
INSERT INTO `realtimereporthistory` VALUES ('5', '2794ae82-03c1-42ec-bde6-03bdbae0f9dc', '/realtime/mobilereportrecord/1/2794ae82-03c1-42ec-bde6-03bdbae0f9dc', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '4', 'Camera4_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/4/20181021/2018-10-21_11_54_01_1_1_1_4.jpg', '', '', '2018-10-21 12:25:57', '', '2018-10-21 11:54:01');
INSERT INTO `realtimereporthistory` VALUES ('6', 'bb103ae3-2cc5-4608-a889-113a802f70ee', '/realtime/mobilereportrecord/1/bb103ae3-2cc5-4608-a889-113a802f70ee', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '3', 'Camera3_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/3/20181021/2018-10-21_11_55_16_1_1_1_3.jpg', '', '', '2018-10-21 12:26:00', '', '2018-10-21 11:55:16');
INSERT INTO `realtimereporthistory` VALUES ('7', '2a6e4791-1221-436b-a09b-97a5d88fbb06', '/realtime/mobilereportrecord/1/2a6e4791-1221-436b-a09b-97a5d88fbb06', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '2', 'Camera2_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/2/20181021/2018-10-21_14_12_27_1_1_1_2.jpg', '', '', '2018-10-21 14:27:07', '', '2018-10-21 14:12:27');
INSERT INTO `realtimereporthistory` VALUES ('8', '02d1df74-d427-44f8-bf16-dcefc8254e46', '/realtime/mobilereportrecord/1/02d1df74-d427-44f8-bf16-dcefc8254e46', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '2', 'Camera2_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/2/20181021/2018-10-21_14_12_43_1_1_1_2.jpg', '', '', '2018-10-21 14:27:10', '', '2018-10-21 14:12:43');
INSERT INTO `realtimereporthistory` VALUES ('9', 'a4615074-6b3c-4a7b-bbe0-8b05f294dcbd', '/realtime/mobilereportrecord/1/a4615074-6b3c-4a7b-bbe0-8b05f294dcbd', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '6', 'Camera6_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/6/20181021/2018-10-21_14_22_02_1_1_1_6.jpg', '', '', '2018-10-21 14:27:19', '', '2018-10-21 14:22:02');
INSERT INTO `realtimereporthistory` VALUES ('10', '16d73a45-c5a4-4a9c-a0a0-b79865775a86', '/realtime/mobilereportrecord/1/16d73a45-c5a4-4a9c-a0a0-b79865775a86', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '3', 'Camera3_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/3/20181021/2018-10-21_15_01_02_1_1_1_3.jpg', '', '', '2018-10-21 15:12:53', '', '2018-10-21 15:01:02');
INSERT INTO `realtimereporthistory` VALUES ('11', '992c62d1-d33e-4f56-a129-096873595f81', '/realtime/mobilereportrecord/1/992c62d1-d33e-4f56-a129-096873595f81', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '2', 'Camera2_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/2/20181021/2018-10-21_14_58_28_1_1_1_2.jpg', '', '', '2018-10-21 15:12:58', '', '2018-10-21 14:58:28');
INSERT INTO `realtimereporthistory` VALUES ('12', '137df24d-05f0-4018-812f-0ff88df586a5', '/realtime/mobilereportrecord/1/137df24d-05f0-4018-812f-0ff88df586a5', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '2', 'Camera2_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/2/20181021/2018-10-21_15_06_05_1_1_1_2.jpg', '', '', '2018-10-21 15:13:04', '', '2018-10-21 15:06:05');
INSERT INTO `realtimereporthistory` VALUES ('13', 'b88d36d4-937e-4855-8a99-6fcf86b08173', '/realtime/mobilereportrecord/1/b88d36d4-937e-4855-8a99-6fcf86b08173', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '2', 'Camera2_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/2/20181021/2018-10-21_14_47_48_1_1_1_2.jpg', '', '', '2018-10-21 15:13:07', '', '2018-10-21 14:47:48');
INSERT INTO `realtimereporthistory` VALUES ('14', 'e8d5423e-d259-42b6-b147-f9e8e58b2de6', '/realtime/mobilereportrecord/1/e8d5423e-d259-42b6-b147-f9e8e58b2de6', '1', '7', 'shishi', '1', '杭临城铁隧道', '1', '杭临城铁隧道', '7', 'Camera7_杭临城铁隧道', '12000', '安全帽违规检测', '/getrecordimg/1/1/7/20181021/2018-10-21_14_46_43_1_1_1_7.jpg', '', '', '2018-10-21 15:13:11', '', '2018-10-21 14:46:43');

-- ----------------------------
-- Table structure for reportadmin
-- ----------------------------
DROP TABLE IF EXISTS `reportadmin`;
CREATE TABLE `reportadmin` (
  `UserId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reportadmin
-- ----------------------------

-- ----------------------------
-- Table structure for reportcommenthistory
-- ----------------------------
DROP TABLE IF EXISTS `reportcommenthistory`;
CREATE TABLE `reportcommenthistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL COMMENT '评论提交时间',
  `reporttype` int(11) NOT NULL COMMENT '报告类型',
  `reportid` varchar(255) NOT NULL COMMENT '报告id',
  `recordid` varchar(255) NOT NULL DEFAULT '' COMMENT '记录id，运营报告特有',
  `comments` binary(255) NOT NULL DEFAULT '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT '评论内容',
  `sender` varchar(255) DEFAULT NULL COMMENT '发送者姓名',
  `receiver` varchar(255) NOT NULL DEFAULT '' COMMENT '接受者姓名，为空表示对报告进行评论',
  `isdelete` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1 为删除，默认为0',
  PRIMARY KEY (`id`),
  KEY `report_comment_report_normal` (`reporttype`,`reportid`(36)) USING BTREE,
  KEY `report_comment_record_normal` (`recordid`(36)) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reportcommenthistory
-- ----------------------------

-- ----------------------------
-- Table structure for reportcomments
-- ----------------------------
DROP TABLE IF EXISTS `reportcomments`;
CREATE TABLE `reportcomments` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Summary` varchar(32) DEFAULT NULL,
  `Comments` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reportcomments
-- ----------------------------

-- ----------------------------
-- Table structure for reportextconfig
-- ----------------------------
DROP TABLE IF EXISTS `reportextconfig`;
CREATE TABLE `reportextconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cfgname` varchar(64) NOT NULL DEFAULT '',
  `cfgvalue` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `cfgname` (`cfgname`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reportextconfig
-- ----------------------------
INSERT INTO `reportextconfig` VALUES ('1', 'enableextensionflag', '0');

-- ----------------------------
-- Table structure for reportextflag
-- ----------------------------
DROP TABLE IF EXISTS `reportextflag`;
CREATE TABLE `reportextflag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flagname` varchar(64) NOT NULL,
  `flag1` varchar(128) NOT NULL DEFAULT '',
  `flag2` varchar(128) NOT NULL DEFAULT '',
  `flag3` varchar(128) NOT NULL DEFAULT '',
  `flag4` varchar(128) NOT NULL DEFAULT '',
  `flag5` varchar(128) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `flagname` (`flagname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reportextflag
-- ----------------------------

-- ----------------------------
-- Table structure for reportextflagrela
-- ----------------------------
DROP TABLE IF EXISTS `reportextflagrela`;
CREATE TABLE `reportextflagrela` (
  `deviceid` int(11) NOT NULL,
  `flagid` int(11) NOT NULL,
  PRIMARY KEY (`deviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reportextflagrela
-- ----------------------------

-- ----------------------------
-- Table structure for reporthistory
-- ----------------------------
DROP TABLE IF EXISTS `reporthistory`;
CREATE TABLE `reporthistory` (
  `JobId` int(11) NOT NULL,
  `OperatorId` int(11) NOT NULL,
  `AdminId` int(11) NOT NULL,
  `CameraNo` int(11) NOT NULL,
  `ClientId` varchar(256) NOT NULL,
  `PosId` varchar(256) NOT NULL,
  `Keyword` varchar(256) NOT NULL,
  `VideoStartTime` datetime NOT NULL,
  `VideoEndTime` datetime NOT NULL,
  `ReviewTime` int(11) NOT NULL,
  `PosText` varchar(10250) DEFAULT NULL,
  `OperatorStatus` int(11) NOT NULL,
  `OperatorFlag` int(11) NOT NULL,
  `AdminStatus` int(11) NOT NULL,
  `AdminFlag` int(11) NOT NULL,
  `Comments` varchar(256) DEFAULT NULL,
  `PosType` int(11) DEFAULT '0',
  `extflag` varchar(256) NOT NULL DEFAULT '',
  KEY `in_clientid` (`ClientId`(255)),
  KEY `in_operatorid` (`OperatorId`),
  KEY `in_posid` (`PosId`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reporthistory
-- ----------------------------

-- ----------------------------
-- Table structure for reportoperator
-- ----------------------------
DROP TABLE IF EXISTS `reportoperator`;
CREATE TABLE `reportoperator` (
  `UserId` int(11) NOT NULL,
  `AdminId` int(11) NOT NULL,
  `LastJobId` int(11) NOT NULL,
  `LastResetTime` datetime NOT NULL,
  `SearchInterval` int(11) NOT NULL DEFAULT '300',
  `PlayAdvanceTime` int(11) NOT NULL DEFAULT '60',
  `CheckedDays` int(11) DEFAULT '1',
  PRIMARY KEY (`UserId`),
  KEY `in_userid` (`UserId`),
  KEY `in_adminid` (`AdminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reportoperator
-- ----------------------------

-- ----------------------------
-- Table structure for scene
-- ----------------------------
DROP TABLE IF EXISTS `scene`;
CREATE TABLE `scene` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brandid` int(11) NOT NULL,
  `scenename` varchar(64) NOT NULL,
  `modelname` varchar(64) NOT NULL,
  `typeid` tinyint(64) NOT NULL,
  `setupfilename` varchar(64) DEFAULT NULL,
  `sceneno` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scene
-- ----------------------------

-- ----------------------------
-- Table structure for scheduleconfig
-- ----------------------------
DROP TABLE IF EXISTS `scheduleconfig`;
CREATE TABLE `scheduleconfig` (
  `Duration` int(11) NOT NULL DEFAULT '10',
  `CaptureNum` int(11) NOT NULL DEFAULT '3',
  `IntervalSecond` int(11) NOT NULL DEFAULT '10'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scheduleconfig
-- ----------------------------

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

-- ----------------------------
-- Table structure for standardbrand
-- ----------------------------
DROP TABLE IF EXISTS `standardbrand`;
CREATE TABLE `standardbrand` (
  `brandid` int(11) NOT NULL,
  `standardid` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  KEY `brandid` (`brandid`,`standardid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of standardbrand
-- ----------------------------
INSERT INTO `standardbrand` VALUES ('1', '1', '0');

-- ----------------------------
-- Table structure for standardoutlet
-- ----------------------------
DROP TABLE IF EXISTS `standardoutlet`;
CREATE TABLE `standardoutlet` (
  `standardid` int(11) NOT NULL,
  `outletid` int(11) NOT NULL,
  KEY `standardid` (`standardid`,`outletid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of standardoutlet
-- ----------------------------
INSERT INTO `standardoutlet` VALUES ('1', '1');
INSERT INTO `standardoutlet` VALUES ('1', '2');

-- ----------------------------
-- Table structure for systemsengineer
-- ----------------------------
DROP TABLE IF EXISTS `systemsengineer`;
CREATE TABLE `systemsengineer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phonenum1` varchar(255) DEFAULT NULL,
  `phonenum2` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `enginner_unique` (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemsengineer
-- ----------------------------

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

-- ----------------------------
-- Table structure for useralarmcamera
-- ----------------------------
DROP TABLE IF EXISTS `useralarmcamera`;
CREATE TABLE `useralarmcamera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `DeviceNo_Alarm` int(11) NOT NULL,
  `Input_Alarm` int(10) unsigned DEFAULT '0',
  `CameraNo_Trigger` int(10) unsigned DEFAULT '0',
  `DeviceNo_Trigger` int(10) unsigned DEFAULT '0',
  `OutputDeviceNo_Trigger` int(10) unsigned DEFAULT '0',
  `Output_Trigger` int(10) unsigned DEFAULT '0',
  `Description` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of useralarmcamera
-- ----------------------------

-- ----------------------------
-- Table structure for useralarmdevice
-- ----------------------------
DROP TABLE IF EXISTS `useralarmdevice`;
CREATE TABLE `useralarmdevice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `DeviceNo` int(11) NOT NULL,
  PRIMARY KEY (`UserId`,`DeviceNo`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of useralarmdevice
-- ----------------------------

-- ----------------------------
-- Table structure for usercamera
-- ----------------------------
DROP TABLE IF EXISTS `usercamera`;
CREATE TABLE `usercamera` (
  `UserId` int(11) NOT NULL,
  `CameraNo` int(11) NOT NULL,
  `DeviceNo` int(11) NOT NULL,
  `DeviceAuthority` varchar(256) NOT NULL DEFAULT '0',
  `CameraAuthority` varchar(256) NOT NULL DEFAULT '0,0,0,0,0,0',
  PRIMARY KEY (`UserId`,`CameraNo`),
  KEY `usercameranormal` (`UserId`,`DeviceNo`,`CameraNo`) USING BTREE,
  KEY `usercameraCameraNo` (`CameraNo`,`DeviceNo`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of usercamera
-- ----------------------------
INSERT INTO `usercamera` VALUES ('2', '1', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('2', '2', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('2', '3', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('2', '4', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('2', '5', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('2', '6', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('2', '7', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('2', '8', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '1', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '2', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '3', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '4', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '5', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '6', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '7', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '8', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '9', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '10', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '11', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '12', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '13', '3', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('3', '16', '3', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('5', '1', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('5', '2', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('5', '3', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('5', '4', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('5', '5', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('5', '6', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('5', '7', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('5', '8', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('7', '1', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('7', '2', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('7', '3', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('7', '4', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('7', '5', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('7', '6', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('7', '7', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('7', '8', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('8', '1', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('8', '2', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('8', '3', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('8', '4', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('8', '5', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('8', '6', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('8', '7', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('8', '8', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('9', '1', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('9', '2', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('9', '3', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('9', '4', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('9', '5', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('9', '6', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('9', '7', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('9', '8', '1', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('10', '9', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('10', '10', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('10', '11', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('10', '12', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('10', '13', '3', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('10', '16', '3', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('11', '9', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('11', '10', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('11', '11', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('11', '12', '2', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('11', '13', '3', '1', '1,1,1,1,1,1');
INSERT INTO `usercamera` VALUES ('11', '16', '3', '1', '1,1,1,1,1,1');

-- ----------------------------
-- Table structure for useremap
-- ----------------------------
DROP TABLE IF EXISTS `useremap`;
CREATE TABLE `useremap` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `MapId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `RunStartup` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`MapId`,`UserId`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of useremap
-- ----------------------------

-- ----------------------------
-- Table structure for useroutlet
-- ----------------------------
DROP TABLE IF EXISTS `useroutlet`;
CREATE TABLE `useroutlet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `outletid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `useroutlet_unique` (`outletid`,`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of useroutlet
-- ----------------------------
INSERT INTO `useroutlet` VALUES ('4', '1', '2');
INSERT INTO `useroutlet` VALUES ('3', '1', '3');
INSERT INTO `useroutlet` VALUES ('6', '1', '5');
INSERT INTO `useroutlet` VALUES ('7', '1', '7');
INSERT INTO `useroutlet` VALUES ('8', '1', '8');
INSERT INTO `useroutlet` VALUES ('9', '1', '9');
INSERT INTO `useroutlet` VALUES ('12', '2', '3');
INSERT INTO `useroutlet` VALUES ('10', '2', '10');
INSERT INTO `useroutlet` VALUES ('11', '2', '11');

-- ----------------------------
-- Table structure for userright
-- ----------------------------
DROP TABLE IF EXISTS `userright`;
CREATE TABLE `userright` (
  `UserId` int(11) NOT NULL,
  `Ccode` int(10) unsigned NOT NULL DEFAULT '0',
  `Fcode` int(10) unsigned NOT NULL DEFAULT '0',
  `Param1` int(10) unsigned DEFAULT '0',
  `Param2` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`UserId`,`Ccode`,`Fcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of userright
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) DEFAULT NULL,
  `Passwd` varchar(255) DEFAULT NULL,
  `Authlevel` int(10) unsigned DEFAULT '0',
  `Default_DeviceAuthority` varchar(255) NOT NULL DEFAULT '0',
  `Default_CameraAuthority` varchar(255) NOT NULL DEFAULT '0,0,0,0,0,0',
  `safecode` varchar(255) DEFAULT '',
  `bind` int(11) NOT NULL DEFAULT '0',
  `online` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `Username_UNIQUE` (`Username`),
  KEY `usersnormal` (`Authlevel`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'hadmin', 'h1234567', '1', '1', '1,1,1,1,1,1', '', '0', '1');
INSERT INTO `users` VALUES ('2', 'aifilter', 'ab123456', '5', '1', '1,1,1,1,1,1', '', '0', '0');
INSERT INTO `users` VALUES ('3', 'aicapture', 'ab123456', '4', '1', '1,1,1,1,1,1', '', '0', '0');
INSERT INTO `users` VALUES ('5', 'oparthur', 'ab123456', '3', '1', '1,1,1,1,1,1', '', '0', '0');
INSERT INTO `users` VALUES ('6', 'report', 'ab123456', '6', '1', '1,1,1,1,1,1', '', '0', '1');
INSERT INTO `users` VALUES ('7', 'shishi', 'ab123456', '8', '1', '1,1,1,1,1,1', '', '0', '0');
INSERT INTO `users` VALUES ('8', 'webapp', 'ab123456', '15', '1', '1,1,1,1,1,1', '', '0', '1');
INSERT INTO `users` VALUES ('9', 'icsserver', 'ab123456', '14', '1', '1,1,1,1,1,1', '', '0', '1');
INSERT INTO `users` VALUES ('10', 'qhx_ICSServer', 'ab123456', '14', '1', '1,1,1,1,1,1', '', '0', '0');
INSERT INTO `users` VALUES ('11', 'qhx_webapp', 'ab123456', '15', '1', '1,1,1,1,1,1', '', '0', '1');

-- ----------------------------
-- Table structure for uservideogroup
-- ----------------------------
DROP TABLE IF EXISTS `uservideogroup`;
CREATE TABLE `uservideogroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `GroupId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `RunStartup` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`GroupId`,`UserId`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of uservideogroup
-- ----------------------------

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `MainVersion` int(11) NOT NULL DEFAULT '1',
  `SubVersion` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`MainVersion`,`SubVersion`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of version
-- ----------------------------

-- ----------------------------
-- Table structure for videodevice
-- ----------------------------
DROP TABLE IF EXISTS `videodevice`;
CREATE TABLE `videodevice` (
  `DeviceNo` int(11) NOT NULL AUTO_INCREMENT,
  `DeviceType` int(10) unsigned NOT NULL,
  `ModelName` varchar(256) DEFAULT NULL,
  `DeviceName` varchar(256) DEFAULT NULL,
  `EnableDomain` int(10) unsigned DEFAULT '1',
  `DomainName` varchar(256) DEFAULT NULL,
  `IPAddress` varchar(256) DEFAULT NULL,
  `TCPPort` int(10) unsigned NOT NULL,
  `Username` varchar(256) DEFAULT NULL,
  `Passwd` varchar(256) DEFAULT NULL,
  `ChanNum` int(10) unsigned DEFAULT '0',
  `Timeout` int(10) unsigned DEFAULT '10',
  `DefaultLiveVideo` int(10) unsigned DEFAULT '0',
  `DefaultPlaybackStream` int(10) unsigned DEFAULT '0',
  `IsRecord` int(10) unsigned DEFAULT '1',
  `ContactId` int(10) unsigned DEFAULT '0',
  `WebPort` int(10) unsigned DEFAULT '0',
  `ClientId` varchar(256) DEFAULT '',
  `Authority` varchar(256) NOT NULL DEFAULT '1',
  `TranscodePort` int(10) NOT NULL,
  `suspend` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`DeviceNo`),
  UNIQUE KEY `DeviceNo_UNIQUE` (`DeviceNo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of videodevice
-- ----------------------------
INSERT INTO `videodevice` VALUES ('1', '4', 'Hikvision device', '杭临城铁隧道', '1', '', '124.160.56.50', '8000', 'admin', 'qw123456', '8', '10', '0', '0', '1', '0', '0', '', '1', '0', '0');
INSERT INTO `videodevice` VALUES ('2', '5', 'DAHUA device', '地铁义蓬站1', '1', '', '218.108.97.78', '37779', 'admin', 'q12345678', '4', '10', '0', '0', '1', '0', '0', '', '1', '0', '0');
INSERT INTO `videodevice` VALUES ('3', '4', 'Hikvision device', '地铁义蓬站2', '1', '', '218.108.97.78', '8000', 'admin', 'admin12345', '2', '10', '0', '0', '1', '0', '0', '', '1', '0', '0');

-- ----------------------------
-- Table structure for videogroup
-- ----------------------------
DROP TABLE IF EXISTS `videogroup`;
CREATE TABLE `videogroup` (
  `GroupId` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(45) DEFAULT '',
  `Style` int(10) unsigned DEFAULT '0',
  `StillTime` int(10) unsigned DEFAULT '10',
  `brandid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`GroupId`),
  UNIQUE KEY `GroupId_UNIQUE` (`GroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of videogroup
-- ----------------------------

-- ----------------------------
-- Table structure for videooutlet
-- ----------------------------
DROP TABLE IF EXISTS `videooutlet`;
CREATE TABLE `videooutlet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `outletid` int(11) NOT NULL,
  `deviceno` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `videooutlet_normal` (`id`,`outletid`,`deviceno`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of videooutlet
-- ----------------------------
INSERT INTO `videooutlet` VALUES ('1', '1', '1');
INSERT INTO `videooutlet` VALUES ('2', '2', '2');
INSERT INTO `videooutlet` VALUES ('3', '2', '3');

-- ----------------------------
-- View structure for aitaskview
-- ----------------------------
DROP VIEW IF EXISTS `aitaskview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `aitaskview` AS select `aitask`.`id` AS `id`,`aitask`.`taskname` AS `taskname`,`aitask`.`userid` AS `userid`,`aitask`.`deviceno` AS `deviceno`,`aitask`.`camerano` AS `camerano`,`aitask`.`worktime` AS `worktime`,`aitask`.`taskstatus` AS `taskstatus`,`aitask`.`enable` AS `enable`,`aitask`.`modelid` AS `modelid`,`aitask`.`checktype` AS `checktype`,`aitask`.`maxsavetime` AS `maxsavetime`,`aitask`.`similaritythreshold` AS `similaritythreshold`,`scene`.`modelname` AS `modelname`,`scene`.`setupfilename` AS `setupfilename`,`scene`.`id` AS `sceneid`,`scene`.`sceneno` AS `sceneno`,`scene`.`scenename` AS `scenename` from (`aitask` join `scene` on((`aitask`.`modelid` = `scene`.`id`))) ;

-- ----------------------------
-- View structure for ai_channel_view
-- ----------------------------
DROP VIEW IF EXISTS `ai_channel_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `ai_channel_view` AS select `users`.`UserId` AS `userid`,`users`.`Username` AS `username`,`aitask`.`camerano` AS `camerano`,`aitask`.`modelid` AS `modelid`,`checkconfig`.`ip` AS `ip`,`checkconfig`.`ip2` AS `ip2`,`checkconfig`.`port` AS `PORT`,1 AS `ServerType` from ((`users` join `aitask` on((`users`.`UserId` = `aitask`.`userid`))) join `checkconfig` on((`aitask`.`userid` = `checkconfig`.`userid`))) where (((char_length(`checkconfig`.`ip`) > 0) or (char_length(`checkconfig`.`ip2`) > 0)) and (`users`.`Authlevel` = 5)) ;

-- ----------------------------
-- View structure for brandcustomerview
-- ----------------------------
DROP VIEW IF EXISTS `brandcustomerview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `brandcustomerview` AS select `a`.`customerid` AS `customerid`,`a`.`brandid` AS `brandid`,`a`.`brandname` AS `brandname`,`a`.`brandlogo` AS `brandlogo`,`a`.`description` AS `description`,`a`.`parentid` AS `parentid`,`a`.`schedule_time` AS `schedule_time`,`a`.`layer` AS `layer`,`a`.`wait_time` AS `wait_time`,`a`.`Enabled` AS `Enabled` from `brandcustomerview3` `a` union select `a`.`customerid` AS `customerid`,`b`.`id` AS `brandid`,`b`.`brandname` AS `brandname`,`b`.`brandlogo` AS `brandlogo`,`b`.`description` AS `description`,`b`.`parentid` AS `parentid`,`b`.`schedule_time` AS `schedule_time`,`b`.`layer` AS `layer`,`b`.`wait_time` AS `wait_time`,`b`.`Enabled` AS `Enabled` from (`brandcustomerview3` `a` join `brand` `b`) where (`a`.`parentid` = `b`.`id`) order by `customerid`,`brandid` ;

-- ----------------------------
-- View structure for brandcustomerview1
-- ----------------------------
DROP VIEW IF EXISTS `brandcustomerview1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `brandcustomerview1` AS select `a`.`customerid` AS `customerid`,`c`.`id` AS `brandid`,`c`.`brandname` AS `brandname`,`c`.`brandlogo` AS `brandlogo`,`c`.`description` AS `description`,`c`.`parentid` AS `parentid`,`c`.`schedule_time` AS `schedule_time`,`c`.`layer` AS `layer`,`c`.`wait_time` AS `wait_time`,`c`.`Enabled` AS `Enabled` from (`brandcustomer` `a` join `brand` `c` on((`a`.`brandid` = `c`.`id`))) group by `a`.`customerid`,`c`.`id` ;

-- ----------------------------
-- View structure for brandcustomerview2
-- ----------------------------
DROP VIEW IF EXISTS `brandcustomerview2`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `brandcustomerview2` AS select `a`.`customerid` AS `customerid`,`a`.`brandid` AS `brandid`,`a`.`brandname` AS `brandname`,`a`.`brandlogo` AS `brandlogo`,`a`.`description` AS `description`,`a`.`parentid` AS `parentid`,`a`.`schedule_time` AS `schedule_time`,`a`.`layer` AS `layer`,`a`.`wait_time` AS `wait_time`,`a`.`Enabled` AS `Enabled` from `brandcustomerview1` `a` union select `a`.`customerid` AS `customerid`,`b`.`id` AS `brandid`,`b`.`brandname` AS `brandname`,`b`.`brandlogo` AS `brandlogo`,`b`.`description` AS `description`,`b`.`parentid` AS `parentid`,`b`.`schedule_time` AS `schedule_time`,`b`.`layer` AS `layer`,`b`.`wait_time` AS `wait_time`,`b`.`Enabled` AS `Enabled` from (`brandcustomerview1` `a` join `brand` `b`) where (`a`.`parentid` = `b`.`id`) order by `customerid`,`brandid` ;

-- ----------------------------
-- View structure for brandcustomerview3
-- ----------------------------
DROP VIEW IF EXISTS `brandcustomerview3`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `brandcustomerview3` AS select `a`.`customerid` AS `customerid`,`a`.`brandid` AS `brandid`,`a`.`brandname` AS `brandname`,`a`.`brandlogo` AS `brandlogo`,`a`.`description` AS `description`,`a`.`parentid` AS `parentid`,`a`.`schedule_time` AS `schedule_time`,`a`.`layer` AS `layer`,`a`.`wait_time` AS `wait_time`,`a`.`Enabled` AS `Enabled` from `brandcustomerview2` `a` union select `a`.`customerid` AS `customerid`,`b`.`id` AS `brandid`,`b`.`brandname` AS `brandname`,`b`.`brandlogo` AS `brandlogo`,`b`.`description` AS `description`,`b`.`parentid` AS `parentid`,`b`.`schedule_time` AS `schedule_time`,`b`.`layer` AS `layer`,`b`.`wait_time` AS `wait_time`,`b`.`Enabled` AS `Enabled` from (`brandcustomerview2` `a` join `brand` `b`) where (`a`.`parentid` = `b`.`id`) order by `customerid`,`brandid` ;

-- ----------------------------
-- View structure for brandoutletcustomerview
-- ----------------------------
DROP VIEW IF EXISTS `brandoutletcustomerview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `brandoutletcustomerview` AS select `customeroutlet`.`customerid` AS `customerid`,`customeroutlet`.`outletid` AS `outletid`,`brandoutlet`.`brandid` AS `brandid` from (`customeroutlet` join `brandoutlet` on((`customeroutlet`.`outletid` = `brandoutlet`.`outletid`))) ;

-- ----------------------------
-- View structure for brandoutletuserview
-- ----------------------------
DROP VIEW IF EXISTS `brandoutletuserview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `brandoutletuserview` AS select `brandoutlet`.`brandid` AS `brandid`,`useroutlet`.`userid` AS `userid`,`useroutlet`.`outletid` AS `outletid` from (`brandoutlet` join `useroutlet` on((`useroutlet`.`outletid` = `brandoutlet`.`outletid`))) ;

-- ----------------------------
-- View structure for branduserview
-- ----------------------------
DROP VIEW IF EXISTS `branduserview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `branduserview` AS select `a`.`userid` AS `userid`,`a`.`brandid` AS `brandid`,`a`.`brandname` AS `brandname`,`a`.`brandlogo` AS `brandlogo`,`a`.`description` AS `description`,`a`.`parentid` AS `parentid`,`a`.`schedule_time` AS `schedule_time`,`a`.`layer` AS `layer`,`a`.`wait_time` AS `wait_time`,`a`.`Enabled` AS `Enabled` from `branduserview3` `a` union select `a`.`userid` AS `userid`,`b`.`id` AS `brandid`,`b`.`brandname` AS `brandname`,`b`.`brandlogo` AS `brandlogo`,`b`.`description` AS `description`,`b`.`parentid` AS `parentid`,`b`.`schedule_time` AS `schedule_time`,`b`.`layer` AS `layer`,`b`.`wait_time` AS `wait_time`,`b`.`Enabled` AS `Enabled` from (`branduserview3` `a` join `brand` `b`) where (`a`.`parentid` = `b`.`id`) order by `userid`,`brandid` ;

-- ----------------------------
-- View structure for branduserview1
-- ----------------------------
DROP VIEW IF EXISTS `branduserview1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `branduserview1` AS select `a`.`userid` AS `userid`,`c`.`id` AS `brandid`,`c`.`brandname` AS `brandname`,`c`.`brandlogo` AS `brandlogo`,`c`.`description` AS `description`,`c`.`parentid` AS `parentid`,`c`.`schedule_time` AS `schedule_time`,`c`.`layer` AS `layer`,`c`.`wait_time` AS `wait_time`,`c`.`Enabled` AS `Enabled` from (`branduser` `a` join `brand` `c` on((`a`.`brandid` = `c`.`id`))) group by `a`.`userid`,`c`.`id` ;

-- ----------------------------
-- View structure for branduserview2
-- ----------------------------
DROP VIEW IF EXISTS `branduserview2`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `branduserview2` AS select `a`.`userid` AS `userid`,`a`.`brandid` AS `brandid`,`a`.`brandname` AS `brandname`,`a`.`brandlogo` AS `brandlogo`,`a`.`description` AS `description`,`a`.`parentid` AS `parentid`,`a`.`schedule_time` AS `schedule_time`,`a`.`layer` AS `layer`,`a`.`wait_time` AS `wait_time`,`a`.`Enabled` AS `Enabled` from `branduserview1` `a` union select `a`.`userid` AS `userid`,`b`.`id` AS `brandid`,`b`.`brandname` AS `brandname`,`b`.`brandlogo` AS `brandlogo`,`b`.`description` AS `description`,`b`.`parentid` AS `parentid`,`b`.`schedule_time` AS `schedule_time`,`b`.`layer` AS `layer`,`b`.`wait_time` AS `wait_time`,`b`.`Enabled` AS `Enabled` from (`branduserview1` `a` join `brand` `b`) where (`a`.`parentid` = `b`.`id`) order by `userid`,`brandid` ;

-- ----------------------------
-- View structure for branduserview3
-- ----------------------------
DROP VIEW IF EXISTS `branduserview3`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `branduserview3` AS select `a`.`userid` AS `userid`,`a`.`brandid` AS `brandid`,`a`.`brandname` AS `brandname`,`a`.`brandlogo` AS `brandlogo`,`a`.`description` AS `description`,`a`.`parentid` AS `parentid`,`a`.`schedule_time` AS `schedule_time`,`a`.`layer` AS `layer`,`a`.`wait_time` AS `wait_time`,`a`.`Enabled` AS `Enabled` from `branduserview2` `a` union select `a`.`userid` AS `userid`,`b`.`id` AS `brandid`,`b`.`brandname` AS `brandname`,`b`.`brandlogo` AS `brandlogo`,`b`.`description` AS `description`,`b`.`parentid` AS `parentid`,`b`.`schedule_time` AS `schedule_time`,`b`.`layer` AS `layer`,`b`.`wait_time` AS `wait_time`,`b`.`Enabled` AS `Enabled` from (`branduserview2` `a` join `brand` `b`) where (`a`.`parentid` = `b`.`id`) order by `userid`,`brandid` ;

-- ----------------------------
-- View structure for capture_channel_view
-- ----------------------------
DROP VIEW IF EXISTS `capture_channel_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `capture_channel_view` AS select `users`.`UserId` AS `userid`,`users`.`Username` AS `username`,`capturechanneltask`.`camerano` AS `camerano`,0 AS `modelid`,`captureconfig`.`ip` AS `ip`,`captureconfig`.`ip2` AS `ip2`,`captureconfig`.`port` AS `PORT`,0 AS `ServerType` from ((`users` join `capturechanneltask` on((`users`.`UserId` = `capturechanneltask`.`userid`))) join `captureconfig` on((`capturechanneltask`.`userid` = `captureconfig`.`userid`))) where (((char_length(`captureconfig`.`ip`) > 0) or (char_length(`captureconfig`.`ip2`) > 0)) and (`users`.`Authlevel` = 4)) ;

-- ----------------------------
-- View structure for checkstandardview
-- ----------------------------
DROP VIEW IF EXISTS `checkstandardview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `checkstandardview` AS select 0 AS `standardtype`,`checkstandard`.`id` AS `id`,0 AS `parentid`,`checkstandard`.`template` AS `name`,'' AS `standard`,0 AS `type`,0 AS `severity`,0 AS `priority` from `checkstandard` where (`checkstandard`.`isdelete` = 0) union all select 1 AS `standardtype`,`checkstandarddirectory`.`id` AS `id`,`checkstandarddirectory`.`templateid` AS `parentid`,`checkstandarddirectory`.`directoryname` AS `name`,'' AS `standard`,0 AS `type`,0 AS `severity`,0 AS `priority` from `checkstandarddirectory` where (`checkstandarddirectory`.`isdelete` = 0) union all select 2 AS `standardtype`,`checkstandardsubdirectory`.`id` AS `id`,`checkstandardsubdirectory`.`directoryid` AS `parentid`,`checkstandardsubdirectory`.`subdirectoryname` AS `name`,'' AS `standard`,0 AS `type`,0 AS `severity`,0 AS `priority` from `checkstandardsubdirectory` where (`checkstandardsubdirectory`.`isdelete` = 0) union all select 3 AS `standardtype`,`checkstandarditem`.`id` AS `id`,`checkstandarditem`.`subdirectoryid` AS `parentid`,`checkstandarditem`.`itemname` AS `name`,`checkstandarditem`.`standard` AS `standard`,`checkstandarditem`.`type` AS `type`,`checkstandarditem`.`severity` AS `severity`,`checkstandarditem`.`priority` AS `priority` from `checkstandarditem` where (`checkstandarditem`.`isdelete` = 0) ;

-- ----------------------------
-- View structure for check_channel_view
-- ----------------------------
DROP VIEW IF EXISTS `check_channel_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `check_channel_view` AS select `users`.`UserId` AS `userid`,`users`.`Username` AS `username`,`checktask`.`camerano` AS `camerano`,`checkconfig`.`ip` AS `ip`,`checkconfig`.`ip2` AS `ip2`,`checkconfig`.`port` AS `PORT`,1 AS `ServerType`,0 AS `modelid` from ((`users` join `checktask` on((`users`.`UserId` = `checktask`.`userid`))) join `checkconfig` on((`checktask`.`userid` = `checkconfig`.`userid`))) where ((char_length(`checkconfig`.`ip`) > 0) and (`users`.`Authlevel` = 5)) ;

-- ----------------------------
-- View structure for customerchannelview
-- ----------------------------
DROP VIEW IF EXISTS `customerchannelview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customerchannelview` AS select `customerchannelview1`.`customerid` AS `customerid`,`customerchannelview1`.`CameraNo` AS `CameraNo`,`customerchannelview1`.`deviceid` AS `deviceid`,`customerchannelview1`.`Authority` AS `Authority`,`customerchannelview1`.`ChanIndex` AS `ChanIndex`,`customerchannelview1`.`CameraName` AS `CameraName`,`customerchannelview1`.`POS_X` AS `POS_X`,`customerchannelview1`.`POS_Y` AS `POS_Y`,`customerchannelview1`.`POS_FontSize` AS `POS_FontSize`,`customerchannelview1`.`POS_FontType` AS `POS_FontType`,`customerchannelview1`.`POS_FontColor` AS `POS_FontColor`,`customerchannelview1`.`POS_FontBold` AS `POS_FontBold`,`customerchannelview1`.`UseProfile` AS `UseProfile`,`customerchannelview1`.`MainProfile` AS `MainProfile`,`customerchannelview1`.`SubProfile` AS `SubProfile`,`customerchannelview1`.`Enabled` AS `Enabled`,`customerchannelview1`.`CheckSD` AS `CheckSD`,`customerchannelview1`.`CheckLowBattery` AS `CheckLowBattery`,`customerchannelview1`.`deviceright` AS `deviceright` from `customerchannelview1` union select `rangerchannelview`.`customerid` AS `customerid`,`rangerchannelview`.`CameraNo` AS `CameraNo`,`rangerchannelview`.`deviceid` AS `deviceid`,`rangerchannelview`.`Authority` AS `Authority`,`rangerchannelview`.`ChanIndex` AS `ChanIndex`,`rangerchannelview`.`CameraName` AS `CameraName`,`rangerchannelview`.`POS_X` AS `POS_X`,`rangerchannelview`.`POS_Y` AS `POS_Y`,`rangerchannelview`.`POS_FontSize` AS `POS_FontSize`,`rangerchannelview`.`POS_FontType` AS `POS_FontType`,`rangerchannelview`.`POS_FontColor` AS `POS_FontColor`,`rangerchannelview`.`POS_FontBold` AS `POS_FontBold`,`rangerchannelview`.`UseProfile` AS `UseProfile`,`rangerchannelview`.`MainProfile` AS `MainProfile`,`rangerchannelview`.`SubProfile` AS `SubProfile`,`rangerchannelview`.`Enabled` AS `Enabled`,`rangerchannelview`.`CheckSD` AS `CheckSD`,`rangerchannelview`.`CheckLowBattery` AS `CheckLowBattery`,`rangerchannelview`.`deviceright` AS `deviceright` from `rangerchannelview` ;

-- ----------------------------
-- View structure for customerchannelview1
-- ----------------------------
DROP VIEW IF EXISTS `customerchannelview1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customerchannelview1` AS select `a`.`customerid` AS `customerid`,`a`.`cameraid` AS `CameraNo`,`a`.`deviceid` AS `deviceid`,`b`.`Authority` AS `Authority`,`b`.`ChanIndex` AS `ChanIndex`,`b`.`CameraName` AS `CameraName`,`b`.`POS_X` AS `POS_X`,`b`.`POS_Y` AS `POS_Y`,`b`.`POS_FontSize` AS `POS_FontSize`,`b`.`POS_FontType` AS `POS_FontType`,`b`.`POS_FontColor` AS `POS_FontColor`,`b`.`POS_FontBold` AS `POS_FontBold`,`b`.`UseProfile` AS `UseProfile`,`b`.`MainProfile` AS `MainProfile`,`b`.`SubProfile` AS `SubProfile`,`b`.`Enabled` AS `Enabled`,`b`.`CheckSD` AS `CheckSD`,`b`.`CheckLowBattery` AS `CheckLowBattery`,`c`.`deviceright` AS `deviceright` from ((`customercamera` `a` left join `camera` `b` on(((`a`.`cameraid` = `b`.`CameraNo`) and (`a`.`deviceid` = `b`.`DeviceNo`)))) join `customer` `c` on((`c`.`id` = `a`.`customerid`))) where ((`b`.`Enabled` = 1) and (`c`.`deviceright` = 1)) ;

-- ----------------------------
-- View structure for customercustomerview
-- ----------------------------
DROP VIEW IF EXISTS `customercustomerview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customercustomerview` AS select `customercustomerview1`.`customerid` AS `customerid`,`customercustomerview1`.`outletid` AS `outletid`,`customercustomerview1`.`userid` AS `userid`,`customercustomerview1`.`username` AS `username`,`customercustomerview1`.`userdes` AS `userdes`,`customercustomerview1`.`userright` AS `userright`,`customercustomerview1`.`reportright` AS `reportright`,`customercustomerview1`.`deviceright` AS `deviceright`,`customercustomerview1`.`managerright` AS `managerright`,`customercustomerview1`.`isinspector` AS `isinspector`,`customercustomerview1`.`customercode` AS `customercode`,`customercustomerview1`.`relationship` AS `relationship`,`customercustomerview1`.`userpswd` AS `userpswd`,`customercustomerview1`.`bindsafecode` AS `bindsafecode`,`customercustomerview1`.`phone` AS `phone`,`customercustomerview1`.`position` AS `position` from `customercustomerview1` union select `customercustomerview2`.`customerid` AS `customerid`,`customercustomerview2`.`outletid` AS `outletid`,`customercustomerview2`.`userid` AS `userid`,`customercustomerview2`.`username` AS `username`,`customercustomerview2`.`userdes` AS `userdes`,`customercustomerview2`.`userright` AS `userright`,`customercustomerview2`.`reportright` AS `reportright`,`customercustomerview2`.`deviceright` AS `deviceright`,`customercustomerview2`.`managerright` AS `managerright`,`customercustomerview2`.`isinspector` AS `isinspector`,`customercustomerview2`.`customercode` AS `customercode`,`customercustomerview2`.`relationship` AS `relationship`,`customercustomerview2`.`userpswd` AS `userpswd`,`customercustomerview2`.`bindsafecode` AS `bindsafecode`,`customercustomerview2`.`phone` AS `phone`,`customercustomerview2`.`position` AS `position` from `customercustomerview2` union select `customercustomerview3`.`customerid` AS `customerid`,`customercustomerview3`.`outletid` AS `outletid`,`customercustomerview3`.`userid` AS `userid`,`customercustomerview3`.`username` AS `username`,`customercustomerview3`.`userdes` AS `userdes`,`customercustomerview3`.`userright` AS `userright`,`customercustomerview3`.`reportright` AS `reportright`,`customercustomerview3`.`deviceright` AS `deviceright`,`customercustomerview3`.`managerright` AS `managerright`,`customercustomerview3`.`isinspector` AS `isinspector`,`customercustomerview3`.`customercode` AS `customercode`,`customercustomerview3`.`relationship` AS `relationship`,`customercustomerview3`.`userpswd` AS `userpswd`,`customercustomerview3`.`bindsafecode` AS `bindsafecode`,`customercustomerview3`.`phone` AS `phone`,`customercustomerview3`.`position` AS `position` from `customercustomerview3` union select `customercustomerview4`.`customerid` AS `customerid`,`customercustomerview4`.`outletid` AS `outletid`,`customercustomerview4`.`userid` AS `userid`,`customercustomerview4`.`username` AS `username`,`customercustomerview4`.`userdes` AS `userdes`,`customercustomerview4`.`userright` AS `userright`,`customercustomerview4`.`reportright` AS `reportright`,`customercustomerview4`.`deviceright` AS `deviceright`,`customercustomerview4`.`managerright` AS `managerright`,`customercustomerview4`.`isinspector` AS `isinspector`,`customercustomerview4`.`customercode` AS `customercode`,`customercustomerview4`.`relationship` AS `relationship`,`customercustomerview4`.`userpswd` AS `userpswd`,`customercustomerview4`.`bindsafecode` AS `bindsafecode`,`customercustomerview4`.`phone` AS `phone`,`customercustomerview4`.`position` AS `position` from `customercustomerview4` union select `customercustomerview5`.`customerid` AS `customerid`,`customercustomerview5`.`outletid` AS `outletid`,`customercustomerview5`.`userid` AS `userid`,`customercustomerview5`.`username` AS `username`,`customercustomerview5`.`userdes` AS `userdes`,`customercustomerview5`.`userright` AS `userright`,`customercustomerview5`.`reportright` AS `reportright`,`customercustomerview5`.`deviceright` AS `deviceright`,`customercustomerview5`.`managerright` AS `managerright`,`customercustomerview5`.`isinspector` AS `isinspector`,`customercustomerview5`.`customercode` AS `customercode`,`customercustomerview5`.`relationship` AS `relationship`,`customercustomerview5`.`userpswd` AS `userpswd`,`customercustomerview5`.`bindsafecode` AS `bindsafecode`,`customercustomerview5`.`phone` AS `phone`,`customercustomerview5`.`position` AS `position` from `customercustomerview5` ;

-- ----------------------------
-- View structure for customercustomerview1
-- ----------------------------
DROP VIEW IF EXISTS `customercustomerview1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customercustomerview1` AS select `a`.`customerid` AS `customerid`,`a`.`outletid` AS `outletid`,`b`.`customerid` AS `userid`,`c`.`username` AS `username`,`c`.`userdes` AS `userdes`,`c`.`userright` AS `userright`,`c`.`reportright` AS `reportright`,`c`.`deviceright` AS `deviceright`,`c`.`managerright` AS `managerright`,`c`.`isinspector` AS `isinspector`,`c`.`customercode` AS `customercode`,0 AS `relationship`,'' AS `userpswd`,`d`.`isinspector` AS `customerisinspector`,`c`.`bindsafecode` AS `bindsafecode`,`c`.`phone` AS `phone`,`c`.`position` AS `position` from (((`customeroutlet` `a` left join `customeroutlet` `b` on((`b`.`outletid` = `a`.`outletid`))) left join `customer` `c` on((`b`.`customerid` = `c`.`id`))) left join `customer` `d` on((`a`.`customerid` = `d`.`id`))) where (((`c`.`userright` & 32) <> 32) and (`d`.`isinspector` = 0)) ;

-- ----------------------------
-- View structure for customercustomerview2
-- ----------------------------
DROP VIEW IF EXISTS `customercustomerview2`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customercustomerview2` AS select `a`.`customerid` AS `customerid`,`b`.`outletid` AS `outletid`,`d`.`id` AS `userid`,`d`.`username` AS `username`,`d`.`userdes` AS `userdes`,`d`.`userright` AS `userright`,`d`.`reportright` AS `reportright`,`d`.`deviceright` AS `deviceright`,`d`.`managerright` AS `managerright`,`d`.`isinspector` AS `isinspector`,`d`.`customercode` AS `customercode`,0 AS `relationship`,'' AS `userpswd`,`d`.`bindsafecode` AS `bindsafecode`,`d`.`phone` AS `phone`,`d`.`position` AS `position` from ((((`brandcustomerview` `a` left join `brandoutlet` `b` on((`b`.`brandid` = `a`.`brandid`))) join `customeroutlet` `c` on((`c`.`outletid` = `b`.`outletid`))) join `customer` `d` on(((`c`.`customerid` = `d`.`id`) and ((`d`.`userright` & 8) = 8) and (`d`.`isinspector` = 0)))) join `customer` `e` on((`a`.`customerid` = `e`.`id`))) where (`e`.`isinspector` = 0) ;

-- ----------------------------
-- View structure for customercustomerview3
-- ----------------------------
DROP VIEW IF EXISTS `customercustomerview3`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customercustomerview3` AS select `a`.`customerid` AS `customerid`,`b`.`outletid` AS `outletid`,`d`.`id` AS `userid`,`d`.`username` AS `username`,`d`.`userdes` AS `userdes`,`d`.`userright` AS `userright`,`d`.`reportright` AS `reportright`,`d`.`deviceright` AS `deviceright`,`d`.`managerright` AS `managerright`,`d`.`isinspector` AS `isinspector`,`d`.`customercode` AS `customercode`,0 AS `relationship`,'' AS `userpswd`,`d`.`bindsafecode` AS `bindsafecode`,`d`.`phone` AS `phone`,`d`.`position` AS `position` from ((((`customer` `e` join `brandcustomerview` `a` on((`e`.`id` = `a`.`customerid`))) join `brandoutlet` `b` on((`b`.`brandid` = `a`.`brandid`))) join `customeroutlet` `c` on((`c`.`outletid` = `b`.`outletid`))) join `customer` `d` on(((`c`.`customerid` = `d`.`id`) and ((`d`.`userright` & 16) = 16) and (`d`.`isinspector` = 0)))) where ((`e`.`userright` & 4) = 4) ;

-- ----------------------------
-- View structure for customercustomerview4
-- ----------------------------
DROP VIEW IF EXISTS `customercustomerview4`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customercustomerview4` AS select `a`.`customerid` AS `customerid`,`a`.`inspectorid` AS `userid`,0 AS `outletid`,`d`.`username` AS `username`,`d`.`userdes` AS `userdes`,`d`.`userright` AS `userright`,`d`.`reportright` AS `reportright`,`d`.`deviceright` AS `deviceright`,`d`.`managerright` AS `managerright`,`d`.`isinspector` AS `isinspector`,`d`.`customercode` AS `customercode`,`a`.`relationship` AS `relationship`,'' AS `userpswd`,`d`.`bindsafecode` AS `bindsafecode`,`d`.`phone` AS `phone`,`d`.`position` AS `position` from (`inspectorcustomerrelationship` `a` join `customer` `d` on((`a`.`inspectorid` = `d`.`id`))) ;

-- ----------------------------
-- View structure for customercustomerview5
-- ----------------------------
DROP VIEW IF EXISTS `customercustomerview5`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customercustomerview5` AS select `a`.`inspectorid` AS `customerid`,`a`.`customerid` AS `userid`,0 AS `outletid`,`b`.`username` AS `username`,`b`.`userdes` AS `userdes`,`b`.`userright` AS `userright`,`b`.`reportright` AS `reportright`,`b`.`deviceright` AS `deviceright`,`b`.`managerright` AS `managerright`,`b`.`isinspector` AS `isinspector`,`b`.`customercode` AS `customercode`,`a`.`relationship` AS `relationship`,'' AS `userpswd`,`b`.`bindsafecode` AS `bindsafecode`,`b`.`phone` AS `phone`,`b`.`position` AS `position` from (`inspectorcustomerrelationship` `a` join `customer` `b` on((`b`.`id` = `a`.`customerid`))) ;

-- ----------------------------
-- View structure for customerdeviceview
-- ----------------------------
DROP VIEW IF EXISTS `customerdeviceview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customerdeviceview` AS select `customerdeviceview1`.`customerid` AS `customerid`,`customerdeviceview1`.`deviceid` AS `deviceid`,`customerdeviceview1`.`Authority` AS `Authority`,`customerdeviceview1`.`DeviceType` AS `DeviceType`,`customerdeviceview1`.`ModelName` AS `ModelName`,`customerdeviceview1`.`DeviceName` AS `DeviceName`,`customerdeviceview1`.`EnableDomain` AS `EnableDomain`,`customerdeviceview1`.`DomainName` AS `DomainName`,`customerdeviceview1`.`IPAddress` AS `IPAddress`,`customerdeviceview1`.`TCPPort` AS `TCPPort`,`customerdeviceview1`.`Username` AS `Username`,`customerdeviceview1`.`Passwd` AS `Passwd`,`customerdeviceview1`.`ChanNum` AS `ChanNum`,`customerdeviceview1`.`Timeout` AS `Timeout`,`customerdeviceview1`.`DefaultLiveVideo` AS `DefaultLiveVideo`,`customerdeviceview1`.`DefaultPlaybackStream` AS `DefaultPlaybackStream`,`customerdeviceview1`.`IsRecord` AS `IsRecord`,`customerdeviceview1`.`ContactId` AS `ContactId`,`customerdeviceview1`.`WebPort` AS `WebPort`,`customerdeviceview1`.`ClientId` AS `ClientId`,`customerdeviceview1`.`TranscodePort` AS `TranscodePort`,`customerdeviceview1`.`deviceright` AS `deviceright`,`customerdeviceview1`.`outletid` AS `outletid` from `customerdeviceview1` union select `rangerdeviceview`.`customerid` AS `customerid`,`rangerdeviceview`.`deviceid` AS `deviceid`,`rangerdeviceview`.`Authority` AS `Authority`,`rangerdeviceview`.`DeviceType` AS `DeviceType`,`rangerdeviceview`.`ModelName` AS `ModelName`,`rangerdeviceview`.`DeviceName` AS `DeviceName`,`rangerdeviceview`.`EnableDomain` AS `EnableDomain`,`rangerdeviceview`.`DomainName` AS `DomainName`,`rangerdeviceview`.`IPAddress` AS `IPAddress`,`rangerdeviceview`.`TCPPort` AS `TCPPort`,`rangerdeviceview`.`Username` AS `Username`,`rangerdeviceview`.`Passwd` AS `Passwd`,`rangerdeviceview`.`ChanNum` AS `ChanNum`,`rangerdeviceview`.`Timeout` AS `Timeout`,`rangerdeviceview`.`DefaultLiveVideo` AS `DefaultLiveVideo`,`rangerdeviceview`.`DefaultPlaybackStream` AS `DefaultPlaybackStream`,`rangerdeviceview`.`IsRecord` AS `IsRecord`,`rangerdeviceview`.`ContactId` AS `ContactId`,`rangerdeviceview`.`WebPort` AS `WebPort`,`rangerdeviceview`.`ClientId` AS `ClientId`,`rangerdeviceview`.`TranscodePort` AS `TranscodePort`,`rangerdeviceview`.`deviceright` AS `deviceright`,`rangerdeviceview`.`outletid` AS `outletid` from `rangerdeviceview` ;

-- ----------------------------
-- View structure for customerdeviceview1
-- ----------------------------
DROP VIEW IF EXISTS `customerdeviceview1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customerdeviceview1` AS select `a`.`customerid` AS `customerid`,`a`.`deviceid` AS `deviceid`,`b`.`Authority` AS `Authority`,`b`.`DeviceType` AS `DeviceType`,`b`.`ModelName` AS `ModelName`,`b`.`DeviceName` AS `DeviceName`,`b`.`EnableDomain` AS `EnableDomain`,`b`.`DomainName` AS `DomainName`,`b`.`IPAddress` AS `IPAddress`,`b`.`TCPPort` AS `TCPPort`,`b`.`Username` AS `Username`,`b`.`Passwd` AS `Passwd`,`b`.`ChanNum` AS `ChanNum`,`b`.`Timeout` AS `Timeout`,`b`.`DefaultLiveVideo` AS `DefaultLiveVideo`,`b`.`DefaultPlaybackStream` AS `DefaultPlaybackStream`,`b`.`IsRecord` AS `IsRecord`,`b`.`ContactId` AS `ContactId`,`b`.`WebPort` AS `WebPort`,`b`.`ClientId` AS `ClientId`,`b`.`TranscodePort` AS `TranscodePort`,`c`.`deviceright` AS `deviceright`,`d`.`outletid` AS `outletid` from (((`customercamera` `a` join `videodevice` `b` on((`a`.`deviceid` = `b`.`DeviceNo`))) join `customer` `c` on((`c`.`id` = `a`.`customerid`))) join `videooutlet` `d` on((`a`.`deviceid` = `d`.`deviceno`))) where (`c`.`deviceright` = 1) group by `a`.`deviceid`,`a`.`customerid` ;

-- ----------------------------
-- View structure for customeroutletview
-- ----------------------------
DROP VIEW IF EXISTS `customeroutletview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customeroutletview` AS select `a`.`id` AS `userid`,`a`.`username` AS `username`,`a`.`userdes` AS `userdes`,`a`.`userright` AS `userright`,`a`.`reportright` AS `reportright`,`a`.`deviceright` AS `deviceright`,`a`.`managerright` AS `managerright`,`a`.`isinspector` AS `isinspector`,`b`.`outletid` AS `outletid`,`a`.`customercode` AS `customercode`,'' AS `userpswd`,`a`.`bindsafecode` AS `bindsafecode`,`a`.`phone` AS `phone`,`a`.`position` AS `position` from (`customer` `a` left join `customeroutlet` `b` on((`a`.`id` = `b`.`customerid`))) where ((`a`.`userright` & 32) <> 32) ;

-- ----------------------------
-- View structure for customerreportview
-- ----------------------------
DROP VIEW IF EXISTS `customerreportview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `customerreportview` AS select `a`.`reportid` AS `reportid`,`a`.`reporturl` AS `reporturl`,`a`.`reportpdf` AS `reportpdf`,`a`.`adminid` AS `adminid`,`a`.`recordid` AS `recordid`,`a`.`createtime` AS `createtime`,`b`.`readtime` AS `readtime`,if(isnull(`a`.`readtime`),0,1) AS `isread`,`a`.`outletid` AS `outletid`,`c`.`outletname` AS `outletname`,`a`.`severity` AS `severity`,`b`.`customerid` AS `customerid`,`b`.`status` AS `status`,`b`.`actiontype` AS `actiontype`,`b`.`transcount` AS `transcount` from ((`customerreport` `b` join `patrolreporthistory` `a`) join `outlet` `c`) where ((`b`.`reporttype` = 2) and (`a`.`reportid` = `b`.`reportid`) and (`a`.`outletid` = `c`.`outletid`)) order by `a`.`createtime` desc ;

-- ----------------------------
-- View structure for inspectorcustomerrelationshipview
-- ----------------------------
DROP VIEW IF EXISTS `inspectorcustomerrelationshipview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `inspectorcustomerrelationshipview` AS select `b`.`customerid` AS `inspectid`,`a`.`id` AS `customerid`,`a`.`username` AS `username`,`a`.`userdes` AS `userdes`,`b`.`relationship` AS `relationship` from (`customer` `a` join `inspectorcustomerrelationship` `b` on((`a`.`id` = `b`.`customerid`))) ;

-- ----------------------------
-- View structure for inspectreportview
-- ----------------------------
DROP VIEW IF EXISTS `inspectreportview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `inspectreportview` AS select `a`.`reportid` AS `reportid`,`a`.`outletid` AS `outletid`,`c`.`outletname` AS `outletname`,`b`.`customerid` AS `customerid`,`a`.`customerid` AS `inspectorid`,`a`.`createtime` AS `createtime`,`a`.`wifiname` AS `wifiname`,`a`.`itemid` AS `itemid`,`a`.`isemergency` AS `isemergency`,`a`.`distancefromdestoutlet` AS `distancefromdestoutlet`,`a`.`comment` AS `comment`,`a`.`reporturl` AS `reporturl`,`b`.`status` AS `status`,`b`.`actiontype` AS `actiontype`,`b`.`transcount` AS `transcount`,`a`.`reportmd5` AS `reportmd5` from ((`customerreport` `b` join `inspectreporthistory` `a`) join `outlet` `c`) where ((`b`.`reporttype` = 3) and (`a`.`reportid` = `b`.`reportid`) and (`a`.`outletid` = `c`.`outletid`)) order by `a`.`createtime` desc ;

-- ----------------------------
-- View structure for internalreportview
-- ----------------------------
DROP VIEW IF EXISTS `internalreportview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `internalreportview` AS select `b`.`AdminId` AS `AdminId`,`a`.`JobId` AS `JobId`,`a`.`OperatorId` AS `OperatorId`,`a`.`CameraNo` AS `CameraNo`,`a`.`ClientId` AS `ClientId`,`a`.`PosId` AS `PosId`,`a`.`Keyword` AS `Keyword`,`a`.`VideoStartTime` AS `VideoStartTime`,`a`.`VideoEndTime` AS `VideoEndTime`,`a`.`ReviewTime` AS `ReviewTime`,`a`.`PosText` AS `PosText`,`a`.`OperatorStatus` AS `OperatorStatus`,`a`.`OperatorFlag` AS `OperatorFlag`,`a`.`Comments` AS `Comments`,`a`.`PosType` AS `Postype` from (`internalreport` `a` left join `reportoperator` `b` on((`a`.`OperatorId` = `b`.`UserId`))) ;

-- ----------------------------
-- View structure for outletadminview
-- ----------------------------
DROP VIEW IF EXISTS `outletadminview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletadminview` AS select `a`.`outletid` AS `id`,`a`.`outletname` AS `outletname`,`a`.`outletlogo` AS `outletlogo`,`a`.`description` AS `description`,`a`.`outletaddress` AS `outletaddress`,`b`.`brandid` AS `brandid`,`c`.`standardid` AS `standardid` from ((`outlet` `a` join `brandoutlet` `b` on((`b`.`outletid` = `a`.`outletid`))) join `standardoutlet` `c` on((`c`.`outletid` = `a`.`outletid`))) ;

-- ----------------------------
-- View structure for outletbrandview
-- ----------------------------
DROP VIEW IF EXISTS `outletbrandview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletbrandview` AS select distinct `b`.`outletid` AS `outletid`,`b`.`brandid` AS `brandid`,`b`.`brandname` AS `brandname`,`b`.`layer` AS `layer`,`b`.`parentid` AS `parentid`,`b`.`outletname` AS `outletname`,`b`.`description` AS `description`,`b`.`outletlogo` AS `outletlogo` from `outletbrandview4` `b` where (`b`.`layer` = 0) ;

-- ----------------------------
-- View structure for outletbrandview1
-- ----------------------------
DROP VIEW IF EXISTS `outletbrandview1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletbrandview1` AS select `brandoutlet`.`brandid` AS `brandid`,`brandoutlet`.`outletid` AS `outletid`,`brand`.`brandname` AS `brandname`,`brand`.`parentid` AS `parentid`,`brand`.`layer` AS `layer`,`outlet`.`outletname` AS `outletname`,`outlet`.`description` AS `description`,`outlet`.`outletlogo` AS `outletlogo` from ((`brandoutlet` join `outlet` on((`brandoutlet`.`outletid` = `outlet`.`outletid`))) join `brand` on((`brand`.`id` = `brandoutlet`.`brandid`))) ;

-- ----------------------------
-- View structure for outletbrandview2
-- ----------------------------
DROP VIEW IF EXISTS `outletbrandview2`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletbrandview2` AS select `brand`.`id` AS `brandid`,`brand`.`brandname` AS `brandname`,`brand`.`layer` AS `layer`,`brand`.`parentid` AS `parentid`,`outletbrandview1`.`outletid` AS `outletid`,`outletbrandview1`.`outletname` AS `outletname`,`outletbrandview1`.`description` AS `description`,`outletbrandview1`.`outletlogo` AS `outletlogo` from (`outletbrandview1` join `brand` on((`outletbrandview1`.`parentid` = `brand`.`id`))) union all select `a`.`brandid` AS `brandid`,`a`.`brandname` AS `brandname`,`a`.`layer` AS `layer`,`a`.`parentid` AS `parentid`,`a`.`outletid` AS `outletid`,`a`.`outletname` AS `outletname`,`a`.`description` AS `description`,`a`.`outletlogo` AS `outletlogo` from `outletbrandview1` `a` ;

-- ----------------------------
-- View structure for outletbrandview3
-- ----------------------------
DROP VIEW IF EXISTS `outletbrandview3`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletbrandview3` AS select `brand`.`id` AS `brandid`,`brand`.`brandname` AS `brandname`,`brand`.`layer` AS `layer`,`brand`.`parentid` AS `parentid`,`outletbrandview2`.`outletid` AS `outletid`,`outletbrandview2`.`outletname` AS `outletname`,`outletbrandview2`.`description` AS `description`,`outletbrandview2`.`outletlogo` AS `outletlogo` from (`outletbrandview2` join `brand` on((`outletbrandview2`.`parentid` = `brand`.`id`))) union all select `a`.`brandid` AS `brandid`,`a`.`brandname` AS `brandname`,`a`.`layer` AS `layer`,`a`.`parentid` AS `parentid`,`a`.`outletid` AS `outletid`,`a`.`outletname` AS `outletname`,`a`.`description` AS `description`,`a`.`outletlogo` AS `outletlogo` from `outletbrandview2` `a` ;

-- ----------------------------
-- View structure for outletbrandview4
-- ----------------------------
DROP VIEW IF EXISTS `outletbrandview4`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletbrandview4` AS select `brand`.`id` AS `brandid`,`brand`.`brandname` AS `brandname`,`brand`.`layer` AS `layer`,`brand`.`parentid` AS `parentid`,`c`.`outletid` AS `outletid`,`c`.`outletname` AS `outletname`,`c`.`description` AS `description`,`c`.`outletlogo` AS `outletlogo` from (`outletbrandview3` `c` join `brand` on((`c`.`parentid` = `brand`.`id`))) union all select `a`.`brandid` AS `brandid`,`a`.`brandname` AS `brandname`,`a`.`layer` AS `layer`,`a`.`parentid` AS `parentid`,`a`.`outletid` AS `outletid`,`a`.`outletname` AS `outletname`,`a`.`description` AS `description`,`a`.`outletlogo` AS `outletlogo` from `outletbrandview3` `a` ;

-- ----------------------------
-- View structure for outletcameraview
-- ----------------------------
DROP VIEW IF EXISTS `outletcameraview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletcameraview` AS select `a`.`DeviceId` AS `DeviceId`,`b`.`CameraNo` AS `CameraNo`,`b`.`Authority` AS `Authority`,`b`.`ChanIndex` AS `ChanIndex`,`b`.`CameraName` AS `CameraName`,`b`.`POS_X` AS `POS_X`,`b`.`POS_Y` AS `POS_Y`,`b`.`POS_FontSize` AS `POS_FontSize`,`b`.`POS_FontType` AS `POS_FontType`,`b`.`POS_FontColor` AS `POS_FontColor`,`b`.`POS_FontBold` AS `POS_FontBold`,`b`.`UseProfile` AS `UseProfile`,`b`.`MainProfile` AS `MainProfile`,`b`.`SubProfile` AS `SubProfile`,`b`.`Enabled` AS `Enabled`,`b`.`CheckSD` AS `CheckSD`,`b`.`CheckLowBattery` AS `CheckLowBattery` from (`outletdeviceview` `a` left join `camera` `b` on((`a`.`DeviceId` = `b`.`DeviceNo`))) where (`b`.`Enabled` = 1) ;

-- ----------------------------
-- View structure for outletcorpview
-- ----------------------------
DROP VIEW IF EXISTS `outletcorpview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletcorpview` AS select `a`.`outletid` AS `outletid`,`a`.`brandid` AS `brandid`,`b`.`corpid` AS `corpid`,`b`.`corpname` AS `corpname`,`b`.`sendmode` AS `sendmode` from (`outletbrandview` `a` join `corporation` `b` on((`b`.`brandid` = `a`.`brandid`))) order by `a`.`outletid` ;

-- ----------------------------
-- View structure for outletcustomerview
-- ----------------------------
DROP VIEW IF EXISTS `outletcustomerview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletcustomerview` AS select `a`.`id` AS `customerid`,`a`.`deviceright` AS `deviceright`,`b`.`outletid` AS `outletid`,`c`.`outletname` AS `outletname`,`c`.`description` AS `description`,`c`.`outletlogo` AS `outletlogo`,`c`.`outletaddress` AS `outletaddress`,`d`.`brandid` AS `brandid`,`e`.`standardid` AS `standardid` from ((((`customer` `a` left join `customeroutlet` `b` on((`a`.`id` = `b`.`customerid`))) join `outlet` `c` on((`c`.`outletid` = `b`.`outletid`))) join `brandoutlet` `d` on((`d`.`outletid` = `b`.`outletid`))) join `standardoutlet` `e` on((`e`.`outletid` = `b`.`outletid`))) ;

-- ----------------------------
-- View structure for outletdeviceuserview
-- ----------------------------
DROP VIEW IF EXISTS `outletdeviceuserview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletdeviceuserview` AS select `a`.`deviceno` AS `deviceno`,`a`.`outletid` AS `outletid`,`b`.`userid` AS `userid` from (`videooutlet` `a` left join `useroutlet` `b` on((`a`.`outletid` = `b`.`outletid`))) ;

-- ----------------------------
-- View structure for outletdeviceview
-- ----------------------------
DROP VIEW IF EXISTS `outletdeviceview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletdeviceview` AS select `a`.`deviceno` AS `DeviceId`,`a`.`outletid` AS `OutletId`,`b`.`Authority` AS `Authority`,`b`.`DeviceType` AS `DeviceType`,`b`.`ModelName` AS `ModelName`,`b`.`DeviceName` AS `DeviceName`,`b`.`EnableDomain` AS `EnableDomain`,`b`.`DomainName` AS `DomainName`,`b`.`IPAddress` AS `IPAddress`,`b`.`TCPPort` AS `TCPPort`,`b`.`Username` AS `Username`,`b`.`Passwd` AS `Passwd`,`b`.`ChanNum` AS `ChanNum`,`b`.`Timeout` AS `Timeout`,`b`.`DefaultLiveVideo` AS `DefaultLiveVideo`,`b`.`DefaultPlaybackStream` AS `DefaultPlaybackStream`,`b`.`IsRecord` AS `IsRecord`,`b`.`ContactId` AS `ContactId`,`b`.`WebPort` AS `WebPort`,`b`.`ClientId` AS `ClientId`,`b`.`TranscodePort` AS `TranscodePort` from (`videooutlet` `a` left join `videodevice` `b` on((`a`.`deviceno` = `b`.`DeviceNo`))) ;

-- ----------------------------
-- View structure for outletreportview
-- ----------------------------
DROP VIEW IF EXISTS `outletreportview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletreportview` AS select `patrolreporthistory`.`reportid` AS `ReportId`,`patrolreporthistory`.`outletid` AS `OutletId`,`patrolreporthistory`.`reporturl` AS `ReportUrl`,`patrolreporthistory`.`reportpdf` AS `ReportPdf`,`patrolreporthistory`.`createtime` AS `CreateTime`,`patrolreporthistory`.`readtime` AS `ReadTime`,`patrolreporthistory`.`severity` AS `Severity`,if(isnull(`patrolreporthistory`.`readtime`),0,1) AS `isread` from `patrolreporthistory` ;

-- ----------------------------
-- View structure for outletuserview
-- ----------------------------
DROP VIEW IF EXISTS `outletuserview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outletuserview` AS select distinct `brandoutlet`.`brandid` AS `brandid`,`brandoutlet`.`outletid` AS `id`,`outlet_path_view`.`check_path` AS `check_path`,`outlet_path_view`.`capture_path` AS `capture_path`,`outlet_path_view`.`checktype` AS `checktype`,`outlet`.`outletname` AS `outletname`,`outlet`.`description` AS `description`,`outlet`.`outletlogo` AS `outletlogo`,`outlet`.`outletaddress` AS `outletaddress`,`useroutlet`.`userid` AS `userid`,`standardoutlet`.`standardid` AS `standardid` from ((((`useroutlet` left join `outlet` on((`useroutlet`.`outletid` = `outlet`.`outletid`))) left join `brandoutlet` on((`brandoutlet`.`outletid` = `outlet`.`outletid`))) left join `outlet_path_view` on((`outlet`.`outletid` = `outlet_path_view`.`outletid`))) left join `standardoutlet` on((`standardoutlet`.`outletid` = `outlet`.`outletid`))) ;

-- ----------------------------
-- View structure for outlet_path_view
-- ----------------------------
DROP VIEW IF EXISTS `outlet_path_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `outlet_path_view` AS select `user_outlet_check_view`.`checktype` AS `checktype`,`user_outlet_check_view`.`savepath` AS `check_path`,`user_outlet_capture_view`.`savepath` AS `capture_path`,`outlet`.`outletid` AS `outletid` from ((`outlet` left join `user_outlet_capture_view` on((`user_outlet_capture_view`.`outletid` = `outlet`.`outletid`))) left join `user_outlet_check_view` on((`outlet`.`outletid` = `user_outlet_check_view`.`outletid`))) ;

-- ----------------------------
-- View structure for patrolopprogressview
-- ----------------------------
DROP VIEW IF EXISTS `patrolopprogressview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `patrolopprogressview` AS select `a`.`camerano` AS `camerano`,`a`.`operatorid` AS `operatorid`,`a`.`committime` AS `lastcommittime`,`a`.`majorpic` AS `lastphotourl`,`c`.`todaycommitcount` AS `todaycommitcount` from ((`patrolrecord` `a` join `patrolopprogressviewhelp1` `b` on(((`a`.`camerano` = `b`.`camerano`) and (`a`.`committime` = `b`.`maxtime`)))) left join `patrolopprogressviewhelp2` `c` on((`a`.`camerano` = `c`.`camerano`))) ;

-- ----------------------------
-- View structure for patrolopprogressviewhelp1
-- ----------------------------
DROP VIEW IF EXISTS `patrolopprogressviewhelp1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `patrolopprogressviewhelp1` AS select `patrolrecord`.`camerano` AS `camerano`,max(`patrolrecord`.`committime`) AS `maxtime` from `patrolrecord` group by `patrolrecord`.`camerano` ;

-- ----------------------------
-- View structure for patrolopprogressviewhelp2
-- ----------------------------
DROP VIEW IF EXISTS `patrolopprogressviewhelp2`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `patrolopprogressviewhelp2` AS select count(1) AS `todaycommitcount`,`patrolrecord`.`camerano` AS `camerano` from `patrolrecord` where (to_days(`patrolrecord`.`committime`) = to_days(now())) group by `patrolrecord`.`camerano` ;

-- ----------------------------
-- View structure for patrolrecordview
-- ----------------------------
DROP VIEW IF EXISTS `patrolrecordview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `patrolrecordview` AS select `patrolrecord`.`id` AS `id`,`patrolrecord`.`reportid` AS `reportid`,`patrolrecord`.`outletid` AS `outletid`,`patrolrecord`.`deviceno` AS `deviceno`,`patrolrecord`.`camerano` AS `camerano`,`patrolrecord`.`operatorid` AS `operatorid`,`patrolrecord`.`clientip` AS `clientip`,`patrolrecord`.`committime` AS `committime`,`patrolrecord`.`actiontime` AS `actiontime`,`patrolrecord`.`itemid` AS `itemid`,`patrolrecord`.`comment` AS `comment`,`patrolrecord`.`majorpic` AS `majorpic`,`patrolrecord`.`majormd5` AS `majormd5`,`patrolrecord`.`minorpic` AS `minorpic`,`patrolrecord`.`minormd5` AS `minormd5`,`patrolrecord`.`videoinfo` AS `videoinfo` from `patrolrecord` union select `patrolrecordhistory`.`id` AS `id`,`patrolrecordhistory`.`reportid` AS `reportid`,`patrolrecordhistory`.`outletid` AS `outletid`,`patrolrecordhistory`.`deviceno` AS `deviceno`,`patrolrecordhistory`.`camerano` AS `camerano`,`patrolrecordhistory`.`operatorid` AS `operatorid`,`patrolrecordhistory`.`clientip` AS `clientip`,`patrolrecordhistory`.`committime` AS `committime`,`patrolrecordhistory`.`actiontime` AS `actiontime`,`patrolrecordhistory`.`itemid` AS `itemid`,`patrolrecordhistory`.`comment` AS `comment`,`patrolrecordhistory`.`majorpic` AS `majorpic`,`patrolrecordhistory`.`majormd5` AS `majormd5`,`patrolrecordhistory`.`minorpic` AS `minorpic`,`patrolrecordhistory`.`minormd5` AS `minormd5`,`patrolrecordhistory`.`videoinfo` AS `videoinfo` from `patrolrecordhistory` ;

-- ----------------------------
-- View structure for rangerchannelview
-- ----------------------------
DROP VIEW IF EXISTS `rangerchannelview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `rangerchannelview` AS select `a`.`customerid` AS `customerid`,`a`.`deviceid` AS `deviceid`,`a`.`deviceright` AS `deviceright`,`b`.`CameraNo` AS `CameraNo`,`b`.`Authority` AS `Authority`,`b`.`ChanIndex` AS `ChanIndex`,`b`.`CameraName` AS `CameraName`,`b`.`POS_X` AS `POS_X`,`b`.`POS_Y` AS `POS_Y`,`b`.`POS_FontSize` AS `POS_FontSize`,`b`.`POS_FontType` AS `POS_FontType`,`b`.`POS_FontColor` AS `POS_FontColor`,`b`.`POS_FontBold` AS `POS_FontBold`,`b`.`UseProfile` AS `UseProfile`,`b`.`MainProfile` AS `MainProfile`,`b`.`SubProfile` AS `SubProfile`,`b`.`Enabled` AS `Enabled`,`b`.`CheckSD` AS `CheckSD`,`b`.`CheckLowBattery` AS `CheckLowBattery` from (`rangerdeviceview` `a` left join `camera` `b` on((`a`.`deviceid` = `b`.`DeviceNo`))) ;

-- ----------------------------
-- View structure for rangerdeviceview
-- ----------------------------
DROP VIEW IF EXISTS `rangerdeviceview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `rangerdeviceview` AS select `a`.`id` AS `customerid`,`a`.`deviceright` AS `deviceright`,`b`.`outletid` AS `outletid`,`d`.`DeviceNo` AS `deviceid`,`d`.`Authority` AS `Authority`,`d`.`DeviceType` AS `DeviceType`,`d`.`ModelName` AS `ModelName`,`d`.`DeviceName` AS `DeviceName`,`d`.`EnableDomain` AS `EnableDomain`,`d`.`DomainName` AS `DomainName`,`d`.`IPAddress` AS `IPAddress`,`d`.`TCPPort` AS `TCPPort`,`d`.`Username` AS `Username`,`d`.`Passwd` AS `Passwd`,`d`.`ChanNum` AS `ChanNum`,`d`.`Timeout` AS `Timeout`,`d`.`DefaultLiveVideo` AS `DefaultLiveVideo`,`d`.`DefaultPlaybackStream` AS `DefaultPlaybackStream`,`d`.`IsRecord` AS `IsRecord`,`d`.`ContactId` AS `ContactId`,`d`.`WebPort` AS `WebPort`,`d`.`ClientId` AS `ClientId`,`d`.`TranscodePort` AS `TranscodePort` from (((`customer` `a` join `customeroutlet` `b` on((`b`.`customerid` = `a`.`id`))) join `videooutlet` `c` on((`c`.`outletid` = `b`.`outletid`))) join `videodevice` `d` on((`d`.`DeviceNo` = `c`.`deviceno`))) where (`a`.`isinspector` = 1) ;

-- ----------------------------
-- View structure for realtimeaudit_channel_view
-- ----------------------------
DROP VIEW IF EXISTS `realtimeaudit_channel_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `realtimeaudit_channel_view` AS select `users`.`UserId` AS `UserId`,`users`.`Username` AS `Username`,`userchannelview`.`CameraNo` AS `CameraNo` from (`users` join `userchannelview` on((`users`.`UserId` = `userchannelview`.`UserId`))) where (`users`.`Authlevel` = 8) ;

-- ----------------------------
-- View structure for realtimereportview
-- ----------------------------
DROP VIEW IF EXISTS `realtimereportview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `realtimereportview` AS select `a`.`reportid` AS `reportid`,`a`.`reporturl` AS `reporturl`,`a`.`createtime` AS `createtime`,`b`.`readtime` AS `readtime`,`a`.`itemid` AS `itemid`,`a`.`itemname` AS `itemname`,`a`.`COMMENT` AS `comment`,`a`.`majorpic` AS `picurl`,`a`.`cameraname` AS `cameraname`,`a`.`actiontime` AS `actiontime`,if(isnull(`b`.`readtime`),0,1) AS `isread`,`a`.`outletid` AS `outletid`,`c`.`outletname` AS `outletname`,`b`.`status` AS `status`,`b`.`customerid` AS `customerid`,`b`.`actiontype` AS `actiontype`,`b`.`transcount` AS `transcount` from ((`customerreport` `b` join `realtimereporthistory` `a`) join `outlet` `c`) where ((`b`.`reporttype` = 4) and (`a`.`reportid` = `b`.`reportid`) and (`a`.`outletid` = `c`.`outletid`)) order by `a`.`createtime` desc ;

-- ----------------------------
-- View structure for realtime_outlet_user_view
-- ----------------------------
DROP VIEW IF EXISTS `realtime_outlet_user_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `realtime_outlet_user_view` AS select `brandoutlet`.`outletid` AS `id`,`brandoutlet`.`brandid` AS `brandid`,`outlet`.`outletname` AS `outletname`,`outlet`.`description` AS `description`,`outlet`.`outletlogo` AS `outletlogo`,`outlet`.`outletaddress` AS `outletaddress`,`useroutlet`.`userid` AS `userid`,`standardoutlet`.`standardid` AS `standardid` from (((`useroutlet` join `outlet` on((`useroutlet`.`outletid` = `outlet`.`outletid`))) join `brandoutlet` on((`brandoutlet`.`outletid` = `outlet`.`outletid`))) left join `standardoutlet` on((`standardoutlet`.`outletid` = `outlet`.`outletid`))) ;

-- ----------------------------
-- View structure for reportclientview
-- ----------------------------
DROP VIEW IF EXISTS `reportclientview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `reportclientview` AS select `reporthistory`.`ClientId` AS `ClientId` from `reporthistory` group by `reporthistory`.`ClientId` ;

-- ----------------------------
-- View structure for reporthistoryview
-- ----------------------------
DROP VIEW IF EXISTS `reporthistoryview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `reporthistoryview` AS select `b`.`AdminId` AS `AdminId`,`a`.`JobId` AS `JobId`,`a`.`OperatorId` AS `OperatorId`,`a`.`CameraNo` AS `CameraNo`,`a`.`ClientId` AS `ClientId`,`a`.`PosId` AS `PosId`,`a`.`Keyword` AS `Keyword`,`a`.`VideoStartTime` AS `VideoStartTime`,`a`.`VideoEndTime` AS `VideoEndTime`,`a`.`ReviewTime` AS `ReviewTime`,`a`.`PosText` AS `PosText`,`a`.`OperatorStatus` AS `OperatorStatus`,`a`.`OperatorFlag` AS `OperatorFlag`,`a`.`Comments` AS `Comments`,`a`.`PosType` AS `Postype`,`a`.`extflag` AS `ExtFlag` from (`internalreport` `a` left join `reportoperator` `b` on((`a`.`OperatorId` = `b`.`UserId`))) ;

-- ----------------------------
-- View structure for reportoperatorview
-- ----------------------------
DROP VIEW IF EXISTS `reportoperatorview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `reportoperatorview` AS select `a`.`UserId` AS `UserId`,`a`.`AdminId` AS `AdminId`,`a`.`LastJobId` AS `LastJobId`,`a`.`LastResetTime` AS `LastResetTime`,`a`.`SearchInterval` AS `SearchInterval`,`a`.`PlayAdvanceTime` AS `PlayAdvanceTime`,`a`.`CheckedDays` AS `CheckedDays`,`b`.`Username` AS `Username` from (`reportoperator` `a` join `users` `b`) where (`a`.`UserId` = `b`.`UserId`) ;

-- ----------------------------
-- View structure for reportposview
-- ----------------------------
DROP VIEW IF EXISTS `reportposview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `reportposview` AS select `reporthistory`.`PosId` AS `PosId` from `reporthistory` group by `reporthistory`.`PosId` ;

-- ----------------------------
-- View structure for server_channel_view
-- ----------------------------
DROP VIEW IF EXISTS `server_channel_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `server_channel_view` AS select `ai_channel_view`.`userid` AS `userid`,`ai_channel_view`.`username` AS `username`,`ai_channel_view`.`ip` AS `ip`,`ai_channel_view`.`ip2` AS `ip2`,`ai_channel_view`.`PORT` AS `port`,`ai_channel_view`.`camerano` AS `CameraNo`,`ai_channel_view`.`ServerType` AS `ServerType`,`ai_channel_view`.`modelid` AS `modelid` from `ai_channel_view` union select `check_channel_view`.`userid` AS `userid`,`check_channel_view`.`username` AS `username`,`check_channel_view`.`ip` AS `ip`,`check_channel_view`.`ip2` AS `ip2`,`check_channel_view`.`PORT` AS `port`,`check_channel_view`.`camerano` AS `CameraNo`,`check_channel_view`.`ServerType` AS `ServerType`,`check_channel_view`.`modelid` AS `modelid` from `check_channel_view` union all select `capture_channel_view`.`userid` AS `userid`,`capture_channel_view`.`username` AS `username`,`capture_channel_view`.`ip` AS `ip`,`capture_channel_view`.`ip2` AS `ip2`,`capture_channel_view`.`PORT` AS `port`,`capture_channel_view`.`camerano` AS `CameraNo`,`capture_channel_view`.`ServerType` AS `ServerType`,`capture_channel_view`.`modelid` AS `modelid` from `capture_channel_view` ;

-- ----------------------------
-- View structure for subitemcheckstandardview
-- ----------------------------
DROP VIEW IF EXISTS `subitemcheckstandardview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `subitemcheckstandardview` AS select `checkstandarditem`.`id` AS `id`,`checkstandarditem`.`type` AS `type`,`checkstandarditem`.`priority` AS `priority`,`checkstandarditem`.`severity` AS `severity`,`checkstandarddirectory`.`directoryname` AS `directoryname`,`checkstandardsubdirectory`.`subdirectoryname` AS `subdirectoryname`,`checkstandarditem`.`itemname` AS `itemname`,`checkstandarddirectory`.`templateid` AS `templateid` from (((`checkstandarditem` left join `checkstandardsubdirectory` on((`checkstandarditem`.`subdirectoryid` = `checkstandardsubdirectory`.`id`))) left join `checkstandarddirectory` on((`checkstandardsubdirectory`.`directoryid` = `checkstandarddirectory`.`id`))) left join `checkstandard` on((`checkstandarddirectory`.`templateid` = `checkstandard`.`id`))) ;

-- ----------------------------
-- View structure for userchannelview
-- ----------------------------
DROP VIEW IF EXISTS `userchannelview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `userchannelview` AS select `a`.`CameraNo` AS `CameraNo`,`a`.`DeviceNo` AS `DeviceNo`,`a`.`UserId` AS `UserId`,`a`.`DeviceAuthority` AS `DeviceAuthority`,if(strcmp('0,0,0,0,0,0',`a`.`CameraAuthority`),if(strcmp('0,0,0,0,0,0',`b`.`Authority`),`b`.`Authority`,'0,0,0,0,0,0'),'0,0,0,0,0,0') AS `CameraAuthority`,`b`.`ChanIndex` AS `ChanIndex`,`b`.`CameraName` AS `CameraName`,`b`.`POS_X` AS `POS_X`,`b`.`POS_Y` AS `POS_Y`,`b`.`POS_FontSize` AS `POS_FontSize`,`b`.`POS_FontType` AS `POS_FontType`,`b`.`POS_FontColor` AS `POS_FontColor`,`b`.`POS_FontBold` AS `POS_FontBold`,`b`.`UseProfile` AS `UseProfile`,`b`.`MainProfile` AS `MainProfile`,`b`.`SubProfile` AS `SubProfile`,`b`.`Enabled` AS `Enabled`,`b`.`CheckSD` AS `CheckSD`,`b`.`CameraType` AS `CameraType`,`b`.`CheckLowBattery` AS `CheckLowBattery`,10 AS `tasktype` from (`usercamera` `a` join `camera` `b` on(((`a`.`CameraNo` = `b`.`CameraNo`) and (`a`.`DeviceNo` = `b`.`DeviceNo`)))) where (`b`.`Enabled` = 1) ;

-- ----------------------------
-- View structure for userchannelwithtaskview
-- ----------------------------
DROP VIEW IF EXISTS `userchannelwithtaskview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `userchannelwithtaskview` AS select `userchannelview`.`UserId` AS `userid`,`userchannelview`.`DeviceNo` AS `deviceno`,`userchannelview`.`CameraNo` AS `camerano` from ((`captureconfig` join `userchannelview` on((`captureconfig`.`userid` = `userchannelview`.`UserId`))) join `capturechanneltask` on(((`userchannelview`.`UserId` = `capturechanneltask`.`userid`) and (`userchannelview`.`CameraNo` = `capturechanneltask`.`camerano`) and (`userchannelview`.`DeviceNo` = `capturechanneltask`.`deviceno`)))) union select `userchannelview`.`UserId` AS `userid`,`checktask`.`deviceno` AS `deviceno`,`userchannelview`.`CameraNo` AS `camerano` from ((`userchannelview` join `checkconfig` on((`checkconfig`.`userid` = `userchannelview`.`UserId`))) join `checktask` on(((`userchannelview`.`CameraNo` = `checktask`.`camerano`) and (`userchannelview`.`DeviceNo` = `checktask`.`deviceno`) and (`userchannelview`.`UserId` = `checktask`.`userid`)))) union select `userchannelview`.`UserId` AS `userid`,`aitask`.`deviceno` AS `deviceno`,`userchannelview`.`CameraNo` AS `camerano` from ((`userchannelview` join `checkconfig` on((`checkconfig`.`userid` = `userchannelview`.`UserId`))) join `aitask` on(((`userchannelview`.`CameraNo` = `aitask`.`camerano`) and (`userchannelview`.`DeviceNo` = `aitask`.`deviceno`) and (`userchannelview`.`UserId` = `aitask`.`userid`)))) ;

-- ----------------------------
-- View structure for userdeviceview
-- ----------------------------
DROP VIEW IF EXISTS `userdeviceview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `userdeviceview` AS select `a`.`UserId` AS `UserId`,`a`.`DeviceNo` AS `DeviceNo`,`a`.`DeviceAuthority` AS `DeviceAuthority`,`b`.`DeviceType` AS `DeviceType`,`b`.`ModelName` AS `ModelName`,`b`.`DeviceName` AS `DeviceName`,`b`.`EnableDomain` AS `EnableDomain`,`b`.`DomainName` AS `DomainName`,`b`.`IPAddress` AS `IPAddress`,`b`.`TCPPort` AS `TCPPort`,`b`.`Username` AS `Username`,`b`.`Passwd` AS `Passwd`,`b`.`ChanNum` AS `ChanNum`,`b`.`Timeout` AS `Timeout`,`b`.`DefaultLiveVideo` AS `DefaultLiveVideo`,`b`.`DefaultPlaybackStream` AS `DefaultPlaybackStream`,`b`.`IsRecord` AS `IsRecord`,`b`.`ContactId` AS `ContactId`,`b`.`WebPort` AS `WebPort`,`b`.`ClientId` AS `ClientId`,`b`.`TranscodePort` AS `TranscodePort`,`videooutlet`.`outletid` AS `Outletid` from ((`usercamera` `a` left join `videodevice` `b` on((`a`.`DeviceNo` = `b`.`DeviceNo`))) join `videooutlet` on((`b`.`DeviceNo` = `videooutlet`.`deviceno`))) ;

-- ----------------------------
-- View structure for useremapview
-- ----------------------------
DROP VIEW IF EXISTS `useremapview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `useremapview` AS select `a`.`UserId` AS `UserId`,`a`.`MapId` AS `MapId`,`a`.`RunStartup` AS `RunStartup`,`b`.`MapName` AS `MapName`,`b`.`MapObject` AS `MapObject`,`b`.`MD5` AS `MD5` from (`useremap` `a` left join `emap` `b` on((`a`.`MapId` = `b`.`MapId`))) ;

-- ----------------------------
-- View structure for usergroupcamerapairview
-- ----------------------------
DROP VIEW IF EXISTS `usergroupcamerapairview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `usergroupcamerapairview` AS select `a`.`UserId` AS `userid`,`a`.`GroupId` AS `GroupId`,`b`.`CameraNo` AS `camerano`,`b`.`DeviceNo` AS `DeviceNo`,`b`.`IndexInGroup` AS `IndexInGroup` from (`uservideogroup` `a` left join `cameragrouppair` `b` on((`b`.`GroupId` = `a`.`GroupId`))) ;

-- ----------------------------
-- View structure for uservideogroupview
-- ----------------------------
DROP VIEW IF EXISTS `uservideogroupview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `uservideogroupview` AS select `a`.`UserId` AS `UserId`,`a`.`GroupId` AS `GroupId`,`a`.`RunStartup` AS `RunStartup`,`b`.`GroupName` AS `GroupName`,`b`.`Style` AS `Style`,`b`.`StillTime` AS `StillTime` from (`uservideogroup` `a` left join `videogroup` `b` on((`b`.`GroupId` = `a`.`GroupId`))) ;

-- ----------------------------
-- View structure for user_outlet_capture_view
-- ----------------------------
DROP VIEW IF EXISTS `user_outlet_capture_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `user_outlet_capture_view` AS select `useroutlet`.`outletid` AS `outletid`,`useroutlet`.`userid` AS `userid`,`captureconfig`.`savepath` AS `savepath` from (`useroutlet` join `captureconfig` on((`useroutlet`.`userid` = `captureconfig`.`userid`))) ;

-- ----------------------------
-- View structure for user_outlet_check_view
-- ----------------------------
DROP VIEW IF EXISTS `user_outlet_check_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`freedom`@`%` SQL SECURITY DEFINER VIEW `user_outlet_check_view` AS select `useroutlet`.`outletid` AS `outletid`,`checkconfig`.`userid` AS `userid`,`checkconfig`.`savepath` AS `savepath`,`checkconfig`.`checktype` AS `checktype` from (`checkconfig` join `useroutlet` on((`checkconfig`.`userid` = `useroutlet`.`userid`))) ;
DROP TRIGGER IF EXISTS `t_afterdelete_on_camera`;
DELIMITER ;;
CREATE TRIGGER `t_afterdelete_on_camera` AFTER DELETE ON `camera` FOR EACH ROW BEGIN
delete from CameraGroupPair where CameraNo = old.CameraNo;
delete from UserCamera where CameraNo = old.CameraNo;
delete from camerapos where CameraNo = old.CameraNo;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `triErplyCustom`;
DELIMITER ;;
CREATE TRIGGER `triErplyCustom` BEFORE DELETE ON `erplycustom` FOR EACH ROW BEGIN
DECLARE customIdentify INT;
SET customIdentify = old.customerCode;
DELETE FROM erplyposinfo  WHERE customerCode = customIdentify;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `triErplyPosInfo`;
DELIMITER ;;
CREATE TRIGGER `triErplyPosInfo` BEFORE DELETE ON `erplyposinfo` FOR EACH ROW BEGIN
DECLARE posinfoId INT;
SET posinfoId = old.Id;
DELETE FROM camerapos WHERE PosType = 1 AND  PosSpecialId=posinfoId;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `t_afterinsert_on_reporthistory`;
DELIMITER ;;
CREATE TRIGGER `t_afterinsert_on_reporthistory` AFTER INSERT ON `reporthistory` FOR EACH ROW BEGIN
delete from internalreport where internalreport.JobId  =new.JobId and internalreport.OperatorId = new.OperatorId;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `triUserDelete`;
DELIMITER ;;
CREATE TRIGGER `triUserDelete` BEFORE DELETE ON `users` FOR EACH ROW BEGIN
DECLARE authlevel INT;
SET authlevel = old.Authlevel;
IF authlevel = 0 THEN
DELETE FROM reportadmin  WHERE UserId = old.UserId;
DELETE FROM reportoperator WHERE AdminId = old.UserId;
ELSE
DELETE FROM reportoperator WHERE UserId = old.UserId;
END IF;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `t_afterdelete_on_users`;
DELIMITER ;;
CREATE TRIGGER `t_afterdelete_on_users` AFTER DELETE ON `users` FOR EACH ROW BEGIN
delete from UserCamera where UserId = old.UserId;
delete from UserVideoGroup where UserId = old.UserId;
delete from UserEmap where UserId = old.UserId;
delete from UserRight where UserId = old.UserId;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `t_afterdelete_on_device`;
DELIMITER ;;
CREATE TRIGGER `t_afterdelete_on_device` AFTER DELETE ON `videodevice` FOR EACH ROW BEGIN
delete from CameraGroupPair where DeviceNo = old.DeviceNo;
delete from Camera where DeviceNo = old.DeviceNo;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `t_afterdelete_on_videogroup`;
DELIMITER ;;
CREATE TRIGGER `t_afterdelete_on_videogroup` AFTER DELETE ON `videogroup` FOR EACH ROW BEGIN
delete from cameragrouppair where cameragrouppair.GroupId  =old.GroupId;
delete from  uservideogroup where  uservideogroup.GroupId = old.GroupId;
END
;;
DELIMITER ;
