package com.Springboot.Vue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Springboot.Vue.Mapper.heroPropertyMapper;
import com.Springboot.Vue.entity.heroProperty;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class heroController {
	@Autowired
	heroPropertyMapper heroMapper;
	
	//获得所有英雄列表
	@ResponseBody
	@CrossOrigin
	@GetMapping("/getAllHero")
	public List<heroProperty> getAllHero() {
		/*
		 * JSONArray array = new JSONArray(); JSONObject object = new JSONObject();
		 */
		List<heroProperty> list = heroMapper.getAllHero();
		/*
		 * for(int i=0;i<list.size();i++) { object.put("id", list.get(i).getId());
		 * object.put("name", list.get(i).getName()); array.add(object); }
		 */
		 
		return list;
	}
	
	//新增英雄
	@ResponseBody
	@CrossOrigin
	@PostMapping("/insertHero")
	public Integer insertHero(@RequestBody String heroString) {
		System.out.println(heroString);
		heroProperty hero = (heroProperty) JSONObject.toBean(JSONObject.fromObject(heroString), heroProperty.class);
		return heroMapper.addHero(hero);
	}
	
	//英雄购买装备后更新英雄属性
	@ResponseBody
	@CrossOrigin
	@PutMapping("/updateHeroInfo")
	public Integer updateHeroInfo(@RequestBody String heroString) {
		System.out.println(heroString);
		heroProperty hero = (heroProperty) JSONObject.toBean(JSONObject.fromObject(heroString), heroProperty.class);
		return heroMapper.updateHeroInfo(hero);
	}
}
