package com.omnm.sale.DAO;

import com.omnm.sale.Entity.Sale;
import com.omnm.sale.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleDAO {
    @Autowired
    private SaleRepository saleRepository;
    public void createSale(Sale sale) {
        this.saleRepository.save(sale);
    }
    public List<Sale> findSale(){
        return this.saleRepository.findAll();
    }
}