/*
 Navicat Premium Data Transfer

 Source Server         : localhost-本地mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : db_springbootmybatis

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 12/02/2019 16:00:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_user_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_user_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `resource_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `resource_type` int(11) DEFAULT NULL,
  `resource_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES (2, 'a9c370fa2d224a9c87474ebf701ae433', 0, 0, NULL, 'system', '2019-01-17 16:06:59', NULL, NULL, 'user/getpage', 0, 'user/getpage');
INSERT INTO `t_resource` VALUES (3, '0cd034a77ada40648af1ed711b1e5857', 0, 0, NULL, 'system', '2019-01-17 16:07:06', NULL, NULL, 'user/save', 0, 'user/save');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_user_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_user_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (4, '822354ee1e61445c98c0001b4fba7bc3', 0, 0, NULL, 'system', '2019-01-17 16:05:14', NULL, NULL, '超级管理员');

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_resource
-- ----------------------------
INSERT INTO `t_role_resource` VALUES (3, 4, 2);
INSERT INTO `t_role_resource` VALUES (4, 4, 3);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_user_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_user_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (23, '0edcbe3a22a8409eafee4bd503310264', 0, 0, NULL, 'system', '2019-01-17 15:24:12', NULL, NULL, 'admin', 'MENCRjA4NEIyNDMzNjU1QzdDQzVEOEVBQzJGOTYyQTQ=');
INSERT INTO `t_user` VALUES (24, '8bd23f80fd6044d6a7ed42e8f65f7fc1', 0, 0, NULL, 'system', '2019-01-17 16:20:23', NULL, NULL, 'admin2', 'NDFEM0U4RkM3MDAwQzFDRTYzREJCQkY4M0M3MDIwQUQ=');
INSERT INTO `t_user` VALUES (25, 'b9a4b49c53b6421ba41678efc70dce6a', 0, 0, NULL, 'system', '2019-01-17 17:12:31', NULL, NULL, '黄洪', 'QzJEOUNGQkZCN0MyNEQyNEE0ODdCNDNGQ0ZGREY2OUE=');
INSERT INTO `t_user` VALUES (26, '327bb610bfdd42e0aa0efd9fa206e273', 0, 0, NULL, 'system', '2019-01-18 13:15:55', NULL, NULL, '黄洪2', 'MTQxMTlCOTU0MjdCNDkzN0UzMDdFODhFMzNERjcwQTk=');
INSERT INTO `t_user` VALUES (27, '1844d35a93924f0580715121a6ce0e59', 0, 0, NULL, 'system', '2019-01-18 13:47:06', NULL, NULL, '黄洪3', 'MTFBNjkxMEQyRjQ1NTE5RTU1NzdBODg3NEQwNjZFQjc=');
INSERT INTO `t_user` VALUES (28, '221f505fab47418e814c30296e52c086', 0, 0, NULL, 'system', '2019-01-18 13:48:45', NULL, NULL, '黄洪4', 'QUQwRTk1RUIxMUJCMUU4MjQ4NjRBOUU5NUE0RkU3REM=');
INSERT INTO `t_user` VALUES (30, '7fe1f9f40c474a2aacc8912c74d4b4aa', 0, 0, NULL, '23', '2019-01-18 14:34:02', NULL, NULL, '黄洪5', 'N0U3QjFFNjlDNkQ4NEFGMTlEMkRERTZCRUE2RjU3MzY=');
INSERT INTO `t_user` VALUES (31, 'ed2142fe0b04431a95a059f795ec8666', 0, 0, NULL, '23', '2019-01-18 15:11:28', NULL, NULL, '黄洪10', 'OTEwNjM2ODU2MTNGQjhEODg2NDAxNDI5QkYwQTg4Mzc=');
INSERT INTO `t_user` VALUES (32, '98c7651511494762b2d892867f38dfc8', 0, 0, NULL, '23', '2019-01-18 15:20:58', NULL, NULL, '黄洪11', 'MzkxNzlENzEyMUU3OTAyMDlDRkIwREIyOTgxMkU0Qjk=');
INSERT INTO `t_user` VALUES (33, 'ff8f9961a9054664839229097f6c7c9c', 0, 0, NULL, '23', '2019-01-18 17:01:09', NULL, NULL, '黄洪15', 'NTg3MEE3NTFCNTdGMkQzQTE3RTAzQTZDNzJENDFBNEQ=');
INSERT INTO `t_user` VALUES (34, 'c52c153d03a34fa6abfd14c6598b4e8a', 0, 0, NULL, '23', '2019-01-21 13:51:09', NULL, NULL, '黄洪17', 'NkE1MjlFOTNGMzI3MDc3RUIwMjI3QkFDMzI2RjU4NEM=');
INSERT INTO `t_user` VALUES (35, 'c2dde13fa8264ffbbcc99045ab681ba1', 0, 0, NULL, '23', '2019-01-21 13:57:39', NULL, NULL, '黄洪18', 'NkRDRkI1OTBGMEQ5Qzk2REM2QTk1MTU3M0ZGRTk4MzE=');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 23, 4);

SET FOREIGN_KEY_CHECKS = 1;
