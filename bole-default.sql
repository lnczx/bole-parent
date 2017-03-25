-- MySQL dump 10.13  Distrib 5.7.16, for osx10.11 (x86_64)
--
-- Host: localhost    Database: bole_db
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
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
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0 = 代理  1 = 客服  2 = 超级管理员',
  `open_id` varchar(128) NOT NULL COMMENT '微信ID',
  `nick_name` varchar(64) NOT NULL COMMENT '昵称',
  `pay_account` varchar(128) NOT NULL COMMENT '支付宝账号',
  `head_img` varchar(255) NOT NULL COMMENT '头像',
  `game_id` varchar(32) NOT NULL COMMENT '游戏ID',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `invite_code` varchar(32) NOT NULL COMMENT '邀请码',
  `level` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '用户等级，最高6级',
  `p_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '上级ID',
  `p_game_id` varchar(32) NOT NULL COMMENT '上级游戏ID',
  `lft` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT ' 树形左值',
  `rgt` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '树形右值',
  `score_money` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
  `score` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '钻石数',
  `score_last_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '最后充值时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `active` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否激活 0 = 未激活 1 = 激活',
  `add_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间戳',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间戳',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `game_id` (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'','','','','000000','d20de65510f1690056650ea84c18a766','',0,0,'',1,20,0.00,0.00,0,1,1,1490353401,1490353401),(2,1,'','','','','001654','6d0d2621c95de44eb6d56aa31bf7ad34','',1,0,'',0,0,0.00,0.00,1490357690,1,1,1490353589,1490434078),(3,1,'','','','','001655','6d0d2621c95de44eb6d56aa31bf7ad34','',1,0,'',0,0,0.00,0.00,0,1,1,1490355268,1490355268),(4,2,'','','bole@126.com','','001521','d1a8192d98c2cc4426e08ba73c91421e','wp8o4p',1,1,'000000',2,13,0.00,0.00,0,1,1,1490355476,1490355692),(5,2,'','','bole@126.com','','001522','d1a8192d98c2cc4426e08ba73c91421e','wpaoj6',1,1,'000000',14,15,0.00,0.00,0,1,1,1490355488,1490355745),(6,2,'','','bole@126.com','','001523','d1a8192d98c2cc4426e08ba73c91421e','wpsof3',1,1,'000000',16,17,0.00,0.00,0,1,1,1490355492,1490355778),(7,2,'','','bole@126.com','','001524','d1a8192d98c2cc4426e08ba73c91421e','wp2oqh',1,1,'000000',18,19,0.00,0.00,0,1,1,1490355497,1490355811),(8,0,'','','bole@126.com','','001530','670b14728ad9902aecba32e22fa4f6bd','wp7oyn',1,4,'001521',3,12,0.00,0.00,0,1,1,1490355977,1490356005),(9,0,'','','bole@126.com','','001540','670b14728ad9902aecba32e22fa4f6bd','wpropu',2,8,'001530',4,11,0.00,0.00,1490376688,1,1,1490356060,1490376688),(10,0,'','','bole@126.com','','001541','670b14728ad9902aecba32e22fa4f6bd','wp4obb',1,9,'001540',5,6,0.00,0.00,1490370547,1,1,1490356092,1490370547),(11,0,'','','bole@126.com','','001542','670b14728ad9902aecba32e22fa4f6bd','wpvopm',1,9,'001540',7,8,0.00,0.00,1490433357,1,1,1490356134,1490433357),(12,0,'','','bole@126.com','','001543','670b14728ad9902aecba32e22fa4f6bd','wpyopk',1,9,'001540',9,10,0.00,0.00,1490434078,1,1,1490356159,1490434078);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_level_log`
--

DROP TABLE IF EXISTS `user_level_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_level_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `log_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0 = 自动升级  1= 手动升级',
  `level_pre` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '升级前',
  `level_after` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '升级后',
  `remarks` varchar(255) NOT NULL DEFAULT '0' COMMENT '备注',
  `admin_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '操作人',
  `add_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间戳',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_level_log`
--

