package com.itwill.myleaves.repository.faq;

import com.itwill.myleaves.dto.faq.FaQUpdateDto;
import com.itwill.myleaves.repository.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;import jakarta.persistence.GenerationType;
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
@Table(name="INFO_FAQ")
@SequenceGenerator(name = "INFO_FAQ_SEQ_GEN" , sequenceName = "INFO_FAQ_SEQ", allocationSize = 1)
public class FaQ {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INFO_FAQ_SEQ_GEN")
	private long fqid;
	
	@Column(nullable = false)
	private String question;
	
	@Column(nullable = false)
	private String answer;
	
	
	public FaQ update(FaQUpdateDto dto) { 
		this.question = dto.getQuestion();
		this.answer = dto.getAnswer(); 
		
		return this;
	} 
}
