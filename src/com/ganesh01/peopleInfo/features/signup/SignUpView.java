package com.ganesh01.peopleInfo.features.signup;

import com.ganesh01.peopleInfo.util.ConsoleInput;
import java.util.Scanner;

public class SignUpView {

  private SignUpPresenter presenter;
  private final Scanner scanner;

  public SignUpView() {
    presenter = new SignUpPresenter(this);
    scanner = ConsoleInput.getScanner();
  }

  public void init() {
    System.out.println("\n===== SIGN UP =====");

    System.out.print("Enter Name: ");
    String name = scanner.nextLine();

    System.out.print("Enter Email: ");
    String email = scanner.nextLine();

    System.out.print("Enter Password: ");
    String password = scanner.nextLine();

    System.out.print("Enter Role (HR / Employee): ");
    String role = scanner.nextLine();

    presenter.register(name, email, password, role);
  }

  public void onSuccess(String message) {
    System.out.println("\n" + message);
  }

  public void onFailure(String message) {
    System.out.println("\nError: " + message);
  }
}
