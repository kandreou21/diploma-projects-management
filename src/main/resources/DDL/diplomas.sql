CREATE DATABASE  IF NOT EXISTS `diplomas`;
USE `diplomas`;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `professors`;
DROP TABLE IF EXISTS `students`;
DROP TABLE IF EXISTS `subjects`;
DROP TABLE IF EXISTS `applications`;
DROP TABLE IF EXISTS `theses`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` text DEFAULT NULL,
  `password` text DEFAULT NULL,
  `role` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `professors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` text DEFAULT NULL,
  `specialty` text DEFAULT NULL,
  `user_name` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` text DEFAULT NULL,
  `year_of_studies` int NOT NULL DEFAULT 0,
  `avg_grade` double NOT NULL DEFAULT 0,
  `courses_remain` int NOT NULL DEFAULT 0,
  `user_name` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` text DEFAULT NULL,
  `objectives` text DEFAULT NULL,
  `supervisor_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_supervisor` FOREIGN KEY (`supervisor_id`) REFERENCES `professors` (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `applications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_applicantStudent` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `fk_applicantSubject` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `theses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `implementation_grade` double NOT NULL DEFAULT 0,
  `report_grade` double NOT NULL DEFAULT 0,
  `presentation_grade` double NOT NULL DEFAULT 0,
  `student_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  `professor_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`), 
  CONSTRAINT `fk_subject` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`),
  CONSTRAINT `fk_professor` FOREIGN KEY (`professor_id`) REFERENCES `professors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `users` VALUES 
	(1,'professor','professor','PROFESSOR'),
	(2,'student','student','STUDENT');
	
insert into professors(id, fullname, specialty, user_name)values(1,'professor','software','professor');
insert into students(id, fullname, year_of_studies, avg_grade, courses_remain, user_name)values(1,'student',5,7,8,'student');
insert into subjects(id, title, objectives, supervisor_id)values(1,'software engineering','spring boot',1);
insert into theses(id, implementation_grade, report_grade, presentation_grade, student_id, subject_id, professor_id)values(1,0,0,0,1,1,1);