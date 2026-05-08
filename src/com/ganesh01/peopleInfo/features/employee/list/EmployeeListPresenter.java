package com.ganesh01.peopleInfo.features.employee.list;

import com.ganesh01.peopleInfo.data.dto.Employee;
import java.util.List;

public class EmployeeListPresenter {

  private EmployeeListView view;
  private EmployeeListModel model;

  public EmployeeListPresenter(EmployeeListView view) {
    this.view = view;
    this.model = new EmployeeListModel(this);
  }

  public void onGetAllEmployees() {
    model.getAllEmployees();
  }

  public void onGetEmployeesByRole(Employee.Role role) {
    model.getEmployeesByRole(role);
  }

  public void onEmployeesFetched(List<Employee> list) {
    view.displayList(list);
  }

  public void onMessage(String msg) {
    view.showMessage(msg);
  }
}
