package com.ganesh01.peopleInfo.features.employee.list;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.util.ConsoleInput;
import java.util.List;
import java.util.Scanner;

public class EmployeeListView {

  private EmployeeListPresenter presenter;
  private final Scanner scanner;
  private Employee employee;

  public EmployeeListView(Employee employee) {
    this.employee = employee;
    this.presenter = new EmployeeListPresenter(this);
    scanner = ConsoleInput.getScanner();
  }

  public void init() {
    showMenu();
  }

  public void showMenu() {

    while (true) {
      System.out.println("\n===== EMPLOYEE LIST =====");
      System.out.println("1. View All Employees");

      if (employee.getRole() == Employee.Role.HR) {
        System.out.println("2. View Only HR");
        System.out.println("3. View Only Employees");
      }

      System.out.println("0. Back");
      System.out.print("Choose: ");

      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          presenter.onGetAllEmployees();
          break;

        case 2:
          if (employee.getRole() == Employee.Role.HR) {
            presenter.onGetEmployeesByRole(Employee.Role.HR);
          }
          break;

        case 3:
          if (employee.getRole() == Employee.Role.HR) {
            presenter.onGetEmployeesByRole(Employee.Role.EMPLOYEE);
          }
          break;

        case 0:
          return;

        default:
          System.out.println("Invalid choice!");
      }
    }
  }

  public void displayList(List<Employee> list) {

    System.out.println("\n----- EMPLOYEE LIST -----");

    for (Employee emp : list) {
      System.out.println(emp.getEmployeeId() + " | " + emp.getName() + " | " + emp.getRole());
    }
  }

  public void showMessage(String message) {
    System.out.println("\n" + message);
  }
}
