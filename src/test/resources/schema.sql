CREATE TABLE `wrestler` (
  `wrestler_id` bigint NOT NULL AUTO_INCREMENT,
  `in_ring_name` varchar(100) DEFAULT NULL,
  `wrestler_name` varchar(100) DEFAULT NULL,
  `wrestler_pic_path` varchar(100) DEFAULT NULL,
  `wrestling_brand_id` bigint DEFAULT NULL,
  `wrestling_locker_room_id` bigint DEFAULT NULL,
  `wrestling_streak` int DEFAULT NULL,
  PRIMARY KEY (`wrestler_id`)
);

CREATE TABLE `wrestling_brand` (
  `wrestling_brand_id` bigint NOT NULL AUTO_INCREMENT,
  `wrestling_brand_name` varchar(100) DEFAULT NULL
);

CREATE TABLE `locker_room` (
  `wrestling_locker_room_id` bigint NOT NULL AUTO_INCREMENT,
  `wrestling_locker_room_name` varchar(100) DEFAULT NULL
);

CREATE TABLE `wrestler_match_victories` (
  `winner_wrestler_id` bigint NOT NULL AUTO_INCREMENT,
  `match_victories_wrestling_match_id` bigint DEFAULT NULL
);

CREATE TABLE `wrestler_match_losses` (
  `loser_wrestler_id` bigint NOT NULL AUTO_INCREMENT,
  `match_losses_wrestling_match_id` bigint DEFAULT NULL
);

