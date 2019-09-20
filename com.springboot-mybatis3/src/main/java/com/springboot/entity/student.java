package com.springboot.entity;

public class student {
	private String name;
	private String math;
	private String chinese;
	private String language;	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMath() {
		return math;
	}
	public void setMath(String math) {
		this.math = math;
	}
	public String getChinese() {
		return chinese;
	}
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "student [name=" + name + ", math=" + math + ", chinese=" + chinese + ", language=" + language + "]";
	}
}
