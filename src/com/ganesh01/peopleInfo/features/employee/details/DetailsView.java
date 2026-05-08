package com.ganesh01.peopleInfo.features.employee.details;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.util.ConsoleInput;
import java.util.List;
import java.util.Scanner;

public class DetailsView {

  private DetailsPresenter presenter;
  private final Scanner scanner;
  private Employee employee;

  public DetailsView(Employee employee) {
    this.employee = employee;
    this.presenter = new DetailsPresenter(this);
    scanner = ConsoleInput.getScanner();
  }

  public void init() {
    showMenu();
  }

  public void showMenu() {

    while (true) {
      System.out.println("\n===== DETAILS MENU =====");
      System.out.println("1. View My Profile");

      if (employee.getRole() == Employee.Role.HR) {
        System.out.println("2. View Employee by ID");
        System.out.println("3. View All Employees");
      }

      System.out.println("0. Back");
      System.out.print("Choose: ");

      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          presenter.onGetMyProfile(employee);
          break;
        case 2:
          if (employee.getRole() == Employee.Role.HR) {
            System.out.print("Enter Employee ID: ");
            String id = scanner.nextLine();
            presenter.onGetEmployeeById(id);
          }
          break;

        case 3:
          if (employee.getRole() == Employee.Role.HR) {
            presenter.onGetAllEmployees();
          }
          break;

        case 0:
          return;

        default:
          System.out.println("Invalid choice!");
      }
    }
  }

  public void displayEmployee(Employee emp) {
    System.out.println("\n----- EMPLOYEE DETAILS -----");
    System.out.println("Emp ID: " + emp.getEmployeeId());
    System.out.println("Name: " + emp.getName());
    System.out.println("Email: " + emp.getEmail());
    System.out.println("Role: " + emp.getRole());
    System.out.println("Department: " + emp.getDepartment());
    System.out.println("Designation: " + emp.getDesignation());
    System.out.println("Status: " + emp.getStatus());
  }

  public void displayEmployeeList(List<Employee> list) {
    System.out.println("\n----- ALL EMPLOYEES -----");

    for (Employee emp : list) {
      System.out.println(emp.getEmployeeId() + " - " + emp.getName() + " (" + emp.getRole() + ")");
    }
  }

  public void showError(String message) {
    System.out.println("\nError: " + message);
  }
}
