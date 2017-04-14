CREATE DATABASE  IF NOT EXISTS dachi /*!40100 DEFAULT CHARACTER SET utf8 */;
USE dachi;
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
-- Table structure for table user_comments
--

DROP TABLE IF EXISTS user_comments;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE user_comments (
  comment_id int(11) NOT NULL AUTO_INCREMENT,
  comment_text varchar(255) DEFAULT NULL,
  creation_date datetime DEFAULT current_timestamp,
  updation_date datetime DEFAULT current_timestamp, 
  user_id int(11) DEFAULT NULL,
  query_id int(11) DEFAULT NULL,
  user_query tinyblob,
  comment_status varchar(255) DEFAULT 'ACTIVE',
  PRIMARY KEY (comment_id),
  KEY FK_USER_COMMENT (user_id),
  KEY FK_QUERY_COMMENT (query_id),
  CONSTRAINT FK_QUERY_COMMENT FOREIGN KEY (query_id) REFERENCES user_query (query_id),
  CONSTRAINT FK_USER_COMMENT FOREIGN KEY (user_id) REFERENCES user_details (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table user_comments
--

LOCK TABLES user_comments WRITE;
/*!40000 ALTER TABLE user_comments DISABLE KEYS */;
INSERT INTO user_comments VALUES (1,'flipkart is selling it for 430','2016-11-17 00:00:00',1,1,NULL),(2,'in snapdeal for 425','2016-11-17 00:00:00',2,1,NULL),(3,'best is jet. Available for 400!','2016-11-17 00:00:00',3,1,NULL),(4,'Its not a new deal. Its there in their website for couple of days now.','2016-11-17 00:00:00',1,2,NULL),(5,'Looks like all deals are over',NULL,1,1,NULL),(6,'No, i see amazon deals are still present','2016-12-14 14:39:39',1,1,NULL),(7,'Even jet, even their deals are still on','2016-12-14 14:48:21',1,1,NULL),(8,'testing again, whether username shows up','2016-12-14 14:52:36',1,1,NULL),(9,'new test','2016-12-14 14:55:22',NULL,1,NULL),(10,'testing one more time','2016-12-14 15:00:25',3,1,NULL),(11,'test 123','2016-12-14 15:08:38',3,1,NULL),(12,'Latest test with a lengthy string to see the behaviour on the screen. Hope it folds gracefully to next line.','2016-12-14 21:41:13',2,1,NULL),(13,'Test again 123 123 1234','2016-12-14 23:02:40',2,1,NULL),(14,'Deal is no longer there','2016-12-14 23:14:05',2,2,NULL),(16,'No, they had removed that deal few days back. Now, they have introduced it','2016-12-15 08:47:31',2,2,NULL),(20,'Try writing to amazon. If that doesnot work, return amazon product and but it from jet','2016-12-15 10:15:14',2,3,NULL),(21,'Now, even amazon is showing the same product for 65$','2016-12-15 10:15:46',2,3,NULL),(22,'its time you write to amazon :)','2016-12-15 10:16:21',1,3,NULL),(26,'Looks like they are removing the deal and posting it back again. Should i be conserned about the product quality?','2016-12-15 11:17:55',3,2,NULL),(27,'Don\'t worry about the quality of the product. Usually they sell good product. In case you still feel quality is not good, you can return it back. You will have 2 weeks time.','2016-12-15 11:28:26',1,2,NULL),(28,'Check if its a fake ad. Doesn\'t sound realistic','2016-12-15 21:57:36',1,5,NULL),(29,'yes till tomorrow afternoon you can get a quick discount','2016-12-16 19:26:13',1,9,NULL),(30,'How to get more discount','2016-12-16 19:26:23',1,9,NULL),(31,'wow this has alternate colors','2016-12-16 19:26:33',1,9,NULL),(32,'more comments','2016-12-16 19:26:39',1,9,NULL),(33,'atleast this is smooth ui','2016-12-16 19:26:46',1,9,NULL),(34,'good use of angular js. keep it up','2016-12-16 19:26:58',1,9,NULL),(35,'Cant find this deal anymore :( missed it','2017-01-03 14:12:13',3,6,NULL),(36,'Checking if the page can scroll','2017-01-03 14:14:01',3,3,NULL);
/*!40000 ALTER TABLE user_comments ENABLE KEYS */;
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
