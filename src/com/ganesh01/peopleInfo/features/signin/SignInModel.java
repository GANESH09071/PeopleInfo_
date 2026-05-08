package com.ganesh01.peopleInfo.features.signin;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.repositry.PeopleInfoDB;

public class SignInModel {

  private SignInPresenter presenter;

  public SignInModel(SignInPresenter presenter) {
    this.presenter = presenter;
  }

  public void validateLogin(String email, String password) {

    Employee employee = PeopleInfoDB.getInstance().validateUser(email, password);

    if (employee == null) {
      presenter.onLoginFailed("Invalid credentials or inactive user!");
    } else {
      presenter.onLoginSuccess(employee);
    }
  }
}
