package com.springboot.asdCopy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.asdCopy.entity.Robot;
import com.springboot.asdCopy.mapper.robotMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class robotController {
	@Autowired
	robotMapper mapper;
	
	@CrossOrigin
	@ResponseBody
	@GetMapping("/robot/getLocationInfo")
	public List<Robot> getAllInfo(){
//		System.out.println(mapper.getAllInfo());
		return mapper.getAllInfo();
	}
	
	
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/robot/updateButtonStatus")
	public int updateButtonStatus(@RequestBody String buttonObj) {
		System.out.println(buttonObj);
		JSONArray arr = JSONArray.fromObject(buttonObj);
		JSONObject object = arr.getJSONObject(0);
		return mapper.updateButtonStatus(object.get("name").toString(),object.get("status").toString());
	}
	
	@CrossOrigin
	@ResponseBody
	@PostMapping("/robot/selectButtonStatus")
	public String getButtonStatus(@RequestBody String objString) {
		JSONArray arr = JSONArray.fromObject(objString);
		JSONObject object = arr.getJSONObject(0);
		System.out.println(object.get("name").toString());
		String status = mapper.getButtonStatus(object.get("name").toString());
		return status;
	}
}
