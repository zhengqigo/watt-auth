CREATE DATABASE `auth` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `auth`;


DROP TABLE IF EXISTS `mealset`;
CREATE TABLE `mealset` (
  `id` bigint(20) DEFAULT NULL,
  `code` varchar(16) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `role_name` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='套餐表';


DROP TABLE IF EXISTS `object`;
CREATE TABLE `object` (
  `id` bigint(20) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `refer` bigint(20) DEFAULT NULL COMMENT '* or id',
  `remark` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对象表';
insert  into `object`(`id`,`name`,`type`,`refer`,`remark`) values (NULL,NULL,NULL,NULL,NULL);


DROP TABLE IF EXISTS `object_role`;
CREATE TABLE `object_role` (
  `object_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `permitted` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对象-角色关系表';
insert  into `object_role`(`object_id`,`role_id`,`permitted`) values (1,1,NULL);


DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(16) DEFAULT NULL,
  `method` varchar(16) DEFAULT NULL,
  `url` varchar(32) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';
insert  into `permission`(`id`,`name`,`method`,`url`,`remark`) values (1,'admin','*','/admin',NULL);


DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role` (
  `permission_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `permitted` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限-角色关系表';
insert  into `permission_role`(`permission_id`,`role_id`,`permitted`) values (1,1,'N');


DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile` (
  `id` bigint(20) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  `active` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `type` char(1) DEFAULT NULL COMMENT 'MENU,BUTTON,LINK etc',
  `remark` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';


DROP TABLE IF EXISTS `resource_role`;
CREATE TABLE `resource_role` (
  `resource_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `permitted` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源-角色关系表';
insert  into `resource_role`(`resource_id`,`role_id`,`permitted`) values (1,1,NULL);


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(16) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
insert  into `role`(`id`,`name`,`remark`) values (1,'admin',NULL);


DROP TABLE IF EXISTS `role_rule`;
CREATE TABLE `role_rule` (
  `role_id` bigint(20) NOT NULL,
  `rule_id` varchar(16) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色-规则关系表';


DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `id` bigint(20) NOT NULL,
  `name` varchar(16) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规则表';


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(16) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
insert  into `user`(`id`,`name`,`password`) values (1,'admin','admin');


DROP TABLE IF EXISTS `user_mealset`;
CREATE TABLE `user_mealset` (
  `user_id` bigint(20) NOT NULL,
  `mealset_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户-套餐关系表';
insert  into `user_mealset`(`user_id`,`mealset_id`) values (1,1);