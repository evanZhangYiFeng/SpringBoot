package com.springboot.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.entity.student;
import com.springboot.mapper.studentMapper;
import com.springboot.service.studentService;

@Service
public class studentServiceImpl implements studentService {
	@Autowired   //自动装配
	studentMapper stuMapper;

	public List<student> getStudentInfo() {		
		return stuMapper.getStudentInfo();
	}

	public student getStudentByName(String name) {
		return stuMapper.getStudentByName(name);
	}

	public Integer addStudent(student stu) {
		return stuMapper.addStudent(stu);
		
	}

	public int changeInfoByName(student stu) {
		return stuMapper.changeInfoByName(stu);
		
	}

	public int deleteInfoByName(String name) {
		return stuMapper.deleteInfoByName(name);
		
	}
}
