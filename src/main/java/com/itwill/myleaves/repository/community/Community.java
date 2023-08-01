package com.itwill.myleaves.repository.community;


import com.itwill.myleaves.repository.BaseTimeEntity;

import groovy.transform.ToString;
import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
	
	
}