LOCK TABLES `user_level_log` WRITE;
/*!40000 ALTER TABLE `user_level_log` DISABLE KEYS */;
INSERT INTO `user_level_log` VALUES (1,9,0,1,2,'下级有3人达到1, 升级为2',0,1490356181);
/*!40000 ALTER TABLE `user_level_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_level_stat`
--

DROP TABLE IF EXISTS `user_level_stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_level_stat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `level` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0 =  代表全部 其他数据代表各个级别的代理人数',
  `total` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '总人数',
  `add_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间戳',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间戳',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `user_id_2` (`user_id`,`level`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_level_stat`
--

LOCK TABLES `user_level_stat` WRITE;
/*!40000 ALTER TABLE `user_level_stat` DISABLE KEYS */;
INSERT INTO `user_level_stat` VALUES (1,1,0,9,1490355476,1490356181),(2,1,1,8,1490355476,1490356181),(3,1,2,1,1490355476,1490356181),(4,1,3,0,1490355476,1490356181),(5,1,4,0,1490355476,1490356181),(6,1,5,0,1490355476,1490356181),(7,1,6,0,1490355476,1490356181),(8,4,0,5,1490355977,1490356181),(9,4,1,4,1490355977,1490356181),(10,4,2,1,1490355977,1490356181),(11,4,3,0,1490355977,1490356181),(12,4,4,0,1490355977,1490356181),(13,4,5,0,1490355977,1490356181),(14,4,6,0,1490355977,1490356181),(15,8,0,4,1490356060,1490356181),(16,8,1,3,1490356060,1490356181),(17,8,2,1,1490356060,1490356181),(18,8,3,0,1490356060,1490356181),(19,8,4,0,1490356060,1490356181),(20,8,5,0,1490356060,1490356181),(21,8,6,0,1490356060,1490356181),(22,9,0,3,1490356092,1490356181),(23,9,1,3,1490356092,1490356181),(24,9,2,0,1490356092,1490356181),(25,9,3,0,1490356092,1490356181),(26,9,4,0,1490356092,1490356181),(27,9,5,0,1490356092,1490356181),(28,9,6,0,1490356092,1490356181);
/*!40000 ALTER TABLE `user_level_stat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_logined`
--

DROP TABLE IF EXISTS `user_logined`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_logined` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL DEFAULT '0',
  `mobile` char(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `login_from` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `login_ip` int(11) unsigned NOT NULL DEFAULT '0',
  `add_time` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户登录记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_logined`
--

LOCK TABLES `user_logined` WRITE;
/*!40000 ALTER TABLE `user_logined` DISABLE KEYS */;
INSERT INTO `user_logined` VALUES (1,1,'',1,2130706433,1490353568),(2,1,'',1,2130706433,1490355260),(3,4,'',1,2130706433,1490355823),(4,4,'',1,2130706433,1490355948),(5,4,'',1,2130706433,1490357658),(6,2,'',1,2130706433,1490370693),(7,2,'',1,2130706433,1490370991),(8,2,'',1,2130706433,1490371163),(9,4,'',1,2130706433,1490371175),(10,8,'',1,2130706433,1490374947),(11,2,'',1,2130706433,1490375445),(12,3,'',1,2130706433,1490375476),(13,3,'',1,2130706433,1490375649),(14,3,'',1,2130706433,1490376120),(15,2,'',1,2130706433,1490376124),(16,8,'',1,2130706433,1490376229),(17,2,'',1,2130706433,1490376486),(18,2,'',1,2130706433,1490376665),(19,8,'',1,2130706433,1490376739),(20,10,'',1,2130706433,1490376761),(21,10,'',1,2130706433,1490376845),(22,4,'',1,2130706433,1490377314),(23,4,'',1,2130706433,1490377349),(24,4,'',1,2130706433,1490377391),(25,4,'',1,2130706433,1490377563),(26,4,'',1,2130706433,1490377732),(27,4,'',1,2876189850,1490378519),(28,8,'',1,2875906636,1490433106),(29,2,'',1,2875906636,1490433254),(30,8,'',1,2875906636,1490433419),(31,9,'',1,2875906636,1490433491),(32,8,'',1,2875906636,1490433544),(33,12,'',1,2875906636,1490433926),(34,2,'',1,2875906636,1490433985),(35,8,'',1,2875906636,1490434127),(36,8,'',1,2876189850,1490435935),(37,8,'',1,2130706433,1490436194),(38,8,'',1,2130706433,1490436361),(39,2,'',1,2130706433,1490436389),(40,2,'',1,2130706433,1490439601),(41,4,'',1,2130706433,1490439838),(42,2,'',1,2130706433,1490439927),(43,8,'',1,2130706433,1490440498),(44,2,'',1,2130706433,1490440767),(45,2,'',1,2130706433,1490443756);
/*!40000 ALTER TABLE `user_logined` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_score_cash`
--

DROP TABLE IF EXISTS `user_score_cash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_score_cash` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `score_cash` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '提现数值',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0 = 申请中  1 = 已提现  2 = 关闭',
  `add_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间戳',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间戳',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `user_id_2` (`user_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_score_cash`
--

LOCK TABLES `user_score_cash` WRITE;
/*!40000 ALTER TABLE `user_score_cash` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_score_cash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_score_detail`
--

DROP TABLE IF EXISTS `user_score_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_score_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id_from` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '来源ID',
  `user_id_to` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '目的ID',
  `score` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '数值',
  `score_money` decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `score_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0 = 赠送，  1= 付款   2= 返利 3 = 领取.',
  `score_pre` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '充值前数值',
  `score_after` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '充值后数值',
  `link_detail_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '返利的对应ID',
  `link_back_level` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '第几层返利',
  `link_back_ratio` decimal(3,2) NOT NULL DEFAULT '0.00' COMMENT '返利的比例',
  `remarks` varchar(255) NOT NULL COMMENT '备注',
  `add_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间戳',
  PRIMARY KEY (`id`),
  KEY `user_id_from` (`user_id_from`),
  KEY `user_id_to` (`user_id_to`),
  KEY `user_id_from_2` (`user_id_from`,`user_id_to`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_score_detail`
--

LOCK TABLES `user_score_detail` WRITE;
/*!40000 ALTER TABLE `user_score_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_score_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-25 21:53:15
