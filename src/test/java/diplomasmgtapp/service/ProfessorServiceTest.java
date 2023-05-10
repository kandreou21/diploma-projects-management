package diplomasmgtapp.service;

import diplomasmgtapp.dao.ApplicationDAO;
import diplomasmgtapp.model.*;
import diplomasmgtapp.model.strategies.BestApplicantStrategy;
import diplomasmgtapp.model.strategies.ThresholdStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

		professorService.deleteById(professor.getId());

		Assertions.assertNotNull(retProfessor);
		Assertions.assertInstanceOf(Professor.class, retProfessor);
	}

	@Test
	void testSaveProfile(){
		Professor testProfessor = new Professor("testProfessor", "testSpecialty");
		testProfessor.setUsername("testProfessor");
		professorService.saveProfile(testProfessor);
		Professor professor = professorService.retrieveProfile("testProfessor");

		professorService.deleteById(professor.getId());

		Assertions.assertNotNull(professor);
	}

	@Test
	@Transactional
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

		boolean contain = professorService.listProfessorSubjects("testProfessor3").contains(testSubject);

		subjectService.deleteById(testSubject.getId());
		professorService.deleteById(professor.getId());

		Assertions.assertTrue(contain);
	}

	@Test
	@Transactional
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

		//applicationDAO.deleteById(application.getId());
		subjectService.deleteById(subject.getId());
		professorService.deleteById(professor.getId());
		studentService.deleteById(student.getId());

		Assertions.assertEquals(bef, aft-1);

	}

	@Test
	@Transactional
	void testAssignSubject(){
		Professor professor = new Professor("professor", "specialty");
		professor.setUsername("testProfessor5");
		Subject subject = new Subject("title", "objective", professor);
		Student student = new Student("student", 1, 10, 1);
		student.setUsername("testPStudent2");
		Application application = new Application(student, subject);
		List<Application> list = subject.getApplications();
		list.add(application);
		subject.setApplications(list);


		professorService.saveProfile(professor);
		subjectService.save(subject);
		studentService.saveProfile(student);
		applicationDAO.save(application);

		int bef = professorService.listApplications(subject.getId()).size();

		professorService.assignSubject("testProfessor5", subject.getId(), 0);

		int aft = professorService.listApplications(subject.getId()).size();

		Assertions.assertEquals(aft, bef-1);
	}

	@Test
	@Transactional
	void testAssignThresholdStrategy(){
		Professor professor = new Professor("professor", "specialty");
		professor.setUsername("testProfessor4");
		Subject subject = new Subject("title", "objective", professor);
		Student student = new Student("student", 1, 10, 1);
		student.setUsername("testPStudent1");
		Application application = new Application(student, subject);
		List<Application> list = subject.getApplications();
		list.add(application);
		subject.setApplications(list);


		professorService.saveProfile(professor);
		subjectService.save(subject);
		studentService.saveProfile(student);
		applicationDAO.save(application);

		int bef = professorService.listApplications(subject.getId()).size();

		professorService.assignThresholdStrategy(
				"testProfessor4", subject.getId(), 2, 2);

		int aft = professorService.listApplications(subject.getId()).size();

		Assertions.assertEquals(aft, bef-1);



//		applicationDAO.deleteById(application.getId());
//		subjectService.deleteById(subject.getId());
//		professorService.deleteById(professor.getId());
//		studentService.deleteById(student.getId());
	}
}
