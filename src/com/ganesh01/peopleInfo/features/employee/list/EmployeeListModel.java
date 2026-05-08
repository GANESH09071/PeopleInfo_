package com.ganesh01.peopleInfo.features.employee.list;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.repositry.PeopleInfoDB;
import java.util.List;

public class EmployeeListModel {

  private EmployeeListPresenter presenter;

  public EmployeeListModel(EmployeeListPresenter presenter) {
    this.presenter = presenter;
  }

  public void getAllEmployees() {
    List<Employee> list = PeopleInfoDB.getInstance().getAllEmployees();

    if (list.isEmpty()) {
      presenter.onMessage("No employees found!");
    } else {
      presenter.onEmployeesFetched(list);
    }
  }

  public void getEmployeesByRole(Employee.Role role) {
    List<Employee> list = PeopleInfoDB.getInstance().getEmployeesByRole(role);

    if (list.isEmpty()) {
      presenter.onMessage("No employees found for this role!");
    } else {
      presenter.onEmployeesFetched(list);
    }
  }
}
