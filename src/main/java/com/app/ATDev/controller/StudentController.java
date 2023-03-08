package com.app.ATDev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ATDev.entity.Student;
import com.app.ATDev.exception.ResourceNotFoundException;
import com.app.ATDev.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	
	private StudentService studentService;
	
	@GetMapping("")
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id){
		Optional<Student> student = studentService.getStudentById(id);
		
		if(student.isPresent()) {
			return ResponseEntity.ok(student.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student student) throws ResourceNotFoundException{
		Student updateStudent = studentService.updateStudent(id, student);
		return ResponseEntity.ok(updateStudent);
	}
	
	@PostMapping("")
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		Student createStudent = studentService.createStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(createStudent);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
		studentService.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}
}










