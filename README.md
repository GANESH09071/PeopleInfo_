# PeopleInfo

PeopleInfo is a Java-based Human Resources and Employee Management console application built with the Model-View-Presenter (MVP) architecture and backed by a MySQL database using JDBC.

## Features

- **Role-Based Access Control:** Distinct functionalities for `HR` and `EMPLOYEE` roles.
- **Employee Management:** 
    - Add new employees with detailed profiles.
    - List all employees and view specific employee details.
- **Leave Management:**
    - Employees can apply for SICK, CASUAL, or EARNED leaves.
    - HR Managers can view all leave requests and Approve or Reject them.
- **Timesheet Tracking:**
    - Check-in and Check-out system to track daily working hours.
    - Calculates total hours worked per session.
- **Hiring Requirements:**
    - HR can post new job requirements and track vacancies.
    - Update hiring statuses (OPEN, CLOSED, ON_HOLD).

## Architecture

This project strictly adheres to the **Model-View-Presenter (MVP)** design pattern to cleanly separate the user interface (Console Views), business logic (Presenters), and data access (Models and Repository).

- **Models:** Handle core business logic and state.
- **Views:** Handle console input/output using standard Java `Scanner`.
- **Presenters:** Act as the middleman to route data between Views and Models.
- **Repository (`PeopleInfoDB`):** Centralized Data Access Object (DAO) that uses JDBC to communicate with the MySQL database.

## Technologies Used

- **Java 21**
- **MySQL 8.0+**
- **JDBC** (mysql-connector-j)

## Setup & Installation

1.  Clone the repository.
2.  Ensure you have a local MySQL server running.
3.  Update the database credentials in `src/com/ganesh01/peopleInfo/util/DBConnection.java`.
4.  Compile the Java files ensuring the `mysql-connector-j-9.7.0.jar` is in your classpath.
5.  Run `Main.java`. The application will automatically create all necessary database tables (`employees`, `timesheets`, `leave_requests`, `hiring_requirements`) on the first run.
