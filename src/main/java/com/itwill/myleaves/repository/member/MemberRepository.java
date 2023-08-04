package com.itwill.myleaves.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>{

	Member findByUserId(String userId);

	Member findByNameAndEmail(String name, String email);
}