CREATE TABLE IF NOT EXISTS `_cluster` (  `uuid` int NOT NULL,  `name` varchar(45) NOT NULL,  `endpoint` varchar(100) NOT NULL,  `registeredon` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,  `status` varchar(1) NOT NULL,  `token` varchar(100) DEFAULT NULL,`environment` varchar(20) NOT NULL,  PRIMARY KEY (`uuid`))

CREATE TABLE IF NOT EXISTS `_user` (  `uuid` int NOT NULL,  `email` VARCHAR(255) NULL,  `fullname` VARCHAR(64) NOT NULL,`cluster_user_id` VARCHAR(32) NULL,  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,   `status` VARCHAR(1) NOT NULL,  PRIMARY KEY (`uuid`)) 



