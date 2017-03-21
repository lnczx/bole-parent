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
  `lft` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT ' 树形左值',
  `rgt` mediumint(8) unsigned NOT NULL DEFAULT '0' COMMENT '树形右值',
  `score` decimal(9,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
  `score_last_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '最后充值时间',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `active` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否激活 0 = 未激活 1 = 激活',
  `add_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间戳',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间戳',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `game_id` (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'','','','001652','d1a8192d98c2cc4426e08ba73c91421e','',1,0,'',1,52,0.00,0,1,1,1489570243,1489570243),(2,2,'','','','001653','d1a8192d98c2cc4426e08ba73c91421e','',1,0,'',0,0,0.00,0,1,1,1489643503,1489643503),(3,1,'','','','001654','6d0d2621c95de44eb6d56aa31bf7ad34','',1,0,'',0,0,1000.00,1490030997,1,1,1490026550,1490030997),(4,1,'','','','001655','6d0d2621c95de44eb6d56aa31bf7ad34','',1,0,'',0,0,0.00,0,1,1,1490026585,1490026585),(5,0,'','','','002654','670b14728ad9902aecba32e22fa4f6bd','e55oib',1,1,'0',2,25,0.00,0,1,1,1490028359,1490031087),(6,0,'','','','002655','670b14728ad9902aecba32e22fa4f6bd','e5io3g',1,5,'002654',3,8,0.00,0,1,1,1490028427,1490031161),(7,0,'','','','003601','670b14728ad9902aecba32e22fa4f6bd','89aow6',1,6,'002655',4,5,0.00,0,1,1,1490029957,1490031186),(8,0,'','','','003602','','89so9f',1,6,'002655',6,7,0.00,0,1,0,1490029983,1490029983),(9,0,'','','','002657','','e53oub',1,5,'002654',9,16,0.00,0,1,0,1490030009,1490030009),(10,0,'','','','002656','','e5ko25',1,5,'002654',17,24,0.00,0,1,0,1490030057,1490030057),(11,0,'','','','003607','','899oea',1,9,'002657',10,11,0.00,0,1,0,1490030094,1490030094),(12,0,'','','','003608','','89colp',1,9,'002657',12,13,0.00,0,1,0,1490030099,1490030099),(13,0,'','','','003609','','897o3b',1,9,'002657',14,15,0.00,0,1,0,1490030112,1490030112),(14,0,'','','','003604','','89dofw',1,10,'002656',18,19,0.00,0,1,0,1490030126,1490030126),(15,0,'','','','003605','','89zoq3',1,10,'002656',20,21,0.00,0,1,0,1490030132,1490030132),(16,0,'','','','003606','','89xo8n',1,10,'002656',22,23,0.00,0,1,0,1490030138,1490030138),(17,0,'','','','002660','','e5uohk',1,1,'0',26,51,0.00,0,1,0,1490030198,1490030198),(18,0,'','','','002665','','e5yogv',1,17,'002660',27,34,0.00,0,1,0,1490030227,1490030227),(19,0,'','','','002666','','e5lo2f',1,17,'002660',35,42,0.00,0,1,0,1490030236,1490030236),(20,0,'','','','002667','','e5to6v',1,17,'002660',43,50,0.00,0,1,0,1490030253,1490030253),(21,0,'','','','003661','','8cgox5',1,18,'002665',28,29,0.00,0,1,0,1490030268,1490030268),(22,0,'','','','003662','','8choem',1,18,'002665',30,31,0.00,0,1,0,1490030274,1490030274),(23,0,'','','','003663','','87qohv',1,18,'002665',32,33,0.00,0,1,0,1490030279,1490030279),(24,0,'','','','003664','','87wokf',1,19,'002666',36,37,0.00,0,1,0,1490030298,1490030298),(25,0,'','','','003665','','87eo9k',1,19,'002666',38,39,0.00,0,1,0,1490030303,1490030303),(26,0,'','','','003666','','878ovt',1,19,'002666',40,41,0.00,0,1,0,1490030307,1490030307),(27,0,'','','','003667','','87aoni',1,20,'002667',44,45,0.00,0,1,0,1490030314,1490030314),(28,0,'','','','003668','','87sor6',1,20,'002667',46,47,0.00,0,1,0,1490030318,1490030318),(29,0,'','','','003669','','872ovd',1,20,'002667',48,49,0.00,0,1,0,1490030324,1490030324);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_level_log`
--

LOCK TABLES `user_level_log` WRITE;
/*!40000 ALTER TABLE `user_level_log` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_level_stat`
--

LOCK TABLES `user_level_stat` WRITE;
/*!40000 ALTER TABLE `user_level_stat` DISABLE KEYS */;
INSERT INTO `user_level_stat` VALUES (1,1,0,25,1490028370,1490031162),(2,1,1,25,1490028370,1490031162),(3,1,2,0,1490028370,1490031162),(4,1,3,0,1490028370,1490031162),(5,1,4,0,1490028370,1490031162),(6,1,5,0,1490028370,1490031162),(7,1,6,0,1490028370,1490031162),(8,5,0,11,1490028430,1490031161),(9,5,1,11,1490028430,1490031161),(10,5,2,0,1490028430,1490031162),(11,5,3,0,1490028430,1490031162),(12,5,4,0,1490028430,1490031162),(13,5,5,0,1490028430,1490031162),(14,5,6,0,1490028430,1490031162),(15,6,0,2,1490029957,1490029983),(16,6,1,2,1490029957,1490029983),(17,6,2,0,1490029957,1490029983),(18,6,3,0,1490029957,1490029983),(19,6,4,0,1490029957,1490029983),(20,6,5,0,1490029957,1490029983),(21,6,6,0,1490029957,1490029983),(22,9,0,3,1490030094,1490030112),(23,9,1,3,1490030094,1490030112),(24,9,2,0,1490030094,1490030112),(25,9,3,0,1490030094,1490030112),(26,9,4,0,1490030094,1490030112),(27,9,5,0,1490030094,1490030112),(28,9,6,0,1490030094,1490030112),(29,10,0,3,1490030126,1490030138),(30,10,1,3,1490030126,1490030138),(31,10,2,0,1490030126,1490030138),(32,10,3,0,1490030126,1490030138),(33,10,4,0,1490030126,1490030138),(34,10,5,0,1490030126,1490030138),(35,10,6,0,1490030126,1490030138),(36,17,0,3,1490030227,1490030253),(37,17,1,3,1490030227,1490030253),(38,17,2,0,1490030227,1490030253),(39,17,3,0,1490030227,1490030253),(40,17,4,0,1490030228,1490030253),(41,17,5,0,1490030228,1490030253),(42,17,6,0,1490030228,1490030253),(43,18,0,3,1490030268,1490030279),(44,18,1,3,1490030268,1490030279),(45,18,2,0,1490030268,1490030279),(46,18,3,0,1490030268,1490030279),(47,18,4,0,1490030268,1490030279),(48,18,5,0,1490030268,1490030279),(49,18,6,0,1490030268,1490030279),(50,19,0,3,1490030298,1490030307),(51,19,1,3,1490030298,1490030308),(52,19,2,0,1490030298,1490030308),(53,19,3,0,1490030298,1490030308),(54,19,4,0,1490030298,1490030308),(55,19,5,0,1490030298,1490030308),(56,19,6,0,1490030298,1490030308),(57,20,0,3,1490030314,1490030324),(58,20,1,3,1490030314,1490030324),(59,20,2,0,1490030314,1490030324),(60,20,3,0,1490030314,1490030324),(61,20,4,0,1490030314,1490030324),(62,20,5,0,1490030314,1490030324),(63,20,6,0,1490030314,1490030324);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户登录记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_logined`
--

LOCK TABLES `user_logined` WRITE;
/*!40000 ALTER TABLE `user_logined` DISABLE KEYS */;
INSERT INTO `user_logined` VALUES (1,1,'',1,2130706433,1490026408),(2,1,'',1,2130706433,1490027132),(3,1,'',1,2130706433,1490028131),(4,1,'',1,2130706433,1490028332),(5,1,'',1,2130706433,1490029945),(6,3,'',1,2130706433,1490030786),(7,3,'',1,2130706433,1490030901),(8,1,'',1,2130706433,1490030965),(9,5,'',1,2130706433,1490031111);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_score_detail`
--

LOCK TABLES `user_score_detail` WRITE;
/*!40000 ALTER TABLE `user_score_detail` DISABLE KEYS */;
INSERT INTO `user_score_detail` VALUES (1,1,3,1000.00,0,0.00,1000.00,0,'',1490030997);
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

-- Dump completed on 2017-03-21  1:34:23
