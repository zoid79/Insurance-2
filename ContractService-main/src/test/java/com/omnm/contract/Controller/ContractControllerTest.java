package com.omnm.contract.Controller;

import com.omnm.contract.DTO.ContractList;
import com.omnm.contract.DTO.GetContractAndInsuranceTypeByIdResponse;
import com.omnm.contract.DTO.PostContractRequest;
import com.omnm.contract.Entity.Contract;
import com.omnm.contract.Service.ContractService;
import com.omnm.contract.enumeration.ContractStatus;
import com.omnm.contract.enumeration.ContractTerm;
import com.omnm.contract.enumeration.PaymentCycle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContractControllerTest {
    @Mock
    private ContractService contractService;

    @InjectMocks
    private ContractController contractController;

    @Test
    public void testGetContractListByContractStatus() {
        ContractStatus status = ContractStatus.Apply;
        List<Contract> contracts = Arrays.asList(new Contract(), new Contract());
        when(contractService.getContractListByContractStatus(status)).thenReturn(new ResponseEntity<>(new ContractList(contracts), HttpStatus.OK));
        ResponseEntity<ContractList> response = contractController.getContractListByContractStatus(status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contracts, response.getBody().getContractList());
        verify(contractService, times(1)).getContractListByContractStatus(status);
    }

    @Test
    public void testGetContractListByCustomerId() {
        String customerId = "123";
        List<Contract> contracts = Arrays.asList(new Contract(), new Contract());
        when(contractService.getContractListByCustomerId(customerId)).thenReturn(new ResponseEntity<>(new ContractList(contracts), HttpStatus.OK));

        ResponseEntity<ContractList> response = contractController.getContractListByCustomerId(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contracts, response.getBody().getContractList());
        verify(contractService, times(1)).getContractListByCustomerId(customerId);
    }

    @Test
    public void testGetContractListByCustomerIdAndContractStatus() {
        String customerId = "123";
        ContractStatus status = ContractStatus.Apply;
        List<Contract> contracts = Arrays.asList(new Contract(), new Contract());
        when(contractService.getContractListByCustomerIdAndContractStatus(customerId, status)).thenReturn(new ResponseEntity<>(new ContractList(contracts), HttpStatus.OK));

        ResponseEntity<ContractList> response = contractController.getContractListByCustomerIdAndContractStatus(customerId, status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contracts, response.getBody().getContractList());
        verify(contractService, times(1)).getContractListByCustomerIdAndContractStatus(customerId, status);
    }
    @Test
   public void testGetContractById() {
        Integer id = 1;
        Contract contract = new Contract();
        when(contractService.getContractById(id)).thenReturn(new ResponseEntity<>(contract, HttpStatus.OK));

        ResponseEntity<Contract> response = contractController.getContractById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contract, response.getBody());
        verify(contractService, times(1)).getContractById(id);
    }

    @Test
    public void testPostContract() {
        Contract contract = new Contract(123, "123", 123, "123",
                ContractTerm.ThreeYear, new Timestamp(System.currentTimeMillis()), new
                Timestamp(System.currentTimeMillis()), 123, PaymentCycle.SixMonth,
                new Timestamp(System.currentTimeMillis()),123, ContractStatus.Apply);

        when(contractService.postContract(contract)).thenReturn(new ResponseEntity<>(1, HttpStatus.CREATED));

        ResponseEntity<Integer> result = contractController.postContract(contract);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(1, result.getBody().intValue());

        verify(contractService, times(1)).postContract(contract);
        verifyNoMoreInteractions(contractService);
    }

    @Test
    public void testGetContractAndInsuranceTypeById() {
        Integer id = 1;
        GetContractAndInsuranceTypeByIdResponse responseEntity =
                new GetContractAndInsuranceTypeByIdResponse(
                        new Contract(123, "123", 123, "123", ContractTerm.ThreeYear, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 123, PaymentCycle.SixMonth, new Timestamp(System.currentTimeMillis()),123, ContractStatus.Apply), "HomeFire");
        when(contractService.getContractAndInsuranceTypeById(id)).thenReturn(responseEntity);

        GetContractAndInsuranceTypeByIdResponse response = contractController.getContractAndInsuranceTypeById(id);

        assertEquals(responseEntity, response);
        verify(contractService, times(1)).getContractAndInsuranceTypeById(id);
    }

    @Test
    public void testPatchStartDateAndExpirationDateAndDeadlineAndContractStatusInContractById() {
        Integer id = 1;
        when(contractService.patchStartDateAndExpirationDateAndDeadlineAndContractStatusInContractById(id)).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

        ResponseEntity<Boolean> response = contractController.patchStartDateAndExpirationDateAndDeadlineAndContractStatusInContractById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
        verify(contractService, times(1)).patchStartDateAndExpirationDateAndDeadlineAndContractStatusInContractById(id);
    }
}