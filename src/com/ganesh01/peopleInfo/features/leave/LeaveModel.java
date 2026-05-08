package com.ganesh01.peopleInfo.features.leave;

import com.ganesh01.peopleInfo.data.dto.LeaveRequest;
import com.ganesh01.peopleInfo.data.repositry.PeopleInfoDB;
import java.util.List;

public class LeaveModel {

  private LeavePresenter presenter;

  public LeaveModel(LeavePresenter presenter) {
    this.presenter = presenter;
  }

  public void applyLeave(LeaveRequest req) {
    LeaveRequest created = PeopleInfoDB.getInstance().applyLeave(req);
    if (created != null) {
      presenter.onLeaveApplied(created);
    } else {
      presenter.onError("Failed to apply for leave.");
    }
  }

  public void getMyLeaves(String empId) {
    List<LeaveRequest> list = PeopleInfoDB.getInstance().getLeavesByEmployee(empId);
    if (list.isEmpty()) {
      presenter.onError("No leave requests found.");
    } else {
      presenter.onLeavesFetched(list);
    }
  }

  public void getAllLeaves() {
    List<LeaveRequest> list = PeopleInfoDB.getInstance().getAllLeaves();
    if (list.isEmpty()) {
      presenter.onError("No leave requests found.");
    } else {
      presenter.onLeavesFetched(list);
    }
  }

  public void updateLeaveStatus(Long leaveId, LeaveRequest.LeaveStatus status, String approverId) {
    boolean success = PeopleInfoDB.getInstance().updateLeaveStatus(leaveId, status, approverId);
    if (success) {
      presenter.onStatusUpdated();
    } else {
      presenter.onError("Failed to update status. Invalid ID.");
    }
  }
}
