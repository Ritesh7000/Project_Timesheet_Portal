-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 29, 2023 at 05:05 AM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `testdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `add_task`
--

DROP TABLE IF EXISTS `add_task`;
CREATE TABLE IF NOT EXISTS `add_task` (
  `user_name` varchar(30) DEFAULT NULL,
  `selected_date` varchar(30) NOT NULL,
  `task_name` varchar(500) NOT NULL,
  `task_description` varchar(3000) NOT NULL,
  `project_manager` varchar(500) NOT NULL,
  `project_name` varchar(2000) NOT NULL,
  `time_spent` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `add_task`
--

INSERT INTO `add_task` (`user_name`, `selected_date`, `task_name`, `task_description`, `project_manager`, `project_name`, `time_spent`) VALUES
('mohit', '02-02-2021', 'Meeting for start of project ', 'Arranged meeting for discussing what is needed in project', 'Mohit Shetty', 'Music System', '5:20'),
('mohit', '02-02-2021', 'Discussion ', 'With employees discussing basic of project', 'Mohit Shetty', 'Music System', '3:40'),
('mohit', '03-02-2021', 'Starting work', 'templates required for project organized', 'Mohit Shetty', 'Music System', '8:20'),
('sonam', '02-02-2021', 'photos added to front page', 'the pics which attracts user to the site are added to the frontpage of site', 'Poonam Gupta', 'Meal Manager', '7:50'),
('sonam', '03-02-2021', 'layouts of screen', 'workig with layout required for screen', 'Poonam Gupta', 'Meal Manager', '8:10');

-- --------------------------------------------------------

--
-- Table structure for table `login_data`
--

DROP TABLE IF EXISTS `login_data`;
CREATE TABLE IF NOT EXISTS `login_data` (
  `username` varchar(30) NOT NULL,
  `u_password` varchar(30) NOT NULL,
  `u_confirmpassword` varchar(30) NOT NULL,
  `role_` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_data`
--

INSERT INTO `login_data` (`username`, `u_password`, `u_confirmpassword`, `role_`) VALUES
('sonam', 'sonam123', 'sonam123', 'Employee'),
('mohit', 'mohit999', 'mohit999', 'Manager'),
('shikha', 'shikha12', 'shikha12', 'Manager'),
('poonam', 'poonam12', 'poonam12', 'Manager'),
('vinod', 'vinod888', 'vinod888', 'Manager'),
('sonam_', '90909090', '90909090', 'Employee');

-- --------------------------------------------------------

--
-- Table structure for table `personal_deatils`
--

