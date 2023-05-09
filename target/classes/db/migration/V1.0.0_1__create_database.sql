CREATE TABLE user_role (
  id int PRIMARY KEY AUTO_INCREMENT,
  role_name varchar(20) NOT NULL
);

CREATE TABLE userr (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  email varchar(40) NOT NULL,
  password varchar(80) NOT NULL,
  user_role_id int NOT NULL
);

CREATE TABLE reservation (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  checkin_date DATE NOT NULL,
  checkout_date DATE NOT NULL,
  res_confirmation TINYINT NOT NULL,
  num_adults int,
  num_children int,
  user_id int NOT NULL
);

CREATE TABLE guest_information (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name varchar(25) NOT NULL,
  last_name varchar(25) NOT NULL,
  phone_number varchar(10) NOT NULL,
  pin VARCHAR(100) NOT NULL,
  age int
);

CREATE TABLE room (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  number_of_places int NOT NULL,
  price_per_night DOUBLE(5,2) NOT NULL,
  description varchar(500)
);

CREATE TABLE reservation_room (
  reservation_id int NOT NULL,
  room_id int NOT NULL
);

CREATE TABLE guest_review (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  comment varchar(500) NOT NULL,
  rating int,
  room_id int NOT NULL
);

CREATE TABLE image (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  image_binary LONGBLOB NOT NULL
);

CREATE TABLE room_image (
  image_id int NOT NULL,
  room_id int NOT NULL
);

CREATE TABLE facility (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(50) NOT NULL
);

CREATE TABLE room_facility (
  facility_id int NOT NULL,
  room_id int NOT NULL
);

CREATE TABLE reservation_guestinformation (
  guestinformation_id int NOT NULL,
  reservation_id int NOT NULL
);


ALTER TABLE userr
ADD CONSTRAINT FK_userruserrRole
FOREIGN KEY(user_role_id) REFERENCES user_role(ID);

ALTER TABLE userr
ADD CONSTRAINT UniqueEmail
UNIQUE(email);

ALTER TABLE userr
ALTER user_role_id SET DEFAULT 1;

ALTER TABLE reservation
ADD CONSTRAINT FK_Reservationuserr
FOREIGN KEY(user_id) REFERENCES userr(id);

ALTER TABLE guest_information
ADD CONSTRAINT UniquePhoneNumber
UNIQUE(phone_number);

ALTER TABLE guest_information
ADD CONSTRAINT UniquePIN
UNIQUE(pin);

ALTER TABLE reservation_room
ADD CONSTRAINT FK_ReservedRoomsReservation
FOREIGN KEY(reservation_id) REFERENCES reservation(id);

ALTER TABLE reservation_room
ADD CONSTRAINT FK_ReservedRoomsRoom
FOREIGN KEY(room_id) REFERENCES room(id);

ALTER TABLE guest_review
ADD CONSTRAINT FK_GuestReviewRoom
FOREIGN KEY(room_id) REFERENCES room(id);

ALTER TABLE room_image
ADD CONSTRAINT FK_RoomImagesRoom
FOREIGN KEY(room_id) REFERENCES room(id);

ALTER TABLE room_image
ADD CONSTRAINT FK_RoomImagesImage
FOREIGN KEY(image_id) REFERENCES image(id);

ALTER TABLE room_facility
ADD CONSTRAINT FK_RoomFacilitiesRoom
FOREIGN KEY(room_id) REFERENCES room(id);

ALTER TABLE room_facility
ADD CONSTRAINT FK_RoomFacilitiesFacilities
FOREIGN KEY(facility_id) REFERENCES facility(id);

ALTER TABLE reservation_guestinformation
ADD CONSTRAINT FK_GuestInformationReservationReservation
FOREIGN KEY(reservation_id) REFERENCES reservation(id);

ALTER TABLE reservation_guestinformation
ADD CONSTRAINT FK_GuestInformationReservationGuestInformation
FOREIGN KEY(guestinformation_id) REFERENCES guest_information(id);