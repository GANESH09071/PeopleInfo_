package com.ganesh01.peopleInfo.features.signin;

import com.ganesh01.peopleInfo.data.dto.Employee;

public class SignInPresenter {

  private SignInView view;
  private SignInModel model;

  public SignInPresenter(SignInView view) {
    this.view = view;
    this.model = new SignInModel(this);
  }

  public void validateLogin(String email, String password) {
    if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
      view.loginFailed("Email and password cannot be empty!");
      return;
    }
    model.validateLogin(email, password);
  }

  public void onLoginSuccess(Employee employee) {
    view.loginSuccess(employee);
  }

  public void onLoginFailed(String message) {
    view.loginFailed(message);
  }
}
