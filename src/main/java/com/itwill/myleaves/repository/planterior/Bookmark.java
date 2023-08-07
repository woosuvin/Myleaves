package com.itwill.myleaves.repository.planterior;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "BOOKMARK")
@SequenceGenerator(name = "BOOKMARK_SEQ_GEN", sequenceName = "BOOKMARK_SEQ", allocationSize = 1)
// create,delete만 존재하는 클래스
public class Bookmark {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKMARK_SEQ_GEN" )
	private Long BId;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private Long planteriorId;

}
