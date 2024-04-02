package com.omnm.employee.Repository;


import com.omnm.employee.Entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findByEmployeeId(String employeeId);
}