package com.Springboot.Vue.entity;

public class equipmentType {
	private Integer eqp_id;
	private String eqp_type_name;
	private Integer eqp_attack_defense;
	public Integer getEqp_id() {
		return eqp_id;
	}
	public void setEqp_id(Integer eqp_id) {
		this.eqp_id = eqp_id;
	}
	public String getEqp_type_name() {
		return eqp_type_name;
	}
	public void setEqp_type_name(String eqp_type_name) {
		this.eqp_type_name = eqp_type_name;
	}
	public Integer getEqp_attack_defense() {
		return eqp_attack_defense;
	}
	public void setEqp_attack_defense(Integer eqp_attack_defense) {
		this.eqp_attack_defense = eqp_attack_defense;
	}
	@Override
	public String toString() {
		return "equipmentType [eqp_id=" + eqp_id + ", eqp_type_name=" + eqp_type_name + ", eqp_attack_defense="
				+ eqp_attack_defense + "]";
	}
	
}
