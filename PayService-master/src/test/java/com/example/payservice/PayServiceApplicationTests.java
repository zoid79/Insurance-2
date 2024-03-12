package com.example.payservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnm.pay.DTO.PostPayRequest;
import com.omnm.pay.Entity.Contract;
import com.omnm.pay.Entity.Pay;
import com.omnm.pay.PayServiceApplication;
import com.omnm.pay.Service.PayService;
import com.omnm.pay.enumeration.ContractStatus;
import com.omnm.pay.enumeration.ContractTerm;
import com.omnm.pay.enumeration.PaymentCycle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PayServiceApplication.class)
@AutoConfigureMockMvc
class PayServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PayService payService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPostPay() throws Exception {
        Contract contract = new Contract(1,1,"1","1", ContractTerm.OneYear,3000, PaymentCycle.AMonth,10000, ContractStatus.Apply);
        contract.setId(1);
        Pay pay = new Pay(1,"123");
        when(payService.postPay(contract, pay)).thenReturn(ResponseEntity.ok(1));
        PostPayRequest postPayRequest = new PostPayRequest();
        postPayRequest.setContract(contract);
        postPayRequest.setPay(pay);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pay")
                        .content(objectMapper.writeValueAsString(postPayRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        verify(payService, times(1)).postPay(any(Contract.class), any(Pay.class));
        System.out.println("테스트가 성공적으로 완료되었습니다");
    }


}