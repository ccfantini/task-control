-- users
INSERT INTO 
	USER_TASK (username, password, role) 
VALUES
  	('juliana',/*juli2020*/ '$2a$10$lebnyysfT.4tDbv4VDoKbOTU5qoImgYa.sdbZfC.B4ACrsaOOA5eW','ADMIN'),
	('caio',/*caio2020*/ '$2a$10$u7weR.0orVbGP42/8TwToesWrFpWN0hQZ6kWy/L6zBp1v1w3r/Bey','USER'),
	('isabela',/*isab2020*/ '$2a$10$ZJwwu7IuLHIWes5FNEDGF.o2t6opmBwCqguEAg85ptNgSb9jCzjm.','USER'),
	('gabriel',/*gabi2020*/ '$2a$10$u9DfsE5pV.bVNjIsGcbppeVb70a/Y6dM.5XnAY1EcoWFSKJnPteJ2','USER');

-- tasks
INSERT INTO 
	TASK (user_id, created_date, change_date, summary, description, status) 
VALUES
  	(1, CURRENT_TIMESTAMP(), null, 'teste summary 1', 'teste description 1', 0),
	(1, CURRENT_TIMESTAMP(), null, 'teste summary 2', 'teste description 2', 0),
	(2, CURRENT_TIMESTAMP(), null, 'teste summary 3', 'teste description 3', 1);