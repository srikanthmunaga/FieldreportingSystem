CREATE OR REPLACE FUNCTION Actual_pod (fdate      IN VARCHAR2,
                                       userrole   IN VARCHAR2,
                                       emp_id     IN VARCHAR2)
   RETURN NUMBER
IS
   tmpVar      NUMBER;
   userrole2   VARCHAR2 (10);
BEGIN
   tmpVar := 0;
   userrole2 := userrole;

   IF userrole = 'HO'
   THEN
      --Write HO query here
      SELECT SUM (w1.REMARKS_ACTPOD)
        INTO tmpVar
        FROM (  SELECT REMARKS_ACTPOD, bsflunit_ucode, REMARKS_FDATE
                  FROM WAR_REMARKS
                 WHERE (bsflunit_ucode, REMARKS_FDATE) IN (  SELECT bsflunit_ucode,
                                                                    MAX (
                                                                       REMARKS_FDATE)
                                                               FROM war_remarks
                                                              WHERE REMARKS_FDATE <=
                                                                       fdate
                                                           GROUP BY bsflunit_ucode)
              ORDER BY remarks_fdate DESC) w1;
   --dbms_output.put_line('inside HO');
   ELSIF userrole = 'AH'
   THEN
      --Write AH query here
      SELECT SUM (w1.REMARKS_ACTPOD)
        INTO tmpVar
        FROM (  SELECT REMARKS_ACTPOD,
                       bsflunit_ucode,
                       REMARKS_FDATE,
                       area_id
                  FROM WAR_REMARKS
                 WHERE (bsflunit_ucode, REMARKS_FDATE) IN (  SELECT bsflunit_ucode,
                                                                    MAX (
                                                                       REMARKS_FDATE)
                                                               FROM war_remarks
                                                              WHERE REMARKS_FDATE <=
                                                                       fdate
                                                           GROUP BY bsflunit_ucode)
              ORDER BY remarks_fdate DESC) w1
       WHERE w1.area_id in (emp_id);
   --  dbms_output.put_line('inside AH');
   --ELSIF userrole ='UH' THEN
   --Write AH query here
   --    dbms_output.put_line('inside UH');

   ELSE
      --Write LSR/UH query here, since both are same here
      SELECT SUM (w1.REMARKS_ACTPOD)
        INTO tmpVar
        FROM (  SELECT REMARKS_ACTPOD,
                       bsflunit_ucode,
                       REMARKS_FDATE,
                       area_id
                  FROM WAR_REMARKS
                 WHERE (bsflunit_ucode, REMARKS_FDATE) IN (  SELECT bsflunit_ucode,
                                                                    MAX (
                                                                       REMARKS_FDATE)
                                                               FROM war_remarks
                                                              WHERE REMARKS_FDATE <=
                                                                       fdate
                                                           GROUP BY bsflunit_ucode)
              ORDER BY remarks_fdate DESC) w1
       WHERE w1.bsflunit_ucode in (emp_id);
   --dbms_output.put_line('inside USER');
   --dbms_output.put_line('hey the while current index2');
   END IF;


   RETURN tmpVar;
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
      --NULL;
      RETURN '';
   WHEN OTHERS
   THEN
      -- Consider logging the error and then re-raise
      RAISE;
END Actual_pod;
/
