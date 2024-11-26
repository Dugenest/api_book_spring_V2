package com.societe.exception;

public class DuplicateAuthorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	 public DuplicateAuthorException(String message) {
	        super(message);
	    }
}
