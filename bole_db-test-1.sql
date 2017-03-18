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
  `head_img` varchar(255) NOT NULL COMMENT '头像',
  `game_id` varchar(32) NOT NULL COMMENT '游戏ID',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `invite_code` varchar(32) NOT NULL COMMENT '邀请码',
  `level` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '用户等级，最高6级',
  `p_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '上级ID',
  `p_game_id` varchar(32) NOT NULL COMMENT '上级游戏ID',
  `p_code` varchar(128) NOT NULL COMMENT '树形gameId',
  `score` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
  `score_last_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '最后充值时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `active` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否激活 0 = 未激活 1 = 激活',
  `add_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间戳',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间戳',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `game_id` (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'','','','001652','d1a8192d98c2cc4426e08ba73c91421e','',1,0,'','',0.00,0,1,1,1489570243,1489570243),(3,2,'','','','001653','d1a8192d98c2cc4426e08ba73c91421e','',1,0,'','',0.00,0,1,1,1489643503,1489643503),(4,1,'','','','001654','6d0d2621c95de44eb6d56aa31bf7ad34','',1,0,'','',20.00,1489668101,1,0,1489668101,1489668101),(19,0,'','','','002654','670b14728ad9902aecba32e22fa4f6bd','e55oc2',2,0,'0','',0.00,0,1,1,1489863594,1489865871),(20,0,'','','','002655','670b14728ad9902aecba32e22fa4f6bd','e5iog2',1,19,'002654','002654--002655',0.00,0,1,1,1489863918,1489865614),(21,0,'','','','003601','670b14728ad9902aecba32e22fa4f6bd','89aozn',1,20,'002655','002654-002655--003601',0.00,0,1,1,1489864055,1489865994),(22,0,'','','','003602','','89sov6',1,20,'002655','002654-002655--003602',0.00,0,1,0,1489864088,1489864088),(23,0,'','','','003603','','892okf',1,20,'002655','002654-002655--003603',0.00,0,1,0,1489864094,1489864094),(24,0,'','','','002656','670b14728ad9902aecba32e22fa4f6bd','e5koen',1,19,'002654','002654--002656',0.00,0,1,1,1489864110,1489865746),(25,0,'','','','002657','670b14728ad9902aecba32e22fa4f6bd','e53o8i',1,19,'002654','002654--002657',0.00,0,1,1,1489864122,1489865840),(26,0,'','','','003604','','89do3n',1,24,'002656','002654-002656--003604',0.00,0,1,0,1489864138,1489864138),(27,0,'','','','003605','','89zo85',1,24,'002656','002654-002656--003605',0.00,0,1,0,1489864143,1489864143),(28,0,'','','','003606','','89xoil',1,24,'002656','002654-002656--003606',0.00,0,1,0,1489864146,1489864146),(29,0,'','','','003607','','899oil',1,25,'002657','002654-002657--003607',0.00,0,1,0,1489864154,1489864154),(30,0,'','','','003608','','89coay',1,25,'002657','002654-002657--003608',0.00,0,1,0,1489864158,1489864158),(31,0,'','','','003609','','897o3g',1,25,'002657','002654-002657--003609',0.00,0,1,0,1489864161,1489864161);
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
INSERT INTO `user_level_log` VALUES (1,19,0,1,2,'下级有3人达到1, 升级为2',0,1489865878);
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
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_level_stat`
--

