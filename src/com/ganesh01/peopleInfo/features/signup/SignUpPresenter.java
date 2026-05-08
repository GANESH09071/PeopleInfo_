package com.ganesh01.peopleInfo.features.signup;

public class SignUpPresenter {

  private SignUpView view;
  private SignUpModel model;

  public SignUpPresenter(SignUpView view) {
    this.view = view;
    this.model = new SignUpModel(this);
  }

  public void register(String name, String email, String password, String role) {
    if (name == null
        || name.isEmpty()
        || email == null
        || email.isEmpty()
        || password == null
        || password.isEmpty()) {
      view.onFailure("All fields are required!");
      return;
    }

    String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    if (!email.matches(emailRegex)) {
      view.onFailure("Invalid email format!");
      return;
    }

    String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_!])(?=\\S+$).{8,}$";
    if (!password.matches(passwordRegex)) {
      view.onFailure(
          "Password must be at least 8 chars long, contain uppercase, lowercase, a digit, and a"
              + " special character!");
      return;
    }

    model.register(name, email, password, role);
  }

  public void onSuccess(String message) {
    view.onSuccess(message);
  }

  public void onFailure(String message) {
    view.onFailure(message);
  }
}
