-- MariaDB dump 10.19  Distrib 10.4.27-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: reboot
-- ------------------------------------------------------
-- Server version	10.4.27-MariaDB

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4l3j89hkh9f2p987piyh4pgb0` (`card_id`),
  KEY `FKb0e1g6c44ampoe1ondy9t6v8w` (`user_id`),
  KEY `id` (`id`),
  CONSTRAINT `FK4l3j89hkh9f2p987piyh4pgb0` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FKb0e1g6c44ampoe1ondy9t6v8w` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS activity_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `activity_before_insert` BEFORE INSERT ON `activity` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS activity_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `activity_before_update` BEFORE UPDATE ON `activity` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `file_nm` varchar(255) NOT NULL COMMENT 'file name',
  `type` varchar(255) DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpyjq6uiperx43dbsny1gjvxne` (`card_id`),
  KEY `id` (`id`),
  CONSTRAINT `FKpyjq6uiperx43dbsny1gjvxne` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (1,15,'chung ta','','igm',NULL,'2023-05-09 10:12:26','\0',NULL,'2023-05-09 10:12:26'),(2,15,'cua hein tai','','igm',NULL,'2023-05-09 10:12:26','\0',NULL,'2023-05-09 10:12:26'),(3,15,'heo kho','','zip',NULL,'2023-05-09 10:12:26','\0',NULL,'2023-05-09 10:12:32'),(4,15,'di nhung kin iem','','mp3',NULL,'2023-05-09 10:12:26','\0',NULL,'2023-05-09 10:12:32'),(5,15,'ngay mai nguoi luyen luu theo nhung giac mo tung co','','mp4',NULL,'2023-05-09 10:12:26','\0',NULL,'2023-05-09 10:12:32'),(6,15,'lieu rang chia tay trong em có quen được câu ca','',NULL,NULL,'2023-05-11 12:46:30','\0',NULL,'2023-05-11 12:46:30'),(7,15,'tinh yeu trong em co dau nao phoi pha','',NULL,NULL,'2023-05-11 12:46:30','\0',NULL,'2023-05-11 12:46:30'),(8,15,'đừng nhìn anh nữa đôi mắt ngày xưa','',NULL,NULL,'2023-05-11 12:46:30','\0',NULL,'2023-05-11 12:46:30'),(9,15,'Giờ ở đâu em còn là em','',NULL,NULL,'2023-05-11 12:46:30','\0',NULL,'2023-05-11 12:46:30'),(10,15,'em đã khác rồi','',NULL,NULL,'2023-05-11 12:46:30','\0',NULL,'2023-05-11 12:46:30'),(11,15,'em muốn quay lưng','',NULL,NULL,'2023-05-11 12:46:30','\0',NULL,'2023-05-11 12:46:30'),(12,15,'quên hết đi à','',NULL,NULL,'2023-05-11 12:46:30','\0',NULL,'2023-05-11 12:46:30'),(13,15,'tình yêu trong em giờ toàn giả dối','',NULL,NULL,'2023-05-11 12:46:30','\0',NULL,'2023-05-11 12:46:30'),(14,15,'anh không muốn lục tìm trong mơ','',NULL,NULL,'2023-05-11 12:46:30','\0',NULL,'2023-05-11 12:46:30'),(15,15,'anh không muốn đi tìm giấc mơ ngày hôm nao','',NULL,NULL,'2023-05-11 12:46:30','\0',NULL,'2023-05-11 12:46:30'),(16,15,'dick','pussy','exe',NULL,'2023-05-11 16:50:15','\0',NULL,'2023-05-11 16:50:15'),(17,4,'Screenshot 2023-03-17 091514.png','f909f5a9-7d7a-4a8a-90f9-bd397440647a.png','png','huyvu8051@gmail.com','2023-05-11 16:55:45','\0','huyvu8051@gmail.com','2023-05-11 16:55:45'),(18,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (3).png','a4a01a3b-d9e9-4860-acfc-2e3947943f9c.png','png','huyvu8051@gmail.com','2023-05-12 08:08:58','\0','huyvu8051@gmail.com','2023-05-12 08:08:58'),(19,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (4).png','7f4fda8a-7b42-4d95-b718-f43ed9692aca.png','png','huyvu8051@gmail.com','2023-05-12 08:08:58','\0','huyvu8051@gmail.com','2023-05-12 08:08:58'),(20,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (2).png','c678ee07-377a-4a9e-82ea-c57158209448.png','png','huyvu8051@gmail.com','2023-05-12 08:08:58','\0','huyvu8051@gmail.com','2023-05-12 08:08:58'),(21,15,'query-analize.txt','e128bd60-2738-4612-a861-22447499fc82.txt','txt','huyvu8051@gmail.com','2023-05-12 11:03:54','\0','huyvu8051@gmail.com','2023-05-12 11:03:54'),(22,15,'images.png','016c80a5-e120-43b2-ba37-c6c8d92098c0.png','png','huyvu8051@gmail.com','2023-05-12 11:03:55','\0','huyvu8051@gmail.com','2023-05-12 11:03:55'),(23,15,'images.jpg','a28a78a2-d74b-4be9-86c2-5eb64507a807.jpg','jpg','huyvu8051@gmail.com','2023-05-12 11:03:55','\0','huyvu8051@gmail.com','2023-05-12 11:03:55'),(24,15,'query-analize.txt','1ccd6520-b471-489d-b95c-f0113c8ba51f.txt','txt','huyvu8051@gmail.com','2023-05-12 11:10:42','\0','huyvu8051@gmail.com','2023-05-12 11:10:42'),(25,15,'images.png','580d8a48-c5b6-4ad1-9891-590e73b8da1a.png','png','huyvu8051@gmail.com','2023-05-12 11:10:42','\0','huyvu8051@gmail.com','2023-05-12 11:10:42'),(26,15,'images.jpg','dceead02-e7ab-4c6c-a7c6-1638d386d3be.jpg','jpg','huyvu8051@gmail.com','2023-05-12 11:10:42','\0','huyvu8051@gmail.com','2023-05-12 11:10:42'),(27,15,'images.jpg','c2d1b51f-1500-422e-8ce1-f9970795ba5d.jpg','jpg','huyvu8051@gmail.com','2023-05-12 11:15:13','\0','huyvu8051@gmail.com','2023-05-12 11:15:13'),(28,15,'query-analize.txt','c7c1a21d-e66e-4601-b8f3-f9a3b69435f8.txt','txt','huyvu8051@gmail.com','2023-05-12 11:15:13','\0','huyvu8051@gmail.com','2023-05-12 11:15:13'),(29,15,'images.png','57083ed8-367c-459a-bae6-ad5f635021e4.png','png','huyvu8051@gmail.com','2023-05-12 11:15:13','\0','huyvu8051@gmail.com','2023-05-12 11:15:13'),(30,15,'query-analize.txt','877940a4-5d95-49a5-aba0-b460359e003c.txt','txt','huyvu8051@gmail.com','2023-05-12 11:16:02','\0','huyvu8051@gmail.com','2023-05-12 11:16:02'),(31,15,'images.png','23c8e969-67a6-47e6-b7d1-9f2b13f379ec.png','png','huyvu8051@gmail.com','2023-05-12 11:16:02','\0','huyvu8051@gmail.com','2023-05-12 11:16:02'),(32,15,'images.jpg','a902087f-b436-4a4a-a77d-a99bad823ce9.jpg','jpg','huyvu8051@gmail.com','2023-05-12 11:16:02','\0','huyvu8051@gmail.com','2023-05-12 11:16:02'),(33,15,'images.jpg','65788c48-dd0c-4307-894c-0274e22b0784.jpg','jpg','huyvu8051@gmail.com','2023-05-12 11:16:45','\0','huyvu8051@gmail.com','2023-05-12 11:16:45'),(34,15,'images.png','916c3dce-30db-46e6-830e-493c584dcdf4.png','png','huyvu8051@gmail.com','2023-05-12 11:16:45','\0','huyvu8051@gmail.com','2023-05-12 11:16:45'),(35,15,'query-analize.txt','1683cb1b-85ec-459a-bb28-7e73df749511.txt','txt','huyvu8051@gmail.com','2023-05-12 11:16:45','\0','huyvu8051@gmail.com','2023-05-12 11:16:45'),(36,15,'query-analize.txt','7b47a76c-532b-43ba-a974-b4e8c7fefbce.txt','txt','huyvu8051@gmail.com','2023-05-12 11:18:24','\0','huyvu8051@gmail.com','2023-05-12 11:18:24'),(37,15,'images.jpg','d8467060-ad88-448e-ba51-2531ffba3b2e.jpg','jpg','huyvu8051@gmail.com','2023-05-12 11:18:24','\0','huyvu8051@gmail.com','2023-05-12 11:18:24'),(38,15,'images.png','9ad58bac-0f3a-48c5-92aa-a52cd9054840.png','png','huyvu8051@gmail.com','2023-05-12 11:18:24','\0','huyvu8051@gmail.com','2023-05-12 11:18:24'),(39,15,'images.png','cd1a04ba-879a-4ee7-8b0c-93d658f01900.png','png','huyvu8051@gmail.com','2023-05-12 11:19:20','\0','huyvu8051@gmail.com','2023-05-12 11:19:20'),(40,15,'images.jpg','c7c0397a-e88a-4098-ae10-97b21bab43fc.jpg','jpg','huyvu8051@gmail.com','2023-05-12 11:19:20','\0','huyvu8051@gmail.com','2023-05-12 11:19:20'),(41,15,'query-analize.txt','a78333eb-4b7d-44c8-8a8b-d2d3f450b92f.txt','txt','huyvu8051@gmail.com','2023-05-12 11:19:21','\0','huyvu8051@gmail.com','2023-05-12 11:19:21'),(42,15,'query-analize.txt','28f01343-9824-402e-bdaa-f37a942105ca.txt','txt','huyvu8051@gmail.com','2023-05-12 11:20:30','\0','huyvu8051@gmail.com','2023-05-12 11:20:30'),(43,15,'images.png','77f72612-2a09-4fcd-a3c9-2c13b56a52d4.png','png','huyvu8051@gmail.com','2023-05-12 11:20:30','\0','huyvu8051@gmail.com','2023-05-12 11:20:30'),(44,15,'images.jpg','100765ba-3fdb-4268-a983-15c9fbe80123.jpg','jpg','huyvu8051@gmail.com','2023-05-12 11:20:30','\0','huyvu8051@gmail.com','2023-05-12 11:20:30'),(45,15,'Default.rdp','f32d9aa2-00f9-40b0-b1a7-53dcf03e87a7.rdp','rdp','huyvu8051@gmail.com','2023-05-12 11:22:41','\0','huyvu8051@gmail.com','2023-05-12 11:22:41'),(46,15,'createfile.xlsx','4ddfdca7-e9ff-4f79-8538-44c11bb7291e.xlsx','xlsx','huyvu8051@gmail.com','2023-05-12 11:22:42','\0','huyvu8051@gmail.com','2023-05-12 11:22:42'),(47,15,'huyoi.xlsx','576b6621-bb3b-4e65-8a0f-ef2bb2c6cd4d.xlsx','xlsx','huyvu8051@gmail.com','2023-05-12 11:22:42','\0','huyvu8051@gmail.com','2023-05-12 11:22:42'),(48,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (2).png','1c99ff41-2908-4d98-9a68-bfb1f1ecf8ed.png','png','huyvu8051@gmail.com','2023-05-12 11:25:18','\0','huyvu8051@gmail.com','2023-05-12 11:25:18'),(49,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (3).png','9257a97f-abdd-4d31-9bb7-43e0d6fe7b8a.png','png','huyvu8051@gmail.com','2023-05-12 11:25:18','\0','huyvu8051@gmail.com','2023-05-12 11:25:18'),(50,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (1).png','108d4e77-962f-4ea7-9d8b-43e1f23c8c34.png','png','huyvu8051@gmail.com','2023-05-12 11:25:18','\0','huyvu8051@gmail.com','2023-05-12 11:25:18'),(51,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (4).png','6e593c38-0785-4d58-a1f1-00b94f971dc7.png','png','huyvu8051@gmail.com','2023-05-12 11:25:18','\0','huyvu8051@gmail.com','2023-05-12 11:25:18'),(52,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89.png','ff2756d2-d4b8-46e2-9d2b-2798eaec4036.png','png','huyvu8051@gmail.com','2023-05-12 11:25:18','\0','huyvu8051@gmail.com','2023-05-12 11:25:18'),(53,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (2).png','8738b745-32f0-44c1-947f-55e2001940dc.png','png','huyvu8051@gmail.com','2023-05-12 11:39:23','\0','huyvu8051@gmail.com','2023-05-12 11:39:23'),(54,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (3).png','c726c18d-7744-434e-9a25-aef10a805548.png','png','huyvu8051@gmail.com','2023-05-12 11:39:23','\0','huyvu8051@gmail.com','2023-05-12 11:39:23'),(55,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (4).png','8914f155-b2cf-479f-a340-ac413d821a84.png','png','huyvu8051@gmail.com','2023-05-12 11:39:23','\0','huyvu8051@gmail.com','2023-05-12 11:39:23'),(56,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89.png','966472dc-cae9-4a7e-bdd0-7e1664ec4ce3.png','png','huyvu8051@gmail.com','2023-05-12 11:39:23','\0','huyvu8051@gmail.com','2023-05-12 11:39:23'),(57,15,'label_generator.rar','ff861802-366e-4408-8da9-ccfd6c3a59c0.rar','rar','huyvu8051@gmail.com','2023-05-12 11:39:23','\0','huyvu8051@gmail.com','2023-05-12 11:39:23'),(58,15,'pexels-asad-photo-maldives-457881.jpg','8ab85f52-43db-440e-8441-bd76b23d82c3.jpg','jpg','huyvu8051@gmail.com','2023-05-12 11:39:23','\0','huyvu8051@gmail.com','2023-05-12 11:39:23'),(59,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (1).png','047102f1-48c3-4c3c-bdd9-370bd33cf5f6.png','png','huyvu8051@gmail.com','2023-05-12 11:39:23','\0','huyvu8051@gmail.com','2023-05-12 11:39:23'),(60,15,'pexels-baskin-creative-studios-1766838.jpg','2a6d6522-fd4a-4525-8553-84bb4a0c42e5.jpg','jpg','huyvu8051@gmail.com','2023-05-12 11:39:23','\0','huyvu8051@gmail.com','2023-05-12 11:39:23'),(61,15,'BusinessProcessFlow(SCM & MES).png','1cbcc2c0-308a-48a2-9af4-04be9ca93f3e.png','png','huyvu8051@gmail.com','2023-05-12 11:44:30','\0','huyvu8051@gmail.com','2023-05-12 11:44:30'),(62,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (4).png','e6c87f7f-9694-4ce7-96fd-c6b435aeec05.png','png','huyvu8051@gmail.com','2023-05-12 11:46:00','\0','huyvu8051@gmail.com','2023-05-12 11:46:00'),(63,15,'khangnh.png','7b7c8226-6681-4b2d-9e21-7915f0a346c6.png','png','huyvu8051@gmail.com','2023-05-12 11:49:58','\0','huyvu8051@gmail.com','2023-05-12 11:49:58'),(64,15,'Screenshot_2023-01-03-23-37-27.png','d8276fbb-e9cf-40cc-9594-529b75d0c542.png','png','huyvu8051@gmail.com','2023-05-12 11:50:54','\0','huyvu8051@gmail.com','2023-05-12 11:50:54'),(65,4,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (3).png','1752caf1-ef25-4d47-8a98-6ee45166e7c9.png','png','huyvu8051@gmail.com','2023-05-12 12:03:33','\0','huyvu8051@gmail.com','2023-05-12 12:03:33'),(66,4,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (2).png','c676ef16-d753-4852-b115-3049364f9659.png','png','huyvu8051@gmail.com','2023-05-12 12:03:33','\0','huyvu8051@gmail.com','2023-05-12 12:03:33'),(67,4,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (1).png','a5c59a1b-dd60-4df0-a6c5-beca3165678c.png','png','huyvu8051@gmail.com','2023-05-12 12:03:33','\0','huyvu8051@gmail.com','2023-05-12 12:03:33'),(68,4,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89.png','bdd470d9-fef5-44b8-9982-666416632594.png','png','huyvu8051@gmail.com','2023-05-12 12:03:33','\0','huyvu8051@gmail.com','2023-05-12 12:03:33'),(69,4,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (4).png','995d2609-ca75-41f3-bbe2-11d431aabde1.png','png',NULL,'2023-05-12 12:03:33','\0',NULL,'2023-05-12 12:03:33'),(70,4,'7b7c8226-6681-4b2d-9e21-7915f0a346c6.png','b2b4b196-b5b7-438c-80e3-eb1372061702.png','png','huyvu8051@gmail.com','2023-05-12 12:06:06','\0','huyvu8051@gmail.com','2023-05-12 12:06:06'),(71,15,'Screenshot 2023-03-07 111714.png','6a0bcf0a-c43e-492d-9178-263fe6d257ea.png','png','huyvu8051@gmail.com','2023-05-12 13:19:56','\0','huyvu8051@gmail.com','2023-05-12 13:19:56'),(72,15,'dbb493ad-9d60-496e-9b5f-0f1ff9c6ba89 (1).png','cca21ad9-97df-4bd6-a8ac-ddb584127890.png','png','huyvu8051@gmail.com','2023-05-15 07:54:13','\0','huyvu8051@gmail.com','2023-05-15 07:54:13'),(73,15,'Screenshot 2023-03-14 145916.png','07fac5c2-a7d9-4b75-839a-e2e98f04f5aa.png','png','huyvu8051@gmail.com','2023-05-22 08:36:35','\0','huyvu8051@gmail.com','2023-05-22 08:36:35'),(74,15,'Screenshot 2023-03-14 132540.png','c9ce19fd-5533-4d64-96ce-45ea2090f4f2.png','png','huyvu8051@gmail.com','2023-05-23 13:15:53','\0','huyvu8051@gmail.com','2023-05-23 13:15:53'),(75,15,'Screenshot 2023-03-14 145916.png','431a35a8-2db5-4954-9d4c-7d9c9c428a85.png','png','huyvu8051@gmail.com','2023-05-23 13:17:17','\0','huyvu8051@gmail.com','2023-05-23 13:17:17'),(76,15,'Screenshot (1).png','d86c4ef1-4e69-4928-b13d-1c170187e12c.png','png','huyvu8051@gmail.com','2023-05-23 13:18:46','\0','huyvu8051@gmail.com','2023-05-23 13:18:46'),(77,15,'Screenshot 2023-03-16 131845.png','04d6933b-2d91-4600-8e70-4b3b30c1f944.png','png','huyvu8051@gmail.com','2023-05-23 13:19:12','\0','huyvu8051@gmail.com','2023-05-23 13:19:12'),(78,15,'Screenshot (5).png','7ec48ac7-58ff-4f77-a725-9679d578d9ea.png','png','huyvu8051@gmail.com','2023-05-23 13:19:36','\0','huyvu8051@gmail.com','2023-05-23 13:19:36'),(79,15,'Screenshot 2023-03-07 111714.png','071910b3-2842-455e-908a-174f6cc40910.png','png','huyvu8051@gmail.com','2023-05-23 13:20:01','\0','huyvu8051@gmail.com','2023-05-23 13:20:01'),(80,4,'Screenshot 2023-03-14 132540.png','1976a5ba-49c8-4299-838d-46b9f01eaa13.png','png','huyvu8051@gmail.com','2023-05-29 09:38:08','\0','huyvu8051@gmail.com','2023-05-29 09:38:08'),(81,4,'Screenshot 2023-03-14 132540.png','9f28abed-c9e9-4054-af01-3ebd52e14c03.png','png','huyvu8051@gmail.com','2023-05-29 14:32:55','\0','huyvu8051@gmail.com','2023-05-29 14:32:55'),(82,15,'FileZilla_3.64.0_win64_sponsored2-setup.exe','7fe954e0-ad00-4733-afd7-6df4c072cd17.exe','exe','huyvu8051@gmail.com','2023-05-30 08:10:14','\0','huyvu8051@gmail.com','2023-05-30 08:10:14'),(83,15,'Screenshot 2023-03-14 132540.png','077381c4-f3d2-4443-b8ed-1558d8686141.png','png','huyvu8051@gmail.com','2023-05-30 08:43:07','\0','huyvu8051@gmail.com','2023-05-30 08:43:07'),(84,15,'Screenshot 2023-03-07 111714.png','9d1ff391-a02a-4f98-9e08-3dc2c1decbc5.png','png','huyvu8051@gmail.com','2023-05-30 08:49:36','\0','huyvu8051@gmail.com','2023-05-30 08:49:36'),(85,15,'Screenshot 2023-03-17 091514.png','f1993160-c3d7-47ba-859d-926ce8968683.png','png','huyvu8051@gmail.com','2023-05-30 13:26:34','\0','huyvu8051@gmail.com','2023-05-30 13:26:34'),(86,15,'Screenshot 2023-03-20 151853.png','83077dc1-e4f6-4bd1-b513-5d3f32a88ea0.png','png','huyvu8051@gmail.com','2023-05-30 13:27:15','\0','huyvu8051@gmail.com','2023-05-30 13:27:15'),(87,15,'Screenshot (7).png','71ebeddb-2292-46fe-9c4d-6e8d76a3c1d0.png','png','huyvu8051@gmail.com','2023-05-30 13:29:15','\0','huyvu8051@gmail.com','2023-05-30 13:29:15'),(88,15,'Screenshot 2023-03-17 150248.png','ceb14b52-396f-4328-89a3-a48bc88413e8.png','png','huyvu8051@gmail.com','2023-05-30 13:29:29','\0','huyvu8051@gmail.com','2023-05-30 13:29:29'),(89,15,'Screenshot 2023-03-14 132540.png','ff7bc300-da72-4687-9955-6afd48d2ec2a.png','png','huyvu8051@gmail.com','2023-05-30 13:29:47','\0','huyvu8051@gmail.com','2023-05-30 13:29:47'),(90,15,'putty-64bit-0.78-installer.msi','efafce48-c898-4e5b-8173-a5f2b0684468.msi','msi','huyvu8051@gmail.com','2023-05-30 13:30:21','\0','huyvu8051@gmail.com','2023-05-30 13:30:21'),(91,15,'glue-intellij-settings.zip','7664ad2f-df18-4680-ad66-ca444fa6a532.zip','zip','huyvu8051@gmail.com','2023-05-30 13:34:27','\0','huyvu8051@gmail.com','2023-05-30 13:34:27'),(92,15,'glue-posco-m0b.rar','d5aed5df-e02f-4b08-bb5b-f49fd59c99ea.rar','rar','huyvu8051@gmail.com','2023-05-30 13:34:27','\0','huyvu8051@gmail.com','2023-05-30 13:34:27'),(93,15,'Extract.zip','95b87091-d902-4c33-a35e-ed119da5e4fe.zip','zip','huyvu8051@gmail.com','2023-05-30 13:34:30','\0','huyvu8051@gmail.com','2023-05-30 13:34:30'),(94,23,'Screenshot (8).png','626c4bdd-e542-4b48-83f2-e69bd136a929.png','png','huyvu8051@gmail.com','2023-05-30 13:59:24','\0','huyvu8051@gmail.com','2023-05-30 13:59:24');
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS attachment_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `attachment_before_insert` BEFORE INSERT ON `attachment` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS attachment_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `attachment_before_update` BEFORE UPDATE ON `attachment` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `workspace_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `background_image` varchar(255) DEFAULT NULL,
  `stared` bit(1) NOT NULL DEFAULT b'0',
  `visibility` int(11) DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh8r4ryxrng25r7ko3yh5eaudu` (`workspace_id`),
  KEY `id` (`id`),
  CONSTRAINT `FKh8r4ryxrng25r7ko3yh5eaudu` FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,110,'nguoi theo huong hoa',NULL,'\0',NULL,'huyvu8051@gmail.com','2023-02-26 21:41:13','',NULL,'2023-02-26 21:51:15'),(2,110,'nguoi theo huong hoa',NULL,'\0',NULL,'huyvu8051@gmail.com','2023-02-26 21:51:29','\0','huyvu8051@gmail.com','2023-02-26 21:51:29'),(3,110,'Chung ta cua hien tai',NULL,'\0',NULL,'huyvu8051@gmail.com','2023-02-28 19:07:18','\0','huyvu8051@gmail.com','2023-02-28 19:07:18'),(4,110,'em cua ngay hom qua',NULL,'\0',NULL,'huyvu8051@gmail.com','2023-02-28 19:13:20','\0','huyvu8051@gmail.com','2023-02-28 19:13:20'),(5,112,'cc',NULL,'\0',NULL,'ng.nguyencntt@gmail.com','2023-03-09 11:32:39','\0','ng.nguyencntt@gmail.com','2023-03-09 11:32:39'),(6,113,' co ai thuong em',NULL,'\0',NULL,'huyvu8051@gmail.com','2023-05-05 10:23:37','\0','huyvu8051@gmail.com','2023-05-05 10:23:37'),(7,114,'Security',NULL,'\0',NULL,'huyvu8051@gmail.com','2023-05-30 13:58:32','\0','huyvu8051@gmail.com','2023-05-30 13:58:32');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS board_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `board_before_insert` BEFORE INSERT ON `board` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS board_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `board_before_update` BEFORE UPDATE ON `board` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lizt_id` bigint(20) DEFAULT NULL COMMENT 'Lizt Id',
  `b_id` bigint(20) DEFAULT NULL COMMENT 'Board Id',
  `title` varchar(255) DEFAULT NULL,
  `ordinal` decimal(20,6) NOT NULL DEFAULT 0.000000,
  `automation` varchar(255) DEFAULT NULL,
  `cover_color` varchar(255) DEFAULT NULL,
  `cover_size` varchar(255) DEFAULT NULL,
  `cover_url` varchar(255) DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`description`)),
  `due_date` datetime DEFAULT NULL,
  `due_date_complete` bit(1) NOT NULL DEFAULT b'0',
  `due_date_reminder` varchar(255) DEFAULT NULL,
  `is_template` bit(1) NOT NULL DEFAULT b'0',
  `location` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2pmnauf5d5cg76rtydn0dofox` (`lizt_id`),
  KEY `id` (`id`),
  KEY `b_id` (`b_id`),
  FULLTEXT KEY `card_title_index` (`title`),
  CONSTRAINT `FK2pmnauf5d5cg76rtydn0dofox` FOREIGN KEY (`lizt_id`) REFERENCES `lizt` (`id`),
  CONSTRAINT `FK_b_id` FOREIGN KEY (`b_id`) REFERENCES `board` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,42,4,'nguoi theo huong hoa',50.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-02 19:29:44','\0',NULL,'2023-05-30 13:13:14'),(2,41,4,'Chung ta cua hien heo kho did nhung kini em sdfs dfsdf sdkf kdkdk kdkf dchungt acua hen tai',25.000000,NULL,NULL,NULL,'183fb890-84d0-442b-9f84-54ed7f4b4ab4.png','{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-02 19:31:51','\0',NULL,'2023-05-30 13:13:14'),(3,48,4,'nguoi theo huong hoa may mu giang loi',130.600000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-02 19:35:51','\0',NULL,'2023-05-30 13:13:14'),(4,49,4,'khong phai dang vua dau',0.000000,NULL,NULL,NULL,'9f28abed-c9e9-4054-af01-3ebd52e14c03.png','{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-02 21:28:45','\0',NULL,'2023-05-30 13:13:14'),(5,54,4,'',0.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-02 21:39:09','\0',NULL,'2023-05-30 13:13:14'),(6,41,4,'co ai thuong em den vvay, co ai can em den the',50.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-02 21:39:10','\0',NULL,'2023-05-30 13:13:14'),(7,39,4,'nguoi theo huong hoa',126.100000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-02 22:10:22','\0',NULL,'2023-05-30 13:13:14'),(8,9,2,'chung ta',200.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-03 08:17:58','\0',NULL,'2023-05-30 13:13:14'),(9,26,2,'nguoi theo huong hoa',25.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-03 08:18:01','\0',NULL,'2023-05-30 13:13:14'),(10,13,2,'em cua ngay hom qua',0.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-03 08:18:07','\0',NULL,'2023-05-30 13:13:14'),(11,10,2,'la ang may ben troi voi vang ngang qua',236.500000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-03 08:18:28','\0',NULL,'2023-05-30 13:13:14'),(12,26,2,'cu nhu vay thoi',50.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-03 08:18:52','\0',NULL,'2023-05-30 13:13:14'),(13,23,2,'khong mot loi',25.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-03 08:18:58','\0',NULL,'2023-05-30 13:13:14'),(14,10,2,'lang le the chia xa',210.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-03 08:19:11','\0',NULL,'2023-05-30 13:13:14'),(15,42,4,'Có cái đầu bưởi khong lam ma odi co an',0.000000,NULL,NULL,NULL,'ff7bc300-da72-4687-9955-6afd48d2ec2a.png','{\"time\":1685427392808,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến chung ta cuar hei nrtaisjfjasdi id did dsaifiasdf\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-07 15:08:45','\0','huyvu8051@gmail.com','2023-05-30 13:29:47'),(16,10,2,'chung ta cuah ien tai',211.500000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'vuvanhuy15082001@gmail.com','2023-03-08 14:59:52','\0',NULL,'2023-05-30 13:13:14'),(17,10,2,'chung ta cua hien tai',209.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'vuvanhuy15082001@gmail.com','2023-03-08 15:00:13','\0',NULL,'2023-05-30 13:13:14'),(18,26,2,'asdfasdfsdf',-25.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-03-08 15:01:44','\0',NULL,'2023-05-30 13:13:14'),(19,26,2,'chung ta cua hien tai',0.000000,NULL,NULL,NULL,NULL,'{\"time\":1685418401959,\"blocks\":[{\"id\":\"6I9fMeNkqD\",\"type\":\"paragraph\",\"data\":{\"text\":\"co cai dau buoi\"}},{\"id\":\"uYnlAcVTvO\",\"type\":\"paragraph\",\"data\":{\"text\":\"heo kho di nhung ki niem xua&nbsp; kia\"}},{\"id\":\"yyrClTxlf_\",\"type\":\"table\",\"data\":{\"withHeadings\":true,\"content\":[[\"asdfasdf\",\"fasdfasdf\"],[\"asdfad\",\"sfasd\"],[\"gfgjhfghfghghjgjhk\",\"\"]]}},{\"id\":\"xO-XvjTPH0\",\"type\":\"quote\",\"data\":{\"text\":\"Sống trên đời, làm nhiều thì ăn nhiều, làm ít thì ăn ít, không làm thì chỉ asdfadssfasd có ăn bầu đuồi ăn shit thôi asdf asdf&nbsp;<br><br>Khong lam am doi co an thi chi co an dau buoi an cuc thoi\",\"caption\":\"quote chiến\",\"alignment\":\"left\"}},{\"id\":\"lyy4fHS4W5\",\"type\":\"code\",\"data\":{\"code\":\"System.out.print(\\\"tôy yêu em Bình ❤️\\\");\\n\"}}],\"version\":\"2.26.5\"}',NULL,'\0',NULL,'\0',NULL,NULL,'vuvanhuy15082001@gmail.com','2023-03-08 15:02:50','\0',NULL,'2023-05-30 13:13:14'),(20,58,5,'222',25.000000,NULL,NULL,NULL,NULL,NULL,NULL,'\0',NULL,'\0',NULL,NULL,'ng.nguyencntt@gmail.com','2023-03-09 11:32:55','\0','ng.nguyencntt@gmail.com','2023-03-09 11:33:03'),(21,59,5,'22',25.000000,NULL,NULL,NULL,NULL,NULL,NULL,'\0',NULL,'\0',NULL,NULL,'ng.nguyencntt@gmail.com','2023-03-09 11:32:59','\0','ng.nguyencntt@gmail.com','2023-03-09 11:33:35'),(22,61,6,'nguoi theo huong hoa may mu giang loi',0.000000,NULL,NULL,NULL,NULL,NULL,NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-05-05 10:24:09','\0','huyvu8051@gmail.com','2023-05-05 13:03:55'),(23,62,7,'Thi chi co an dau buoi an cuc thooi asfdasdf',0.000000,NULL,NULL,NULL,'626c4bdd-e542-4b48-83f2-e69bd136a929.png',NULL,NULL,'\0',NULL,'\0',NULL,NULL,'huyvu8051@gmail.com','2023-05-30 13:59:09','\0','huyvu8051@gmail.com','2023-05-30 14:34:03');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS card_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `card_before_insert` BEFORE INSERT ON `card` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS card_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `card_before_update` BEFORE UPDATE ON `card` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `card_member`
--

DROP TABLE IF EXISTS `card_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `notification` bit(1) NOT NULL DEFAULT b'0',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKq5low2n4jxef19lg35k6syqru` (`user_id`),
  KEY `id` (`id`),
  KEY `card_id` (`card_id`),
  CONSTRAINT `FKgp6lai9ewkcfodigcua5taanf` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FKq5low2n4jxef19lg35k6syqru` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_member`
--

LOCK TABLES `card_member` WRITE;
/*!40000 ALTER TABLE `card_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS card_member_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `card_member_before_insert` BEFORE INSERT ON `card_member` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS card_member_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `card_member_before_update` BEFORE UPDATE ON `card_member` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `checklist`
--

