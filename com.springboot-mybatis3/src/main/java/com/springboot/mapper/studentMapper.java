package com.springboot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.springboot.entity.student;

@Mapper
public interface studentMapper {
	public List<student> getStudentInfo();
}
