DELIMITER $$

DROP PROCEDURE IF EXISTS aumentar_salario_funcionarios $$

CREATE PROCEDURE aumentar_salario_funcionarios(IN percentual INT)
BEGIN
    UPDATE funcionario
       SET salario = salario + (salario * percentual / 100);
END $$

DELIMITER ;
