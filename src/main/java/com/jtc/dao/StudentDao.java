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
	//admin access
	void setAdminAccess(String email, boolean isAdmin);
	
	Student getStudentByEmailAndPassword(String email, String password);
    List<Student> getAllStudents();
    void updateStudent(Student student);
    void deleteStudent(int id);
	public String generateStudentId();
	
	//give access as a admin
	//UPDATE students SET isAdmin = true WHERE email = 'Pdipankar28@gmail.com';


}
