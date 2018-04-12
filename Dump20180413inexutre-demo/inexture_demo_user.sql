-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: inexture_demo
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` bigint(10) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(24) NOT NULL,
  `mobile` decimal(12,0) NOT NULL,
  `gender` bit(1) NOT NULL,
  `dob` date NOT NULL,
  `role` int(11) NOT NULL,
  `tech` int(11) NOT NULL,
  PRIMARY KEY (`iduser`),
  KEY `role_idx` (`role`),
  KEY `tech_idx` (`tech`),
  CONSTRAINT `role` FOREIGN KEY (`role`) REFERENCES `role_master` (`idrole_master`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tech` FOREIGN KEY (`tech`) REFERENCES `tech_master` (`idtech_master`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=315 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (262,'jigar jigo','jigo','jigar@jigo.com','WDnJzT2D030IqXOcWH6xtw==',9825663355,'\0','1999-12-21',1,1),(265,'chiragyo','bharwad','chirag@gmail.com','v1jXM0WZDNyZnsnIrZdlWA==',9622534511,'\0','1997-06-16',2,8),(288,'paresh','chaudhary','pkc@gmail.com','gnVZoc0H0htCZWLUH5RKvQ==',9866533323,'\0','2018-03-15',2,2),(298,'meet','chaudhary','meetchaudhary97@gmail.com','qDn8xHD6jART4pzOSSQq9A==',8141740922,'\0','1997-02-21',2,1),(299,'hardik','mithwani','heloadsf@glkj.com','zJAuhuWtvngQfcGVJF3fUA==',6656565656,'\0','2018-04-01',2,7),(302,'nwazuddin','sidiqui','nwazuddin@gmail.com','gDzNC/97HsLYVxeya6hRvQ==',6629923232,'\0','1970-04-01',2,1),(303,'asdfadsf','asdfasdf','ggggggg@dddd.ll','Drb9q+oZXfRQFFMLFt3a3A==',3243243243,'\0','2018-03-25',2,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-13  1:41:54
