DROP TABLE IF EXISTS officers;
CREATE TABLE officers
(
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    rank VARCHAR (50) NOT NULL,
    PRIMARY KEY (id)
);