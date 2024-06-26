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
import org.springframework.transaction.annotation.Transactional;

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

		studentService.deleteById(retStudent.getId());

		Assertions.assertNotNull(retStudent);
		Assertions.assertInstanceOf(Student.class, retStudent);
	}

	@Test
	void testSaveProfile()
	{
		Student testStudent = new Student("testStudent");
		studentService.saveProfile(testStudent);
		Student student = studentService.retrieveProfile("testStudent");

		studentService.deleteById(student.getId());

		Assertions.assertNotNull(student);
	}

	@Test
	@Transactional
	void testApplyToSubject(){
		Student student = new Student("testStudent");
		student.setApplications(new ArrayList<Application>());
		studentService.saveProfile(student);

		Professor testProfessor = new Professor("testSProfessor");
		professorService.saveProfile(testProfessor);
		testProfessor = professorService.retrieveProfile("testSProfessor");

		Subject subject = new Subject("title","objective", testProfessor);
		subjectService.save(subject);

		int bef = studentService.listApplications("testStudent").size();
		studentService.applyToSubject("testStudent", 99);
		int aft = studentService.listApplications("testStudent").size();

		subjectService.deleteById(subject.getId());
		professorService.deleteById(testProfessor.getId());
		studentService.deleteById(student.getId());

		Assertions.assertEquals(bef, aft - 1);
	}

}
