package com.itwill.myleaves.repository.community;


import com.itwill.myleaves.dto.community.CommunityUpdateDto;
import com.itwill.myleaves.repository.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import groovy.transform.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString


@Entity
@SequenceGenerator(name = "COMMUNITY_SEQ_GEN", sequenceName = "COMMUNITY_SEQ", allocationSize = 1)
public class Community extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMUNITY_SEQ_GEN")
	@Column(nullable = false)
	private Long communityId;
	
	@Column(nullable = true)
	private String userId;
	
	@Column(nullable = true)
	private String title;
	
	@Column(nullable = true)
	private String content;
	
	@Column(nullable = true)
	private int views;
	
	@Column(nullable = true)
	private String hrsHd;
	
	// Community 엔터티의 title과 content를 수정해서 리턴하는 메서드(setter 메서드 두개의 역할)
	public Community update(CommunityUpdateDto dto) {
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.hrsHd = dto.getHrsHd();
		
		return this;
	}

	
}
