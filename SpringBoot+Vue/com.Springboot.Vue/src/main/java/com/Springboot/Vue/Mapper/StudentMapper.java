package com.Springboot.Vue.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.Springboot.Vue.entity.Student;

@Mapper
public interface StudentMapper {
	public List<Student> getAllStudent();
	
	public Student getStudentByName(String name);
	
	public Student getStudentById(Integer id);
	//插入学生信息
	public Integer insertStudent(List<Student> stus);
	//删除学生信息
	public Integer deleteStudent(Integer stuId);
	//修改学生信息
	public Integer updateStudent(Student stu);
}
