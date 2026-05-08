package com.ganesh01.peopleInfo.data.dto;

public class LeaveRequest {

  private Long LeaveId;
  private String employeeId;
  private LeaveType leaveType;
  private Long startDate;
  private Long endDate;
  private String reason;
  private LeaveStatus status;
  private String approvedBy;
  private Long createdTime;
  private Long updatedTime;

  public enum LeaveType {
    SICK,
    CASUAL,
    EARNED
  }

  public enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED,
    CANCELLED
  }

  public LeaveRequest() {}

  public Long getLeaveId() {
    return LeaveId;
  }

  public void setLeaveId(Long id) {
    this.LeaveId = id;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public LeaveType getLeaveType() {
    return leaveType;
  }

  public void setLeaveType(LeaveType leaveType) {
    this.leaveType = leaveType;
  }

  public Long getStartDate() {
    return startDate;
  }

  public void setStartDate(Long startDate) {
    this.startDate = startDate;
  }

  public Long getEndDate() {
    return endDate;
  }

  public void setEndDate(Long endDate) {
    this.endDate = endDate;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public LeaveStatus getStatus() {
    return status;
  }

  public void setStatus(LeaveStatus status) {
    this.status = status;
  }

  public String getApprovedBy() {
    return approvedBy;
  }

  public void setApprovedBy(String approvedBy) {
    this.approvedBy = approvedBy;
  }

  public Long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Long createdTime) {
    this.createdTime = createdTime;
  }

  public Long getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(Long updatedTime) {
    this.updatedTime = updatedTime;
  }
}
