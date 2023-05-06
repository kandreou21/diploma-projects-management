package diplomasmgtapp.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import diplomasmgtapp.model.Professor;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
public class ProfessorDAOJpaTest {

	@Autowired 
	ProfessorDAO professorDAO;
	
	@Test
	void testProfessorDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(professorDAO);
	} 

	@Test
	void testFindByUsernameReturnsProfessor() {
		Professor storedProfessor = professorDAO.findByUsername("professor");
		Assertions.assertNotNull(storedProfessor);
	}
	
	@Test
	void testFindByUsernameReturnsNull() {
		Professor storedProfessor = professorDAO.findByUsername("xxxxxx");
		Assertions.assertNull(storedProfessor);
	}
}


