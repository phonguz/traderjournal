-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema trademgmt
--

CREATE DATABASE IF NOT EXISTS trademgmt;
USE trademgmt;

--
-- Definition of table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `balance` decimal(9,2) default NULL,
  `percent_risk` decimal(9,2) default NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  `ccy_id` int(11) NOT NULL,
  `trader_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_accounts_ccy_idx` (`ccy_id`),
  KEY `fk_accounts_trader1_idx` (`trader_id`),
  CONSTRAINT `fk_accounts_ccy` FOREIGN KEY (`ccy_id`) REFERENCES `ccy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_accounts_trader1` FOREIGN KEY (`trader_id`) REFERENCES `trader` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `accounts`
--

/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` (`id`,`name`,`balance`,`percent_risk`,`lastupdtdate`,`updatedby`,`ccy_id`,`trader_id`) VALUES 
 (1,'DharmeshAccount','35000.00','2.00','2014-07-18 14:31:45',2,2,2),
 (2,'DharmeshTestAccount','25000.00','3.00','2014-07-18 14:32:09',2,2,2),
 (3,'NavneetAccount','45000.00','5.00','2014-07-18 15:09:24',3,2,3),
 (4,'NavneetTestAccount','50000.00','4.00','2014-07-18 15:09:43',3,3,3),
 (5,'ShekharAccount','15000.00','1.00','2014-07-18 15:23:24',7,2,7),
 (6,'KapilAccount','30000.00','2.00','2014-07-18 15:29:39',6,1,6),
 (7,'KapilTestAccount','15000.00','1.00','2014-07-18 15:30:26',6,2,6),
 (8,'PrakashAccount','55000.00','3.00','2014-07-18 15:41:05',4,2,4),
 (9,'PrakashTestAccount','12000.00','2.00','2014-07-18 15:41:36',4,2,4);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;


--
-- Definition of table `ccy`
--

DROP TABLE IF EXISTS `ccy`;
CREATE TABLE `ccy` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(25) default NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ccy`
--

/*!40000 ALTER TABLE `ccy` DISABLE KEYS */;
INSERT INTO `ccy` (`id`,`name`,`lastupdtdate`,`updatedby`) VALUES 
 (1,'Euro','2014-06-02 17:21:30',1),
 (2,'INR','2014-06-03 16:50:01',1),
 (3,'Dollar','2014-06-02 17:22:09',1),
 (4,'Pound','2014-06-02 17:23:09',1),
 (5,'Dinar','2014-06-20 18:06:37',1),
 (6,'Yen','2014-06-20 18:43:34',1);
/*!40000 ALTER TABLE `ccy` ENABLE KEYS */;


--
-- Definition of table `instrument`
--

DROP TABLE IF EXISTS `instrument`;
CREATE TABLE `instrument` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(200) default NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  `ccy_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_instrument_ccy1_idx` (`ccy_id`),
  CONSTRAINT `fk_instrument_ccy1` FOREIGN KEY (`ccy_id`) REFERENCES `ccy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `instrument`
--

/*!40000 ALTER TABLE `instrument` DISABLE KEYS */;
INSERT INTO `instrument` (`id`,`name`,`lastupdtdate`,`updatedby`,`ccy_id`) VALUES 
 (1,'Instrument1','2014-07-18 14:36:08',1,2),
 (2,'Intstrument2','2014-07-18 14:36:23',1,3),
 (3,'Instrument3','2014-07-18 14:36:37',1,1),
 (4,'Instrument4','2014-07-18 14:36:51',1,4),
 (5,'TestInstDh','2014-07-18 14:55:22',2,2);
/*!40000 ALTER TABLE `instrument` ENABLE KEYS */;


--
-- Definition of table `sharedaccount`
--

DROP TABLE IF EXISTS `sharedaccount`;
CREATE TABLE `sharedaccount` (
  `id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `share_trader_id` int(11) NOT NULL,
  `trader_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK452C1228C2CFD6F6` (`share_trader_id`),
  KEY `FK452C122870E89756` (`trader_id`),
  KEY `FK452C12283513A9AF` (`account_id`),
  CONSTRAINT `FK452C12283513A9AF` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK452C122870E89756` FOREIGN KEY (`trader_id`) REFERENCES `trader` (`id`),
  CONSTRAINT `FK452C1228C2CFD6F6` FOREIGN KEY (`share_trader_id`) REFERENCES `trader` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sharedaccount`
