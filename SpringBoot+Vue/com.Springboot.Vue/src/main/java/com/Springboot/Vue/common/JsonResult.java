package com.Springboot.Vue.common;

import static org.mockito.Matchers.anyMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonResult implements Serializable {
	private static final int ACCESS_TOKEN_INVALID = 50;
	private static final long serialVersionUID = 7911919307011379110L;
	private Integer code;
	private Boolean success;
	private String msg;
	//private Object data;
	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public JsonResult(Boolean success) {
		super();
		this.success = success;
	}
	public JsonResult(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	public JsonResult(Boolean success, String name,String password) {
		super();
		this.success = success;
		this.name = name;
		this.password = password;
	}

	/*
	 * public void put(String name,String password) { Map<String, String> map = new
	 * HashMap<String, String>(); map.put(name, password); System.out.println(map);
	 * }
	 */
	@Override
	public String toString() {
		/*
		 * JSONObject jsonObject = new JSONObject(); JSONArray array = new JSONArray();
		 * jsonObject.put("name", name); jsonObject.put("password", password);
		 * array.add(jsonObject); System.out.println(array);
		 */
		return "[code=" + code + ", success=" + success + ", msg=" + msg + ",name=" + name + ",password=" + password + "]";
	}
	
	/*
	 * public void put(Object key,Object value) { if(data == null) { data = new
	 * HashMap<Object, Object>(); } ((HashMap) data).put(key,value);
	 * System.out.println("map集合元素:"+data); //((Map) data).put(key, value); }
	 */
	/*
	 * public void putAll(Map<Object, Object> map) { if (data == null) { data = new
	 * HashMap<Object,Object>(); } ((Map) data).putAll(map); }
	 */
	
	
	
}
