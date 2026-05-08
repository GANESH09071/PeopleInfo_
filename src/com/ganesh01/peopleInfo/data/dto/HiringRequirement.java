package com.ganesh01.peopleInfo.data.dto;

public class HiringRequirement {

  private Long id;
  private String jobTitle;
  private String department;
  private String requiredSkills;
  private String experience;
  private Integer vacancies;
  private HiringStatus status;
  private Long createdBy;
  private Long createdTime;
  private Long updatedTime;

  public enum HiringStatus {
    OPEN,
    CLOSED,
    ON_HOLD
  }

  public HiringRequirement() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getRequiredSkills() {
    return requiredSkills;
  }

  public void setRequiredSkills(String requiredSkills) {
    this.requiredSkills = requiredSkills;
  }

  public String getExperience() {
    return experience;
  }

  public void setExperience(String experience) {
    this.experience = experience;
  }

  public Integer getVacancies() {
    return vacancies;
  }

  public void setVacancies(Integer vacancies) {
    this.vacancies = vacancies;
  }

  public HiringStatus getStatus() {
    return status;
  }

  public void setStatus(HiringStatus status) {
    this.status = status;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
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
