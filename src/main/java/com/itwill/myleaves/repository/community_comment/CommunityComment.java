package com.itwill.myleaves.repository.community_comment;

import com.itwill.myleaves.repository.BaseTimeEntity;
import com.itwill.myleaves.repository.community.Community;

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

@NoArgsConstructor
@Getter

@AllArgsConstructor
@Builder
@Entity
// @Table(name = "COMMUNITY_COMMENT")	
@SequenceGenerator(name = "COMMUNITY_COMMENT_SEQ_GEN", sequenceName = "COMMUNITY_COMMENT_SEQ", allocationSize = 1 )
public class CommunityComment extends BaseTimeEntity {

	@Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "COMMUNITY_COMMENT_SEQ_GEN")   
	@Column(nullable = false)
	private Long communityCommentId;  // 댓글 ID 
	
//	private Community community; // 커뮤니티 ID
//	private Long communityId;
	@JoinColumn(name = "community_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Community communityId;
	
	private String content;
	
	private String userId;
	
	// 댓글 내용을 수정하고, 수정된 엔터티를 리턴하는 메서드 
	public CommunityComment update(String content) {
		this.content = content;
		return this;
	}
}
