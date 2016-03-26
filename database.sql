-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: localhost    Database: SecuritySystem
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `config_id` int(11) DEFAULT '1',
  `config_type` varchar(45) NOT NULL DEFAULT '',
  `area_id` varchar(45) NOT NULL DEFAULT '',
  `room_type` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `user_id` varchar(45) DEFAULT '',
  `system_id` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`config_type`,`system_id`,`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disaster_log`
--

DROP TABLE IF EXISTS `disaster_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disaster_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) DEFAULT NULL,
  `disaster_type` varchar(45) DEFAULT NULL,
  `area_id` varchar(45) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `Action_taken` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disaster_log`
--

LOCK TABLES `disaster_log` WRITE;
/*!40000 ALTER TABLE `disaster_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `disaster_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `date` date NOT NULL,
  `from_time` time DEFAULT NULL,
  `to_time` time DEFAULT NULL,
  `system_id` varchar(45) NOT NULL,
  `config_type` varchar(45) NOT NULL DEFAULT 'fire',
  PRIMARY KEY (`date`,`system_id`,`config_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES ('2015-09-02','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-02','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-03','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-03','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-04','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-04','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-05','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-05','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-06','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-06','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-07','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-07','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-08','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-08','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-09','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-09','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-10','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-10','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-11','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-11','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-12','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-12','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-13','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-13','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-14','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-14','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-15','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-15','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-16','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-16','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-17','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-17','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-18','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-18','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-19','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-19','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-20','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-20','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-21','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-21','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-22','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-22','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-23','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-23','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-24','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-24','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-25','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-25','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-26','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-26','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-27','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-27','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-28','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-28','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-29','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-29','13:48:46','23:48:46','security_system_1','sensor'),('2015-09-30','13:48:46','23:48:46','security_system_1','fire_alarm'),('2015-09-30','13:48:46','23:48:46','security_system_1','sensor');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_log`
--

DROP TABLE IF EXISTS `schedule_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule_log` (
  `schedule_id` int(11) NOT NULL,
  `system_id` varchar(45) DEFAULT NULL,
  `from_date` varchar(45) DEFAULT NULL,
  `to_date` varchar(45) DEFAULT NULL,
  `config-type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_log`
--

LOCK TABLES `schedule_log` WRITE;
/*!40000 ALTER TABLE `schedule_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user2system`
--

DROP TABLE IF EXISTS `user2system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user2system` (
  `user_id` varchar(45) NOT NULL,
  `system_id` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`,`system_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user2system`
--

LOCK TABLES `user2system` WRITE;
/*!40000 ALTER TABLE `user2system` DISABLE KEYS */;
INSERT INTO `user2system` VALUES ('user','security_system_1');
/*!40000 ALTER TABLE `user2system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_id` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT 'admin',
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES ('rahmi','Marufa','Rahmi','San Jose','mrahmi@scu.edu','scu','primary','0123456789'),('user','John','Doe','Santa Clara, CA','tasneema.sust@gmail.com','scu','admin','0987654321');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-02 16:23:28
