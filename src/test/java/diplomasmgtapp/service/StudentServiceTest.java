package diplomasmgtapp.service;

import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Student;
import diplomasmgtapp.model.Subject;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@SpringBootTest
@TestPropertySource(
		locations = "classpath:application.properties"
)
class StudentServiceTest {

	@Autowired
	StudentService studentService;
	@Autowired
	ProfessorService professorService;
	@Autowired
	SubjectService subjectService;

	@Test
	void testStudentServiceImplIsNotNull(){Assertions.assertNotNull(studentService);}

	@Test
	void testRetrieveProfileReturnsNull(){
		Student student = studentService.retrieveProfile("xxxxxx");
		Assertions.assertNull(student);
	}

	@Test
	void testRetrieveProfileReturnsStudent(){
		Student student = new Student("testStudent1");
		studentService.saveProfile(student);
		Student retStudent = studentService.retrieveProfile("testStudent1");
		Assertions.assertNotNull(retStudent);
	}

	@Test
	void testSaveProfile()
	{
		Student testStudent = new Student("testStudent");
		studentService.saveProfile(testStudent);
		Student student = studentService.retrieveProfile("testStudent");
		Assertions.assertNotNull(student);
	}

	@Test
	void testApplyToSubject(){
		Student student = new Student("student");
		student.setApplications(new ArrayList<Application>());
		studentService.saveProfile(student);

		Professor testProfessor = new Professor("testSProfessor");
		professorService.saveProfile(testProfessor);
		testProfessor = professorService.retrieveProfile("testSProfessor");

		Subject subject = new Subject("title","objective", testProfessor);
		subject.setId(99);
		subjectService.save(subject);

		int bef = studentService.listApplications("student").size();
		studentService.applyToSubject("student", 99);
		int aft = studentService.listApplications("student").size();
		Assertions.assertEquals(bef, aft - 1);
	}

}