--

/*!40000 ALTER TABLE `sharedaccount` DISABLE KEYS */;
INSERT INTO `sharedaccount` (`id`,`account_id`,`share_trader_id`,`trader_id`) VALUES 
 (0,1,6,2);
/*!40000 ALTER TABLE `sharedaccount` ENABLE KEYS */;


--
-- Definition of table `trade`
--

DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `id` int(11) NOT NULL auto_increment,
  `opentradedate` timestamp NULL default NULL,
  `openprice` decimal(7,2) default NULL,
  `closetradedate` timestamp NULL default NULL,
  `closeprice` decimal(7,2) default NULL,
  `stoploss` decimal(7,2) default NULL,
  `tp` decimal(7,2) default NULL,
  `entrycoms` decimal(7,2) default NULL,
  `exitcoms` decimal(7,2) default NULL,
  `entryfees` decimal(7,2) default NULL,
  `exitfees` decimal(7,2) default NULL,
  `reference` varchar(255) default NULL,
  `carrycost` decimal(7,2) default NULL,
  `quantity` int(11) default NULL,
  `accounts_id` int(11) NOT NULL,
  `trader_id` int(11) NOT NULL,
  `instrument_id` int(11) NOT NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  `tradetype` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_trade_accounts1_idx` (`accounts_id`),
  KEY `fk_trade_trader1_idx` (`trader_id`),
  KEY `fk_trade_instrument1_idx` (`instrument_id`),
  CONSTRAINT `fk_trade_accounts1` FOREIGN KEY (`accounts_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_trade_instrument1` FOREIGN KEY (`instrument_id`) REFERENCES `instrument` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trade_trader1` FOREIGN KEY (`trader_id`) REFERENCES `trader` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trade`
--

/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
INSERT INTO `trade` (`id`,`opentradedate`,`openprice`,`closetradedate`,`closeprice`,`stoploss`,`tp`,`entrycoms`,`exitcoms`,`entryfees`,`exitfees`,`reference`,`carrycost`,`quantity`,`accounts_id`,`trader_id`,`instrument_id`,`lastupdtdate`,`updatedby`,`tradetype`) VALUES 
 (1,'2014-07-18 00:00:00','12000.00','2014-07-18 00:00:00','25000.00','11800.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,1,2,1,'2014-07-18 14:52:29',2,0),
 (2,'2014-07-18 00:00:00','15200.00','2014-07-20 00:00:00','1000.00','13500.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,9,2,2,5,'2014-07-18 15:06:43',2,1),
 (3,'2014-07-18 00:00:00','8000.00','2014-07-19 00:00:00','2000.00','7800.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,1,2,1,'2014-07-18 15:03:09',2,0),
 (4,'2014-07-18 00:00:00','12000.00','2014-07-20 00:00:00','5000.00','8000.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,3,3,2,'2014-07-18 15:10:51',3,0),
 (5,'2014-07-18 00:00:00','5000.00','2014-07-22 00:00:00',NULL,'2500.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,30,3,3,3,'2014-07-18 15:11:53',3,0),
 (6,'2014-07-18 00:00:00','9000.00','2014-07-26 00:00:00','2000.00','5500.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,4,3,4,'2014-07-18 15:12:24',3,1),
 (7,'2014-07-18 00:00:00','4500.00','2014-07-28 00:00:00','1500.00','2500.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,4,3,1,'2014-07-18 15:12:55',3,1),
 (8,'2014-07-18 00:00:00','2000.00','2014-07-20 00:00:00','1000.00','1800.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,5,7,1,'2014-07-18 15:24:11',7,0),
 (9,'2014-07-18 00:00:00','3000.00','2014-07-21 00:00:00','2000.00','1500.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,5,7,2,'2014-07-18 15:24:52',7,1),
 (10,'2014-07-18 00:00:00','7000.00','2014-07-29 00:00:00','1000.00','5000.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,35,6,6,3,'2014-07-18 15:32:05',6,0),
 (11,'2014-07-18 00:00:00','8000.00','2014-07-31 00:00:00','1500.00','7800.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,38,7,6,1,'2014-07-18 15:34:46',6,1),
 (12,'2014-07-18 00:00:00','15000.00','2014-07-20 00:00:00',NULL,'14000.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,90,6,6,5,'2014-07-18 15:35:34',6,1),
 (13,'2014-07-19 00:00:00','15000.00','2014-07-20 00:00:00','1200.00','12000.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,80,8,4,3,'2014-07-18 15:42:11',4,0),
 (14,'2014-07-18 00:00:00','10000.00','2014-07-21 00:00:00',NULL,'8000.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,30,9,4,2,'2014-07-18 15:42:46',4,1),
 (15,'2014-07-19 00:00:00','5000.00','2014-07-29 00:00:00','2000.00','1500.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,9,4,1,'2014-07-18 15:43:08',4,0),
 (16,'2014-07-19 00:00:00','4000.00','2014-07-19 00:00:00',NULL,'1200.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,30,8,4,5,'2014-07-18 15:56:45',4,0);
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;


--
-- Definition of table `tradeattachment`
--

DROP TABLE IF EXISTS `tradeattachment`;
CREATE TABLE `tradeattachment` (
  `id` int(11) NOT NULL auto_increment,
  `description` longtext,
  `filename` varchar(45) default NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  `tradeevent_id` int(11) NOT NULL,
  `filetype` varchar(6) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_tradeattachment_tradeevent1` (`tradeevent_id`),
  CONSTRAINT `fk_tradeattachment_tradeevent1` FOREIGN KEY (`tradeevent_id`) REFERENCES `tradeevent` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tradeattachment`
--

/*!40000 ALTER TABLE `tradeattachment` DISABLE KEYS */;
INSERT INTO `tradeattachment` (`id`,`description`,`filename`,`lastupdtdate`,`updatedby`,`tradeevent_id`,`filetype`) VALUES 
 (1,'New idea for loss in trading.','slide 3.1.png','2014-07-18 14:58:43',2,1,'png'),
 (2,'New idea for loss in trading.','slide 3.2.png','2014-07-18 14:58:43',2,1,'png'),
 (3,'Best thing to make trade.','1.jpg','2014-07-18 15:03:38',2,2,'jpg'),
 (4,'Best thing to make trade.','2.jpg','2014-07-18 15:03:39',2,2,'jpg'),
 (5,'Best time for selling.','698199.png','2014-07-18 15:07:39',2,3,'png'),
 (6,'Best time for selling.','slide 2.3.png','2014-07-18 15:07:39',2,3,'png'),
 (7,'Never sell quickly.','slide 3.4.png','2014-07-18 15:14:55',3,4,'png'),
 (8,'','Chrysanthemum.jpg','2014-07-18 15:21:54',3,8,'jpg'),
 (9,'Best buy for trading.','Tulips.jpg','2014-07-18 15:25:21',7,9,'jpg'),
 (10,'','Jellyfish.jpg','2014-07-18 15:26:12',7,10,'jpg'),
 (11,'Sell due to emergency.','Hydrangeas.jpg','2014-07-18 15:27:13',7,12,'jpg'),
 (12,'Sell due to emergency.','Desert.jpg','2014-07-18 15:27:13',7,12,'jpg'),
 (13,'Best product for trade.','Desert.jpg','2014-07-18 15:36:03',6,13,'jpg'),
 (14,'Best product for trade.','Hydrangeas.jpg','2014-07-18 15:36:03',6,13,'jpg'),
 (15,'Best product for trade.','Jellyfish.jpg','2014-07-18 15:36:03',6,13,'jpg'),
 (16,'Attachment description','Tulips.jpg','2014-07-18 15:49:21',4,15,'jpg'),
 (17,'For test','Desert.jpg','2014-07-18 15:51:19',4,16,'jpg');
/*!40000 ALTER TABLE `tradeattachment` ENABLE KEYS */;


--
-- Definition of table `tradeevent`
--

DROP TABLE IF EXISTS `tradeevent`;
CREATE TABLE `tradeevent` (
  `id` int(11) NOT NULL auto_increment,
  `eventdate` timestamp NULL default NULL,
  `description` longtext,
  `eventorder` int(11) default NULL,
  `newvalue` decimal(7,2) default NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  `trade_id` int(11) NOT NULL,
  `tradeeventtype_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_tradeevent_trade1_idx` (`trade_id`),
  KEY `fk_tradeevent_tradeeventtype1_idx` (`tradeeventtype_id`),
  CONSTRAINT `fk_tradeevent_trade1` FOREIGN KEY (`trade_id`) REFERENCES `trade` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_tradeevent_tradeeventtype1` FOREIGN KEY (`tradeeventtype_id`) REFERENCES `tradeeventtype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tradeevent`
