use mall

-- Ensure foreign key checks are disabled to clear the tables
SET FOREIGN_KEY_CHECKS = 0;

-- Clear the data from the tables
TRUNCATE TABLE OrderDetails;
TRUNCATE TABLE Payments;
TRUNCATE TABLE Carts;
TRUNCATE TABLE Orders;
TRUNCATE TABLE Products;
TRUNCATE TABLE Users;

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Insert test data into `Users`
INSERT INTO Users (Username, Password, Email) VALUES
                                                  ('Alice', 'password123', 'alice@example.com'),
                                                  ('Bob', 'password456', 'bob@example.com');

-- Insert test data into `Products`
INSERT INTO Products (Name, Description, Price, Stock, ImageURL) VALUES
                                                                     ('Coffee Mug', 'A large coffee mug.', 9.99, 100, 'http://example.com/mug.jpg'),
                                                                     ('Tea Cup', 'A delicate tea cup.', 5.99, 200, 'http://example.com/teacup.jpg');

-- Insert test data into `Orders`
INSERT INTO Orders (UserID, TotalAmount, Status) VALUES
                                                     ((SELECT UserID FROM Users WHERE Username = 'Alice'), 59.94, 'Placed'),
                                                     ((SELECT UserID FROM Users WHERE Username = 'Bob'), 17.97, 'Paid');

-- Assuming the Orders have IDs 1 and 2, insert test data into `OrderDetails`
INSERT INTO OrderDetails (OrderID, ProductID, Quantity, UnitPrice, Subtotal) VALUES
                                                                                 (1, (SELECT ProductID FROM Products WHERE Name = 'Coffee Mug'), 2, 9.99, 2 * 9.99),
                                                                                 (1, (SELECT ProductID FROM Products WHERE Name = 'Tea Cup'), 4, 5.99, 4 * 5.99),
                                                                                 (2, (SELECT ProductID FROM Products WHERE Name = 'Tea Cup'), 3, 5.99, 3 * 5.99);

-- Insert test data into `Carts`
INSERT INTO Carts (UserID, ProductID, Quantity) VALUES
                                                    ((SELECT UserID FROM Users WHERE Username = 'Alice'), (SELECT ProductID FROM Products WHERE Name = 'Coffee Mug'), 1),
                                                    ((SELECT UserID FROM Users WHERE Username = 'Bob'), (SELECT ProductID FROM Products WHERE Name = 'Tea Cup'), 2);

-- Assuming the Orders have IDs 1 and 2, insert test data into `Payments`
INSERT INTO Payments (OrderID, Amount, Method, Status) VALUES
                                                           (1, 59.94, 'CreditCard', 'Pending'),
                                                           (2, 17.97, 'EWallet', 'Completed');
