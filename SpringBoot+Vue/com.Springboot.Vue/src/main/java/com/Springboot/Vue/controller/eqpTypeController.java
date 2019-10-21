package com.Springboot.Vue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Springboot.Vue.Mapper.eqpTypeMapper;
import com.Springboot.Vue.entity.equipmentType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class eqpTypeController {
	@Autowired
	eqpTypeMapper eqpMapper;
	
	@ResponseBody
	@GetMapping("/getAllTypes")
	@CrossOrigin
	public JSONArray getAllEqpTypes(){
		List<equipmentType> list = eqpMapper.getEqpTypes();
		JSONArray array = getTypes(list);
		return array;
	}
	@ResponseBody
	@GetMapping("/getEqpTypesDefense")
	@CrossOrigin
	public JSONArray getEqpTypesDefense(){
		List<equipmentType> list = eqpMapper.getEqpTypesDefense();
		JSONArray array = getTypes(list);
		return array;
	}
	
	//返回封装好的json对象数组
	public JSONArray getTypes(List<equipmentType> list) {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		for(int i=0;i<list.size();i++) {
			object.put("eqp_id", list.get(i).getEqp_id());
			object.put("eqp_name", list.get(i).getEqp_type_name());
			object.put("eqp_attack_defense", list.get(i).getEqp_attack_defense());
			array.add(object);
		}
		return array;
	}
	
	
	@ResponseBody
	@PostMapping("/insertEqpTypes")
	@CrossOrigin
	public Integer insertEqpTypes(@RequestBody String eqpType) {
		equipmentType type = (equipmentType) JSONObject.toBean(JSONObject.fromObject(eqpType), equipmentType.class);
		return eqpMapper.insertEqpTypes(type);
	}
}