--

/*!40000 ALTER TABLE `tradeevent` DISABLE KEYS */;
INSERT INTO `tradeevent` (`id`,`eventdate`,`description`,`eventorder`,`newvalue`,`lastupdtdate`,`updatedby`,`trade_id`,`tradeeventtype_id`) VALUES 
 (1,'2014-07-18 00:00:00','Trade without an close price.',1,'1200.00','2014-07-18 14:59:13',2,3,1),
 (2,'2014-07-18 00:00:00','Adding close price after some.',1,'2000.00','2014-07-18 15:04:01',2,3,2),
 (3,'2014-07-18 00:00:00','Update open price due to profit',1,'200.00','2014-07-18 15:07:52',2,2,3),
 (4,'2014-07-19 00:00:00','Update quantity for the trade.',1,'35.00','2014-07-18 15:16:33',3,7,1),
 (5,'2014-07-18 00:00:00','Update stop loss for the selling.',1,'1500.00','2014-07-18 15:17:47',3,7,1),
 (6,'2014-07-18 00:00:00','Best profit ever.',1,'15000.00','2014-07-18 15:18:32',3,6,2),
 (7,'2014-07-18 00:00:00','No need to add close',1,'1200.00','2014-07-18 15:19:11',3,5,3),
 (8,'2014-07-18 00:00:00','Opening new one for international trade.',1,'5000.00','2014-07-18 15:21:58',3,4,2),
 (9,'2014-07-18 00:00:00','Buy new one at best deal',1,'2000.00','2014-07-18 15:25:29',7,9,2),
 (10,'2014-07-19 00:00:00','Sale for profit.',1,'3000.00','2014-07-18 15:26:15',7,8,2),
 (11,'2014-07-20 00:00:00','Best trade for business.',1,'1200.00','2014-07-18 15:26:38',7,9,1),
 (12,'2014-07-19 00:00:00','Selling is best deal',1,'1500.00','2014-07-18 15:27:21',7,8,1),
 (13,'2014-07-18 00:00:00','Best trade with most quantity.',1,'15000.00','2014-07-18 15:36:11',6,12,1),
 (14,'2014-07-19 00:00:00','First best trading for account',1,'1200.00','2014-07-18 15:46:51',4,15,2),
 (15,'2014-07-18 00:00:00','For the test purpose',1,'1000.00','2014-07-18 15:50:02',4,14,1),
 (16,'2014-07-20 00:00:00','At loss a lot.',1,'3000.00','2014-07-18 15:51:30',4,14,1);
