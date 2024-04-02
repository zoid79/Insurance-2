package com.omnm.sale.Service;

import com.omnm.sale.DAO.SaleDAO;
import com.omnm.sale.DTO.SaleList;
import com.omnm.sale.Entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements SaleServiceIF {
    @Autowired
    SaleDAO saleDAO;
    @Override
    public ResponseEntity<Integer> postSale(String saleEmployeeId, String customerId, Integer insuranceId, String message) {
        Sale sale = new Sale(saleEmployeeId, customerId, insuranceId, message);
        saleDAO.createSale(sale);
        return new ResponseEntity<>(sale.getId(), new HttpHeaders(), HttpStatus.valueOf(200));
    }
    @Override
    public ResponseEntity<SaleList> getSaleListByCustomerId(String customerId) {
        long beforeTime = System.currentTimeMillis();
        List<Sale> saleList = new ArrayList<>();
        for(Sale sale : this.saleDAO.findSale()){
            if(sale.getCustomerId().equals(customerId)) {
                saleList.add(sale);
            }
        }
        if(saleList.isEmpty()) return new ResponseEntity<>(new SaleList(saleList), new HttpHeaders(), HttpStatus.valueOf(404));
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        if(secDiffTime>=7) return new ResponseEntity<>(new SaleList(saleList), new HttpHeaders(), HttpStatus.valueOf(500));
        return new ResponseEntity<>(new SaleList(saleList), new HttpHeaders(), HttpStatus.valueOf(200));
    }
}
