Create table editorial 
(
	editorial_id int(11) not null AUTO_INCREMENT,
	heading_text varchar(1000), 
    main_content mediumtext,
    editorial_date datetime DEFAULT current_timestamp,
    user_id int(11),
    primary key(editorial_id),
    KEY FK_USER_EDITORIAL (user_id),
  	CONSTRAINT FK_USER_EDITORIAL FOREIGN KEY (user_id) REFERENCES user_details (user_id)
);