/*!40000 ALTER TABLE `tradeevent` ENABLE KEYS */;


--
-- Definition of table `tradeeventtype`
--

DROP TABLE IF EXISTS `tradeeventtype`;
CREATE TABLE `tradeeventtype` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(200) default NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tradeeventtype`
--

/*!40000 ALTER TABLE `tradeeventtype` DISABLE KEYS */;
INSERT INTO `tradeeventtype` (`id`,`name`,`lastupdtdate`,`updatedby`) VALUES 
 (1,'Loss','2014-07-18 14:32:43',1),
 (2,'Profit','2014-07-18 14:32:51',1),
 (3,'Sale','2014-07-18 14:32:58',1);
/*!40000 ALTER TABLE `tradeeventtype` ENABLE KEYS */;


--
-- Definition of table `trader`
--

DROP TABLE IF EXISTS `trader`;
CREATE TABLE `trader` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(45) default NULL,
  `firstname` varchar(45) default NULL,
  `lastname` varchar(45) default NULL,
  `password` varchar(50) default NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  `type` varchar(15) NOT NULL,
  `tmppassword` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trader`
--

/*!40000 ALTER TABLE `trader` DISABLE KEYS */;
INSERT INTO `trader` (`id`,`username`,`firstname`,`lastname`,`password`,`lastupdtdate`,`updatedby`,`type`,`tmppassword`) VALUES 
 (1,'imtiyazd9@gmail.com','Imtiyaz','Devda','WFxFWEhQSwAB','2014-07-18 14:17:41',1,'ADMIN',0),
 (2,'dharmesh.j.java@gmail.com','Dharmesh','Joshi','RVRCRQADAgU=','2014-07-18 14:22:59',2,'USER',0),
 (3,'navneet.aegis@gmail.com','Navneet','Patel','RVRCRQADAgU=','2014-07-18 14:23:44',3,'USER',0),
 (4,'prakash.v.java@gmail.com','Prakash','Vidja','RVRCRQADAgU=','2014-07-18 14:24:18',4,'USER',0),
 (5,'chirag.p.java@gmail.com','Chirag','Panara','RVRCRQADAgU=','2014-07-18 14:24:50',5,'USER',0),
 (6,'kapil.p.java@gmail.com','Kapil','Purohit','RVRCRQADAgU=','2014-07-18 14:26:34',6,'USER',0),
 (7,'shekhar.b.java@gmail.com','Shekhar','Suchak','RVRCRQADAgU=','2014-07-18 14:27:19',7,'USER',0),
 (8,'vivek.m.java@gmail.com','Vivek','Mehta','RVRCRQADAgU=','2014-07-18 14:27:48',8,'USER',0),
 (9,'denish.v.java@gmail.com','Denish','Virani','RVRCRQADAgU=','2014-07-18 14:28:29',9,'USER',0),
 (10,'minesh.b.java@gmail.com','Minesh','Bhadeshia','RVRCRQADAgU=','2014-07-18 14:28:59',10,'USER',0),
 (11,'jwalit.p.java@gmail.com','Jwalit','Patel','RVRCRQADAgU=','2014-07-18 14:30:30',11,'USER',0);
