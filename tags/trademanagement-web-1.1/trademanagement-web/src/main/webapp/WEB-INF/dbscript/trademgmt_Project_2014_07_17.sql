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
 (3,'BaseAccount1','25000.00','5.00','2014-06-20 18:06:01',6,2,6),
 (8,'DharmeshAccount1','30000.00','2.00','2014-07-02 15:47:30',6,1,6),
 (9,'TestAccount','1000.00','5.00','2014-07-03 15:09:04',6,2,6),
 (10,'Shekhar Account','54000.00','2.00','2014-07-07 15:44:05',5,2,5),
 (11,'Main Account','30000.00','2.00','2014-07-10 11:24:04',6,2,6),
 (12,'Vivek Account','27000.00','3.00','2014-07-10 11:25:06',10,2,10);
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
 (15,'BestInstrument1','2014-06-20 18:06:24',6,4),
 (16,'NewInstrument12','2014-06-25 15:04:42',6,2),
 (17,'TestInstrument1','2014-06-25 15:30:24',6,2),
 (18,'NewInstForNewTrader','2014-06-27 18:43:01',6,1),
 (19,'test','2014-07-04 13:55:00',6,2);
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
 (17,'2014-06-27 00:00:00','15000.00','2014-06-29 00:00:00','35000.00','1200.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,13,8,6,18,'2014-07-07 11:17:43',6,0),
 (19,'2014-07-04 00:00:00','1500.00','2014-07-04 00:00:00',NULL,'150.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,3,6,1,'2014-07-04 15:07:27',6,0),
 (20,'2014-07-11 00:00:00','12000.00','2014-07-11 00:00:00',NULL,'5000.00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,3,6,18,'2014-07-11 13:06:27',6,0);
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
 (3,'Description for attachment of event.','slide 3.1.png','2014-07-17 16:26:12',6,7,'png');
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
 (1,'2014-07-02 15:47:30','dsfsfsfdsfsfsfsfsfds',1,'1200.00','2014-07-02 15:47:30',6,19,1),
 (2,'2014-07-09 00:00:00','Event description at add event for particular trade.',1,'12000.00','2014-07-08 16:49:24',6,19,1),
 (3,'2014-07-10 00:00:00','Don\'t use data attributes from multiple plugins on the same element. For example, a button cannot both have a tooltip and toggle a modal',1,'15000.00','2014-07-09 13:57:03',6,19,1),
 (4,'2014-07-09 00:00:00','At Trade Management, we empower our clients by giving them the ability to pay only for productive labor, which ensures maximized profitability for their business.',1,'20000.00','2014-07-09 14:12:04',6,19,2),
 (5,'2014-07-11 00:00:00','Test description.',1,'12000.00','2014-07-11 17:16:20',6,20,2),
 (6,'2014-07-17 00:00:00','File upload by ajax as event attachment for particular trade.',1,'12000.00','2014-07-17 16:14:02',6,20,1),
 (7,'2014-07-17 00:00:00','Testing description for event of trade.',1,'22000.00','2014-07-17 16:26:50',6,20,2);
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
  `tmppassword` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trader`
--

/*!40000 ALTER TABLE `trader` DISABLE KEYS */;
INSERT INTO `trader` (`id`,`username`,`firstname`,`lastname`,`password`,`lastupdtdate`,`updatedby`,`type`,`tmppassword`) VALUES 
 (5,'shekhar.b.java@gmail.com','shekhar','Suchak','RVRCRQADAgU=','2014-06-06 16:42:48',6,'USER',0),
 (6,'imtiyazd9@gmail.com','Imtiyaz','Devda','WFxFWEhQSwAB','2014-07-09 18:39:33',6,'ADMIN',0),
 (7,'prakash@gmail.com','Prakash','Patel','RVRCRQADAgU=','2014-06-05 18:02:32',6,'USER',0),
 (8,'imtiyaz@gmail.com','imtiyaz','devda','RVRCRQADAgU=','2014-06-09 17:40:39',6,'USER',0),
 (10,'vivek@gmail.com','vivek','mehta','RVRCRQADAgU=','2014-06-09 17:49:31',6,'USER',0),
 (11,'minesh@gmail.com','minesh','bhadesia','RVRCRQADAgU=','2014-06-09 17:57:59',11,'USER',0),
 (12,'denish@gmail.com','denish','virani','RVRCRQADAgU=','2014-06-09 18:03:23',12,'USER',0),
 (13,'dharmesh@gmail.com','dharmesh','joshi','RVRCRQADAgU=','2014-06-09 18:06:27',13,'USER',0);
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
 (1,'10000.00',1,'2014-07-10 11:24:04',6,11),
 (2,'12000.00',1,'2014-07-10 11:25:06',10,12);
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
