package com.omnm.sale.Service;

import com.omnm.sale.DTO.SaleList;
import com.omnm.sale.Entity.Sale;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SaleServiceIF {
    ResponseEntity<Integer> postSale(String saleEmployeeId, String customerId, Integer insuranceId, String message);
    ResponseEntity<SaleList> getSaleListByCustomerId(String customerId);
}
