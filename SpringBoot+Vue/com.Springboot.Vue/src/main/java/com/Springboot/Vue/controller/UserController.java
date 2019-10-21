package com.Springboot.Vue.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Springboot.Vue.Mapper.UserMapper;
import com.Springboot.Vue.common.JsonResult;
import com.Springboot.Vue.entity.User;

@Controller
public class UserController {
	@Autowired
	UserMapper userMapper;
	
	@ResponseBody      //将value返回给界面
	@GetMapping(value = "/api/login")
	@CrossOrigin     //用来处理跨域请求的注解
	public JsonResult login(String name,String password) {
		if(StringUtils.isEmpty(name)) {
			return new JsonResult(false, "用户名不能为空1");
		}else if (StringUtils.isEmpty(password)) {
			return new JsonResult(false, "密码不能为空1");
		}
		
		User user = userMapper.find(name, password);
		if(user == null) {
			return new JsonResult(false, "用户名或者密码错误");
		}
		JsonResult jsonResult = new JsonResult(true,name,password);
		//jsonResult.put(name, password);
		System.out.println("jsonResult里面的元素:"+jsonResult);
		return jsonResult;
	}
}
