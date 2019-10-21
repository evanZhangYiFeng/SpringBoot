package com.Springboot.Vue.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.Springboot.Vue.entity.equipmentType;

@Mapper
public interface eqpTypeMapper {
	//获得攻击装备类型
	public List<equipmentType> getEqpTypes();
	
	//获得防御装备类型
	public List<equipmentType> getEqpTypesDefense();
	
	//插入装备类型
	public Integer insertEqpTypes(equipmentType eqpTyps);
}
