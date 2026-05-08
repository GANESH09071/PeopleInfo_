package com.ganesh01.peopleInfo.features.employee.addEmployee;

import com.ganesh01.peopleInfo.data.dto.Employee;

public class AddEmployeePresenter {

  private AddEmployeeView view;
  private AddEmployeeModel model;

  public AddEmployeePresenter(AddEmployeeView view) {
    this.view = view;
    this.model = new AddEmployeeModel(this);
  }

  public void onAddEmployee(
      String name,
      String email,
      String password,
      String mobileNo,
      Employee.Role role,
      String department,
      String designation) {

    if (name == null
        || name.isEmpty()
        || email == null
        || email.isEmpty()
        || password == null
        || password.isEmpty()) {
      view.showError("Name, Email, and Password are required fields.");
      return;
    }

    Employee newEmployee = new Employee();
    newEmployee.setName(name);
    newEmployee.setEmail(email);
    newEmployee.setPassword(password);
    newEmployee.setMobileNo(mobileNo);
    newEmployee.setRole(role);
    newEmployee.setDepartment(department);
    newEmployee.setDesignation(designation);

    model.saveEmployee(newEmployee);
  }

  public void onEmployeeAddedSuccess(Employee employee) {
    view.showSuccess(
        "Employee "
            + employee.getName()
            + " added successfully with ID: "
            + employee.getEmployeeId());
  }

  public void onEmployeeAddedError(String error) {
    view.showError(error);
  }
}
