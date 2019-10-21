package com.Springboot.Vue.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.Springboot.Vue.entity.User;

@Mapper
public interface UserMapper {
	public User find(@Param("name") String name,@Param("password") String password);
}
