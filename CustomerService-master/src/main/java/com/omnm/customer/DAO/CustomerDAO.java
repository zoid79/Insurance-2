package com.omnm.customer.DAO;

import com.omnm.customer.Entity.Customer;
import com.omnm.customer.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDAO{
    @Autowired CustomerRepository customerRepository;
    public List<Customer> findCustomerList() {return customerRepository.findAll();}
    public Customer saveCustomer(Customer customer) {return customerRepository.save(customer);}
    public Customer findByCustomerId(String customerId) {return customerRepository.findByCustomerId(customerId);}
}
