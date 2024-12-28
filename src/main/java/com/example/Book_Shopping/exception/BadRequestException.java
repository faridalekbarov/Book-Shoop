package com.example.Book_Shopping.exception;

public class BadRequestException extends RuntimeException {
	
	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;
	private String message;

	public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

}