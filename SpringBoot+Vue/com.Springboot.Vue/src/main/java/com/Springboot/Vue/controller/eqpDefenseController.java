package com.Springboot.Vue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Springboot.Vue.Mapper.eqpDefenseMapper;
import com.Springboot.Vue.entity.equipmentDefense;

import net.sf.json.JSONObject;

@Controller
public class eqpDefenseController {
	@Autowired
	eqpDefenseMapper mapper;
	
	@ResponseBody
	@CrossOrigin
	@GetMapping("/getAllEqpDefense")
	public List<equipmentDefense> getAllEqpDefense(){
		List<equipmentDefense> list = mapper.getAllEqpDefense();
		return list;
	}
	
	@ResponseBody
	@CrossOrigin
	@PostMapping("/insertEqpDefense")
	public Integer insertEqpDefense(@RequestBody String msg) {
		equipmentDefense equipment = (equipmentDefense) JSONObject.toBean(JSONObject.fromObject(msg), equipmentDefense.class);
		return mapper.insertEqpDefense(equipment);
	}
}
