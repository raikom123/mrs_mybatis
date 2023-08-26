
CREATE TABLE IF NOT EXISTS meeting_room (
                                            room_id SERIAL,
                                            room_name VARCHAR(255) NOT NULL,
    PRIMARY KEY(room_id)
    );

CREATE TABLE IF NOT EXISTS reservable_room (
                                               reserved_date DATE,
                                               room_id INT REFERENCES meeting_room(room_id),
    PRIMARY KEY(reserved_date, room_id)
    );

CREATE TABLE IF NOT EXISTS usr (
    user_id VARCHAR(255),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    PRIMARY KEY(user_id)
    );

CREATE TABLE IF NOT EXISTS reservation (
                                           reservation_id SERIAL,
                                           start_time TIME NOT NULL,
                                           end_time TIME NOT NULL,
                                           reserved_date DATE NOT NULL,
                                           room_id INT NOT NULL,
                                           user_id VARCHAR(255) NOT NULL,
    PRIMARY KEY(reservation_id),
    FOREIGN KEY(reserved_date, room_id) REFERENCES reservable_room(reserved_date, room_id),
    FOREIGN KEY(user_id) REFERENCES usr(user_id)
    );

ALTER TABLE public.reservation
    ADD COLUMN memo character varying(1000) COLLATE pg_catalog."default";

ALTER TABLE public.reservation
    ADD COLUMN member_count integer;

ALTER TABLE public.reservation
    ADD COLUMN remind_date date;

ALTER TABLE public.reservation
    ADD COLUMN remind_time time without time zone;
