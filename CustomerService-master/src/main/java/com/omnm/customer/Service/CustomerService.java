package com.omnm.customer.Service;


import com.omnm.customer.DAO.CustomerDAO;
import com.omnm.customer.DTO.CustomerList;
import com.omnm.customer.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceIF{

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public ResponseEntity<Integer> registerCustomer(Customer customer) {
        Customer result = customerDAO.saveCustomer(customer);
        if (customerDAO.findByCustomerId(customer.getCustomerId()) != null) return new ResponseEntity<>(0, new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(1, new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Customer> loginCustomer(String id, String password) {
        Customer customer = customerDAO.findByCustomerId(id);
        if(customer == null || !customer.getPassword().equals(password)) return new ResponseEntity<>(customer, new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(customer, new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<Customer> getCustomer(String customemrId) {
        Customer customer = customerDAO.findByCustomerId(customemrId);
        if(customer == null) return new ResponseEntity<>(customer, new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(customer, new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<CustomerList> getCustomerList() {
        CustomerList customerList = new CustomerList(customerDAO.findCustomerList());
        if (customerList.getCustomerList().size() == 0) return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.valueOf(200));
        return new ResponseEntity<>(customerList, new HttpHeaders(), HttpStatus.valueOf(200));
    }

}