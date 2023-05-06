package diplomasmgtapp.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import diplomasmgtapp.model.Thesis;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class ThesisDAOJpaTest {

	@Autowired 
	ThesisDAO thesisDAO;
	
	@Test
	void testThesisDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(thesisDAO);
	} 

	@Test
	void testFindByIdReturnsThesis() {
		Thesis storedThesis = thesisDAO.findById(1);
		Assertions.assertNotNull(storedThesis);
	}
	
	@Test
	void testFindByIdReturnsNull() {
		Thesis storedThesis = thesisDAO.findById(1000);
		Assertions.assertNull(storedThesis);
	}
}

