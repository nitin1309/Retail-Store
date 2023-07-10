DROP DATABASE IF EXISTS Retail_Store;
CREATE DATABASE Retail_Store;
USE Retail_Store;

CREATE TABLE Address(
	Address_ID CHAR(10) PRIMARY KEY,
    Street VARCHAR(50) NOT NULL,
    City VARCHAR(20) NOT NULL,
    State VARCHAR(20) NOT NULL,
    Pincode CHAR(6) NOT NULL
);

INSERT INTO Address VALUES(5678439871, 'A-1 ramgarh', 'Gurugram', 'Haryana', '110001');
INSERT INTO Address VALUES(5678439872, 'B-2 aligarh', 'Gurugram', 'Haryana', '110001');
INSERT INTO Address VALUES(5678439873, 'B-1 Azadpur', 'New Delhi', 'Delhi', '110033');
INSERT INTO Address VALUES(5678439874, 'C-2 Shalimar Bagh', 'New Delhi', 'Delhi', '110022');
INSERT INTO Address VALUES(5678439875, 'D-1 Mangolpuri', 'New Delhi', 'Delhi', '110002');
INSERT INTO Address VALUES(5678439876, 'B-1 Narnaul', 'Mahendergarh', 'Haryana', '110055');
INSERT INTO Address VALUES(5678439877, 'C-13 Nirmi Gao', 'Jhajjar', 'Haryana', '124103');
INSERT INTO Address VALUES(5678439878, 'Z-2 Gurchawk', 'Bhatinda', 'Punjab', '130089');
INSERT INTO Address VALUES(5678439879, 'C-1 Meerut chowk', 'Merrut Cantt', 'Uttar Pradesh', '110011');
INSERT INTO Address VALUES(5678439880, 'D-12 Azadpur', 'New Delhi', 'Delhi', '110033');
INSERT INTO Address VALUES(5678439881, 'Hudson Lane', 'New Delhi', 'Delhi', '110018');
INSERT INTO Address VALUES(5678439882, 'Andheri Street', 'Mumbai', 'Maharashtra', '210089');
INSERT INTO Address VALUES(5678439883, 'Satya Chowk', 'Guwahati', 'Assam', '100006');
INSERT INTO Address VALUES(5678439884, 'Dashin Dawar', 'Bengaluru', 'Karnataka', '104003');
INSERT INTO Address VALUES(5678439885, 'District Centre', 'Bhopal', 'Madhya Pradesh', '110332');
INSERT INTO Address VALUES(5678439886, 'Kalkaji', 'Oakhla', 'Delhi', '110201');
INSERT INTO Address VALUES(5678439887, 'C-11 Surya Nagar', 'Ghaziabad', 'Uttar Pradesh', '201011');
INSERT INTO Address VALUES(5678439888, 'A-4 Ramprastha', 'Ghaziabad', 'Uttar Pradesh', '201012');
INSERT INTO Address VALUES(5678439889, 'B-22 Rampuri', 'Ghaziabad', 'Uttar Pradesh', '201011');
INSERT INTO Address VALUES(5678439890, 'H-11 Kharghar', 'Navi Mumbai', 'Maharashtra', '110332');
INSERT INTO Address VALUES(5678439891, 'H-45 Ballard State', 'Navi Mumbai', 'Maharashtra', '900112');
INSERT INTO Address VALUES(5678439892, 'H-11 Kharghar', 'Navi Mumbai', 'Madhya Pradesh', '110332');
INSERT INTO Address VALUES(5678439893, 'A-25 Hauz Khaus', 'New Delhi', 'Delhi', '110220');
INSERT INTO Address VALUES(5678439894, 'C-11 Rohini', 'New Delhi', 'Delhi', '110332');
INSERT INTO Address VALUES(5678439895, 'D-26 Punjabi Bagh', 'New Delhi', 'Delhi', '114333');
INSERT INTO Address VALUES(5678439896, 'Imperial Street', 'New Delhi', 'Delhi', '110006');
INSERT INTO Address VALUES(5678439897, 'Hanuman Gali', 'Mumbai', 'Maharashtra', '110018');
INSERT INTO Address VALUES(5678439898, 'Aerocity Gardens', 'Bengaluru', 'Karnataka', '110608');
INSERT INTO Address VALUES(5678439899, 'Astra Kosh', 'Guwahati', 'Assam', '003944');
INSERT INTO Address VALUES(5678439900, 'Barakhamba Road', 'Bhopal', 'Madhya Pradesh', '300222');
INSERT INTO Address VALUES(5678439901, 'District Centre-2', 'Bhopal', 'Madhya Pradesh', '110332');
INSERT INTO Address VALUES(5678439902, 'Industral lane', 'New Delhi', 'Delhi', '103390');
INSERT INTO Address VALUES(5678439903, 'IST - 3', 'Mumbai', 'Maharashtra', '100082');
INSERT INTO Address VALUES(5678439904, 'Yamuna street', 'Guwahati', 'Assam', '100395');

