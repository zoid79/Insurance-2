package com.omnm.sale.Service;

import com.omnm.sale.DAO.SaleDAO;
import com.omnm.sale.DTO.SaleList;
import com.omnm.sale.Entity.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;

class SaleServiceTest {

    @Mock
    private SaleDAO saleDAO;

    @InjectMocks
    private SaleService saleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPostSale() {
        String saleEmployeeId = "123";
        String customerId = "456";
        Integer insuranceId = 789;
        String message = "Test Sale";
        Sale sale = new Sale(saleEmployeeId, customerId, insuranceId, message);
        doNothing().when(saleDAO).createSale(any());
        ResponseEntity<Integer> response = saleService.postSale(saleEmployeeId, customerId, insuranceId, message);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sale.getId(), response.getBody());

        verify(saleDAO, times(1)).createSale(any());
        System.out.println("testPostSale 테스트 성공");
    }

    @Test
    void testGetSaleListByCustomerId() {
        String customerId = "456";
        Sale sale1 = new Sale("123", customerId, 789, "Test Sale 1");
        Sale sale2 = new Sale("456", customerId, 789, "Test Sale 2");
        List<Sale> saleList = Arrays.asList(sale1, sale2);
        when(saleDAO.findSale()).thenReturn(saleList);

        ResponseEntity<SaleList> response = saleService.getSaleListByCustomerId(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(saleList, response.getBody().getSaleList());

        verify(saleDAO, times(1)).findSale();
        System.out.println("getSaleListByCustomerId 테스트 성공");
    }
}
