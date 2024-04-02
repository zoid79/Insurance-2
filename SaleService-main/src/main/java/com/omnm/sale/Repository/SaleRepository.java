package com.omnm.sale.Repository;

import com.omnm.sale.Entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

}
