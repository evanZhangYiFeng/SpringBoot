package com.springboot.asdCopy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.asdCopy.entity.User;

@Mapper
public interface UserMapper {
	public User findUser(@Param("name") String name,@Param("password") String password);
}
