package com.omnm.customer.DTO;

import com.omnm.customer.Entity.Customer;

import java.util.List;

public class CustomerList {
    private List<Customer> customerList;

    public CustomerList() {}
    public CustomerList(List<Customer> customerList) {this.customerList = customerList;}

    public List<Customer> getCustomerList() {return customerList;}
    public void setCustomerList(List<Customer> customerList) {this.customerList = customerList;}
}
