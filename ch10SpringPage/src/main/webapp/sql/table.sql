CREATE TABLE spmember(
	mem_num NUMBER NOT NULL,
	id VARCHAR2(12) UNIQUE NOT NULL,
	auth NUMBER(1) DEFAULT 2 NOT NULL, /*0:탈퇴회원, 1:정지회원, 2:일반회원, 3:관리자*/
	
	CONSTRAINT spmember_pk PRIMARY KEY (mem_num)
);

CREATE TABLE spmember_detail(
	mem_num NUMBER NOT NULL,
	name VARCHAR2(30) NOT NULL,
	passwd VARCHAR2(35) NOT NULL,
	phone VARCHAR2(15) NOT NULL,
	email VARCHAR2(50) NOT NULL,
	zipcode VARCHAR2(5) NOT NULL,
	address1 VARCHAR2(90) NOT NULL,
	address2 VARCHAR2(90) NOT NULL,
	photo BLOB, /*BLOB : 대용량 바이너리 파일(Binary Large Object)*/
	photoname VARCHAR2(100), /*이미지파일명*/
	reg_date DATE DEFAULT SYSDATE NOT NULL,
	modify_date DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT spmember_detail_pk PRIMARY KEY (mem_num),
	CONSTRAINT spmember_detail_fk FOREIGN KEY (mem_num) REFERENCES spmember (mem_num)
);

CREATE SEQUENCE member_seq START WITH 100;

/* 관리자 입력 */
INSERT INTO spmember (mem_num, id, auth) VALUES (1, 'admin', 3);
INSERT INTO spmember_detail (mem_num, name, passwd, phone, email, zipcode, address1, address2) 
VALUES (1, '관리자', '1234', '010-1234-1234', 'test@test.com', '12345', '서울시 강남구 역삼동', '푸르지오A');

COMMIT;

CREATE TABLE spboard(
	board_num NUMBER NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content CLOB NOT NULL,
	hit NUMBER(5) DEFAULT 0 NOT NULL,
	reg_date DATE DEFAULT SYSDATE NOT NULL,
	modify_date DATE DEFAULT SYSDATE NOT NULL,
	uploadfile BLOB,
	filename VARCHAR2(100),
	ip VARCHAR2(40) NOT NULL,
	mem_num NUMBER NOT NULL,
	
	CONSTRAINT spboard_pk PRIMARY KEY (board_num),
	CONSTRAINT spboard_fk FOREIGN KEY (mem_num) REFERENCES spmember (mem_num)
);

CREATE SEQUENCE board_seq;

COMMIT;