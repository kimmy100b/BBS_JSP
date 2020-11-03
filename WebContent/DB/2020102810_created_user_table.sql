CREATE TABLE bbs.`user` (
	`userID` VARCHAR(20) NOT NULL COLLATE 'utf8_general_ci',
	`userPassword` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`userName` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`userGender` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`userEmail` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`userID`) USING BTREE
)
COMMENT='회원 DB'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
