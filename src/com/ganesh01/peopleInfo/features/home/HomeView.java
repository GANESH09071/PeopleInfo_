package com.ganesh01.peopleInfo.features.home;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.features.employee.addEmployee.AddEmployeeView;
import com.ganesh01.peopleInfo.features.employee.details.DetailsView;
import com.ganesh01.peopleInfo.features.employee.list.EmployeeListView;
import com.ganesh01.peopleInfo.features.hiringrequriments.RequirementView;
import com.ganesh01.peopleInfo.features.leave.LeaveView;
import com.ganesh01.peopleInfo.features.timesheet.TimesheetView;
import com.ganesh01.peopleInfo.util.ConsoleInput;
import java.util.Scanner;

public class HomeView {

  private final HomePresenter presenter;
  private final Employee employee;
  private final Scanner scanner;

  public HomeView(Employee employee) {
    this.employee = employee;
    this.presenter = new HomePresenter(this);
    this.scanner = ConsoleInput.getScanner();
  }

  public void init() {
    presenter.init(employee);
  }

  public void showUnauthorized() {
    System.out.println("Unauthorized access!");
  }

  public void showHrMenu() {

    while (true) {
      System.out.println("\n===== Manager Home =====");
      System.out.println("1. List all employees");
      System.out.println("2. Details of employees");
      System.out.println("3. Add Employee");
      System.out.println("4. Hiring Requirement");
      System.out.println("5. Timesheet");
      System.out.println("6. Leave Management");
      System.out.println("7. Sign out");

      System.out.print("Choose: ");
      String choice = scanner.nextLine();

      switch (choice) {
        case "1":
          new EmployeeListView(employee).init();
          break;

        case "2":
          new DetailsView(employee).init();
          break;

        case "3":
          new AddEmployeeView(employee).init();
          break;

        case "4":
          new RequirementView(employee).init();
          break;

        case "5":
          new TimesheetView(employee).init();
          break;

        case "6":
          new LeaveView(employee).init();
          break;

        case "7":
          System.out.println("Signed out!");
          return;

        default:
          System.out.println("Invalid choice!");
      }
    }
  }

  public void showEmployeeMenu() {

    while (true) {
      System.out.println("\n===== Employee Home =====");
      System.out.println("1. List Employees");
      System.out.println("2. Apply Leave");
      System.out.println("3. Timesheet");
      System.out.println("4. Sign out");

      System.out.print("Choose: ");
      String choice = scanner.nextLine();

      switch (choice) {
        case "1":
          new EmployeeListView(employee).init();
          break;

        case "2":
          new LeaveView(employee).init();
          break;

        case "3":
          new TimesheetView(employee).init();
          break;

        case "4":
          System.out.println("Signed out!");
          return;

        default:
          System.out.println("Invalid choice!");
      }
    }
  }
}
