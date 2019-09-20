package com.springboot.service;

import java.util.List;

import com.springboot.entity.student;

public interface studentService {
	public List<student> getStudentInfo();
	//根据姓名查询学生
	public student getStudentByName(String name);
	//新增学生信息
	public Integer addStudent(student stu);
	//修改信息
	public int changeInfoByName(student stu);
	//删除学生信息
	public int deleteInfoByName(String name);
}