DROP TABLE IF EXISTS `personal_deatils`;
CREATE TABLE IF NOT EXISTS `personal_deatils` (
  `user_name` varchar(30) DEFAULT NULL,
  `title` varchar(20) NOT NULL,
  `employee_name` varchar(500) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(1000) NOT NULL,
  `country` varchar(100) NOT NULL,
  `phone_no` varchar(20) NOT NULL,
  `office_id_no` varchar(30) NOT NULL,
  `bank_acc_no` varchar(50) NOT NULL,
  `dob` varchar(100) NOT NULL,
  `place_of_birth` varchar(200) NOT NULL,
  `natioanlity` varchar(200) NOT NULL,
  `marital_status` varchar(30) NOT NULL,
  `personal_id_no` varchar(30) NOT NULL,
  `ssn` varchar(30) NOT NULL,
  PRIMARY KEY (`ssn`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personal_deatils`
--

INSERT INTO `personal_deatils` (`user_name`, `title`, `employee_name`, `email`, `address`, `country`, `phone_no`, `office_id_no`, `bank_acc_no`, `dob`, `place_of_birth`, `natioanlity`, `marital_status`, `personal_id_no`, `ssn`) VALUES
('sonam', 'Miss', 'Sonam Gupta', 'sonam@gmail.com', 'Kanjurmarg(E).', 'India', '8399203843', '323789393', '92390093284332', '12/09/1991', 'Pune', 'Indian', 'Unmarried', '893297263842', '982938493'),
('poonam', 'Mrs.', 'Poonam Gupta', 'poonam12@gmail.com', 'Malad(W).', 'India', '7882829364', '893292034', '78439020994309', '13/03//1989', 'Patna', 'Indian', 'Married', '782372893478', '778298392'),
('mohit', 'Mr.', 'Mohit Shetty', 'mohit@gmail.com', 'Dadar(E).', 'India', '9029349505', '894399302', '8934902090343', '24/03/1980', 'Kashmir', 'Indian', 'Married', '892020349502', '293939494');

-- --------------------------------------------------------

--
-- Table structure for table `project_data`
--

DROP TABLE IF EXISTS `project_data`;
CREATE TABLE IF NOT EXISTS `project_data` (
  `user_name` varchar(30) DEFAULT NULL,
  `project_manager` varchar(500) NOT NULL,
  `project_name` varchar(2000) NOT NULL,
  `project_id` varchar(30) NOT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_data`
--

INSERT INTO `project_data` (`user_name`, `project_manager`, `project_name`, `project_id`) VALUES
('poonam', 'Poonam Gupta', 'Timesheet Portal', 'P8723'),
('poonam', 'Poonam Gupta', 'Railway management System', 'P3287'),
('poonam', 'Poonam Gupta', 'Airline management System', 'P6732'),
('poonam', 'Poonam Gupta', 'Task Manager', 'P8921'),
('poonam', 'Poonam Gupta', 'Meal Manager', 'P6095'),
('mohit', 'Mohit Shetty', 'Music System', 'P9832'),
('mohit', 'Mohit Shetty', 'Roadway Management System', 'P9009'),
('mohit', 'Mohit Shetty', 'Newspaper Reader', 'P9003');

-- --------------------------------------------------------

--
-- Table structure for table `project_details`
--

DROP TABLE IF EXISTS `project_details`;
CREATE TABLE IF NOT EXISTS `project_details` (
  `user_name` varchar(30) DEFAULT NULL,
  `project_name` varchar(2000) NOT NULL,
  `project_id` varchar(30) NOT NULL,
  `project_description` varchar(3000) NOT NULL,
  `customer_name` varchar(1000) NOT NULL,
  `project_status` varchar(30) NOT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_details`
--

INSERT INTO `project_details` (`user_name`, `project_name`, `project_id`, `project_description`, `customer_name`, `project_status`) VALUES
('poonam', 'Timesheet Portal', 'P8723', 'To keep track of work done by employee and manager everyday.', 'Aledex Costa', 'In Progress'),
('mohit', 'Music System', 'P9832', 'The way music syatem works we have to design new android app for this which is of type responsive.', 'Alexa Tem', 'In Progress'),
('poonam', 'Meal Manager', 'P6095', 'Stores data of all food that ne person eats in a day and gives feedback as per that data.', 'Suhana Main', 'In Progress'),
('mohit', 'Traffic Management System', 'P9533', 'To store all data regarding traffic system. ', 'Diogo Saif', 'In Progress');

-- --------------------------------------------------------

--
-- Table structure for table `signup_data`
--

DROP TABLE IF EXISTS `signup_data`;
CREATE TABLE IF NOT EXISTS `signup_data` (
  `s_username` varchar(30) NOT NULL,
  `s_password` varchar(30) NOT NULL,
  `s_confirmpassword` varchar(30) NOT NULL,
  `role_` varchar(20) NOT NULL,
  PRIMARY KEY (`s_username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `signup_data`
--

INSERT INTO `signup_data` (`s_username`, `s_password`, `s_confirmpassword`, `role_`) VALUES
('sonam', 'sonam123', 'sonam123', 'Employee'),
('mohit', 'mohit999', 'mohit999', 'Manager'),
('shikha', 'shikha12', 'shikha12', 'Manager'),
('poonam', 'poonam12', 'poonam12', 'Manager'),
('vinod', 'vinod888', 'vinod888', 'Manager'),
('sonam_', '90909090', '90909090', 'Employee');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
