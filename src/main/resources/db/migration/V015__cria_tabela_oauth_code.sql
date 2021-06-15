CREATE TABLE oauth_code (
	code varchar(256),
	authentication blob
) engine=InnoDB default charset=UTF8MB4;