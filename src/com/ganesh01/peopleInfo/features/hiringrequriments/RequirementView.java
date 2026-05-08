package com.ganesh01.peopleInfo.features.hiringrequriments;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.dto.HiringRequirement;
import com.ganesh01.peopleInfo.util.ConsoleInput;
import java.util.List;
import java.util.Scanner;

public class RequirementView {

  private RequirementPresenter presenter;
  private Employee employee;
  private Scanner scanner;

  public RequirementView(Employee employee) {
    this.employee = employee;
    this.scanner = ConsoleInput.getScanner();
    this.presenter = new RequirementPresenter(this);
  }

  public void init() {
    showMenu();
  }

  public void showMenu() {
    while (true) {
      System.out.println("\n--- Hiring Requirements Menu ---");
      System.out.println("1. Add Hiring Requirement");
      System.out.println("2. View All Requirements");
      System.out.println("3. Update Requirement Status");
      System.out.println("4. Back");
      System.out.print("Choose an option: ");
      String choice = scanner.nextLine().trim();

      switch (choice) {
        case "1":
          addRequirement();
          break;
        case "2":
          presenter.onViewRequirements();
          break;
        case "3":
          updateStatus();
          break;
        case "4":
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void addRequirement() {
    System.out.print("Enter Job Title: ");
    String jobTitle = scanner.nextLine().trim();
    System.out.print("Enter Department: ");
    String department = scanner.nextLine().trim();
    System.out.print("Enter Required Skills: ");
    String skills = scanner.nextLine().trim();
    System.out.print("Enter Experience: ");
    String experience = scanner.nextLine().trim();

    System.out.print("Enter Number of Vacancies: ");
    int vacancies;
    try {
      vacancies = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Invalid number for vacancies.");
      return;
    }

    long createdBy = -1;
    try {
      if (employee.getEmployeeId() != null && employee.getEmployeeId().startsWith("EMP")) {
        createdBy = Long.parseLong(employee.getEmployeeId().substring(3));
      } else {
        createdBy = Long.parseLong(employee.getEmployeeId());
      }
    } catch (Exception e) {

    }

    presenter.onAddRequirement(jobTitle, department, skills, experience, vacancies, createdBy);
  }

  private void updateStatus() {
    System.out.print("Enter Requirement ID: ");
    long id;
    try {
      id = Long.parseLong(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Invalid Requirement ID.");
      return;
    }

    System.out.println("Select New Status:");
    System.out.println("1. OPEN");
    System.out.println("2. CLOSED");
    System.out.println("3. ON_HOLD");
    System.out.print("Choose status: ");
    String choice = scanner.nextLine().trim();

    HiringRequirement.HiringStatus status = null;
    switch (choice) {
      case "1":
        status = HiringRequirement.HiringStatus.OPEN;
        break;
      case "2":
        status = HiringRequirement.HiringStatus.CLOSED;
        break;
      case "3":
        status = HiringRequirement.HiringStatus.ON_HOLD;
        break;
      default:
        System.out.println("Invalid status choice.");
        return;
    }

    presenter.onUpdateStatus(id, status);
  }

  public void displayRequirements(List<HiringRequirement> list) {
    System.out.println("\n--- Hiring Requirements List ---");
    for (HiringRequirement req : list) {
      System.out.println(
          String.format(
              "ID: %d | Title: %s | Dept: %s | Status: %s | Vacancies: %d",
              req.getId(),
              req.getJobTitle(),
              req.getDepartment(),
              req.getStatus(),
              req.getVacancies()));
    }
  }

  public void showSuccess(String message) {
    System.out.println("Success " + message);
  }

  public void showError(String message) {
    System.out.println("Error " + message);
  }
}
