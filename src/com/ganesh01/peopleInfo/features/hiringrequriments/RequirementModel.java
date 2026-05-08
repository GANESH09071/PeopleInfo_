package com.ganesh01.peopleInfo.features.hiringrequriments;

import com.ganesh01.peopleInfo.data.dto.HiringRequirement;
import com.ganesh01.peopleInfo.data.repositry.PeopleInfoDB;
import java.util.List;

public class RequirementModel {

  private RequirementPresenter presenter;

  public RequirementModel(RequirementPresenter presenter) {
    this.presenter = presenter;
  }

  public void addRequirement(HiringRequirement req) {
    HiringRequirement created = PeopleInfoDB.getInstance().addHiringRequirement(req);
    if (created != null) {
      presenter.onRequirementAdded(created);
    } else {
      presenter.onRequirementAddError("Failed to add hiring requirement.");
    }
  }

  public void getAllRequirements() {
    List<HiringRequirement> list = PeopleInfoDB.getInstance().getAllHiringRequirements();
    if (list.isEmpty()) {
      presenter.onRequirementsFetchError("No hiring requirements found.");
    } else {
      presenter.onRequirementsFetched(list);
    }
  }

  public void updateRequirementStatus(Long id, HiringRequirement.HiringStatus status) {
    boolean success = PeopleInfoDB.getInstance().updateHiringRequirementStatus(id, status);
    if (success) {
      presenter.onStatusUpdated();
    } else {
      presenter.onStatusUpdateError("Failed to update status. Invalid ID.");
    }
  }
}
