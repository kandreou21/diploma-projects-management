package diplomasmgtapp.service;

import diplomasmgtapp.model.Student;
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
		Assertions.fail("Not implemented yet");
	}

}
