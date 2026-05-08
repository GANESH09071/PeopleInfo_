package com.ganesh01.peopleInfo.features.timesheet;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.dto.TimeSheet;
import com.ganesh01.peopleInfo.util.ConsoleInput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TimesheetView {

  private TimesheetPresenter presenter;
  private final Scanner scanner;
  private Employee employee;

  public TimesheetView(Employee employee) {
    this.employee = employee;
    this.presenter = new TimesheetPresenter(this);
    scanner = ConsoleInput.getScanner();
  }

  public void init() {
    showMenu();
  }

  public void showMenu() {

    while (true) {
      System.out.println("\n===== TIMESHEET =====");
      System.out.println("1. Check In");
      System.out.println("2. Check Out");
      System.out.println("3. View My Timesheets");
      System.out.println("0. Back");

      System.out.print("Choose: ");
      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          presenter.onCheckIn(employee);
          break;

        case 2:
          presenter.onCheckOut(employee);
          break;

        case 3:
          presenter.onViewTimesheets(employee);
          break;

        case 0:
          return;

        default:
          System.out.println("Invalid choice");
      }
    }
  }

  public void display(List<TimeSheet> list) {

    System.out.println("\n--- TIMESHEETS ---");

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

    for (TimeSheet t : list) {

      String loginTime = sdf.format(new Date(t.getLoginTime()));

      String logoutTime =
          (t.getLogoutTime() == null) ? "Still Working" : sdf.format(new Date(t.getLogoutTime()));

      String workedTime;

      if (t.getTotalHours() == null) {
        workedTime = "-";
      } else {
        int minutes = (int) (t.getTotalHours() * 60);
        workedTime = minutes + " mins";
      }

      System.out.println(
          "Login: " + loginTime + " | Logout: " + logoutTime + " | Worked: " + workedTime);
    }
  }

  public void showMessage(String msg) {
    System.out.println(msg);
  }
}
