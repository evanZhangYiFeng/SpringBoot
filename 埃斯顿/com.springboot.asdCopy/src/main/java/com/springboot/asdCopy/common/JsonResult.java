package com.springboot.asdCopy.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResult {
	private boolean success;
	private Integer code;
	private String message;
	private String name;
	private String password;
	private String active_time;
	public JsonResult(boolean success, Integer code, String message, String name, String password) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
		this.name = name;
		this.password = password;
	}
	public JsonResult(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
	public JsonResult() {
		super();
	}
	@Override
	public String toString() {
		return "JsonResult [success=" + success + ", code=" + code + ", message=" + message + ", name=" + name
				+ ", password=" + password + "]";
	}
	public JsonResult(boolean success, String name, String password,String active_time) {
		super();
		this.success = success;
		this.name = name;
		this.password = password;
		this.active_time = active_time;
	}
	
}
