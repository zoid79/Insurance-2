package com.omnm.contract.Controller;

import com.omnm.contract.DTO.*;
import com.omnm.contract.Entity.Contract;
import com.omnm.contract.enumeration.ContractStatus;
import com.omnm.contract.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContractController {
    @Autowired
    ContractService contractService;
    @GetMapping("/list/contracts")
    public ResponseEntity<ContractList> getContractListByContractStatus(@Param(value = "status") ContractStatus status) {
        return this.contractService.getContractListByContractStatus(status);
    }
    @GetMapping("/list/contracts/{customerId}")
    public ResponseEntity<ContractList> getContractListByCustomerId(@PathVariable("customerId") String customerId) {
        return this.contractService.getContractListByCustomerId(customerId);
    }
    @GetMapping("/list/contracts/{customerId}/{status}")
    public ResponseEntity<ContractList> getContractListByCustomerIdAndContractStatus(@PathVariable("customerId") String customerId, @PathVariable("status") ContractStatus status) {
        return this.contractService.getContractListByCustomerIdAndContractStatus(customerId, status);
    }
    @GetMapping("/contract/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable("id") Integer id) {
        return this.contractService.getContractById(id);
    }
    @PostMapping("/appliance")
    public ResponseEntity<Integer> postContract(@RequestBody Contract contract) {
        return this.contractService.postContract(contract);
    }
    @GetMapping("/contract-and-insurance_id")
    public GetContractAndInsuranceTypeByIdResponse getContractAndInsuranceTypeById(@Param(value = "id") Integer id) {
        return this.contractService.getContractAndInsuranceTypeById(id);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/conclusion")
    public ResponseEntity<Boolean> patchStartDateAndExpirationDateAndDeadlineAndContractStatusInContractById(@Param(value = "id") Integer id) {
        return this.contractService.patchStartDateAndExpirationDateAndDeadlineAndContractStatusInContractById(id);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/underwriting-examination")
    public ResponseEntity<Boolean> patchContractStatusInContractById(@RequestBody PatchContractStatusInContractByIdRequest patchContractStatusInContractByIdRequest) {
        return this.contractService.patchContractStatusInContractById(patchContractStatusInContractByIdRequest.getContractId(), patchContractStatusInContractByIdRequest.getStatus());
    }
    @GetMapping("list/contracts/unpaid")
    public ResponseEntity<ContractList> getUnpaidContractListByCustomerId(@Param("customerId") String customerId) {
        return this.contractService.getUnpaidContractListByCustomerId(customerId);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("payment-deadline-setting")
    public ResponseEntity<Boolean> patchDeadlineInContractById(@RequestBody PatchDeadlineInContractByIdRequest patchDeadlineInContractByIdRequest) {
        return this.contractService.patchDeadlineInContractById(patchDeadlineInContractByIdRequest.getId(), patchDeadlineInContractByIdRequest.getPaymentDeadline());
    }
}
