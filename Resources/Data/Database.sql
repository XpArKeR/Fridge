CREATE TABLE IF NOT EXISTS `article` (
  `ID` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Description` varchar(250) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `CategoryID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DateAdded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Amount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `articlelines` (
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

CREATE TABLE IF NOT EXISTS `barcodes` (
  `ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Barcode` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ArticleID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `categories` (
  `ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `shops` (
  `ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `settings` (
  `ID` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `PropertyName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Value` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;