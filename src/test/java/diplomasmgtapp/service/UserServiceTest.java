package diplomasmgtapp.service;

import static org.junit.jupiter.api.Assertions.*;

import diplomasmgtapp.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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
	void testIsUserPresentReturnsTrue(){
		User user = new User();
		user.setUsername("testUser");
		user.setPassword("testPassword");
		userService.saveUser(user);
		Assertions.assertTrue(userService.isUserPresent(user));
	}

	@Test
	void testFindByIdReturnsUser(){
		User user = new User();
		user.setUsername("testUser1");
		user.setPassword("testPassword1");
		userService.saveUser(user);
		User returnUser = userService.findById(user.getId());
		Assertions.assertEquals(user.getId(), returnUser.getId());
		Assertions.assertInstanceOf(User.class, returnUser);
	}

}
