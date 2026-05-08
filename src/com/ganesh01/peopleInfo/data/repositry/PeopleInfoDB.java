package com.ganesh01.peopleInfo.data.repositry;

import com.ganesh01.peopleInfo.data.dto.Employee;
import com.ganesh01.peopleInfo.data.dto.HiringRequirement;
import com.ganesh01.peopleInfo.data.dto.LeaveRequest;
import com.ganesh01.peopleInfo.data.dto.TimeSheet;
import com.ganesh01.peopleInfo.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeopleInfoDB {

  private static PeopleInfoDB instance;

  private PeopleInfoDB() {}

  public static PeopleInfoDB getInstance() {
    if (instance == null) {
      instance = new PeopleInfoDB();
    }
    return instance;
  }

  private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
    Employee emp = new Employee();
    emp.setEmployeeId(rs.getString("employeeId"));
    emp.setName(rs.getString("name"));
    emp.setEmail(rs.getString("email"));
    emp.setPassword(rs.getString("password"));
    emp.setMobileNo(rs.getString("mobileNo"));
    emp.setDob(rs.getLong("dob") == 0 ? null : rs.getLong("dob"));
    String roleStr = rs.getString("role");
    if (roleStr != null) {
      emp.setRole(Employee.Role.valueOf(roleStr));
    }
    emp.setDepartment(rs.getString("department"));
    emp.setDesignation(rs.getString("designation"));
    emp.setReportingTo(rs.getLong("reportingTo") == 0 ? null : rs.getLong("reportingTo"));
    String statusStr = rs.getString("status");
    if (statusStr != null) {
      emp.setStatus(Employee.EmployeeStatus.valueOf(statusStr));
    }
    java.sql.Timestamp createdTs = rs.getTimestamp("createdAt");
    emp.setCreatedAt(createdTs != null ? createdTs.getTime() : 0);
    return emp;
  }

  public Employee addEmployee(Employee employee) {
    if (isEmailExists(employee.getEmail())) {
      return null;
    }

    int nextId = 1;
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM employees");
        ResultSet rs = stmt.executeQuery()) {
      if (rs.next()) {
        nextId = rs.getInt(1) + 1;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    employee.setEmployeeId(String.format("EMP%03d", nextId));
    employee.setStatus(Employee.EmployeeStatus.ACTIVE);
    employee.setCreatedAt(System.currentTimeMillis());

    String sql =
        "INSERT INTO employees (employeeId, name, email, password, mobileNo, dob, role, department,"
            + " designation, reportingTo, status, createdAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
            + " ?, ?)";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, employee.getEmployeeId());
      pstmt.setString(2, employee.getName());
      pstmt.setString(3, employee.getEmail().toLowerCase());
      pstmt.setString(4, employee.getPassword());
      pstmt.setString(5, employee.getMobileNo());
      pstmt.setObject(6, employee.getDob());
      pstmt.setString(7, employee.getRole() != null ? employee.getRole().name() : null);
      pstmt.setString(8, employee.getDepartment());
      pstmt.setString(9, employee.getDesignation());
      pstmt.setObject(10, employee.getReportingTo());
      pstmt.setString(11, employee.getStatus().name());
      pstmt.setTimestamp(12, new java.sql.Timestamp(employee.getCreatedAt()));

      if (pstmt.executeUpdate() > 0) {
        return employee;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Employee validateUser(String email, String password) {
    String sql = "SELECT * FROM employees WHERE email = ? AND password = ? AND status = 'ACTIVE'";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, email.toLowerCase());
      pstmt.setString(2, password);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) return extractEmployeeFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean isEmailExists(String email) {
    String sql = "SELECT COUNT(*) FROM employees WHERE email = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, email.toLowerCase());
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next() && rs.getInt(1) > 0) return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public List<Employee> getEmployeesByRole(Employee.Role role) {
    List<Employee> result = new ArrayList<>();
    String sql = "SELECT * FROM employees WHERE role = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, role.name());
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) result.add(extractEmployeeFromResultSet(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public Employee getEmployeeByEmail(String email) {
    String sql = "SELECT * FROM employees WHERE email = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, email.toLowerCase());
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) return extractEmployeeFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Employee getEmployeeById(String id) {
    String sql = "SELECT * FROM employees WHERE employeeId = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) return extractEmployeeFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Employee> getAllEmployees() {
    List<Employee> result = new ArrayList<>();
    String sql = "SELECT * FROM employees";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) result.add(extractEmployeeFromResultSet(rs));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  private TimeSheet extractTimeSheet(ResultSet rs) throws SQLException {
    TimeSheet t = new TimeSheet();
    t.setEmployeeId(rs.getString("employeeId"));

    java.sql.Date d = rs.getDate("date");
    t.setDate(d != null ? d.getTime() : null);

    java.sql.Timestamp login = rs.getTimestamp("loginTime");
    t.setLoginTime(login != null ? login.getTime() : null);

    java.sql.Timestamp logout = rs.getTimestamp("logoutTime");
    t.setLogoutTime(logout != null ? logout.getTime() : null);

    double hours = rs.getDouble("totalHours");
    t.setTotalHours(rs.wasNull() ? null : hours);

    String status = rs.getString("status");
    if (status != null) t.setStatus(TimeSheet.TimesheetStatus.valueOf(status));

    java.sql.Timestamp created = rs.getTimestamp("createdTime");
    t.setCreatedTime(created != null ? created.getTime() : null);

    return t;
  }

  public void checkIn(String empId) {
    String sql =
        "INSERT INTO timesheets (employeeId, date, loginTime, status, createdTime) VALUES (?, ?, ?,"
            + " ?, ?)";
    long now = System.currentTimeMillis();
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, empId);
      pstmt.setDate(2, new java.sql.Date(now));
      pstmt.setTimestamp(3, new java.sql.Timestamp(now));
      pstmt.setString(4, TimeSheet.TimesheetStatus.CHECKED_IN.name());
      pstmt.setTimestamp(5, new java.sql.Timestamp(now));
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void checkOut(String empId) {
    String selectSql =
        "SELECT * FROM timesheets WHERE employeeId = ? AND status = 'CHECKED_IN' ORDER BY id DESC"
            + " LIMIT 1";
    String updateSql =
        "UPDATE timesheets SET logoutTime = ?, totalHours = ?, status = ? WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
        PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

      selectStmt.setString(1, empId);
      try (ResultSet rs = selectStmt.executeQuery()) {
        if (rs.next()) {
          int id = rs.getInt("id");
          java.sql.Timestamp loginTs = rs.getTimestamp("loginTime");
          long loginTime = loginTs != null ? loginTs.getTime() : 0;
          long logoutTime = System.currentTimeMillis();
          double hours = (logoutTime - loginTime) / (1000.0 * 60 * 60);

          try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setTimestamp(1, new java.sql.Timestamp(logoutTime));
            updateStmt.setDouble(2, hours);
            updateStmt.setString(3, TimeSheet.TimesheetStatus.CHECKED_OUT.name());
            updateStmt.setInt(4, id);
            updateStmt.executeUpdate();
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<TimeSheet> getTimeSheets(String empId) {
    List<TimeSheet> list = new ArrayList<>();
    String sql = "SELECT * FROM timesheets WHERE employeeId = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, empId);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) list.add(extractTimeSheet(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  private HiringRequirement extractHiringReq(ResultSet rs) throws SQLException {
    HiringRequirement req = new HiringRequirement();
    req.setId(rs.getLong("id"));
    req.setJobTitle(rs.getString("jobTitle"));
    req.setDepartment(rs.getString("department"));
    req.setRequiredSkills(rs.getString("requiredSkills"));
    req.setExperience(rs.getString("experience"));
    req.setVacancies(rs.getInt("vacancies"));
    String status = rs.getString("status");
    if (status != null) req.setStatus(HiringRequirement.HiringStatus.valueOf(status));
    req.setCreatedBy(rs.getLong("createdBy"));
    java.sql.Timestamp created = rs.getTimestamp("createdTime");
    req.setCreatedTime(created != null ? created.getTime() : null);
    java.sql.Timestamp updated = rs.getTimestamp("updatedTime");
    req.setUpdatedTime(updated != null ? updated.getTime() : null);
    return req;
  }

  public HiringRequirement addHiringRequirement(HiringRequirement req) {
    req.setCreatedTime(System.currentTimeMillis());
    req.setStatus(HiringRequirement.HiringStatus.OPEN);

    String sql =
        "INSERT INTO hiring_requirements (jobTitle, department, requiredSkills, experience,"
            + " vacancies, status, createdBy, createdTime, updatedTime) VALUES (?, ?, ?, ?, ?, ?,"
            + " ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      pstmt.setString(1, req.getJobTitle());
      pstmt.setString(2, req.getDepartment());
      pstmt.setString(3, req.getRequiredSkills());
      pstmt.setString(4, req.getExperience());
      pstmt.setObject(5, req.getVacancies());
      pstmt.setString(6, req.getStatus().name());
      pstmt.setObject(7, req.getCreatedBy());
      pstmt.setTimestamp(8, req.getCreatedTime() != null ? new java.sql.Timestamp(req.getCreatedTime()) : null);
      pstmt.setTimestamp(9, req.getUpdatedTime() != null ? new java.sql.Timestamp(req.getUpdatedTime()) : null);

      pstmt.executeUpdate();
      try (ResultSet rs = pstmt.getGeneratedKeys()) {
        if (rs.next()) {
          req.setId(rs.getLong(1));
          return req;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<HiringRequirement> getAllHiringRequirements() {
    List<HiringRequirement> list = new ArrayList<>();
    String sql = "SELECT * FROM hiring_requirements";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) list.add(extractHiringReq(rs));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public HiringRequirement getHiringRequirementById(Long id) {
    String sql = "SELECT * FROM hiring_requirements WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setLong(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) return extractHiringReq(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean updateHiringRequirementStatus(Long id, HiringRequirement.HiringStatus status) {
    String sql = "UPDATE hiring_requirements SET status = ?, updatedTime = ? WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, status.name());
      pstmt.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
      pstmt.setLong(3, id);
      return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  private LeaveRequest extractLeaveReq(ResultSet rs) throws SQLException {
    LeaveRequest req = new LeaveRequest();
    req.setLeaveId(rs.getLong("leaveId"));
    req.setEmployeeId(rs.getString("employeeId"));
    String type = rs.getString("leaveType");
    if (type != null) req.setLeaveType(LeaveRequest.LeaveType.valueOf(type));
    req.setStartDate(rs.getLong("startDate"));
    req.setEndDate(rs.getLong("endDate"));
    req.setReason(rs.getString("reason"));
    String status = rs.getString("status");
    if (status != null) req.setStatus(LeaveRequest.LeaveStatus.valueOf(status));
    req.setApprovedBy(rs.getString("approvedBy"));
    req.setCreatedTime(rs.getLong("createdTime"));
    req.setUpdatedTime(rs.getLong("updatedTime"));
    return req;
  }

  public LeaveRequest applyLeave(LeaveRequest req) {
    req.setCreatedTime(System.currentTimeMillis());
    req.setStatus(LeaveRequest.LeaveStatus.PENDING);

    String sql =
        "INSERT INTO leave_requests (employeeId, leaveType, startDate, endDate, reason, status,"
            + " approvedBy, createdTime, updatedTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      pstmt.setString(1, req.getEmployeeId());
      pstmt.setString(2, req.getLeaveType() != null ? req.getLeaveType().name() : null);
      pstmt.setObject(3, req.getStartDate());
      pstmt.setObject(4, req.getEndDate());
      pstmt.setString(5, req.getReason());
      pstmt.setString(6, req.getStatus().name());
      pstmt.setString(7, req.getApprovedBy());
      pstmt.setLong(8, req.getCreatedTime());
      pstmt.setObject(9, req.getUpdatedTime());

      pstmt.executeUpdate();
      try (ResultSet rs = pstmt.getGeneratedKeys()) {
        if (rs.next()) {
          req.setLeaveId(rs.getLong(1));
          return req;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<LeaveRequest> getLeavesByEmployee(String empId) {
    List<LeaveRequest> list = new ArrayList<>();
    String sql = "SELECT * FROM leave_requests WHERE employeeId = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, empId);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) list.add(extractLeaveReq(rs));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<LeaveRequest> getAllLeaves() {
    List<LeaveRequest> list = new ArrayList<>();
    String sql = "SELECT * FROM leave_requests";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) list.add(extractLeaveReq(rs));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public boolean updateLeaveStatus(
      Long leaveId, LeaveRequest.LeaveStatus status, String approvedBy) {
    String sql =
        "UPDATE leave_requests SET status = ?, approvedBy = ?, updatedTime = ? WHERE leaveId = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, status.name());
      pstmt.setString(2, approvedBy);
      pstmt.setLong(3, System.currentTimeMillis());
      pstmt.setLong(4, leaveId);
      return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
