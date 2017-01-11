CREATE TABLE `users` (
  `ac` varchar(32) NOT NULL,
  `pw` varchar(256) DEFAULT NULL,
  `role` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ac`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
