package com.wearewaes.binarydiff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7742609508098045414L;

	 public FileNotFoundException(String message) {
	        super(message);
	    }

	    public FileNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }

}
