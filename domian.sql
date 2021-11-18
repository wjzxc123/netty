/*
 Navicat Premium Data Transfer

 Source Server         : licon
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : domian

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 18/11/2021 15:05:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `USER_ID` int(32) NOT NULL,
  `ACCOUNT_ID` int(32) DEFAULT NULL,
  `ACCOUNT_NUMBER` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `AVAILABLE` decimal(10, 0) DEFAULT NULL,
  `DAILY_LIMIT` decimal(10, 0) DEFAULT NULL,
  `CURRENCY` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 1, '123456', 6000, 1200, 'CNY');
INSERT INTO `account` VALUES (2, 2, '1234567', 14000, 1200, 'CNY');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('scheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('scheduler', 'JTYSL-024D5W81637219018176', 1637219098982, 20000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
