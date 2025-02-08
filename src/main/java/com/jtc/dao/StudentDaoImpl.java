package com.jtc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtc.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<Student> getAllStudent() {

		List<Student> all = hibernateTemplate.loadAll(Student.class);
		return all;
	}

	@Override
	@Transactional
	public void update(Student student) {

		hibernateTemplate.update(student);

	}

	@Override
	@Transactional
	public void delete(String id) {

		Student delete = hibernateTemplate.get(Student.class, id);

		hibernateTemplate.delete(delete);

	}

	@Override
	@Transactional
	public int saveStudent(Student student) {
		
		// Check if the email is already registered
	    Student existingStudent = getStudentByEmail(student.getEmail());
	    if (existingStudent != null) {
	        // Email already exists, return a custom error code or message
	        throw new IllegalArgumentException("Email is already registered.");
	    }
	    
	    // Generate student ID
	    String studentId = generateStudentId();
	    student.setStudentId(studentId);
		

		int i = (int) hibernateTemplate.save(student);

		return i;
	}
	
	
	public String generateStudentId() {
		String hql = "SELECT MAX(studentId) FROM Student";
        String lastStudentId = (String) hibernateTemplate.find(hql).get(0);
        
        if (lastStudentId != null) {
            int numberPart = Integer.parseInt(lastStudentId.substring(4));
            return "JTC-" + String.format("%03d", numberPart + 1);
        } else {
            return "JTC-001";
        }
	}
	
	

	@Override
	public Student getStudentById(int id) {

		Student student = hibernateTemplate.get(Student.class, id);
		return student;
	}
	
	@Override
    public Student getStudentByEmail(String email) {
        String hql = "FROM Student WHERE email = :email";
        List<Student> students = (List<Student>) hibernateTemplate.findByNamedParam(hql, "email", email);
        return students.isEmpty() ? null : students.get(0);
    }
	
	//admin 
	
	@Override
    public Student getStudentByEmailAndPassword(String email, String password) {
        String hql = "FROM students WHERE email = :email AND password = :password";
        List<Student> students = (List<Student>) hibernateTemplate.findByNamedParam(hql, 
            new String[]{"email", "password"}, new Object[]{email, password});
        return students.isEmpty() ? null : students.get(0);
    }
    
    @Override
    public List<Student> getAllStudents() {
        return hibernateTemplate.loadAll(Student.class);
    }
    
    @Override
    @Transactional
    public void updateStudent(Student student) {
        hibernateTemplate.update(student);
    }
    
    @Override
    @Transactional
    public void deleteStudent(int id) {
        Student student = hibernateTemplate.get(Student.class, id);
        if (student != null) {
            hibernateTemplate.delete(student);
        }
    }
	

    @Override
    @Transactional
    public void setAdminAccess(String email, boolean isAdmin) {
        Student student = getStudentByEmail(email);
        if (student != null) {
            student.setAdmin(isAdmin);
            hibernateTemplate.update(student);
        }
    }
    
    
}
