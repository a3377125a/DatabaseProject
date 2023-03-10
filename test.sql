/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 17/02/2023 11:54:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for access
-- ----------------------------
DROP TABLE IF EXISTS `access`;
CREATE TABLE `access`  (
  `s_id` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `access` int NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`, `cname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of access
-- ----------------------------
INSERT INTO `access` VALUES ('20301234567', 'F校区', 1);
INSERT INTO `access` VALUES ('20301234567', 'H校区', 1);
INSERT INTO `access` VALUES ('20301234567', 'J校区', 1);
INSERT INTO `access` VALUES ('20301234567', 'Z校区', 1);
INSERT INTO `access` VALUES ('20301234568', 'F校区', 1);
INSERT INTO `access` VALUES ('20301234568', 'H校区', 1);
INSERT INTO `access` VALUES ('20301234568', 'J校区', 1);
INSERT INTO `access` VALUES ('20301234568', 'Z校区', 1);
INSERT INTO `access` VALUES ('20301234569', 'F校区', 1);
INSERT INTO `access` VALUES ('20301234569', 'H校区', 1);
INSERT INTO `access` VALUES ('20301234569', 'J校区', 1);
INSERT INTO `access` VALUES ('20301234569', 'Z校区', 1);
INSERT INTO `access` VALUES ('20301234570', 'F校区', 1);
INSERT INTO `access` VALUES ('20301234570', 'H校区', 1);
INSERT INTO `access` VALUES ('20301234570', 'J校区', 1);
INSERT INTO `access` VALUES ('20301234570', 'Z校区', 1);
INSERT INTO `access` VALUES ('20301234571', 'F校区', 1);
INSERT INTO `access` VALUES ('20301234571', 'H校区', 1);
INSERT INTO `access` VALUES ('20301234571', 'J校区', 1);
INSERT INTO `access` VALUES ('20301234571', 'Z校区', 1);
INSERT INTO `access` VALUES ('20301234572', 'F校区', 1);
INSERT INTO `access` VALUES ('20301234572', 'H校区', 1);
INSERT INTO `access` VALUES ('20301234572', 'J校区', 1);
INSERT INTO `access` VALUES ('20301234572', 'Z校区', 1);
INSERT INTO `access` VALUES ('20301234573', 'F校区', 1);
INSERT INTO `access` VALUES ('20301234573', 'H校区', 1);
INSERT INTO `access` VALUES ('20301234573', 'J校区', 1);
INSERT INTO `access` VALUES ('20301234573', 'Z校区', 1);
INSERT INTO `access` VALUES ('20301234574', 'F校区', 1);
INSERT INTO `access` VALUES ('20301234574', 'H校区', 1);
INSERT INTO `access` VALUES ('20301234574', 'J校区', 1);
INSERT INTO `access` VALUES ('20301234574', 'Z校区', 1);
INSERT INTO `access` VALUES ('20301234575', 'F校区', 1);
INSERT INTO `access` VALUES ('20301234575', 'H校区', 1);
INSERT INTO `access` VALUES ('20301234575', 'J校区', 1);
INSERT INTO `access` VALUES ('20301234575', 'Z校区', 1);
INSERT INTO `access` VALUES ('20301234576', 'F校区', 1);
INSERT INTO `access` VALUES ('20301234576', 'H校区', 1);
INSERT INTO `access` VALUES ('20301234576', 'J校区', 1);
INSERT INTO `access` VALUES ('20301234576', 'Z校区', 1);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `a_id` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('2001', 'a1');
INSERT INTO `admin` VALUES ('2002', 'a2');
INSERT INTO `admin` VALUES ('2003', 'a3');
INSERT INTO `admin` VALUES ('2004', 'a4');
INSERT INTO `admin` VALUES ('2005', 'a5');
INSERT INTO `admin` VALUES ('2006', 'a6');
INSERT INTO `admin` VALUES ('2007', 'a7');
INSERT INTO `admin` VALUES ('2008', 'a8');
INSERT INTO `admin` VALUES ('2009', 'a9');
INSERT INTO `admin` VALUES ('2010', 'a10');

-- ----------------------------
-- Table structure for campus
-- ----------------------------
DROP TABLE IF EXISTS `campus`;
CREATE TABLE `campus`  (
  `campusId` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `cname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`campusId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of campus
-- ----------------------------
INSERT INTO `campus` VALUES (1, 'H校区');
INSERT INTO `campus` VALUES (2, 'J校区');
INSERT INTO `campus` VALUES (3, 'F校区');
INSERT INTO `campus` VALUES (4, 'Z校区');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `className` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deptId` int NOT NULL,
  `i_id` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`className`, `deptId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1班', 1, '01001');
INSERT INTO `class` VALUES ('1班', 2, '02001');
INSERT INTO `class` VALUES ('1班', 4, '01006');
INSERT INTO `class` VALUES ('2班', 3, '01005');
INSERT INTO `class` VALUES ('2班', 5, '01004');
INSERT INTO `class` VALUES ('3班', 1, '01003');
INSERT INTO `class` VALUES ('3班', 7, '01002');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `deptId` int NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `a_id` char(5) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`deptId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '软件学院', '2001');
INSERT INTO `department` VALUES (2, '计算机科学技术学院', '2002');
INSERT INTO `department` VALUES (3, '微电子学院', '2003');
INSERT INTO `department` VALUES (4, '信息科学与工程学院', '2004');
INSERT INTO `department` VALUES (5, '大数据学院', '2005');
INSERT INTO `department` VALUES (6, '法学院', '2006');
INSERT INTO `department` VALUES (7, '经济学院', '2007');
INSERT INTO `department` VALUES (8, '管理学院', '2008');
INSERT INTO `department` VALUES (9, '新闻学院', '2009');
INSERT INTO `department` VALUES (10, '基础医学院', '2010');

-- ----------------------------
-- Table structure for hjoutnal
-- ----------------------------
DROP TABLE IF EXISTS `hjoutnal`;
CREATE TABLE `hjoutnal`  (
  `s_id` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date` datetime NOT NULL,
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `temper` float NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`, `date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of hjoutnal
-- ----------------------------
INSERT INTO `hjoutnal` VALUES ('20301234567', '2023-02-17 10:01:18', '湖北', 36.8);
INSERT INTO `hjoutnal` VALUES ('20301234568', '2023-02-06 08:00:00', 'Shanghai', 36.7);
INSERT INTO `hjoutnal` VALUES ('20301234568', '2023-02-07 06:00:00', 'Shanghai', 36.4);
INSERT INTO `hjoutnal` VALUES ('20301234568', '2023-02-08 20:00:00', 'Shanghai', 36.5);
INSERT INTO `hjoutnal` VALUES ('20301234568', '2023-02-09 12:00:00', 'Shanghai', 36.8);
INSERT INTO `hjoutnal` VALUES ('20301234568', '2023-02-10 21:00:00', 'Shanghai', 37.1);
INSERT INTO `hjoutnal` VALUES ('20301234568', '2023-02-11 15:00:00', 'Shanghai', 36.4);
INSERT INTO `hjoutnal` VALUES ('20301234568', '2023-02-12 15:50:55', 'shanghai', 36.7);
INSERT INTO `hjoutnal` VALUES ('20301234568', '2023-02-15 15:30:17', '湖北', 37.1);
INSERT INTO `hjoutnal` VALUES ('20301234568', '2023-02-17 10:04:08', '上海', 36.9);
INSERT INTO `hjoutnal` VALUES ('20301234569', '2023-02-17 10:04:16', '上海', 36.9);
INSERT INTO `hjoutnal` VALUES ('20301234570', '2023-02-17 06:04:25', '上海', 37.1);
INSERT INTO `hjoutnal` VALUES ('20301234571', '2023-02-17 10:04:32', '北京', 36.9);
INSERT INTO `hjoutnal` VALUES ('20301234572', '2023-02-17 10:04:39', '上海', 36.9);
INSERT INTO `hjoutnal` VALUES ('20301234573', '2023-02-17 07:04:47', '北京', 37.1);
INSERT INTO `hjoutnal` VALUES ('20301234574', '2023-02-17 09:04:54', '上海', 36.9);

-- ----------------------------
-- Table structure for instructor
-- ----------------------------
DROP TABLE IF EXISTS `instructor`;
CREATE TABLE `instructor`  (
  `i_id` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`i_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of instructor
-- ----------------------------
INSERT INTO `instructor` VALUES ('01001', 'aaa');
INSERT INTO `instructor` VALUES ('01002', 'bbb');
INSERT INTO `instructor` VALUES ('01003', 'ccc');
INSERT INTO `instructor` VALUES ('01004', 'ddd');
INSERT INTO `instructor` VALUES ('01005', 'eee');
INSERT INTO `instructor` VALUES ('01006', 'rrrr');
INSERT INTO `instructor` VALUES ('02001', 'aaaa');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `s_id` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `campusId` int NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `action` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (1, '20301234567', 1, '2022-12-19 08:30:00', '离校');
INSERT INTO `log` VALUES (2, '20301234567', 2, '2022-12-19 14:54:39', '入校');
INSERT INTO `log` VALUES (3, '20301234568', 1, '2022-12-19 16:05:29', '入校');
INSERT INTO `log` VALUES (4, '20301234567', 2, '2022-12-19 20:20:20', '离校');
INSERT INTO `log` VALUES (5, '20301234569', 3, '2022-12-21 20:27:33', '入校');
INSERT INTO `log` VALUES (6, '20301234569', 3, '2022-12-21 22:28:21', '离校');
INSERT INTO `log` VALUES (7, '20301234570', 4, '2022-12-20 08:00:00', '离校');
INSERT INTO `log` VALUES (8, '20301234571', 4, '2022-12-22 08:55:24', '入校');
INSERT INTO `log` VALUES (9, '20301234572', 1, '2022-12-21 08:56:28', '离校');
INSERT INTO `log` VALUES (10, '20301234570', 3, '2022-12-21 10:56:15', '入校');
INSERT INTO `log` VALUES (11, '20301234574', 2, '2022-12-21 11:00:37', '入校');
INSERT INTO `log` VALUES (12, '20301234573', 2, '2022-12-21 11:57:27', '离校');
INSERT INTO `log` VALUES (13, '20301234567', 1, '2023-01-11 10:04:38', '入校');
INSERT INTO `log` VALUES (14, '20301234567', 1, '2023-01-10 12:05:17', '离校');
INSERT INTO `log` VALUES (15, '20301234567', 2, '2023-02-02 20:11:54', '入校');
INSERT INTO `log` VALUES (16, '20301234567', 2, '2023-02-11 11:06:18', '离校');
INSERT INTO `log` VALUES (17, '20301234567', 1, '2023-02-14 10:07:12', '入校');
INSERT INTO `log` VALUES (18, '20301234567', 1, '2023-02-14 11:07:12', '离校');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `s_id` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `className` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tellphone` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `adress` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `familyAdress` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `IdNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deptId` int NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('20301234567', '张三', '1班', '18112345678', '20301234567@fudan.edu.cn', 'xxx', 'xxx', 'xxxx', '二代身份证', 1);
INSERT INTO `student` VALUES ('20301234568', '李四', '1班', '18212345678', '20301234568@fudan.edu.cn', 'xxx', 'xxxx', 'xxxxxx', '二代身份证', 2);
INSERT INTO `student` VALUES ('20301234569', '张三1', '2班', '18112345678', '20301234569@fudan.edu.cn', 'xxx', 'xxx', 'xxxx', '二代身份证', 5);
INSERT INTO `student` VALUES ('20301234570', '张三2', '3班', '18112345678', '20301234570@fudan.edu.cn', 'xxx', 'xxx', 'xxxx', '二代身份证', 1);
INSERT INTO `student` VALUES ('20301234571', '李四1', '2班', '18112345678', '20301234571@fudan.edu.cn', 'xxx', 'xxx', 'xxxx', '二代身份证', 3);
INSERT INTO `student` VALUES ('20301234572', '李四2', '3班', '18112345678', '20301234572@fudan.edu.cn', 'xxx', 'xxx', 'xxxx', '二代身份证', 7);
INSERT INTO `student` VALUES ('20301234573', '张三3', '2班', '18112345678', '20301234573@fudan.edu.cn', 'xxx', 'xxx', 'xxxx', '二代身份证', 3);
INSERT INTO `student` VALUES ('20301234574', '李四3', '1班', '18112345678', '20301234574@fudan.edu.cn', 'xxx', 'xxx', 'xxxx', '二代身份证', 4);
INSERT INTO `student` VALUES ('20301234575', '甲', '1班', '18112345678', '20301234567@fudan.edu.cn', 'xxx', 'xxx', 'xxxx', '二代身份证', 1);
INSERT INTO `student` VALUES ('20301234576', '乙', '1班', '18112345678', '20301234567@fudan.edu.cn', 'xxx', 'xxx', 'xxxx', '二代身份证', 1);

-- ----------------------------
-- Table structure for t_come
-- ----------------------------
DROP TABLE IF EXISTS `t_come`;
CREATE TABLE `t_come`  (
  `t_id` int NOT NULL AUTO_INCREMENT,
  `s_id` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Addresses` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comedate` date NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_come
-- ----------------------------
INSERT INTO `t_come` VALUES (1, '20301234568', '假期返校', '湖北', '2023-02-20', 4, 'xxxxxxx', '2023-02-06 15:44:34');
INSERT INTO `t_come` VALUES (2, '20301234567', '假期返校', '上海', '2023-02-18', 1, NULL, '2023-02-15 15:45:43');
INSERT INTO `t_come` VALUES (4, '20301234568', '假期返校', '湖北', '2023-02-23', 2, NULL, '2023-02-15 16:11:57');
INSERT INTO `t_come` VALUES (5, '20301234569', '假期返校', '湖北', '2023-02-20', 4, 'xxxxxxx', '2023-02-06 15:44:34');
INSERT INTO `t_come` VALUES (6, '20301234569', '假期返校', '湖北', '2023-02-20', 4, 'xxxxxxx', '2023-02-06 15:44:34');
INSERT INTO `t_come` VALUES (7, '20301234569', '假期返校', '湖北', '2023-02-20', 4, 'xxxxxxx', '2023-02-06 15:44:34');
INSERT INTO `t_come` VALUES (8, '20301234575', '假期返校', '湖北', '2023-02-20', 4, 'xxxxxxx', '2023-02-06 15:44:34');
INSERT INTO `t_come` VALUES (9, '20301234575', '假期返校', '湖北', '2023-02-20', 2, 'xxxxxxx', '2023-02-06 15:44:34');
INSERT INTO `t_come` VALUES (10, '20301234575', '假期返校', '湖北', '2023-02-20', 4, 'xxxxxxx', '2023-02-06 15:44:34');
INSERT INTO `t_come` VALUES (11, '20301234576', '假期返校', '湖北', '2023-02-20', 4, 'asd', '2023-02-06 15:44:34');
INSERT INTO `t_come` VALUES (12, '20301234576', '假期返校', '湖北', '2023-02-20', 3, '信息不全', '2023-02-06 15:44:34');

-- ----------------------------
-- Table structure for t_leave
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave`  (
  `t_id` int NOT NULL AUTO_INCREMENT,
  `s_id` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `destAdress` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `leavedate` date NULL DEFAULT NULL,
  `comedate` date NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_leave
-- ----------------------------
INSERT INTO `t_leave` VALUES (1, '20301234567', 'xxxxxxx', 'yyy', '2023-02-07', '2023-02-13', 1, NULL, '2023-02-06 16:13:42');
INSERT INTO `t_leave` VALUES (2, '20301234568', 'awertyhjngbfvdcfsd', 'asdfghmn', '2023-02-15', '2023-02-21', 1, NULL, '2023-02-15 16:16:14');
INSERT INTO `t_leave` VALUES (3, '20301234569', 'awertyhjngbfvdcfsd', 'asdfghmn', '2023-02-20', '2023-02-21', 2, '', '2023-02-15 16:16:14');
INSERT INTO `t_leave` VALUES (4, '20301234567', 'xxxxxxx', 'yyy', '2023-02-08', '2023-02-22', 4, '', '2023-02-06 16:13:42');
INSERT INTO `t_leave` VALUES (5, '20301234570', 'xxxxxxx', 'yyy', '2023-02-08', '2023-02-22', 2, '', '2023-02-06 16:13:42');

-- ----------------------------
-- View structure for log_campus
-- ----------------------------
DROP VIEW IF EXISTS `log_campus`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `log_campus` AS select `log`.`s_id` AS `s_id`,`department`.`dept_name` AS `dept_name`,`campus`.`cname` AS `cname`,`log`.`time` AS `time` from (((`log` join `student` on((`log`.`s_id` = `student`.`s_id`))) join `campus` on((`log`.`campusId` = `campus`.`campusId`))) join `department` on((`student`.`deptId` = `department`.`deptId`)));

-- ----------------------------
-- View structure for student_h
-- ----------------------------
DROP VIEW IF EXISTS `student_h`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `student_h` AS select `student`.`s_id` AS `s_id` from `student` where `student`.`s_id` in (select distinct `hjoutnal`.`s_id` from `hjoutnal` where (`hjoutnal`.`date` >= (curdate() - interval 1 day))) is false;

-- ----------------------------
-- View structure for student_state
-- ----------------------------
DROP VIEW IF EXISTS `student_state`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `student_state` AS select `log`.`s_id` AS `s_id`,`t`.`time1` AS `time1`,`log`.`action` AS `action` from (`log` join (select `log`.`s_id` AS `s_id`,max(`log`.`time`) AS `time1` from `log` group by `log`.`s_id`) `t` on(((`log`.`s_id` = `t`.`s_id`) and (`t`.`time1` = `log`.`time`))));

-- ----------------------------
-- Event structure for updateAccess
-- ----------------------------
DROP EVENT IF EXISTS `updateAccess`;
delimiter ;;
CREATE EVENT `updateAccess`
ON SCHEDULE
EVERY '1' DAY STARTS '2023-02-18 00:00:00'
ON COMPLETION PRESERVE
DO UPDATE access set access=0 where s_id in (SELECT s_id from student_h)
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
