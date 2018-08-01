package com.wearewaes.binarydiff.dto;

import java.io.Serializable;
/**
 * DTO with the files from LEFT and RIGHT folder comparison result
 * @author Alvaro
 *
 */
public class FileDiffResponseDto implements Serializable{

	private static final long serialVersionUID = -4596930066738260594L;
	/**
	 * File identification
	 */
	private String fileId;
	/**
	 * Inform the files are equal. Same HASH with MD5
	 */
	private boolean filesAreEqual;
	/**
	 * Same number of byte[]
	 */
	private boolean sameSize;
	/**
	 * File size 
	 */
	private long sizeFileLeft;
	/**
	 * File size 
	 */
	private long sizeFileRight;
	
	public FileDiffResponseDto(){}
	
	public FileDiffResponseDto(String fileId, boolean isEqual, boolean sameSize, long sizeFileLeft,
			long sizeFileRight) {
		super();
		this.fileId = fileId;
		this.filesAreEqual = isEqual;
		this.sameSize = sameSize;
		this.sizeFileLeft = sizeFileLeft;
		this.sizeFileRight = sizeFileRight;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public boolean isSameSize() {
		return sameSize;
	}
	public void setSameSize(boolean sameSize) {
		this.sameSize = sameSize;
	}
	public long getSizeFileLeft() {
		return sizeFileLeft;
	}
	public void setSizeFileLeft(long sizeFileLeft) {
		this.sizeFileLeft = sizeFileLeft;
	}
	public long getSizeFileRight() {
		return sizeFileRight;
	}
	public void setSizeFileRight(long sizeFileRight) {
		this.sizeFileRight = sizeFileRight;
	}
	public boolean isFilesAreEqual() {
		return filesAreEqual;
	}

	public void setFilesAreEqual(boolean filesAreEqual) {
		this.filesAreEqual = filesAreEqual;
	}

}
