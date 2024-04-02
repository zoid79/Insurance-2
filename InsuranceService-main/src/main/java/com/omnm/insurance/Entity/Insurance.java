package com.omnm.insurance.Entity;

import java.io.Serializable;
import com.omnm.insurance.enumeration.InsuranceStatus;
import com.omnm.insurance.enumeration.InsuranceType;
import javax.persistence.*;

@Entity
@Table(name = "insurance")
public class Insurance implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private InsuranceType type;
	private String target;
	@Column(name = "calculation_formula_id", nullable = false)
	private Integer calculationFormulaId;
	@Column(name = "compensate_condition", nullable = false)
	private String compensateCondition;
	@Column(name = "not_compensate_condition", nullable = false)
	private String notCompensateCondition;
	private InsuranceStatus status;

	public Insurance(String name, InsuranceType type, String target, Integer calculationFormulaId,
					 String compensateCondition, String notCompensateCondition, InsuranceStatus status) {
		this.name = name;
		this.type = type;
		this.target = target;
		this.calculationFormulaId = calculationFormulaId;
		this.compensateCondition = compensateCondition;
		this.notCompensateCondition = notCompensateCondition;
		this.status = status;
	}

	public Insurance() {

	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InsuranceType getType() {
		return type;
	}

	public void setType(InsuranceType type) {
		this.type = type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getCalculationFormulaId() {
		return calculationFormulaId;
	}

	public void setCalculationFormulaId(Integer calculationFormulaId) {
		this.calculationFormulaId = calculationFormulaId;
	}

	public String getCompensateCondition() {
		return compensateCondition;
	}

	public void setCompensateCondition(String compensateCondition) {
		this.compensateCondition = compensateCondition;
	}

	public String getNotCompensateCondition() {
		return notCompensateCondition;
	}

	public void setNotCompensateCondition(String notCompensateCondition) {
		this.notCompensateCondition = notCompensateCondition;
	}

	public InsuranceStatus getStatus() {
		return status;
	}

	public void setStatus(InsuranceStatus status) {
		this.status = status;
	}

}