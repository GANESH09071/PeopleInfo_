package com.ganesh01.peopleInfo.features.employee.details;

import com.ganesh01.peopleInfo.data.dto.Employee;
import java.util.List;

public class DetailsPresenter {

  private DetailsView view;
  private DetailsModel model;

  public DetailsPresenter(DetailsView view) {
    this.view = view;
    this.model = new DetailsModel(this);
  }

  public void onGetMyProfile(Employee loggedInUser) {
    model.getMyProfile(loggedInUser);
  }

  public void onGetEmployeeById(String id) {
    if (id == null || id.isEmpty()) {
      view.showError("Employee ID cannot be empty!");
      return;
    }
    model.getEmployeeById(id);
  }

  public void onGetAllEmployees() {
    model.getAllEmployees();
  }

  public void onEmployeeFetched(Employee emp) {
    view.displayEmployee(emp);
  }

  public void onEmployeeListFetched(List<Employee> list) {
    view.displayEmployeeList(list);
  }

  public void onError(String message) {
    view.showError(message);
  }
}
