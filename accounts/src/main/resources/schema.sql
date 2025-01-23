CREATE TABLE IF NOT EXISTS `Customer` (
  `customerId` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobileNo` varchar(20) NOT NULL,
  `createdAt` date NOT NULL,
  `createdBy` varchar(20) NOT NULL,
  `updatedAt` date DEFAULT NULL,
    `updatedBy` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `Accounts` (
  `customerId` int NOT NULL,
   `accountNo` int AUTO_INCREMENT  PRIMARY KEY,
  `accountType` varchar(100) NOT NULL,
  `branchAddress` varchar(200) NOT NULL,
  `createdAt` date NOT NULL,
   `createdBy` varchar(20) NOT NULL,
   `updatedAt` date DEFAULT NULL,
    `updatedBy` varchar(20) DEFAULT NULL
);