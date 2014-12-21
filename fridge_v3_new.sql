/*
SQLyog Community v11.4 (64 bit)
MySQL - 5.5.16 : Database - fridge
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fridge` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `fridge`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `ID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Description` varchar(250) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `CategoryID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DateAdded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Amount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `article` */

insert  into `article`(`ID`,`Name`,`Description`,`CategoryID`,`DateAdded`,`Amount`) values ('54497251-bc8b-40b7-9efc-43c991de6e14','Test ARticle','A Test description for this article','b7278936-bb75-4b9f-84a5-77d5f1c6342e','2014-02-09 20:51:23',0);

/*Table structure for table `articlelines` */

DROP TABLE IF EXISTS `articlelines`;

CREATE TABLE `articlelines` (
  `ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ArticleID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Description` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `Amount` int(11) NOT NULL DEFAULT '0',
  `Weight` decimal(10,2) NOT NULL DEFAULT '0.00',
  `DateAdded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ExpiryDate` date NOT NULL,
  `ShopID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `articlelines` */

insert  into `articlelines`(`ID`,`ArticleID`,`Description`,`Amount`,`Weight`,`DateAdded`,`ExpiryDate`,`ShopID`) values ('54497251-bc8b-40b7-9efc-43c99','54497251-bc8b-40b7-9efc-43c991de6e14','SomeDescription...',2,'0.15','2014-02-16 20:56:00','2014-02-19','f1f1ff4a-9f96-41b9-aac8-b04cf49436a5');

/*Table structure for table `barcodes` */

DROP TABLE IF EXISTS `barcodes`;

CREATE TABLE `barcodes` (
  `ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Barcode` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ArticleID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `barcodes` */

insert  into `barcodes`(`ID`,`Barcode`,`ArticleID`) values ('barcode','12345123','54497251-bc8b-40b7-9efc-43c991de6e14');

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `categories` */

insert  into `categories`(`ID`,`Name`,`Description`) values ('b7278936-bb75-4b9f-84a5-77d5f1c6342e','TestCategory','A simple test Category');

/*Table structure for table `shops` */

DROP TABLE IF EXISTS `shops`;

CREATE TABLE `shops` (
  `ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `shops` */

insert  into `shops`(`ID`,`Name`,`Description`) values ('f1f1ff4a-9f96-41b9-aac8-b04cf49436a5','Penner','A Shop like Migros');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
