package com.springboot.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.entity.student;
import com.springboot.mapper.studentMapper;
import com.springboot.service.studentService;

@Service
public class studentServiceImpl implements studentService {
	@Autowired
	studentMapper stuMapper;

	public List<student> getStudentInfo() {		
		return stuMapper.getStudentInfo();
	}
}
