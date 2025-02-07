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

		int i = (int) hibernateTemplate.save(student);

		return i;
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

}
