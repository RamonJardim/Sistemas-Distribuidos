package models;

import java.io.Serializable;
import java.util.Date;

public class Metadata implements Serializable {
	private String fileName;
	private Date creationTime;
	private Date lastModifiedTime;
	private long fileSize;
	private String fileExtension;

	public Metadata(String fileName, Date creationTime, Date lastModifiedTime, long fileSize, String fileExtension) {
		this.fileName = fileName;
		this.creationTime = creationTime;
		this.lastModifiedTime = lastModifiedTime;
		this.fileSize = fileSize;
		this.fileExtension = fileExtension;
	}

	public String getFileName() {
		return fileName;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getFileExtension() {
		return fileExtension;
	}
}