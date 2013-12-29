DROP SCHEMA IF EXISTS `doc_hub` ;
CREATE DATABASE  IF NOT EXISTS `doc_hub` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `doc_hub`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: doc_hub
-- ------------------------------------------------------
-- Server version	5.6.10

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
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `url` varchar(256) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `document_id` int(10) unsigned NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_attachment_document1_idx` (`document_id`),
  KEY `fk_attachment_user1_idx` (`created_by`),
  CONSTRAINT `fk_attachment_document1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_attachment_user1` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

-- -----------------------------------------------------
-- Table `doc_hub`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `doc_hub`.`comment` ;

CREATE TABLE IF NOT EXISTS `doc_hub`.`comment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` INT NULL DEFAULT 0,
  `content` TEXT NOT NULL,
  `rank` TINYINT NULL,
  `document_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `published` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_document1_idx` (`document_id` ASC),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_document1`
    FOREIGN KEY (`document_id`)
    REFERENCES `doc_hub`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `doc_hub`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_property`
--

DROP TABLE IF EXISTS `comment_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_property` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value` text,
  `comment_property_type_id` int(10) unsigned NOT NULL,
  `comment_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_property_comment_property_type1_idx` (`comment_property_type_id`),
  KEY `fk_comment_property_comment1_idx` (`comment_id`),
  CONSTRAINT `fk_comment_property_comment_property_type1` FOREIGN KEY (`comment_property_type_id`) REFERENCES `comment_property_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_property_comment1` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_property`
--

LOCK TABLES `comment_property` WRITE;
/*!40000 ALTER TABLE `comment_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_property_type`
--

DROP TABLE IF EXISTS `comment_property_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_property_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_property_type`
--

LOCK TABLES `comment_property_type` WRITE;
/*!40000 ALTER TABLE `comment_property_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_property_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `author` varchar(256) DEFAULT NULL,
  `year` datetime DEFAULT NULL,
  `pages` varchar(45) DEFAULT 'all pages',
  `abstract` text NOT NULL,
  `keywords` varchar(64) NOT NULL,
  `publisher` varchar(128) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `document_type_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `published` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_document_document_type1_idx` (`document_type_id`),
  KEY `fk_document_user1_idx` (`user_id`),
  CONSTRAINT `fk_document_document_type1` FOREIGN KEY (`document_type_id`) REFERENCES `document_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (3,'使用 Spring Security 保护 Web 应用的安全','成 富, 软件工程师, IBM 中国软件开发中心','2010-12-24 00:00:00','','安全一直是 Web 应用开发中非常重要的一个方面。从安全的角度来说，需要考虑用户认证和授权两个方面。为 Web 应用增加安全方面的能力并非一件简单的事情，需要考虑不同的认证和授权机制。Spring Security 为使用 Spring 框架的 Web 应用提供了良好的支持。本文将详细介绍如何使用 Spring Security 框架为 Web 应用提供安全支持。','Spring Security','','http://www.ibm.com/developerworks/cn/java/j-lo-springsecurity/',NULL,NULL,8,1,0),(4,'The IP Network Address Translator (NAT)','K. Egevang,P. Francis','1994-12-24 00:00:00','','The two most compelling problems facing the IP Internet are IP\naddress depletion and scaling in routing. Long-term and short-term\nsolutions to these problems are being developed. The short-term\nsolution is CIDR (Classless InterDomain Routing). The long-term\nsolutions consist of various proposals for new internet protocols\nwith larger addresses.\nIt is possible that CIDR will not be adequate to maintain the IP\nInternet until the long-term solutions are in place. This memo\nproposes another short-term solution, address reuse, that complements\nCIDR or even makes it unnecessary. The address reuse solution is to\nplace Network Address Translators (NAT) at the borders of stub\ndomains. Each NAT box has a table consisting of pairs of local IP\naddresses and globally unique addresses. The IP addresses inside the\nstub domain are not globally unique. They are reused in other\ndomains, thus solving the address depletion problem. The globally\nunique IP addresses are assigned according to current CIDR address\nallocation schemes. CIDR solves the scaling problem. The main\nadvantage of NAT is that it can be installed without changes to\nrouters or hosts. This memo presents a preliminary design for NAT,\nand discusses its pros and cons.','NAT','','',NULL,NULL,5,1,0),(5,'统计学-基本概念和方法','吴喜之,程博柳,林旭啤, 全莉萍,钟文瑄，熊怀羽  译','2000-12-24 00:00:00','2~480','这本统叶学敬材在设计和写作「都相当独特。该书是为了满足当代学生时统计日雄增t\n而又尚未满踵的榕在，使他们能够熟鲸地掌擦统计信息的特性。对于希壤他们的学生能愤点\n统计知识的教师们来说，这本书很有梅益。然问，仅凭这一本书，是不可能使学生们变成统叶\n幸事的。\n在过击JL年姐，统HI自且已纷战政府机构积满班尘的档靠中和学*计算中心、照解散出3住\n了n 从阔事关于健康改革和周防的政策到对于预期寿命ω婚姻、堕贿、教育相体育的态度、统计\n俗且在很茹宽阔扮演了囊整角色。统计{商品经常校报纸、翔在、广娥和电视节目中出现，它们\n甚至偶尔会在MTV 和卡通片中做点辙。统叶也曾量遭到了我们的教育课程中。在小学教室略\n和博士生讨论班巾.蜿叶f自息已成沟敏宵的基本将衍。\n理智统计有这么多的应用，但是我们很难说大家对于统计信息革仅接费而且有f 较多的\n了解。2!l人们辑到…个耐f究销播时，他们如何判晰结论是份正确9 他们是份合问逃个研究中\n的变量是如何定义的9 用了什么样的统计方法?什么是\"统叶显著\"的结果?所报告的结果有\n什么样的不足?这艘闷顿正最我们在本将中讨论的一部什内畴。M然，观解了统叶学的1:擦\n概在以后，大事才能够明白那些专门鼓捣数字的人们都干了些付么，井对他们结果进行评价。\n立起本11脱R由于G叫mund R. Jvc:N! en 开设的一门躁的讲义，目的是满Ji!人们时蜿计筒息日\n益增恒的需要。该课是Swarthmo陀学院为使大学文科的学生能够迎接21 世纪的挑战而开设\n的一蕉列课根之币。开设班峡课税的思想是为了使学生们能够开阔眼界，而不是拘崩于某…\n学科的直杂在址。这些课程试图使学生们了解一个领域的主要思想是如何联AI 于现事世界\n的。在许事万丽，统I1 \'学着越来正最迫樊课赖的现想选择尤一。原骨统计学可能是…门令人\n因辈革的、自我膨胀的、神植莫测的学科，但它也能够成为理解许多其它学科的一把钥跑。i躁和\n{统讨学\"统计思想》摘最被设计成产使这种理解力的。事实证明，埠门课非常景就坝，其规\n模每年都在扩大d 随着时间的流逝，这I\'J啤的讲义变得跑祟越精练租车窗，激络构成了本书的屠础。','统计学','Springer','',NULL,NULL,2,1,0);
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_has_document_property`
--

