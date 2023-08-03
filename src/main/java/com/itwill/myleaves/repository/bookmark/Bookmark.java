package com.itwill.myleaves.repository.bookmark;

import com.itwill.myleaves.repository.Planterior.Planterior;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

// create,delete만 존재하는 클래스
public class Bookmark {
	
	@Id
	private String userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Planterior platerior;

}
