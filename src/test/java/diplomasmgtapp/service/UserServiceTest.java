package diplomasmgtapp.service;

import static org.junit.jupiter.api.Assertions.*;

import diplomasmgtapp.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource(
		locations = "classpath:application.properties"
)
class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	void testProfessorServiceImplIsNotNull(){ Assertions.assertNotNull(userService); }

	@Test
	@Transactional
	void testIsUserPresentReturnsTrue(){
		User user = new User();
		user.setUsername("testUser");
		user.setPassword("testPassword");
		userService.saveUser(user);

		boolean present = userService.isUserPresent(user);

		userService.deleteById(user.getId());


		Assertions.assertTrue(present);
	}

	@Test
	@Transactional
	void testFindByIdReturnsUser(){
		User user = new User();
		user.setUsername("testUser1");
		user.setPassword("testPassword1");
		userService.saveUser(user);
		User returnUser = userService.findById(user.getId());

		userService.deleteById(user.getId());
		userService.deleteById(returnUser.getId());

		Assertions.assertEquals(user.getId(), returnUser.getId());
		Assertions.assertInstanceOf(User.class, returnUser);

	}

}
