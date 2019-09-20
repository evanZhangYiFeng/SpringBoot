package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springboot.entity.student;
import com.springboot.service.studentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class studentController {
	@Autowired
	studentService stuService;

	@RequestMapping("/getStudentInfo")
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
}