CREATE TABLE Customer(
	Customer_ID CHAR(10) PRIMARY KEY,
    Customer_name VARCHAR(30) NOT NULL,
    Customer_password VARCHAR(20) NOT NULL,
    Phone_number CHAR(10) NOT NULL UNIQUE,
    Address_ID CHAR(10) NOT NULL,
    FOREIGN KEY (Address_ID) REFERENCES Address(Address_ID)
);

INSERT INTO Customer VALUES('3345671281', 'Aditya Mehra', 'aditya1122' , 9876543210 , 5678439871);
INSERT INTO Customer VALUES('3345671282', 'Aditya Anand', 'aditya2211' , 9876543211 , 5678439872);
INSERT INTO Customer VALUES('3345671283', 'Tanishq', 'tanishq2211' , 9876543212 , 5678439873);
INSERT INTO Customer VALUES('3345671284', 'Anmol Kaushik', 'anmol2211' , 9876543213 , 5678439874);
INSERT INTO Customer VALUES('3345671285', 'N Rhytham', 'tanishq1905' , 9876543214 , 5678439875);
INSERT INTO Customer VALUES('3345671286', 'VARUN KUMAR', 'varun1906' , 9876543215 , 5678439876);
INSERT INTO Customer VALUES('3345671287', 'ARUN KUMAR', 'arun1907' , 9876543216 , 5678439877);
INSERT INTO Customer VALUES('3345671288', 'GAURAV MEENA', 'gaurav1908' , 9876543217 , 5678439878);
INSERT INTO Customer VALUES('3345671289', 'ROHIT ROY', 'roy1909' , 9876543218 , 5678439879);
INSERT INTO Customer VALUES('3345671290', 'KIRTI', 'kirti1910' , 9876543219 , 5678439880);

CREATE TABLE Store(
	Store_ID CHAR(10) NOT NULL PRIMARY KEY,
	Phone CHAR(10) NOT NULL UNIQUE,
    Address_ID CHAR(10) NOT NULL,
    FOREIGN KEY (Address_ID) REFERENCES Address(Address_ID)
);

INSERT INTO Store VALUES('5567382131', '9932324556', 5678439881);
INSERT INTO Store VALUES('5567382132', '9905830489', 5678439882);
INSERT INTO Store VALUES('5567382133', '8739482063', 5678439883);
INSERT INTO Store VALUES('5567382134', '8700225678', 5678439884);
INSERT INTO Store VALUES('5567382135', '9398503349', 5678439885);

CREATE TABLE Employees(
	Employee_ID CHAR(10) NOT NULL PRIMARY KEY,
    Employee_name VARCHAR(30) NOT NULL, 
    Employee_password VARCHAR(20) NOT NULL, 
    Employee_phone CHAR(10) NOT NULL UNIQUE, 
    Employee_salary FLOAT NOT NULL,
    Store_ID CHAR(10) NOT NULL,
    Address_ID CHAR(10) NOT NULL,
    DOB DATE NOT NULL,
    FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID),
    FOREIGN KEY (Address_ID) REFERENCES Address(Address_ID)
);

/* Creating a view for Admin of the store */
CREATE VIEW V2 AS SELECT Customer_ID, Customer_name, Phone_number, Address_ID FROM Customer;
-- SELECT * FROM V2;

