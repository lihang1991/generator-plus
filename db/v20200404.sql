CREATE TABLE `sys_config` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `data_source_type` varchar(32) DEFAULT NULL COMMENT '数据库类型mysql、oracle、sqlserver、postgresql',
  `data_source_url` varchar(120) DEFAULT NULL COMMENT '数据库链接地址',
  `user_name` varchar(32) DEFAULT NULL COMMENT '数据库帐号名',
  `password` varchar(32) DEFAULT NULL COMMENT '数据库密码',
  `use_local_date` tinyint(1) DEFAULT 1 COMMENT '是否使用jdk8的localdatetime',
  `use_lombok` tinyint(1) DEFAULT 1 COMMENT '是否使用lombok',
  `package_name` varchar(32) DEFAULT NULL COMMENT '包名',
  `module_name` varchar(32) DEFAULT NULL COMMENT '包名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成规则配置'