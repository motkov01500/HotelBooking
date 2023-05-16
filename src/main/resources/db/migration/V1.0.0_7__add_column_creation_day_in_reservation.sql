ALTER TABLE reservation
ADD created_at DATE;

UPDATE reservation
SET created_at = '2023-02-10' WHERE ID = 1;

UPDATE reservation
SET created_at = '2023-02-20' WHERE ID = 2;

UPDATE reservation
SET created_at = '2023-03-01' WHERE ID = 3;

UPDATE reservation
SET created_at = '2023-03-10' WHERE ID = 4;