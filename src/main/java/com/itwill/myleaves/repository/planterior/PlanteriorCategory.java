package com.itwill.myleaves.repository.planterior;

import com.itwill.myleaves.dto.planterior.PlanteriorCategoryUpdateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class PlanteriorCategory {
	
	@Id
	private Long planteriorId;
	
	@Column(nullable = false)
	private String stateContent;
	
	@Column(nullable = false)
	private String conditionContent;
	
	public PlanteriorCategory update(PlanteriorCategoryUpdateDto dto) {
		this.planteriorId = dto.getPlanteriorId();
		this.conditionContent = dto.getConditionContent();
		this.stateContent = dto.getStateContent();
		return this;
	}

}
