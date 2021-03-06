SELECT * FROM USER_COL_COMMENTS WHERE  TABLE_NAME IN ('TB_BOOK', 'TB_BOOK_AUTHOR', 'TB_PUBLISHER', 'TB_WRITER');

-- 1. 
SELECT 'SELECT COUNT(*) FROM '||TABLE_NAME||';' AS " "
FROM   USER_TABLES U
WHERE  TABLE_NAME IN ('TB_BOOK', 'TB_BOOK_AUTHOR', 'TB_PUBLISHER', 'TB_WRITER');

--2.
SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE, DATA_DEFAULT, NULLABLE, CONSTRAINT_NAME, CONSTRAINT_TYPE, R_CONSTRAINT_NAME
FROM   USER_TAB_COLS 
LEFT JOIN   (SELECT TABLE_NAME, COLUMN_NAME, CONSTRAINT_NAME, CONSTRAINT_TYPE, R_CONSTRAINT_NAME
              FROM   USER_CONS_COLUMNS
				      JOIN   USER_CONSTRAINTS USING  (TABLE_NAME, CONSTRAINT_NAME)
				      WHERE  TABLE_NAME IN ('TB_BOOK', 'TB_BOOK_AUTHOR','TB_PUBLISHER','TB_WRITER')) V USING (TABLE_NAME, COLUMN_NAME) 
WHERE  TABLE_NAME IN ('TB_BOOK', 'TB_BOOK_AUTHOR', 'TB_PUBLISHER', 'TB_WRITER')
ORDER BY 1, 2;

--3.
SELECT BOOK_NO, BOOK_NM
FROM   TB_BOOK
WHERE  LENGTH(BOOK_NM) >= 25; 

--4.
SELECT *
FROM   ( SELECT WRITER_NM, 
                 OFFICE_TELNO, 
								 HOME_TELNO, 
								 MOBILE_NO
         FROM   TB_WRITER
				 WHERE  WRITER_NM LIKE '김%'
				 AND    MOBILE_NO LIKE '019-%'
				 ORDER BY 1 )
WHERE  ROWNUM = 1;
--또는
SELECT WRITER_NM, OFFICE_TELNO, HOME_TELNO, MOBILE_NO
FROM   ( SELECT WRITER_NM, 
                 OFFICE_TELNO, 
								 HOME_TELNO, 
								 MOBILE_NO,
								 RANK() OVER (ORDER BY WRITER_NM) AS RANK
         FROM   TB_WRITER
				 WHERE  WRITER_NM LIKE '김%'
				 AND    MOBILE_NO LIKE '019-%' )
WHERE   RANK = 1;				 

--5.
SELECT COUNT(DISTINCT WRITER_NM)"작가(명)"
FROM   TB_WRITER
JOIN   TB_BOOK_AUTHOR USING  (WRITER_NO)
WHERE  COMPOSE_TYPE = '옮김'; 

--6.
SELECT  COMPOSE_TYPE, COUNT(*)
FROM   TB_BOOK_AUTHOR
WHERE  COMPOSE_TYPE IS NOT NULL
GROUP BY COMPOSE_TYPE
HAVING COUNT(*) >= 300
ORDER BY 2 DESC, 1; 

--7.
SELECT BOOK_NM,
        ISSUE_DATE,
        PUBLISHER_NM
FROM   TB_BOOK
WHERE  ISSUE_DATE = (SELECT MAX(ISSUE_DATE)
                      FROM   TB_BOOK); 

--8.
SELECT *
FROM   (SELECT WRITER_NM AS "작가 이름", 
                COUNT(*) AS "권 수"
        FROM   TB_BOOK_AUTHOR
        JOIN   TB_WRITER USING (WRITER_NO)
        GROUP  BY WRITER_NM
        ORDER  BY 2 DESC)
WHERE  ROWNUM <= 3 ; 

