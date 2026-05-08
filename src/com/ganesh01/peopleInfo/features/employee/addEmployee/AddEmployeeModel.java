package com.ganesh01.peopleInfo.features.employee.addEmployee;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.repositry.PeopleInfoDB;

public class AddEmployeeModel {

  private AddEmployeePresenter presenter;

  public AddEmployeeModel(AddEmployeePresenter presenter) {
    this.presenter = presenter;
  }

  public void saveEmployee(Employee employee) {
    Employee savedEmployee = PeopleInfoDB.getInstance().addEmployee(employee);
    if (savedEmployee != null) {
      presenter.onEmployeeAddedSuccess(savedEmployee);
    } else {
      presenter.onEmployeeAddedError("Failed to add employee. Email might already exist.");
    }
  }
}
