CREATE TABLE IF NOT EXISTS `_cluster` (  `uuid` int NOT NULL,  `name` varchar(45) NOT NULL,  `endpoint` varchar(100) NOT NULL,  `registeredon` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,  `status` varchar(1) NOT NULL,  `token` varchar(1000) DEFAULT NULL,`environment` varchar(20) NOT NULL,  PRIMARY KEY (`uuid`))

CREATE TABLE IF NOT EXISTS `_user` (  `uuid` int NOT NULL,  `email` varchar(255) DEFAULT NULL,  `fullname` varchar(64) NOT NULL,  `cluster_user_id` varchar(32) DEFAULT NULL,  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,  `status` varchar(1) NOT NULL,  PRIMARY KEY (`uuid`)) 



CREATE TABLE IF NOT EXISTS `_costlist` (  `uuid` int NOT NULL,  `clusterId` int NOT NULL,  `currency` varchar(45) NOT NULL,  `cpuunit` varchar(45) NOT NULL,  `lastUpdated` date DEFAULT NULL,  `memoryunit` varchar(45) NOT NULL,  `storageunit` varchar(45) DEFAULT NULL,  `timelengthunit` varchar(45) DEFAULT NULL,  `cpucost` float DEFAULT NULL,  `momorycost` float DEFAULT NULL,  `storagecost` float DEFAULT NULL,  PRIMARY KEY (`uuid`)) 

  