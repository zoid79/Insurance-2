package com.omnm.pay.Repository;


import com.omnm.pay.Entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository  extends JpaRepository<Pay,Integer> {
}
