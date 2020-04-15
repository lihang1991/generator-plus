CREATE TABLE `sys_config` (
  `ID` varchar(32) PRIMARY KEY AUTO_INCREMENT,
  `data_source_type` varchar(32) NULL,
  `data_source_url` varchar(120) NULL,
  `user_name` varchar(32)  NULL,
  `password` varchar(32)  NULL,
  `use_local_date` tinyint(1),
  `use_lombok` tinyint(1),
  `package_name` varchar(32)  NULL ,
  `module_name` varchar(32)  NULL
);

CREATE TABLE `sys_datasource` (
  `ID` varchar(32) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(32) NULL,
  `url` varchar(120) NULL,
  `driver_class_name` varchar(32)  NULL,
  `username` varchar(32)  NULL,
  `password` varchar(32),
  `data_source_type` varchar(32)
);