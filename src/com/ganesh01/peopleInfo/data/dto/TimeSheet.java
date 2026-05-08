package com.ganesh01.peopleInfo.data.dto;

public class TimeSheet {

  private String employeeId;
  private Long date;
  private Long loginTime;
  private Long logoutTime;
  private Double totalHours;
  private TimesheetStatus status;
  private Long createdTime;

  public enum TimesheetStatus {
    CHECKED_IN,
    CHECKED_OUT,
    MISSED
  }

  public TimeSheet() {}

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public Long getDate() {
    return date;
  }

  public void setDate(Long date) {
    this.date = date;
  }

  public Long getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Long loginTime) {
    this.loginTime = loginTime;
  }

  public Long getLogoutTime() {
    return logoutTime;
  }

  public void setLogoutTime(Long logoutTime) {
    this.logoutTime = logoutTime;
  }

  public Double getTotalHours() {
    return totalHours;
  }

  public void setTotalHours(Double totalHours) {
    this.totalHours = totalHours;
  }

  public TimesheetStatus getStatus() {
    return status;
  }

  public void setStatus(TimesheetStatus status) {
    this.status = status;
  }

  public Long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Long createdTime) {
    this.createdTime = createdTime;
  }
}
