package com.omnm.pay.Service;


import com.omnm.pay.Entity.Contract;
import com.omnm.pay.Entity.Pay;
import org.springframework.http.ResponseEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PayServiceIF{
    ResponseEntity<Integer> postPay(Contract contract, Pay pay);
}
