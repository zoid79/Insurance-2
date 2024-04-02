package com.omnm.customer.Controller;

import com.omnm.customer.DTO.CustomerList;
import com.omnm.customer.Entity.Customer;
import com.omnm.customer.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<Customer> loginCustomer(@RequestBody HashMap<String, String> param) {
        return customerService.loginCustomer(param.get("customerId"), param.get("password"));
    }
    @PostMapping(value = "/signup") //화면과 코드의 관계 + 이름 규칙
    public ResponseEntity<Integer> registerCustomer(@RequestBody Customer customer) {
        return this.customerService.registerCustomer(customer);
    }
    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomerByCustomerId(@Param("customerId") String customerId){
        return this.customerService.getCustomer(customerId);
    }
    @GetMapping("/list/customer")
    public ResponseEntity<CustomerList> getCustomerList(){
        return this.customerService.getCustomerList();
    }
}
