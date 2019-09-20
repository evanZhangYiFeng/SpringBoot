package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.entity.student;
import com.springboot.service.studentService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller   
/*
 * 不使用@RestController,否则返回return后面的数据或者字符串，而不是html页面
 *  @RestController相当于, @Controller和@ResponseBody的组合注解
 */

public class studentController {
	@Autowired
	studentService stuService;
	
	@GetMapping("/all")
	public String getInfo(Model model) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		List<student> list = stuService.getStudentInfo();
		for(int i=0;i<list.size();i++) {
			if(list.get(i) != null) {
				obj.put("name", list.get(i).getName());
				obj.put("math",list.get(i).getMath());
				obj.put("language", list.get(i).getLanguage());
				obj.put("chinese", list.get(i).getChinese());
				arr.add(obj);
			}
		}
		model.addAttribute("stu", arr);
		return "student";
	}
	
	//新增学生信息
	@ResponseBody
	@PostMapping("insertStudent")
	public int addStudent(@RequestBody student stu) {
		student stude = (student) JSONObject.toBean(JSONObject.fromObject(stu),student.class);
		return stuService.addStudent(stude);
	}
	
	//跳转页面,更新学生信息
	@ResponseBody
	@PutMapping("studentChange")
	public int changeInfo(@RequestBody student stu) {
		System.out.println("需要修改的学生信息"+stu);
		student stude = (student) JSONObject.toBean(JSONObject.fromObject(stu),student.class);
		System.out.println("另外输出"+stude);
		return stuService.changeInfoByName(stu);
	}
	
	//删除学生信息
	@ResponseBody
	@DeleteMapping("studentDelete")
	public int deletStudent(@RequestBody String name) {
		System.out.println("前端传来的名字:"+name);
		return stuService.deleteInfoByName(name);
	}
}
