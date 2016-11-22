CREATE TABLE `actorinfo` (
  `movieName` varchar(50) NOT NULL,
  `actorName` varchar(50) NOT NULL,
  `actorRole` varchar(50) NOT NULL,
  PRIMARY KEY (`movieName`,`actorName`,`actorRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `articlefromdaily` (
  `articleName` char(50) NOT NULL,
  `articleAuthor` char(50) DEFAULT NULL,
  `articleContent` text,
  UNIQUE KEY `articleName` (`articleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `articlefromone` (
  `authorWork` varchar(50) NOT NULL,
  `article` text NOT NULL,
  `pubTime` varchar(50) NOT NULL,
  `picUrl` char(200) NOT NULL,
  UNIQUE KEY `authorWork` (`authorWork`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `movieinfo` (
  `date` varchar(50) DEFAULT NULL,
  `movieName` varchar(50) DEFAULT NULL,
  `mark` double DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  `director` varchar(50) DEFAULT NULL,
  `playWriter` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `briefIntro` text,
  `moreInfo` varchar(100) DEFAULT NULL,
  `pageUrl` varchar(100) DEFAULT NULL,
  `actor` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `musicinfo` (
  `queryId` varchar(50) NOT NULL,
  `date` char(50) NOT NULL,
  `songName` varchar(50) DEFAULT NULL,
  `artistId` varchar(50) DEFAULT NULL,
  `artistName` varchar(50) DEFAULT NULL,
  `songLink` text,
  `lrcLink` text,
  `albumId` varchar(50) DEFAULT NULL,
  `albumName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`queryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `picturefromone` (
  `authorWork` varchar(50) DEFAULT NULL,
  `picDescription` text,
  `picDate` char(50) DEFAULT NULL,
  `picUrl` char(50) DEFAULT NULL,
  `pubTime` char(50) DEFAULT NULL,
  UNIQUE KEY `authorWork` (`authorWork`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `poemfromchinapoem` (
  `poemTitle` char(50) DEFAULT NULL,
  `poemAuthor` char(50) DEFAULT NULL,
  `pubTime` char(50) DEFAULT NULL,
  `poemContent` text,
  `url` char(50) DEFAULT NULL,
  UNIQUE KEY `poemTitle` (`poemTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
