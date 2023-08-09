package com.itwill.myleaves.repository.notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.myleaves.dto.notice.NoticeReadInterface;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
	
	// id 내림차순 정렬:
	// SELECT * FROM POSTS ORDER BY ID DESC
	List<Notice> findByOrderByNidDesc();
	
	// id 내림차순 및 고정글:
	@Query(
		value = 
		"SELECT " +
		"ROW_NUMBER() OVER (ORDER BY n.CREATED_DATE DESC) AS rn" +
		", n.* " +
		"FROM INFO_NOTICE n " +
		"WHERE n.fix < 0 " +
		"ORDER BY n.CREATED_DATE DESC",
		nativeQuery = true
	)
	List<NoticeReadInterface> searchByFixNotice();
	
	
	@Query(
		value = 
		"WITH T AS ( " +
		"SELECT * FROM INFO_NOTICE n " +
		"WHERE n.fix = 0) " +
		"SELECT " +
		"ROW_NUMBER() OVER (ORDER BY T.CREATED_DATE DESC) AS rn" +
		", T.* " +
		"FROM T " +
		"ORDER BY T.CREATED_DATE DESC " +
		"OFFSET :offset ROWS FETCH NEXT :rows ROWS ONLY",
		nativeQuery = true
	)
	List<NoticeReadInterface> searchByNotFixNoticePaging(@Param("offset") int offset, @Param("rows") int rows);
	
	
	@Query(
		value = 
		"SELECT " +
		"ROW_NUMBER() OVER (ORDER BY n.CREATED_DATE DESC) AS rn" +
		", n.* " +
		"FROM INFO_NOTICE n " +
		"WHERE n.fix = 0 " +
		"ORDER BY n.CREATED_DATE DESC",
		nativeQuery = true
	)
	List<NoticeReadInterface> searchByFixNoticeAll();
	
	// 사용자 공지사항 view update
	@Modifying
	@Query(
		"UPDATE Notice n " +
		"SET n.views = n.views + 1 " +
		"WHERE n.nid = :nid"
	)
	int updateView(@Param("nid") Long nid);
}
