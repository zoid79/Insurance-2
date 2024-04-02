package com.omnm.employee.Controller;

import com.omnm.employee.DTO.LoginEmployeeRequest;
import com.omnm.employee.Entity.Employee;
import com.omnm.employee.Service.EmployeeService;
import com.omnm.employee.enumeration.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerEmployee(@RequestBody Employee employee) {
        return this.employeeService.registerEmployee(employee);
    }
    @PostMapping("/login")
    public ResponseEntity<Employee> loginEmployee(@RequestBody LoginEmployeeRequest loginEmployeeRequest) {
        return this.employeeService.loginEmployee(loginEmployeeRequest.getEmployeeId(), loginEmployeeRequest.getPassword());
    }
    @GetMapping("/employee")
    public ResponseEntity<Employee> getEmployeeByEmployeeIdAndDepartment(@Param("employeeId") String employeeId, @Param("department") Department department) {
        return this.employeeService.getEmployeeByEmployeeIdAndDepartment(employeeId, department);
    }
}
