package com.itwill.myleaves.dto.uploadFile;

import org.hibernate.annotations.GenericGenerator;

import groovy.transform.builder.Builder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "UPLOAD_FILE")
public class UploadFileReadDto {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
//	@Column(columnDefinition = "BINARY(16)")
	private String ufid;
	
	private String fileName;
	
	private int fileOrder;
	
	private Long nid;	
    
}
