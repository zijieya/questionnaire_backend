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
-- Table structure for table `serveyResult`
--

DROP TABLE IF EXISTS `serveyResult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serveyResult` (
  `serveyresultid` int(11) NOT NULL AUTO_INCREMENT,
  `serveyresultserialid` varchar(45) NOT NULL DEFAULT '',
  `surveyserialid` varchar(45) NOT NULL DEFAULT '' COMMENT '问卷序列号',
  `answererserialId` varchar(45) NOT NULL DEFAULT '' COMMENT '回答者者序列号',
  `answer` varchar(45) NOT NULL DEFAULT '' COMMENT '答案 逗号拼接',
  `result` varchar(45) DEFAULT '' COMMENT '问卷结果 ',
  `remark` varchar(45) DEFAULT '' COMMENT '备注',
  `createdatetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '备注',
  `updatedatetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`serveyresultid`),
  UNIQUE KEY `serveyresultid_UNIQUE` (`serveyresultid`),
  UNIQUE KEY `serveyresultserialid_UNIQUE` (`serveyresultserialid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='问卷结果表 方便注册用户查看已回答的问卷信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serveyResult`
--

LOCK TABLES `serveyResult` WRITE;
/*!40000 ALTER TABLE `serveyResult` DISABLE KEYS */;
INSERT INTO `serveyResult` VALUES (1,'asdasacscaca','41d49f8e-8c28-44b8-b416-ba607c89225a','abcd','3-A,5-C','','','2018-12-02 21:46:22','2018-12-02 21:46:22');
/*!40000 ALTER TABLE `serveyResult` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-02 22:05:33
