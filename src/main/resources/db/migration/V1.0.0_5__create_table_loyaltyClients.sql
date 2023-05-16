CREATE TABLE loyalty_type(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    discount int NOT NULL
);

INSERT INTO loyalty_type(name, discount)
VALUES("BRONZE", 0);

INSERT INTO loyalty_type(name, discount)
VALUES("GOLDEN", 10);

INSERT INTO loyalty_type(name, discount)
VALUES("PLATINUM", 15);