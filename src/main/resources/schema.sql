CREATE TABLE IF NOT EXISTS `_cluster` (  `uuid` int NOT NULL,  `name` varchar(45) NOT NULL,  `endpoint` varchar(100) NOT NULL,  `registeredon` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,  `status` varchar(1) NOT NULL,  `token` varchar(1000) DEFAULT NULL,`environment` varchar(20) NOT NULL,  PRIMARY KEY (`uuid`))

CREATE TABLE IF NOT EXISTS `_user` (  `uuid` int NOT NULL,  `email` varchar(255) DEFAULT NULL,  `fullname` varchar(64) NOT NULL,  `cluster_user_id` varchar(32) DEFAULT NULL,  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,  `status` varchar(1) NOT NULL,  PRIMARY KEY (`uuid`)) 



CREATE TABLE IF NOT EXISTS  `_costlist` (  `uuid` int NOT NULL,  `clusterId` int NOT NULL,  `currency` varchar(45) NOT NULL,  `resourceUnit` varchar(45) NOT NULL,  `costPerMonth` decimal(10,0) DEFAULT NULL,  `lastUpdated` date DEFAULT NULL,  `resourceType` varchar(45) NOT NULL,  PRIMARY KEY (`uuid`))