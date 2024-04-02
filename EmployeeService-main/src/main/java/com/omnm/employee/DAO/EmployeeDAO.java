package com.omnm.employee.DAO;

import com.omnm.employee.Entity.Employee;
import com.omnm.employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {
	@Autowired
	EmployeeRepository employeeRepository;
	public void createEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}
	public Employee findEmployeeByEmployeeId(String employeeId) {
		return this.employeeRepository.findByEmployeeId(employeeId);
	}
}