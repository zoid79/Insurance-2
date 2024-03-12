package com.omnm.pay.Controller;

import com.omnm.pay.DTO.PostPayRequest;
import com.omnm.pay.Service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;

@RestController
public class PayController {
    @Autowired
    PayService payService;
    @PostMapping("/pay")
    public ResponseEntity<Integer> postPay(@RequestBody PostPayRequest postPayRequest){
        return payService.postPay(postPayRequest.getContract(), postPayRequest.getPay());
    }
}
