package com.omnm.customer.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String customerId;
	private String password;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private Boolean hasHome;
	private Boolean hasWorkplace;

	public Customer() {
		this.customerId = "";
		this.password = "";
		this.name = "";
		this.email = "";
		this.phoneNumber = "";
		this.address = "";
		this.hasHome = false;
		this.hasWorkplace = false;
	}

	public Customer(int id, String customerId, String password, String name, String email, String phoneNumber, String address, Boolean hasHome, Boolean hasWorkplace) {
		this.id = id;
		this.customerId = customerId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.hasHome = hasHome;
		this.hasWorkplace = hasWorkplace;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getHasHome() {
		return hasHome;
	}

	public Boolean getHasWorkplace() {
		return hasWorkplace;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String id) {
		this.customerId = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean isHasHome() {
		return hasHome;
	}

	public void setHasHome(Boolean hasHome) {
		this.hasHome = hasHome;
	}

	public Boolean isHasWorkplace() {
		return hasWorkplace;
	}

	public void setHasWorkplace(Boolean hasWorkplace) {
		this.hasWorkplace = hasWorkplace;
	}

}