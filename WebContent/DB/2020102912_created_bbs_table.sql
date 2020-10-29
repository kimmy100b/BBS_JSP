CREATE TABLE `bbs` (
	`bbsID` INT(10) NOT NULL,
	`bbsTitle` VARCHAR(50) NULL DEFAULT NULL COMMENT '제목' COLLATE 'utf8_general_ci',
	`userID` VARCHAR(20) NULL DEFAULT NULL COMMENT '사용자 ID' COLLATE 'utf8_general_ci',
	`bbsContent` MEDIUMTEXT NULL DEFAULT NULL COMMENT '내용' COLLATE 'utf8_general_ci',
	`regDate` DATETIME NULL DEFAULT NULL COMMENT '등록일',
	`bbsAvailable` INT(10) NULL DEFAULT NULL,
	PRIMARY KEY (`bbsID`) USING BTREE
)
COMMENT='게시판 DB'
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;