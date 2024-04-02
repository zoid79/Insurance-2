package com.omnm.insurance.Repository;

import com.omnm.insurance.Entity.Insurance;
import com.omnm.insurance.enumeration.InsuranceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance,Integer> {
    Insurance findById(int insuranceId);
    Insurance findByName(String name);
    List<Insurance> findByStatus(InsuranceStatus status);

    Insurance findInsuranceById(Integer id);
}
