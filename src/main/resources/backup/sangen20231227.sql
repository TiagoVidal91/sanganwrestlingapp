-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sangenwrestlingdb
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `finishers`
--

DROP TABLE IF EXISTS `finishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `finishers` (
  `wrestler_id` bigint NOT NULL,
  `finishers` varchar(255) DEFAULT NULL,
  KEY `FKatdbwcuwnpemuemwqwsi5oimp` (`wrestler_id`),
  CONSTRAINT `FKatdbwcuwnpemuemwqwsi5oimp` FOREIGN KEY (`wrestler_id`) REFERENCES `wrestler` (`wrestler_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finishers`
--

LOCK TABLES `finishers` WRITE;
/*!40000 ALTER TABLE `finishers` DISABLE KEYS */;
INSERT INTO `finishers` VALUES (1,'Fallen Angel'),(1,'Bullseye'),(2,'French Kiss'),(2,'Sweet Lullaby');
/*!40000 ALTER TABLE `finishers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locker_room`
--

DROP TABLE IF EXISTS `locker_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locker_room` (
  `wrestling_locker_room_id` bigint NOT NULL AUTO_INCREMENT,
  `wrestling_locker_room_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wrestling_locker_room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locker_room`
--

LOCK TABLES `locker_room` WRITE;
/*!40000 ALTER TABLE `locker_room` DISABLE KEYS */;
INSERT INTO `locker_room` VALUES (1,'Female'),(2,'Male');
/*!40000 ALTER TABLE `locker_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nicknames`
--

DROP TABLE IF EXISTS `nicknames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nicknames` (
  `wrestler_id` bigint NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  KEY `FKw2x89yeo5bqjwnqg28s4qgtk` (`wrestler_id`),
  CONSTRAINT `FKw2x89yeo5bqjwnqg28s4qgtk` FOREIGN KEY (`wrestler_id`) REFERENCES `wrestler` (`wrestler_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nicknames`
--

LOCK TABLES `nicknames` WRITE;
/*!40000 ALTER TABLE `nicknames` DISABLE KEYS */;
INSERT INTO `nicknames` VALUES (1,'The Eagle'),(1,'The Lockeroom Leader'),(2,'Women Destroyer');
/*!40000 ALTER TABLE `nicknames` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `signature_moves`
--

DROP TABLE IF EXISTS `signature_moves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `signature_moves` (
  `wrestler_id` bigint NOT NULL,
  `signature_moves` varchar(255) DEFAULT NULL,
  KEY `FKotmh13ieryca9jduhtj4poxbx` (`wrestler_id`),
  CONSTRAINT `FKotmh13ieryca9jduhtj4poxbx` FOREIGN KEY (`wrestler_id`) REFERENCES `wrestler` (`wrestler_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signature_moves`
--

LOCK TABLES `signature_moves` WRITE;
/*!40000 ALTER TABLE `signature_moves` DISABLE KEYS */;
INSERT INTO `signature_moves` VALUES (1,'Headshot'),(1,'Falcon Arrow'),(2,'The B-Slap'),(2,'Zig-Zag');
/*!40000 ALTER TABLE `signature_moves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestler`
--

DROP TABLE IF EXISTS `wrestler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestler` (
  `wrestler_id` bigint NOT NULL AUTO_INCREMENT,
  `in_ring_name` varchar(255) DEFAULT NULL,
  `wrestler_name` varchar(255) DEFAULT NULL,
  `wrestler_pic_path` varchar(255) DEFAULT NULL,
  `wrestling_brand_id` bigint DEFAULT NULL,
  `wrestling_locker_room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`wrestler_id`),
  KEY `FKagi0jab1bu9gwawmmkvb09jfl` (`wrestling_brand_id`),
  KEY `FKghkno2ujdse5ybmqguig0d1ar` (`wrestling_locker_room_id`),
  CONSTRAINT `FKagi0jab1bu9gwawmmkvb09jfl` FOREIGN KEY (`wrestling_brand_id`) REFERENCES `wrestling_brand` (`wrestling_brand_id`),
  CONSTRAINT `FKghkno2ujdse5ybmqguig0d1ar` FOREIGN KEY (`wrestling_locker_room_id`) REFERENCES `locker_room` (`wrestling_locker_room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestler`
--

LOCK TABLES `wrestler` WRITE;
/*!40000 ALTER TABLE `wrestler` DISABLE KEYS */;
INSERT INTO `wrestler` VALUES (1,'Adam Fletcher','Adler Furcht',NULL,2,2),(2,'Lex Valentine','Lavrenty Vaselvold',NULL,2,2);
/*!40000 ALTER TABLE `wrestler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestler_match_interference`
--

DROP TABLE IF EXISTS `wrestler_match_interference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestler_match_interference` (
  `interference_wrestler_id` bigint NOT NULL,
  `match_interference_wrestling_match_id` bigint NOT NULL,
  KEY `FKgt794fqnuhvns9atcors2k38y` (`match_interference_wrestling_match_id`),
  KEY `FKgpr34r2fifg4owm7gbxqfre0u` (`interference_wrestler_id`),
  CONSTRAINT `FKgpr34r2fifg4owm7gbxqfre0u` FOREIGN KEY (`interference_wrestler_id`) REFERENCES `wrestler` (`wrestler_id`),
  CONSTRAINT `FKgt794fqnuhvns9atcors2k38y` FOREIGN KEY (`match_interference_wrestling_match_id`) REFERENCES `wrestling_match` (`wrestling_match_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestler_match_interference`
--

LOCK TABLES `wrestler_match_interference` WRITE;
/*!40000 ALTER TABLE `wrestler_match_interference` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrestler_match_interference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestler_match_losses`
--

DROP TABLE IF EXISTS `wrestler_match_losses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestler_match_losses` (
  `loser_wrestler_id` bigint NOT NULL,
  `match_losses_wrestling_match_id` bigint NOT NULL,
  KEY `FKbovv883jumlcj9ojefqq3ds0i` (`match_losses_wrestling_match_id`),
  KEY `FKidg7obgslpulvlegem08aly61` (`loser_wrestler_id`),
  CONSTRAINT `FKbovv883jumlcj9ojefqq3ds0i` FOREIGN KEY (`match_losses_wrestling_match_id`) REFERENCES `wrestling_match` (`wrestling_match_id`),
  CONSTRAINT `FKidg7obgslpulvlegem08aly61` FOREIGN KEY (`loser_wrestler_id`) REFERENCES `wrestler` (`wrestler_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestler_match_losses`
--

LOCK TABLES `wrestler_match_losses` WRITE;
/*!40000 ALTER TABLE `wrestler_match_losses` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrestler_match_losses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestler_match_victories`
--

DROP TABLE IF EXISTS `wrestler_match_victories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestler_match_victories` (
  `winner_wrestler_id` bigint NOT NULL,
  `match_victories_wrestling_match_id` bigint NOT NULL,
  KEY `FKbcko79ho3wggp6kd9nwcnnwgj` (`match_victories_wrestling_match_id`),
  KEY `FKdtvebf13srxrr38plc07ncvfy` (`winner_wrestler_id`),
  CONSTRAINT `FKbcko79ho3wggp6kd9nwcnnwgj` FOREIGN KEY (`match_victories_wrestling_match_id`) REFERENCES `wrestling_match` (`wrestling_match_id`),
  CONSTRAINT `FKdtvebf13srxrr38plc07ncvfy` FOREIGN KEY (`winner_wrestler_id`) REFERENCES `wrestler` (`wrestler_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestler_match_victories`
--

LOCK TABLES `wrestler_match_victories` WRITE;
/*!40000 ALTER TABLE `wrestler_match_victories` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrestler_match_victories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestler_wrestling_matches_list`
--

DROP TABLE IF EXISTS `wrestler_wrestling_matches_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestler_wrestling_matches_list` (
  `opponents_wrestler_id` bigint NOT NULL,
  `wrestling_matches_list_wrestling_match_id` bigint NOT NULL,
  KEY `FK3cmeao6l7k9quaf5abnv9tnch` (`wrestling_matches_list_wrestling_match_id`),
  KEY `FKn7huc1ibi930hccnbd0sj6kxt` (`opponents_wrestler_id`),
  CONSTRAINT `FK3cmeao6l7k9quaf5abnv9tnch` FOREIGN KEY (`wrestling_matches_list_wrestling_match_id`) REFERENCES `wrestling_match` (`wrestling_match_id`),
  CONSTRAINT `FKn7huc1ibi930hccnbd0sj6kxt` FOREIGN KEY (`opponents_wrestler_id`) REFERENCES `wrestler` (`wrestler_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestler_wrestling_matches_list`
--

LOCK TABLES `wrestler_wrestling_matches_list` WRITE;
/*!40000 ALTER TABLE `wrestler_wrestling_matches_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrestler_wrestling_matches_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestling_brand`
--

DROP TABLE IF EXISTS `wrestling_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestling_brand` (
  `wrestling_brand_id` bigint NOT NULL AUTO_INCREMENT,
  `wrestling_brand_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wrestling_brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestling_brand`
--

LOCK TABLES `wrestling_brand` WRITE;
/*!40000 ALTER TABLE `wrestling_brand` DISABLE KEYS */;
INSERT INTO `wrestling_brand` VALUES (1,'Smackdown'),(2,'Sangen');
/*!40000 ALTER TABLE `wrestling_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestling_match`
--

DROP TABLE IF EXISTS `wrestling_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestling_match` (
  `wrestling_match_id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `is_draw` bit(1) DEFAULT NULL,
  `is_money_in_the_bank_cash_in` bit(1) DEFAULT NULL,
  `is_title_match` bit(1) DEFAULT NULL,
  `rating` double NOT NULL,
  `wrestling_show_id` bigint DEFAULT NULL,
  `wrestling_title_id` bigint DEFAULT NULL,
  PRIMARY KEY (`wrestling_match_id`),
  KEY `FKj1s8154kmrwb6rs4h7tn0mhai` (`wrestling_show_id`),
  KEY `FKh9pga060btaacp1nmaiqidgrv` (`wrestling_title_id`),
  CONSTRAINT `FKh9pga060btaacp1nmaiqidgrv` FOREIGN KEY (`wrestling_title_id`) REFERENCES `wrestling_title` (`wrestling_title_id`),
  CONSTRAINT `FKj1s8154kmrwb6rs4h7tn0mhai` FOREIGN KEY (`wrestling_show_id`) REFERENCES `wrestling_show` (`wrestling_show_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestling_match`
--

LOCK TABLES `wrestling_match` WRITE;
/*!40000 ALTER TABLE `wrestling_match` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrestling_match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestling_month`
--

DROP TABLE IF EXISTS `wrestling_month`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestling_month` (
  `wrestling_month_id` bigint NOT NULL AUTO_INCREMENT,
  `wrestling_month_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wrestling_month_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestling_month`
--

LOCK TABLES `wrestling_month` WRITE;
/*!40000 ALTER TABLE `wrestling_month` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrestling_month` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestling_show`
--

DROP TABLE IF EXISTS `wrestling_show`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestling_show` (
  `wrestling_show_id` bigint NOT NULL AUTO_INCREMENT,
  `isppv` bit(1) DEFAULT NULL,
  `wrestling_brand_id` bigint DEFAULT NULL,
  `wrestling_month_id` bigint DEFAULT NULL,
  `wrestling_week_id` bigint DEFAULT NULL,
  `wrestling_year_id` bigint DEFAULT NULL,
  PRIMARY KEY (`wrestling_show_id`),
  KEY `FKqt22twqi7lo4b3xc2u18hbu2h` (`wrestling_brand_id`),
  KEY `FK4j4xbn5ryd3xpqy6jqtqp1gmy` (`wrestling_month_id`),
  KEY `FKmww25p917icqy1grecgebon4d` (`wrestling_week_id`),
  KEY `FKf3eb9x8jsqowh0yh1qf8itfeh` (`wrestling_year_id`),
  CONSTRAINT `FK4j4xbn5ryd3xpqy6jqtqp1gmy` FOREIGN KEY (`wrestling_month_id`) REFERENCES `wrestling_month` (`wrestling_month_id`),
  CONSTRAINT `FKf3eb9x8jsqowh0yh1qf8itfeh` FOREIGN KEY (`wrestling_year_id`) REFERENCES `wrestling_year` (`wrestling_year_id`),
  CONSTRAINT `FKmww25p917icqy1grecgebon4d` FOREIGN KEY (`wrestling_week_id`) REFERENCES `wrestling_week` (`wrestling_week_id`),
  CONSTRAINT `FKqt22twqi7lo4b3xc2u18hbu2h` FOREIGN KEY (`wrestling_brand_id`) REFERENCES `wrestling_brand` (`wrestling_brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestling_show`
--

LOCK TABLES `wrestling_show` WRITE;
/*!40000 ALTER TABLE `wrestling_show` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrestling_show` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestling_title`
--

DROP TABLE IF EXISTS `wrestling_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestling_title` (
  `wrestling_title_id` bigint NOT NULL AUTO_INCREMENT,
  `title_name` varchar(255) DEFAULT NULL,
  `wrestling_locker_room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`wrestling_title_id`),
  KEY `FKjy82xs4eudp0i935v8w7tcerh` (`wrestling_locker_room_id`),
  CONSTRAINT `FKjy82xs4eudp0i935v8w7tcerh` FOREIGN KEY (`wrestling_locker_room_id`) REFERENCES `locker_room` (`wrestling_locker_room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestling_title`
--

LOCK TABLES `wrestling_title` WRITE;
/*!40000 ALTER TABLE `wrestling_title` DISABLE KEYS */;
INSERT INTO `wrestling_title` VALUES (1,'Sangen Men\'s Championship',2),(2,'Sangen Women\'s Championship',1),(3,'Intercontinental Men\'s Championship',2),(4,'Intercontinental Women\'s Title',1),(5,'Men\'s Tag Team Championship',2),(6,'Women\'s Tag Team Championship',1);
/*!40000 ALTER TABLE `wrestling_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestling_title_wrestler_list`
--

DROP TABLE IF EXISTS `wrestling_title_wrestler_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestling_title_wrestler_list` (
  `wrestling_title_list_wrestling_title_id` bigint NOT NULL,
  `wrestler_list_wrestler_id` bigint NOT NULL,
  KEY `FK7b85oikce76cnxgoe6t8ovhet` (`wrestler_list_wrestler_id`),
  KEY `FKjoj6ct1dhae7gtsbg00ecd3b5` (`wrestling_title_list_wrestling_title_id`),
  CONSTRAINT `FK7b85oikce76cnxgoe6t8ovhet` FOREIGN KEY (`wrestler_list_wrestler_id`) REFERENCES `wrestler` (`wrestler_id`),
  CONSTRAINT `FKjoj6ct1dhae7gtsbg00ecd3b5` FOREIGN KEY (`wrestling_title_list_wrestling_title_id`) REFERENCES `wrestling_title` (`wrestling_title_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestling_title_wrestler_list`
--

LOCK TABLES `wrestling_title_wrestler_list` WRITE;
/*!40000 ALTER TABLE `wrestling_title_wrestler_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrestling_title_wrestler_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestling_week`
--

DROP TABLE IF EXISTS `wrestling_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestling_week` (
  `wrestling_week_id` bigint NOT NULL AUTO_INCREMENT,
  `wrestling_week_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wrestling_week_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestling_week`
--

LOCK TABLES `wrestling_week` WRITE;
/*!40000 ALTER TABLE `wrestling_week` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrestling_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrestling_year`
--

DROP TABLE IF EXISTS `wrestling_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrestling_year` (
  `wrestling_year_id` bigint NOT NULL AUTO_INCREMENT,
  `wrestling_year_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wrestling_year_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrestling_year`
--

LOCK TABLES `wrestling_year` WRITE;
/*!40000 ALTER TABLE `wrestling_year` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrestling_year` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sangenwrestlingdb'
--

--
-- Dumping routines for database 'sangenwrestlingdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-27 17:29:49
