-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.16-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 4fun_v1.0 的数据库结构
CREATE DATABASE IF NOT EXISTS `4fun_v1.0` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `4fun_v1.0`;


-- 导出  表 4fun_v1.0.articlefromdaily 结构
CREATE TABLE IF NOT EXISTS `articlefromdaily` (
  `articleName` char(50) NOT NULL,
  `articleTime` char(50) NOT NULL,
  `articleId` int(10) NOT NULL AUTO_INCREMENT,
  `articleAuthor` char(50) DEFAULT NULL,
  `articleContent` text,
  PRIMARY KEY (`articleName`),
  KEY `articleId` (`articleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 4fun_v1.0.articlefromone 结构
CREATE TABLE IF NOT EXISTS `articlefromone` (
  `articleAuthor` varchar(50) NOT NULL,
  `articleTitle` varchar(50) NOT NULL,
  `articleContent` text NOT NULL,
  `pubTime` varchar(50) NOT NULL,
  `articleUrl` text NOT NULL,
  `articleId` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`articleTitle`),
  KEY `articleId` (`articleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 4fun_v1.0.movieactorinfo 结构
CREATE TABLE IF NOT EXISTS `movieactorinfo` (
  `movieName` varchar(50) NOT NULL,
  `actorName` varchar(50) NOT NULL,
  `actorRole` varchar(50) NOT NULL,
  PRIMARY KEY (`movieName`,`actorName`,`actorRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 4fun_v1.0.movieinfo 结构
CREATE TABLE IF NOT EXISTS `movieinfo` (
  `date` varchar(50) DEFAULT NULL,
  `movieName` varchar(50) NOT NULL,
  `mark` double DEFAULT NULL,
  `pic` text,
  `director` varchar(50) DEFAULT NULL,
  `playWriter` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `briefIntro` text,
  `moreInfo` text,
  `pageUrl` varchar(50) DEFAULT NULL,
  `movieType` varchar(50) DEFAULT NULL,
  `movieId` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`movieName`),
  UNIQUE KEY `movieName` (`movieName`),
  KEY `movieId` (`movieId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 4fun_v1.0.musicinfo 结构
CREATE TABLE IF NOT EXISTS `musicinfo` (
  `queryId` varchar(50) NOT NULL,
  `date` char(50) NOT NULL,
  `songName` varchar(100) DEFAULT NULL,
  `artistId` varchar(50) DEFAULT NULL,
  `artistName` varchar(150) DEFAULT NULL,
  `songLink` text,
  `lrcLink` text,
  `albumId` varchar(50) DEFAULT NULL,
  `albumName` varchar(50) DEFAULT NULL,
  `imgUrl` text,
  `musicId` int(50) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`queryId`),
  KEY `Id` (`musicId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 4fun_v1.0.picturefromone 结构
CREATE TABLE IF NOT EXISTS `picturefromone` (
  `authorWork` varchar(50) DEFAULT NULL,
  `picDescription` text,
  `picDate` char(50) DEFAULT NULL,
  `picUrl` char(200) DEFAULT NULL,
  `pubTime` char(50) DEFAULT NULL,
  `VOL` char(50) DEFAULT NULL,
  `picId` int(11) NOT NULL AUTO_INCREMENT,
  UNIQUE KEY `authorWork` (`authorWork`),
  KEY `picId` (`picId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 4fun_v1.0.picturezhihu 结构
CREATE TABLE IF NOT EXISTS `picturezhihu` (
  `text` text NOT NULL,
  `time` varchar(50) DEFAULT NULL,
  `img` varchar(100) NOT NULL,
  PRIMARY KEY (`img`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 4fun_v1.0.poemchina 结构
CREATE TABLE IF NOT EXISTS `poemchina` (
  `poemContent` text,
  `poemAuthor` varchar(50) DEFAULT NULL,
  `poemDate` varchar(50) DEFAULT NULL,
  `poemVoice` text,
  `poemTitle` varchar(25) NOT NULL,
  `poemId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`poemTitle`),
  KEY `poemId` (`poemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
