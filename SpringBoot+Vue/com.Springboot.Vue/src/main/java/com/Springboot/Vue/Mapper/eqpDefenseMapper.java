package com.Springboot.Vue.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.Springboot.Vue.entity.equipmentDefense;

@Mapper
public interface eqpDefenseMapper {
	public List<equipmentDefense> getAllEqpDefense();
	
	public Integer insertEqpDefense(equipmentDefense equipmentDefense);
}
