-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 173.82.232.134    Database: questionnaire
-- ------------------------------------------------------
-- Server version	5.7.24-log

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
-- Table structure for table `servey`
--

DROP TABLE IF EXISTS `servey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servey` (
  `serveyid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `surveyserialid` varchar(45) NOT NULL DEFAULT '' COMMENT '问卷序列号',
  `creatorserialId` varchar(45) NOT NULL DEFAULT '' COMMENT '创建者序列号',
  `title` varchar(45) NOT NULL DEFAULT '' COMMENT '标题',
  `tag` varchar(45) DEFAULT '' COMMENT '标签（多个标签用逗号分隔开）',
  `remark` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  `total` int(11) NOT NULL DEFAULT '0' COMMENT '作答次数',
  `createdatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '备注',
  `updatedatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`serveyid`),
  UNIQUE KEY `serveyid_UNIQUE` (`serveyid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='问卷表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servey`
--

LOCK TABLES `servey` WRITE;
/*!40000 ALTER TABLE `servey` DISABLE KEYS */;
INSERT INTO `servey` VALUES (1,'sdvsvsf','c516ea89-38f2-4375-b5ab-4ed6d9d7d6af','sad','adasd','s',0,'2018-11-25 22:46:24','2018-11-25 22:46:24'),(14,'41d49f8e-8c28-44b8-b416-ba607c89225a','c516ea89-38f2-4375-b5ab-4ed6d9d7d6af','问卷1','','',0,'2018-11-29 21:11:31','2018-11-29 21:11:31');
/*!40000 ALTER TABLE `servey` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-02 22:05:23
