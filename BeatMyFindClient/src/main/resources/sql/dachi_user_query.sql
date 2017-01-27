CREATE DATABASE  IF NOT EXISTS `dachi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dachi`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dachi
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `user_query`
--

DROP TABLE IF EXISTS `user_query`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_query` (
  `query_id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `query_status` varchar(255) DEFAULT NULL,
  `query_text` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`query_id`),
  KEY `FK9ioqkan91hy7nbna8m4vheq41` (`user_id`),
  CONSTRAINT `FK9ioqkan91hy7nbna8m4vheq41` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_query`
--

LOCK TABLES `user_query` WRITE;
/*!40000 ALTER TABLE `user_query` DISABLE KEYS */;
INSERT INTO `user_query` VALUES (1,'2017-01-10 12:42:54','OPEN','Tablemate available in amazon for 500rs',1),(2,'2017-01-10 12:42:54','OPEN','New deal in walmart! vaccuum cleaner for 30$',1),(3,'2016-11-17 00:00:00','OPEN','bought microwave for 80$ in amazon. Now seeing the same one for 65$ in jet.com',3),(5,'2017-01-10 12:42:54','OPEN','Room lamps available for 5$ in jet',1),(6,'2016-12-15 22:11:25','OPEN','Beat this find!! Visio 32 inch smart tv for 130$',2),(7,'2017-01-10 15:47:27','CLOSED','Amazon prime subscription free for an year. Check their website now!',2),(8,'2017-01-10 15:39:14','CLOSED','',2),(9,'2017-01-10 12:42:54','OPEN','Do you have a tmobile discount in Chicago',1);
/*!40000 ALTER TABLE `user_query` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-27 10:43:27
