CREATE TABLE Employees
   (EmployeeID INTEGER NOT NULL, 
	LastName VARCHAR2(50) NOT NULL,
	FirstName VARCHAR2(50) NOT NULL
	);

Create Unique Index PK_Employees
		On Employees(EmployeeID);
    
CREATE SEQUENCE EMPLOYEES_SEQ
START WITH 10
INCREMENT BY 1
MAXVALUE 999999999999;

Insert Into Employees(EmployeeID,LastName,FirstName) 
VALUES(EMPLOYEES_SEQ.NEXTVAL,'Davolio','Nancy');

Insert Into Employees(EmployeeID,LastName,FirstName) 
VALUES(EMPLOYEES_SEQ.NEXTVAL,'Fuller','Andrew');

Insert Into Employees(EmployeeID,LastName,FirstName) 
VALUES(EMPLOYEES_SEQ.NEXTVAL,'Leverling','Janet');

Insert Into Employees(EmployeeID,LastName,FirstName) 
VALUES(EMPLOYEES_SEQ.NEXTVAL,'Peacock','Margaret');

Insert Into Employees(EmployeeID,LastName,FirstName) 
VALUES(EMPLOYEES_SEQ.NEXTVAL,'Buchanan','Steven');

Insert Into Employees(EmployeeID,LastName,FirstName) 
VALUES(EMPLOYEES_SEQ.NEXTVAL,'Suyama','Michael');

Insert Into Employees(EmployeeID,LastName,FirstName) 
VALUES(EMPLOYEES_SEQ.NEXTVAL,'King','Robert');

Insert Into Employees(EmployeeID,LastName,FirstName) 
VALUES(EMPLOYEES_SEQ.NEXTVAL,'Callahan','Laura');

Insert Into Employees(EmployeeID,LastName,FirstName) 
VALUES(EMPLOYEES_SEQ.NEXTVAL,'Dodsworth','Anne');