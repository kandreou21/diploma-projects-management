package diplomasmgtapp.model.strategies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.List;
import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Subject;
import diplomasmgtapp.model.Student;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class BestAvgGradeStrategyTest {
	private static Subject subject;
	private static Student student;
	private static Student studentOther;
	private static Application app;
	private static Application appWinner;

	@Autowired
	private BestAvgGradeStrategy bestAvgGrade;

	@BeforeAll
	static void setup() {
		subject = new Subject();
		student = new Student("student", 5, 5, 5);
		studentOther = new Student("studentOther", 1, 10, 25);
        app = new Application(student, subject);
        appWinner = new Application(studentOther, subject);
    }
	
	@Test
	void testBestAvgGrade() {
		assertEquals(bestAvgGrade.findBestApplicant(List.of(app, appWinner)), appWinner);
	}
}
