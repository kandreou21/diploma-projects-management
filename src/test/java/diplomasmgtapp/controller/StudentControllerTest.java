package diplomasmgtapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import diplomasmgtapp.model.Student;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@AutoConfigureMockMvc
class StudentControllerTest {

	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	StudentController studentController;

	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testStudentControllerIsNotNull() {
		Assertions.assertNotNull(studentController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	@Test 
	void testGetStudentMainMenuReturnsPage() throws Exception {
		mockMvc.perform(get("/student/dashboard")).
		andExpect(status().isOk()).
		andExpect(view().name("student/dashboard"));		
	}
	
	@WithMockUser(value = "student")
	@Test
	void testSetProfileInfoReturnsFormPage() throws Exception {
		mockMvc.perform(get("/student/setProfileInfo")).
		andExpect(status().isOk()).
		andExpect(view().name("student/student-form"));		
	}
	
	@WithMockUser(value = "student")
	@Transactional
	@Test 
	void testSaveProfileReturnsPage() throws Exception {
		
	    Student student = new Student("studentDummy", 5, 10, 8);
	    student.setUsername("studentDummy");
	    
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(student.getId()));
	    multiValueMap.add("fullname", student.getFullname());
	    multiValueMap.add("yearOfStudies", Integer.toString(student.getYearOfStudies()));
	    multiValueMap.add("averageGrade", String.valueOf(student.getAverageGrade()));
	    multiValueMap.add("remainingCourses", Integer.toString(student.getRemainingCourses()));
	    multiValueMap.add("username", student.getUsername());
	    
		mockMvc.perform(
				post("/student/save")
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/student/dashboard"));		
	}
	
	@WithMockUser(value = "student")
	@Test 
	void testListSubjectsReturnsPage() throws Exception {
		mockMvc.perform(get("/student/listAvailableSubjects")).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("allSubjects")).
		andExpect(view().name("student/list-subjects"));		
	}
	
	
	@WithMockUser(value = "student")
	@Transactional
	@Test 
	void testApplyToSubjectReturnsPage() throws Exception {
		mockMvc.perform(
			get("/student/applyToSubject?subjectId=1")).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/student/listAvailableSubjects"));		
	}
	
	@WithMockUser(value = "student")
	@Test 
	void testListApplicationsReturnsPage() throws Exception {
		mockMvc.perform(get("/student/showApplications")).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("applications")).
		andExpect(view().name("student/list-applications"));		
	}
}
