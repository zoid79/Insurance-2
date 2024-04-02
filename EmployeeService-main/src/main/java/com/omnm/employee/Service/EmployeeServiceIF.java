package com.omnm.employee.Service;


import java.rmi.Remote;

import com.omnm.employee.Entity.Employee;
import com.omnm.employee.enumeration.Department;
import org.springframework.http.ResponseEntity;

public interface EmployeeServiceIF  extends Remote {
    ResponseEntity<Boolean> registerEmployee(Employee Employee);

    ResponseEntity<Employee> loginEmployee(String id, String password);

    ResponseEntity<Employee> getEmployeeByEmployeeIdAndDepartment(String id, Department department);
}
