package com.itwill.myleaves.repository.notice;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.itwill.myleaves.dto.notice.NoticeUpdateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "INFO_NOTICE")
@SequenceGenerator(name = "INFO_NOTICE_SEQ_GEN", sequenceName = "INFO_NOTICE_SEQ", allocationSize = 1)
public class Notice {
	
	@Id	// Primary key 제약조건
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="INFO_NOTICE_SEQ_GEN")
	private Long nid;
	
	@Column(nullable = false)	// Not Null 제약조건
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	@ColumnDefault(value= "0")
	private Long views;
	
	private int fix;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	private int rn;
	
	// Notice 엔터티의 title과 content를 수정해서 리턴하는 메서드
	public Notice update(NoticeUpdateDto dto) {
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.fix = dto.getFix();
	
		return this;
	}
	
}