INSERT INTO Employees VALUES('7743562341', 'Shanu Kuttan', 'kuttan123', '9625956699', 40000, '5567382131', 5678439886, '1980-11-17');
INSERT INTO Employees VALUES('7743562342', 'Ankit Yadav', 'ankit2244', '9925956690', 5000, '5567382132', 5678439887, '1982-7-21');
INSERT INTO Employees VALUES('7743562343', 'Amit Kumar', 'kamit899', '9325956690', 22000, '5567382133', 5678439888, '1989-10-5');
INSERT INTO Employees VALUES('7743562344', 'Manish Verma', 'vermaji123', '7625956690', 70000, '5567382134', 5678439889, '1995-3-26');
INSERT INTO Employees VALUES('7743562345', 'Abhishek Goyal', 'abhi1523', '8625956690', 10000, '5567382135', 5678439890, '1987-5-12');
INSERT INTO Employees VALUES('7743562346', 'Varun Singh', 'varun1100', '9675956690', 80000, '5567382131', 5678439891, '1979-8-29');
INSERT INTO Employees VALUES('7743562347', 'Abhipriya Kumar', 'abhip11', '2625956690', 9000, '5567382132', 5678439892, '1982-11-25');
INSERT INTO Employees VALUES('7743562348', 'Akshat Kumar', 'akshat000', '7725956690', 6000, '5567382133', 5678439893, '1993-12-11');
INSERT INTO Employees VALUES('7743562349', 'Nisha Verma', 'nish2233', '9625555690', 70000, '5567382134', 5678439894, '1980-12-17');
INSERT INTO Employees VALUES('7743562350', 'Srishti Singh', 'sris123', '9877000099', 70000, '5567382135', 5678439895, '1995-9-12');

CREATE INDEX salary_index ON Employees (Employee_salary);

CREATE TABLE Products(
	Product_ID CHAR(10) NOT NULL PRIMARY KEY,
	Product_name VARCHAR(20) NOT NULL,
    Category VARCHAR(15) NOT NULL,
	Cost INT NOT NULL,
    Average_rating FLOAT
);

INSERT INTO Products VALUES('2216553341', 'Teddy Bear', 'Toys', 599, NULL);
INSERT INTO Products VALUES('2216553342', 'Fun Skool Car', 'Toys', 1299, NULL);
INSERT INTO Products VALUES('2216553343', 'Lego', 'Toys', 1199, NULL);
INSERT INTO Products VALUES('2216553344', 'Hot Wheels', 'Toys', 1999, NULL);

INSERT INTO Products VALUES('2216553345', 'Sunrise Over Ayodhya', 'Books', 499, NULL);
INSERT INTO Products VALUES('2216553346', 'The Canterbury Tales', 'Books', 299, NULL);
INSERT INTO Products VALUES('2216553347', '400 Days', 'Books', 199, NULL);
INSERT INTO Products VALUES('2216553348', 'Chanakya Neeti', 'Books', 249, NULL);

INSERT INTO Products VALUES('2216553349', 'Moisturizer', 'Cosmetics', 199, 4);
INSERT INTO Products VALUES('2216553350', 'Shampoo', 'Cosmetics', 699, NULL);
INSERT INTO Products VALUES('2216553351', 'Hair Oil', 'Cosmetics', 599, 4);
INSERT INTO Products VALUES('2216553352', 'Face Wash', 'Cosmetics', 549, 4);

INSERT INTO Products VALUES('2216553353', 'Wireless Mouse', 'Electronics', 899, 4);
INSERT INTO Products VALUES('2216553354', 'Air purifier', 'Electronics', 9999, NULL);
INSERT INTO Products VALUES('2216553355', 'Earphones', 'Electronics', 1499, NULL);
INSERT INTO Products VALUES('2216553356', 'Calculator', 'Electronics', 200, NULL);
INSERT INTO Products VALUES('2216553357', 'Wireless Keyboard', 'Electronics', 1899, NULL);

INSERT INTO Products VALUES('2216553358', 'Atta 1Kg', 'Grocery', 100, NULL);
INSERT INTO Products VALUES('2216553359', 'Potato 1Kg', 'Grocery', 70, NULL);
INSERT INTO Products VALUES('2216553360', 'Tata Tea 250g', 'Grocery', 250, 4);
INSERT INTO Products VALUES('2216553361', 'Almonds 250g', 'Grocery', 600, NULL);
INSERT INTO Products VALUES('2216553362', 'Coconut Oil 100ml', 'Grocery', 300, NULL);
INSERT INTO Products VALUES('2216553363', 'Maggi', 'Grocery', 20, NULL);

/* Creating a view for customer */
CREATE VIEW V1 AS SELECT Product_name, Category, Cost, Average_rating FROM Products;
SELECT * FROM V1;

CREATE INDEX productCost_index ON Products (Cost, Product_name);

CREATE TABLE Reviews(
	Product_ID CHAR(10) NOT NULL,
    Customer_ID CHAR(10) NOT NULL,
    Rating INT NOT NULL,
    FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID)
);

