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
class ThresholdStrategyTest {
	private static Subject subject;
	private static Student student;
	private static Student studentOther;
	private static Application appWinner;
	private static Application appOther;

	@Autowired
	private ThresholdStrategy thresholdStrategy;

	@BeforeAll
	static void setup() {
		subject = new Subject();
		student = new Student("student", 5, 5, 5);
		studentOther = new Student("studentOther", 1, 10, 25);
		appWinner = new Application(student, subject);
        appOther = new Application(studentOther, subject);
    }
	
	@Test
	void testThreshold() {
		thresholdStrategy.setGradeThreshold(5);
        thresholdStrategy.setCoursesThreshold(10);
		assertEquals(thresholdStrategy.findBestApplicant(List.of(appWinner, appOther)), appWinner);
	}
	
	@Test
	void testNotFoundBestApplicant() {	//none of the applications meet the thresholds
		thresholdStrategy.setGradeThreshold(10);
        thresholdStrategy.setCoursesThreshold(5);
		assertThrows(NullPointerException.class, () -> thresholdStrategy.findBestApplicant(List.of(appWinner, appOther)));
	}
}
