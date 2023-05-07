package diplomasmgtapp.service;

import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Student;
import diplomasmgtapp.model.Subject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import org.junit.jupiter.api.Test;

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
		Student student = studentService.retrieveProfile("student");
		Assertions.assertNotNull(student);
	}

	@Test
	void testSaveProfile()
	{
		Student testStudent = new Student("student");
		studentService.saveProfile(testStudent);
		Student student = studentService.retrieveProfile("student");
		Assertions.assertNotNull(student);
	}

	@Test
	void testApplyToSubject(){
		Student student = studentService.retrieveProfile("student");
		studentService.saveProfile(student);

		Professor testProfessor = new Professor("professor", "testSpecialty");
		testProfessor.setUsername("professor");
		professorService.saveProfile(testProfessor);
		testProfessor = professorService.retrieveProfile("professor");

		Subject subject = new Subject("title","objective", testProfessor);
		subject.setId(99);
		subjectService.save(subject);

		int bef = studentService.listApplications("student").size();
		studentService.applyToSubject("student", 99);
		int aft = studentService.listApplications("student").size();
		Assertions.assertEquals(bef, aft - 1);
	}

}
