package com.itwill.myleaves.repository.community;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long>{

	List<Community> findByOrderByCommunityIdDesc();
}
