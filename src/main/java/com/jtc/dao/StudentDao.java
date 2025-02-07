package com.jtc.dao;

import java.util.List;

import com.jtc.entity.Student;

public interface StudentDao {
	
	public int saveStudent(Student student);
	public Student getStudentById(int id);
	public List<Student> getAllStudent();
	public void update(Student student);
	public void delete(String id);
	Student getStudentByEmail(String email);

}
