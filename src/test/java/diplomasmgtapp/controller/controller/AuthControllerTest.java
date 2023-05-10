package diplomasmgtapp.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import diplomasmgtapp.model.Role;
import diplomasmgtapp.model.User;
import diplomasmgtapp.dao.UserDAO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@AutoConfigureMockMvc
class AuthControllerTest {
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	AuthController authController;

	@Autowired 
	UserDAO userDAO;
	
	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testAuthControllerIsNotNull() {
		Assertions.assertNotNull(authController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	
	@WithMockUser(value = "professor")
	@Test 
	void testLoginReturnsPage() throws Exception {
		mockMvc.perform(get("/login")).
		andExpect(status().isOk()).
		andExpect(view().name("auth/signin"));		
	}
	
	@WithMockUser(value = "professor")
	@Test 
	void testRegisterReturnsPage() throws Exception {
		mockMvc.perform(get("/register")).
		andExpect(status().isOk()).
		andExpect(view().name("auth/signup"));		
	}
	
	@WithMockUser(value = "professor")
	@Test 
	@Transactional
	void testSaveUserReturnsPage() throws Exception {
		
	    User user = new User();
	    user.setUsername("SUTuser");
	    user.setPassword("SUTuser");
	    user.setRole(Role.PROFESSOR);
	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(user.getId()));
	    multiValueMap.add("username", user.getUsername());
	    multiValueMap.add("password", user.getPassword());
	    multiValueMap.add("role", user.getRole().name());
	    
		mockMvc.perform(
				post("/save").
			    params(multiValueMap)).
				andExpect(status().isOk()).
				andExpect(view().name("auth/signin"));	
		
		userDAO.deleteById(user.getId());
	}
}