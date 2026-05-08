package com.ganesh01.peopleInfo.features.employee.details;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.repositry.PeopleInfoDB;
import java.util.List;

public class DetailsModel {

  private DetailsPresenter presenter;

  public DetailsModel(DetailsPresenter presenter) {
    this.presenter = presenter;
  }

  public void getMyProfile(Employee loggedInUser) {
    presenter.onEmployeeFetched(loggedInUser);
  }

  public void getEmployeeById(String id) {
    Employee emp = PeopleInfoDB.getInstance().getEmployeeById(id);

    if (emp == null) {
      presenter.onError("Employee not found!");
    } else {
      presenter.onEmployeeFetched(emp);
    }
  }

  public void getAllEmployees() {
    List<Employee> list = PeopleInfoDB.getInstance().getAllEmployees();
    presenter.onEmployeeListFetched(list);
  }
}
