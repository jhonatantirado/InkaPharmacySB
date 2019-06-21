CREATE TABLE IF NOT EXISTS estadocuenta (
  id_estadoc INT NOT NULL AUTO_INCREMENT,
  dni INT NULL,
  nombrecli VARCHAR(30) NULL,
  producto VARCHAR(30) NULL,
  monto DOUBLE(5,2) NULL,
  fecha DATE,
  PRIMARY KEY (id_estadoc));
  
  
INSERT INTO inkapharmacy.estadocuenta (dni, nombrecli, producto,monto,fecha)
	VALUES	(45664829,'Gustavo Osorio', 'Tarjetas Credito','12.00','2018-12-11');
INSERT INTO inkapharmacy.estadocuenta (dni, nombrecli, producto,monto,fecha)
	VALUES	(45664828,'Juanita Carbo', 'Tarjetas CasBach','11.00','2018-11-11');
INSERT INTO inkapharmacy.estadocuenta (dni, nombrecli, producto,monto,fecha)
	VALUES	(45664827,'Joselyn Gomez', 'Tarjetas Plantinium','10.00','2018-11-11');
INSERT INTO inkapharmacy.estadocuenta (dni, nombrecli, producto,monto,fecha)
	VALUES	(45664826,'Carmen Montes', 'Tarjetas Debito','19.00','2017-12-11');		

