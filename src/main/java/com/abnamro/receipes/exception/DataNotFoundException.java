package com.abnamro.receipes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Ashu Mahajan This class handles the data not found errors
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends Throwable {
	public DataNotFoundException() {
		super();
	}
}
