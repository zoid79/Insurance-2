package com.omnm.employee.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.omnm.employee.DAO.EmployeeDAO;
import com.omnm.employee.Entity.Employee;
import com.omnm.employee.enumeration.Department;

@Service
public class EmployeeService implements EmployeeServiceIF {
    @Autowired
    EmployeeDAO employeeDAO;
    @Override
    public ResponseEntity<Boolean> registerEmployee(Employee employee) {
        if (employeeDAO.findEmployeeByEmployeeId(employee.getEmployeeId()) != null) return new ResponseEntity<>(false, new HttpHeaders(), HttpStatus.valueOf(500));
        employeeDAO.createEmployee(employee);
        return new ResponseEntity<>(true, new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Employee> loginEmployee(String employeeId, String password) {
        Employee employee = employeeDAO.findEmployeeByEmployeeId(employeeId);
        if(employee == null || !employee.getPassword().equals(password)) return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.valueOf(404));
        return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Employee> getEmployeeByEmployeeIdAndDepartment(String employeeId, Department department) {
        Employee employee = employeeDAO.findEmployeeByEmployeeId(employeeId);
        if(employee == null || employee.getDepartment() != department) return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.valueOf(404));
        return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.valueOf(200));
    }
}
