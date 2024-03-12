package com.omnm.pay.Service;

import com.omnm.pay.DAO.PayDao;
import com.omnm.pay.DTO.PatchDeadlineInContractByIdRequest;
import com.omnm.pay.Entity.Contract;
import com.omnm.pay.Entity.Pay;
import com.omnm.pay.configuration.Constants;
import com.omnm.pay.configuration.PatchRestTemplate;
import com.omnm.pay.enumeration.PaymentCycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PayService implements PayServiceIF {
    @Autowired
    PayDao payDao;
    @Override
    public ResponseEntity<Integer> postPay(Contract contract, Pay pay){
        int cycle= PaymentCycle.getCycle(contract.getPayCycle());
        Timestamp deadline= contract.getPaymentDeadline();
        LocalDateTime newDeadline = deadline.toLocalDateTime();
        newDeadline = newDeadline.plus(cycle, ChronoUnit.MONTHS);
        boolean isSuccess = setPaymentDeadline(contract.getId(), newDeadline);
        if(!isSuccess) return new ResponseEntity<>(0,new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(this.payDao.createPay(pay),new HttpHeaders(), HttpStatus.valueOf(200));
    }

    private boolean setPaymentDeadline(int id, LocalDateTime newDeadline) {
        PatchRestTemplate template = new PatchRestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(Constants.BASE_URL)
                .path(Constants.CONTRACT_SERVICE_PATCH_CONTRACT_URL + "payment-deadline-setting")
                .encode()
                .build()
                .toUri();

        PatchDeadlineInContractByIdRequest patchDeadlineInContractByIdRequest = new PatchDeadlineInContractByIdRequest(id,Timestamp.valueOf(newDeadline));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity(patchDeadlineInContractByIdRequest, headers);

        ResponseEntity<Boolean> result = template.exchange(uri, HttpMethod.PATCH, requestEntity, Boolean.class);
        return result.getBody();
    }


}
