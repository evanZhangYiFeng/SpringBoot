package com.Springboot.Vue.Mapper;

import org.apache.ibatis.annotations.Mapper;

import com.Springboot.Vue.entity.heroMoney;

@Mapper
public interface heroMoneyMapper {
	//更新金币
	public Integer updateHeroMoney(heroMoney hero);
	
}
