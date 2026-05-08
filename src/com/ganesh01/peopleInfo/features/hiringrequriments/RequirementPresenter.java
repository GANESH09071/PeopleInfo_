package com.ganesh01.peopleInfo.features.hiringrequriments;

import com.ganesh01.peopleInfo.data.dto.HiringRequirement;
import java.util.List;

public class RequirementPresenter {

  private RequirementView view;
  private RequirementModel model;

  public RequirementPresenter(RequirementView view) {
    this.view = view;
    this.model = new RequirementModel(this);
  }

  public void onAddRequirement(
      String jobTitle,
      String department,
      String requiredSkills,
      String experience,
      Integer vacancies,
      Long createdBy) {
    HiringRequirement req = new HiringRequirement();
    req.setJobTitle(jobTitle);
    req.setDepartment(department);
    req.setRequiredSkills(requiredSkills);
    req.setExperience(experience);
    req.setVacancies(vacancies);
    req.setCreatedBy(createdBy);

    model.addRequirement(req);
  }

  public void onViewRequirements() {
    model.getAllRequirements();
  }

  public void onUpdateStatus(Long reqId, HiringRequirement.HiringStatus status) {
    model.updateRequirementStatus(reqId, status);
  }

  public void onRequirementAdded(HiringRequirement req) {
    view.showSuccess("Hiring requirement added successfully with ID: " + req.getId());
  }

  public void onRequirementAddError(String error) {
    view.showError(error);
  }

  public void onRequirementsFetched(List<HiringRequirement> list) {
    view.displayRequirements(list);
  }

  public void onRequirementsFetchError(String error) {
    view.showError(error);
  }

  public void onStatusUpdated() {
    view.showSuccess("Requirement status updated successfully.");
  }

  public void onStatusUpdateError(String error) {
    view.showError(error);
  }
}
