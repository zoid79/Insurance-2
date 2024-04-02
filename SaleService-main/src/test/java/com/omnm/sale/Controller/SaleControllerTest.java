package com.omnm.sale.Controller;

import com.omnm.sale.DTO.SaleList;
import com.omnm.sale.Entity.Sale;
import com.omnm.sale.Service.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SaleControllerTest {

    @Mock
    private SaleService saleService;

    @InjectMocks
    private SaleController saleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPostSale() {
        HashMap<String, String> param = new HashMap<>();
        param.put("saleEmployeeId", "123");
        param.put("customerId", "456");
        param.put("insuranceId", "789");
        param.put("message", "Test message");

        when(saleService.postSale("123", "456", 789, "Test message"))
                .thenReturn(new ResponseEntity<>(123, HttpStatus.CREATED));

        ResponseEntity<Integer> response = saleController.postSale(param);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(123, response.getBody());

        verify(saleService, times(1)).postSale("123", "456", 789, "Test message");
        verifyNoMoreInteractions(saleService);
        System.out.println("postSale 테스트 성공");
    }

    @Test
    void testGetSaleListByCustomerId() {
        String customerId = "456";
        List<Sale> saleListExample = new ArrayList<>();

        // Add Sale objects to the list
        saleListExample.add(new Sale("employee1", "456", 1, "Message 1"));
        saleListExample.add(new Sale("employee2", "456", 2, "Message 2"));
        saleListExample.add(new Sale("employee3", "456", 3, "Message 3"));

        SaleList saleList = new SaleList(saleListExample);

        when(saleService.getSaleListByCustomerId("456"))
                .thenReturn(new ResponseEntity<>(saleList, HttpStatus.OK));

        ResponseEntity<SaleList> response = saleController.getSaleListByCustomerId(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(saleList, response.getBody());

        verify(saleService, times(1)).getSaleListByCustomerId("456");
        verifyNoMoreInteractions(saleService);
        System.out.println("getSaleListByCustomerId 테스트 성공");
    }
}
