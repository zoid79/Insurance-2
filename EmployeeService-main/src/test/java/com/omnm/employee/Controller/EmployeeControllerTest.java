package com.omnm.employee.Controller;

import com.omnm.employee.DTO.LoginEmployeeRequest;
import com.omnm.employee.Entity.Employee;
import com.omnm.employee.enumeration.Department;
import com.omnm.employee.Service.EmployeeService;
import com.omnm.employee.enumeration.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterEmployee() {
        Employee employee = new Employee(1, "1234", "1234", Department.UW, "ank", "ankemail", 1111111111, Rank.SeniorStaff);

        when(employeeService.registerEmployee(employee)).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

        ResponseEntity<Boolean> response = employeeController.registerEmployee(employee);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());

        verify(employeeService, times(1)).registerEmployee(employee);
        verifyNoMoreInteractions(employeeService);
        System.out.println("testRegisterEmployee 테스트 성공");
    }

    @Test
    void testLoginEmployee() {
        LoginEmployeeRequest loginRequest = new LoginEmployeeRequest("1234", "1234");
        Employee employee = new Employee(/* Set employee details */);

        when(employeeService.loginEmployee(loginRequest.getEmployeeId(), loginRequest.getPassword()))
                .thenReturn(new ResponseEntity<>(employee, HttpStatus.OK));

        ResponseEntity<Employee> response = employeeController.loginEmployee(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());

        verify(employeeService, times(1)).loginEmployee(loginRequest.getEmployeeId(), loginRequest.getPassword());
        verifyNoMoreInteractions(employeeService);
        System.out.println("testLoginEmployee 테스트 성공");
    }

    @Test
    void testGetEmployeeByEmployeeIdAndDepartment() {
        String employeeId = "123";
        Department department = Department.UW;
        Employee employee = new Employee(1, "1234", "1234", Department.UW, "ank", "ankemail", 1111111111, Rank.SeniorStaff);

        when(employeeService.getEmployeeByEmployeeIdAndDepartment(employeeId, department))
                .thenReturn(new ResponseEntity<>(employee, HttpStatus.OK));

        ResponseEntity<Employee> response = employeeController.getEmployeeByEmployeeIdAndDepartment(employeeId, department);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());

        verify(employeeService, times(1)).getEmployeeByEmployeeIdAndDepartment(employeeId, department);
        verifyNoMoreInteractions(employeeService);
        System.out.println("testGetEmployeeByEmployeeIdAndDepartment 테스트 성공");
    }
}
