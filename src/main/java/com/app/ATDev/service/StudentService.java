package com.app.ATDev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ATDev.entity.Student;
import com.app.ATDev.exception.ResourceNotFoundException;
import com.app.ATDev.repo.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public Optional<Student> getStudentById(Long id){
		return studentRepository.findById(id);
	}
	
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
	
	 public Student updateStudent(Long id, Student student) throws ResourceNotFoundException {
	        Optional<Student> existingStudent = studentRepository.findById(id);
	        
	        if (existingStudent.isPresent()) {
	            student.setId(id);
	            return studentRepository.save(student);
	        }
	        else {
	        	throw new ResourceNotFoundException("student with","student id" , id);
	        }
	    }
	 
	 public void deleteStudent(Long id) {
	        studentRepository.deleteById(id);
	    }
}
