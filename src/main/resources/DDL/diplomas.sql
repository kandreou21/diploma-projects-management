CREATE DATABASE  IF NOT EXISTS `diplomas`;
USE `diplomas`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `skatausers,subjects`;


CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` text DEFAULT NULL,
  `password` text DEFAULT NULL,
  `role` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` text DEFAULT NULL,
  `objectives` text DEFAULT NULL,
  `applications` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `apps` (
  `id` int NOT NULL AUTO_INCREMENT,
  --`student_id` int,
  --FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) 
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;