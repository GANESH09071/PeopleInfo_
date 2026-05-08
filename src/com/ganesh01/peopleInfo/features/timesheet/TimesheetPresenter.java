package com.ganesh01.peopleInfo.features.timesheet;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.dto.TimeSheet;
import java.util.List;

public class TimesheetPresenter {

  private TimesheetView view;
  private TimesheetModel model;

  public TimesheetPresenter(TimesheetView view) {
    this.view = view;
    this.model = new TimesheetModel(this);
  }

  public void onCheckIn(Employee user) {
    model.checkIn(user);
  }

  public void onCheckOut(Employee user) {
    model.checkOut(user);
  }

  public void onViewTimesheets(Employee user) {
    model.viewTimesheets(user);
  }

  public void onTimesheetsFetched(List<TimeSheet> list) {
    view.display(list);
  }

  public void onMessage(String msg) {
    view.showMessage(msg);
  }
}
