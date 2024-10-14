--
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: dev
--

INSERT INTO app_user (id, created_at, email, password, username) VALUES (101, '2024-10-07 08:27:47', 'admin@mail.com', '$2a$12$YiGY3222ET4TyEaK1aM2KuRwxb1PiR6DThD3vzGO2iEFwgU9IG7o6', 'admin');
INSERT INTO app_user (id, created_at, email, password, username) VALUES (102, '2024-10-07 09:03:47', 'user@mail.com', '$2a$12$CvQBpk/EA8Cn5mXmxUGcs.6tXo4WMLLaoXkheHDdvRy.zi3dKIbMi', 'user');


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: dev
--

INSERT INTO category (id, description, name) VALUES (102, 'Be ready to negociate', 'Negociation');
INSERT INTO category (id, description, name) VALUES (103, 'Game with different kind of cards', 'Card Game');
INSERT INTO category (id, description, name) VALUES (104, 'Better be lucky', 'Dice');
INSERT INTO category (id, description, name) VALUES (105, 'Let''s have fun', 'Party Game');
INSERT INTO category (id, description, name) VALUES (101, 'Just be the richest by the end of the game.', 'Economic');


--
-- Data for Name: game; Type: TABLE DATA; Schema: public; Owner: dev
--

INSERT INTO game (id, description, image_url, max_players, min_players, name, playing_time, thumbnail_url, year_published) VALUES (101, 'In CATAN (formerly The Settlers of Catan), players try to be the dominant force on the island of Catan by building settlements, cities, and roads. On each turn dice are rolled to determine what resources the island produces. Players build by spending resources (sheep, wheat, wood, brick and ore) that are depicted by these resource cards; each land type, with the exception of the unproductive desert, produces a specific resource: hills produce brick, forests produce wood, mountains produce ore, fields produce wheat, and pastures produce sheep.&amp;#10;&amp;#10;Set-up includes randomly placing large hexagonal tiles (each showing a resource or the desert) in a honeycomb shape and surrounding them with water tiles, some of which contain ports of exchange. Number disks, which will correspond to die rolls (two 6-sided dice are used), are placed on each resource tile. Each player is given two settlements (think: houses) and roads (sticks) which are, in turn, placed on intersections and borders of the resource tiles. Players collect a hand of resource cards based on which hex tiles their last-placed house is adjacent to. A robber pawn is placed on the desert tile.&amp;#10;&amp;#10;A turn consists of possibly playing a development card, rolling the dice, everyone (perhaps) collecting resource cards based on the roll and position of houses (or upgraded cities&amp;mdash;think: hotels) unless a 7 is rolled, turning in resource cards (if possible and desired) for improvements, trading cards at a port, and trading resource cards with other players. If a 7 is rolled, the active player moves the robber to a new hex tile and steals resource cards from other players who have built structures adjacent to that tile.&amp;#10;&amp;#10;Points are accumulated by building settlements and cities, having the longest road and the largest army (from some of the development cards), and gathering certain development cards that simply award victory points. When a player has gathered 10 points (some of which may be held in secret), he announces his total and claims the win.&amp;#10;&amp;#10;CATAN has won multiple awards and is one of the most popular games in recent history due to its amazing ability to appeal to experienced gamers as well as those new to the hobby.&amp;#10;&amp;#10;Die Siedler von Catan was originally published by KOSMOS and has gone through multiple editions. It was licensed by Mayfair and has undergone four editions as The Settlers of Catan. In 2015, it was formally renamed CATAN to better represent itself as the core and base game of the CATAN series. It has been re-published in two travel editions, portable edition and compact edition, as a special gallery edition (replaced in 2009 with a family edition), as an anniversary wooden edition, as a deluxe 3D collector&amp;#039;s edition, in the basic Simply Catan, as a beginner version, and with an entirely new theme in Japan and Asia as Settlers of Catan: Rockman Edition. Numerous spin-offs and expansions have also been made for the game.&amp;#10;&amp;#10;', 'https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__original/img/xV7oisd3RQ8R-k18cdWAYthHXsA=/0x0/filters:format(jpeg)/pic2419375.jpg', 4, 3, 'Catan', 120, 'https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__original/img/xV7oisd3RQ8R-k18cdWAYthHXsA=/0x0/filters:format(jpeg)/pic2419375.jpg', 0);
INSERT INTO game (id, description, image_url, max_players, min_players, name, playing_time, thumbnail_url, year_published) VALUES (102, 'In Cabanga!, players try to get rid of their hand of cards as quickly as possible &amp;mdash; but ideally without picking up penalties along the way.&amp;#10;&amp;#10;After the row cards and starting cards in all four colors have been placed in the middle of the table and players each have a hand of eight cards, the round begins. Players then take turns placing one card next to the matching row card in the middle, ideally with as small a difference as possible because the larger the number gap, the greater the chance that the other players will call out &amp;quot;Cabanga!&amp;quot; and throw cards with the values between the two number cards to the active player. These thrown-in cards are placed on the discard pile, then the active player must draw the same number of cards from the penalty pile.&amp;#10;&amp;#10;When a player has no more cards in hand, the round ends and all players count the points on their cards. As soon as a player has collected 18 points, the game ends and the player with the fewest points wins!&amp;#10;&amp;#10;', 'https://cf.geekdo-images.com/nbGlxygT3CGhM7L-9_1gkQ__original/img/gOKpNlUbsbc8AXDqKLcSMa5xFTA=/0x0/filters:format(jpeg)/pic7655903.jpg', 6, 3, 'Cabanga!', 20, 'https://cf.geekdo-images.com/nbGlxygT3CGhM7L-9_1gkQ__original/img/gOKpNlUbsbc8AXDqKLcSMa5xFTA=/0x0/filters:format(jpeg)/pic7655903.jpg', 0);
INSERT INTO game (id, description, image_url, max_players, min_players, name, playing_time, thumbnail_url, year_published) VALUES (103, 'Piraten Kapern (&amp;quot;Pirate Capers&amp;quot;) is the German version of the 2011 game Otsarot o Tsarot, published by Shafir Games in Hebrew and English. It''s part of a line of combined dice-and-card games that publisher AMIGO Spiel introduced in 2012.&amp;#10;&amp;#10;As you might expect with a game titled Piraten Kapern, players must set off in search of treasure, pushing themselves to find as much as possible without losing their heads.&amp;#10;&amp;#10;At the start of a turn, the active player rolls the eight special dice. He must set aside any skulls rolled, and his turn ends immediately with no score if he rolls a third skull. The player is free to set aside any number of other dice, rerolling the rest. He may continue to do this until the skulls get him or he stops. He then scores for dice combinations, with a three-of-a-kind earning 100 points, a four-of-a-kind 200 points, and so on all the way up to an eight-of-a-kind earning 4,000 points. Each gold or diamond showing is worth 100 points on its own. A player earns a 500 point bonus if all eight dice score.&amp;#10;If a player is not able to set aside any valid dice, all points are lost.&amp;#10;&amp;#10;If a player rolls four or more skulls on his first roll, he heads to Skull Island for the turn, setting aside those skulls and continuing to roll as long as he sets aside at least one skull each time. Once he stops, all other players lose 100 points for each skull showing.&amp;#10;&amp;#10;Before a player starts to roll for his turn, however, he draws a card from the pirate deck, which affects what''s possible on that turn: He might score double for the treasure he collects, or need to roll sabers to fight off other pirates, or receive a free diamond or gold coin, or be able to score treasure even if he collect three skulls.&amp;#10;&amp;#10;Once a player reaches 6,000 points, all other players take one final turn, then the player with the most points wins.&amp;#10;&amp;#10;', 'https://cf.geekdo-images.com/ASvNr8Z7n4uuXiIeKTCpjA__original/img/wFq8_y9ycpinYVvBxwxraxix91c=/0x0/filters:format(jpeg)/pic5755894.jpg', 5, 2, 'Mille Sabords!', 30, 'https://cf.geekdo-images.com/ASvNr8Z7n4uuXiIeKTCpjA__original/img/wFq8_y9ycpinYVvBxwxraxix91c=/0x0/filters:format(jpeg)/pic5755894.jpg', 0);


