package com.itwill.myleaves.repository.oauth_member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthMemberRepository extends JpaRepository<OAuthMember, String> {

	Optional<OAuthMember> findByEmail(String email);
}