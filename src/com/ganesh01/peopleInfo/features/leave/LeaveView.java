package com.ganesh01.peopleInfo.features.leave;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.dto.LeaveRequest;
import com.ganesh01.peopleInfo.util.ConsoleInput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LeaveView {

  private LeavePresenter presenter;
  private Employee employee;
  private final Scanner scanner;

  public LeaveView(Employee employee) {
    this.employee = employee;
    this.presenter = new LeavePresenter(this);
    this.scanner = ConsoleInput.getScanner();
  }

  public void init() {
    showMenu();
  }

  public void showMenu() {
    while (true) {
      System.out.println("\n===== LEAVE MANAGEMENT =====");
      System.out.println("1. Apply for Leave");
      System.out.println("2. View My Leave Requests");

      if (employee.getRole() == Employee.Role.HR) {
        System.out.println("3. View All Leave Requests");
        System.out.println("4. Approve/Reject Leave Requests");
      }

      System.out.println("0. Back");
      System.out.print("Choose: ");

      int choice = scanner.nextInt();
      scanner.nextLine(); // consume newline

      switch (choice) {
        case 1:
          applyForLeave();
          break;
        case 2:
          presenter.onViewMyLeaves(employee.getEmployeeId());
          break;
        case 3:
          if (employee.getRole() == Employee.Role.HR) {
            presenter.onViewAllLeaves();
          } else {
            System.out.println("Invalid choice!");
          }
          break;
        case 4:
          if (employee.getRole() == Employee.Role.HR) {
            updateLeaveStatus();
          } else {
            System.out.println("Invalid choice!");
          }
          break;
        case 0:
          return;
        default:
          System.out.println("Invalid choice!");
      }
    }
  }

  private void applyForLeave() {
    System.out.println("\n--- Apply for Leave ---");
    System.out.print("Enter Leave Type (SICK, CASUAL, EARNED): ");
    String type = scanner.nextLine().trim();

    System.out.print("Enter Start Date (YYYY-MM-DD): ");
    String startDate = scanner.nextLine().trim();

    System.out.print("Enter End Date (YYYY-MM-DD): ");
    String endDate = scanner.nextLine().trim();

    System.out.print("Enter Reason: ");
    String reason = scanner.nextLine().trim();

    presenter.onApplyLeave(employee.getEmployeeId(), type, startDate, endDate, reason);
  }

  private void updateLeaveStatus() {
    System.out.println("\n--- Update Leave Status ---");
    System.out.print("Enter Leave ID: ");
    long leaveId;
    try {
      leaveId = Long.parseLong(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Invalid Leave ID.");
      return;
    }

    System.out.print("Enter Status (APPROVED, REJECTED, CANCELLED): ");
    String status = scanner.nextLine().trim();

    presenter.onUpdateLeaveStatus(leaveId, status, employee.getEmployeeId());
  }

  public void displayLeaves(List<LeaveRequest> list) {
    System.out.println("\n--- Leave Requests ---");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    for (LeaveRequest req : list) {
      String start = sdf.format(new Date(req.getStartDate()));
      String end = sdf.format(new Date(req.getEndDate()));

      System.out.println(
          "ID: "
              + req.getLeaveId()
              + " | Emp: "
              + req.getEmployeeId()
              + " | Type: "
              + req.getLeaveType()
              + " | Date: "
              + start
              + " to "
              + end
              + " | Status: "
              + req.getStatus());
    }
  }

  public void showSuccess(String message) {
    System.out.println("\nSuccess: " + message);
  }

  public void showError(String message) {
    System.out.println("\nError: " + message);
  }
}
