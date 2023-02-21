-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.27-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */
/*!40101 SET NAMES utf8 */
/*!50503 SET NAMES utf8mb4 */
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */
/*!40103 SET TIME_ZONE='+00:00' */
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */


-- Dumping database structure for reboot
CREATE DATABASE IF NOT EXISTS `reboot` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */
USE `reboot`

-- Dumping structure for table reboot.activity
CREATE TABLE IF NOT EXISTS `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4l3j89hkh9f2p987piyh4pgb0` (`card_id`),
  KEY `FKb0e1g6c44ampoe1ondy9t6v8w` (`user_id`),
  CONSTRAINT `FK4l3j89hkh9f2p987piyh4pgb0` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FKb0e1g6c44ampoe1ondy9t6v8w` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.activity: ~0 rows (approximately)
DELETE FROM `activity`

-- Dumping structure for table reboot.attachment
CREATE TABLE IF NOT EXISTS `attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpyjq6uiperx43dbsny1gjvxne` (`card_id`),
  CONSTRAINT `FKpyjq6uiperx43dbsny1gjvxne` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.attachment: ~0 rows (approximately)
DELETE FROM `attachment`

-- Dumping structure for table reboot.board
CREATE TABLE IF NOT EXISTS `board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `workspace_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `background_image` varchar(255) DEFAULT NULL,
  `stared` bit(1) NOT NULL DEFAULT b'0',
  `visibility` int(11) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh8r4ryxrng25r7ko3yh5eaudu` (`workspace_id`),
  CONSTRAINT `FKh8r4ryxrng25r7ko3yh5eaudu` FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.board: ~0 rows (approximately)
DELETE FROM `board`

