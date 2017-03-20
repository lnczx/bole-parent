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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'','','','001652','d1a8192d98c2cc4426e08ba73c91421e','',1,0,'',1,2,0.00,0,1,1,1489570243,1489570243),(2,2,'','','','001653','d1a8192d98c2cc4426e08ba73c91421e','',1,0,'',0,0,0.00,0,1,1,1489643503,1489643503),(3,1,'','','','001654','6d0d2621c95de44eb6d56aa31bf7ad34','',1,0,'',0,0,0.00,0,1,1,1490026550,1490026550),(4,1,'','','','001655','6d0d2621c95de44eb6d56aa31bf7ad34','',1,0,'',0,0,0.00,0,1,1,1490026585,1490026585);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_level_stat`
--

LOCK TABLES `user_level_stat` WRITE;
/*!40000 ALTER TABLE `user_level_stat` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户登录记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_logined`
--

LOCK TABLES `user_logined` WRITE;
/*!40000 ALTER TABLE `user_logined` DISABLE KEYS */;
INSERT INTO `user_logined` VALUES (1,1,'',1,2130706433,1490026408);
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

-- Dump completed on 2017-03-21  0:21:34
