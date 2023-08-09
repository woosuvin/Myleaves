package com.itwill.myleaves.service.uploadfile;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.myleaves.dto.uploadFile.UploadFileReadDto;
import com.itwill.myleaves.repository.uploadfile.UploadFile;
import com.itwill.myleaves.repository.uploadfile.UploadFileReadRepository;
import com.itwill.myleaves.repository.uploadfile.UploadFileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadFileService {
	
	private final UploadFileRepository uploadFileRepository;
	private final UploadFileReadRepository readRepository;
	
	// 파일 저장
	public void save(MultipartFile file, Long nid, int i) throws IOException {
		log.info("UploadFileService save()");
		String fileName = file.getOriginalFilename();
		UploadFile uploadFile = new UploadFile(fileName, file.getContentType(), file.getBytes(), nid, i);

		uploadFileRepository.save(uploadFile);
	}
	
	// uploadfileDto 파일 리스트
	public List<UploadFileReadDto> read(Long nid) {
		List<UploadFileReadDto> list = readRepository.findByNidOrderByFileOrderAsc(nid);
		
		return list;
	}
	
	// uploadfile 파일 리스트
	public List<UploadFile> read1(Long nid) {
		List<UploadFile> list = uploadFileRepository.findByNidOrderByFileOrderAsc(nid);
		
		return list;
	}
	
	// 파일 하나 리턴
	public UploadFile readFile(String ufid) {
		System.out.println("service read ufid=" + ufid);
		return uploadFileRepository.findByUfid(ufid);
	}

	// 파일 하나 삭제
	public void deleteFile(String ufid) {
		log.info("ufid = {}", ufid);
		
		// 해당 파일 삭제
		uploadFileRepository.deleteById(ufid);
	}
	
	// 해당 nid에 있는 order가 가장 큰 값
	public int fileOrderValue(Long nid) {
		UploadFile file = uploadFileRepository.findTopByNidOrderByFileOrderDesc(nid);
		
		int result = 0;
		if(file != null) {
			result = file.getFileOrder();
		}
		
		return result;
	}
	

}
