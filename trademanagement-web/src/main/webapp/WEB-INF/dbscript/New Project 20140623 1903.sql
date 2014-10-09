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
  `balance` decimal(7,2) default NULL,
  `percent_risk` decimal(7,2) default NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  `ccy_id` int(11) NOT NULL,
  `trader_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_accounts_ccy_idx` (`ccy_id`),
  KEY `fk_accounts_trader1_idx` (`trader_id`),
  CONSTRAINT `fk_accounts_ccy` FOREIGN KEY (`ccy_id`) REFERENCES `ccy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_accounts_trader1` FOREIGN KEY (`trader_id`) REFERENCES `trader` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `accounts`
--

/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` (`id`,`name`,`balance`,`percent_risk`,`lastupdtdate`,`updatedby`,`ccy_id`,`trader_id`) VALUES 
 (1,'TestAccount','4545.00','54.00','2014-06-10 18:28:48',3,2,3),
 (2,'Mainaccount','10000.00','2.00','2014-06-10 18:33:28',3,2,3),
 (3,'BaseAccount1','25000.00','5.00','2014-06-20 18:06:01',6,2,6),
 (4,'TestAccount1','10000.00','2.00','2014-06-23 18:24:28',9,2,9),
 (6,'wasus','55000.00','66.00','2014-06-23 18:43:33',9,3,9);
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
 (5,'Dinar','2014-06-20 18:06:37',6),
 (6,'Yen','2014-06-20 18:43:34',6);
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
 (1,'Tools','2014-06-05 17:35:41',1,1),
 (2,'Instrument1','2014-06-05 17:38:35',1,2),
 (3,'Intstrument2','2014-06-11 12:03:52',6,2),
 (4,'Instrument3','2014-06-11 12:07:03',6,2),
 (5,'Instrument4','2014-06-11 12:12:07',6,2),
 (14,'TestIntsrument5','2014-06-11 16:53:25',6,2),
 (15,'BestInstrument1','2014-06-20 18:06:24',6,4);
/*!40000 ALTER TABLE `instrument` ENABLE KEYS */;


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
  PRIMARY KEY  (`id`),
  KEY `fk_trade_accounts1_idx` (`accounts_id`),
  KEY `fk_trade_trader1_idx` (`trader_id`),
  KEY `fk_trade_instrument1_idx` (`instrument_id`),
  CONSTRAINT `fk_trade_accounts1` FOREIGN KEY (`accounts_id`) REFERENCES `accounts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trade_instrument1` FOREIGN KEY (`instrument_id`) REFERENCES `instrument` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trade_trader1` FOREIGN KEY (`trader_id`) REFERENCES `trader` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trade`
--

/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
INSERT INTO `trade` (`id`,`opentradedate`,`openprice`,`closetradedate`,`closeprice`,`stoploss`,`tp`,`entrycoms`,`exitcoms`,`entryfees`,`exitfees`,`reference`,`carrycost`,`quantity`,`accounts_id`,`trader_id`,`instrument_id`,`lastupdtdate`,`updatedby`) VALUES 
 (1,'2014-06-10 00:00:00','12000.00','2014-06-11 00:00:00','1230.00','1520.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1200,2,6,1,'2014-06-10 18:55:38',6),
 (4,'2014-06-10 00:00:00','12.00','2014-06-11 00:00:00','1000.00','1.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,6,1,'2014-06-17 18:39:18',6),
 (5,'2014-06-12 00:00:00','12000.00','2014-06-13 00:00:00','1000.00','1200.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,6,4,'2014-06-11 12:07:04',6),
 (6,'2014-06-16 00:00:00','15000.00','2014-06-17 00:00:00','1000.00','1520.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1200,1,6,5,'2014-06-11 12:12:07',6),
 (7,'2014-06-10 00:00:00','12.00','2014-06-11 00:00:00','1000.00','1.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,6,5,'2014-06-11 16:52:11',6),
 (8,'2014-06-10 00:00:00','12.00','2014-06-11 00:00:00','1000.00','1.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1,6,1,'2014-06-18 16:06:02',6),
 (9,'2014-06-20 00:00:00','27500.00','2014-06-27 00:00:00','30000.00','12.50',NULL,NULL,NULL,NULL,NULL,NULL,NULL,25,1,6,14,'2014-06-20 18:02:26',6),
 (10,'2014-06-18 00:00:00','343.00','2014-07-01 00:00:00','34.00','344.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,43,4,9,1,'2014-06-23 18:28:31',9),
 (11,'2014-06-17 00:00:00','7777.00','2014-07-01 00:00:00','56.00','65.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,65,4,9,2,'2014-06-23 18:31:50',9),
 (12,'2014-06-19 00:00:00','26500.00','2014-07-03 00:00:00','55.00','344.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,545,4,9,14,'2014-06-23 18:37:29',9);
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;


--
-- Definition of table `tradeattachment`
--

DROP TABLE IF EXISTS `tradeattachment`;
CREATE TABLE `tradeattachment` (
  `id` int(11) NOT NULL auto_increment,
  `description` longtext,
  `image` mediumblob,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  `tradeevent_id` int(11) NOT NULL,
  `filetype` varchar(6) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_tradeattachment_tradeevent1` (`tradeevent_id`),
  CONSTRAINT `fk_tradeattachment_tradeevent1` FOREIGN KEY (`tradeevent_id`) REFERENCES `tradeevent` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tradeattachment`
--

/*!40000 ALTER TABLE `tradeattachment` DISABLE KEYS */;
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
  CONSTRAINT `fk_tradeevent_trade1` FOREIGN KEY (`trade_id`) REFERENCES `trade` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tradeevent_tradeeventtype1` FOREIGN KEY (`tradeeventtype_id`) REFERENCES `tradeeventtype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tradeevent`