DROP TABLE IF EXISTS `document_has_document_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_has_document_property` (
  `document_id` int(10) unsigned NOT NULL,
  `document_property_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_document_has_document_property_document_property1_idx` (`document_property_id`),
  KEY `fk_document_has_document_property_document1_idx` (`document_id`),
  CONSTRAINT `fk_document_has_document_property_document1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_has_document_property_document_property1` FOREIGN KEY (`document_property_id`) REFERENCES `document_property` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_has_document_property`
--

LOCK TABLES `document_has_document_property` WRITE;
/*!40000 ALTER TABLE `document_has_document_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_has_document_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_property`
--

DROP TABLE IF EXISTS `document_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_property` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value` varchar(64) DEFAULT NULL,
  `document_property_type_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_document_property_document_property_type1_idx` (`document_property_type_id`),
  CONSTRAINT `fk_document_property_document_property_type1` FOREIGN KEY (`document_property_type_id`) REFERENCES `document_property_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_property`
--

LOCK TABLES `document_property` WRITE;
/*!40000 ALTER TABLE `document_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_property_type`
--

DROP TABLE IF EXISTS `document_property_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_property_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_property_type`
--

LOCK TABLES `document_property_type` WRITE;
/*!40000 ALTER TABLE `document_property_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_property_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_type`
--

DROP TABLE IF EXISTS `document_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_type`
--

LOCK TABLES `document_type` WRITE;
/*!40000 ALTER TABLE `document_type` DISABLE KEYS */;
INSERT INTO `document_type` VALUES (1,'未分类'),(2,'图书'),(3,'图书章节'),(4,'期刊'),(5,'学术论文'),(6,'会议'),(7,'技术报告'),(8,'在线资源');
/*!40000 ALTER TABLE `document_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_type_has_document_property_type`
--

DROP TABLE IF EXISTS `document_type_has_document_property_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_type_has_document_property_type` (
  `document_type_id` int(10) unsigned NOT NULL,
  `document_property_type_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_document_type_has_document_property_type_document_proper_idx` (`document_property_type_id`),
  KEY `fk_document_type_has_document_property_type_document_type1_idx` (`document_type_id`),
  CONSTRAINT `fk_document_type_has_document_property_type_document_type1` FOREIGN KEY (`document_type_id`) REFERENCES `document_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_type_has_document_property_type_document_property1` FOREIGN KEY (`document_property_type_id`) REFERENCES `document_property_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_type_has_document_property_type`
--

LOCK TABLES `document_type_has_document_property_type` WRITE;
/*!40000 ALTER TABLE `document_type_has_document_property_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `document_type_has_document_property_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reference`
--

DROP TABLE IF EXISTS `reference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reference` (
  `source` int(10) unsigned NOT NULL,
  `destination` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `reference_type_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_document_has_document_document2_idx` (`destination`),
  KEY `fk_document_has_document_document1_idx` (`source`),
  KEY `fk_reference_reference_type1_idx` (`reference_type_id`),
  CONSTRAINT `fk_document_has_document_document1` FOREIGN KEY (`source`) REFERENCES `document` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_has_document_document2` FOREIGN KEY (`destination`) REFERENCES `document` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reference_reference_type1` FOREIGN KEY (`reference_type_id`) REFERENCES `reference_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference`
--

LOCK TABLES `reference` WRITE;
/*!40000 ALTER TABLE `reference` DISABLE KEYS */;
/*!40000 ALTER TABLE `reference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reference_type`
--

DROP TABLE IF EXISTS `reference_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reference_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference_type`
--

LOCK TABLES `reference_type` WRITE;
/*!40000 ALTER TABLE `reference_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `reference_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tagging`
--

DROP TABLE IF EXISTS `tagging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tagging` (
  `document_id` int(10) unsigned NOT NULL,
  `tag_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tagged_by` int(10) unsigned NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_document_has_tag_tag1_idx` (`tag_id`),
  KEY `fk_document_has_tag_document1_idx` (`document_id`),
  KEY `fk_tagging_user1_idx` (`tagged_by`),
  CONSTRAINT `fk_document_has_tag_document1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_has_tag_tag1` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tagging_user1` FOREIGN KEY (`tagged_by`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tagging`
--

LOCK TABLES `tagging` WRITE;
/*!40000 ALTER TABLE `tagging` DISABLE KEYS */;
/*!40000 ALTER TABLE `tagging` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `salt` varchar(12) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `permission_level` tinyint(4) DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','null','administrator',NULL,0,'2013-12-24 22:36:25','2013-12-24 22:36:25',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_log`
--

DROP TABLE IF EXISTS `user_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_log` (
  `user_id` int(10) unsigned NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` smallint(6) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_user_log_user1` (`user_id`),
  CONSTRAINT `fk_user_log_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_log`
--

LOCK TABLES `user_log` WRITE;
/*!40000 ALTER TABLE `user_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-24 22:45:46
