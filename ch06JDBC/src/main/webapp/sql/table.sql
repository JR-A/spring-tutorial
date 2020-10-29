CREATE TABLE zboard(
	num NUMBER NOT NULL PRIMARY KEY,
	writer VARCHAR2(30) NOT NULL,
	title VARCHAR2(60) NOT NULL,
	passwd VARCHAR2(12) NOT NULL,
	content CLOB NOT NULL,
	reg_date DATE NOT NULL
);

CREATE SEQUENCE zboard_seq;

COMMIT;