--

/*!40000 ALTER TABLE `tradeevent` DISABLE KEYS */;
INSERT INTO `tradeevent` (`id`,`eventdate`,`description`,`eventorder`,`newvalue`,`lastupdtdate`,`updatedby`,`trade_id`,`tradeeventtype_id`) VALUES 
 (1,'2014-06-19 00:00:00','This short description for stock loss for this trade.',1,'15000.00','2014-06-17 18:39:18',6,4,2),
 (2,'2014-06-18 00:00:00','fsdfs',1,'12000.00','2014-06-18 17:04:18',6,5,1),
 (3,'2014-06-18 00:00:00','fsdfsds',1,'12000.00','2014-06-18 17:09:45',6,5,1),
 (4,'2014-06-20 00:00:00','Update open price',1,'27500.00','2014-06-20 18:02:26',6,9,2),
 (5,'2014-06-03 00:00:00','ff',1,'66.00','2014-06-23 18:28:31',9,10,1),
 (6,'2014-06-10 00:00:00','dhgh',1,'4.00','2014-06-23 18:31:50',9,11,2);
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
 (1,'Delete','2014-06-17 17:01:17',6),
 (2,'Drop','2014-06-17 17:01:32',6),
 (3,'Loss','2014-06-17 17:01:45',6),
 (4,'Event1','2014-06-23 18:45:46',9),
 (5,'Event2','2014-06-23 18:45:53',9);
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
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trader`
--

/*!40000 ALTER TABLE `trader` DISABLE KEYS */;
INSERT INTO `trader` (`id`,`username`,`firstname`,`lastname`,`password`,`lastupdtdate`,`updatedby`,`type`) VALUES 
 (3,'navneet.aegis@gmai.com','Navneet','Patel','64a43b6ca15d128ac6a0679b39bc9c07','2014-06-05 18:45:26',6,'USER'),
 (5,'shekhar.b.java@gmail.com','shekhar','Suchak','098f6bcd4621d373cade4e832627b4f6','2014-06-06 16:42:48',6,'USER'),
 (6,'imtiyaz@gmail.com','Imtiyaz','Devda','563dfe888a68df8b35a0c4091453c62c','2014-06-05 17:18:24',6,'ADMIN'),
 (7,'prakash@gmail.com','Prakash','Patel','782a2d56bd5a1cc389ff3148d11afe72','2014-06-05 18:02:32',6,'USER'),
 (8,'imtiyazd9@gmail.com','imtiyaz','devda','098f6bcd4621d373cade4e832627b4f6','2014-06-09 17:40:39',NULL,'USER'),
 (9,'kapil@gmail.com','kapil','purohit','098f6bcd4621d373cade4e832627b4f6','2014-06-23 18:17:46',9,'USER'),
 (10,'vivek@gmail.com','vivek','mehta','098f6bcd4621d373cade4e832627b4f6','2014-06-09 17:49:31',NULL,'USER'),
 (11,'minesh@gmail.com','minesh','bhadesia','098f6bcd4621d373cade4e832627b4f6','2014-06-09 17:57:59',11,'USER'),
 (12,'denish@gmail.com','denish','virani','098f6bcd4621d373cade4e832627b4f6','2014-06-09 18:03:23',12,'USER'),
 (13,'dharmesh@gmail.com','dharmesh','joshi','098f6bcd4621d373cade4e832627b4f6','2014-06-09 18:06:27',13,'USER'),
 (14,'chirag@gmail.com','chirag','patel','098f6bcd4621d373cade4e832627b4f6','2014-06-23 17:41:56',6,'USER'),
 (15,'goti@gmail.com','Mahendra','goti','098f6bcd4621d373cade4e832627b4f6','2014-06-23 17:54:58',6,'USER'),
 (16,'rahul@gmail.com','rahul','patel','098f6bcd4621d373cade4e832627b4f6','2014-06-09 18:15:34',16,'USER'),
 (17,'vijay@gmail.com','vijay','patel','098f6bcd4621d373cade4e832627b4f6','2014-06-09 18:16:43',17,'USER'),
 (18,'you@you.com','me','hum','d8578edf8458ce06fbc5bb76a58c5ca4','2014-06-23 18:35:02',18,'USER');
/*!40000 ALTER TABLE `trader` ENABLE KEYS */;


--
-- Definition of table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL auto_increment,
  `transactionvalue` decimal(7,2) default NULL,
  `transactiontype_id` int(11) NOT NULL,
  `lastupdtdate` timestamp NULL default NULL,
  `updatedby` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_transaction_transactiontype1_idx` (`transactiontype_id`),
  CONSTRAINT `fk_transaction_transactiontype1` FOREIGN KEY (`transactiontype_id`) REFERENCES `transactiontype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transaction`
--

/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
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
 (1,'TransactionType1','2014-06-20 18:05:30',6),
 (2,'TestTransaction','2014-06-23 18:46:27',9),
 (3,'Transaction4','2014-06-23 18:46:08',9),
 (4,'Transaction2','2014-06-23 18:46:13',9),
 (5,'Transaction3','2014-06-23 18:46:18',9);
/*!40000 ALTER TABLE `transactiontype` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