-- Dumping structure for table reboot.board_member
CREATE TABLE IF NOT EXISTS `board_member` (
  `board_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`board_id`,`user_id`),
  KEY `FK5ho5ohld9jnbbq33dl4xuuq9w` (`user_id`),
  CONSTRAINT `FK5ho5ohld9jnbbq33dl4xuuq9w` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK5nku3bcf54r5yvv5qn3dubgsi` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.board_member: ~0 rows (approximately)
DELETE FROM `board_member`

-- Dumping structure for table reboot.card
CREATE TABLE IF NOT EXISTS `card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lizt_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `automation` varchar(255) DEFAULT NULL,
  `cover_color` varchar(255) DEFAULT NULL,
  `cover_size` varchar(255) DEFAULT NULL,
  `cover_url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `due_date_complete` bit(1) NOT NULL DEFAULT b'0',
  `due_date_reminder` varchar(255) DEFAULT NULL,
  `is_template` bit(1) NOT NULL DEFAULT b'0',
  `location` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2pmnauf5d5cg76rtydn0dofox` (`lizt_id`),
  CONSTRAINT `FK2pmnauf5d5cg76rtydn0dofox` FOREIGN KEY (`lizt_id`) REFERENCES `lizt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.card: ~0 rows (approximately)
DELETE FROM `card`

-- Dumping structure for table reboot.card_member
CREATE TABLE IF NOT EXISTS `card_member` (
  `card_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `notification` bit(1) NOT NULL DEFAULT b'0',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`card_id`,`user_id`),
  KEY `FKq5low2n4jxef19lg35k6syqru` (`user_id`),
  CONSTRAINT `FKgp6lai9ewkcfodigcua5taanf` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FKq5low2n4jxef19lg35k6syqru` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.card_member: ~0 rows (approximately)
DELETE FROM `card_member`

-- Dumping structure for table reboot.checklist
CREATE TABLE IF NOT EXISTS `checklist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `show_checked_items` bit(1) NOT NULL DEFAULT b'0',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmq05kujpd06x59cm9u46s9eui` (`card_id`),
  CONSTRAINT `FKmq05kujpd06x59cm9u46s9eui` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.checklist: ~0 rows (approximately)
DELETE FROM `checklist`

-- Dumping structure for table reboot.checklist_item
CREATE TABLE IF NOT EXISTS `checklist_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `checklist_id` bigint(20) DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `due_date_reminder` varchar(255) DEFAULT NULL,
  `is_check` bit(1) NOT NULL DEFAULT b'0',
  `title` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo1pmhdej4mt1uhfrpq9hn68hu` (`user_id`),
  KEY `FKn8v1y7el5kl0xefukq4nu3gfm` (`checklist_id`),
  CONSTRAINT `FKn8v1y7el5kl0xefukq4nu3gfm` FOREIGN KEY (`checklist_id`) REFERENCES `checklist` (`id`),
  CONSTRAINT `FKo1pmhdej4mt1uhfrpq9hn68hu` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.checklist_item: ~0 rows (approximately)
DELETE FROM `checklist_item`

-- Dumping structure for table reboot.label
CREATE TABLE IF NOT EXISTS `label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `board_id` bigint(20) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnl4pv15ws9o049sxm6c6dl8oy` (`board_id`),
  CONSTRAINT `FKnl4pv15ws9o049sxm6c6dl8oy` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.label: ~0 rows (approximately)
DELETE FROM `label`

-- Dumping structure for table reboot.labeled
CREATE TABLE IF NOT EXISTS `labeled` (
  `card_id` bigint(20) NOT NULL,
  `label_id` bigint(20) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`card_id`,`label_id`),
  KEY `FKt0fjqpq3kfepviqionu0qu866` (`label_id`),
  CONSTRAINT `FKf248152pjipi76om7ie74wde9` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FKt0fjqpq3kfepviqionu0qu866` FOREIGN KEY (`label_id`) REFERENCES `label` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.labeled: ~0 rows (approximately)
DELETE FROM `labeled`

-- Dumping structure for table reboot.lizt
CREATE TABLE IF NOT EXISTS `lizt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `board_id` bigint(20) DEFAULT NULL,
  `ordinal` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_date` bigint(20) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKosv002clbdihfs44dffmu2egt` (`board_id`),
  CONSTRAINT `FKosv002clbdihfs44dffmu2egt` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.lizt: ~0 rows (approximately)
DELETE FROM `lizt`

-- Dumping structure for table reboot.user_account
CREATE TABLE IF NOT EXISTS `user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_date` tinytext DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `modified_date` tinytext DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.user_account: ~0 rows (approximately)
DELETE FROM `user_account`
INSERT INTO `user_account` (`id`, `username`, `password`, `full_name`, `picture_url`, `is_deleted`, `created_date`, `created_by`, `modified_date`, `modified_by`) VALUES
	(33, 'huyvu8051@gmail.com', NULL, 'Huy Vũ Văn', 'https://lh3.googleusercontent.com/a/AEdFTp4sfV2EyLIGQeZx72R61YKBWu_TpeJSGtdAXRgwoA=s96-c', b'0', '2023-02-15 17:47:35', 'anonymousUser', '2023-02-15 17:47:35', 'anonymousUser')

-- Dumping structure for table reboot.workspace
CREATE TABLE IF NOT EXISTS `workspace` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.workspace: ~0 rows (approximately)
DELETE FROM `workspace`

-- Dumping structure for table reboot.workspace_member
CREATE TABLE IF NOT EXISTS `workspace_member` (
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` bigint(20) DEFAULT NULL,
  `is_admin` bit(1) NOT NULL DEFAULT b'0',
  `user_id` bigint(20) NOT NULL,
  `wp_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`wp_id`),
  KEY `FK63j92rshcw8dephxvjh9iha1p` (`wp_id`),
  CONSTRAINT `FK63j92rshcw8dephxvjh9iha1p` FOREIGN KEY (`wp_id`) REFERENCES `workspace` (`id`),
  CONSTRAINT `FKmqpu62lr3vvysaqeuj9a6e62u` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

-- Dumping data for table reboot.workspace_member: ~0 rows (approximately)
DELETE FROM `workspace_member`

-- Dumping structure for trigger reboot.activity_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION'
DELIMITER //
CREATE TRIGGER `activity_before_insert` BEFORE INSERT ON `activity` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX
END//
DELIMITER
SET SQL_MODE=@OLDTMP_SQL_MODE

-- Dumping structure for trigger reboot.activity_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION'
DELIMITER //
CREATE TRIGGER `activity_before_update` BEFORE UPDATE ON `activity` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX
END//
DELIMITER
SET SQL_MODE=@OLDTMP_SQL_MODE

-- Dumping structure for trigger reboot.attachment_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION'
DELIMITER //
CREATE TRIGGER `attachment_before_insert` BEFORE INSERT ON `attachment` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX
END//
DELIMITER
SET SQL_MODE=@OLDTMP_SQL_MODE

-- Dumping structure for trigger reboot.attachment_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION'
DELIMITER //
CREATE TRIGGER `attachment_before_update` BEFORE UPDATE ON `attachment` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX
END//
DELIMITER
SET SQL_MODE=@OLDTMP_SQL_MODE

-- Dumping structure for trigger reboot.board_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION'
DELIMITER //
CREATE TRIGGER `board_before_insert` BEFORE INSERT ON `board` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX
END//
DELIMITER
SET SQL_MODE=@OLDTMP_SQL_MODE

-- Dumping structure for trigger reboot.board_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION'
DELIMITER //
CREATE TRIGGER `board_before_update` BEFORE UPDATE ON `board` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX
END//
DELIMITER
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.board_member_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `board_member_before_insert` BEFORE INSERT ON `board_member` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.board_member_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `board_member_before_update` BEFORE UPDATE ON `board_member` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.card_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `card_before_insert` BEFORE INSERT ON `card` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.card_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `card_before_update` BEFORE UPDATE ON `card` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.card_member_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `card_member_before_insert` BEFORE INSERT ON `card_member` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.card_member_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `card_member_before_update` BEFORE UPDATE ON `card_member` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.checklist_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `checklist_before_insert` BEFORE INSERT ON `checklist` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.checklist_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `checklist_before_update` BEFORE UPDATE ON `checklist` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.checklist_item_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `checklist_item_before_insert` BEFORE INSERT ON `checklist_item` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.checklist_item_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `checklist_item_before_update` BEFORE UPDATE ON `checklist_item` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.labeled_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `labeled_before_insert` BEFORE INSERT ON `labeled` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.labeled_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `labeled_before_update` BEFORE UPDATE ON `labeled` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.label_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `label_before_insert` BEFORE INSERT ON `label` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.label_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `label_before_update` BEFORE UPDATE ON `label` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.lizt_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `lizt_before_insert` BEFORE INSERT ON `lizt` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.lizt_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `lizt_before_update` BEFORE UPDATE ON `lizt` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.user_account_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `user_account_before_insert` BEFORE INSERT ON `user_account` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.user_account_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `user_account_before_update` BEFORE UPDATE ON `user_account` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.workspace_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `workspace_before_insert` BEFORE INSERT ON `workspace` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.workspace_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `workspace_before_update` BEFORE UPDATE ON `workspace` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.workspace_member_before_insert
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `workspace_member_before_insert` BEFORE INSERT ON `workspace_member` FOR EACH ROW BEGIN
	SET NEW.created_date = SYSDATE(),
	    NEW.modified_date = SYSDATE(),
		 NEW.created_by = @USER_CTX,
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger reboot.workspace_member_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `workspace_member_before_update` BEFORE UPDATE ON `workspace_member` FOR EACH ROW BEGIN
	SET NEW.modified_date = SYSDATE(),
		 NEW.modified_by = @USER_CTX;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
