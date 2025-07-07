package com.githubrepo.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	
	private Integer errorCode;
	private String errorDesc;
	private Date date;

}
