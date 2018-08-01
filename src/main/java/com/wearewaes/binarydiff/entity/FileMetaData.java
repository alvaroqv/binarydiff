package com.wearewaes.binarydiff.entity;

import java.io.Serializable;

public class FileMetaData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 316917917981433196L;
	
	private String id;
	private String fileName;
	private String filePath;
	private String fileType;
	private long size;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	

}
