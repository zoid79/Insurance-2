package com.omnm.pay.DAO;


import com.omnm.pay.Entity.Pay;
import com.omnm.pay.Repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public class PayDao {
    @Autowired
    PayRepository payRepository;

    public int createPay(Pay pay) {
        return payRepository.save(pay).getId();
    }
}
