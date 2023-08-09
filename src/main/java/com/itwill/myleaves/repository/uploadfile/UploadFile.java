package com.itwill.myleaves.repository.uploadfile;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "UPLOAD_FILE")
public class UploadFile {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private String ufid;
	
	private String fileName;
	
	@Lob
	private byte[] atchdFile;
	
	@ColumnDefault("0")
	private int fileOrder;
	
	private Long nid;
	
	private String type;
	
	public UploadFile(String fileName, String type, byte[] atchdFile, Long nid, int fileOrder) {
		this.fileName = fileName;
		this.type = type;
		this.atchdFile = atchdFile;
		this.nid = nid;
		this.fileOrder = fileOrder;
	}
	
	
}