--9.
UPDATE TB_WRITER A
SET    REGIST_DATE = (SELECT MIN(ISSUE_DATE)
                       FROM   TB_BOOK_AUTHOR 
                       JOIN   TB_BOOK USING (BOOK_NO)
                       WHERE  A.WRITER_NO = WRITER_NO); 
COMMIT;											 
											 							 
--10.
CREATE TABLE TB_BOOK_TRANSLATOR
(
  WRITER_NO  VARCHAR2(10) NOT NULL CONSTRAINTS FK_BOOK_TRANSLATOR_02 REFERENCES TB_WRITER,
  BOOK_NO    VARCHAR2(10) NOT NULL CONSTRAINTS FK_BOOK_TRANSLATOR_01 REFERENCES TB_BOOK,
  TRANS_LANG VARCHAR2(60),
	CONSTRAINTS PK_BOOK_TRANSLATOR PRIMARY KEY (BOOK_NO, WRITER_NO)
);
--또는
CREATE TABLE TB_BOOK_TRANSLATOR
(
  WRITER_NO  VARCHAR2(10) NOT NULL,
  BOOK_NO    VARCHAR2(10) NOT NULL,
  TRANS_LANG VARCHAR2(60)
);
ALTER TABLE TB_BOOK_TRANSLATOR
  ADD CONSTRAINT PK_BOOK_TRANSLATOR PRIMARY KEY (BOOK_NO, WRITER_NO);
ALTER TABLE TB_BOOK_TRANSLATOR
  ADD CONSTRAINT FK_BOOK_TRANSLATOR_01 FOREIGN KEY (BOOK_NO)
  REFERENCES TB_BOOK (BOOK_NO);
ALTER TABLE TB_BOOK_TRANSLATOR
  ADD CONSTRAINT FK_BOOK_TRANSLATOR_02 FOREIGN KEY (WRITER_NO)
  REFERENCES TB_WRITER (WRITER_NO);
	
-- 주석은 Optional..
COMMENT ON COLUMN TB_BOOK_TRANSLATOR.WRITER_NO  IS '작가 번호';
COMMENT ON COLUMN TB_BOOK_TRANSLATOR.BOOK_NO  IS '도서 번호';
COMMENT ON COLUMN TB_BOOK_TRANSLATOR.TRANS_LANG  IS '번역 언어';

--11.
INSERT INTO TB_BOOK_TRANSLATOR (BOOK_NO, WRITER_NO)
  SELECT BOOK_NO, WRITER_NO
  FROM   TB_BOOK_AUTHOR
  WHERE  COMPOSE_TYPE IN ('옮김', '역주', '편역', '공역'); 
DELETE FROM TB_BOOK_AUTHOR 
  WHERE  COMPOSE_TYPE IN ('옮김', '역주', '편역', '공역');
COMMIT; 

--12.
SELECT BOOK_NM, 
        WRITER_NM,
        ISSUE_DATE 
FROM   TB_WRITER
JOIN   TB_BOOK_TRANSLATOR USING  (WRITER_NO)
JOIN   TB_BOOK USING  (BOOK_NO)
WHERE  TO_CHAR(ISSUE_DATE, 'RRRR') = '2007'
ORDER BY 1; 

--13.
CREATE OR REPLACE VIEW VW_BOOK_TRANSLATOR AS
SELECT BOOK_NM, 
       WRITER_NM 
FROM   TB_WRITER
JOIN   TB_BOOK_TRANSLATOR USING  (WRITER_NO)
JOIN   TB_BOOK USING  (BOOK_NO)
WHERE  TO_CHAR(ISSUE_DATE, 'RRRR') = '2007'
WITH CHECK OPTION; 

--14.
INSERT INTO TB_PUBLISHER(PUBLISHER_NM, PUBLISHER_TELNO) 
VALUES  ('춘 출판사', '02-6710-3737'); 
COMMIT;

--15.
SELECT WRITER_NM, 
        COUNT(*)
FROM   TB_WRITER
GROUP BY WRITER_NM
HAVING COUNT(*) > 1; 

