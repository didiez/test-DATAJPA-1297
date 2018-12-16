CREATE PROCEDURE DOSOMETHING (param1 IN NUMBER, param2 IN NUMBER, res OUT VARCHAR2) AS
BEGIN
    if param1=param2 then
        res:='OK';
    else
        res:='KO';
    end if;
END; @@