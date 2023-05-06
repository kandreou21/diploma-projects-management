package diplomasmgtapp.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import diplomasmgtapp.model.Application;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class ApplicationDAOJpaTest {

	@Autowired 
	ApplicationDAO applicationDAO;
	
	@Test
	void testApplicationDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(applicationDAO);
	} 

	@Test
	@Sql("insert-application-data.sql")
	void testFindByIdReturnsApplication() {
		Application storedApplication = applicationDAO.findById(100);
		Assertions.assertNotNull(storedApplication);
	}
	
	@Test
	void testDeleteById() {
		applicationDAO.deleteById(100);  
		Application theApplication = applicationDAO.findById(100);
		Assertions.assertNull(theApplication);
	}
}

