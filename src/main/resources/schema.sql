DROP TABLE IF EXISTS TASK; 
DROP TABLE IF EXISTS USER_TASK;

-- CREATE TABLE USER
CREATE TABLE USER_TASK (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(60) NOT NULL,
  password VARCHAR(500) NOT NULL,
  role VARCHAR(60) NOT NULL,
  UNIQUE KEY uk_username(username)
);

-- CREATE TABLE TASK
CREATE TABLE TASK (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  created_date DATETIME NOT NULL,
  change_date DATETIME DEFAULT NULL,
  summary VARCHAR(500) NOT NULL,
  description VARCHAR(4000) NOT NULL,
  status INT NOT NULL COMMENT '0 - pending, 1 - completed',
  FOREIGN KEY (user_id) REFERENCES USER_TASK(id)
);

