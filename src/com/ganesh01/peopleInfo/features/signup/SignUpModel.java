package com.ganesh01.peopleInfo.features.signup;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.repositry.PeopleInfoDB;

public class SignUpModel {

  private SignUpPresenter presenter;

  public SignUpModel(SignUpPresenter presenter) {
    this.presenter = presenter;
  }

  public void register(String name, String email, String password, String roleInput) {

    if (PeopleInfoDB.getInstance().isEmailExists(email)) {
      presenter.onFailure("Email already registered!");
      return;
    }

    Employee.Role role;
    if (roleInput.equalsIgnoreCase("HR")) {
      role = Employee.Role.HR;
    } else {
      role = Employee.Role.EMPLOYEE;
    }

    Employee employee = new Employee();
    employee.setEmployeeId("EMP" + System.currentTimeMillis());
    employee.setName(name);
    employee.setEmail(email);
    employee.setPassword(password);
    employee.setRole(role);
    employee.setStatus(Employee.EmployeeStatus.ACTIVE);
    employee.setCreatedAt(System.currentTimeMillis());

    PeopleInfoDB.getInstance().addEmployee(employee);

    presenter.onSuccess("Registration successful! Please login.");
  }
}
