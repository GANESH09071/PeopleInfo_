package com.ganesh01.peopleInfo.data.repositry;

import com.ganesh01.peopleInfo.util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreation {
  public static void initDB() {
    String createEmployeeTable =
        """
                CREATE TABLE IF NOT EXISTS employees (
                    employeeId VARCHAR(50) PRIMARY KEY,
                    name VARCHAR(100),
                    email VARCHAR(100) UNIQUE,
                    password VARCHAR(100),
                    mobileNo VARCHAR(20),
                    dob BIGINT,
                    role ENUM('HR','EMPLOYEE'),
                    department VARCHAR(100),
                    designation VARCHAR(100),
                    reportingTo BIGINT,
                    status ENUM('ACTIVE','INACTIVE'),
                    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                """;

    String createTimesheetTable = """
            CREATE TABLE IF NOT EXISTS timesheets (
                id INT AUTO_INCREMENT PRIMARY KEY,
                employeeId VARCHAR(50),
                date date,
                loginTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                logoutTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                totalHours DOUBLE,
                status VARCHAR(50),
                createdTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """;

    String createHiringRequirementTable = """
            CREATE TABLE IF NOT EXISTS hiring_requirements (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                jobTitle VARCHAR(100),
                department VARCHAR(100),
                requiredSkills VARCHAR(255),
                experience VARCHAR(50),
                vacancies INT,
                status VARCHAR(50),
                createdBy BIGINT,
                createdTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updatedTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """;

    String createLeaveRequestTable = """
            CREATE TABLE IF NOT EXISTS leave_requests (
                leaveId BIGINT AUTO_INCREMENT PRIMARY KEY,
                employeeId VARCHAR(50),
                leaveType VARCHAR(50),
                startDate BIGINT,
                endDate BIGINT,
                reason VARCHAR(255),
                status VARCHAR(50),
                approvedBy VARCHAR(50),
                createdTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updatedTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """;

    try (Connection conn = DBConnection.getConnection()) {
      if (conn != null) {
        try (Statement stmt = conn.createStatement()) {
          stmt.execute(createEmployeeTable);
          stmt.execute(createTimesheetTable);
          stmt.execute(createHiringRequirementTable);
          stmt.execute(createLeaveRequestTable);
          System.out.println("Database tables initialized successfully.");
        }
      }
    } catch (SQLException e) {
      System.out.println("Failed to initialize database: " + e.getMessage());
    }
  }
}
