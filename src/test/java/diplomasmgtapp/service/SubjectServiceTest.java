package diplomasmgtapp.service;

import static org.junit.jupiter.api.Assertions.*;

import diplomasmgtapp.model.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(
		locations = "classpath:application.properties"
)
class SubjectServiceTest {


	@Autowired
	SubjectService subjectService;

	@Test
	void testProfessorServiceImplIsNotNull(){ Assertions.assertNotNull(subjectService); }

	@Test
	void testFindByIDReturnsSubject(){

		Subject subject = new Subject();
		subjectService.save(subject);
		Subject returnSubject = subjectService.findById(subject.getId());
		Assertions.assertEquals(returnSubject.getId(), subject.getId());
	}

	@Test
	void testDeleteByID(){
		Subject subject = new Subject();
		subjectService.save(subject);
		subjectService.deleteById(subject.getId());
		Assertions.assertThrows(RuntimeException.class, () -> {
			subjectService.findById(subject.getId());
		});
	}

	@Test
	void testFindAll(){
		int bef = subjectService.findAll().size();
		Subject subject = new Subject();
		subjectService.save(subject);
		int aft = subjectService.findAll().size();
		assertEquals(bef, aft-1);
	}
}
