package com.itwill.myleaves.repository.sellbuy;

import com.itwill.myleaves.dto.sellbuy.SellUpdateDto;
import com.itwill.myleaves.repository.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity //JPA 엔터티 클래스- 데이터 베이스 테이블과 매핑되는 클래스
@Table(name = "SELL") // 엔터티 클래스 이름이 데이터 베이스 테이블 이름과 다른 경우, 테이블 이름을 명시/
@SequenceGenerator(name = "SELL_SEQ_GEN", sequenceName = "SELL_SEQ", allocationSize = 1)
public class Sell extends BaseTimeEntity {
//지현
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SELL_SEQ_GEN" )
	private Long sellId;
	
	// 작성자
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = true)
	private String title;
	
	@Column(nullable = true)
	private byte[] thumbnail;
	
	@Column(nullable = true)
	private String content;
	
	@Column(nullable = true)
	private Long price;
	
	@Column(nullable = true)
	private String sido;
	
	
	@Column(nullable = true)
	private String gungu;
	
	@Column(nullable = true)
	private String loc;
	
	@Column(nullable = true)
	private Long sold;
	
	public Sell update(SellUpdateDto dto) {
		this.title = dto.getTitle();
		this.thumbnail = dto.getThumbnail();
		this.content = dto.getContent();
		this.price = dto.getPrice();
		this.sido = dto.getSido();
		this.gungu = dto.getGungu();
		this.loc = dto.getLoc();
		return this;
	}
	
}
