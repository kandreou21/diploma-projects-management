package diplomasmgtapp.service;

import static org.junit.jupiter.api.Assertions.*;

import diplomasmgtapp.model.Subject;
import diplomasmgtapp.model.Thesis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
		locations = "classpath:application.properties"
)
class ThesisServiceTest {

	@Autowired
	ThesisService thesisService;

	@Test
	void testProfessorServiceImplIsNotNull(){ Assertions.assertNotNull(thesisService); }

	@Test
	void testFindByIDReturnsThesis(){
		Thesis thesis =  new Thesis();
		thesisService.save(thesis);
		Thesis returnThesis = thesisService.findById(thesis.getId());

		thesisService.deleteById(returnThesis.getId());

		Assertions.assertEquals(returnThesis.getId(), thesis.getId());
		Assertions.assertInstanceOf(Thesis.class, returnThesis);
	}

	@Test
	void testUpdateThesis(){
		Thesis befThesis = new Thesis();
		befThesis.setReportGrade(-1.0);
		befThesis.setPresentationGrade(-1.0);
		befThesis.setImplementationGrade(-1.0);
		thesisService.save(befThesis);

		Thesis aftThesis = new Thesis();
		aftThesis.setReportGrade(-2.0);
		aftThesis.setPresentationGrade(-2.0);
		aftThesis.setImplementationGrade(-2.0);

		thesisService.updateThesis(befThesis.getId(), aftThesis);
		double rep = aftThesis.getReportGrade();
		double pre = aftThesis.getPresentationGrade();
		double imp = aftThesis.getImplementationGrade();

		thesisService.deleteById(befThesis.getId());
		thesisService.deleteById(aftThesis.getId());

		Assertions.assertEquals(rep, -2);
		Assertions.assertEquals(pre, -2);
		Assertions.assertEquals(imp, -2);
	}

}
