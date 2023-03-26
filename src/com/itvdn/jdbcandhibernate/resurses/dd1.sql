-- use database programming;

drop table courses;

CREATE TABLE courses
(
    course_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name_of_cource VARCHAR(50),
    number_of_students INT,
    comment VARCHAR (100)

);