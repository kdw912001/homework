﻿--1
SELECT STUDENT_NAME "학생 이름", STUDENT_ADDRESS 주소지
FROM TB_STUDENT
ORDER BY STUDENT_NAME ASC;

--2
SELECT STUDENT_NAME, STUDENT_SSN
FROM TB_STUDENT
WHERE ABSENCE_YN = 'Y'
ORDER BY STUDENT_SSN DESC;

--3
SELECT STUDENT_NAME 학생이름, STUDENT_NO 학번, STUDENT_ADDRESS "거주지 주소" 
FROM TB_STUDENT
WHERE STUDENT_NO LIKE '9%' AND STUDENT_ADDRESS LIKE '경기도%' OR STUDENT_ADDRESS LIKE '강원도'
ORDER BY STUDENT_NAME ASC;

--4 --JOIN, 서브쿼리로
SELECT PROFESSOR_NAME, PROFESSOR_SSN
FROM TB_PROFESSOR
WHERE DEPARTMENT_NO = '005'
ORDER BY PROFESSOR_SSN ASC;

SELECT DISTINCT PROFESSOR_NAME, PROFESSOR_SSN
FROM TB_STUDENT
JOIN TB_PROFESSOR USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NO = '005'
ORDER BY PROFESSOR_SSN ASC;
--5
SELECT STUDENT_NO, POINT
FROM TB_GRADE
WHERE TERM_NO = '200402' AND CLASS_NO = 'C3118100'
ORDER BY POINT DESC, STUDENT_NO ASC;

--6
SELECT STUDENT_NO, STUDENT_NAME, DEPARTMENT_NAME
FROM TB_STUDENT S, TB_DEPARTMENT D
WHERE S.DEPARTMENT_NO = D.DEPARTMENT_NO
ORDER BY STUDENT_NAME ASC, DEPARTMENT_NAME;

SELECT STUDENT_NO, STUDENT_NAME, DEPARTMENT_NAME
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
ORDER BY STUDENT_NAME ASC, DEPARTMENT_NAME;

--7
SELECT CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS C, TB_DEPARTMENT D
WHERE C.DEPARTMENT_NO = D.DEPARTMENT_NO
ORDER BY DEPARTMENT_NAME ASC;

SELECT CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS
INNER JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
ORDER BY DEPARTMENT_NAME ASC;

--8
SELECT CLASS_NAME, PROFESSOR_NAME
FROM TB_CLASS_PROFESSOR
JOIN TB_CLASS USING(CLASS_NO)
JOIN TB_PROFESSOR USING(PROFESSOR_NO);

--9

