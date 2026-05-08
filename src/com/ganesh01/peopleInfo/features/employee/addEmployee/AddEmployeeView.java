package com.ganesh01.peopleInfo.features.employee.addEmployee;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.util.ConsoleInput;
import java.util.Scanner;

public class AddEmployeeView {

  private AddEmployeePresenter presenter;
  private Scanner scanner;
  private Employee loggedInEmployee;

  public AddEmployeeView(Employee loggedInEmployee) {
    this.loggedInEmployee = loggedInEmployee;
    this.presenter = new AddEmployeePresenter(this);
    this.scanner = ConsoleInput.getScanner();
  }

  public void init() {
    System.out.println("\n--- Add New Employee ---");

    System.out.print("Enter Name: ");
    String name = scanner.nextLine().trim();

    System.out.print("Enter Email: ");
    String email = scanner.nextLine().trim();

    System.out.print("Enter Password: ");
    String password = scanner.nextLine().trim();

    System.out.print("Enter Mobile Number: ");
    String mobile = scanner.nextLine().trim();

    System.out.println("Select Role:");
    System.out.println("1. HR");
    System.out.println("2. EMPLOYEE");
    System.out.print("Choose (1/2): ");
    String roleChoice = scanner.nextLine().trim();
    Employee.Role role = "1".equals(roleChoice) ? Employee.Role.HR : Employee.Role.EMPLOYEE;

    System.out.print("Enter Department: ");
    String department = scanner.nextLine().trim();

    System.out.print("Enter Designation: ");
    String designation = scanner.nextLine().trim();

    presenter.onAddEmployee(name, email, password, mobile, role, department, designation);
  }

  public void showSuccess(String message) {
    System.out.println("Success " + message);
  }

  public void showError(String message) {
    System.out.println("Error " + message);
  }
}
