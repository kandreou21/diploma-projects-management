package diplomasmgtapp.service;

import diplomasmgtapp.model.Professor;
import diplomasmgtapp.model.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@TestPropertySource(
		locations = "classpath:application.properties"
)
class ProfessorServiceTest {

	@Autowired
	ProfessorService professorService;

	@Test
	void testProfessorServiceImplIsNotNull(){Assertions.assertNotNull(professorService);}

	@Test
	void testRetrieveProfileReturnsNull(){
		Professor professorProfile = professorService.retrieveProfile("xxxxxx");
		Assertions.assertNull(professorProfile);
	}

	@Test
	void testRetrieveProfileReturnsProfessor(){
		Professor professorProfile = professorService.retrieveProfile("professor");
		Assertions.assertNotNull(professorProfile);
	}

	@Test
	void testSaveProfile(){
		Professor testProfessor = new Professor("professor");
		professorService.saveProfile(testProfessor);
		Professor professor = professorService.retrieveProfile("professor");
		Assertions.assertNotNull(professor);
	}

	@Test
	void testAddSubject(){
		Professor professor = professorService.retrieveProfile("professor");
		Subject testSubject = new Subject(
				"testTitle",
				"testObjective",
				professor);
		professorService.addSubject("professor", testSubject);
		Assertions.assertTrue(
				professorService.listProfessorSubjects("professor").contains(testSubject));
	}

	@Test
	void testAssignSubjectExplicitly(){
		fail("Not yet implemented");
	}

	@Test
	void testAssignSubject(){
		fail("Not yet implemented");
	}

	@Test
	void testAssignThresholdStrategy(){
		fail("Not yet implemented");
	}
}
