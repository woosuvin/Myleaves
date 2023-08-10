package com.itwill.myleaves.repository.qna;





import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface QnARepository extends JpaRepository<QnA, Long> {
	/*
     * 페이징 만들기
     */
    // 검색과 페이징을 동시에 처리하는 메소드
    Page<QnA> findAll(Pageable pageable);
    
	
	/*
	 * QnA 전체 리스트 검사
	 */
    List<QnA> findByOrderByQidDesc();
    
    /*
	 * QnA 마이페이지 리스트 검사
	 */
    Page<QnA> findAllByUserIdOrderByQidDesc(String userId , Pageable pageable);
   
    /*
	 * QnA 검색
	 */
    
    // 제목
    Page<QnA> findByTitleContainsIgnoreCaseOrderByQidDesc(String title, Pageable pageable);
    
    // 내용
    Page<QnA> findByContentContainsIgnoreCaseOrderByQidDesc(String content, Pageable pageable);
    
    // 작성자
    Page<QnA> findByUserIdContainsIgnoreCaseOrderByQidDesc(String userId, Pageable pageable);
    
    // 제목 또는 내용
    Page<QnA> findByTitleContainsIgnoreCaseOrContentContainsIgnoreCaseOrderByQidDesc(String title, String content, Pageable pageable);
    
    @Query(
	        "select q from QnA q" + 
            " where lower(q.title) like lower('%' || :keyword || '%') " +
            " or lower(q.content) like lower('%' || :keyword || '%') "  +
            " order by q.qid desc"
			)
    Page<QnA> searchBykeyword(@Param("keyword") String keyword, Pageable pageable);

	
    
  


   
   
}
