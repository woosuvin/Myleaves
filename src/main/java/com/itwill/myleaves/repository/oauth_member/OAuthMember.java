package com.itwill.myleaves.repository.oauth_member;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "OAUTH_USER_INFO_SEQ_GEN", sequenceName = "OAUTH_USER_INFO_SEQ", allocationSize = 1)
@Table(name = "OAUTH_USER_INFO")
public class OAuthMember {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OAUTH_USER_INFO_SEQ_GEN")
    private Long id;

    private String email;

    private String nickname;

    private OAuthProvider oAuthProvider;
    
    @Column(nullable = true)
	@CreationTimestamp
	private LocalDateTime joinDate;

    @Builder
    public OAuthMember(String email, String nickname, OAuthProvider oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }
}