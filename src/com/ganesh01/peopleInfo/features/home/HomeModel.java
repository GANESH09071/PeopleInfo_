package com.ganesh01.peopleInfo.features.home;

import com.ganesh01.peopleInfo.data.dto.Employee;

public class HomeModel {

  private final HomePresenter presenter;

  public HomeModel(HomePresenter presenter) {
    this.presenter = presenter;
  }

  public void init(Employee employee) {

    if (employee == null || employee.getRole() == null) {
      presenter.onUnauthorized();
      return;
    }

    if (employee.getRole() == Employee.Role.HR) {
      presenter.onShowHrMenu();
    } else {
      presenter.onShowEmployeeMenu();
    }
  }
}
