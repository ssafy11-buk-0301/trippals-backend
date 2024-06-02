
ALTER TABLE attraction_detail
    DROP FOREIGN KEY attraction_detail_to_basic_content_id_fk;

ALTER TABLE attraction_info
    DROP FOREIGN KEY attraction_to_content_type_id_fk;

ALTER TABLE attraction_info
    DROP FOREIGN KEY attraction_to_gugun_code_fk;

ALTER TABLE attraction_info
    DROP FOREIGN KEY attraction_to_sido_code_fk;

ALTER TABLE board_file
    ADD fileuuid VARCHAR(255) NULL;

ALTER TABLE board_user_read
    ADD PRIMARY KEY (board_seq, user_seq);

ALTER TABLE attraction_info
    ADD CONSTRAINT FK_ATTRACTIONINFO_ON_GUCOSICO FOREIGN KEY (gugun_code, sido_code) REFERENCES gugun (gugun_code, sido_code);

DROP TABLE attraction_detail;

DROP TABLE content_type;

DROP TABLE temp_random_routes;

ALTER TABLE board_file
    DROP COLUMN FILE_UUID;

ALTER TABLE attraction_info
    DROP COLUMN addr2;

ALTER TABLE attraction_info
    DROP COLUMN content_type_id;

ALTER TABLE attraction_info
    DROP COLUMN first_image2;

ALTER TABLE attraction_info
    DROP COLUMN mlevel;

ALTER TABLE attraction_info
    DROP COLUMN readcount;

ALTER TABLE attraction_info
    DROP COLUMN tel;

ALTER TABLE attraction_info
    DROP COLUMN zipcode;

ALTER TABLE content_type
    DROP COLUMN content_type_id;

ALTER TABLE attraction_description
    DROP COLUMN homepage;

ALTER TABLE attraction_description
    DROP COLUMN telname;

ALTER TABLE attraction_info
    MODIFY addr1 VARCHAR(255);

ALTER TABLE route_attraction
    MODIFY attraction_seq INT NULL;

ALTER TABLE board_bookmark
    MODIFY board_seq INT NULL;

ALTER TABLE board_file
    MODIFY board_seq INT NULL;

ALTER TABLE board
    MODIFY content VARCHAR(255);

ALTER TABLE board
    MODIFY content VARCHAR(255) NULL;

ALTER TABLE route_editors
    MODIFY editor INT NULL;

ALTER TABLE users
    MODIFY email VARCHAR(255);

ALTER TABLE users
    MODIFY email VARCHAR(255) NULL;

ALTER TABLE board_file
    MODIFY file_content_type VARCHAR(255);

ALTER TABLE board_file
    MODIFY file_content_type VARCHAR(255) NULL;

ALTER TABLE board_file
    MODIFY file_name VARCHAR(255);

ALTER TABLE board_file
    MODIFY file_name VARCHAR(255) NULL;

ALTER TABLE attraction_info
    MODIFY first_image VARCHAR(255);

ALTER TABLE gugun
    MODIFY gugun_name VARCHAR(255);

ALTER TABLE attraction_info
    MODIFY latitude DECIMAL;

ALTER TABLE attraction_info
    MODIFY longitude DECIMAL;

ALTER TABLE route
    MODIFY name VARCHAR(255) NULL;

ALTER TABLE users
    MODIFY name VARCHAR(255);

ALTER TABLE users
    MODIFY name VARCHAR(255) NULL;

ALTER TABLE attraction_description
    MODIFY overview VARCHAR(255);

ALTER TABLE route
    MODIFY overview VARCHAR(255) NULL;

ALTER TABLE route
    MODIFY owner INT NULL;

ALTER TABLE users
    MODIFY password VARCHAR(255);

ALTER TABLE users
    MODIFY password VARCHAR(255) NULL;

ALTER TABLE users
    MODIFY profile_image VARCHAR(255);

ALTER TABLE board
    MODIFY read_count INT NOT NULL;

ALTER TABLE route_attraction
    MODIFY route_seq INT NULL;

ALTER TABLE route_editors
    MODIFY route_seq INT NULL;

ALTER TABLE sido
    MODIFY sido_name VARCHAR(255);

ALTER TABLE route
    MODIFY start_date date NULL;

ALTER TABLE route
    MODIFY thumbnail VARCHAR(255);

ALTER TABLE route
    MODIFY thumbnail VARCHAR(255) NULL;

ALTER TABLE attraction_info
    MODIFY title VARCHAR(255);

ALTER TABLE board
    MODIFY title VARCHAR(255);

ALTER TABLE board
    MODIFY title VARCHAR(255) NULL;

ALTER TABLE board
    MODIFY user_seq INT NULL;

ALTER TABLE board_bookmark
    MODIFY user_seq INT NULL;