DROP TABLE IF EXISTS `checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checklist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `show_checked_items` bit(1) NOT NULL DEFAULT b'0',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmq05kujpd06x59cm9u46s9eui` (`card_id`),
  KEY `id` (`id`),
  CONSTRAINT `FKmq05kujpd06x59cm9u46s9eui` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checklist`
--

LOCK TABLES `checklist` WRITE;
/*!40000 ALTER TABLE `checklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `checklist` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS checklist_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checklist_before_insert` BEFORE INSERT ON `checklist` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS checklist_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checklist_before_update` BEFORE UPDATE ON `checklist` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `checklist_item`
--

DROP TABLE IF EXISTS `checklist_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checklist_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `checklist_id` bigint(20) DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `due_date_reminder` varchar(255) DEFAULT NULL,
  `is_check` bit(1) NOT NULL DEFAULT b'0',
  `title` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo1pmhdej4mt1uhfrpq9hn68hu` (`user_id`),
  KEY `FKn8v1y7el5kl0xefukq4nu3gfm` (`checklist_id`),
  KEY `id` (`id`),
  CONSTRAINT `FKn8v1y7el5kl0xefukq4nu3gfm` FOREIGN KEY (`checklist_id`) REFERENCES `checklist` (`id`),
  CONSTRAINT `FKo1pmhdej4mt1uhfrpq9hn68hu` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checklist_item`
--

LOCK TABLES `checklist_item` WRITE;
/*!40000 ALTER TABLE `checklist_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `checklist_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS checklist_item_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checklist_item_before_insert` BEFORE INSERT ON `checklist_item` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS checklist_item_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `checklist_item_before_update` BEFORE UPDATE ON `checklist_item` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `label`
--

