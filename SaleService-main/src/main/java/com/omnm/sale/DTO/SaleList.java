package com.omnm.sale.DTO;

import com.omnm.sale.Entity.Sale;

import java.util.List;

public class SaleList {
    List<Sale> saleList;
    public SaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }
    public List<Sale> getSaleList() {
        return saleList;
    }
    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }
}
