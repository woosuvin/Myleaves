package com.itwill.myleaves.repository.uploadfile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UploadFileRepository extends JpaRepository<UploadFile, String> {

	// nid로 해당 파일 모두 찾기
	public List<UploadFile> findByNidOrderByFileOrderAsc(Long nid);
	
	// ufid로 해당 파일 찾기
	@Query(
		value = 
		"SELECT * FROM UPLOAD_FILE WHERE UFID = :ufid",
		nativeQuery = true
	)
	public UploadFile findByUfid(@Param("ufid") String ufid);
	
	// 가지고 있는 fileOrder에서 가장 큰 값
	public UploadFile findTopByNidOrderByFileOrderDesc(Long nid);
}
