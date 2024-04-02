package com.omnm.insurance.DAO;

import com.omnm.insurance.Repository.InsuranceRepository;
import com.omnm.insurance.enumeration.InsuranceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.omnm.insurance.Entity.Insurance;
import java.util.List;

@Repository
public class InsuranceDAO {
	@Autowired
	private InsuranceRepository insuranceRepository;
	public void createInsurance(Insurance insurance) {
		this.insuranceRepository.save(insurance);
	}
	public List<Insurance> findInsurance() {
		return this.insuranceRepository.findAll();
	}
	public void updateInsuranceStatusInInsuranceById(Integer id, InsuranceStatus status) {
		Insurance insurance = this.findInsuranceById(id);
		insurance.setStatus(status);
		this.insuranceRepository.save(insurance);
	}
	public List<Insurance> findByStatus(InsuranceStatus insuranceStatus) {
		return this.insuranceRepository.findByStatus(insuranceStatus);
	}
	public Insurance findInsuranceById(Integer id) {
		return this.insuranceRepository.findInsuranceById(id);
	}
	public Insurance findInsuranceByName(String name) {
		return this.insuranceRepository.findByName(name);
	}
}