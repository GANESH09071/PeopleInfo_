package com.ganesh01.peopleInfo.features.signin;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.util.ConsoleInput;
import java.util.Scanner;

public class SignInView {

  private SignInPresenter signInPresenter;
  private final Scanner scanner;

  public SignInView() {
    signInPresenter = new SignInPresenter(this);
    scanner = ConsoleInput.getScanner();
  }

  public void init() {
    System.out.println("\n===== LOGIN =====");

    System.out.print("Enter Email: ");
    String email = scanner.nextLine();

    System.out.print("Enter Password: ");
    String password = scanner.nextLine();

    signInPresenter.validateLogin(email, password);
  }

  public void loginSuccess(Employee employee) {
    System.out.println("\nLogin Successful!");
    System.out.println("Welcome " + employee.getName());

    if (employee.getRole() == Employee.Role.HR) {
      System.out.println("Logged in as HR");
    } else {
      System.out.println("Logged in as Employee");
    }

    new com.ganesh01.peopleInfo.features.home.HomeView(employee).init();
  }

  public void loginFailed(String message) {
    System.out.println("\nLogin Failed: " + message);
  }
}
