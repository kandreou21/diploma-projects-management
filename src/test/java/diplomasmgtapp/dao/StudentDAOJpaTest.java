package diplomasmgtapp.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import diplomasmgtapp.model.Student;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
public class StudentDAOJpaTest {

	@Autowired 
	StudentDAO studentDAO;
	
	@Test
	void testStudentDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(studentDAO);
	} 

	@Test
	void testFindByUsernameReturnsStudent() {
		Student storedStudent = studentDAO.findByUsername("student");
		Assertions.assertNotNull(storedStudent);
	}
	
	@Test
	void testFindByUsernameReturnsNull() {
		Student storedStudent = studentDAO.findByUsername("xxxxxx");
		Assertions.assertNull(storedStudent);
	}
	
	@Test
	void testFindAll() {
		List<Student> students = studentDAO.findAll();
		Assertions.assertNotNull(students);
	}
}


