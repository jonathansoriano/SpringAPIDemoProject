CREATE TABLE university (
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(255) NOT NULL
);

CREATE TABLE student (
             id BIGINT AUTO_INCREMENT PRIMARY KEY,
             first_name VARCHAR(20) NOT NULL,
             last_name VARCHAR(20) NOT NULL,
             dob DATE NOT NULL,
             resident_city VARCHAR(40) NOT NULL,
             resident_state VARCHAR(2) NOT NULL,
             university_id BIGINT NOT NULL,
             grade VARCHAR(10) NOT NULL,
             FOREIGN KEY (university_id) REFERENCES university(id)
);
