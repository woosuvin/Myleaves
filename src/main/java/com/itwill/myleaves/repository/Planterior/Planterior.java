package com.itwill.myleaves.repository.Planterior;

import org.hibernate.annotations.DialectOverride.GeneratedColumn;

import com.itwill.myleaves.dto.planterior.PlanteriorUpdateDto;
import com.itwill.myleaves.repository.BaseTimeEntity;
import com.itwill.myleaves.repository.sellbuy.Sell;

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
@Table(name = "PLANTERIOR")
@SequenceGenerator(name = "PLANTERIOR_SEQ_GEN", sequenceName = "PLANTERIOR_SEQ", allocationSize = 1)
public class Planterior extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANTERIOR_SEQ_GEN" )
	private Long planteriorId;
	
	// 작성자
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String plantName;
	
	@Column(nullable = false)
	private String plantNameEnglish;
	
	@Column(nullable = true)
	private String content;
	
	@Column(nullable = true)
	private byte[] thumbnail;
	
	public Planterior update(PlanteriorUpdateDto dto) {
		this.userId = dto.getUserId();
		this.plantName = dto.getPlantName();
		this.plantNameEnglish = dto.getPlantNameEnglish();
		this.thumbnail = dto.getThumbnail();
		return this;
				
	}

}
