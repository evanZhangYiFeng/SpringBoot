package com.Springboot.Vue.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.Springboot.Vue.entity.equipmentAttack;

@Mapper
public interface eqpAttackMapper {
	public List<equipmentAttack> getAllEquipAttack();
	
	public Integer insertEquipAttackInfo(equipmentAttack eqpAttack);
}
