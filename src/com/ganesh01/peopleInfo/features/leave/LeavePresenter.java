package com.ganesh01.peopleInfo.features.leave;

import com.ganesh01.peopleInfo.data.dto.LeaveRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class LeavePresenter {

    private LeaveView view;
    private LeaveModel model;

    public LeavePresenter(LeaveView view) {
        this.view = view;
        this.model = new LeaveModel(this);
    }

    public void onApplyLeave(String employeeId, String leaveTypeStr, String startDateStr, String endDateStr,
            String reason) {
        if (leaveTypeStr == null || leaveTypeStr.isEmpty() || startDateStr == null || startDateStr.isEmpty()
                || endDateStr == null || endDateStr.isEmpty()) {
            view.showError("All fields are required.");
            return;
        }

        LeaveRequest.LeaveType type;
        try {
            type = LeaveRequest.LeaveType.valueOf(leaveTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            view.showError("Invalid leave type. Use SICK, CASUAL, or EARNED.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long start, end;
        try {
            start = sdf.parse(startDateStr).getTime();
            end = sdf.parse(endDateStr).getTime();
        } catch (ParseException e) {
            view.showError("Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        if (start > end) {
            view.showError("Start date cannot be after end date.");
            return;
        }

        LeaveRequest req = new LeaveRequest();
        req.setEmployeeId(employeeId);
        req.setLeaveType(type);
        req.setStartDate(start);
        req.setEndDate(end);
        req.setReason(reason);

        model.applyLeave(req);
    }

    public void onViewMyLeaves(String empId) {
        model.getMyLeaves(empId);
    }

    public void onViewAllLeaves() {
        model.getAllLeaves();
    }

    public void onUpdateLeaveStatus(Long leaveId, String statusStr, String approverId) {
        LeaveRequest.LeaveStatus status;
        try {
            status = LeaveRequest.LeaveStatus.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            view.showError("Invalid status. Use APPROVED, REJECTED, or CANCELLED.");
            return;
        }

        model.updateLeaveStatus(leaveId, status, approverId);
    }

    public void onLeaveApplied(LeaveRequest req) {
        view.showSuccess("Leave applied successfully. ID: " + req.getLeaveId());
    }

    public void onLeavesFetched(List<LeaveRequest> list) {
        view.displayLeaves(list);
    }

    public void onStatusUpdated() {
        view.showSuccess("Leave status updated successfully.");
    }

    public void onError(String error) {
        view.showError(error);
    }
}
