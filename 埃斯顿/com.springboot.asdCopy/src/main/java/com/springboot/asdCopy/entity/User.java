package com.springboot.asdCopy.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String name;
	private String password;
	private String active_time;
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}
}
