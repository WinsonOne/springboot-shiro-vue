/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : springboot-shiro

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-09-30 14:28:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource` (
                                   `role_resource_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色资源关联ID，自增主键',
                                   `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                   `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
                                   `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID',
                                   `create_time` datetime NOT NULL COMMENT '创建时间',
                                   PRIMARY KEY (`role_resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色资源关系表';

-- ----------------------------
-- Records of t_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource` (
                                  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源ID，自增ID',
                                  `resource_name` varchar(64) NOT NULL COMMENT '资源名称',
                                  `resource_type` int(11) NOT NULL COMMENT '资源类型，1目录，2菜单，3按钮',
                                  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级资源ID',
                                  `parent_ids` varchar(128) DEFAULT NULL COMMENT '所有上级资源ID',
                                  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
                                  `path` varchar(128) DEFAULT NULL COMMENT '访问路径',
                                  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
                                  `state` int(11) NOT NULL DEFAULT '1' COMMENT '资源状态，1有效，0禁用',
                                  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除，1是，0否',
                                  `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID',
                                  `create_time` datetime NOT NULL COMMENT '创建时间',
                                  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人ID',
                                  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                                  `perm` varchar(128) DEFAULT NULL COMMENT '资源对应的权限',
                                  `sort` int(11) NOT NULL COMMENT '资源排序',
                                  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统资源表';

-- ----------------------------
-- Records of t_sys_resource
-- ----------------------------
INSERT INTO `t_sys_resource` VALUES ('1', '系统管理', '1', null, null, 'setting', 'system', null, '1', '0', '1', '2019-09-30 14:12:34', null, null, null, '1');
INSERT INTO `t_sys_resource` VALUES ('2', '用户管理', '2', '1', '1', 'user', 'user', null, '1', '0', '1', '2019-09-30 14:13:18', null, null, null, '2');
INSERT INTO `t_sys_resource` VALUES ('3', '角色管理', '2', '1', '1', 'role', 'role', null, '1', '0', '1', '2019-09-30 14:14:21', null, null, null, '3');
INSERT INTO `t_sys_resource` VALUES ('4', '资源管理', '2', '1', '1', 'resource', 'resource', null, '1', '0', '1', '2019-09-30 14:22:56', null, null, null, '4');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
                              `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID，自增主键',
                              `role_name` varchar(64) NOT NULL COMMENT '角色名称',
                              `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
                              `state` int(11) NOT NULL DEFAULT '1' COMMENT '角色状态，1有效，0禁用',
                              `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除，1是，0否',
                              `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID',
                              `create_time` datetime NOT NULL COMMENT '创建时间',
                              `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人ID',
                              `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                              PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色表';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', '测试角色', null, '1', '0', '1', '2019-09-27 19:57:26', null, null);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID，自增主键',
                              `username` varchar(64) NOT NULL COMMENT '用户名，登录账户',
                              `nickname` varchar(64) NOT NULL COMMENT '用户昵称，默认用户名',
                              `password` varchar(64) NOT NULL COMMENT '密文存储的密码',
                              `salt` varchar(32) NOT NULL COMMENT '加密使用的盐值',
                              `mobile` varchar(11) NOT NULL COMMENT '手机号码',
                              `email` varchar(128) NOT NULL COMMENT '电子邮箱',
                              `state` int(11) NOT NULL DEFAULT '1' COMMENT '用户状态，1有效，0禁用',
                              `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除',
                              `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID',
                              `create_time` datetime NOT NULL COMMENT '创建时间',
                              `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人ID',
                              `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户表';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
                               `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色关联ID',
                               `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                               `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                               `create_user_id` bigint(20) NOT NULL COMMENT '创建人ID',
                               `create_time` datetime NOT NULL COMMENT '创建时间',
                               PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关系表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