DROP TABLE IF EXISTS `label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `board_id` bigint(20) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnl4pv15ws9o049sxm6c6dl8oy` (`board_id`),
  KEY `id` (`id`),
  CONSTRAINT `FKnl4pv15ws9o049sxm6c6dl8oy` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `label`
--

LOCK TABLES `label` WRITE;
/*!40000 ALTER TABLE `label` DISABLE KEYS */;
INSERT INTO `label` VALUES (1,4,'{\"backgroundColor\":\"#5f3811\",\"color\":\"#b6c2cf\"}','Bug',NULL,'2023-05-08 15:41:39','\0','huyvu8051@gmail.com','2023-05-29 15:15:13'),(2,4,'{\"backgroundColor\":\"#60c6d2\",\"color\":\"#1d2125\"}','Feature',NULL,'2023-05-08 15:41:39','\0','huyvu8051@gmail.com','2023-05-29 15:22:58'),(3,4,'{}','Help wanted',NULL,'2023-05-08 15:41:39','\0',NULL,'2023-05-29 14:30:28'),(4,4,'{\"backgroundColor\":\"#4bce97\",\"color\":\"#1d2125\"}','Invalid',NULL,'2023-05-08 15:41:39','\0','huyvu8051@gmail.com','2023-05-30 08:15:11'),(5,4,'{}','Wontfix',NULL,'2023-05-08 15:41:39','\0',NULL,'2023-05-29 14:30:28'),(6,4,'{\"backgroundColor\":\"#f87462\",\"color\":\"#1d2125\"}','Documentation',NULL,'2023-05-08 15:41:39','\0','huyvu8051@gmail.com','2023-05-29 15:45:24'),(7,4,'{\"backgroundColor\":\"#50253f\",\"color\":\"#b6c2cf\"}','No comments',NULL,'2023-05-23 09:10:39','\0','huyvu8051@gmail.com','2023-05-29 15:45:28'),(8,4,'{\"backgroundColor\":\"#974f0c\",\"color\":\"#b6c2cf\"}','Unchecked',NULL,'2023-05-23 10:29:48','\0','huyvu8051@gmail.com','2023-05-29 15:45:31'),(9,4,'{\"backgroundColor\":\"#94c748\",\"color\":\"#1d2125\"}','Wanted',NULL,'2023-05-23 11:32:02','\0','huyvu8051@gmail.com','2023-05-29 15:45:34'),(18,4,'{\"backgroundColor\":\"#f87462\",\"color\":\"#1d2125\"}','Binh Nguyen','huyvu8051@gmail.com','2023-05-29 14:52:24','\0','huyvu8051@gmail.com','2023-05-29 15:42:52'),(19,4,'{\"backgroundColor\":\"#f87462\",\"color\":\"#1d2125\"}','VietNam','huyvu8051@gmail.com','2023-05-29 14:52:45','\0','huyvu8051@gmail.com','2023-05-29 15:42:46'),(24,4,'{\"backgroundColor\":\"#faa53d\",\"color\":\"#1d2125\"}','Posco','huyvu8051@gmail.com','2023-05-29 14:59:45','\0','huyvu8051@gmail.com','2023-05-29 15:42:35'),(25,4,'{\"backgroundColor\":\"#e774bb\",\"color\":\"#1d2125\"}','adsfasdfasf','huyvu8051@gmail.com','2023-05-29 15:00:04','','huyvu8051@gmail.com','2023-05-29 15:41:32'),(26,4,'{\"backgroundColor\":\"#e774bb\",\"color\":\"#1d2125\"}','Chung ta ','huyvu8051@gmail.com','2023-05-29 15:41:50','\0','huyvu8051@gmail.com','2023-05-29 15:41:50'),(27,4,'{\"backgroundColor\":\"#216e4e\",\"color\":\"#b6c2cf\"}','small dick','huyvu8051@gmail.com','2023-05-29 15:43:12','','huyvu8051@gmail.com','2023-05-29 15:44:58'),(28,4,'{\"backgroundColor\":\"#e774bb\",\"color\":\"#1d2125\"}','=))','vuvanhuy15082001@gmail.com','2023-05-29 15:46:32','\0','vuvanhuy15082001@gmail.com','2023-05-29 15:46:45');
/*!40000 ALTER TABLE `label` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS label_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `label_before_insert` BEFORE INSERT ON `label` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS label_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `label_before_update` BEFORE UPDATE ON `label` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `labeled`
--

DROP TABLE IF EXISTS `labeled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labeled` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` bigint(20) NOT NULL,
  `label_id` bigint(20) NOT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKt0fjqpq3kfepviqionu0qu866` (`label_id`),
  KEY `id` (`id`),
  KEY `card_id` (`card_id`),
  CONSTRAINT `FKf248152pjipi76om7ie74wde9` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FKt0fjqpq3kfepviqionu0qu866` FOREIGN KEY (`label_id`) REFERENCES `label` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labeled`
--

LOCK TABLES `labeled` WRITE;
/*!40000 ALTER TABLE `labeled` DISABLE KEYS */;
INSERT INTO `labeled` VALUES (1,15,1,NULL,'2023-05-08 15:42:44','\0','huyvu8051@gmail.com','2023-05-29 14:33:21'),(2,15,2,NULL,'2023-05-08 15:42:44','\0','huyvu8051@gmail.com','2023-05-29 15:21:15'),(3,15,3,NULL,'2023-05-08 15:42:44','\0','huyvu8051@gmail.com','2023-05-30 08:14:57'),(4,15,4,NULL,'2023-05-08 15:42:44','\0','huyvu8051@gmail.com','2023-05-30 08:15:12'),(5,15,5,NULL,'2023-05-08 15:42:44','','huyvu8051@gmail.com','2023-05-23 12:54:15'),(6,15,6,NULL,'2023-05-08 15:42:54','','huyvu8051@gmail.com','2023-05-23 12:54:16'),(7,15,7,NULL,'2023-05-23 10:08:41','\0','huyvu8051@gmail.com','2023-05-23 12:25:47'),(8,15,9,'huyvu8051@gmail.com','2023-05-23 12:25:38','\0','vuvanhuy15082001@gmail.com','2023-05-23 12:47:02'),(9,15,8,'huyvu8051@gmail.com','2023-05-23 12:25:49','','vuvanhuy15082001@gmail.com','2023-05-23 13:11:01'),(10,4,1,'huyvu8051@gmail.com','2023-05-29 08:11:01','\0','huyvu8051@gmail.com','2023-05-29 08:11:09'),(11,4,2,'huyvu8051@gmail.com','2023-05-29 08:11:02','\0','huyvu8051@gmail.com','2023-05-29 08:11:08'),(12,4,3,'huyvu8051@gmail.com','2023-05-29 08:11:02','\0','huyvu8051@gmail.com','2023-05-29 08:11:08'),(13,4,4,'huyvu8051@gmail.com','2023-05-29 08:11:02','\0','huyvu8051@gmail.com','2023-05-29 08:11:07'),(14,4,5,'huyvu8051@gmail.com','2023-05-29 08:11:03','\0','huyvu8051@gmail.com','2023-05-29 08:11:09'),(15,4,6,'huyvu8051@gmail.com','2023-05-29 08:11:10','\0','huyvu8051@gmail.com','2023-05-29 08:11:10'),(16,4,8,'huyvu8051@gmail.com','2023-05-29 08:11:10','\0','huyvu8051@gmail.com','2023-05-29 08:11:10'),(17,4,7,'huyvu8051@gmail.com','2023-05-29 08:11:11','\0','huyvu8051@gmail.com','2023-05-29 08:11:11'),(18,4,9,'huyvu8051@gmail.com','2023-05-29 08:11:11','\0','huyvu8051@gmail.com','2023-05-29 08:11:11'),(19,15,18,'huyvu8051@gmail.com','2023-05-29 15:42:55','\0','huyvu8051@gmail.com','2023-05-29 15:42:55'),(20,15,26,'huyvu8051@gmail.com','2023-05-29 15:42:56','\0','huyvu8051@gmail.com','2023-05-29 15:42:56'),(21,15,27,'huyvu8051@gmail.com','2023-05-29 15:43:14','','huyvu8051@gmail.com','2023-05-29 15:43:26'),(22,15,28,'vuvanhuy15082001@gmail.com','2023-05-29 15:46:35','\0','vuvanhuy15082001@gmail.com','2023-05-29 15:46:35');
/*!40000 ALTER TABLE `labeled` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS labeled_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `labeled_before_insert` BEFORE INSERT ON `labeled` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS labeled_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `labeled_before_update` BEFORE UPDATE ON `labeled` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `lizt`
--

DROP TABLE IF EXISTS `lizt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lizt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `w_id` bigint(20) DEFAULT NULL,
  `board_id` bigint(20) DEFAULT NULL,
  `ordinal` decimal(20,6) NOT NULL DEFAULT 0.000000,
  `title` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_date` char(19) DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  `modified_by` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKosv002clbdihfs44dffmu2egt` (`board_id`),
  KEY `id` (`id`),
  KEY `w_id` (`w_id`),
  CONSTRAINT `FK_w_id` FOREIGN KEY (`w_id`) REFERENCES `workspace` (`id`),
  CONSTRAINT `FKosv002clbdihfs44dffmu2egt` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lizt`
--

LOCK TABLES `lizt` WRITE;
/*!40000 ALTER TABLE `lizt` DISABLE KEYS */;
INSERT INTO `lizt` VALUES (1,4,3,0.000000,'Chung ta','','2023-02-28 20:21:12',NULL,'2023-03-01 09:12:33',NULL),(2,4,3,1.000000,'Chung ta','','2023-02-28 20:21:23',NULL,'2023-03-01 09:12:34',NULL),(3,4,3,2.000000,'Chung ta','','2023-02-28 20:21:30',NULL,'2023-03-01 09:12:35',NULL),(4,4,2,0.000000,'nguoi theo huong hoa','','2023-03-01 08:35:44','huyvu8051@gmail.com','2023-03-01 09:12:35',NULL),(5,4,2,0.000000,'nguoi theo huong hoa','','2023-03-01 08:36:04','huyvu8051@gmail.com','2023-03-01 09:12:35',NULL),(6,4,2,0.000000,'nguoi theo huong hoa','','2023-03-01 08:39:43','huyvu8051@gmail.com','2023-03-01 09:12:36',NULL),(7,18,2,0.000000,'chung ta','','2023-03-01 08:47:49','huyvu8051@gmail.com','2023-03-01 09:12:37',NULL),(8,24,2,0.000000,'chung ta','','2023-03-01 08:47:53','huyvu8051@gmail.com','2023-03-01 09:12:37',NULL),(9,110,2,5.500000,'chung ta','\0','2023-03-01 08:53:10','huyvu8051@gmail.com','2023-03-07 15:24:52','huyvu8051@gmail.com'),(10,110,2,2.500000,'em cua ngay hom qua','\0','2023-03-01 08:53:27','huyvu8051@gmail.com','2023-03-08 14:26:15','huyvu8051@gmail.com'),(11,110,2,0.000000,'asdfsaf','','2023-03-01 08:53:40','huyvu8051@gmail.com','2023-03-07 15:20:45',NULL),(12,110,2,0.000000,'asdfsaf','','2023-03-01 08:53:48','huyvu8051@gmail.com','2023-03-07 15:20:48',NULL),(13,110,2,0.000000,'asdfsaf','','2023-03-01 08:54:07','huyvu8051@gmail.com','2023-03-07 15:20:49',NULL),(14,110,2,0.000000,'asdfsaf','','2023-03-01 08:55:17','huyvu8051@gmail.com','2023-03-07 15:20:50',NULL),(15,110,2,0.000000,'rtwet','','2023-03-01 08:57:31','huyvu8051@gmail.com','2023-03-07 15:20:51',NULL),(16,110,2,0.000000,'rtwet','','2023-03-01 08:58:03','huyvu8051@gmail.com','2023-03-07 15:20:51',NULL),(17,110,2,0.000000,'rtwet','','2023-03-01 08:59:35','huyvu8051@gmail.com','2023-03-07 15:20:52',NULL),(18,110,2,0.000000,'rtwet','','2023-03-01 09:06:29','huyvu8051@gmail.com','2023-03-07 15:20:53',NULL),(19,110,2,0.000000,'rtwet','','2023-03-01 09:11:16','huyvu8051@gmail.com','2023-03-07 15:20:53',NULL),(20,110,2,0.000000,'nguoi theo huong hoa','','2023-03-01 09:21:30','huyvu8051@gmail.com','2023-03-07 15:20:54',NULL),(21,110,2,0.000000,'sfdgsd','','2023-03-01 09:25:45','huyvu8051@gmail.com','2023-03-07 15:20:55',NULL),(22,110,2,7.500000,'afddafafd1324124','\0','2023-03-01 09:26:00','huyvu8051@gmail.com','2023-03-07 15:24:59','huyvu8051@gmail.com'),(23,110,2,2.000000,'Chúng ta của hiện tại','\0','2023-03-01 09:27:32','huyvu8051@gmail.com','2023-03-08 14:26:42','huyvu8051@gmail.com'),(24,110,2,0.000000,'sfgsfdgsdfg','','2023-03-01 09:27:35','huyvu8051@gmail.com','2023-03-07 15:20:56',NULL),(25,110,2,3.250000,'asdf','\0','2023-03-01 09:31:57','huyvu8051@gmail.com','2023-03-07 15:24:55','huyvu8051@gmail.com'),(26,110,2,3.000000,'Chúng ta, là áng mây bên trời vội vàng ngang qua,\nChúng ta, chẳng thể nâng niu những câu thề','\0','2023-03-01 09:32:32','huyvu8051@gmail.com','2023-03-08 15:02:28','vuvanhuy15082001@gmail.com'),(27,110,2,4.000000,'asdfasdfasdfds','\0','2023-03-01 09:32:41','huyvu8051@gmail.com','2023-03-01 09:32:41','huyvu8051@gmail.com'),(28,110,2,5.000000,'nguoi theo huong hoa','\0','2023-03-01 09:40:49','huyvu8051@gmail.com','2023-03-01 09:40:49','huyvu8051@gmail.com'),(29,110,2,6.000000,'sfdgfsdg','\0','2023-03-01 09:58:42','huyvu8051@gmail.com','2023-03-01 09:58:42','huyvu8051@gmail.com'),(30,110,2,7.000000,'erty','\0','2023-03-01 09:59:49','huyvu8051@gmail.com','2023-03-01 09:59:49','huyvu8051@gmail.com'),(31,110,2,8.000000,'ertyr','\0','2023-03-01 10:01:16','huyvu8051@gmail.com','2023-03-01 10:01:16','huyvu8051@gmail.com'),(32,110,2,9.000000,'aasdfasd','\0','2023-03-01 10:46:22','huyvu8051@gmail.com','2023-03-01 10:46:22','huyvu8051@gmail.com'),(33,110,2,10.000000,'asdfasf','\0','2023-03-01 10:49:10','huyvu8051@gmail.com','2023-03-01 10:49:10','huyvu8051@gmail.com'),(34,110,2,11.000000,'asdfasdf','\0','2023-03-01 10:49:43','huyvu8051@gmail.com','2023-03-01 10:49:43','huyvu8051@gmail.com'),(35,110,2,12.000000,'chng ta cua hien tai, heo kho','\0','2023-03-01 10:51:50','huyvu8051@gmail.com','2023-03-01 10:51:50','huyvu8051@gmail.com'),(36,110,2,13.000000,'ngay mai, nguoi luyen luu theo nhung giac mo tung co','\0','2023-03-01 10:52:08','huyvu8051@gmail.com','2023-03-01 10:52:08','huyvu8051@gmail.com'),(37,110,2,14.000000,'lieu co ta','\0','2023-03-01 10:52:20','huyvu8051@gmail.com','2023-03-01 10:52:20','huyvu8051@gmail.com'),(38,110,4,13.244750,'nguoi theo huong hoa','','2023-03-01 11:04:11','huyvu8051@gmail.com','2023-03-01 15:44:32',NULL),(39,110,4,6.094750,'khong lam ma doi co an','\0','2023-03-01 11:04:21','huyvu8051@gmail.com','2023-05-23 13:15:39','vuvanhuy15082001@gmail.com'),(40,110,4,13.144750,'dua buoc ai xa roi','','2023-03-01 11:12:55','huyvu8051@gmail.com','2023-03-01 15:44:32',NULL),(41,110,4,0.594750,'lan suong khoi phoi asdfads','\0','2023-03-01 11:13:02','huyvu8051@gmail.com','2023-05-23 13:15:29','vuvanhuy15082001@gmail.com'),(42,110,4,-7.905250,'chung ta cua hien tai','\0','2023-03-01 11:13:19','huyvu8051@gmail.com','2023-05-12 10:41:06','huyvu8051@gmail.com'),(43,110,4,14.244750,'em cua ngay hom qua','','2023-03-01 15:38:27','huyvu8051@gmail.com','2023-03-01 15:44:27',NULL),(44,110,4,15.244750,'abcd','','2023-03-01 15:43:09','huyvu8051@gmail.com','2023-03-01 15:44:06',NULL),(45,110,4,16.244750,'asdfd','','2023-03-01 15:43:25','huyvu8051@gmail.com','2023-03-01 15:44:06',NULL),(46,110,4,17.244750,'ewrwr','','2023-03-01 15:43:28','huyvu8051@gmail.com','2023-03-01 15:44:07',NULL),(47,110,4,18.244750,'nguoi theo huong hoa','','2023-03-01 15:43:38','huyvu8051@gmail.com','2023-03-01 15:44:08',NULL),(48,110,4,7.094750,'sdfgsdfgsdfg','\0','2023-03-01 15:44:37','huyvu8051@gmail.com','2023-05-08 10:48:15','huyvu8051@gmail.com'),(49,110,4,5.594750,'chung ta cua hien tai','\0','2023-03-01 15:44:39','huyvu8051@gmail.com','2023-05-23 13:15:21','vuvanhuy15082001@gmail.com'),(50,110,3,6.350000,'nguoi theo huong hoa','\0','2023-03-01 15:52:19','huyvu8051@gmail.com','2023-03-07 15:16:17','huyvu8051@gmail.com'),(51,110,3,-18.900000,'may mu giang loi','\0','2023-03-01 15:52:24','huyvu8051@gmail.com','2023-03-07 15:19:13','huyvu8051@gmail.com'),(52,110,3,6.325000,'lan suong khoi phoi phai','\0','2023-03-01 15:52:29','huyvu8051@gmail.com','2023-03-07 15:16:59','huyvu8051@gmail.com'),(53,110,3,31.350000,'','\0','2023-03-01 15:56:36','huyvu8051@gmail.com','2023-03-07 15:19:20','huyvu8051@gmail.com'),(54,110,4,22.094750,'asfdkjasdfl;jasfsdlf','\0','2023-03-01 16:46:49','huyvu8051@gmail.com','2023-05-08 10:48:15','huyvu8051@gmail.com'),(55,110,4,72.094750,'casdfasdf','\0','2023-03-02 19:14:09','huyvu8051@gmail.com','2023-05-08 10:53:18','huyvu8051@gmail.com'),(56,110,4,122.094750,'có ai thương em như vậy, có ai gần em đến thế','\0','2023-03-02 19:29:37','huyvu8051@gmail.com','2023-05-08 10:54:21','huyvu8051@gmail.com'),(57,110,4,37.094750,'chung ta cua hien tai','\0','2023-03-07 15:08:59','huyvu8051@gmail.com','2023-05-08 10:53:19','huyvu8051@gmail.com'),(58,112,5,50.000000,'fdgsgsfd','\0','2023-03-09 11:32:46','ng.nguyencntt@gmail.com','2023-03-09 11:32:46','ng.nguyencntt@gmail.com'),(59,112,5,75.000000,'1er21gr1gw','\0','2023-03-09 11:32:51','ng.nguyencntt@gmail.com','2023-03-09 11:33:31','ng.nguyencntt@gmail.com'),(60,113,6,50.000000,'khong phai dang vua dau','\0','2023-05-05 10:23:48','huyvu8051@gmail.com','2023-05-05 10:23:48','huyvu8051@gmail.com'),(61,113,6,100.000000,'vua dau','\0','2023-05-05 10:23:59','huyvu8051@gmail.com','2023-05-05 10:23:59','huyvu8051@gmail.com'),(62,114,7,50.000000,'Khong lam ma doi co an','\0','2023-05-30 13:58:57','huyvu8051@gmail.com','2023-05-30 13:58:57','huyvu8051@gmail.com'),(63,114,7,100.000000,'sasd f as f','\0','2023-05-30 14:02:26','huyvu8051@gmail.com','2023-05-30 14:02:26','huyvu8051@gmail.com');
/*!40000 ALTER TABLE `lizt` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS lizt_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `lizt_before_insert` BEFORE INSERT ON `lizt` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS lizt_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `lizt_before_update` BEFORE UPDATE ON `lizt` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_date` char(19) DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  `modified_by` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`),
  KEY `id` (`id`),
  KEY `username` (`username`),
  FULLTEXT KEY `user_account_username_full_name_index` (`username`,`full_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7396461 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (7396458,'ng.nguyencntt@gmail.com',NULL,'Gia Nguyễn Nguyễn','https://lh3.googleusercontent.com/a/AGNmyxbZ1gycc7bxclGEX47QyECSTKE-b2mNYm0FZNUO=s96-c','\0','2023-03-09 11:32:14','anonymousUser','2023-03-09 11:32:14','anonymousUser'),(7396459,'huyvu8051@gmail.com',NULL,'Huy Vũ Văn','https://lh3.googleusercontent.com/a/AAcHTtfNlMBYBu7CB4NPlz1Z2B5kxxuZowefxQUpgEVxhw=s96-c','\0','2023-05-30 13:58:04','huyvu8051@gmail.com','2023-05-30 13:58:04','huyvu8051@gmail.com'),(7396460,'huyvu8052@gmail.com',NULL,'Huy Vũ Văn','https://lh3.googleusercontent.com/a/AAcHTtfNlMBYBu7CB4NPlz1Z2B5kxxuZowefxQUpgEVxhw=s96-c','\0','2023-05-30 16:09:17',NULL,'2023-05-30 16:09:17',NULL);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS user_account_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `user_account_before_insert` BEFORE INSERT ON `user_account` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS user_account_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `user_account_before_update` BEFORE UPDATE ON `user_account` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `workspace`
--

DROP TABLE IF EXISTS `workspace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workspace` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `picture_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workspace`
--

LOCK TABLES `workspace` WRITE;
/*!40000 ALTER TABLE `workspace` DISABLE KEYS */;
INSERT INTO `workspace` VALUES (4,NULL,'cc','',NULL,'2023-02-22 15:45:02',NULL,'2023-02-26 21:40:29'),(18,NULL,'qwer','','huyvu8051@gmail.com','2023-02-23 08:13:10',NULL,'2023-02-26 21:40:29'),(23,NULL,NULL,'','huyvu8051@gmail.com','2023-02-23 08:38:12',NULL,'2023-02-26 21:40:29'),(24,NULL,NULL,'','huyvu8051@gmail.com','2023-02-23 08:39:16',NULL,'2023-02-26 21:40:29'),(25,NULL,NULL,'','huyvu8051@gmail.com','2023-02-23 08:40:18',NULL,'2023-02-26 21:40:29'),(26,NULL,NULL,'','huyvu8051@gmail.com','2023-02-23 08:41:25',NULL,'2023-02-26 21:40:29'),(27,NULL,NULL,'','huyvu8051@gmail.com','2023-02-23 08:41:40',NULL,'2023-02-26 21:40:29'),(28,NULL,NULL,'','huyvu8051@gmail.com','2023-02-23 08:44:32',NULL,'2023-02-26 21:40:29'),(29,NULL,NULL,'','huyvu8051@gmail.com','2023-02-23 08:47:52',NULL,'2023-02-26 21:40:29'),(31,NULL,NULL,'',NULL,'2023-02-23 09:56:23',NULL,'2023-02-26 21:40:29'),(32,NULL,NULL,'',NULL,'2023-02-23 10:12:52',NULL,'2023-02-26 21:40:29'),(33,NULL,'sdf','','huyvu8051@gmail.com','2023-02-23 10:12:59',NULL,'2023-02-26 21:40:29'),(34,NULL,'nguoi theo huong hoa','','huyvu8051@gmail.com','2023-02-23 10:13:50',NULL,'2023-02-26 21:40:29'),(35,NULL,'nguoi theo huong hoa','','huyvu8051@gmail.com','2023-02-23 10:16:40',NULL,'2023-02-26 21:40:29'),(36,NULL,'nguoi theo huong hoa','','huyvu8051@gmail.com','2023-02-23 10:22:59',NULL,'2023-02-26 21:40:29'),(37,NULL,'Chung ta cua hien tai','','huyvu8051@gmail.com','2023-02-23 10:52:23',NULL,'2023-02-26 21:40:29'),(38,NULL,'Chung ta cua hien tai','','huyvu8051@gmail.com','2023-02-23 10:52:27',NULL,'2023-02-26 21:40:29'),(39,NULL,'Chung ta cua hien tai','','huyvu8051@gmail.com','2023-02-23 11:00:19',NULL,'2023-02-26 21:40:29'),(40,NULL,'asdf','','huyvu8051@gmail.com','2023-02-23 11:01:09',NULL,'2023-02-26 21:40:29'),(41,NULL,'nguoi theo huong hoa','','huyvu8051@gmail.com','2023-02-23 11:03:35',NULL,'2023-02-26 21:40:29'),(42,NULL,'em cua ngay hom qua','','huyvu8051@gmail.com','2023-02-23 11:04:38',NULL,'2023-02-26 21:40:29'),(43,NULL,'Viva odc','','huyvu8051@gmail.com','2023-02-23 11:13:14',NULL,'2023-02-26 21:40:29'),(44,NULL,'posco idc','','huyvu8051@gmail.com','2023-02-23 11:13:50',NULL,'2023-02-26 21:40:29'),(45,NULL,'chung ta cua hien tai','','huyvu8051@gmail.com','2023-02-23 12:51:17',NULL,'2023-02-26 21:40:29'),(46,NULL,'nguoi theo huong hoa','','huyvu8051@gmail.com','2023-02-23 12:51:54',NULL,'2023-02-26 21:40:29'),(47,NULL,'chung ta','','huyvu8051@gmail.com','2023-02-23 12:55:33',NULL,'2023-02-26 21:40:29'),(48,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 12:56:42',NULL,'2023-02-26 21:40:29'),(49,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 12:57:13',NULL,'2023-02-26 21:40:29'),(50,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 13:03:28',NULL,'2023-02-26 21:40:29'),(51,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 13:04:50',NULL,'2023-02-26 21:40:29'),(57,NULL,'ertytr','',NULL,'2023-02-23 13:21:20',NULL,'2023-02-26 21:40:29'),(58,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 13:31:32',NULL,'2023-02-26 21:40:29'),(59,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 13:33:08',NULL,'2023-02-26 21:40:29'),(60,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 13:36:16',NULL,'2023-02-26 21:40:29'),(61,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 13:43:12',NULL,'2023-02-26 21:40:29'),(62,NULL,'ertytr','',NULL,'2023-02-23 13:46:12',NULL,'2023-02-26 21:40:29'),(63,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 13:49:00',NULL,'2023-02-26 21:40:29'),(64,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 13:50:58',NULL,'2023-02-26 21:40:29'),(76,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 14:13:19',NULL,'2023-02-26 21:40:29'),(77,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 14:19:25',NULL,'2023-02-26 21:40:29'),(78,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 14:19:56',NULL,'2023-02-26 21:40:29'),(79,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 14:21:47',NULL,'2023-02-26 21:40:29'),(80,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 14:22:01',NULL,'2023-02-26 21:40:29'),(81,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 14:22:34',NULL,'2023-02-26 21:40:29'),(82,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 14:39:13',NULL,'2023-02-26 21:40:29'),(83,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 14:40:06',NULL,'2023-02-26 21:40:29'),(84,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 14:40:28',NULL,'2023-02-26 21:40:29'),(85,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 14:58:47',NULL,'2023-02-26 21:40:29'),(86,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 15:04:03',NULL,'2023-02-26 21:40:29'),(87,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 15:05:33',NULL,'2023-02-26 21:40:29'),(88,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:00:18',NULL,'2023-02-26 21:40:29'),(89,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:01:41',NULL,'2023-02-26 21:40:29'),(90,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:03:36',NULL,'2023-02-26 21:40:29'),(91,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:04:00',NULL,'2023-02-26 21:40:29'),(92,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:04:24',NULL,'2023-02-26 21:40:29'),(93,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:07:41',NULL,'2023-02-26 21:40:29'),(94,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:08:16',NULL,'2023-02-26 21:40:29'),(95,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:14:12',NULL,'2023-02-26 21:40:29'),(96,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:15:32',NULL,'2023-02-26 21:40:29'),(97,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:15:51',NULL,'2023-02-26 21:40:29'),(98,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:20:04',NULL,'2023-02-26 21:40:29'),(99,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:22:07',NULL,'2023-02-26 21:40:29'),(100,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:22:16',NULL,'2023-02-26 21:40:29'),(101,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:22:39',NULL,'2023-02-26 21:40:29'),(102,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:23:24',NULL,'2023-02-26 21:40:29'),(103,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:23:27',NULL,'2023-02-26 21:40:29'),(104,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:23:53',NULL,'2023-02-26 21:40:29'),(105,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:24:59',NULL,'2023-02-26 21:40:29'),(106,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:25:54',NULL,'2023-02-26 21:40:29'),(107,NULL,'ertytr','','huyvu8051@gmail.com','2023-02-23 16:27:21',NULL,'2023-02-26 21:40:29'),(108,NULL,'abcbcbbc','','huyvu8051@gmail.com','2023-02-23 17:12:14',NULL,'2023-02-26 21:40:29'),(109,NULL,'abcbcbbc','','huyvu8051@gmail.com','2023-02-23 17:12:36',NULL,'2023-02-26 21:40:29'),(110,NULL,'chung ta','\0','huyvu8051@gmail.com','2023-02-26 21:41:02','huyvu8051@gmail.com','2023-02-26 21:41:02'),(111,NULL,'chung ta','','huyvu8051@gmail.com','2023-03-06 15:29:04',NULL,'2023-03-06 15:30:16'),(112,NULL,'123','\0','ng.nguyencntt@gmail.com','2023-03-09 11:32:23','ng.nguyencntt@gmail.com','2023-03-09 11:32:23'),(113,NULL,'chung ta','\0','huyvu8051@gmail.com','2023-05-05 10:23:20','huyvu8051@gmail.com','2023-05-05 10:23:20'),(114,NULL,'Posco DX','\0','huyvu8051@gmail.com','2023-05-30 13:58:19','huyvu8051@gmail.com','2023-05-30 13:58:19');
/*!40000 ALTER TABLE `workspace` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS workspace_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `workspace_before_insert` BEFORE INSERT ON `workspace` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS workspace_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `workspace_before_update` BEFORE UPDATE ON `workspace` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `workspace_member`
--

DROP TABLE IF EXISTS `workspace_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workspace_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `wp_id` bigint(20) NOT NULL,
  `is_admin` bit(1) NOT NULL DEFAULT b'0',
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` char(19) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(30) DEFAULT NULL,
  `modified_date` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`),
  KEY `wp_id` (`wp_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `FK63j92rshcw8dephxvjh9iha1p` FOREIGN KEY (`wp_id`) REFERENCES `workspace` (`id`),
  CONSTRAINT `FKmqpu62lr3vvysaqeuj9a6e62u` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workspace_member`
--

LOCK TABLES `workspace_member` WRITE;
/*!40000 ALTER TABLE `workspace_member` DISABLE KEYS */;
INSERT INTO `workspace_member` VALUES (78,7396459,114,'','huyvu8051@gmail.com','2023-05-30 13:58:19','\0','huyvu8051@gmail.com','2023-05-30 13:58:19');
/*!40000 ALTER TABLE `workspace_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS workspace_member_before_insert */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `workspace_member_before_insert` BEFORE INSERT ON `workspace_member` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50032 DROP TRIGGER IF EXISTS workspace_member_before_update */;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `workspace_member_before_update` BEFORE UPDATE ON `workspace_member` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-30 16:53:47