INSERT INTO Reviews VALUES('2216553349', '3345671285', 4);
INSERT INTO Reviews VALUES('2216553351', '3345671286', 4);
INSERT INTO Reviews VALUES('2216553352', '3345671287', 4);
INSERT INTO Reviews VALUES('2216553353', '3345671288', 4);
INSERT INTO Reviews VALUES('2216553360', '3345671289', 4);
INSERT INTO Reviews VALUES('2216553360', '3345671283', 3);
INSERT INTO Reviews VALUES('2216553360', '3345671287', 5);

CREATE TABLE Supplier(
	Supplier_ID CHAR(10) NOT NULL PRIMARY KEY,
	Supplier_name VARCHAR(30) NOT NULL,
	Phone CHAR(10) NOT NULL UNIQUE,
	Address_ID CHAR(10) NOT NULL,
    FOREIGN KEY (Address_ID) REFERENCES Address(Address_ID)
);

INSERT INTO Supplier VALUES('5567483320', 'Ramesh Wholesalers', '9948289145', 5678439896);
INSERT INTO Supplier VALUES('5567483321', 'Suresh Wholesalers', '9749229145', 5678439897);
INSERT INTO Supplier VALUES('5567483322', 'Eagle Traders', '9909275290', 5678439898);
INSERT INTO Supplier VALUES('5567483323', 'Birla Traders', '9030392004', 5678439899);
INSERT INTO Supplier VALUES('5567483324', 'Jain Wholesalers', '9590455355', 5678439900);
INSERT INTO Supplier VALUES('5567483325', 'Bharadwaj Wholesalers', '9398503349', 5678439901);

CREATE TABLE products_supplied(
	Product_ID CHAR(10) NOT NULL,
    Store_ID CHAR(10) NOT NULL,
    Supplier_ID CHAR(10) NOT NULL,
    Availability INT NOT NULL,
    Expiry_date DATE,
    PRIMARY KEY (Product_ID, Store_ID, Supplier_ID),
    FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID),
    FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID),
    FOREIGN KEY (Supplier_ID) REFERENCES Supplier(Supplier_ID)
);

INSERT INTO products_supplied VALUES('2216553341', '5567382131', '5567483324', 200, NULL);
INSERT INTO products_supplied VALUES('2216553342', '5567382134', '5567483325', 400, NULL);
INSERT INTO products_supplied VALUES('2216553344', '5567382133', '5567483325', 500, NULL);
INSERT INTO products_supplied VALUES('2216553348', '5567382135', '5567483325', 600, NULL);
INSERT INTO products_supplied VALUES('2216553355', '5567382132', '5567483325', 700, NULL);
INSERT INTO products_supplied VALUES('2216553355', '5567382132', '5567483324', 800, NULL);
INSERT INTO products_supplied VALUES('2216553355', '5567382132', '5567483323', 800, NULL);

CREATE TABLE Orders(
    Customer_ID CHAR(10) NOT NULL,
    Order_ID CHAR(10) NOT NULL,
    Product_ID CHAR(10) NOT NULL,
    Quantity INT NOT NULL,
    Total_cost INT NOT NULL,
    Booking_date DATE NOT NULL,
    Delivery_date DATE NOT NULL,
    Payment_mode VARCHAR(10) NOT NULL,
    Store_ID CHAR(10) NOT NULL,
    Charity_donation FLOAT NOT NULL,
    PRIMARY KEY (Order_ID, Product_ID),
    FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID),
    FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID)
);

CREATE INDEX cost_index ON Orders (Total_cost); 

CREATE TRIGGER update_cost
BEFORE INSERT
ON Orders
FOR EACH ROW
SET NEW.Total_cost = NEW.Quantity * (SELECT Cost FROM Products WHERE Product_ID = NEW.Product_ID);

CREATE TRIGGER charity_donation
BEFORE INSERT
ON Orders
FOR EACH ROW
SET NEW.Charity_donation = NEW.Total_cost*0.02;

Select * from Orders;

