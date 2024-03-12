package com.omnm.accident;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnm.accident.Entity.Accident;
import com.omnm.accident.Repository.AccidentRepository;
import com.omnm.accident.Service.AccidentService;
import com.omnm.accident.DTO.AccidentList;
import com.omnm.accident.DTO.PatchStatusByIdRequest;
import com.omnm.accident.enumeration.accident.AccidentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class AccidentServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccidentService accidentService;
    @MockBean
    private AccidentRepository accidentRepository;
    @BeforeEach
        // 테스트 실행 전 실행
    void setup(){
        Accident accident1 = new Accident();
        accident1.setId(1);
        accident1.setContractId(1);
        accident1.setStatus(AccidentStatus.Compensate);
        accident1.setCause("화재");
        accident1.setContent("업장에서 불이 났습니다");
        accident1.setDate(new Timestamp(System.currentTimeMillis()));
        accident1.setAccountNumber("1234-32-2132322");
        accident1.setDamage(50000000);
        accident1.setLocation("강남");
        accidentRepository.save(accident1);
        Accident accident2 = new Accident();
        accident2.setId(2);
        accident2.setContractId(1);
        accident2.setStatus(AccidentStatus.Compensate);
        accident2.setCause("화재");
        accident2.setContent("업장에서 불이 났습니다");
        accident2.setDate(new Timestamp(System.currentTimeMillis()));
        accident2.setAccountNumber("1234-32-2132322");
        accident2.setDamage(50000000);
        accident2.setLocation("강남");
        accidentRepository.save(accident2);
        Accident accident3 = new Accident();
        accident3.setId(3);
        accident3.setContractId(1);
        accident3.setStatus(AccidentStatus.ReportAccident);
        accident3.setCause("화재");
        accident3.setContent("업장에서 불이 났습니다");
        accident3.setDate(new Timestamp(System.currentTimeMillis()));
        accident3.setAccountNumber("1234-32-2132322");
        accident3.setDamage(50000000);
        accident3.setLocation("강남");
        accidentRepository.save(accident3);
    }
    @Test
    void testGetAccidentListByStatus() throws Exception {
        Accident accident1 = new Accident();
        accident1.setId(1);
        accident1.setContractId(1);
        accident1.setStatus(AccidentStatus.Compensate);
        accident1.setCause("화재");
        accident1.setContent("업장에서 불이 났습니다");
        accident1.setDate(new Timestamp(System.currentTimeMillis()));
        accident1.setAccountNumber("1234-32-2132322");
        accident1.setDamage(50000000);
        accident1.setLocation("강남");
        Accident accident2 = new Accident();
        accident2.setId(2);
        accident2.setContractId(1);
        accident2.setStatus(AccidentStatus.Compensate);
        accident2.setCause("화재");
        accident2.setContent("업장에서 불이 났습니다");
        accident2.setDate(new Timestamp(System.currentTimeMillis()));
        accident2.setAccountNumber("1234-32-2132322");
        accident2.setDamage(50000000);
        accident2.setLocation("강남");
        ArrayList arrayList=new ArrayList();
        arrayList.add(accident1);
        arrayList.add(accident2);
        AccidentList accidentList = new AccidentList(arrayList);
        when(accidentService.getAccidentListByStatus(AccidentStatus.ReportAccident)).thenReturn(ResponseEntity.ok(accidentList));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/accidents/ReportAccident")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(accidentService, times(1)).getAccidentListByStatus(AccidentStatus.ReportAccident);
        System.out.println("테스트가 성공적으로 완료되었습니다");
    }

    @Test
    void testGetAccidentListByCustomerId() throws Exception {
        Accident accident1 = new Accident();
        accident1.setId(1);
        accident1.setContractId(1);
        accident1.setStatus(AccidentStatus.Compensate);
        accident1.setCause("화재");
        accident1.setContent("업장에서 불이 났습니다");
        accident1.setDate(new Timestamp(System.currentTimeMillis()));
        accident1.setAccountNumber("1234-32-2132322");
        accident1.setDamage(50000000);
        accident1.setLocation("강남");
        Accident accident2 = new Accident();
        accident2.setId(2);
        accident2.setContractId(1);
        accident2.setStatus(AccidentStatus.Compensate);
        accident2.setCause("화재");
        accident2.setContent("업장에서 불이 났습니다");
        accident2.setDate(new Timestamp(System.currentTimeMillis()));
        accident2.setAccountNumber("1234-32-2132322");
        accident2.setDamage(50000000);
        accident2.setLocation("강남");
        ArrayList arrayList=new ArrayList();
        arrayList.add(accident1);
        arrayList.add(accident2);
        AccidentList accidentList = new AccidentList(arrayList);
        when(accidentService.getAccidentListByCustomerId(1)).thenReturn(ResponseEntity.ok(accidentList));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/list/accidents/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(accidentService, times(1)).getAccidentListByCustomerId(1);
        System.out.println("테스트가 성공적으로 완료되었습니다");
    }

    @Test
    void testGetAccidentById() throws Exception {
        Accident accident = new Accident();
        when(accidentService.getAccidentById(1)).thenReturn(ResponseEntity.ok(accident));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/accident/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(accidentService, times(1)).getAccidentById(1);
        System.out.println("테스트가 성공적으로 완료되었습니다");
    }

    @Test
    void testPostAccident() throws Exception {
        Accident accident = new Accident();
        when(accidentService.postAccident(any(Accident.class))).thenReturn(ResponseEntity.ok(1));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/accident")
                        .content(objectMapper.writeValueAsString(accident))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(accidentService, times(1)).postAccident(any(Accident.class));
        System.out.println("테스트가 성공적으로 완료되었습니다");
    }

    @Test
    void testPatchStatusById() throws Exception {
        PatchStatusByIdRequest patchStatusByIdRequest = new PatchStatusByIdRequest(1, AccidentStatus.RefuseCompensate);
        when(accidentService.patchStatusById(1, AccidentStatus.RefuseCompensate)).thenReturn(ResponseEntity.ok(true));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/status")
                        .content(objectMapper.writeValueAsString(patchStatusByIdRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(accidentService, times(1)).patchStatusById(1, AccidentStatus.RefuseCompensate);
        System.out.println("테스트가 성공적으로 완료되었습니다");
    }
}