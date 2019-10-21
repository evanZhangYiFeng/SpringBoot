package com.Springboot.Vue.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.Springboot.Vue.entity.heroProperty;

@Mapper
public interface heroPropertyMapper {
	public List<heroProperty> getAllHero();
	
	//public heroProperty getHeroByName();
	
	//添加英雄
	public Integer addHero(heroProperty hero);
	
	//更新属性
	public Integer updateHeroInfo(heroProperty hero);
	
}	
