CREATE PROCEDURE DOSOMETHING (param1 INT, param2 INT, OUT res VARCHAR(20))
DETERMINISTIC
BEGIN
    IF param1 = param2 THEN
      SET res = 'OK';
   ELSE
      SET res = 'KO';
   END IF;
END; @@