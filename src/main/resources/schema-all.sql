DROP TABLE IF EXISTS ITEM CASCADE;

CREATE TABLE ITEM(
 ID INT NOT NULL PRIMARY KEY,
 ITEM_NAME VARCHAR(100),
 PRICE INT,
 GROUPID VARCHAR(6),
 REGIST_DATE DATE,
 VERSION_NO INT
);