--
-- Data for Name: game_category; Type: TABLE DATA; Schema: public; Owner: dev
--

INSERT INTO game_category (game_id, category_id) VALUES (102, 103);
INSERT INTO game_category (game_id, category_id) VALUES (103, 103);
INSERT INTO game_category (game_id, category_id) VALUES (103, 104);


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: dev
--

INSERT INTO location (id, created_at, description, name, user_id) VALUES (102, '2024-10-10 12:09:27.587', 'In the living room closet', 'My living room', 101);
INSERT INTO location (id, created_at, description, name, user_id) VALUES (103, '2024-10-11 12:09:27.587', 'Under my bed', 'My bedroom', 102);


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: dev
--

INSERT INTO role (id, name) VALUES (101, 'ROLE_USER');
INSERT INTO role (id, name) VALUES (102, 'ROLE_ADMIN');


--
-- Data for Name: user_game; Type: TABLE DATA; Schema: public; Owner: dev
--

INSERT INTO user_game (id, registered_date, game_id, location_id, user_id) VALUES (101, '2024-10-09 14:57:55.67552', 101, NULL, 101);
INSERT INTO user_game (id, registered_date, game_id, location_id, user_id) VALUES (102, '2024-12-10 01:02:55.67552', 102, 103, 102);
INSERT INTO user_game (id, registered_date, game_id, location_id, user_id) VALUES (103, '2024-12-10 01:02:55.67552', 101, 103, 102);

--
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: dev
--

INSERT INTO user_role (user_id, role_id) VALUES (101, 101);
INSERT INTO user_role (user_id, role_id) VALUES (101, 102);
INSERT INTO user_role (user_id, role_id) VALUES (102, 101);


-- --
-- -- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dev
-- --
--
-- -- SELECT pg_catalog.setval('app_user_id_seq', 2, true);
-- ALTER SEQUENCE app_user_id_seq RESTART WITH 2;
--
--
-- --
-- -- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dev
-- --
--
-- -- SELECT pg_catalog.setval('category_id_seq', 5, true);
-- ALTER SEQUENCE category_id_seq RESTART WITH 5;
--
--
--
-- --
-- -- Name: exchange_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dev
-- --
--
-- -- SELECT pg_catalog.setval('exchange_id_seq', 1, false);
-- ALTER SEQUENCE exchange_id_seq RESTART WITH 1;
--
--
-- --
-- -- Name: game_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dev
-- --
--
-- -- SELECT pg_catalog.setval('game_id_seq', 3, true);
-- ALTER SEQUENCE game_id_seq RESTART WITH 3;
--
--
-- --
-- -- Name: location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dev
-- --
--
-- -- SELECT pg_catalog.setval('location_id_seq', 3, true);
-- ALTER SEQUENCE location_id_seq RESTART WITH 3;
--
--
-- --
-- -- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dev
-- --
--
-- -- SELECT pg_catalog.setval('role_id_seq', 2, true);
-- ALTER SEQUENCE role_id_seq RESTART WITH 2;
--
--
-- --
-- -- Name: user_game_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dev
-- --
--
-- -- SELECT pg_catalog.setval('user_game_id_seq', 3, true);
-- ALTER SEQUENCE user_game_id_seq RESTART WITH 3;
