ALTER TABLE reservation
ADD booking_id varchar(100) NOT NULL;

UPDATE reservation
SET booking_id = (SELECT md5(UUID()));