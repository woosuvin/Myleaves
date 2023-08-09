package com.itwill.myleaves.repository.uploadfile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.myleaves.dto.uploadFile.UploadFileReadDto;

public interface UploadFileReadRepository extends JpaRepository<UploadFileReadDto, String> {

	// nid로 해당 파일 모두 찾기
	public List<UploadFileReadDto> findByNidOrderByFileOrderAsc(Long nid);
}
