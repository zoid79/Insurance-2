package com.omnm.contract.DAO;

import com.omnm.contract.Entity.Contract;
import com.omnm.contract.Repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.omnm.contract.enumeration.ContractStatus;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ContractDAO {
    @Autowired
    private ContractRepository contractRepository;
    public Integer createContract(Contract contract) {
       return this.contractRepository.save(contract).getId();
   }
    public List<Contract> findContract(){
       return this.contractRepository.findAll();
    }
    public Boolean updateContractStatusInContractById(Integer id, ContractStatus status) {
       Contract contract = this.findContractById(id);
       contract.setContractStatus(status);
       this.contractRepository.save(contract);
       return true;
    }
    public Boolean updateDeadlineInContractById(Integer id, Timestamp deadline) {
        Contract contract = this.findContractById(id);
        contract.setPaymentDeadline(deadline);
        this.contractRepository.save(contract);
        return true;
    }
    public Boolean updateStartDateAndExpirationDateAndDeadlineAndContractStatusInContractById(Integer id, Timestamp startDate, Timestamp expirationDate, Timestamp deadline, ContractStatus status) {
        Contract contract = this.findContractById(id);
        contract.setStartDate(startDate);
        contract.setExpirationDate(expirationDate);
        updateContractStatusInContractById(contract.getId(), status);
        updateDeadlineInContractById(contract.getId(), deadline);
        return true;
    }
    public Contract findContractById(Integer id) {
        return this.contractRepository.findContractById(id);
    }
    public List<Contract> findContractByCustomerId(String customerId) {
        return contractRepository.findByCustomerId(customerId);
    }
    public List<Contract> findContractByContractStatus(ContractStatus status) {
        return contractRepository.findByContractStatus(status);
    }
}