SELECT CLASS_NAME, PROFESSOR_NAME
FROM TB_CLASS_PROFESSOR
JOIN TB_CLASS USING(CLASS_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
JOIN TB_PROFESSOR USING(PROFESSOR_NO)
WHERE CATEGORY = '인문사회';

--10
SELECT STUDENT_NO 학번, STUDENT_NAME "학생 이름", ROUND(AVG(POINT),1) "전체 평점"
FROM TB_GRADE
JOIN TB_STUDENT USING (STUDENT_NO)
WHERE DEPARTMENT_NO = '059'
GROUP BY STUDENT_NO, STUDENT_NAME
ORDER BY ROUND(AVG(POINT),1) DESC;

--11
SELECT DEPARTMENT_NAME 학과이름, STUDENT_NAME 학생이름, PROFESSOR_NAME 지도교수이름
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
JOIN TB_PROFESSOR ON (COACH_PROFESSOR_NO = PROFESSOR_NO)
WHERE STUDENT_NO = 'A313047';

--12
SELECT STUDENT_NAME, TERM_NO
FROM TB_GRADE
JOIN TB_STUDENT USING(STUDENT_NO)
JOIN TB_CLASS USING(CLASS_NO)
WHERE CLASS_NAME = '인간관계론' AND TERM_NO LIKE '2007%';

--13

SELECT CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
LEFT JOIN TB_CLASS_PROFESSOR USING (CLASS_NO)
WHERE CATEGORY = '예체능' AND PROFESSOR_NO IS NULL;

--차집합을 이용 선용
SELECT CLASS_NO
FROM TB_CLASS
WHERE DEPARTMENT_NO >= '056' AND DEPARTMENT_NO <= '063'
MINUS
SELECT CLASS_NO
FROM TB_CLASS_PROFESSOR;
--WHERE PROFESSOR_NO IS NOT NULL;

--14
SELECT STUDENT_NAME 학생이름, NVL(PROFESSOR_NAME, '지도교수 미지정')지도교수
--DECODE(PROFESSOR_NAME,NULL,'지도교수 미지정',PROFESSOR_NAME)지도교수
-- CASE PROFESSOR_NAME WHEN NULL THEN '지도교수 미지정' ELSE PROFESSOR_NAME END 지도교수
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
LEFT JOIN TB_PROFESSOR ON (PROFESSOR_NO = COACH_PROFESSOR_NO)
WHERE DEPARTMENT_NAME = (SELECT DEPARTMENT_NAME
                        FROM TB_DEPARTMENT
                        WHERE DEPARTMENT_NAME = '서반아어학과');
                        
--15
SELECT STUDENT_NO 학번, STUDENT_NAME 이름, DEPARTMENT_NAME "학과 이름", ROUND(AVG(POINT),8) 평점
FROM TB_GRADE
JOIN TB_STUDENT USING(STUDENT_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE ABSENCE_YN = 'N'
GROUP BY STUDENT_NO, STUDENT_NAME, DEPARTMENT_NAME
HAVING AVG(POINT) >= 4.0
ORDER BY STUDENT_NAME;

--16
SELECT CLASS_NO, CLASS_NAME, TRUNC(AVG(POINT),8)
FROM TB_GRADE
JOIN TB_CLASS USING (CLASS_NO)
JOIN TB_DEPARTMENT USING (DEPARTMENT_NO)
WHERE DEPARTMENT_NAME = '환경조경학과' AND CLASS_TYPE LIKE '전공%'
GROUP BY CLASS_NO, CLASS_NAME
ORDER BY CLASS_NO;

--17
SELECT STUDENT_NAME, STUDENT_ADDRESS, DEPARTMENT_NAME
FROM TB_STUDENT 
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NAME IN (SELECT DEPARTMENT_NAME
                        FROM TB_DEPARTMENT
                        JOIN TB_STUDENT USING(DEPARTMENT_NO)
                        WHERE STUDENT_NAME = '최경희');
                        
--18
--RANK() 활용해보려다가 모조리 다 실패
SELECT STUDENT_NO, STUDENT_NAME
FROM TB_STUDENT
WHERE STUDENT_NO = SELECT STUDENT_NO
                    FROM TB_GRADE
                    JOIN TB_STUDENT USING(STUDENT_NO)
                    WHERE RANK()OVER(ORDER BY AVG(POINT) DESC) = 1
                    GROUP BY TERM_NO, STUDENT_NO, CLASS_NO;
JOIN TB_STUDENT USING(STUDENT_NO)
GROUP BY TERM_NO, STUDENT_NO, CLASS_NO
HAVING MAX(POINT);


SELECT STUDENT_NO, AVG(POINT), RANK()OVER(ORDER BY AVG(POINT) DESC)--, RANK()OVER(ORDER BY AVG(POINT))
FROM TB_GRADE
JOIN TB_STUDENT USING(STUDENT_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NAME ='국어국문학과' 
GROUP BY STUDENT_NO;
--HAVING RANK()OVER(ORDER BY AVG(POINT) DESC)=1;



SELECT --STUDENT_NO, AVG(POINT),
        MAX(AVG(POINT))
FROM TB_GRADE
JOIN TB_STUDENT USING(STUDENT_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NAME ='국어국문학과' 
GROUP BY STUDENT_NO;

SELECT --STUDENT_NO,AVG(POINT),
    (RANK()OVER(ORDER BY AVG(POINT) DESC))
FROM TB_GRADE
JOIN TB_STUDENT USING(STUDENT_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NAME ='국어국문학과' 
GROUP BY STUDENT_NO;
--HAVING MIN(RANK()OVER(ORDER BY AVG(POINT) DESC));

SELECT STUDENT_NO, STUDENT_NAME
FROM TB_GRADE
WHERE STUDENT_NO = (SELECT STUDENT_NO
                    );


--이것만 2시간 하다 빡쳐서 석호꺼 봄 이건 석호가 한거
SELECT STUDENT_NO, STUDENT_NAME
FROM TB_GRADE
JOIN TB_STUDENT USING(STUDENT_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NAME ='국어국문학과' 
GROUP BY STUDENT_NO, STUDENT_NAME
HAVING AVG(POINT) = (SELECT MAX(AVG(POINT))
                    FROM TB_GRADE
                    JOIN TB_STUDENT USING(STUDENT_NO)
                    JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
                    WHERE DEPARTMENT_NAME ='국어국문학과' 
                    GROUP BY STUDENT_NO);
--인터넷 서칭해보니 내가 하려는 방식으로 하려면 RANK를 FROM절에 서브쿼리를 사용해서 먼저 실행
--FROM 안에 서브쿼리를 사용하려면 서브쿼리문이 FROM에서 끝나야 함.
--선생님께 물어보기
SELECT *
FROM(
    SELECT STUDENT_NO, AVG(POINT), RANK()OVER(ORDER BY AVG(POINT) DESC) 순위--, RANK()OVER(ORDER BY AVG(POINT))
    FROM TB_GRADE
    JOIN TB_STUDENT USING(STUDENT_NO)
    JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
    WHERE DEPARTMENT_NAME ='국어국문학과' 
    GROUP BY STUDENT_NO;
)
WHERE 순위 = 1;
--HAVING RANK()OVER(ORDER BY AVG(POINT) DESC)=1;


--19
SELECT DEPARTMENT_NAME "계열 학과명", ROUND(AVG(POINT),1) 전공평점
FROM TB_GRADE
JOIN TB_STUDENT USING(STUDENT_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
JOIN TB_CLASS USING(CLASS_NO)
WHERE CATEGORY = (SELECT CATEGORY
                  FROM TB_DEPARTMENT
                  WHERE DEPARTMENT_NAME = '환경조경학과')
AND CLASS_TYPE LIKE '전공%'
GROUP BY DEPARTMENT_NAME
ORDER BY DEPARTMENT_NAME;


