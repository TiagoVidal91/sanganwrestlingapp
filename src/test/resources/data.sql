-- Wrestlers

INSERT INTO wrestler (wrestler_id, in_ring_name, wrestler_name, wrestler_pic_path, wrestling_brand_id, wrestling_locker_room_id, wrestling_streak)
VALUES (3,'Lance Drake','Didrick', NULL, 1, 2, 2);

INSERT INTO wrestler (wrestler_id, in_ring_name, wrestler_name, wrestler_pic_path, wrestling_brand_id, wrestling_locker_room_id, wrestling_streak)
VALUES (4,'Raymond Hunter','Renno', NULL, 1, 2, 5);

INSERT INTO wrestler (wrestler_id, in_ring_name, wrestler_name, wrestler_pic_path, wrestling_brand_id, wrestling_locker_room_id, wrestling_streak)
VALUES (9,'Kurt Mackey','Mallast', NULL, 2, 2, 0);

INSERT INTO wrestler (wrestler_id, in_ring_name, wrestler_name, wrestler_pic_path, wrestling_brand_id, wrestling_locker_room_id, wrestling_streak)
VALUES (10,'Tiger Tia','Titilayo Ayomedes', NULL, 1, 1, 5);

INSERT INTO wrestler (wrestler_id, in_ring_name, wrestler_name, wrestler_pic_path, wrestling_brand_id, wrestling_locker_room_id, wrestling_streak)
VALUES (11,'Adorable Ivy','Nelke Furcht', NULL, 2, 1, 0);

INSERT INTO wrestler (wrestler_id, in_ring_name, wrestler_name, wrestler_pic_path, wrestling_brand_id, wrestling_locker_room_id, wrestling_streak)
VALUES (12,'Rebecca Lawrence','Rhemilia', NULL, 1, 1, 1);

INSERT INTO wrestler (wrestler_id, in_ring_name, wrestler_name, wrestler_pic_path, wrestling_brand_id, wrestling_locker_room_id, wrestling_streak)
VALUES (13,'Akane Sakamoto','Akane Hirai', NULL, 2, 1, 4);

INSERT INTO wrestler (wrestler_id, in_ring_name, wrestler_name, wrestler_pic_path, wrestling_brand_id, wrestling_locker_room_id, wrestling_streak)
VALUES (15,'Aaron Schultz','Aaron Krieger', NULL, 2, 2, 1);

-- Wrestling Brand

INSERT INTO wrestling_brand (wrestling_brand_id, wrestling_brand_name) VALUES (1, 'Smackdown');
INSERT INTO wrestling_brand (wrestling_brand_id, wrestling_brand_name) VALUES (2, 'Sangen');

-- Wrestling Locker Room

INSERT INTO locker_room (wrestling_locker_room_id, wrestling_locker_room_name) VALUES (1, 'Female');
INSERT INTO locker_room (wrestling_locker_room_id, wrestling_locker_room_name) VALUES (2, 'Male');


-- Wrestling Victories
-- Aaron (WIN) 3 - OK
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (15,1);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (15,2);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (15,3);

-- Kurt (LOSS) 2x1=2
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (9,4);
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (9,5);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (9,6);

-- Adorable Ivy (LOSS) 4x2=8
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (11,7);
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (11,8);
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (11,9);
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (11,10);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (11,11);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (11,12);

-- Akane (WIN) 5x2=10
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (13,13);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (13,14);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (13,15);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (13,16);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (13,17);
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (13,18);
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (13,19);

-- SMACKDOWN
-- Tiger Tia (WIN) 2x1=2
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (10,20);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (10,21);
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (10,22);

-- Rebecca (LOSS) 3x1=3
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (12,23);
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (12,24);
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (12,25);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (12,26);

-- Lance (LOSS) 3x1=3
INSERT INTO wrestler_match_losses (loser_wrestler_id, match_losses_wrestling_match_id) VALUES (3,27);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (3,28);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (3,29);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (3,30);

-- Renno (WIN) 2 - OK
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (4,31);
INSERT INTO wrestler_match_victories (winner_wrestler_id, match_victories_wrestling_match_id) VALUES (4,32);