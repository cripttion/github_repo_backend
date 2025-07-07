package com.githubrepo.handler;

import lombok.Data;

@Data
public class InvalidInputException extends RuntimeException {
	
	public InvalidInputException(String message) {
		super(message);
	}

}
