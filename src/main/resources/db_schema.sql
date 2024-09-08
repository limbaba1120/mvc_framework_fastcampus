DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
                       userId  VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       name VARCHAR(50) NOT NULL,
                       email VARCHAR(100),

                       PRIMARY KEY (userId)
);