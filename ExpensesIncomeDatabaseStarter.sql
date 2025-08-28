/*------------------------------------table - expenses--------------------------------------*/
DROP TABLE IF EXISTS expenses; /*drops table 'expenses' if it already exists*/

/*creates the 'expenses' table*/
CREATE TABLE expenses(expenseID int NOT NULL AUTO_INCREMENT, 
             title VARCHAR(20),
             category VARCHAR(15),
             amount DECIMAL(10,2), /*learned about decimal data types from https://www.sqlshack.com/understanding-sql-decimal-data-type/ */
             dateIncurred DATE,
			 PRIMARY KEY (expenseID));

/*populating 'expenses' with some data entries*/
INSERT INTO expenses VALUES (1, 'weekly shop', 'groceries', 47.50, '2025-1-7' ); /*example from brief*/
INSERT INTO expenses VALUES (2, 'gym membership', 'fitness', 30.00, '2025-1-9'); /*example from brief*/
INSERT INTO expenses VALUES (3, 'chocolate', 'gift', 23.22, '2025-2-14');
INSERT INTO expenses VALUES (4, 'markers', 'stationery', 16.70, '2025-1-27');

/*------------------------------------table - income--------------------------------------*/
DROP TABLE IF EXISTS income;

/*creates the 'income' table*/
CREATE TABLE income(incomeID int NOT NULL AUTO_INCREMENT, 
             title VARCHAR(20),
             amount DECIMAL(10,2),
             dateIncurred DATE,
			 PRIMARY KEY (incomeID));

/*populating 'expenses' with some data entries*/
INSERT INTO income VALUES (1, 'babysitting', 60.00, '2025-1-5'); /*example from brief*/
INSERT INTO income VALUES (2, 'bar work',  100.00, '2025-1-7'); /*example from brief*/
INSERT INTO income VALUES (3, 'website creation', 120.50, '2025-2-1');
INSERT INTO income VALUES (4, 'debt of Ryan', 20.20 '2025-2-12');