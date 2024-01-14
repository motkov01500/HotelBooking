ALTER TABLE userr
ADD loyalty_id int NOT NULL DEFAULT 1;

ALTER TABLE userr
ADD CONSTRAINT FK_LoyaltyUser
FOREIGN KEY(loyalty_id) REFERENCES loyalty_type(id);