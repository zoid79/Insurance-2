package com.omnm.contract.Repository;


import com.omnm.contract.Entity.Contract;
import com.omnm.contract.enumeration.ContractStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Contract findContractById(Integer id);
    List<Contract> findByContractStatus(ContractStatus status);
    List<Contract> findByCustomerId(String customerId);
}