LOCK TABLES `user_level_stat` WRITE;
/*!40000 ALTER TABLE `user_level_stat` DISABLE KEYS */;
INSERT INTO `user_level_stat` VALUES (1,19,0,3,1489863594,1489865878),(2,19,1,3,1489863594,1489865878),(3,19,2,0,1489863594,1489865878),(4,19,3,0,1489863594,1489865878),(5,19,4,0,1489863594,1489865878),(6,19,5,0,1489863594,1489865878),(7,19,6,0,1489863594,1489865878),(8,20,0,0,1489863919,1489865994),(9,20,1,0,1489863919,1489865994),(10,20,2,0,1489863919,1489865994),(11,20,3,0,1489863919,1489865994),(12,20,4,0,1489863919,1489865994),(13,20,5,0,1489863919,1489865994),(14,20,6,0,1489863919,1489865994),(15,21,0,0,1489864055,1489864055),(16,21,1,0,1489864055,1489864055),(17,21,2,0,1489864055,1489864055),(18,21,3,0,1489864055,1489864055),(19,21,4,0,1489864055,1489864055),(20,21,5,0,1489864055,1489864055),(21,21,6,0,1489864055,1489864055),(22,22,0,0,1489864088,1489864088),(23,22,1,0,1489864088,1489864088),(24,22,2,0,1489864088,1489864088),(25,22,3,0,1489864088,1489864088),(26,22,4,0,1489864088,1489864088),(27,22,5,0,1489864088,1489864088),(28,22,6,0,1489864088,1489864088),(29,23,0,0,1489864094,1489864094),(30,23,1,0,1489864094,1489864094),(31,23,2,0,1489864094,1489864094),(32,23,3,0,1489864094,1489864094),(33,23,4,0,1489864094,1489864094),(34,23,5,0,1489864094,1489864094),(35,23,6,0,1489864094,1489864094),(36,24,0,0,1489864110,1489864110),(37,24,1,0,1489864110,1489864110),(38,24,2,0,1489864110,1489864110),(39,24,3,0,1489864110,1489864110),(40,24,4,0,1489864110,1489864110),(41,24,5,0,1489864110,1489864110),(42,24,6,0,1489864110,1489864110),(43,25,0,0,1489864122,1489864122),(44,25,1,0,1489864122,1489864122),(45,25,2,0,1489864123,1489864123),(46,25,3,0,1489864123,1489864123),(47,25,4,0,1489864123,1489864123),(48,25,5,0,1489864123,1489864123),(49,25,6,0,1489864123,1489864123),(50,26,0,0,1489864138,1489864138),(51,26,1,0,1489864138,1489864138),(52,26,2,0,1489864138,1489864138),(53,26,3,0,1489864138,1489864138),(54,26,4,0,1489864138,1489864138),(55,26,5,0,1489864138,1489864138),(56,26,6,0,1489864138,1489864138),(57,27,0,0,1489864143,1489864143),(58,27,1,0,1489864143,1489864143),(59,27,2,0,1489864143,1489864143),(60,27,3,0,1489864143,1489864143),(61,27,4,0,1489864143,1489864143),(62,27,5,0,1489864143,1489864143),(63,27,6,0,1489864143,1489864143),(64,28,0,0,1489864146,1489864146),(65,28,1,0,1489864146,1489864146),(66,28,2,0,1489864146,1489864146),(67,28,3,0,1489864146,1489864146),(68,28,4,0,1489864146,1489864146),(69,28,5,0,1489864146,1489864146),(70,28,6,0,1489864147,1489864147),(71,29,0,0,1489864154,1489864154),(72,29,1,0,1489864154,1489864154),(73,29,2,0,1489864154,1489864154),(74,29,3,0,1489864154,1489864154),(75,29,4,0,1489864154,1489864154),(76,29,5,0,1489864154,1489864154),(77,29,6,0,1489864154,1489864154),(78,30,0,0,1489864158,1489864158),(79,30,1,0,1489864158,1489864158),(80,30,2,0,1489864158,1489864158),(81,30,3,0,1489864158,1489864158),(82,30,4,0,1489864158,1489864158),(83,30,5,0,1489864158,1489864158),(84,30,6,0,1489864158,1489864158),(85,31,0,0,1489864161,1489864161),(86,31,1,0,1489864161,1489864161),(87,31,2,0,1489864161,1489864161),(88,31,3,0,1489864162,1489864162),(89,31,4,0,1489864162,1489864162),(90,31,5,0,1489864162,1489864162),(91,31,6,0,1489864162,1489864162);
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户登录记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_logined`
--

LOCK TABLES `user_logined` WRITE;
/*!40000 ALTER TABLE `user_logined` DISABLE KEYS */;
INSERT INTO `user_logined` VALUES (1,1,'',1,2130706433,1489749704),(2,1,'',1,2130706433,1489832900),(3,1,'',1,2130706433,1489837749),(4,1,'',1,2130706433,1489840027),(5,1,'',1,2130706433,1489840504),(6,1,'',1,2130706433,1489851540),(7,1,'',1,2130706433,1489851814),(8,1,'',1,2130706433,1489852319),(9,1,'',1,2130706433,1489852434),(10,1,'',1,2130706433,1489852582),(11,1,'',1,2130706433,1489862078),(12,1,'',1,2130706433,1489862647),(13,1,'',1,2130706433,1489862668),(14,1,'',1,2130706433,1489863541),(15,1,'',1,2130706433,1489863899),(16,19,'',1,2130706433,1489864363),(17,19,'',1,2130706433,1489864550),(18,19,'',1,2130706433,1489864677),(19,1,'',1,2130706433,1489865642),(20,1,'',1,2130706433,1489866014);
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
  `score_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0 = 赠送，  1= 付款   2= 返利提现.',
  `score_pre` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '充值前数值',
  `score_after` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '充值后数值',
  `link_detail_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '返利的对应ID',
  `remarks` varchar(255) NOT NULL COMMENT '备注',
  `add_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间戳',
  PRIMARY KEY (`id`),
  KEY `user_id_from` (`user_id_from`),
  KEY `user_id_to` (`user_id_to`),
  KEY `user_id_from_2` (`user_id_from`,`user_id_to`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_score_detail`
--

LOCK TABLES `user_score_detail` WRITE;
/*!40000 ALTER TABLE `user_score_detail` DISABLE KEYS */;
INSERT INTO `user_score_detail` VALUES (1,1,4,10.00,0,0.00,10.00,0,'测试给客服',1489722918),(2,1,4,10.00,0,0.00,10.00,0,'测试赠送金额',1489737991);
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

-- Dump completed on 2017-03-19  4:41:24
