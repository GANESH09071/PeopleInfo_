package com.ganesh01.peopleInfo.features.home;

import com.ganesh01.peopleInfo.data.dto.Employee;

public class HomePresenter {

  private final HomeView view;
  private final HomeModel model;

  public HomePresenter(HomeView view) {
    this.view = view;
    this.model = new HomeModel(this);
  }

  public void init(Employee employee) {
    model.init(employee);
  }

  public void onUnauthorized() {
    view.showUnauthorized();
  }

  public void onShowHrMenu() {
    view.showHrMenu();
  }

  public void onShowEmployeeMenu() {
    view.showEmployeeMenu();
  }
}
