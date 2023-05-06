package diplomasmgtapp.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import diplomasmgtapp.model.Subject;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class SubjectDAOJpaTest {

	@Autowired 
	SubjectDAO subjectDAO;
	
	@Test
	void testSubjectDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(subjectDAO);
	} 

	@Test
	void testFindByIdReturnsSubject() {
		Subject storedSubject = subjectDAO.findById(1);
		Assertions.assertNotNull(storedSubject);
	}
	
	@Test
	void testFindByIdReturnsNull() {
		Subject storedSubject = subjectDAO.findById(1000);
		Assertions.assertNull(storedSubject);
	}
	
	@Test
	void testFindAll() {
		List<Subject> subjects = subjectDAO.findAll();  
		Assertions.assertNotEquals(0, subjects.size());
	}
}

