package com.abnamro.receipes.exception;

import lombok.Data;

/**
 * @author : Ashu Mahajan This class binds the error details
 */
@Data
public class ErrorMessage {
	private String msg;
	private int code;

	public ErrorMessage(String error, int code) {
		this.msg = error;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
