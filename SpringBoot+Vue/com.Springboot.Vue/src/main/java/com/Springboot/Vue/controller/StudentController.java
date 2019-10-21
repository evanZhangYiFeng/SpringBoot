package com.Springboot.Vue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Springboot.Vue.Mapper.StudentMapper;
import com.Springboot.Vue.entity.Student;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
public class StudentController {
	@Autowired
	StudentMapper mapper;
	
	@ResponseBody
	@GetMapping("/getAll")
	@CrossOrigin
	public JSONArray getAllStudent() {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		List<Student> list = mapper.getAllStudent();
		for(int i=0;i<list.size();i++) {
			object.put("stuId", list.get(i).getStuId());
			object.put("stuName", list.get(i).getStuName());
			object.put("stuSex", list.get(i).getStuSex());
			object.put("stuEmail", list.get(i).getStuEmail());
			object.put("stuAddress", list.get(i).getStuAddress());
			array.add(object);
		}
		/* System.out.println(array); */
		return array;
	}
	
	@ResponseBody
	@GetMapping("/getStudentById")
	@CrossOrigin
	public JSONArray getStudentById(String idOrStuName) {
		JSONArray array = returnData(idOrStuName);
		return array;
	}
	
	//将查询的数据转为json数组返回
	public JSONArray returnData(String idOrStuName) {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		Integer id = null;
		Student stu = null;
		try {
			id = Integer.valueOf(idOrStuName);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if(id != null) {
			stu = mapper.getStudentById(id);
		}else {
			stu = mapper.getStudentByName(idOrStuName);
		}
		
		if(stu != null) {
			object.put("stuId", stu.getStuId());
			object.put("stuName", stu.getStuName());
			object.put("stuSex", stu.getStuSex());
			object.put("stuEmail", stu.getStuEmail());
			object.put("stuAddress", stu.getStuAddress());
			array.add(object);
			return array;
		}else {
			return null;
		}
	}
	
	//插入学生信息
	@ResponseBody
	@PostMapping("/insertStudent")
	@CrossOrigin
	public Integer insertStudent(@RequestBody String stu) {
		System.out.println("前端传来的数组对象："+stu);
		//将json字符串转为json数组
		JSONArray array = JSONArray.fromObject(stu);
		List<Student> students = array.toList(array, Student.class);
		//System.out.println(array);
		/*
		 * for(int i=0;i<array.size();i++) { JSONObject object = (JSONObject)
		 * array.get(i); System.out.println("解析后的json对象是:"+object); }
		 * 
		 * Student student = (Student)
		 * JSONObject.toBean(JSONObject.fromObject(stu),Student.class); Student stu1 =
		 * mapper.getStudentById(student.getStuId()); //获得Id if(stu1 != null) { return
		 * 0; //如果传来的stuId在数据库已存在，则返回前端2告诉他该stuId已经存在 }
		 */
		return mapper.insertStudent(students);
	}
	
	//删除学生信息
	@ResponseBody
	@DeleteMapping("/deleteStudent")
	@CrossOrigin
	public Integer deleteStudent(@RequestBody String stuId) {
		System.out.println(stuId);
		Integer id = Integer.valueOf(stuId);
		return mapper.deleteStudent(id);
	}
	
	//更新学生信息
	@ResponseBody
	@PutMapping("/updateStudent")
	@CrossOrigin
	public Integer updateStudent(@RequestBody String stu) {
		System.out.println(stu);
		Student student = (Student) JSONObject.toBean(JSONObject.fromObject(stu),Student.class);
		System.out.println("需要修改信息的学生信息:"+student);
		return mapper.updateStudent(student);
	}
}