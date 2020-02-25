package com.springboot.asdCopy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.asdCopy.entity.Robot;


@Mapper
public interface robotMapper {
	//返回所有的坐标信息
	public List<Robot> getAllInfo();

	public int updateButtonStatus(@Param("name") String name, @Param("status") String status);
	
	public String getButtonStatus(@Param("name") String name);
}
