package com.omnm.contract.Service;

import com.omnm.contract.DTO.ContractList;
import com.omnm.contract.DTO.GetContractAndInsuranceTypeByIdResponse;
import com.omnm.contract.Entity.Contract;
import com.omnm.contract.enumeration.ContractStatus;

import org.springframework.http.*;

import java.sql.Timestamp;

public interface ContractServiceIF {
    ResponseEntity<ContractList> getContractListByContractStatus(ContractStatus status);
    ResponseEntity<ContractList> getContractListByCustomerId(String customerId);
    ResponseEntity<ContractList> getContractListByCustomerIdAndContractStatus(String customerId, ContractStatus status);
    ResponseEntity<ContractList> getUnpaidContractListByCustomerId(String customerId);
    ResponseEntity<Contract> getContractById(Integer id);
    ResponseEntity<Integer> postContract(Contract contract);
    GetContractAndInsuranceTypeByIdResponse getContractAndInsuranceTypeById(Integer id);
    ResponseEntity<Boolean> patchStartDateAndExpirationDateAndDeadlineAndContractStatusInContractById(Integer id);
    ResponseEntity<Boolean> patchContractStatusInContractById(Integer id, ContractStatus status);
    ResponseEntity<Boolean> patchDeadlineInContractById(Integer id, Timestamp deadline);
}
