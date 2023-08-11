package com.itwill.myleaves.repository.planterior;

import com.itwill.myleaves.dto.planterior.PlanteriorCategoryUpdateDto;
import com.itwill.myleaves.dto.planterior.PlanteriorCreateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "PLANTERIOR_CATEGORY")
@SequenceGenerator(name = "PLANTERIOR_CATEGORY_SEQ_GEN", sequenceName = "PLANTERIOR_CATEGORY_SEQ", allocationSize = 1)
public class PlanteriorCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANTERIOR_CATEGORY_SEQ_GEN")
	private Long pcid;
	
	@Column(nullable = true)
	private Long planteriorId;
	
	@Column(nullable = false)
	private String stateContent;
	
	@Column(nullable = false)
	private String conditionContent;
	
	public PlanteriorCategory update(PlanteriorCategoryUpdateDto dto, PlanteriorCreateDto pdto) {
		this.planteriorId = pdto.getPlanteriorId();
		this.conditionContent = dto.getConditionContent();
		this.stateContent = dto.getStateContent();
		this.pcid = dto.getPcid();
		return this;
	}

}