/*!40000 ALTER TABLE `trader` ENABLE KEYS */;


--
-- Definition of table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL auto_increment,
  `transactionvalue` decimal(9,2) default NULL,
  `transactiontype_id` int(11) NOT NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  `account_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_transaction_transactiontype1_idx` (`transactiontype_id`),
  KEY `fk_accountId` (`account_id`),
  CONSTRAINT `fk_accountId` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_transaction_transactiontype1` FOREIGN KEY (`transactiontype_id`) REFERENCES `transactiontype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transaction`
--

/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` (`id`,`transactionvalue`,`transactiontype_id`,`lastupdtdate`,`updatedby`,`account_id`) VALUES 
 (4,'35000.00',1,'2014-07-18 14:31:45',2,1),
 (5,'25000.00',1,'2014-07-18 14:32:09',2,2),
 (6,'45000.00',1,'2014-07-18 15:09:24',3,3),
 (7,'50000.00',1,'2014-07-18 15:09:43',3,4),
 (8,'15000.00',1,'2014-07-18 15:23:24',7,5),
 (9,'30000.00',1,'2014-07-18 15:29:39',6,6),
 (10,'15000.00',1,'2014-07-18 15:30:26',6,7),
 (11,'55000.00',1,'2014-07-18 15:41:05',4,8),
 (12,'12000.00',1,'2014-07-18 15:41:36',4,9);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;


--
-- Definition of table `transactiontype`
--

DROP TABLE IF EXISTS `transactiontype`;
CREATE TABLE `transactiontype` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(200) default NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transactiontype`
--

/*!40000 ALTER TABLE `transactiontype` DISABLE KEYS */;
INSERT INTO `transactiontype` (`id`,`type`,`lastupdtdate`,`updatedby`) VALUES 
 (1,'Deposit','2014-07-08 17:12:52',6),
 (2,'Withdrow','2014-07-08 17:13:05',6);
/*!40000 ALTER TABLE `transactiontype` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
