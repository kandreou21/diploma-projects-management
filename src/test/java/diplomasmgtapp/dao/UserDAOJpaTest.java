package diplomasmgtapp.dao;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import diplomasmgtapp.model.User;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
public class UserDAOJpaTest {

	@Autowired 
	UserDAO userDAO;
	
	@Test
	void testUserDAOJpaImplIsNotNull() {
		Assertions.assertNotNull(userDAO);
	} 

	@Test
	void testFindByUsernameReturnsUser() {
		Optional<User> storedUser = userDAO.findByUsername("professor");
		Assertions.assertNotNull(storedUser);
	}
	
	@Test
	void testFindById() {
		User storedUser = userDAO.findById(1);
		Assertions.assertNotNull(storedUser);
	}
}


