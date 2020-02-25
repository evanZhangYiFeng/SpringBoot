package com.springboot.asdCopy.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.asdCopy.common.JsonResult;
import com.springboot.asdCopy.entity.User;
import com.springboot.asdCopy.mapper.UserMapper;


@Controller
public class UserController {
	@Autowired
	UserMapper mapper;
	
	@CrossOrigin
	@ResponseBody      //将value返回给界面
	@GetMapping(value = "/LOGON")
	public JsonResult findUser(String name,String password) {
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			return new JsonResult(false,"用户名和密码不能为空");
		}
		User user = mapper.findUser(name, password);
		if(user == null) {
			return new JsonResult(false,"用户名或者密码不存在");
		}
		String active_time = user.getActive_time();
		JsonResult json = new JsonResult(true,name,password,active_time);
		return json;
	}
}