--16.
UPDATE TB_BOOK_AUTHOR 
SET    COMPOSE_TYPE='지음'
WHERE  COMPOSE_TYPE IS NULL; 
COMMIT;

--17.
SELECT WRITER_NM, 
        OFFICE_TELNO
FROM   TB_WRITER
WHERE  OFFICE_TELNO LIKE '02%'
AND    OFFICE_TELNO LIKE '02-___-%'
ORDER BY 1; 

--18.
SELECT WRITER_NM
FROM   TB_WRITER
WHERE  MONTHS_BETWEEN(TO_DATE('20060101','YYYYMMDD'), REGIST_DATE) >= 372
ORDER BY 1;

--19.
SELECT BOOK_NM AS "도서명",
        PRICE AS "가격",
      CASE
         WHEN STOCK_QTY < 5 THEN
          '추가주문필요'
         ELSE
          '소량보유' 
       END "재고상태" 
FROM   TB_BOOK
WHERE  PUBLISHER_NM = '황금가지'
AND    STOCK_QTY < 10
ORDER BY STOCK_QTY DESC, 1; 

--20.
SELECT A.BOOK_NM AS "도서명",
       D.WRITER_NM AS "저자",
       E.WRITER_NM AS "역자"
FROM   TB_BOOK A
JOIN   TB_BOOK_AUTHOR B ON  (A.BOOK_NO = B.BOOK_NO)
JOIN   TB_BOOK_TRANSLATOR C ON  (A.BOOK_NO = C.BOOK_NO)
JOIN   TB_WRITER D ON  (D.WRITER_NO = B.WRITER_NO )
JOIN   TB_WRITER E ON  (E.WRITER_NO = C.WRITER_NO )
WHERE  BOOK_NM = '아타트롤'; 
-- 또는
SELECT BOOK_NM,  "저자",  "역자"
FROM   (SELECT BOOK_NM,
               WRITER_NM "저자"
        FROM   TB_BOOK
        JOIN   TB_BOOK_AUTHOR USING  (BOOK_NO)
        JOIN   TB_WRITER USING  (WRITER_NO)
        WHERE  BOOK_NM = '아타트롤')
JOIN   (SELECT BOOK_NM,
               WRITER_NM "역자"
        FROM   TB_BOOK
        JOIN   TB_BOOK_TRANSLATOR USING  (BOOK_NO)
        JOIN   TB_WRITER USING  (WRITER_NO)
        WHERE  BOOK_NM = '아타트롤')
USING  (BOOK_NM); 
-- 또는 
-- 단 건 일 경우만 가능 
SELECT BOOK_NM,
       (SELECT WRITER_NM
        FROM   TB_BOOK
        JOIN   TB_BOOK_AUTHOR USING  (BOOK_NO)
        JOIN   TB_WRITER USING  (WRITER_NO)
        WHERE  BOOK_NM = '아타트롤') "저자",
       (SELECT WRITER_NM
        FROM   TB_BOOK
        JOIN   TB_BOOK_TRANSLATOR USING  (BOOK_NO)
        JOIN   TB_WRITER USING  (WRITER_NO)
        WHERE  BOOK_NM = '아타트롤') "역자"
FROM   TB_BOOK
WHERE  BOOK_NM = '아타트롤'; 

--21.
SELECT BOOK_NM AS "도서명", 
        STOCK_QTY AS "재고 수량", 
				TO_CHAR(PRICE, '99,999') AS "가격(Org)",
				TO_CHAR(PRICE*0.8, '99,999') AS "가격(New)"
FROM   TB_BOOK
WHERE  MONTHS_BETWEEN(SYSDATE, ISSUE_DATE) >= 360
AND    STOCK_QTY >= 90
ORDER BY 2 DESC, 4 DESC, 1;

-- 이럴 경우  만 30년이 아님
--WHERE  TO_CHAR(SYSDATE,'yyyy') - TO_CHAR(ISSUE_DATE,'yyyy') >= 30
