package com.Springboot.Vue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Springboot.Vue.Mapper.eqpAttackMapper;
import com.Springboot.Vue.entity.equipmentAttack;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class eqpAttackController {
	@Autowired
	eqpAttackMapper attackMapper;
	
	@ResponseBody
	@CrossOrigin
	@GetMapping("/getAllEquip")
	public JSONArray getAllEquip() {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		List<equipmentAttack> list = attackMapper.getAllEquipAttack();
		for(int i=0;i<list.size();i++) {
			object.put("eqp_name", list.get(i).getEqp_name());
			object.put("eqp_money", list.get(i).getEqp_money());
			object.put("eqp_attack", list.get(i).getEqp_attack());
			object.put("eqp_magic", list.get(i).getEqp_magic());
			object.put("eqp_crit", list.get(i).getEqp_crit());
			object.put("eqp_boolean_compound", list.get(i).getEqp_boolean_compound());
			object.put("eqp_suck_blood", list.get(i).getEqp_suck_blood());
			object.put("eqp_magic_blood", list.get(i).getEqp_magic_blood());
			object.put("eqp_physics_penetration", list.get(i).getEqp_physics_penetration());
			object.put("eqp_magic_penetration", list.get(i).getEqp_magic_penetration());
			array.add(object);
		}
		return array;
	}
	
	@ResponseBody
	@CrossOrigin
	@PostMapping("/insertEqpAttack")
	public Integer insertEqpAttack(@RequestBody String eqpAttackInfo) {
		equipmentAttack eqpAttack = (equipmentAttack) JSONObject.toBean(JSONObject.fromObject(eqpAttackInfo), equipmentAttack.class);
		Integer i = attackMapper.insertEquipAttackInfo(eqpAttack);
		return i;
	}
}
