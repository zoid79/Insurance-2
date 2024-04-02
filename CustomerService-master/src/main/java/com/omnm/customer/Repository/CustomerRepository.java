package com.omnm.customer.Repository;

import com.omnm.customer.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT r FROM Customer r WHERE r.customerId = :customerId")
    Customer findByCustomerId(String customerId);
}