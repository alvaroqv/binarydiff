package com.wearewaes.binarydiff.dto;

import java.io.Serializable;

public class FileDiffRequestDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3608031211022858556L;
	
	private String name;
	private String data;
	
	public FileDiffRequestDto(){};
	
	public FileDiffRequestDto(String name, String data) {
		super();
		this.name = name;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	

}
