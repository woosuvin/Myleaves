package com.itwill.myleaves.repository.sellbuy;

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
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity //JPA 엔터티 클래스- 데이터 베이스 테이블과 매핑되는 클래스
@Table(name = "BUY") // 엔터티 클래스 이름이 데이터 베이스 테이블 이름과 다른 경우, 테이블 이름을 명시/
@SequenceGenerator(name = "BUY_SEQ_GEN", sequenceName = "BUY_SEQ", allocationSize = 1)
public class Buy {
// 지현
// 입양 테이블은 채팅창에서 판매자가 '거래 완료'로 상태 변경을 했을 때 insert됨.
// 만약 판매자가 채팅창에서 '거래중' 혹은 '판매중'으로 상태 변경 시 테이블에서 해당 데이터 삭제됨.
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUY_SEQ_GEN" )
	private Long buyId;
	
	@Column(nullable = false)
	private Long sellId;
	
	@Column(nullable = false)
	private String sellerId;
	// 판매자 ID(분양자)
	
	@Column(nullable = false)
	private String buyerId;
	// 구매자 ID(입양자)
	
}
