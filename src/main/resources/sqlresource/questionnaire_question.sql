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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `questionid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `surveyserialid` varchar(45) NOT NULL DEFAULT '' COMMENT '问卷序列号',
  `answerA` varchar(45) NOT NULL DEFAULT '',
  `answerB` varchar(45) NOT NULL DEFAULT '',
  `answerC` varchar(45) NOT NULL DEFAULT '',
  `answerD` varchar(45) NOT NULL DEFAULT '',
  `answerE` varchar(45) NOT NULL DEFAULT '',
  `answerF` varchar(45) NOT NULL DEFAULT '',
  `answerG` varchar(45) NOT NULL DEFAULT '',
  `answerAcount` int(11) NOT NULL DEFAULT '0',
  `answerBcount` int(11) NOT NULL DEFAULT '0',
  `answerCcount` int(11) NOT NULL DEFAULT '0',
  `answerDcount` int(11) NOT NULL DEFAULT '0',
  `answerEcount` int(11) NOT NULL DEFAULT '0',
  `answerFcount` int(11) NOT NULL DEFAULT '0',
  `answerGcount` int(11) NOT NULL DEFAULT '0',
  `remark` varchar(45) DEFAULT '' COMMENT '备注',
  `createdatetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '备注',
  `updatedatetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `questionname` varchar(45) NOT NULL,
  PRIMARY KEY (`questionid`),
  UNIQUE KEY `surveyid_UNIQUE` (`questionid`),
  UNIQUE KEY `surveyserialid_UNIQUE` (`surveyserialid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='问题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'sdvsvsf','红','橙','黄','绿','','','',0,0,0,0,0,0,0,'','2018-11-27 15:05:34','2018-11-27 15:08:31','颜色'),(3,'41d49f8e-8c28-44b8-b416-ba607c89225a','数学','英语','语文','化学','','','',0,0,0,0,0,0,0,'','2018-11-29 21:11:31','2018-12-02 21:35:20','最喜欢的课程'),(5,'41d49f8e-8c28-44b8-b416-ba607c895asc','蓝色','红色','黄色','绿色','紫色','','',0,0,0,0,0,0,0,'','2018-12-02 21:36:35','2018-12-02 21:36:35','最喜欢的颜色');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-02 22:05:17