INSERT INTO Orders VALUES('3345671281', '3345643458', '2216553343', 100, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382131', 0);
INSERT INTO Orders VALUES('3345671282', '3345643218', '2216553353', 5, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382132', 0);
INSERT INTO Orders VALUES('3345671283', '3345643219', '2216553355', 7, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382133', 0);
INSERT INTO Orders VALUES('3345671289', '3345643220', '2216553359', 2, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382131', 0);
INSERT INTO Orders VALUES('3345671287', '3345643221', '2216553363', 1, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382134', 0);
INSERT INTO Orders VALUES('3345671285', '3345643222', '2216553360', 5, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382134', 0);
INSERT INTO Orders VALUES('3345671284', '3345643223', '2216553353', 6, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382132', 0);
INSERT INTO Orders VALUES('3345671283', '3345643224', '2216553357', 2, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382133', 0);
INSERT INTO Orders VALUES('3345671281', '3345643225', '2216553348', 3, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382135', 0);
INSERT INTO Orders VALUES('3345671282', '3345643226', '2216553359', 4, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382131', 0);
INSERT INTO Orders VALUES('3345671286', '3345643227', '2216553360', 9, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382132', 0);
INSERT INTO Orders VALUES('3345671285', '3345643228', '2216553351', 1, 0, '2022-3-15', '2022-3-18', 'Cash', '5567382133', 0);

CREATE TABLE Shopping_Cart(
	Customer_ID CHAR(10) NOT NULL,
    Product_ID CHAR(10) NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID),
    FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID)
);

INSERT INTO Shopping_Cart VALUES(3345671282, 2216553344, 5);
INSERT INTO Shopping_Cart VALUES(3345671286, 2216553348, 10);
INSERT INTO Shopping_Cart VALUES(3345671286, 2216553355, 2300);
INSERT INTO Shopping_Cart VALUES(3345671286, 2216553341, 200);

CREATE TABLE Charity(
	Charity_ID CHAR(10) NOT NULL PRIMARY KEY,
	Charity_name VARCHAR(20) NOT NULL,
	Address_ID CHAR(10) NOT NULL,
	Account_no VARCHAR(15) NOT NULL UNIQUE,
    FOREIGN KEY (Address_ID) REFERENCES Address(Address_ID)
);

INSERT INTO Charity VALUES('1656354637', 'The Smile Foundation', 5678439902, '14864521');
INSERT INTO Charity VALUES('5565287564', 'Feed the Babies', 5678439903, '264551230');
INSERT INTO Charity VALUES('6898578645', 'Youth Health Charity', 5678439904, '665165165');

/* GRANT COMMANDS FOR ADMIN, EMPLOYEE AND CUSTOMER*/

-- drop user Tony@localhost;
-- drop user jeffrey@localhost;
flush privileges;
CREATE USER 'jeffrey'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON Orders TO 'jeffrey'@'localhost';

CREATE USER 'Tony'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON Products TO 'Tony'@'localhost';


GRANT ALL ON * TO 'root'@'localhost';

Select * from Orders;

/*DIVERSE SQL QUERIES*/

SELECT *, RANK() OVER (ORDER BY Total_cost DESC) AS Rank_No FROM Orders;
SELECT * FROM Employees WHERE Employee_salary = (SELECT MAX(Employee_salary) FROM Employees WHERE Employee_salary != (SELECT MAX(Employee_salary) FROM Employees));
SELECT Category, COUNT(*) FROM (SELECT P.Category, P.Product_ID, O.Quantity FROM Products AS P INNER JOIN Orders AS O ON P.Product_ID = O.Product_ID) AS T GROUP BY Category;
SELECT *, RANK() OVER (ORDER BY Store_orders DESC) AS Rank_No FROM (SELECT Store_ID, COUNT(*) AS Store_orders FROM Orders GROUP BY Store_ID) AS T;
SELECT Product_ID, Product_name, SUM(Quantity) FROM (SELECT P.Category, P.Product_ID, O.Quantity, P.Product_name FROM Products AS P INNER JOIN Orders AS O ON P.Product_ID = O.Product_ID) AS T GROUP BY Product_ID;
SELECT Employee_name, Store_ID, Employee_salary FROM Employees WHERE Employee_salary IN (SELECT MAX(Employee_salary) FROM Employees GROUP BY Store_ID);
SELECT *, RANK() OVER (ORDER BY Total_donation DESC) AS Rank_no FROM (SELECT Store_ID, SUM(Charity_donation) AS Total_donation FROM Orders GROUP BY Store_ID) AS T;
SELECT Supplier_name, SUM(Availability) AS Quantity_supplied FROM (SELECT S.Supplier_name, S.Supplier_ID, PS.Availability FROM Supplier AS S INNER JOIN products_supplied AS PS ON S.Supplier_ID = PS.Supplier_ID) AS T GROUP BY Supplier_ID;
SELECT Product_ID, Product_name, Category FROM Products WHERE Cost IN (SELECT MAX(Cost) FROM Products GROUP BY Category);
SELECT Customer_name, SUM(Total_cost) AS Total_purchase_amount FROM (SELECT C.Customer_name, C.Customer_ID, O.Total_cost FROM Customer AS C INNER JOIN Orders AS O ON O.Customer_ID = C.Customer_ID) AS T GROUP BY Customer_ID;
