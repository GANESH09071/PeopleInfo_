package com.ganesh01.peopleInfo.features.timesheet;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.dto.TimeSheet;
import com.ganesh01.peopleInfo.data.repositry.PeopleInfoDB;
import java.util.List;

public class TimesheetModel {

  private TimesheetPresenter presenter;

  public TimesheetModel(TimesheetPresenter presenter) {
    this.presenter = presenter;
  }

  public void checkIn(Employee user) {
    PeopleInfoDB.getInstance().checkIn(user.getEmployeeId());
    presenter.onMessage("Checked In!");
  }

  public void checkOut(Employee user) {
    PeopleInfoDB.getInstance().checkOut(user.getEmployeeId());
    presenter.onMessage("Checked Out!");
  }

  public void viewTimesheets(Employee user) {
    List<TimeSheet> list = PeopleInfoDB.getInstance().getTimeSheets(user.getEmployeeId());
    presenter.onTimesheetsFetched(list);
  }
}
