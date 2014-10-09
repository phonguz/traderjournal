CREATE DATABASE  IF NOT EXISTS `trademgmt` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `trademgmt`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: trademgmt
-- ------------------------------------------------------
-- Server version	5.1.34-community

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `balance` decimal(7,2) DEFAULT NULL,
  `percent_risk` decimal(7,2) DEFAULT NULL,
  `lastupdtdate` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  `ccy_id` int(11) NOT NULL,
  `trader_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_accounts_ccy_idx` (`ccy_id`),
  KEY `fk_accounts_trader1_idx` (`trader_id`),
  CONSTRAINT `fk_accounts_ccy` FOREIGN KEY (`ccy_id`) REFERENCES `ccy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_accounts_trader1` FOREIGN KEY (`trader_id`) REFERENCES `trader` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ccy`
--

DROP TABLE IF EXISTS `ccy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ccy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `lastupdtdate` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ccy`
--

LOCK TABLES `ccy` WRITE;
/*!40000 ALTER TABLE `ccy` DISABLE KEYS */;
/*!40000 ALTER TABLE `ccy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instrument`
--

DROP TABLE IF EXISTS `instrument`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instrument` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `lastupdtdate` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  `ccy_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_instrument_ccy1_idx` (`ccy_id`),
  CONSTRAINT `fk_instrument_ccy1` FOREIGN KEY (`ccy_id`) REFERENCES `ccy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instrument`
--

LOCK TABLES `instrument` WRITE;
/*!40000 ALTER TABLE `instrument` DISABLE KEYS */;
/*!40000 ALTER TABLE `instrument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opentradedate` timestamp NULL DEFAULT NULL,
  `openprice` decimal(7,2) DEFAULT NULL,
  `closetradedate` timestamp NULL DEFAULT NULL,
  `closeprice` decimal(7,2) DEFAULT NULL,
  `stoploss` decimal(7,2) DEFAULT NULL,
  `tp` decimal(7,2) DEFAULT NULL,
  `entrycoms` decimal(7,2) DEFAULT NULL,
  `exitcoms` decimal(7,2) DEFAULT NULL,
  `entryfees` decimal(7,2) DEFAULT NULL,
  `exitfees` decimal(7,2) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `carrycost` decimal(7,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `accounts_id` int(11) NOT NULL,
  `trader_id` int(11) NOT NULL,
  `instrument_id` int(11) NOT NULL,
  `lastupdtdate` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_trade_accounts1_idx` (`accounts_id`),
  KEY `fk_trade_trader1_idx` (`trader_id`),
  KEY `fk_trade_instrument1_idx` (`instrument_id`),
  CONSTRAINT `fk_trade_accounts1` FOREIGN KEY (`accounts_id`) REFERENCES `accounts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trade_instrument1` FOREIGN KEY (`instrument_id`) REFERENCES `instrument` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trade_trader1` FOREIGN KEY (`trader_id`) REFERENCES `trader` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tradeattachment`
--

DROP TABLE IF EXISTS `tradeattachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tradeattachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) DEFAULT NULL,
  `image` mediumblob,
  `lastupdtdate` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  `tradeevent_id` int(11) NOT NULL,
  `filetype` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tradeeventimage_tradeevent1_idx` (`tradeevent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tradeattachment`
--

LOCK TABLES `tradeattachment` WRITE;
/*!40000 ALTER TABLE `tradeattachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tradeattachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tradeevent`
--

DROP TABLE IF EXISTS `tradeevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tradeevent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eventdate` timestamp NULL DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `eventorder` int(11) DEFAULT NULL,
  `newvalue` decimal(7,2) DEFAULT NULL,
  `lastupdtdate` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  `trade_id` int(11) NOT NULL,
  `tradeeventtype_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tradeevent_trade1_idx` (`trade_id`),
  KEY `fk_tradeevent_tradeeventtype1_idx` (`tradeeventtype_id`),
  CONSTRAINT `fk_tradeevent_trade1` FOREIGN KEY (`trade_id`) REFERENCES `trade` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tradeevent_tradeeventtype1` FOREIGN KEY (`tradeeventtype_id`) REFERENCES `tradeeventtype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tradeevent`
--

LOCK TABLES `tradeevent` WRITE;
/*!40000 ALTER TABLE `tradeevent` DISABLE KEYS */;
/*!40000 ALTER TABLE `tradeevent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tradeeventtype`
--

DROP TABLE IF EXISTS `tradeeventtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tradeeventtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `lastupdtdate` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tradeeventtype`
--

LOCK TABLES `tradeeventtype` WRITE;
/*!40000 ALTER TABLE `tradeeventtype` DISABLE KEYS */;
/*!40000 ALTER TABLE `tradeeventtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trader`
--

DROP TABLE IF EXISTS `trader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trader` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `lastupdtdate` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trader`
--

LOCK TABLES `trader` WRITE;
/*!40000 ALTER TABLE `trader` DISABLE KEYS */;
/*!40000 ALTER TABLE `trader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transactionvalue` decimal(7,2) DEFAULT NULL,
  `transactiontype_id` int(11) NOT NULL,
  `lastupdtdate` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_transaction_transactiontype1_idx` (`transactiontype_id`),
  CONSTRAINT `fk_transaction_transactiontype1` FOREIGN KEY (`transactiontype_id`) REFERENCES `transactiontype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactiontype`
--

DROP TABLE IF EXISTS `transactiontype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactiontype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(200) DEFAULT NULL,
  `lastupdtdate` timestamp NULL DEFAULT NULL,
  `updatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactiontype`
--

LOCK TABLES `transactiontype` WRITE;
/*!40000 ALTER TABLE `transactiontype` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactiontype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'trademgmt'
--

--
-- Dumping routines for database 'trademgmt'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-26 13:57:47
