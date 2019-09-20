package com.springboot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.springboot.entity.student;

@Mapper
public interface studentMapper {
	//根据姓名查询学生
	public student getStudentByName(String name);
	//查询所有
	public List<student> getStudentInfo();
	//新增学生信息
	public Integer addStudent(student stu);
	//修改信息
	public int changeInfoByName(student stu);
	//删除学生信息
	public int deleteInfoByName(String name);
}
