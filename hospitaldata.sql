/*
Navicat MySQL Data Transfer

Source Server         : sdf
Source Server Version : 50562
Source Host           : 47.96.238.64:3306
Source Database       : hospitaldata

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-11-26 19:12:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `spread` int(255) DEFAULT NULL COMMENT '0不展开1展开',
  `target` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `available` int(255) DEFAULT NULL COMMENT '0不可用1可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', 'COSHIP管理系统', '', '1', null, '&#xe68e;', '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '系统管理', '', '0', '', '&#xe716;', '1');
INSERT INTO `sys_menu` VALUES ('3', '2', '菜单管理', '../toMenuManager', '0', null, '&#xe60f;', '1');
INSERT INTO `sys_menu` VALUES ('4', '2', '角色管理', '../toLoadAllRole', '0', '', '&#xe66f;', '1');
INSERT INTO `sys_menu` VALUES ('5', '2', '用户管理', '../toLoadAllUser', '0', null, '&#xe770;', '1');
INSERT INTO `sys_menu` VALUES ('6', '2', '图标管理', '../icon', '0', null, '&#xe655;', '1');
INSERT INTO `sys_menu` VALUES ('7', '2', '数据源监控', '../druid', '0', null, '&#xe857;', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) DEFAULT NULL,
  `roledesc` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`roleid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '拥有所有菜单权限', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `rid` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  PRIMARY KEY (`rid`,`mid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '7');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`rid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) DEFAULT NULL,
  `identity` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `sex` int(255) DEFAULT NULL COMMENT '0女1男',
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `type` int(255) DEFAULT '2' COMMENT '1，超级管理员,2，系统用户',
  `available` int(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '412365199601236544', '超级管理员', '1', '武汉', '13183380740', '6af4d08340b548cbcce38353d9bcaab4', 'CEO', '1', '1', 'd21fd4');

DROP TABLE IF EXISTS `sys_menu_button`;
CREATE TABLE `sys_menu_button` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL,
  `buttonPath` varchar(100) DEFAULT NULL,
  `buttonDesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

INSERT INTO `sys_menu_button` VALUES ('1', '3','menu/menuRight/searchBtn','查询');
INSERT INTO `sys_menu_button` VALUES ('2', '3','menu/menuRight/resetBtn','重置');
INSERT INTO `sys_menu_button` VALUES ('3', '3','menu/menuRight/addBtn','增加');
INSERT INTO `sys_menu_button` VALUES ('4', '3','menu/menuRight/editBtn','编辑');
INSERT INTO `sys_menu_button` VALUES ('5', '3','menu/menuRight/delBtn','删除');

INSERT INTO `sys_menu_button` VALUES ('6', '4','role/roleManager/searchBtn','查询');
INSERT INTO `sys_menu_button` VALUES ('7', '4','role/roleManager/resetBtn','重置');
INSERT INTO `sys_menu_button` VALUES ('8', '4','role/roleManager/addBtn','增加');
INSERT INTO `sys_menu_button` VALUES ('9', '4','role/roleManager/editBtn','编辑');
INSERT INTO `sys_menu_button` VALUES ('10', '4','role/roleManager/delBtn','删除');
INSERT INTO `sys_menu_button` VALUES ('11', '4','role/roleManager/assignMenuBtn','分配菜单');

INSERT INTO `sys_menu_button` VALUES ('12', '5','user/userManager/searchBtn','查询');
INSERT INTO `sys_menu_button` VALUES ('13', '5','user/userManager/resetBtn','重置');
INSERT INTO `sys_menu_button` VALUES ('14', '5','user/userManager/addBtn','增加');
INSERT INTO `sys_menu_button` VALUES ('15', '5','user/userManager/editBtn','编辑');
INSERT INTO `sys_menu_button` VALUES ('16', '5','user/userManager/delBtn','删除');
INSERT INTO `sys_menu_button` VALUES ('17', '5','user/userManager/resetPwdBtn','重置密码');
INSERT INTO `sys_menu_button` VALUES ('18', '5','user/userManager/assignRoleBtn','分配角色');





DROP TABLE IF EXISTS `sys_role_button`;
CREATE TABLE `sys_role_button` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  `mbid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
