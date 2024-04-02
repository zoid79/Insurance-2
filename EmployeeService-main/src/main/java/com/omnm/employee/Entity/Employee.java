package com.omnm.employee.Entity;

import com.omnm.employee.enumeration.Department;
import com.omnm.employee.enumeration.Rank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "employee_id")
	private String employeeId;
	private String password;
	private Department department;
	private String name;
	private String email;
	@Column(name = "phone_number", nullable = false)
	private long phoneNumber;
	@Column(name = "employee_rank", nullable = false)
	private Rank rank;
//	public Insurance m_Insurance;
//	public Policy m_Policy;

	public Employee(int id, String employeeId, String password, Department department, String name, String email, long phoneNumber, Rank rank) {
		
		this.id = id;
		this.employeeId = employeeId;
		this.password = password;
		this.department = department;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.rank = rank;
	}

	public Employee() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

}