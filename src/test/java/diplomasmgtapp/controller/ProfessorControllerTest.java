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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Thesis;
import diplomasmgtapp.service.ThesisService;


@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
@AutoConfigureMockMvc
class ProfessorControllerTest {

	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ProfessorController professorController;
	
	@Autowired
	ThesisService thesisService;
	
	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testProfessorControllerIsNotNull() {
		Assertions.assertNotNull(professorController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	@Test 
	void testGetProfessorMainMenuReturnsPage() throws Exception {
		mockMvc.perform(get("/professor/dashboard")).
		andExpect(status().isOk()).
		andExpect(view().name("professor/dashboard"));		
	}
	
	@WithMockUser(value = "professor")
	@Test
	void testSetProfileInfoReturnsFormPage() throws Exception {
		mockMvc.perform(get("/professor/setProfileInfo")).
		andExpect(status().isOk()).
		andExpect(view().name("professor/professor-form"));		
	}
	
	@WithMockUser(value = "professor")
	@Test
	void testRetrieveProfileReturnPage() throws Exception {
		mockMvc.perform(get("/professor/retrieveProfile")).
		andExpect(status().isOk()).
		andExpect(view().name("professor/retrieve-professor"));		
	}
	
	@WithMockUser(value = "professor")
	@Transactional
	@Test 
	void testSaveProfileReturnsPage() throws Exception {
		
	    Professor professor = new Professor();
	    professor.setFullname("professorDummy");
	    professor.setSpecialty("software");
	    professor.setUsername("professorDummy");
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(professor.getId()));
	    multiValueMap.add("fullname", professor.getFullname());
	    multiValueMap.add("specialty", professor.getSpecialty());
	    multiValueMap.add("username", professor.getUsername());
	    
		mockMvc.perform(
				post("/professor/save")
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/professor/dashboard"));		
	}
	
	@WithMockUser(value = "professor")
	@Test 
	void testListProfessorSubjectsReturnsPage() throws Exception {
		mockMvc.perform(get("/professor/listProfessorSubjects")).
		andExpect(status().isOk()).
		andExpect(model().attributeExists("subjects")).
		andExpect(view().name("professor/list-subjects"));		
	}
	
	@WithMockUser(value = "professor")
	@Test
	void testSubjectFormReturnsPage() throws Exception {
		mockMvc.perform(get("/professor/showSubjectForm")).
		andExpect(status().isOk()).
		andExpect(view().name("professor/subject-form"));		
	}
	
	@WithMockUser(value = "professor")
	@Transactional
	@Test
	void testAddSubjectReturnsPage() throws Exception {
		mockMvc.perform(get("/professor/addSubject?subjectId=1")).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/professor/listProfessorSubjects"));		
	}
	
	@WithMockUser(value = "professor")
	@Test 
	void testListApplicationsReturnsPage() throws Exception {
		mockMvc.perform(
			get("/professor/listApplications?subjectId=1")).
			andExpect(model().attributeExists("applications")).
			andExpect(view().name("professor/list-applications"));		
	}
	
	@Sql("insert-application-data.sql")
	@WithMockUser(value = "professor")
	@Transactional
	@Test 
	void testAssignExplicitlyReturnsPage() throws Exception {
		mockMvc.perform(
			get("/professor/assignExplicitly?applicationId=100")).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/professor/listProfessorSubjects"));		
	}
	
	@Sql("insert-application-data.sql")
	@WithMockUser(value = "professor")
	@Transactional
	@Test 
	void testAssignSubjectReturnsPage() throws Exception {
		mockMvc.perform(
			get("/professor/assign?subjectId=1&strategyIndex=0")).
			andExpect(status().isFound()).
			andExpect(view().name("redirect:/professor/listProfessorSubjects"));		
	}
	
	@WithMockUser(value = "professor")
	@Test 
	void testListProfessorThesesReturnsPage() throws Exception {
		mockMvc.perform(get("/professor/listProfessorTheses")).
		andExpect(model().attributeExists("theses")).
		andExpect(view().name("professor/list-theses"));		
	}
	
	@WithMockUser(value = "professor")
	@Test
	void testShowThesisFormReturnsPage() throws Exception {
		mockMvc.perform(get("/professor/showThesisForm?thesisId=1")).
		andExpect(status().isOk()).
		andExpect(view().name("professor/thesis-form"));		
	}
	
	@WithMockUser(value = "professor")
	@Transactional
	@Test 
	void testSaveThesisReturnsPage() throws Exception {
		
	    Thesis thesis = thesisService.findById(1);
	    	    
	    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(thesis.getId()));
	    multiValueMap.add("implementationGrade", String.valueOf(thesis.getImplementationGrade()));
	    multiValueMap.add("reportGrade", String.valueOf(thesis.getReportGrade()));
	    multiValueMap.add("presentationGrade", String.valueOf(thesis.getPresentationGrade()));
	    
		mockMvc.perform(
				post("/professor/saveThesis?thesisId=1")
			    .params(multiValueMap))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/professor/listProfessorTheses"));		
	}
	
	@WithMockUser(value = "professor")
	@Test
	void testShowGradesReturnsPage() throws Exception {
		mockMvc.perform(get("/professor/showGrades?thesisId=1")).
		andExpect(status().isOk()).
		andExpect(view().name("professor/show-grades"));		
	}
}
