package com.Springboot.Vue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Springboot.Vue.Mapper.heroMoneyMapper;
import com.Springboot.Vue.entity.heroMoney;

import net.sf.json.JSONObject;

@Controller
public class heroMoneyController {
	@Autowired
	heroMoneyMapper mapper;
	
	@ResponseBody
	@CrossOrigin
	@PutMapping("/updateHeroMoney")
	public Integer updateHeroMoney(@RequestBody String msg) {
		heroMoney hero = (heroMoney) JSONObject.toBean(JSONObject.fromObject(msg), heroMoney.class);
		return mapper.updateHeroMoney(hero);
	}
}
