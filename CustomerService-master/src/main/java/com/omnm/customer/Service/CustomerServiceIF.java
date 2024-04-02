package com.omnm.customer.Service;


import com.omnm.customer.DTO.CustomerList;
import com.omnm.customer.Entity.Customer;
import org.springframework.http.ResponseEntity;

import java.rmi.Remote;

public interface CustomerServiceIF  extends Remote {
    ResponseEntity<Integer> registerCustomer(Customer customer);

    ResponseEntity<Customer> loginCustomer(String id, String password);

    ResponseEntity<Customer> getCustomer(String customerId);

    ResponseEntity<CustomerList> getCustomerList();
}
