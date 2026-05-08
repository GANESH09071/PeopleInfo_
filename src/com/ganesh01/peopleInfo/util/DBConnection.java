package com.ganesh01.peopleInfo.util;

import com.ganesh01.peopleInfo.data.repositry.DBCreation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  private static final String URL = "jdbc:mysql://localhost:3306/PeopleInfo";
  private static final String USER = "root";
  private static final String PASSWORD = "^j61%U6g1234";

  static {
    DBCreation.initDB();
  }

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
      System.out.println("Connection Failed: " + e.getMessage());
      return null;
    }
  }

  public static void main(String[] args) {
    Connection conn = getConnection();
    if (conn != null) {
      System.out.println("Connection Successful!");
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("Connection Failed!");
    }
  }
}
