package com.omnm.employee.DTO;

public class LoginEmployeeRequest {
    String employeeId;
    String password;
    public  LoginEmployeeRequest(String employeeId, String password) {
        this.employeeId = employeeId;
        this.password = password;
    }
    public String getEmployeeId() {
        return employeeId;
    }

    public String getPassword() {
        return password;
    }
}
