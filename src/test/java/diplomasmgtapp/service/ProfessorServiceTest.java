package diplomasmgtapp.service;

import diplomasmgtapp.dao.ApplicationDAO;
import diplomasmgtapp.model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@TestPropertySource(
		locations = "classpath:application.properties"
)
class ProfessorServiceTest {

	@Autowired
	ProfessorService professorService;

	@Autowired
	ApplicationDAO applicationDAO;

	@Autowired
	StudentService studentService;

	@Autowired
	SubjectService subjectService;

	@AfterAll
	static void clear(){
		// find how to delete all objects in db after finishing
	}

	@Test
	void testProfessorServiceImplIsNotNull(){Assertions.assertNotNull(professorService);}

	@Test
	void testRetrieveProfileReturnsNull(){
		Professor professorProfile = professorService.retrieveProfile("xxxxxx");
		Assertions.assertNull(professorProfile);
	}

	@Test
	void testRetrieveProfileReturnsProfessor(){
		Professor professor = new Professor("testProfessor1");
		professorService.saveProfile(professor);
		Professor retProfessor = professorService.retrieveProfile("testProfessor1");
		Assertions.assertNotNull(retProfessor);
		Assertions.assertInstanceOf(Professor.class, retProfessor);
	}

	@Test
	void testSaveProfile(){
		Professor testProfessor = new Professor("testProfessor", "testSpecialty");
		testProfessor.setUsername("testProfessor");
		professorService.saveProfile(testProfessor);
		Professor professor = professorService.retrieveProfile("testProfessor");
		Assertions.assertNotNull(professor);
	}

	@Test
	void testAddSubject(){
		Professor professor = new Professor("professor", "specialty");
		professor.setUsername("testProfessor3");
		professorService.saveProfile(professor);
		Subject testSubject = new Subject(
				"testTitle",
				"testObjective",
				professor);
		subjectService.save(testSubject);
		professorService.addSubject("testProfessor3", testSubject);
		Assertions.assertTrue(
				professorService.listProfessorSubjects("testProfessor3").contains(testSubject));
	}

	@Test
	void testAssignSubjectExplicitly(){
		Student student = new Student("testPStudent");
		Subject subject = new Subject();
		Application application = new Application(student,subject);

		studentService.saveProfile(student);
		subjectService.save(subject);
		applicationDAO.save(application);

		Professor professor = new Professor("professor", "specialty");
		professor.setUsername("testProfessor2");
		professorService.saveProfile(professor);

		int bef = professorService.listProfessorTheses("testProfessor2").size();
		professorService.assignSubjectExplicitly("testProfessor2", application.getId());
		int aft = professorService.listProfessorTheses("testProfessor2").size();
		Assertions.assertEquals(bef, aft-1);
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
