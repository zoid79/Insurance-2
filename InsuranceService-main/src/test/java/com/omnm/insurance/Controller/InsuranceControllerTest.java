package com.omnm.insurance.Controller;

import com.omnm.insurance.DTO.GetInsuranceListByInsuranceTypeAndInsuranceStatusRequest;
import com.omnm.insurance.DTO.InsuranceList;
import com.omnm.insurance.Entity.Insurance;
import com.omnm.insurance.enumeration.InsuranceStatus;
import com.omnm.insurance.Service.InsuranceService;
import com.omnm.insurance.enumeration.InsuranceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InsuranceControllerTest {

    @Mock
    private InsuranceService insuranceService;

    @InjectMocks
    private InsuranceController insuranceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInsuranceList() {
        List<Insurance> insuranceListExample = new ArrayList<>();
        insuranceListExample.add(new Insurance("Health Insurance", InsuranceType.HomeFire, "Individuals", 1,
                "Compensate conditions for health insurance", "Not compensate conditions for health insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Car Insurance", InsuranceType.HomeFire, "Vehicles", 2,
                "Compensate conditions for car insurance", "Not compensate conditions for car insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Home Insurance", InsuranceType.HomeFire, "Residences", 3,
                "Compensate conditions for home insurance", "Not compensate conditions for home insurance", InsuranceStatus.RefuseAuthorize));

        InsuranceList insuranceList = new InsuranceList(insuranceListExample);

        when(insuranceService.getInsuranceList()).thenReturn(new ResponseEntity<>(insuranceList, HttpStatus.OK));

        ResponseEntity<InsuranceList> response = insuranceController.getInsuranceList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insuranceList, response.getBody());

        verify(insuranceService, times(1)).getInsuranceList();
        verifyNoMoreInteractions(insuranceService);
        System.out.println("testGetInsuranceList 테스트 성공");
    }

    @Test
    void testGetInsuranceListByInsuranceStatus() {
        List<Insurance> insuranceListExample = new ArrayList<>();
        insuranceListExample.add(new Insurance("Health Insurance", InsuranceType.HomeFire, "Individuals", 1,
                "Compensate conditions for health insurance", "Not compensate conditions for health insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Car Insurance", InsuranceType.HomeFire, "Vehicles", 2,
                "Compensate conditions for car insurance", "Not compensate conditions for car insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Home Insurance", InsuranceType.HomeFire, "Residences", 3,
                "Compensate conditions for home insurance", "Not compensate conditions for home insurance", InsuranceStatus.RefuseAuthorize));

        InsuranceStatus insuranceStatus = InsuranceStatus.RefuseAuthorize;
        InsuranceList insuranceList = new InsuranceList(insuranceListExample);

        when(insuranceService.getInsuranceListByInsuranceStatus(insuranceStatus))
                .thenReturn(new ResponseEntity<>(insuranceList, HttpStatus.OK));

        ResponseEntity<InsuranceList> response = insuranceController.getInsuranceListByInsuranceStatus(insuranceStatus);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insuranceList, response.getBody());

        verify(insuranceService, times(1)).getInsuranceListByInsuranceStatus(insuranceStatus);
        verifyNoMoreInteractions(insuranceService);
        System.out.println("testGetInsuranceListByInsuranceStatus 테스트 성공");
    }

    @Test
    void testGetInsuranceListByInsuranceTypeAndInsuranceStatus() {
        List<Insurance> insuranceListExample = new ArrayList<>();
        insuranceListExample.add(new Insurance("Health Insurance", InsuranceType.HomeFire, "Individuals", 1,
                "Compensate conditions for health insurance", "Not compensate conditions for health insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Car Insurance", InsuranceType.HomeFire, "Vehicles", 2,
                "Compensate conditions for car insurance", "Not compensate conditions for car insurance", InsuranceStatus.RefuseAuthorize));

        insuranceListExample.add(new Insurance("Home Insurance", InsuranceType.HomeFire, "Residences", 3,
                "Compensate conditions for home insurance", "Not compensate conditions for home insurance", InsuranceStatus.RefuseAuthorize));

        GetInsuranceListByInsuranceTypeAndInsuranceStatusRequest request = new GetInsuranceListByInsuranceTypeAndInsuranceStatusRequest(
                InsuranceType.HomeFire, InsuranceStatus.UnderAuthorize);

        InsuranceList insuranceList = new InsuranceList(insuranceListExample);

        when(insuranceService.getInsuranceListByInsuranceTypeAndInsuranceStatus(request.getType(), request.getStatus()))
                .thenReturn(new ResponseEntity<>(insuranceList, HttpStatus.OK));

        ResponseEntity<InsuranceList> response = insuranceController.getInsuranceListByInsuranceTypeAndInsuranceStatus(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insuranceList, response.getBody());

        verify(insuranceService, times(1)).getInsuranceListByInsuranceTypeAndInsuranceStatus(request.getType(), request.getStatus());
        verifyNoMoreInteractions(insuranceService);
        System.out.println("testGetInsuranceListByInsuranceTypeAndInsuranceStatus 테스트 성공");
    }
    @Test
    void testGetInsuranceById() {
        Integer selectedInsuranceId = 1;
        Insurance insurance = new Insurance();
        when(insuranceService.getInsuranceById(selectedInsuranceId)).thenReturn(new ResponseEntity<>(insurance, HttpStatus.OK));

        ResponseEntity<Insurance> response = insuranceController.getInsuranceById(selectedInsuranceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insurance, response.getBody());

        verify(insuranceService, times(1)).getInsuranceById(selectedInsuranceId);
        System.out.println("testGetInsuranceById 테스트 성공");
    }

    @Test
    void testPostInsurance() {
        Insurance insurance = new Insurance();
        when(insuranceService.postInsurance(insurance)).thenReturn(new ResponseEntity<>(insurance.getId(), HttpStatus.OK));

        ResponseEntity<Integer> response = insuranceController.postInsurance(insurance);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insurance.getId(), response.getBody());

        verify(insuranceService, times(1)).postInsurance(insurance);
        System.out.println("testPostInsurance 테스트 성공");
    }

    @Test
    void testPatchInsuranceStatusInInsuranceById() {
        HashMap<String, String> param = new HashMap<>();
        param.put("id", "1");
        param.put("status", "UnderAuthorize");

        when(insuranceService.patchInsuranceStatusInInsuranceById(1, InsuranceStatus.UnderAuthorize))
                .thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

        ResponseEntity<Boolean> response = insuranceController.patchInsuranceStatusInInsuranceById(param);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());

        verify(insuranceService, times(1)).patchInsuranceStatusInInsuranceById(1, InsuranceStatus.UnderAuthorize);
        System.out.println("testPatchInsuranceStatusInInsuranceById 테스트 성공");
    }

    @Test
    void testGetInsuranceByName() {
        String name = "SampleInsuranceName";
        when(insuranceService.getInsuranceByName(name)).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

        ResponseEntity<Boolean> response = insuranceController.getInsuranceByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());

        verify(insuranceService, times(1)).getInsuranceByName(name);
        System.out.println("testGetInsuranceByName 테스트 성공");
    }
}
