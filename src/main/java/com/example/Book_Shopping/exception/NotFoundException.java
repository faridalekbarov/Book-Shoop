package com.example.Book_Shopping.exception;

public class NotFoundException extends RuntimeException {

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

	public NotFoundException(String message) {
		super(message);
		this.message = message;
	}
}
