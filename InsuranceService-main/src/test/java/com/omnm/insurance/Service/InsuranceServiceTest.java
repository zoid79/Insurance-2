package com.omnm.insurance.Service;

import com.omnm.insurance.DAO.InsuranceDAO;
import com.omnm.insurance.DTO.InsuranceList;
import com.omnm.insurance.enumeration.InsuranceStatus;
import com.omnm.insurance.enumeration.InsuranceType;
import com.omnm.insurance.Entity.Insurance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InsuranceServiceTest {
    @Mock
    private InsuranceDAO insuranceDAO;

    @InjectMocks
    private InsuranceService insuranceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInsuranceList() {
        List<Insurance> insuranceList = new ArrayList<>();
        when(insuranceDAO.findInsurance()).thenReturn(insuranceList);

        ResponseEntity<InsuranceList> response = insuranceService.getInsuranceList();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(insuranceList, response.getBody().getInsuranceList());

        verify(insuranceDAO, times(1)).findInsurance();
        System.out.println("testGetInsuranceList 테스트 성공");
    }

    @Test
    void testGetInsuranceListByInsuranceStatus() {
        InsuranceStatus status = InsuranceStatus.UnderAuthorize; // Replace with your actual status
        List<Insurance> insuranceList = new ArrayList<>();
        when(insuranceDAO.findByStatus(status)).thenReturn(insuranceList);

        ResponseEntity<InsuranceList> response = insuranceService.getInsuranceListByInsuranceStatus(status);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(insuranceList, response.getBody().getInsuranceList());

        verify(insuranceDAO, times(1)).findByStatus(status);
        System.out.println("testGetInsuranceListByInsuranceStatus 테스트 성공");
    }

    @Test
    void testGetInsuranceListByInsuranceTypeAndInsuranceStatus() {
        InsuranceType type = InsuranceType.WorkplaceFire; // Replace with your actual type
        InsuranceStatus status = InsuranceStatus.UnderAuthorize; // Replace with your actual status
        List<Insurance> insuranceList = new ArrayList<>();
        when(insuranceDAO.findByStatus(status)).thenReturn(insuranceList);

        ResponseEntity<InsuranceList> response = insuranceService.getInsuranceListByInsuranceTypeAndInsuranceStatus(type, status);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(insuranceList, response.getBody().getInsuranceList());

        verify(insuranceDAO, times(1)).findByStatus(status);
        System.out.println("testGetInsuranceListByInsuranceTypeAndInsuranceStatus 테스트 성공");
    }
    @Test
    void testGetInsuranceById() {
        Integer selectedInsuranceId = 1;
        Insurance insurance = new Insurance();
        when(insuranceDAO.findInsuranceById(selectedInsuranceId)).thenReturn(insurance);

        ResponseEntity<Insurance> response = insuranceService.getInsuranceById(selectedInsuranceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insurance, response.getBody());

        verify(insuranceDAO, times(1)).findInsuranceById(selectedInsuranceId);
        System.out.println("testGetInsuranceById 테스트 성공");
    }

    @Test
    void testPostInsurance() {
        Insurance insurance = new Insurance();
        when(insuranceDAO.findInsuranceByName(any())).thenReturn(null);

        ResponseEntity<Integer> response = insuranceService.postInsurance(insurance);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insurance.getId(), response.getBody());

        verify(insuranceDAO, times(1)).findInsuranceByName(any());
        verify(insuranceDAO, times(1)).createInsurance(insurance);
        System.out.println("testPostInsurance 테스트 성공");
    }

    @Test
    void testPatchInsuranceStatusInInsuranceById() {
        Integer id = 1;
        InsuranceStatus status = InsuranceStatus.UnderAuthorize;
        Insurance insurance = new Insurance();

        when(insuranceDAO.findInsuranceById(id)).thenReturn(insurance);

        ResponseEntity<Boolean> response = insuranceService.patchInsuranceStatusInInsuranceById(id, status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());

        verify(insuranceDAO, times(1)).findInsuranceById(id);
        verify(insuranceDAO, times(1)).updateInsuranceStatusInInsuranceById(id, status);
        System.out.println("testPatchInsuranceStatusInInsuranceById 테스트 성공");
    }

    @Test
    void testGetInsuranceByName() {
        String name = "SampleInsuranceName";
        when(insuranceDAO.findInsuranceByName(name)).thenReturn(null);

        ResponseEntity<Boolean> response = insuranceService.getInsuranceByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());

        verify(insuranceDAO, times(1)).findInsuranceByName(name);
        System.out.println("testGetInsuranceByName 테스트 성공");
    }
}
